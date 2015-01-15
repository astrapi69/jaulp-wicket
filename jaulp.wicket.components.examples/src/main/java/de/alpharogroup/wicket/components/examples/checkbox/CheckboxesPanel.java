package de.alpharogroup.wicket.components.examples.checkbox;

import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;

import de.alpharogroup.wicket.components.examples.checkbox.image.CheckboxImageListViewPanel;
import de.alpharogroup.wicket.components.examples.checkbox.selector.CheckGroupSelectorExamplePanel;

public class CheckboxesPanel extends Panel {

	/**
	 * The serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;

	public CheckboxesPanel(String id, IModel<?> model) {
		super(id, model);

		add(new CheckGroupSelectorExamplePanel("checkGroupSelectorExamplePanel", null));
		add(new CheckboxImageListViewPanel("checkboxImageListViewPanel"));
		add(new CheckChoicesListViewPanel("checkChoicesListViewPanel", model));
	}

}
