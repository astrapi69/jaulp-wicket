package org.jaulp.wicket.components.menu.suckerfish;

import org.apache.wicket.Component;
import org.apache.wicket.Page;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.model.StringResourceModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;

/**
 * The Class MenuUtils.
 * 
 * @author Asterios Raptis
 */
public class MenuUtils {

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
	public static BookmarkablePageLink<String> createBookmarkablePageLink(
			String linkId, Class<? extends Page> pageClass, String labelId,
			String resourceModelKey, Component component) {
		final BookmarkablePageLink<String> searchEventsLink = new BookmarkablePageLink<String>(
				linkId, pageClass);

		searchEventsLink.add(new Label(labelId,
				new StringResourceModel(resourceModelKey, component, null)));
		return searchEventsLink;
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
	public static MenuItem createMenuItem(
			Class<? extends Page> pageClass, String resourceModelKey,
			Component component, final PageParameters parameters) {
		final BookmarkablePageLink<String> bookmarkablePageLink = 
				new BookmarkablePageLink<String>(
				MenuPanel.LINK_ID, pageClass, parameters);
		StringResourceModel stringResourceModel = new StringResourceModel(
				resourceModelKey, component, null);
		final MenuItem menuItem = new MenuItem(
				bookmarkablePageLink, stringResourceModel);
		return menuItem;
	}

	/**
	 * Creates the menu item.
	 * 
	 * @param stringResourceModel
	 *            the string resource model
	 * @return the suckerfish menu panel. menu item
	 */
	public static MenuItem createMenuItem(
			final StringResourceModel stringResourceModel) {
		final MenuItem menuItem = new MenuItem(
				stringResourceModel);
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
	public static MenuItem createMenuItem(
			Class<? extends Page> pageClass, String resourceModelKey,
			Component component) {
		final BookmarkablePageLink<String> bookmarkablePageLink = new BookmarkablePageLink<String>(
				MenuPanel.LINK_ID, pageClass);
		StringResourceModel stringResourceModel = new StringResourceModel(
				resourceModelKey, component, null);
		final MenuItem menuItem = new MenuItem(
				bookmarkablePageLink, stringResourceModel);
		return menuItem;
	}

	
}
