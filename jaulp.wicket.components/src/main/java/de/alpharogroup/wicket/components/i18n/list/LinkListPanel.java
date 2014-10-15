package de.alpharogroup.wicket.components.i18n.list;

import java.util.List;

import org.apache.wicket.Component;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.link.ExternalLink;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.jaulp.wicket.base.util.resource.ResourceModelFactory;

import de.alpharogroup.wicket.components.link.LinkModel;
import de.alpharogroup.wicket.components.listview.ListViewPanel;

public class LinkListPanel extends ListViewPanel<LinkModel> {

	private static final long serialVersionUID = 1L;

	public LinkListPanel(String id, IModel<List<? extends LinkModel>> model) {
		super(id, model);
	}

	public LinkListPanel(String id, List<? extends LinkModel> list) {
		super(id, list);
	}

	@Override
	protected Component newListComponent(String id, ListItem<LinkModel> item) {
		LinkModel model = item.getModelObject();
		Label itemLinkLabel = new Label("itemLinkLabel",
				ResourceModelFactory.newResourceModel(model.getResourceModelKey(), this));
		if(model.getUrl()!=null) {
			return new ExternalLink(id, Model.of(model.getUrl())).add(itemLinkLabel);
		}
		return new BookmarkablePageLink<String>(id, model.getPageClass()).add(itemLinkLabel);
	}

}
