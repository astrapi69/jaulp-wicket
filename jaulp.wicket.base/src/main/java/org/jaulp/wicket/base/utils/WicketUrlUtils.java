package org.jaulp.wicket.base.utils;

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
		return RequestCycle.get().mapUrlFor(pageClass, null);
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
		String domainUrl = "";
		int indexOf = WicketComponentUtils.getRequestURL().indexOf(
				getBaseUrl().toString());
		if(0 <indexOf){
			domainUrl = WicketComponentUtils.getRequestURL().substring(0,
					indexOf);
		}
		return domainUrl;
	}
}
