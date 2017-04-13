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
package de.alpharogroup.wicket.dialogs.panels.confirm;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.model.IModel;

import de.alpharogroup.resourcebundle.locale.ResourceBundleKey;
import de.alpharogroup.wicket.base.BasePanel;
import de.alpharogroup.wicket.base.util.resource.ResourceModelFactory;
import de.alpharogroup.wicket.components.factory.ComponentFactory;
import lombok.Getter;

/**
 * The Class YesNoPanel.
 *
 * @param <T>
 *            the generic type of the model object
 */
public abstract class YesNoPanel<T> extends BasePanel<T>
{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The Label component. */
	@Getter
	private final Label label;

	/** The no button. */
	@Getter
	private final AjaxButton noButton;

	/** The yes button. */
	@Getter
	private final AjaxButton yesButton;

	/**
	 * Instantiates a new yes no panel.
	 *
	 * @param id
	 *            the id
	 * @param model
	 *            the model
	 * @param labelModel
	 *            the label model
	 */
	public YesNoPanel(final String id, final IModel<T> model, final IModel<String> labelModel)
	{
		super(id, model);
		add(label = newLabel("message", labelModel));
		add(yesButton = newYesButton("yesButton"));
		add(noButton = newNoButton("noButton"));
	}

	/**
	 * Factory method for creating the Label. This method is invoked in the constructor from the
	 * derived classes and can be overridden so users can provide their own version of a Label.
	 *
	 * @param id
	 *            the id
	 * @param model
	 *            the model
	 * @return the label
	 */
	protected Label newLabel(final String id, final IModel<String> model)
	{
		return ComponentFactory.newLabel(id, model);
	}

	/**
	 * Factory method for creating a new no {@link AjaxButton}. This method is invoked in the
	 * constructor from the derived classes and can be overridden so users can provide their own
	 * version of a no {@link AjaxButton}.
	 *
	 * @param id
	 *            the id
	 * @return the new {@link AjaxButton}
	 */
	protected AjaxButton newNoButton(final String id)
	{
		final AjaxButton ajaxButton = new AjaxButton(id)
		{
			/**
			 * The serialVersionUID.
			 */
			private static final long serialVersionUID = 1L;

			@Override
			protected void onError(final AjaxRequestTarget target, final Form<?> form)
			{
				onNo(target, form, true);
			}

			@Override
			protected void onSubmit(final AjaxRequestTarget target, final Form<?> form)
			{
				onNo(target, form, false);
			}
		};
		final IModel<String> noLabelModel = ResourceModelFactory.newResourceModel(
			ResourceBundleKey.builder().key("global.no.label").defaultValue("No").build(), this);
		ajaxButton.add(newLabel("noLabel", noLabelModel));
		return ajaxButton;
	}


	/**
	 * Factory method for creating a new yes {@link AjaxButton}. This method is invoked in the
	 * constructor from the derived classes and can be overridden so users can provide their own
	 * version of a yes {@link AjaxButton}.
	 *
	 * @param id
	 *            the id
	 * @return the new {@link AjaxButton}
	 */
	protected AjaxButton newYesButton(final String id)
	{
		final AjaxButton ajaxButton = new AjaxButton(id)
		{
			/**
			 * The serialVersionUID.
			 */
			private static final long serialVersionUID = 1L;

			@Override
			protected void onError(final AjaxRequestTarget target, final Form<?> form)
			{
				onYes(target, form, true);
			}

			@Override
			protected void onSubmit(final AjaxRequestTarget target, final Form<?> form)
			{
				onYes(target, form, false);
			}

		};
		final IModel<String> yesLabelModel = ResourceModelFactory.newResourceModel(
			ResourceBundleKey.builder().key("global.yes.label").defaultValue("Yes").build(), this);
		ajaxButton.add(newLabel("yesLabel", yesLabelModel));
		return ajaxButton;
	}

	/**
	 * On no.
	 *
	 * @param target
	 *            the target
	 * @param form
	 *            the form
	 * @param error
	 *            the flag if an error occured.
	 */
	public abstract void onNo(final AjaxRequestTarget target, final Form<?> form,
		final boolean error);


	/**
	 * On yes.
	 *
	 * @param target
	 *            the target.
	 * @param form
	 *            the form.
	 * @param error
	 *            the flag if an error occured.
	 */
	public abstract void onYes(final AjaxRequestTarget target, final Form<?> form,
		final boolean error);

}
