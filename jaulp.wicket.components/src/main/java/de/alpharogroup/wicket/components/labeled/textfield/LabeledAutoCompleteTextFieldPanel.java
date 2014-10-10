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
import org.apache.wicket.model.IModel;
import de.alpharogroup.wicket.components.labeled.LabeledFormComponentPanel;

/**
 * Convenience class for labeled TextField.
 *
 * @param <T> the generic type
 */
public abstract class LabeledAutoCompleteTextFieldPanel<T> extends LabeledFormComponentPanel<T> {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
		
	/** The text field. */
	private final AutoCompleteTextField<T> autoCompleteTextField;

	/**
	 * Instantiates a new LabeledTextfieldPanel.
	 *
	 * @param id the id
	 */
	public LabeledAutoCompleteTextFieldPanel(String id) {
		this(id, null, null);
	}

	/**
	 * Instantiates a new LabeledTextfieldPanel.
	 *
	 * @param id the id
	 * @param model the model
	 * @param labelModel the label model
	 */
	public LabeledAutoCompleteTextFieldPanel(String id, IModel<T> model, IModel<String> labelModel) {
		super(id, model, labelModel);
		
		add(autoCompleteTextField = newAutoCompleteTextField("autoCompleteTextField", model));

		add(feedback = newComponentFeedbackPanel("feedback", autoCompleteTextField));

		String markupId = autoCompleteTextField.getMarkupId();
		add(label = newLabel("label", markupId, getLabel()));
	}

	/**
	 * {@inheritDoc}
	 */
	protected void convertInput() {
		setConvertedInput(autoCompleteTextField.getConvertedInput());
	}

	/**
	 * {@inheritDoc}
	 */
	public String getInput() {
		return autoCompleteTextField.getInput();
	}

	/**
	 * Gets the text field.
	 *
	 * @return the text field
	 */
	public AutoCompleteTextField<T> getAutoCompleteTextField() {
		return autoCompleteTextField;
	}

	/**
	 * Abstract Factory method for creating the AutoCompleteTextField. This method is invoked in the
	 * constructor from the derived classes and must be implemented so users can
	 * provide their own version of a AutoCompleteTextField.
	 *
	 * @param id the id
	 * @param model the model
	 * @return the AutoCompleteTextField
	 */
	protected abstract AutoCompleteTextField<T> newAutoCompleteTextField(String id, IModel<T> model);

	/**
	 * {@inheritDoc}
	 */
	protected void onBeforeRender() {
		autoCompleteTextField.setRequired(isRequired());
		super.onBeforeRender();
	}
}
