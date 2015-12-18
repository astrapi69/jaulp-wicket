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

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.wicket.Component;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.StringHeaderItem;
import org.apache.wicket.protocol.http.RequestUtils;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.protocol.https.RequireHttps;
import org.apache.wicket.request.Request;
import org.apache.wicket.request.Response;
import org.apache.wicket.request.cycle.RequestCycle;
import org.apache.wicket.request.http.WebRequest;
import org.apache.wicket.request.http.WebResponse;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.util.time.Time;

import de.alpharogroup.lang.AnnotationExtensions;
import de.alpharogroup.wicket.base.util.application.ApplicationExtensions;
import de.alpharogroup.wicket.base.util.parameter.PageParametersExtensions;

/**
 * The Class WicketComponentExtensions is a helper class for the migration from wicket-version 1.4.x
 * to 1.5.x or 1.5.x to 6.1.0.
 *
 * @author Asterios Raptis
 */
public final class WicketComponentExtensions
{

	/**
	 * Disables caching from a WebPage. To disable the cache override the WebPage.setHeader() and
	 * invoke this method. For instance:<code>
	 * protected void setHeaders(WebResponse response) {
	 * &nbsp;&nbsp;&nbsp;&nbsp;WicketComponentUtils.disableCaching(response);
	 * }
	 * </code>
	 *
	 * @param response
	 *            the response
	 */
	public static void disableCaching(final WebResponse response)
	{
		response.setLastModifiedTime(Time.now());
		final HttpServletResponse httpServletResponse = getHttpServletResponse(response);
		if (httpServletResponse != null)
		{
			httpServletResponse.addHeader("Cache-Control", "max-age=0");
			httpServletResponse.setDateHeader("Expires", 0);
		}
	}

	/**
	 * Gets the context path from the given WebApplication.
	 *
	 * @param application
	 *            the WebApplication
	 * @return the context path
	 * @deprecated use instead {@link ApplicationExtensions#getContextPath(WebApplication)}
	 */
	@Deprecated
	public static String getContextPath(final WebApplication application)
	{
		final String contextPath = application.getServletContext().getContextPath();
		if ((null != contextPath) && !contextPath.isEmpty())
		{
			return contextPath;
		}
		return "";
	}

	/**
	 * Gets the header contributor for favicon.
	 *
	 * @return the header contributor for favicon
	 */
	public static Behavior getHeaderContributorForFavicon()
	{
		return new Behavior()
		{
			private static final long serialVersionUID = 1L;

			@Override
			public void renderHead(final Component component, final IHeaderResponse response)
			{
				super.renderHead(component, response);
				response.render(new StringHeaderItem(
					"<link type=\"image/x-icon\" rel=\"shortcut icon\" href=\"favicon.ico\" />"));
			}
		};
	}

	/**
	 * Gets the http servlet request.
	 *
	 * @return the http servlet request
	 */
	public static HttpServletRequest getHttpServletRequest()
	{
		final Request request = RequestCycle.get().getRequest();
		return getHttpServletRequest(request);
	}

	/**
	 * Gets the http servlet request.
	 *
	 * @param request
	 *            the request
	 * @return the http servlet request
	 */
	public static HttpServletRequest getHttpServletRequest(final Request request)
	{
		final WebRequest webRequest = (WebRequest)request;
		final HttpServletRequest httpServletRequest = (HttpServletRequest)webRequest
			.getContainerRequest();
		return httpServletRequest;
	}

	/**
	 * Gets the http servlet response.
	 *
	 * @return the http servlet response
	 */
	public static HttpServletResponse getHttpServletResponse()
	{
		final Response response = RequestCycle.get().getResponse();
		return getHttpServletResponse(response);
	}

	/**
	 * Gets the http servlet response.
	 *
	 * @param response
	 *            the response
	 * @return the http servlet response
	 */
	public static HttpServletResponse getHttpServletResponse(final Response response)
	{
		final WebResponse webResponse = (WebResponse)response;
		final HttpServletResponse httpServletResponse = (HttpServletResponse)webResponse
			.getContainerResponse();
		return httpServletResponse;
	}

	/**
	 * Gets the ip address.
	 *
	 * @return the ip address
	 */
	public static String getIpAddress()
	{
		final String ipAddress = getHttpServletRequest().getRemoteHost();
		return ipAddress;
	}

	/**
	 * Gets the parameter value from given parameter name. Looks in the query and post parameters.
	 *
	 * @param request
	 *            the request
	 * @param parameterName
	 *            the parameter name
	 * @return the parameter value
	 * @deprecated use instead {@link PageParametersExtensions#getParameter(Request, String)}
	 */
	@Deprecated
	public static String getParameter(final Request request, final String parameterName)
	{
		return PageParametersExtensions.getParameter(request, parameterName);
	}

	/**
	 * Gets the parameter value from given parameter name. Looks in the query and post parameters.
	 *
	 * @param parameterName
	 *            the parameter name
	 * @return the parameter value
	 * @deprecated use instead {@link PageParametersExtensions#getParameter(String)}
	 */
	@Deprecated
	public static String getParameter(final String parameterName)
	{
		return PageParametersExtensions.getParameter(parameterName);
	}

	/**
	 * Gets a map with all parameters. Looks in the query and post parameters. Migration method from
	 * 1.4.* to 1.5.*.
	 *
	 * @return a map with all parameters.
	 * @deprecated use instead {@link PageParametersExtensions#getParameterMap()}
	 */
	@Deprecated
	public static Map<String, String[]> getParameterMap()
	{
		return PageParametersExtensions.getParameterMap();
	}

