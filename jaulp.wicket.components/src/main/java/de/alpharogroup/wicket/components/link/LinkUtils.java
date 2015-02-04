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
package de.alpharogroup.wicket.components.link;

import net.sourceforge.jaulp.locale.ResourceBundleKey;

import org.apache.wicket.Component;
import org.apache.wicket.Page;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.link.ExternalLink;
import org.apache.wicket.model.Model;
import org.jaulp.wicket.base.util.resource.ResourceModelFactory;

/**
 * The Class MenuUtils.
 * 
 * @author Asterios Raptis
 */
public class LinkUtils
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
		return newBookmarkablePageLink(linkId, pageClass, labelId, resourceModelKey, null,
			component);
	}

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
	 * @param defaultValue
	 *            the default value
	 * @param component
	 *            the component
	 * @return the bookmarkable page link
	 */
	public static BookmarkablePageLink<String> newBookmarkablePageLink(String linkId,
		Class<? extends Page> pageClass, String labelId, String resourceModelKey,
		String defaultValue, Component component)
	{
		return newBookmarkablePageLink(linkId, pageClass, labelId, resourceModelKey, null,
			defaultValue, component);
	}

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
	 * @param parameters
	 *            the parameters
	 * @param defaultValue
	 *            the default value
	 * @param component
	 *            the component
	 * @return the bookmarkable page link
	 */
	public static BookmarkablePageLink<String> newBookmarkablePageLink(String linkId,
		Class<? extends Page> pageClass, String labelId, String resourceModelKey,
		Object[] parameters, String defaultValue, Component component)
	{
		return newBookmarkablePageLink(
			linkId,
			pageClass,
			labelId,
			ResourceBundleKey.builder().key(resourceModelKey).parameters(parameters)
				.defaultValue(defaultValue).build(), component);
	}

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
		Class<? extends Page> pageClass, String labelId, ResourceBundleKey resourceModelKey,
		Component component)
	{
		final BookmarkablePageLink<String> bookmarkablePageLink = new BookmarkablePageLink<String>(
			linkId, pageClass);
		bookmarkablePageLink.add(new Label(labelId, ResourceModelFactory.newResourceModel(
			resourceModelKey, component)));
		return bookmarkablePageLink;
	}

	/**
	 * Creates an external link from the given parameters.
	 *
	 * @param linkId
	 *            the link id
	 * @param url
	 *            the external url
	 * @param labelId
	 *            the label id
	 * @param resourceModelKey
	 *            the resource model key
	 * @param component
	 *            the component
	 * @return the external link
	 */
	public static ExternalLink newExternalLink(String linkId, String url, String labelId,
		String resourceModelKey, Component component)
	{
		return newExternalLink(linkId, url, labelId, resourceModelKey, null, component);
	}

	/**
	 * Creates an external link from the given parameters.
	 *
	 * @param linkId
	 *            the link id
	 * @param url
	 *            the external url
	 * @param labelId
	 *            the label id
	 * @param resourceModelKey
	 *            the resource model key
	 * @param defaultValue
	 *            a default value
	 * @param component
	 *            the component
	 * @return the external link
	 */
	public static ExternalLink newExternalLink(String linkId, String url, String labelId,
		String resourceModelKey, String defaultValue, Component component)
	{
		return newExternalLink(linkId, url, labelId, resourceModelKey, null, null, component);
	}

	/**
	 * Creates an external link from the given parameters.
	 *
	 * @param linkId
	 *            the link id
	 * @param url
	 *            the external url
	 * @param labelId
	 *            the label id
	 * @param resourceModelKey
	 *            the resource model key
	 * @param parameters
	 *            the parameters for the resource key
	 * @param defaultValue
	 *            a default value
	 * @param component
	 *            the component
	 * @return the external link
	 */
	public static ExternalLink newExternalLink(String linkId, String url, String labelId,
		String resourceModelKey, Object[] parameters, String defaultValue, Component component)
	{
		return newExternalLink(
			linkId,
			url,
			labelId,
			ResourceBundleKey.builder().key(resourceModelKey).parameters(parameters)
				.defaultValue(defaultValue).build(), component);
	}

	/**
	 * Creates an external link from the given parameters.
	 *
	 * @param linkId
	 *            the link id
	 * @param url
	 *            the external url
	 * @param labelId
	 *            the label id
	 * @param resourceBundleKey
	 *            the resource model key
	 * @param component
	 *            the component
	 * @return the external link
	 */
	public static ExternalLink newExternalLink(String linkId, String url, String labelId,
		ResourceBundleKey resourceBundleKey, Component component)
	{
		ExternalLink externalLink = new ExternalLink(linkId, Model.of(url));
		externalLink.add(new Label(labelId, ResourceModelFactory.newResourceModel(
			resourceBundleKey, component)));
		return externalLink;
	}

}
