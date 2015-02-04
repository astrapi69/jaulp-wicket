package org.jaulp.wicket.base.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.wicket.Application;
import org.apache.wicket.protocol.http.IRequestLogger;
import org.apache.wicket.protocol.http.IRequestLogger.SessionData;
import org.apache.wicket.protocol.http.RequestLogger;
import org.apache.wicket.protocol.http.WebApplication;

/**
 * The Class SessionCountUtils.
 */
public class SessionCountUtils
{

	/**
	 * Gets the request logger of the current WebApplication.
	 * 
	 * @return the request logger
	 */
	public static IRequestLogger getRequestLogger()
	{
		return getRequestLogger(null);
	}

	/**
	 * Gets the request logger from the given WebApplication.
	 * 
	 * @param webApplication
	 *            the web application
	 * @return the request logger
	 */
	public static IRequestLogger getRequestLogger(WebApplication webApplication)
	{
		if (webApplication == null)
		{
			webApplication = (WebApplication)Application.get();
		}
		IRequestLogger requestLogger = webApplication.getRequestLogger();
		if (requestLogger == null)
		{
			requestLogger = new RequestLogger();
		}
		return requestLogger;
	}

	/**
	 * Gets the live sessions.
	 * 
	 * @return the live sessions
	 */
	public static List<SessionData> getLiveSessions()
	{
		return new ArrayList<>(Arrays.asList(getRequestLogger().getLiveSessions()));
	}

	/**
	 * Gets the peak sessions counter.
	 *
	 * @return the peak sessions counter.
	 */
	public static Integer getPeakSessions()
	{
		return getRequestLogger().getPeakSessions();
	}

	/**
	 * Gets the session timeout.
	 *
	 * @return the session timeout
	 */
	public static int getSessionTimeout()
	{
		HttpServletRequest request = WicketComponentUtils.getHttpServletRequest();
		if (request != null)
		{
			HttpSession session = request.getSession();
			if (session != null)
			{
				return session.getMaxInactiveInterval();
			}
		}
		return -1;
	}

	/**
	 * Gets the http session.
	 *
	 * @return the http session
	 */
	public static HttpSession getHttpSession()
	{
		HttpServletRequest request = WicketComponentUtils.getHttpServletRequest();
		if (request != null)
		{
			HttpSession session = request.getSession();
			if (session != null)
			{
				return session;
			}
		}
		return null;
	}

}
