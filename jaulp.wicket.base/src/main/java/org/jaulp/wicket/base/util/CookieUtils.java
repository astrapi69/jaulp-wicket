package org.jaulp.wicket.base.util;

import javax.servlet.http.Cookie;

import org.apache.wicket.request.cycle.RequestCycle;
import org.apache.wicket.request.http.WebRequest;
import org.apache.wicket.request.http.WebResponse;

/**
 * The Class CookieUtils provides some methods to create, add, get or clear a cookie.
 */
public final class CookieUtils
{

	/**
	 * Adds the given cookie.
	 *
	 * @param cookie
	 *            the cookie
	 */
	public static void addCookie(Cookie cookie)
	{
		((WebResponse)RequestCycle.get().getResponse()).addCookie(cookie);
	}

	/**
	 * Clears the given cookie.
	 *
	 * @param cookie
	 *            the cookie to clear.
	 */
	public static void clearCookie(Cookie cookie)
	{
		((WebResponse)RequestCycle.get().getResponse()).clearCookie(cookie);
	}

	/**
	 * Gets the cookie form the given name.
	 *
	 * @param name
	 *            the name
	 * @return the cookie
	 */
	public static Cookie getCookie(String name)
	{
		return ((WebRequest)RequestCycle.get().getRequest()).getCookie(name);
	}

	/**
	 * Creates a new cookie.
	 *
	 * @param name
	 *            the name
	 * @param value
	 *            the value
	 * @param purpose
	 *            the purpose
	 * @param domain
	 *            the domain
	 * @param maxAge
	 *            the max age
	 * @param path
	 *            the path
	 * @param secure
	 *            the secure
	 * @return the cookie
	 */
	public static Cookie newCookie(String name, String value, String purpose, String domain,
		int maxAge, String path, boolean secure)
	{
		Cookie cookie = new Cookie(name, value);
		cookie.setComment(purpose);
		cookie.setDomain(domain);
		cookie.setMaxAge(maxAge);
		cookie.setPath(path);

		return cookie;
	}
}
