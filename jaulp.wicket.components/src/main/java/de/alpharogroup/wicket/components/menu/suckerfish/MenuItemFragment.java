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

import org.apache.wicket.MarkupContainer;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.panel.Fragment;

/**
 * The Class MenuItemFragment.
 * 
 * @author Asterios Raptis
 */
public final class MenuItemFragment extends Fragment
{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 0L;

	/**
	 * Instantiates a new menu item fragment.
	 * 
	 * @param menuItem
	 *            the menu item
	 * @param markupProvider
	 *            the {@link MarkupContainer}
	 */
	public MenuItemFragment(final MenuItem menuItem, final MarkupContainer markupProvider)
	{
		super("menuitemfragment", "MENUITEMFRAGMENT", markupProvider);
		// Add the menu's label (hyperlinked if a link is provided)
		if (menuItem.getLink() != null)
		{
			add(new LinkFragment(menuItem.getLink(), markupProvider));
		}
		else
		{
			add(new TextFragment(menuItem.getLabel(), markupProvider));
		}
		final WebMarkupContainer menuitemlist = new WebMarkupContainer("menuitemlist");
		add(menuitemlist);
		// Hide the <ul> tag if there are no submenus
		menuitemlist.setVisible(menuItem.getChildren().size() > 0);
		// Add a down or right arrow icon if there are children
		if (menuItem.getChildren().size() > 0)
		{
			menuItem.getLabel().add(MenuPanel.menuHasSubmenuAppender);
		}
		// Add the submenus
		menuitemlist.add(new SubMenuListView("menuitemlinks", menuItem.getChildren(),
			markupProvider));
	}
}