package org.jaulp.wicket.base.utils;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.request.Url;
import org.apache.wicket.request.cycle.RequestCycle;

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
	public static Url getUrl(WebPage page) {
		String url = page.urlFor(page.getClass(), null).toString();
		return Url.parse(url);
	}
	
	/**
	 * Gets the page url as Url object from the given WebPage class object.
	 *
	 * @param pageClass the page class
	 * @return the page url
	 */
	public static Url getPageUrl(Class<? extends WebPage> pageClass){
		String url = RequestCycle.get().urlFor(pageClass, null).toString();
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
	 * @param pageClass the page class
	 * @return the base url
	 */
	public static Url getBaseUrl(Class<? extends WebPage> pageClass){
		return RequestCycle.get().mapUrlFor(pageClass, null);
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
		Url pageUrl = getUrl(page);
		Url url = getBaseUrl(page);
		url.resolveRelative(pageUrl);
		String contextPath = getContextPath(page);
		return String.format("%s/%s", contextPath, url);
	}
	
	/**
	 * Gets the url as string from the given WebPage class object.
	 *
	 * @param pageClass the page class
	 * @return the url as string
	 */
	public static String getUrlAsString(Class<? extends WebPage> pageClass) {
		Url pageUrl = getPageUrl(pageClass);
		Url url = getBaseUrl(pageClass);
		url.resolveRelative(pageUrl);
		String contextPath = getContextPath();
		return String.format("%s/%s", contextPath, url);
	}
}
