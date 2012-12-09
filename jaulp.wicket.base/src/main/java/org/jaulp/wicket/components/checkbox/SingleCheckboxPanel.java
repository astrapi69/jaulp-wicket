package org.jaulp.wicket.components.checkbox;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.CheckBox;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;

public class SingleCheckboxPanel extends Panel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private CheckBox checkbox;

	private Label label;

	public SingleCheckboxPanel(String id, IModel<Boolean> model) {
		super(id, model);
		add(label = new Label("label", model));
        add(checkbox = new CheckBox("checkbox", model));
  
	}

}
