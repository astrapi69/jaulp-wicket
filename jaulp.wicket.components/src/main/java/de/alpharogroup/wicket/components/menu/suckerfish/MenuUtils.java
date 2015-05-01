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

import org.apache.wicket.Component;
import org.apache.wicket.Page;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.model.IModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.jaulp.wicket.base.util.resource.ResourceModelFactory;

import de.alpharogroup.wicket.components.link.LinkUtils;

/**
 * The Class MenuUtils.
 *
 * @author Asterios Raptis
 */
public class MenuUtils
{

	/**
	 * Creates the bookmarkable page link.
	 *
	 * @param linkId
	 *            the link id
	 * @param pageClass
	 *            the page class
	 * @param labelId
	 *            the label id
	 * @param resourceModelKey
	 *            the resource model key
	 * @param component
	 *            the component
	 * @return the bookmarkable page link
	 */
	public static BookmarkablePageLink<String> newBookmarkablePageLink(String linkId,
		Class<? extends Page> pageClass, String labelId, String resourceModelKey,
		Component component)
	{
		return LinkUtils.newBookmarkablePageLink(linkId, pageClass, labelId, resourceModelKey,
			component);
	}

	/**
	 * Creates the menu item.
	 *
	 * @param pageClass
	 *            the page class
	 * @param resourceModelKey
	 *            the resource model key
	 * @param component
	 *            the component
	 * @param parameters
	 *            the {@link PageParameters}
	 * @return the suckerfish menu panel. menu item
	 */
	public static MenuItem newMenuItem(Class<? extends Page> pageClass, String resourceModelKey,
		Component component, final PageParameters parameters)
	{
		final BookmarkablePageLink<String> bookmarkablePageLink = new BookmarkablePageLink<>(
			MenuPanel.LINK_ID, pageClass, parameters);
		IModel<String> labelModel = ResourceModelFactory.newResourceModel(resourceModelKey,
			component);
		final MenuItem menuItem = new MenuItem(bookmarkablePageLink, labelModel);
		return menuItem;
	}

	/**
	 * Creates the menu item.
	 *
	 * @param labelModel
	 *            the label model
	 * @return the suckerfish menu panel. menu item
	 */
	// TODO delete...
	public static MenuItem newMenuItem(final IModel<String> labelModel)
	{
		final MenuItem menuItem = new MenuItem(labelModel);
		return menuItem;
	}


	/**
	 * Creates the menu item.
	 *
	 * @param pageClass
	 *            the page class
	 * @param resourceModelKey
	 *            the resource model key
	 * @param component
	 *            the component
	 * @return the suckerfish menu panel. menu item
	 */
	public static MenuItem newMenuItem(Class<? extends Page> pageClass, String resourceModelKey,
		Component component)
	{
		final BookmarkablePageLink<String> bookmarkablePageLink = new BookmarkablePageLink<>(
			MenuPanel.LINK_ID, pageClass);
		IModel<String> labelModel = ResourceModelFactory.newResourceModel(resourceModelKey,
			component);
		final MenuItem menuItem = new MenuItem(bookmarkablePageLink, labelModel);
		return menuItem;
	}


}
