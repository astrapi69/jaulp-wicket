package org.jaulp.wicket.components.termofuse.copyright;

import org.apache.wicket.Component;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.model.IModel;
import org.jaulp.wicket.behaviors.AddJsQueryBehavior;
import org.jaulp.wicket.components.i18n.list.HeaderContentListModel;
import org.jaulp.wicket.components.i18n.list.HeaderContentListPanel;

import net.sourceforge.jaulp.locale.ResourceBundleKey;

public class CopyrightPanel extends HeaderContentListPanel {
	private static final long serialVersionUID = 1L;

	public CopyrightPanel(String id) {
		this(id, null);
	}

	public CopyrightPanel(String id, IModel<HeaderContentListModel> model) {
		super(id, model);
	}

	@Override
	protected Component newListComponent(String id, ListItem<ResourceBundleKey> item) {
		return new Label(id, newContentResourceModel(item.getModel())).add(new AddJsQueryBehavior("wrap", "<p></p>"));
	}

	@Override
	protected Component newHeaderLabel(String id, IModel<String> model) {
		return super.newHeaderLabel(id, model).add(new AddJsQueryBehavior("wrap", "<h2></h2>"));
	}

}
