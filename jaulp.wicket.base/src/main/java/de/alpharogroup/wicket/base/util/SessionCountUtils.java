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
package de.alpharogroup.wicket.base.util;

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
		HttpSession session = getHttpSession();
		if (session != null)
		{
			return session.getMaxInactiveInterval();
		}
		return -1;
	}

	/**
	 * Sets the given interval(in seconds) that will be set the session timeout.
	 *
	 * @param interval
	 *            The number of seconds.
	 *
	 * @return the given interval(in seconds) or -1 if the {@link javax.servlet.http.HttpSession} is
	 *         null.
	 */
	public static int setSessionTimeout(final int interval)
	{
		HttpSession session = getHttpSession();
		if (session != null)
		{
			session.setMaxInactiveInterval(interval);
			return interval;
		}
		return -1;
	}

	/**
	 * Gets the current http session.
	 *
	 * @return the current http session.
	 */
	public static HttpSession getHttpSession()
	{
		HttpServletRequest request = WicketComponentUtils.getHttpServletRequest();
		if (request != null)
		{
			HttpSession session = request.getSession(false);
			if (session != null)
			{
				return session;
			}
		}
		return null;
	}


}
