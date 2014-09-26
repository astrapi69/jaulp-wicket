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
package org.jaulp.wicket.base.util;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.request.Url;
import org.apache.wicket.request.cycle.RequestCycle;
import org.apache.wicket.request.mapper.parameter.PageParameters;

/**
 * The Class WicketUrlUtils.
 */
public class WicketUrlUtils {

	/**
	 * Gets the page url as Url object from the given WebPage.
	 * 
	 * @param page
	 *            the page
	 * @return the page url
	 */
	public static Url getPageUrl(WebPage page) {
		return getPageUrl(page.getClass());
	}
	
	/**
	 * Gets the page url.
	 *
	 * @param page the page
	 * @param parameters the parameters
	 * @return the page url
	 */
	public static Url getPageUrl(WebPage page, PageParameters parameters) {
		return getPageUrl(page.getClass(), parameters);
	}

	/**
	 * Gets the page url as Url object from the given WebPage class object.
	 * 
	 * @param pageClass
	 *            the page class
	 * @return the page url
	 */
	public static Url getPageUrl(Class<? extends WebPage> pageClass) {
		return getPageUrl(pageClass, null);
	}
	
	/**
	 * Gets the page url.
	 *
	 * @param pageClass the page class
	 * @param parameters the parameters
	 * @return the page url
	 */
	public static Url getPageUrl(Class<? extends WebPage> pageClass, PageParameters parameters) {
		String url = RequestCycle.get().urlFor(pageClass, parameters).toString();
		return Url.parse(url);
	}
	
	/**
	 * Gets the canonical page url. Try to reduce url by eliminating '..' and
	 * '.' from the path where appropriate (this is somehow similar to
	 * {@link java.io.File#getCanonicalPath()}).
	 * @see Url#canonical()
	 *
	 * @param pageClass
	 *            the page class
	 * @return the page url
	 */
	public static Url getCanonicalPageUrl(Class<? extends WebPage> pageClass) {
		return getCanonicalPageUrl(pageClass, null);
	}
	
	/**
	 * Gets the canonical page url. Try to reduce url by eliminating '..' and
	 * '.' from the path where appropriate (this is somehow similar to
	 * {@link java.io.File#getCanonicalPath()}).
	 * @see Url#canonical()
	 *
	 * @param pageClass
	 *            the page class
	 * @param parameters
	 *            the parameters
	 * @return the page url
	 */
	public static Url getCanonicalPageUrl(Class<? extends WebPage> pageClass, PageParameters parameters) {
		return getPageUrl(pageClass, parameters).canonical();
	}

	/**
	 * Gets the base url from the given WebPage.
	 * 
	 * @param page
	 *            the page
	 * @return the base url
	 */
	public static Url getBaseUrl(WebPage page) {
		return new Url(page.getRequestCycle().getUrlRenderer().getBaseUrl());
	}

	/**
	 * Gets the base url from the given WebPage class object.
	 * 
	 * @param pageClass
	 *            the page class
	 * @return the base url
	 */
	public static Url getBaseUrl(Class<? extends WebPage> pageClass) {
		return getBaseUrl(pageClass, null);
	}
	
	/**
	 * Gets the base url.
	 *
	 * @param pageClass the page class
	 * @param parameters the parameters
	 * @return the base url
	 */
	public static Url getBaseUrl(Class<? extends WebPage> pageClass, PageParameters parameters) {
		return RequestCycle.get().mapUrlFor(pageClass, parameters);
	}

	/**
	 * Gets the base Url.
	 * 
	 * @return base Url
	 */
	public static Url getBaseUrl() {
		return RequestCycle.get().getUrlRenderer().getBaseUrl();
	}

	/**
	 * Gets the context path from the given WebPage.
	 * 
	 * @param page
	 *            the page
	 * @return the context path
	 */
	public static String getContextPath(WebPage page) {
		return page.getRequest().getContextPath();
	}

	/**
	 * Gets the context path.
	 * 
	 * @return the context path
	 */
	public static String getContextPath() {
		return RequestCycle.get().getRequest().getContextPath();
	}

	/**
	 * Gets the url as string from the given WebPage.
	 * 
	 * @param page
	 *            the page
	 * @return the url as string
	 */
	public static String getUrlAsString(WebPage page) {
		return getUrlAsString(page.getClass());
	}

	/**
	 * Gets the url as string from the given WebPage class object.
	 * 
	 * @param pageClass
	 *            the page class
	 * @return the url as string
	 */
	public static String getUrlAsString(Class<? extends WebPage> pageClass) {
		Url pageUrl = getPageUrl(pageClass);
		Url url = getBaseUrl(pageClass);
		url.resolveRelative(pageUrl);
		String contextPath = getContextPath();
		return String.format("%s/%s", contextPath, url);
	}

	/**
	 * Returns the host name or the ip address on which the request was
	 * received.
	 * 
	 * @return a <code>String</code> containing the host name of the ip address
	 *         on which the request was received.
	 * 
	 * @see javax.servlet.ServletRequest#getLocalName()
	 */
	public static String getLocalName() {
		return WicketComponentUtils.getHttpServletRequest().getLocalName();
	}

	/**
	 * Gets the server name.
	 *
	 * @return the server name
	 */
	public static String getServerName() {
		return WicketComponentUtils.getHttpServletRequest().getServerName();
	}

	/**
	 * Gets the domain url.
	 *
	 * @return the domain url
	 */
	public static String getDomainUrl() {

		return getDomainUrl(true);
	}

	/**
	 * Gets the domain url.
	 * 
	 * @param withServerPort
	 *            the with server port
	 * @return the domain url
	 */
	public static String getDomainUrl(boolean withServerPort) {
		return getDomainUrl(withServerPort, true);
	}

	/**
	 * Gets the domain url.
	 *
	 * @param withServerPort the with server port
	 * @param withSlashAtTheEnd the with slash at the end
	 * @return the domain url
	 */
	public static String getDomainUrl(boolean withServerPort, boolean withSlashAtTheEnd) {
		StringBuilder domainUrl = new StringBuilder();
		domainUrl.append(WicketComponentUtils.getHttpServletRequest()
				.getScheme());
		domainUrl.append("://");
		domainUrl.append(WicketUrlUtils.getServerName());
		if (withServerPort) {
			domainUrl.append(":");
			domainUrl.append(WicketComponentUtils.getHttpServletRequest()
					.getServerPort());
		}
		if(withSlashAtTheEnd) {
			domainUrl.append("/");
		}
		return domainUrl.toString();
	}
}
