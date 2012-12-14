package org.jaulp.wicket.components.labeled.textarea;

import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;
import org.jaulp.wicket.components.labeled.LabeledFormComponentPanel;

public class LabeledTextAreaPanel<T> extends LabeledFormComponentPanel<T> {
	private static final long serialVersionUID = 1L;
	@SuppressWarnings("unused")
	private T text;
	private TextArea<T> textArea;

	public LabeledTextAreaPanel(String id) {
		this(id, null);
	}

	public LabeledTextAreaPanel(String id, IModel<T> model) {
		super(id, model);

		PropertyModel<T> textAreaModel = new PropertyModel<T>(this, "text");
		add(textArea = newTextArea("textArea", textAreaModel));

		add(feedback = newComponentFeedbackPanel("feedback", textArea));

		String markupId = textArea.getMarkupId();
		add(label = newLabel("label", markupId, model));
	}

	protected TextArea<T> newTextArea(String id, PropertyModel<T> model) {
		TextArea<T> textArea = new TextArea<T>(id, model);
		textArea.setOutputMarkupId(true);
		return textArea;
	}

	public String getInput() {
		return textArea.getInput();
	}

	protected void convertInput() {
		setConvertedInput(textArea.getConvertedInput());
	}

	protected void onBeforeRender() {
		text = (T) getModelObject();
		textArea.setRequired(isRequired());
		super.onBeforeRender();
	}
}