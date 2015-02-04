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

import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sourceforge.jaulp.lang.AnnotationUtils;

import org.apache.wicket.Component;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.CssReferenceHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.markup.head.JavaScriptReferenceHeaderItem;
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
import org.apache.wicket.request.resource.CssResourceReference;
import org.apache.wicket.request.resource.JavaScriptResourceReference;
import org.apache.wicket.util.time.Time;
import org.jaulp.wicket.PackageResourceReferenceWrapper;
import org.jaulp.wicket.PackageResourceReferences;
import org.jaulp.wicket.base.enums.ResourceReferenceType;
import org.jaulp.wicket.base.util.parameter.PageParametersUtils;

/**
 * The Class WicketComponentUtils is a helper class for the migration from wicket-version 1.4.x to
 * 1.5.x or 1.5.x to 6.1.0.
 * 
 * @author Asterios Raptis
 */
public final class WicketComponentUtils
{

	/**
	 * Gets the request url.
	 * 
	 * @return the request url
	 */
	public static String getRequestURL()
	{
		StringBuffer url = WicketComponentUtils.getHttpServletRequest().getRequestURL();
		return url.toString();
	}

	/**
	 * Gets the http servlet request.
	 * 
	 * @return the http servlet request
	 */
	public static HttpServletRequest getHttpServletRequest()
	{
		Request request = RequestCycle.get().getRequest();
		return getHttpServletRequest(request);
	}

	/**
	 * Gets the http servlet request.
	 * 
	 * @param request
	 *            the request
	 * @return the http servlet request
	 */
	public static HttpServletRequest getHttpServletRequest(Request request)
	{
		WebRequest webRequest = (WebRequest)request;
		HttpServletRequest httpServletRequest = (HttpServletRequest)webRequest
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
		Response response = RequestCycle.get().getResponse();
		return getHttpServletResponse(response);
	}

	/**
	 * Gets the http servlet response.
	 * 
	 * @param response
	 *            the response
	 * @return the http servlet response
	 */
	public static HttpServletResponse getHttpServletResponse(Response response)
	{
		WebResponse webResponse = (WebResponse)response;
		HttpServletResponse httpServletResponse = (HttpServletResponse)webResponse
			.getContainerResponse();
		return httpServletResponse;
	}

	/**
	 * Gets the parameter value from given parameter name. Looks in the query and post parameters.
	 * 
	 * @param request
	 *            the request
	 * @param parameterName
	 *            the parameter name
	 * @return the parameter value
	 * @deprecated use instead {@link PageParametersUtils#getParameter(Request, String)}
	 */
	public static String getParameter(Request request, String parameterName)
	{
		return PageParametersUtils.getParameter(request, parameterName);
	}

	/**
	 * Gets a map with all parameters. Looks in the query and post parameters. Migration method from
	 * 1.4.* to 1.5.*.
	 * 
	 * @return a map with all parameters.
	 * @deprecated use instead {@link PageParametersUtils#getParameterMap()}
	 */
	public static Map<String, String[]> getParameterMap()
	{
		return PageParametersUtils.getParameterMap();
	}

	/**
	 * Gets a map with all parameters. Looks in the query and post parameters. Migration method from
	 * 1.4.* to 1.5.*.
	 * 
	 * @param request
	 *            the request
	 * @return a map with all parameters.
	 * @deprecated use instead {@link PageParametersUtils#getParameterMap(Request)}
	 */
	public static Map<String, String[]> getParameterMap(Request request)
	{
		return PageParametersUtils.getParameterMap(request);
	}

	/**
	 * Converts the given Map to a {@link PageParameters} object.
	 * 
	 * @param parameters
	 *            the {@link Map} with the parameters to set.
	 * @return the {@link PageParameters}
	 * @deprecated use instead {@link PageParametersUtils#toPageParameters(Map)}
	 */
	public static PageParameters toPageParameters(Map<String, String> parameters)
	{
		return PageParametersUtils.toPageParameters(parameters);
	}

	/**
	 * Gets the parameter value from given parameter name. Looks in the query and post parameters.
	 * 
	 * @param parameterName
	 *            the parameter name
	 * @return the parameter value
	 * @deprecated use instead {@link PageParametersUtils#getParameter(String)}
	 */
	public static String getParameter(String parameterName)
	{
		return PageParametersUtils.getParameter(parameterName);
	}

