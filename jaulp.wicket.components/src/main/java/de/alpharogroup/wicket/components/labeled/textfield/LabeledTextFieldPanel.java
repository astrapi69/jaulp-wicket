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

import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;
import de.alpharogroup.wicket.components.labeled.LabeledFormComponentPanel;

/**
 * Convenience class for labeled TextField.
 *
 * @param <T> the generic type
 */
public class LabeledTextFieldPanel<T> extends LabeledFormComponentPanel<T> {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
		
	/** The text field. */
	private final TextField<T> textField;

	/**
	 * Instantiates a new LabeledTextfieldPanel.
	 *
	 * @param id the id
	 */
	public LabeledTextFieldPanel(String id) {
		this(id, null, null);
	}

	/**
	 * Instantiates a new LabeledTextfieldPanel.
	 *
	 * @param id the id
	 * @param model the model
	 * @param labelModel the label model
	 */
	public LabeledTextFieldPanel(String id, IModel<T> model, IModel<String> labelModel) {
		super(id, model, labelModel);		
		
		add(textField = newTextField("textField", model));

		add(feedback = newComponentFeedbackPanel("feedback", textField));

		String markupId = textField.getMarkupId();
		add(label = newLabel("label", markupId, getLabel()));
	}

	/**
	 * {@inheritDoc}
	 */
	public void convertInput() {
		setConvertedInput(textField.getConvertedInput());
	}

	/**
	 * {@inheritDoc}
	 */
	public String getInput() {
		return textField.getInput();
	}

	/**
	 * Gets the text field.
	 *
	 * @return the text field
	 */
	public TextField<T> getTextField() {
		return textField;
	}

	/**
	 * Factory method for creating the TextField. This method is invoked in the
	 * constructor from the derived classes and can be overridden so users can
	 * provide their own version of a TextField.
	 *
	 * @param id the id
	 * @param model the model
	 * @return the text field
	 */
	protected TextField<T> newTextField(String id, IModel<T> model) {
		PropertyModel<T> textFieldModel = new PropertyModel<T>(model.getObject(), getId());
		TextField<T> textField = new TextField<T>(id, textFieldModel);
		textField.setOutputMarkupId(true);
		return textField;
	}

	/**
	 * {@inheritDoc}
	 */
	protected void onBeforeRender() {
		textField.setRequired(isRequired());
		super.onBeforeRender();
	}
}
