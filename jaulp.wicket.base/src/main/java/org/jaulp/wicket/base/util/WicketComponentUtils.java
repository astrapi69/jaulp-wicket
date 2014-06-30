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

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.wicket.Application;
import org.apache.wicket.Component;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.CssReferenceHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.markup.head.JavaScriptReferenceHeaderItem;
import org.apache.wicket.markup.head.StringHeaderItem;
import org.apache.wicket.protocol.http.IRequestLogger;
import org.apache.wicket.protocol.http.RequestLogger;
import org.apache.wicket.protocol.http.RequestUtils;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.request.IRequestParameters;
import org.apache.wicket.request.Request;
import org.apache.wicket.request.Response;
import org.apache.wicket.request.cycle.RequestCycle;
import org.apache.wicket.request.http.WebRequest;
import org.apache.wicket.request.http.WebResponse;
import org.apache.wicket.request.resource.CssResourceReference;
import org.apache.wicket.request.resource.JavaScriptResourceReference;
import org.apache.wicket.util.file.File;
import org.apache.wicket.util.file.Files;
import org.apache.wicket.util.resource.IResourceStream;
import org.apache.wicket.util.string.StringValue;
import org.apache.wicket.util.time.Time;
import org.jaulp.wicket.PackageResourceReferenceWrapper;
import org.jaulp.wicket.PackageResourceReferences;
import org.jaulp.wicket.base.enums.ResourceReferenceType;
import org.jaulp.wicket.base.util.resource.ByteArrayResourceStreamWriter;

/**
 * The Class WicketComponentUtils is a helper class for the migration from
 * wicket-version 1.4.x to 1.5.x or 1.5.x to 6.1.0.
 * 
 * @author Asterios Raptis
 */
public final class WicketComponentUtils {

	/**
	 * Gets the request url.
	 * 
	 * @return the request url
	 */
	public static String getRequestURL() {
		StringBuffer url = getHttpServletRequest().getRequestURL();
		return url.toString();
	}

