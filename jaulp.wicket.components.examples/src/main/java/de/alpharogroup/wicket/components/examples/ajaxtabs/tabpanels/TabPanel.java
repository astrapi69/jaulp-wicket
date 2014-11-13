package de.alpharogroup.wicket.components.examples.ajaxtabs.tabpanels;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;

public class TabPanel extends Panel {

	private static final long serialVersionUID = 1L;

	public TabPanel(String id, IModel<String> model) {
		super(id, model);
		add(new Label("label", model));
	}
};