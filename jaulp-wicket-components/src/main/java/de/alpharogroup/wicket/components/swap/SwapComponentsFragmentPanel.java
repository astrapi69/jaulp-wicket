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

import org.apache.wicket.Component;
import org.apache.wicket.markup.html.panel.Fragment;
import org.apache.wicket.model.IModel;

/**
 * The abstract class {@link SwapComponentsFragmentPanel} provides the feature to swap components.
 * The components can be created with the abstract factory methods that have to be implemented.
 *
 * @param <T>
 *            the generic type of the model object.
 */
public abstract class SwapComponentsFragmentPanel<T> extends SwapFragmentPanel<T>
{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new {@link SwapComponentsFragmentPanel}.
	 *
	 * @param id
	 *            the id
	 * @param model
	 *            the model
	 */
	public SwapComponentsFragmentPanel(final String id, final IModel<T> model)
	{
		super(id, model);
	}

	/**
	 * Abstract factory method for the new editable {@link Component}. This method is invoked in the
	 * method {@link SwapComponentsFragmentPanel#newEditFragment(String)} and have to be overridden
	 * so users can provide their own version of a new editable {@link Component}.
	 *
	 * @param id
	 *            the id
	 * @param model
	 *            the model
	 * @return the new editable {@link Component}.
	 */
	protected abstract Component newEditComponent(final String id, final IModel<T> model);

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected Fragment newEditFragment(final String id)
	{
		final Fragment editFragment = new Fragment(id, "edit", this, getDefaultModel());
		editFragment.setOutputMarkupPlaceholderTag(true);
		editFragment.add(newEditComponent("editComponent", getModel()));
		return editFragment;
	}

	/**
	 * Abstract factory method for the new {@link Component} for the view. This method is invoked in
	 * the method {@link SwapComponentsFragmentPanel#newViewFragment(String)} and have to be
	 * overridden so users can provide their own version of a new {@link Component} for the view.
	 *
	 * @param id
	 *            the id
	 * @param model
	 *            the model
	 * @return the new {@link Component} for the view.
	 */
	protected abstract Component newViewComponent(final String id, final IModel<T> model);


	/**
	 * {@inheritDoc}
	 */
	@Override
	protected Fragment newViewFragment(final String id)
	{
		final Fragment viewFragment = new Fragment(id, "view", this, getModel());
		viewFragment.setOutputMarkupPlaceholderTag(true);
		viewFragment.add(newViewComponent("viewComponent", getModel()));
		return viewFragment;
	}

}
