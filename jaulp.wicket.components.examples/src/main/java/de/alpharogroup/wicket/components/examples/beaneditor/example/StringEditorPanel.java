package de.alpharogroup.wicket.components.examples.beaneditor.example;

import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;


public class StringEditorPanel extends Panel
{

	/**
	 * The serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	public StringEditorPanel(String id, IModel<String> model, IModel<String> labelModel,
		boolean required)
	{
		super(id, model);
		add(new TextField<String>("edit", model).setLabel(labelModel).setRequired(required));
	}
}
