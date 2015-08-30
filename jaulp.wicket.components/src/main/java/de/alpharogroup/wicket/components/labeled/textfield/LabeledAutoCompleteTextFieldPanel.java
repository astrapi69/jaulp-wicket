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

import lombok.Getter;

import org.apache.wicket.extensions.ajax.markup.html.autocomplete.AutoCompleteTextField;
import org.apache.wicket.model.IModel;

import de.alpharogroup.wicket.components.labeled.LabeledFormComponentPanel;

/**
 * Convenience class for labeled {@link AutoCompleteTextField}.
 *
 * @param <T>
 *            the generic type
 */
public abstract class LabeledAutoCompleteTextFieldPanel<T> extends LabeledFormComponentPanel<T>
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
	public LabeledAutoCompleteTextFieldPanel(final String id, final IModel<T> model,
		final IModel<String> labelModel)
	{
		super(id, model, labelModel);

		add(autoCompleteTextField = newAutoCompleteTextField("autoCompleteTextField", model));

		add(feedback = newComponentFeedbackPanel("feedback", autoCompleteTextField));

		final String markupId = autoCompleteTextField.getMarkupId();
		add(label = newLabel("label", markupId, getLabel()));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void convertInput()
	{
		setConvertedInput(autoCompleteTextField.getConvertedInput());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getInput()
	{
		return autoCompleteTextField.getInput();
	}

	/**
	 * Abstract Factory method for create the new {@link AutoCompleteTextField}. This method is
	 * invoked in the constructor from the derived classes and must be implemented so users can
	 * provide their own version of a new {@link AutoCompleteTextField}.
	 *
	 * @param id
	 *            the id
	 * @param model
	 *            the model
	 * @return the new {@link AutoCompleteTextField}.
	 */
	protected abstract AutoCompleteTextField<T> newAutoCompleteTextField(final String id,
		final IModel<T> model);

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void onBeforeRender()
	{
		autoCompleteTextField.setRequired(isRequired());
		super.onBeforeRender();
	}
}
