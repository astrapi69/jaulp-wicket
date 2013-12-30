package org.jaulp.wicket.components.editable.textfield;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;

/**
 * An editable TextField that can be switched to a Label.
 */
public class EditableTextField extends Panel {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The flag editable. */
	private boolean editable;
	
	/** The Label component. */
	private final Label label;

	/** The text field. */
	private final TextField<String> textField;

	/**
	 * Checks if is editable.
	 * 
	 * @return true, if it is editable
	 */
	public boolean isEditable() {
		return editable;
	}

	/**
	 * Instantiates a new editable text field.
	 *
	 * @param id the id
	 * @param model the model
	 */
	public EditableTextField(String id, IModel<String> model) {
		super(id, model);
		add(label = newLabel("label", model));
		add(textField = newTextField("textField", model));
	}
	
	/**
	 * Factory method for creating the MultiLineLabel. This method is invoked in the
	 * constructor from the derived classes and can be overridden so users can
	 * provide their own version of a MultiLineLabel.
	 *
	 * @param id the id
	 * @param model the model
	 * @return the MultiLineLabel
	 */
	protected Label newLabel(String id, IModel<String> model) {
		Label label = new Label(id, model){
			private static final long serialVersionUID = 1L;
			@Override
			public boolean isVisible() {
				return !isEditable();
			}
		};
		label.setOutputMarkupId(true).setOutputMarkupPlaceholderTag(true);
		return label;
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
	protected TextField<String> newTextField(String id, IModel<String> model) {
		TextField<String> textField = new TextField<String>(id, model){
			private static final long serialVersionUID = 1L;
			@Override
			public boolean isVisible() {
				return isEditable();
			}
		};
		textField.setOutputMarkupId(true).setOutputMarkupPlaceholderTag(true);
		return textField;
	}

	/**
	 * Gets the label.
	 *
	 * @return the label
	 */
	public Label getLabel() {
		return label;
	}

	/**
	 * Gets the text field.
	 *
	 * @return the text field
	 */
	public TextField<String> getTextField() {
		return textField;
	}

}
