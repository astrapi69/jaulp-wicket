package org.jaulp.wicket.components.labeled.checkbox;

import org.apache.wicket.markup.html.form.CheckBox;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;
import org.jaulp.wicket.components.labeled.LabeledFormComponentPanel;

public class LabeledCheckboxPanel extends LabeledFormComponentPanel<Boolean> {

	private static final long serialVersionUID = 1L;
	@SuppressWarnings("unused")
	private Boolean checked;
	
	private CheckBox checkBox;

	public LabeledCheckboxPanel(String id) {
		this(id, null);
	}

	public LabeledCheckboxPanel(String id, IModel<Boolean> model) {
		super(id, model);
		PropertyModel<Boolean> checkBoxModel = new PropertyModel<Boolean>(this, "checked");
        add(checkBox = new CheckBox("checkbox", checkBoxModel));

		add(feedback = newComponentFeedbackPanel("feedback", checkBox));

		String markupId = checkBox.getMarkupId();
		add(label = newLabel("label", markupId, model));
  
	}
	

	public String getInput() {
		return checkBox.getInput();
	}
	

	protected void convertInput() {
		setConvertedInput(checkBox.getConvertedInput());
	}

	protected void onBeforeRender() {
		checked = (Boolean) getModelObject();
		checkBox.setRequired(isRequired());
		super.onBeforeRender();
	}

}