	/**
	 * Gets a map with all parameters. Looks in the query and post parameters. Migration method from
	 * 1.4.* to 1.5.*.
	 *
	 * @param request
	 *            the request
	 * @return a map with all parameters.
	 * @deprecated use instead {@link PageParametersExtensions#getParameterMap(Request)}
	 */
	@Deprecated
	public static Map<String, String[]> getParameterMap(final Request request)
	{
		return PageParametersExtensions.getParameterMap(request);
	}

	/**
	 * Gets the remote addr.
	 *
	 * @return the remote addr
	 */
	public static String getRemoteAddr()
	{
		final String ipAddress = getHttpServletRequest().getRemoteAddr();
		return ipAddress;
	}

	/**
	 * Gets the request url.
	 *
	 * @return the request url
	 */
	public static String getRequestURL()
	{
		final StringBuffer url = WicketComponentExtensions.getHttpServletRequest().getRequestURL();
		return url.toString();
	}

	/**
	 * Checks if the current request has the scheme 'https'.
	 *
	 * @return true if the current request has the scheme 'https', otherwise false
	 */
	public static boolean isHttps()
	{
		return WicketComponentExtensions.getHttpServletRequest().getScheme()
			.equalsIgnoreCase("https");
	}

	/**
	 * Checks if the given component has as parent a page that is annotated with
	 * {@link RequireHttps}.
	 *
	 * @param component
	 *            the component to check
	 * @return true if the component is inside a page that require https, otherwise false
	 */
	public static boolean isSecure(final Component component)
	{
		if (AnnotationExtensions.isAnnotationPresentInSuperClassesOrInterfaces(component.getClass(),
			RequireHttps.class))
		{
			return true;
		}
		return false;
	}

	/**
	 * Sets the security headers for the category access control.
	 *
	 * Note: Set this header only if you know what your doing. This header refers to a policy file. Specifying how
	 * resources should be allowed to be loaded from a different domain. The value 'master only'
	 * indicates that only the file specified should be considered valid on this domain.
	 *
	 * @param response
	 *            the response to set the header.
	 */
	public static void setSecurityAccessControlHeader(final WebResponse response)
	{
		response.setHeader("X-Permitted-Cross-Domain-Policies", "master-only");
	}

	/**
	 * Sets the security headers. You can check your setting on on the link below.
	 *
	 * @see <a href="http://cyh.herokuapp.com/cyh">check headers</a>
	 *
	 * @param response
	 *            the new security headers
	 */
	public static void setSecurityHeaders(final WebResponse response)
	{
		// Category: Framing
		WicketComponentExtensions.setSecurityFramingHeaders(response);
		// Category: Transport
		WicketComponentExtensions.setSecurityTransportHeaders(response);
		// Category: XSS
		WicketComponentExtensions.setSecurityXSSHeaders(response);
		// Category: Caching
		WicketComponentExtensions.setSecurityCachingHeaders(response);
		// Set-Cookie
		WicketComponentExtensions.setSecurityCookieHeaders(response);
	}

	/**
	 * Sets the security headers for the category Caching.
	 *
	 * @param response
	 *            the response to set the security headers
	 */
	public static void setSecurityCookieHeaders(final WebResponse response)
	{
		// Set-Cookie
		response.setHeader("Set-Cookie", "secure;httponly;");
	}

	/**
	 * Sets the security headers for the category Caching.
	 *
	 * @param response
	 *            the response to set the security headers
	 */
	public static void setSecurityCachingHeaders(final WebResponse response)
	{
		// Category: Caching
		response.setHeader("Cache-Control", "must-revalidate;");
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Expires", "-1");
	}

	/**
	 * Sets the security headers for the category XSS.
	 *
	 * @param response
	 *            the response to set the security headers
	 */
	public static void setSecurityXSSHeaders(final WebResponse response)
	{
		// Category: XSS
		response.setHeader("X-XSS-Protection", "1; mode=block");
	}

	/**
	 * Sets the security headers for the category Content.
	 *
	 * @param response
	 *            the response to set the security headers
	 */
	public static void setSecurityContentHeaders(final WebResponse response)
	{
		// Category: Content
		response.setHeader("X-Content-Type-Options", "nosniff");
		response.setHeader("Content-Type", "text/html;charset=utf-8");
	}

	/**
	 * Sets the security headers for the category Transport.
	 *
	 * @param response
	 *            the response to set the security headers
	 */
	public static void setSecurityTransportHeaders(final WebResponse response)
	{
		// Category: Transport
		response.setHeader("Strict-Transport-Security", "max-age=31536000; includeSubDomains");
	}

	/**
	 * Sets the security headers for the category framing.
	 *
	 * @param response
	 *            the response to set the security headers
	 */
	public static void setSecurityFramingHeaders(final WebResponse response)
	{
		// Category: Framing
		// see https://www.owasp.org/index.php/Clickjacking#X-FRAME-OPTIONS
		response.setHeader("X-Frame-Options", "sameorigin");
	}



	/**
	 * Helper method for the migration from wicket-version 1.5.x to 6.x.
	 *
	 * @param relativePagePath
	 *            the relative page path
	 * @return the string
	 */
	public static String toAbsolutePath(final String relativePagePath)
	{
		final HttpServletRequest req = (HttpServletRequest)((WebRequest)RequestCycle.get()
			.getRequest()).getContainerRequest();
		return RequestUtils.toAbsolutePath(req.getRequestURL().toString(), relativePagePath);
	}

	/**
	 * Converts the given Map to a {@link PageParameters} object.
	 *
	 * @param parameters
	 *            the {@link Map} with the parameters to set.
	 * @return the {@link PageParameters}
	 * @deprecated use instead {@link PageParametersExtensions#toPageParameters(Map)}
	 */
	@Deprecated
	public static PageParameters toPageParameters(final Map<String, String> parameters)
	{
		return PageParametersExtensions.toPageParameters(parameters);
	}

}
