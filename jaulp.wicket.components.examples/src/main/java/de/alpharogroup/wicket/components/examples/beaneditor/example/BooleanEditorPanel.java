package de.alpharogroup.wicket.components.examples.beaneditor.example;

import org.apache.wicket.markup.html.form.CheckBox;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;

public class BooleanEditorPanel extends Panel
{

	/**
	 * The serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	public BooleanEditorPanel(String id, IModel<Boolean> model, IModel<String> labelModel)
	{
		super(id, model);
		add(new CheckBox("edit", model).setLabel(labelModel));
	}
}
