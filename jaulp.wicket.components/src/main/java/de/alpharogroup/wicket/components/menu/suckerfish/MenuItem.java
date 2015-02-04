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
package de.alpharogroup.wicket.components.menu.suckerfish;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.AbstractLink;
import org.apache.wicket.model.StringResourceModel;

/**
 * Lightweight menu object that stores a menu and its label.
 * 
 * @author Asterios Raptis
 */
public class MenuItem implements Serializable
{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 0L;

	/** The link. */
	private final AbstractLink link;

	/** The label. */
	private final Label label;

	/** The sub menu items. */
	private final List<MenuItem> subMenuItems = new ArrayList<MenuItem>();

	/**
	 * Instantiates a new menu item.
	 * 
	 * @param link
	 *            the link
	 * @param strLabel
	 *            the str label
	 */
	public MenuItem(final AbstractLink link, final String strLabel)
	{
		if (link != null && !link.getId().equals(MenuPanel.LINK_ID))
		{
			throw new IllegalArgumentException("The id must be SuckerfishMenuPanel.LINK_ID");
		}
		this.link = link;
		label = new Label(MenuPanel.LINK_TEXT_ID, strLabel);
		this.link.add(label);
	}

	/**
	 * Instantiates a new menu item.
	 * 
	 * @param link
	 *            the link
	 * @param strLabel
	 *            the str label
	 */
	public MenuItem(final AbstractLink link, final StringResourceModel strLabel)
	{
		if (link != null && !link.getId().equals(MenuPanel.LINK_ID))
		{
			throw new IllegalArgumentException("The id must be SuckerfishMenuPanel.LINK_ID");
		}
		this.link = link;
		label = new Label(MenuPanel.LINK_TEXT_ID, strLabel);
		this.link.add(label);
	}

	/**
	 * Instantiates a new menu item.
	 * 
	 * @param strLabel
	 *            the str label
	 */
	public MenuItem(final String strLabel)
	{
		link = null;
		label = new Label(MenuPanel.LINK_TEXT_ID, strLabel);
	}

	/**
	 * Instantiates a new menu item.
	 * 
	 * @param stringResourceModel
	 *            the string resource model
	 */
	public MenuItem(final StringResourceModel stringResourceModel)
	{
		link = null;
		label = new Label(MenuPanel.LINK_TEXT_ID, stringResourceModel);
	}

	/**
	 * Add one menu item.
	 * 
	 * @param menu
	 *            the menu
	 */
	public void addMenu(final MenuItem menu)
	{
		subMenuItems.add(menu);
	}

	/**
	 * Add all menus at once.
	 * 
	 * @param menuItems
	 *            the new menu items
	 */
	public void setMenuItems(final List<MenuItem> menuItems)
	{
		subMenuItems.clear();
		for (final MenuItem child : menuItems)
		{
			addMenu(child);
		}
	}

	/**
	 * Gets the link.
	 * 
	 * @return the link
	 */
	public AbstractLink getLink()
	{
		return link;
	}

	/**
	 * Gets the children.
	 * 
	 * @return the children
	 */
	public List<MenuItem> getChildren()
	{
		return subMenuItems;
	}

	/**
	 * Gets the label.
	 * 
	 * @return the label
	 */
	public Label getLabel()
	{
		return label;
	}
}