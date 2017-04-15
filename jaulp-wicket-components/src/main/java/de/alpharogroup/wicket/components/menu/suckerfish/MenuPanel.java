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


import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;

import de.alpharogroup.io.annotations.ImportResource;
import de.alpharogroup.io.annotations.ImportResources;
import de.alpharogroup.wicket.base.BasePanel;
import de.alpharogroup.wicket.header.contributors.HeaderResponseExtensions;
import lombok.Getter;

/**
 * The Class {@link MenuPanel}.
 */
@ImportResources(resources = {
		@ImportResource(resourceName = "MenuPanel.js", resourceType = "js") })
public class MenuPanel extends BasePanel<Object>
{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 0L;

	/** The Constant LINK_ID. */
	public static final String LINK_ID = "linkid";

	/** The Constant LINK_TEXT_ID. */
	public static final String LINK_TEXT_ID = "linktext";

	/**
	 * This appender is used to add a down or right arrow icon if there are children.
	 */
	public static final AttributeAppender menuHasSubmenuAppender = new AttributeAppender("class",
		Model.of("menu-has-submenu"), " ");

	/** The top menu items. */
	@Getter
	private final List<MenuItem> topMenuItems = new ArrayList<>();

	/**
	 * Instantiates a new {@link MenuPanel}.
	 *
	 * @param id
	 *            the id
	 */
	public MenuPanel(final String id)
	{
		super(id);
		// Add the top menus
		add(new SubMenuListView("topmenuitems", new PropertyModel<>(this, "topMenuItems"), this));
	}

	/**
	 * Add one menu item.
	 *
	 * @param menu
	 *            the menu
	 */
	public void addMenu(final MenuItem menu)
	{
		topMenuItems.add(menu);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void renderHead(final IHeaderResponse response)
	{
		super.renderHead(response);
		HeaderResponseExtensions.renderHeaderResponse(response, this.getClass());
	}

	/**
	 * Add all menus at once.
	 *
	 * @param menuItems
	 *            the new menu items
	 */
	public void setMenuItems(final List<MenuItem> menuItems)
	{
		topMenuItems.clear();
		topMenuItems.addAll(menuItems);
	}


}
