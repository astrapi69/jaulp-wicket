package org.jaulp.wicket.components.link;

import org.apache.wicket.Component;
import org.apache.wicket.Page;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.model.StringResourceModel;

/**
 * The Class MenuUtils.
 * 
 * @author Asterios Raptis
 */
public class LinkUtils {

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
	
}
