package de.alpharogroup.wicket.components.labeled.dropdown;

import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;

public class LabeledEnumDropDownPanel extends Panel
{

	/**
	 * The serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public LabeledEnumDropDownPanel(String id, IModel<?> model, IModel<String> labelModel, IModel<?> choices)
	{
		super(id, model);
		add(new DropDownChoice("edit", model, choices).setLabel(labelModel));
	}

}
