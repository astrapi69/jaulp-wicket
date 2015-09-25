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
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import de.alpharogroup.wicket.components.factory.ComponentFactory;
import lombok.Getter;

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
	@Getter
	private final AbstractLink link;

	/** The label. */
	@Getter
	private final Label label;

	/** The sub menu items. */
	@Getter
	private final List<MenuItem> children = new ArrayList<MenuItem>();

	/**
	 * Instantiates a new {@link MenuItem}.
	 *
	 * @param link
	 *            the link
	 * @param labelModel
	 *            the model of the label text.
	 */
	public MenuItem(final AbstractLink link, final IModel<String> labelModel)
	{
		if ((link != null) && !link.getId().equals(MenuPanel.LINK_ID))
		{
			throw new IllegalArgumentException("The id have to be SuckerfishMenuPanel.LINK_ID");
		}
		this.link = link;
		this.link.add(this.label = newLabel(MenuPanel.LINK_TEXT_ID, labelModel));
	}

	/**
	 * Instantiates a new {@link MenuItem}.
	 *
	 * @param link
	 *            the link
	 * @param label
	 *            The label text
	 */
	public MenuItem(final AbstractLink link, final String label)
	{
		this(link, Model.of(label));
	}

	/**
	 * Instantiates a new {@link MenuItem}.
	 *
	 * @param labelModel
	 *            the model of the label text.
	 */
	public MenuItem(final IModel<String> labelModel)
	{
		this.link = null;
		this.label = newLabel(MenuPanel.LINK_TEXT_ID, labelModel);
	}

	/**
	 * Instantiates a new {@link MenuItem}.
	 *
	 * @param label
	 *            The label text
	 */
	public MenuItem(final String label)
	{
		this(Model.of(label));
	}

	/**
	 * Adds the given menu item to this menu item.
	 *
	 * @param menu
	 *            the menu item to add to this menu item.
	 * @return This menu item
	 */
	public MenuItem addMenu(final MenuItem menu)
	{
		this.children.add(menu);
		return this;
	}

	/**
	 * Factory method for creating a new {@link Label}. This method is invoked in the constructor
	 * from the derived classes and can be overridden so users can provide their own version of a
	 * new {@link Label}.
	 *
	 * @param id
	 *            the id
	 * @param model
	 *            the model
	 * @return the new {@link Label}.
	 */
	protected Label newLabel(final String id, final IModel<String> model)
	{
		return ComponentFactory.newLabel(id, model);
	}

	/**
	 * Add all menus at once.
	 *
	 * @param menuItems
	 *            the new menu items
	 * @return this {@link MenuItem}
	 */
	public MenuItem setMenuItems(final List<MenuItem> menuItems)
	{
		this.children.clear();
		this.children.addAll(menuItems);
		return this;
	}

}
