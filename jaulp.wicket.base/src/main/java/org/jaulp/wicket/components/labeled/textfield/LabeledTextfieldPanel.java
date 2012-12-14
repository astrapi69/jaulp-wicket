package org.jaulp.wicket.components.labeled.textfield;

import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;
import org.jaulp.wicket.components.labeled.LabeledFormComponentPanel;

public class LabeledTextfieldPanel<T> extends LabeledFormComponentPanel<T> {

	private static final long serialVersionUID = 1L;
	@SuppressWarnings("unused")
	private T text;
	private TextField<T> textField;

	public LabeledTextfieldPanel(String id) {
		this(id, null);
	}

	public LabeledTextfieldPanel(String id, IModel<T> model) {
		super(id, model);
		PropertyModel<T> textFieldModel = new PropertyModel<T>(this, "text");
		add(textField = new TextField<T>("textField", textFieldModel));

		add(feedback = newComponentFeedbackPanel("feedback", textField));

		String markupId = textField.getMarkupId();
		add(label = newLabel("label", markupId, model));
	}

	protected TextField<T> newTextField(String id, PropertyModel<T> model) {
		TextField<T> textField = new TextField<T>(id, model);
		textField.setOutputMarkupId(true);
		return textField;
	}

	public String getInput() {
		return textField.getInput();
	}

	protected void convertInput() {
		setConvertedInput(textField.getConvertedInput());
	}

	protected void onBeforeRender() {
		text = (T) getModelObject();
		textField.setRequired(isRequired());
		super.onBeforeRender();
	}
}