	/**
	 * Gets the http servlet request.
	 * 
	 * @return the http servlet request
	 */
	public static HttpServletRequest getHttpServletRequest() {
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
	public static HttpServletRequest getHttpServletRequest(Request request) {
		WebRequest webRequest = (WebRequest) request;
		HttpServletRequest httpServletRequest = (HttpServletRequest) webRequest
				.getContainerRequest();
		return httpServletRequest;
	}

	/**
	 * Gets the http servlet response.
	 * 
	 * @return the http servlet response
	 */
	public static HttpServletResponse getHttpServletResponse() {
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
	public static HttpServletResponse getHttpServletResponse(Response response) {
		WebResponse webResponse = (WebResponse) response;
		HttpServletResponse httpServletResponse = (HttpServletResponse) webResponse
				.getContainerResponse();
		return httpServletResponse;
	}

	/**
	 * Gets the parameter value from given parameter name. Looks in the query
	 * and post parameters.
	 * 
	 * @param request
	 *            the request
	 * @param parameterName
	 *            the parameter name
	 * @return the parameter value
	 */
	public static String getParameter(Request request, String parameterName) {
		String parameterValue = request.getRequestParameters()
				.getParameterValue(parameterName).toString();
		if (parameterValue == null || parameterValue.isEmpty()) {
			parameterValue = request.getPostParameters()
					.getParameterValue(parameterName).toString();
		}
		return parameterValue;
	}
	


	/**
	 * Gets a map with all parameters. Looks in the query
	 * and post parameters. Migration method from 1.4.* to 1.5.*.
	 * 
	 * @param request
	 *            the request
	 * @return a map with all parameters.
	 */
	public static Map<String, String[]> getParameterMap(Request request) {
		IRequestParameters parameters = request.getRequestParameters();
		final Map<String, String[]> map = new HashMap<>();
		Set<String> parameterNames = parameters.getParameterNames();
		for (String parameterName : parameterNames) {
			List<StringValue> parameterValues = parameters.getParameterValues(parameterName);
			String[] stringArray = new String[parameterValues.size()];
			if(parameterValues != null && !parameterValues.isEmpty()) {				
				for (int i = 0; i < parameterValues.size(); i++) {
					stringArray[i] = parameterValues.get(i).toString();
				}
			}
			map.put(parameterName, stringArray);
		}
		return map;
	}

	/**
	 * Gets the parameter value from given parameter name. Looks in the query
	 * and post parameters.
	 * 
	 * @param parameterName
	 *            the parameter name
	 * @return the parameter value
	 */
	public static String getParameter(String parameterName) {
		Request request = RequestCycle.get().getRequest();
		return getParameter(request, parameterName);
	}

	/**
	 * Gets the ip address.
	 * 
	 * @return the ip address
	 */
	public static String getIpAddress() {
		String ipAddress = getHttpServletRequest().getRemoteHost();
		return ipAddress;
	}

	/**
	 * Gets the remote addr.
	 * 
	 * @return the remote addr
	 */
	public static String getRemoteAddr() {
		String ipAddress = getHttpServletRequest().getRemoteAddr();
		return ipAddress;
	}

	/**
	 * Gets the header contributor for favicon.
	 * 
	 * @return the header contributor for favicon
	 */
	public static Behavior getHeaderContributorForFavicon() {
		return new Behavior() {
			private static final long serialVersionUID = 1L;

			@Override
			public void renderHead(Component component, IHeaderResponse response) {
				response.render(new StringHeaderItem(
						"<link type=\"image/x-icon\" rel=\"shortcut icon\" href=\"favicon.ico\" />"));

			}
		};
	}

	/**
	 * Disables caching from a WebPage. To disable the cache override the
	 * WebPage.setHeader() and invoke this method. <br/>
	 * For instance:<code><br/>
	 * protected void setHeaders(WebResponse response) {<br/>
	 * &nbsp;&nbsp;&nbsp;&nbsp;WicketComponentUtils.disableCaching(response);<br/>
	 * }<br/>
	 * </code>
	 * 
	 * @param response
	 *            the response
	 */
	public static void disableCaching(WebResponse response) {
		response.setLastModifiedTime(Time.now());

		HttpServletResponse httpServletResponse = getHttpServletResponse();
		if (httpServletResponse != null) {
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
	public static String toAbsolutePath(final String relativePagePath) {
		HttpServletRequest req = (HttpServletRequest) ((WebRequest) RequestCycle
				.get().getRequest()).getContainerRequest();
		return RequestUtils.toAbsolutePath(req.getRequestURL().toString(),
				relativePagePath);
	}

	/**
	 * Gets the context path from the given WebApplication.
	 * 
	 * @param application
	 *            the appl
	 * @return the context path
	 */
	public static String getContextPath(final WebApplication application) {
		String contextPath = application.getServletContext().getContextPath();
		if (null != contextPath && !contextPath.isEmpty()) {
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
	public static void renderHeaderResponse(IHeaderResponse response,
			Class<?> componentClass) {
		Set<PackageResourceReferenceWrapper> headerContributors = PackageResourceReferences
				.getInstance().getPackageResourceReference(componentClass);
		if (null != headerContributors && !headerContributors.isEmpty()) {
			for (final PackageResourceReferenceWrapper packageResourceReference : headerContributors) {
				if (packageResourceReference.getType().equals(
						ResourceReferenceType.JS)) {
					JavaScriptResourceReference reference = new JavaScriptResourceReference(
							componentClass, packageResourceReference
									.getPackageResourceReference().getName());
					if (!response.wasRendered(reference)) {
						JavaScriptReferenceHeaderItem headerItem = JavaScriptHeaderItem
								.forReference(reference);
						response.render(headerItem);
					}
				}
				if (packageResourceReference.getType().equals(
						ResourceReferenceType.CSS)) {
					CssResourceReference reference = new CssResourceReference(
							componentClass, packageResourceReference
									.getPackageResourceReference().getName());
					if (!response.wasRendered(reference)) {
						CssReferenceHeaderItem headerItem = CssHeaderItem
								.forReference(reference);
						response.render(headerItem);
					}
				}
			}
		}
	}

	/**
	 * Gets the request logger of the current WebApplication.
	 * 
	 * @return the request logger
	 */
	public static IRequestLogger getRequestLogger() {
		return getRequestLogger(null);
	}

	/**
	 * Gets the real path corresponding to the given virtual path from the given
	 * WebApplication. This method gets decorated the method of the
	 * {@link javax.servlet.ServletContext#getRealPath(String)}.
	 * 
	 * @param application
	 *            the wicket application
	 * @param path
	 *            the virtual path to be translated to a real path
	 * @return the real path, or null if the translation cannot be performed
	 */

	public static String getRealPath(final WebApplication application,
			String path) {
		String realPath = application.getServletContext().getRealPath(path);
		if (null != realPath && !realPath.isEmpty()) {
			return realPath;
		}
		return "";
	}
	
	/**
	 * Gets the resource stream from the given parameters.
	 *
	 * @param application the application
	 * @param path the path
	 * @param contentType the content type
	 * @return the resource stream
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static IResourceStream getResourceStream(final WebApplication application,
			final String path, final String contentType) throws IOException {
    	return new ByteArrayResourceStreamWriter() {
			private static final long serialVersionUID = 1L;
			@Override
			public String getContentType() {
				return contentType;
			}
			@Override
			protected byte[] load() throws IOException {
				byte[] data = null;
				final String realPath = WicketComponentUtils.getRealPath(application, path);		    	
				final File file = new File(realPath);				
				data = Files.readBytes(file);				
				return data;
			}    		
    	};
	}

	/**
	 * Gets the request logger from the given WebApplication.
	 * 
	 * @param webApplication
	 *            the web application
	 * @return the request logger
	 */
	public static IRequestLogger getRequestLogger(WebApplication webApplication) {
		if (webApplication == null) {
			webApplication = (WebApplication) Application.get();
		}
		IRequestLogger requestLogger = webApplication.getRequestLogger();
		if (requestLogger == null) {
			requestLogger = new RequestLogger();
		}
		return requestLogger;
	}
}
