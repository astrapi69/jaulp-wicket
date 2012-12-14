package org.jaulp.wicket.components.labeled;

import org.apache.wicket.Component;
import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.FormComponentPanel;
import org.apache.wicket.markup.html.panel.ComponentFeedbackPanel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

public abstract class LabeledFormComponentPanel<T> extends FormComponentPanel<T> {
	private static final long serialVersionUID = 1L;
	protected Label label;
	protected ComponentFeedbackPanel feedback;

    public LabeledFormComponentPanel(String id) {
        super(id);
    }

    public LabeledFormComponentPanel(String id, IModel<T> model) {
        super(id, model);
    }

	protected Label newLabel(String id, String forId, IModel<T> model) {
		Label label = new Label(id, model);
		label.add(new AttributeAppender("for", Model.of(forId), " "));
		return label;
	}

	protected ComponentFeedbackPanel newComponentFeedbackPanel(String id,
			Component filter) {
		ComponentFeedbackPanel feedbackPanel = new ComponentFeedbackPanel(id,
				filter);
		feedbackPanel.setOutputMarkupId(true);
		return feedbackPanel;
	}
}
