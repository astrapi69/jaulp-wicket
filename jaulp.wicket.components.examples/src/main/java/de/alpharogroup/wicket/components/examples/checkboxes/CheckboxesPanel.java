package de.alpharogroup.wicket.components.examples.checkboxes;

import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;

public class CheckboxesPanel extends Panel {

	/**
	 * The serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;

	public CheckboxesPanel(String id, IModel<?> model) {
		super(id, model);
		add(new CheckboxImageListViewPanel("checkboxImageListViewPanel"));
		add(new CheckChoicesListViewPanel("checkChoicesListViewPanel", model));
	}

}
