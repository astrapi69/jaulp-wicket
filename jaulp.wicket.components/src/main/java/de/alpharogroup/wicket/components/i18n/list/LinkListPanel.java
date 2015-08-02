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

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.Component;
import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.AbstractLink;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.link.ExternalLink;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import de.alpharogroup.wicket.base.util.resource.ResourceModelFactory;
import de.alpharogroup.wicket.components.factory.ComponentFactory;
import de.alpharogroup.wicket.components.link.LinkItem;
import de.alpharogroup.wicket.components.listview.ListViewPanel;

/**
 * This Panel holds a list with links and labels.
 */
public class LinkListPanel extends ListViewPanel<LinkItem>
{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	// instance block for set the markup id, with other words make this component ajaxifiable...
	{
		setOutputMarkupId(true);
	}

	/**
	 * Instantiates a new link list panel.
	 *
	 * @param id
	 *            the id
	 * @param model
	 *            the model
	 */
	public LinkListPanel(final String id, final IModel<List<LinkItem>> model)
	{
		super(id, model);
	}

	/**
	 * Instantiates a new link list panel.
	 *
	 * @param id
	 *            the id
	 * @param list
	 *            the list
	 */
	public LinkListPanel(final String id, final List<LinkItem> list)
	{
		super(id, list);
	}

	/**
	 * This css class will be added to the current page.
	 *
	 * @return the current page css class
	 */
	protected String getCurrentPageCssClass()
	{
		return "current-page";
	}

	/**
	 * Factory method for create a new item link.
	 *
	 * @param id
	 *            the id
	 * @param model
	 *            the model
	 * @return the abstract link
	 */
	protected AbstractLink newAbstractLink(final String id, final LinkItem model)
	{
		AttributeModifier target = null;
		AbstractLink link = null;
		if (model.getTarget() != null && !model.getTarget().isEmpty())
		{
			target = new AttributeModifier("target", Model.of(model.getTarget()));
		}
		if (model.getUrl() != null)
		{
			link = new ExternalLink(id, Model.of(model.getUrl()));
		}
		if (link == null)
		{
			link = new BookmarkablePageLink<String>(id, model.getPageClass());
		}
		// if target not null then set it...
		if (target != null)
		{
			link.add(target);
		}
		link.setOutputMarkupId(true);
		return link;
	}

	/**
	 * Factory method for create a new item link Label. This method is invoked in the constructor
	 * from the derived classes and can be overridden so users can provide their own version of a
	 * new item link Label.
	 *
	 * @param id
	 *            the id
	 * @param model
	 *            the model
	 * @return the label
	 */
	protected Label newItemLinkLabel(final String id, final LinkItem model)
	{
		final Label itemLinkLabel = ComponentFactory.newLabel(id,
			ResourceModelFactory.newResourceModel(model.getResourceModelKey(), this));
		// add css class to current page.
		if (model.getPageClass() != null && model.getPageClass().equals(getPage().getClass()))
		{
			itemLinkLabel.add(new AttributeAppender("class", " " + getCurrentPageCssClass()));
		}
		return itemLinkLabel;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected Component newListComponent(final String id, final ListItem<LinkItem> item)
	{
		final LinkItem model = item.getModelObject();
		final Label itemLinkLabel = newItemLinkLabel("itemLinkLabel", model);
		final AbstractLink link = newAbstractLink(id, model);
		link.add(itemLinkLabel);
		return link;
	}

}
