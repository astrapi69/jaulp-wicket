package org.jaulp.wicket.components.textarea;

import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.FormComponentPanel;
import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.markup.html.panel.ComponentFeedbackPanel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;

public class LabeledTextAreaPanel<T> extends FormComponentPanel<T> {
	private static final long serialVersionUID = 1L;
	@SuppressWarnings("unused")
	private String text;
	private TextArea<T> textArea;
	@SuppressWarnings("unused")
	private ComponentFeedbackPanel feedback;
	@SuppressWarnings("unused")
	private Label label;

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

	protected ComponentFeedbackPanel newComponentFeedbackPanel(String id,
			TextArea<T> textArea) {
		ComponentFeedbackPanel feedbackPanel = new ComponentFeedbackPanel(id,
				textArea);
		feedbackPanel.setOutputMarkupId(true);
		return feedbackPanel;
	}

	protected TextArea<T> newTextArea(String id, PropertyModel<T> model) {
		TextArea<T> textArea = new TextArea<T>(id, model);
		textArea.setOutputMarkupId(true);
		return new TextArea<T>(id, model);
	}

	protected Label newLabel(String id, String forId, IModel<T> model) {
		Label label = new Label(id, model);
		label.add(new AttributeAppender("for", Model.of(forId), " "));
		return label;
	}

	public String getInput() {
		return textArea.getInput();
	}

	protected void convertInput() {
		setConvertedInput(textArea.getConvertedInput());
	}

	protected void onBeforeRender() {
		text = (String) getModelObject();
		textArea.setRequired(isRequired());
		super.onBeforeRender();
	}
}