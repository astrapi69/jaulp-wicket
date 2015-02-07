/**
 * Copyright (C) 2010 Asterios Raptis
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package de.alpharogroup.wicket.components.i18n.list;

import java.util.List;

import org.apache.wicket.Component;
import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.link.ExternalLink;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.jaulp.wicket.base.util.resource.ResourceModelFactory;

import de.alpharogroup.wicket.components.link.LinkModel;
import de.alpharogroup.wicket.components.listview.ListViewPanel;

public class LinkListPanel extends ListViewPanel<LinkModel>
{

	private static final long serialVersionUID = 1L;

	public LinkListPanel(String id, IModel<List<LinkModel>> model)
	{
		super(id, model);
	}

	public LinkListPanel(String id, List<LinkModel> list)
	{
		super(id, list);
	}

	@Override
	protected Component newListComponent(String id, ListItem<LinkModel> item)
	{
		LinkModel model = item.getModelObject();
		Label itemLinkLabel = new Label("itemLinkLabel", ResourceModelFactory.newResourceModel(
			model.getResourceModelKey(), this));
		if (model.getUrl() != null)
		{
			return new ExternalLink(id, Model.of(model.getUrl())).add(itemLinkLabel);
		}
		// add css class to current page.
		if (model.getPageClass().equals(getPage().getClass()))
		{
			itemLinkLabel.add(new AttributeAppender("class", " " + getCurrentPageCssClass()));
		}
		return new BookmarkablePageLink<String>(id, model.getPageClass()).add(itemLinkLabel);
	}

	protected String getCurrentPageCssClass()
	{
		return "current-page";
	}

}
