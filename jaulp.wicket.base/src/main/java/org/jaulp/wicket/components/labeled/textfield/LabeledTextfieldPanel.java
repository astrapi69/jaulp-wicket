package org.jaulp.wicket.components.labeled.textfield;

import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;
import org.jaulp.wicket.components.labeled.LabeledFormComponentPanel;

/**
 * Convenience class for labeled TextField.
 *
 * @param <T> the generic type
 */
public class LabeledTextfieldPanel<T> extends LabeledFormComponentPanel<T> {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
		
	/** The text field. */
	private final TextField<T> textField;

	/**
	 * Instantiates a new LabeledTextfieldPanel.
	 *
	 * @param id the id
	 */
	public LabeledTextfieldPanel(String id) {
		this(id, null, null);
	}

	/**
	 * Instantiates a new LabeledTextfieldPanel.
	 *
	 * @param id the id
	 * @param model the model
	 * @param labelModel the label model
	 */
	public LabeledTextfieldPanel(String id, IModel<T> model, IModel<String> labelModel) {
		super(id, model, labelModel);
		
		PropertyModel<T> textFieldModel = new PropertyModel<T>(model.getObject(), id);
		add(textField = newTextField("textField", textFieldModel));

		add(feedback = newComponentFeedbackPanel("feedback", textField));

		String markupId = textField.getMarkupId();
		add(label = newLabel("label", markupId, getLabel()));
	}

	/**
	 * {@inheritDoc}
	 */
	protected void convertInput() {
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
		TextField<T> textField = new TextField<T>(id, model);
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
