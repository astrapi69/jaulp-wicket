package org.jaulp.wicket.components.textfield;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.FormComponentPanel;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;

public class LabeledTextfieldPanel extends FormComponentPanel<String> {
	@SuppressWarnings("unused")
	private String text;
	private TextField<String> textField;
	@SuppressWarnings("unused")
	private Label label;

	public LabeledTextfieldPanel(String id) {
		this(id, null);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public LabeledTextfieldPanel(String id, IModel<String> model) {
		super(id, model);
		PropertyModel<String> textFieldModel = new PropertyModel<String>(this, "text");
		add(label = new Label("label", model));
		add(textField = new TextField<String>("textfield", textFieldModel));
	}

	public String getInput() {
		return textField.getInput();
	}

	protected void convertInput() {
		setConvertedInput(textField.getConvertedInput());
	}

	protected void onBeforeRender() {
		text = (String) getModelObject();
		textField.setRequired(isRequired());
		super.onBeforeRender();
	}
}