	/**
	 * Gets the ip address.
	 * 
	 * @return the ip address
	 */
	public static String getIpAddress()
	{
		String ipAddress = getHttpServletRequest().getRemoteHost();
		return ipAddress;
	}

	/**
	 * Gets the remote addr.
	 * 
	 * @return the remote addr
	 */
	public static String getRemoteAddr()
	{
		String ipAddress = getHttpServletRequest().getRemoteAddr();
		return ipAddress;
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
			public void renderHead(Component component, IHeaderResponse response)
			{
				response.render(new StringHeaderItem(
					"<link type=\"image/x-icon\" rel=\"shortcut icon\" href=\"favicon.ico\" />"));
			}
		};
	}

	/**
	 * Disables caching from a WebPage. To disable the cache override the WebPage.setHeader() and
	 * invoke this method. <br/>
	 * For instance:<code><br/>
	 * protected void setHeaders(WebResponse response) {<br/>
	 * &nbsp;&nbsp;&nbsp;&nbsp;WicketComponentUtils.disableCaching(response);<br/>
	 * }<br/>
	 * </code>
	 * 
	 * @param response
	 *            the response
	 */
	public static void disableCaching(WebResponse response)
	{
		response.setLastModifiedTime(Time.now());
		HttpServletResponse httpServletResponse = getHttpServletResponse(response);
		if (httpServletResponse != null)
		{
			httpServletResponse.addHeader("Cache-Control", "max-age=0");
			httpServletResponse.setDateHeader("Expires", 0);
		}
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
		HttpServletRequest req = (HttpServletRequest)((WebRequest)RequestCycle.get().getRequest())
			.getContainerRequest();
		return RequestUtils.toAbsolutePath(req.getRequestURL().toString(), relativePagePath);
	}

	/**
	 * Gets the context path from the given WebApplication.
	 * 
	 * @param application
	 *            the WebApplication
	 * @return the context path
	 * @deprecated use instead {@link ApplicationUtils#getContextPath(WebApplication)}
	 */
	public static String getContextPath(final WebApplication application)
	{
		String contextPath = application.getServletContext().getContextPath();
		if (null != contextPath && !contextPath.isEmpty())
		{
			return contextPath;
		}
		return "";
	}

	/**
	 * Render header response.
	 * 
	 * @param response
	 *            the response
	 * @param componentClass
	 *            the component class
	 */
	public static void renderHeaderResponse(IHeaderResponse response, Class<?> componentClass)
	{
		Set<PackageResourceReferenceWrapper> headerContributors = PackageResourceReferences
			.getInstance().getPackageResourceReference(componentClass);
		if (null != headerContributors && !headerContributors.isEmpty())
		{
			for (final PackageResourceReferenceWrapper packageResourceReference : headerContributors)
			{
				if (packageResourceReference.getType().equals(ResourceReferenceType.JS))
				{
					JavaScriptResourceReference reference = new JavaScriptResourceReference(
						componentClass, packageResourceReference.getPackageResourceReference()
							.getName());
					if (!response.wasRendered(reference))
					{
						JavaScriptReferenceHeaderItem headerItem = JavaScriptHeaderItem
							.forReference(reference);
						response.render(headerItem);
					}
				}
				if (packageResourceReference.getType().equals(ResourceReferenceType.CSS))
				{
					CssResourceReference reference = new CssResourceReference(componentClass,
						packageResourceReference.getPackageResourceReference().getName());
					if (!response.wasRendered(reference))
					{
						CssReferenceHeaderItem headerItem = CssHeaderItem.forReference(reference);
						response.render(headerItem);
					}
				}
			}
		}
	}

	/**
	 * Checks if the given component has as parent a page that is annotated with
	 * {@link RequireHttps}.
	 * 
	 * @param component
	 *            the component to check
	 * @return true if the component is inside a page that require https, otherwise false
	 */
	public static boolean isSecure(Component component)
	{
		if (AnnotationUtils.isAnnotationPresentInSuperClassesOrInterfaces(component.getClass(),
			RequireHttps.class))
		{
			return true;
		}
		return false;
	}

	/**
	 * Checks if the current request has the scheme 'https'.
	 * 
	 * @return true if the current request has the scheme 'https', otherwise false
	 */
	public static boolean isHttps()
	{
		return WicketComponentUtils.getHttpServletRequest().getScheme().equalsIgnoreCase("https");
	}

}
