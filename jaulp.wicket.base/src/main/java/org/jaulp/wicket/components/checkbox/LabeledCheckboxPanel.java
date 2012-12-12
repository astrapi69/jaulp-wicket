package org.jaulp.wicket.components.checkbox;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.CheckBox;
import org.apache.wicket.markup.html.form.FormComponentPanel;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;

public class LabeledCheckboxPanel extends FormComponentPanel<Boolean> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@SuppressWarnings("unused")
	private Boolean checked;
	
	private CheckBox checkBox;
	@SuppressWarnings("unused")
	private Label label;
	

	public LabeledCheckboxPanel(String id) {
		this(id, null);
	}

	public LabeledCheckboxPanel(String id, IModel<Boolean> model) {
		super(id, model);
		add(label = new Label("label", model));
		PropertyModel<Boolean> checkBoxModel = new PropertyModel<Boolean>(this, "checked");
		
        add(checkBox = new CheckBox("checkbox", checkBoxModel));
  
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
