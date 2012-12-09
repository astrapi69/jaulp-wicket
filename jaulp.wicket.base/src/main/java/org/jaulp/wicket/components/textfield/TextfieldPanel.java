package org.jaulp.wicket.components.textfield;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.basic.Label;

import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;

public class TextfieldPanel extends Panel {
	
	private TextField<String> textField;
	private Label label;

    public TextField<String> getTextField() {
		return textField;
	}

	public Label getLabel() {
		return label;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TextfieldPanel(String name, IModel<String> model) {
        super(name, model);
        add(label = new Label("label", model));
        add(textField = new TextField<String>("textfield", model));
    }
}
