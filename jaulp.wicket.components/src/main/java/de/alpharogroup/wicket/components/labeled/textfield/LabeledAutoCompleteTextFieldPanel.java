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
package de.alpharogroup.wicket.components.labeled.textfield;

import org.apache.wicket.extensions.ajax.markup.html.autocomplete.AutoCompleteTextField;
import org.apache.wicket.markup.html.form.FormComponent;
import org.apache.wicket.model.IModel;

import de.alpharogroup.wicket.components.labeled.LabeledFormComponentPanel;
import lombok.Getter;

/**
 * Convenience class for labeled {@link AutoCompleteTextField}.
 *
 * @param <T>
 *            the generic type of model object from the {@link AutoCompleteTextField}
 * @param <M>
 *            the generic type of model object
 */
public abstract class LabeledAutoCompleteTextFieldPanel<T, M> extends LabeledFormComponentPanel<T, M>
{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The text field. */
	@Getter
	private final AutoCompleteTextField<T> autoCompleteTextField;

	/**
	 * Instantiates a new {@link LabeledAutoCompleteTextFieldPanel}.
	 *
	 * @param id
	 *            the id
	 * @param model
	 *            the model
	 * @param labelModel
	 *            the label model
	 */
	public LabeledAutoCompleteTextFieldPanel(final String id, final IModel<M> model,
		final IModel<String> labelModel)
	{
		super(id, model, labelModel);

		setOutputMarkupId(true);

		add(autoCompleteTextField = newAutoCompleteTextField("autoCompleteTextField", model));

		add(feedback = newComponentFeedbackPanel("feedback", autoCompleteTextField));

		final String markupId = autoCompleteTextField.getMarkupId();
		add(label = newLabel("label", markupId, getLabel()));
	}

	/**
	 * Abstract Factory method for create the new {@link AutoCompleteTextField}. This method is
	 * invoked in the constructor from the derived classes and have to be implemented so users can
	 * provide their own version of a new {@link AutoCompleteTextField}.
	 *
	 * @param id
	 *            the id
	 * @param model
	 *            the model
	 * @return the new {@link AutoCompleteTextField}.
	 */
	protected abstract AutoCompleteTextField<T> newAutoCompleteTextField(final String id,
		final IModel<M> model);

	/**
	 * {@inheritDoc}
	 */
	@Override
	public FormComponent<T> getFormComponent()
	{
		return this.autoCompleteTextField;
	}

}
