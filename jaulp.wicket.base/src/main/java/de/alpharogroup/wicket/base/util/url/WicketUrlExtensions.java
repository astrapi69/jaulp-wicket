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
package de.alpharogroup.wicket.base.util.url;

import org.apache.wicket.Page;
import org.apache.wicket.protocol.https.Scheme;
import org.apache.wicket.request.Url;
import org.apache.wicket.request.cycle.RequestCycle;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import de.alpharogroup.wicket.base.util.WicketComponentExtensions;

/**
 * The Class WicketUrlUtils.
 */
public class WicketUrlExtensions
{

	/**
	 * Returns the absolute url for the given page without the server port.
	 * 
	 * @param <C>
	 *            the generic type
	 * @param page
	 *            the page
	 * @return the string
	 */
	public static <C extends Page> String absoluteUrlFor(final Class<C> page)
	{
		return absoluteUrlFor(page, null, false);
	}

	/**
	 * Returns the absolute url for the given page and optionally with the server port.
	 * 
	 * @param <C>
	 *            the generic type
	 * @param page
	 *            the page
	 * @param withServerPort
	 *            the with server port
	 * @return the string
	 */
	public static <C extends Page> String absoluteUrlFor(final Class<C> page,
		final boolean withServerPort)
	{
		return absoluteUrlFor(page, null, withServerPort);
	}

	/**
	 * Returns the absolute url for the given page with the parameters and optionally with the
	 * server port.
	 * 
	 * @param <C>
	 *            the generic type
	 * @param page
	 *            the page
	 * @param parameters
	 *            the parameters
	 * @param withServerPort
	 *            the with server port
	 * @return the string
	 */
	public static <C extends Page> String absoluteUrlFor(final Class<C> page,
		final PageParameters parameters, final boolean withServerPort)
	{
		final StringBuilder url = new StringBuilder();
		url.append(WicketUrlExtensions.getDomainUrl(withServerPort));
		url.append(WicketUrlExtensions.getBaseUrl(page, parameters).canonical().toString());
		return url.toString();
	}

	/**
	 * Gets the base Url.
	 * 
	 * @return base Url
	 */
	public static Url getBaseUrl()
	{
		return RequestCycle.get().getUrlRenderer().getBaseUrl();
	}

	/**
	 * Gets the base url from the given WebPage class object.
	 * 
	 * @param pageClass
	 *            the page class
	 * @return the base url
	 */
	public static Url getBaseUrl(final Class<? extends Page> pageClass)
	{
		return getBaseUrl(pageClass, null);
	}

	/**
	 * Gets the base url.
	 *
	 * @param pageClass
	 *            the page class
	 * @param parameters
	 *            the parameters
	 * @return the base url
	 */
	public static Url getBaseUrl(final Class<? extends Page> pageClass,
		final PageParameters parameters)
	{
		return RequestCycle.get().mapUrlFor(pageClass, parameters);
	}

	/**
	 * Gets the base url from the given WebPage.
	 * 
	 * @param page
	 *            the page
	 * @return the base url
	 */
	public static Url getBaseUrl(final Page page)
	{
		return getBaseUrl(page.getPageClass());
	}

	/**
	 * Gets the canonical page url. Try to reduce url by eliminating '..' and '.' from the path
	 * where appropriate (this is somehow similar to {@link java.io.File#getCanonicalPath()}).
	 *
	 * @param pageClass
	 *            the page class
	 * @return the page url
	 * @see Url#canonical()
	 */
	public static Url getCanonicalPageUrl(final Class<? extends Page> pageClass)
	{
		return getCanonicalPageUrl(pageClass, null);
	}

	/**
	 * Gets the canonical page url. Try to reduce url by eliminating '..' and '.' from the path
	 * where appropriate (this is somehow similar to {@link java.io.File#getCanonicalPath()}).
	 *
	 * @param pageClass
	 *            the page class
	 * @param parameters
	 *            the parameters
	 * @return the page url
	 * @see Url#canonical()
	 */
	public static Url getCanonicalPageUrl(final Class<? extends Page> pageClass,
		final PageParameters parameters)
	{
		return getPageUrl(pageClass, parameters).canonical();
	}

	/**
	 * Gets the context path.
	 * 
	 * @return the context path
	 */
	public static String getContextPath()
	{
		return RequestCycle.get().getRequest().getContextPath();
	}

	/**
	 * Gets the context path from the given WebPage.
	 * 
	 * @param page
	 *            the page
	 * @return the context path
	 */
	public static String getContextPath(final Page page)
	{
		return page.getRequest().getContextPath();
	}

	/**
	 * Gets the domain url.
	 *
	 * @return the domain url
	 */
	public static String getDomainUrl()
	{

		return getDomainUrl(true);
	}

	/**
	 * Gets the domain url.
	 * 
	 * @param withServerPort
	 *            the with server port
	 * @return the domain url
	 */
	public static String getDomainUrl(final boolean withServerPort)
	{
		return getDomainUrl(withServerPort, true);
	}

	/**
	 * Gets the domain url.
	 *
	 * @param withServerPort
	 *            the with server port
	 * @param withSlashAtTheEnd
	 *            the with slash at the end
	 * @return the domain url
	 */
	public static String getDomainUrl(final boolean withServerPort, final boolean withSlashAtTheEnd)
	{
		return getDomainUrl(false, withServerPort, withSlashAtTheEnd);
	}

