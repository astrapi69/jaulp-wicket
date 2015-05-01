/**
 * Copyright (C) 2010 Asterios Raptis
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package de.alpharogroup.wicket.components.swap;

import lombok.Getter;

import org.apache.log4j.Logger;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.Fragment;
import org.apache.wicket.markup.html.panel.GenericPanel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.util.lang.Args;

/**
 * The abstract class SwapFragmentPanel holds to Fragment that can be swapped.
 *
 * @param <T>
 *            the generic type of the model object.
 */
public abstract class SwapFragmentPanel<T> extends GenericPanel<T>
{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	/** The Constant logger. */
	protected static final Logger LOGGER = Logger.getLogger(SwapFragmentPanel.class.getName());

	/** The view fragment. */
	@Getter
	private Fragment view;

	/** The edit fragment. */
	@Getter
	private Fragment edit;

	/** The ModeContext shows if the view mode or edit mode is visible. */
	@Getter
	private ModeContext modeContext = ModeContext.VIEW_MODE;

	/** The id for the placeholder where to swap the fragments. */
	private static final String FRAGMENT_ID = "fragment-placeholder";

	/**
	 * Instantiates a new swap fragment panel.
	 *
	 * @param id
	 *            the id
	 * @param model
	 *            the model
	 */
	public SwapFragmentPanel(String id, IModel<T> model)
	{
		super(id, Args.notNull(model, "model"));
		setOutputMarkupPlaceholderTag(true);
		add(view = newViewFragment(FRAGMENT_ID));
		edit = newEditFragment(FRAGMENT_ID);
	}

	/**
	 * Swap the fragments.
	 */
	private void swapFragments()
	{
		Fragment fragment = view;
		view.replaceWith(edit);
		view = edit;
		edit = fragment;
	}

	/**
	 * Abstract factory method for the view fragment.
	 *
	 * @param id
	 *            the id
	 * @return the view fragment
	 */
	protected abstract Fragment newViewFragment(final String id);


	/**
	 * Abstract factory method for the edit fragment.
	 *
	 * @param id
	 *            the id
	 * @return the edit fragment
	 */
	protected abstract Fragment newEditFragment(final String id);

	/**
	 * Swaps from the view fragment to the edit fragment.
	 *
	 * @param target
	 *            the target
	 * @param form
	 *            the form
	 */
	public void onSwapToEdit(final AjaxRequestTarget target, final Form<?> form)
	{
		swapFragments();
		if (target != null)
		{
			target.add(view);
		}
		else
		{
			LOGGER
				.error("AjaxRequestTarget is null on method SwapFragmentPanel#onSwapToEdit(AjaxRequestTarget, Form)");
		}
		modeContext = ModeContext.EDIT_MODE;
	}

	/**
	 * Swaps from the edit fragment to the view fragment.
	 *
	 * @param target
	 *            the target
	 * @param form
	 *            the form
	 */
	public void onSwapToView(final AjaxRequestTarget target, final Form<?> form)
	{
		if (target != null)
		{
			target.add(edit);
		}
		else
		{
			LOGGER
				.error("AjaxRequestTarget is null on method SwapFragmentPanel#onSwapToView(AjaxRequestTarget, Form)");
		}
		swapFragments();
		modeContext = ModeContext.VIEW_MODE;
	}

	/**
	 * Swaps the fragments.
	 *
	 * @param target
	 *            the target
	 * @param form
	 *            the form
	 */
	protected void swapFragments(final AjaxRequestTarget target, final Form<?> form)
	{
		if (modeContext.equals(ModeContext.VIEW_MODE))
		{
			onSwapToEdit(target, form);
		}
		else
		{
			onSwapToView(target, form);
		}
	}

}
