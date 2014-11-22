package de.alpharogroup.wicket.components.radio;

import java.util.ArrayList;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Radio;
import org.apache.wicket.markup.html.form.RadioGroup;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;

public class RadioGroupPanel extends Panel {

	/**
	 * The serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	public RadioGroupPanel(String id, IModel<?> model) {
		super(id, model);
		RadioGroup group = new RadioGroup("group");
		add(group);
		ListView persons = new ListView("persons", new ArrayList()) {
			protected void populateItem(ListItem item) {
				item.add(new Radio("radio", item.getModel()));
				item.add(new Label("name", new PropertyModel(item.getModel(),
						"name")));
			}
		};

	}

}