	/**
	 * Gets the domain url.
	 * 
	 * @param ssl
	 *            if the domain url is secure the scheme https will be added otherwise http
	 * @param withServerPort
	 *            the with server port
	 * @param withSlashAtTheEnd
	 *            the with slash at the end
	 * @return the domain url
	 */
	public static String getDomainUrl(final boolean ssl, final boolean withServerPort,
		final boolean withSlashAtTheEnd)
	{
		return newDomainUrl(ssl ? Scheme.HTTPS.urlName() : Scheme.HTTP.urlName(),
			WicketUrlExtensions.getServerName(), WicketComponentExtensions.getHttpServletRequest()
				.getServerPort(), withServerPort, withSlashAtTheEnd);
	}


	/**
	 * Returns the host name or the ip address on which the request was received.
	 * 
	 * @return a <code>String</code> containing the host name of the ip address on which the request
	 *         was received.
	 * 
	 * @see javax.servlet.ServletRequest#getLocalName()
	 */
	public static String getLocalName()
	{
		return WicketComponentExtensions.getHttpServletRequest().getLocalName();
	}

	/**
	 * Gets the page url as Url object from the given WebPage class object.
	 * 
	 * @param pageClass
	 *            the page class
	 * @return the page url
	 */
	public static Url getPageUrl(final Class<? extends Page> pageClass)
	{
		return getPageUrl(pageClass, null);
	}

	/**
	 * Gets the page url.
	 *
	 * @param pageClass
	 *            the page class
	 * @param parameters
	 *            the parameters
	 * @return the page url
	 */
	public static Url getPageUrl(final Class<? extends Page> pageClass,
		final PageParameters parameters)
	{
		final String url = RequestCycle.get().urlFor(pageClass, parameters).toString();
		return Url.parse(url);
	}

	/**
	 * Gets the page url as Url object from the given WebPage.
	 * 
	 * @param page
	 *            the page
	 * @return the page url
	 */
	public static Url getPageUrl(final Page page)
	{
		return getPageUrl(page.getPageClass());
	}

	/**
	 * Gets the page url.
	 *
	 * @param page
	 *            the page
	 * @param parameters
	 *            the parameters
	 * @return the page url
	 */
	public static Url getPageUrl(final Page page, final PageParameters parameters)
	{
		return getPageUrl(page.getPageClass(), parameters);
	}

	/**
	 * Gets the server name.
	 *
	 * @return the server name
	 */
	public static String getServerName()
	{
		return WicketComponentExtensions.getHttpServletRequest().getServerName();
	}

	/**
	 * Gets the url as string from the given WebPage class object.
	 * 
	 * @param pageClass
	 *            the page class
	 * @return the url as string
	 */
	public static String getUrlAsString(final Class<? extends Page> pageClass)
	{
		final Url pageUrl = getPageUrl(pageClass);
		final Url url = getBaseUrl(pageClass);
		url.resolveRelative(pageUrl);
		final String contextPath = getContextPath();
		return String.format("%s/%s", contextPath, url);
	}

	/**
	 * Gets the url as string from the given WebPage.
	 * 
	 * @param page
	 *            the page
	 * @return the url as string
	 */
	public static String getUrlAsString(final Page page)
	{
		return getUrlAsString(page.getPageClass());
	}

	/**
	 * Creates a new domain url from the given parameters.
	 *
	 * @param scheme
	 *            the scheme
	 * @param domainName
	 *            the domain name
	 * @param port
	 *            the port
	 * @param withServerPort
	 *            the with server port
	 * @param withSlashAtTheEnd
	 *            the with slash at the end
	 * @return the string
	 */
	public static String newDomainUrl(final String scheme, final String domainName, final int port,
		final boolean withServerPort, final boolean withSlashAtTheEnd)
	{
		final StringBuilder domainUrl = new StringBuilder();
		domainUrl.append(scheme);
		domainUrl.append("://");
		domainUrl.append(domainName);
		if (withServerPort)
		{
			domainUrl.append(":");
			domainUrl.append(port);
		}
		if (withSlashAtTheEnd)
		{
			domainUrl.append("/");
		}
		return domainUrl.toString();
	}

	/**
	 * Gets the base Url as String.
	 * 
	 * @return base Url as String.
	 */
	public static String toBaseUrl()
	{
		return getBaseUrl().canonical().toString();
	}

	/**
	 * Gets the base url from the given WebPage class object as String.
	 * 
	 * @param pageClass
	 *            the page class
	 * @return the base url as String.
	 */
	public static String toBaseUrl(final Class<? extends Page> pageClass)
	{
		return getBaseUrl(pageClass).canonical().toString();
	}

	/**
	 * Gets the base ur as String.
	 *
	 * @param pageClass
	 *            the page class
	 * @param parameters
	 *            the parameters
	 * @return the base url as String.
	 */
	public static String toBaseUrl(final Class<? extends Page> pageClass,
		final PageParameters parameters)
	{
		return getBaseUrl(pageClass, parameters).canonical().toString();
	}

}
