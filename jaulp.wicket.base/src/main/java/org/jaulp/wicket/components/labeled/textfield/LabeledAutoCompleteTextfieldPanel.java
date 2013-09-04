package org.jaulp.wicket.components.labeled.textfield;

import org.apache.wicket.extensions.ajax.markup.html.autocomplete.AutoCompleteTextField;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;
import org.jaulp.wicket.components.labeled.LabeledFormComponentPanel;

/**
 * Convenience class for labeled TextField.
 *
 * @param <T> the generic type
 */
public abstract class LabeledAutoCompleteTextfieldPanel<T> extends LabeledFormComponentPanel<T> {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
		
	/** The text field. */
	private final AutoCompleteTextField<T> autoCompleteTextField;

	/**
	 * Instantiates a new LabeledTextfieldPanel.
	 *
	 * @param id the id
	 */
	public LabeledAutoCompleteTextfieldPanel(String id) {
		this(id, null, null);
	}

	/**
	 * Instantiates a new LabeledTextfieldPanel.
	 *
	 * @param id the id
	 * @param model the model
	 * @param labelModel the label model
	 */
	public LabeledAutoCompleteTextfieldPanel(String id, IModel<T> model, IModel<String> labelModel) {
		super(id, model, labelModel);
		
		PropertyModel<T> textFieldModel = new PropertyModel<T>(model.getObject(), id);
		add(autoCompleteTextField = newAutoCompleteTextField("autoCompleteTextField", textFieldModel));

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
