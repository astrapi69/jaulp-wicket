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

import de.alpharogroup.wicket.base.util.resource.ResourceModelFactory;
import de.alpharogroup.wicket.components.link.LinkFactory;


/**
 * A factory for creating MenuItem objects.
 *
 * @author Asterios Raptis
 */
public class MenuItemFactory
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
	 * @deprecated use instead
	 *             {@link LinkFactory#newBookmarkablePageLink(String, Class, String, de.alpharogroup.resourcebundle.locale.ResourceBundleKey, Component)}
	 */
	@Deprecated
	public static BookmarkablePageLink<String> newBookmarkablePageLink(final String linkId,
		final Class<? extends Page> pageClass, final String labelId, final String resourceModelKey,
		final Component component)
	{
		return LinkFactory.newBookmarkablePageLink(linkId, pageClass, labelId, resourceModelKey,
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
	 * @return the suckerfish menu panel. menu item
	 */
	public static MenuItem newMenuItem(final Class<? extends Page> pageClass,
		final String resourceModelKey, final Component component)
	{
		final BookmarkablePageLink<String> bookmarkablePageLink = new BookmarkablePageLink<>(
			MenuPanel.LINK_ID, pageClass);
		final IModel<String> labelModel = ResourceModelFactory.newResourceModel(resourceModelKey,
			component);
		final MenuItem menuItem = new MenuItem(bookmarkablePageLink, labelModel);
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
	 * @param parameters
	 *            the {@link PageParameters}
	 * @return the suckerfish menu panel. menu item
	 */
	public static MenuItem newMenuItem(final Class<? extends Page> pageClass,
		final String resourceModelKey, final Component component, final PageParameters parameters)
	{
		final BookmarkablePageLink<String> bookmarkablePageLink = new BookmarkablePageLink<>(
			MenuPanel.LINK_ID, pageClass, parameters);
		final IModel<String> labelModel = ResourceModelFactory.newResourceModel(resourceModelKey,
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
	public static MenuItem newMenuItem(final IModel<String> labelModel)
	{
		final MenuItem menuItem = new MenuItem(labelModel);
		return menuItem;
	}


}
