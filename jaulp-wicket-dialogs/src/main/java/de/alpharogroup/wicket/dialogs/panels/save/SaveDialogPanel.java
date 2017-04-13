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
package de.alpharogroup.wicket.dialogs.panels.save;

import lombok.Getter;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.model.IModel;

import de.alpharogroup.resourcebundle.locale.ResourceBundleKey;
import de.alpharogroup.wicket.base.BasePanel;
import de.alpharogroup.wicket.components.factory.ComponentFactory;

/**
 * The Class {@link SaveDialogPanel}.
 *
 * @param <T>
 *            the generic type of the model object
 */
public class SaveDialogPanel<T> extends BasePanel<T>
{

	/** The serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * the description label.
	 */
	@Getter
	private final Label descriptionLabel;

	/**
	 * the cancel label.
	 */
	@Getter
	private final Label cancelLabel;

	/**
	 * the save label.
	 */
	@Getter
	private final Label saveLabel;

	/**
	 * the cancel button.
	 */
	@Getter
	private final AjaxButton cancelButton;

	/**
	 * the save button.
	 */
	@Getter
	private final AjaxButton saveButton;

	/** The label text field. */
	@Getter
	private final Component textFieldLabel;

	/**
	 * the form.
	 */
	@Getter
	private final Form<?> form;
	/** The text field. */
	@Getter
	private final Component textField;

	/**
	 * Instantiates a new {@link SaveDialogPanel}.
	 *
	 * @param id
	 *            the id
	 * @param model
	 *            the model
	 */
	public SaveDialogPanel(final String id, final IModel<T> model)
	{
		super(id, model);
		setOutputMarkupId(true);
		add(form = newForm("form", model));
		form.add(descriptionLabel = newDescriptionLabel("descriptionLabel", ResourceBundleKey
			.builder().key("save.description.label").build()));
		form.add(textFieldLabel = newTextFieldLabel("textFieldLabel", ResourceBundleKey.builder()
			.key("main.global.form.textfield.label").build()));
		form.add(textField = newTextField("textField", model));

		form.add(cancelButton = newCancelButton("cancelButton", form));
		cancelButton.add(cancelLabel = newCancelLabel("cancelLabel", ResourceBundleKey.builder()
			.key("main.global.cancel.button.label").defaultValue("Cancel").build()));
		form.add(saveButton = newSaveButton("saveButton", form));
		saveButton.add(saveLabel = newSaveLabel("saveLabel",
			ResourceBundleKey.builder().key("main.global.save.button.label").defaultValue("Save")
				.build()));
	}

	/**
	 * Factory method for creating a new cancel {@link AjaxButton}. This method is invoked in the
	 * constructor from the derived classes and can be overridden so users can provide their own
	 * version of a cancel {@link AjaxButton}.
	 *
	 * @param id
	 *            the id
	 * @param form
	 *            the form
	 * @return the new {@link AjaxButton}
	 */
	protected AjaxButton newCancelButton(final String id, final Form<?> form)
	{
		final AjaxButton cancelButton = new AjaxButton(id, form)
		{
			/**
			 * The serialVersionUID.
			 */
			private static final long serialVersionUID = 1L;

			/**
			 * {@inheritDoc}
			 */
			@Override
			protected void onSubmit(final AjaxRequestTarget target, final Form<?> form)
			{
				onCancel(target, form);
			}
		};
		return cancelButton;
	}

	/**
	 * Factory method for creating a new cancel {@link Label} with a {@link ResourceBundleKey}. This
	 * method is invoked in the constructor from the derived classes and can be overridden so users
	 * can provide their own version of a cancel {@link Label}.
	 *
	 * @param id
	 *            the id
	 * @param resourceKey
	 *            the resource key
	 * @return the new {@link Label}
	 */
	protected Label newCancelLabel(final String id, final ResourceBundleKey resourceKey)
	{
		return ComponentFactory.newLabel(id, resourceKey, this);
	}

	/**
	 * Factory method for creating a new description {@link Label} with a {@link ResourceBundleKey}.
	 * This method is invoked in the constructor from the derived classes and can be overridden so
	 * users can provide their own version of a description {@link Label}.
	 *
	 * @param id
	 *            the id
	 * @param resourceKey
	 *            the resource key
	 * @return the new {@link Label}
	 */
	protected Label newDescriptionLabel(final String id, final ResourceBundleKey resourceKey)
	{
		return ComponentFactory.newLabel(id, resourceKey, this);
	}

	/**
	 * Factory method for creating a new {@link Form}. This method is invoked in the constructor
	 * from the derived classes and can be overridden so users can provide their own version of a
	 * {@link Form}.
	 *
	 * @param id
	 *            the id
	 * @param model
	 *            the model
	 * @return the Form
	 */
	protected Form<?> newForm(final String id, final IModel<T> model)
	{
		return ComponentFactory.newForm(id, model);
	}

	/**
	 * Factory method for creating a new save {@link AjaxButton}. This method is invoked in the
	 * constructor from the derived classes and can be overridden so users can provide their own
	 * version of a save {@link AjaxButton}.
	 *
	 * @param id
	 *            the id
	 * @param form
	 *            the form
	 * @return the new {@link AjaxButton}
	 */
	protected AjaxButton newSaveButton(final String id, final Form<?> form)
	{
		final AjaxButton saveButton = new AjaxButton(id, form)
		{
			/**
			 * The serialVersionUID.
			 */
			private static final long serialVersionUID = 1L;

			/**
			 * {@inheritDoc}
			 */
			@Override
			protected void onSubmit(final AjaxRequestTarget target, final Form<?> form)
			{
				onSave(target, form);
			}
		};
		return saveButton;
	}

	/**
	 * Factory method for creating a new save {@link Label} with a {@link ResourceBundleKey}. This
	 * method is invoked in the constructor from the derived classes and can be overridden so users
	 * can provide their own version of a save {@link Label}.
	 *
	 * @param id
	 *            the id
	 * @param resourceKey
	 *            the resource key
	 * @return the new {@link Label}
	 */
	protected Label newSaveLabel(final String id, final ResourceBundleKey resourceKey)
	{
		return ComponentFactory.newLabel(id, resourceKey, this);
	}

	protected Component newTextField(final String id, final IModel<T> model)
	{
		return ComponentFactory.newTextField(id, model);
	}

	protected Component newTextFieldLabel(final String id, final ResourceBundleKey resourceKey)
	{
		return ComponentFactory.newLabel(id, resourceKey, this);
	}

	/**
	 * Callback method for cancel.
	 *
	 * @param target
	 *            the target
	 * @param form
	 *            the form
	 */
	protected void onCancel(final AjaxRequestTarget target, final Form<?> form)
	{
		target.add(form);
	}

	/**
	 * Callback method for save.
	 *
	 * @param target
	 *            the target
	 * @param form
	 *            the form
	 */
	protected void onSave(final AjaxRequestTarget target, final Form<?> form)
	{
		target.add(form);
	}

}