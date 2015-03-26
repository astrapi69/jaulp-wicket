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
package org.jaulp.wicket.base.util.application;

import java.io.IOException;

import org.apache.wicket.Application;
import org.apache.wicket.RuntimeConfigurationType;
import org.apache.wicket.core.util.file.WebApplicationPath;
import org.apache.wicket.javascript.DefaultJavaScriptCompressor;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.filter.JavaScriptFilteredIntoFooterHeaderResponse;
import org.apache.wicket.markup.html.IHeaderResponseDecorator;
import org.apache.wicket.markup.html.IPackageResourceGuard;
import org.apache.wicket.markup.html.SecurePackageResourceGuard;
import org.apache.wicket.protocol.http.IRequestLogger;
import org.apache.wicket.protocol.http.RequestLogger;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.protocol.https.HttpsConfig;
import org.apache.wicket.protocol.https.HttpsMapper;
import org.apache.wicket.protocol.https.Scheme;
import org.apache.wicket.request.Url;
import org.apache.wicket.request.component.IRequestablePage;
import org.apache.wicket.request.cycle.AbstractRequestCycleListener;
import org.apache.wicket.request.resource.ResourceReference;
import org.apache.wicket.request.resource.UrlResourceReference;
import org.apache.wicket.settings.IExceptionSettings;
import org.apache.wicket.settings.IResourceSettings;
import org.apache.wicket.spring.injection.annot.SpringComponentInjector;
import org.apache.wicket.util.file.File;
import org.apache.wicket.util.file.Files;
import org.apache.wicket.util.resource.IResourceStream;
import org.apache.wicket.util.time.Duration;
import org.jaulp.wicket.base.util.resource.ByteArrayResourceStreamWriter;

/**
 * The Class ApplicationUtils.
 */
public final class ApplicationUtils
{

	/**
	 * private constructor.
	 */
	private ApplicationUtils()
	{
	}

	/**
	 * Replace the default jquery resource reference from the given application with the given cdn
	 * url.
	 * 
	 * @param application
	 *            the WebApplication
	 * @param cdnjsUrl
	 *            the given cdn url.
	 */
	public static void replaceJQueryReference(WebApplication application, String cdnjsUrl)
	{
		application.getJavaScriptLibrarySettings().setJQueryReference(
			new UrlResourceReference(Url.parse(cdnjsUrl)));
	}

	/**
	 * Initializes the given WebApplication with the Spring framework.
	 *
	 * @param application
	 *            the WebApplication
	 */
	public static void initializeSpring(WebApplication application)
	{
		application.getComponentInstantiationListeners().add(
			new SpringComponentInjector(application));
	}

	/**
	 * Gets the context path from the given WebApplication.
	 *
	 * @param application
	 *            the WebApplication
	 * @return the context path
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
	 * Gets the real path corresponding to the given virtual path from the given WebApplication.
	 * This method gets decorated the method of the
	 * {@link javax.servlet.ServletContext#getRealPath(String)}.
	 *
	 * @param application
	 *            the wicket application
	 * @param path
	 *            the virtual path to be translated to a real path
	 * @return the real path, or null if the translation cannot be performed
	 */

	public static String getRealPath(final WebApplication application, String path)
	{
		String realPath = application.getServletContext().getRealPath(path);
		if (null != realPath && !realPath.isEmpty())
		{
			return realPath;
		}
		return "";
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
	 * Gets the request logger of the current WebApplication.
	 *
	 * @return the request logger
	 */
	public static IRequestLogger getRequestLogger()
	{
		return getRequestLogger(null);
	}

	/**
	 * Gets the resource stream from the given parameters.
	 *
	 * @param application
	 *            the application
	 * @param path
	 *            the path
	 * @param contentType
	 *            the content type
	 * @return the resource stream
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public static IResourceStream getResourceStream(final WebApplication application,
		final String path, final String contentType) throws IOException
	{
		return new ByteArrayResourceStreamWriter()
		{
			private static final long serialVersionUID = 1L;

			@Override
			public String getContentType()
			{
				return contentType;
			}

			@Override
			protected byte[] load() throws IOException
			{
				final String realPath = ApplicationUtils.getRealPath(application, path);
				final File file = new File(realPath);
				byte[] data = Files.readBytes(file);
				return data;
			}
		};
	}

	/**
	 * Gets the resource stream from the given parameters.
	 *
	 * @param file
	 *            the file
	 * @param contentType
	 *            the content type
	 * @return the resource stream
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public static IResourceStream getResourceStream(final java.io.File file,
		final String contentType) throws IOException
	{
		return new ByteArrayResourceStreamWriter()
		{
			private static final long serialVersionUID = 1L;

			@Override
			public String getContentType()
			{
				return contentType;
			}

			@Override
			protected byte[] load() throws IOException
			{
				byte[] data = Files.readBytes(file);
				return data;
			}
		};
	}

	/**
	 * Gets the default jquery reference from the given application.
	 *
	 * @param application
	 *            the application
	 * @return the default jquery reference
	 */
	public static ResourceReference getJQueryReference(final Application application)
	{
		return application.getJavaScriptLibrarySettings().getJQueryReference();
	}

	/**
	 * Gets the default jquery reference from the current application.
	 *
	 * @return the default jquery reference
	 */
	public static ResourceReference getJQueryReference()
	{
		return getJQueryReference(Application.get());
	}

	/**
	 * Sets an {@link IHeaderResponseDecorator} for the given application to use to decorate header
	 * responses.
	 *
	 * @param application
	 *            the application
	 * @param footerFilterName
	 *            the footer filter name
	 */
	public static void setHeaderResponseDecorator(final Application application,
		final String footerFilterName)
	{
		application.setHeaderResponseDecorator(new IHeaderResponseDecorator()
		{
			@Override
			public IHeaderResponse decorate(IHeaderResponse response)
			{
				return new JavaScriptFilteredIntoFooterHeaderResponse(response, footerFilterName);
			}
		});
	}

	/**
	 * Sets the footer header response for the given application from the given footerFilterName.
	 *
	 * @param application
	 *            the application
	 * @param footerFilterName
	 *            the name of the filter that you will use for your footer container
	 * @deprecated use instead
	 *             {@link ApplicationUtils#setHeaderResponseDecorator(Application, String)}
	 */
	@Deprecated
	public static void setFooterHeaderResponse(final Application application,
		final String footerFilterName)
	{
		setHeaderResponseDecorator(application, footerFilterName);
	}

	/**
	 * Sets the debug settings for development mode for the given application.
	 *
	 * @param application
	 *            the new debug settings for development
	 */
	public static void setDebugSettingsForDevelopment(final Application application)
	{
		application.getDebugSettings().setComponentUseCheck(true);
		application.getDebugSettings().setOutputMarkupContainerClassName(true);
		application.getDebugSettings().setLinePreciseReportingOnAddComponentEnabled(true);
		application.getDebugSettings().setLinePreciseReportingOnNewComponentEnabled(true);
		application.getDebugSettings().setAjaxDebugModeEnabled(true);
		application.getDebugSettings().setDevelopmentUtilitiesEnabled(true);
		application.getDebugSettings().setOutputComponentPath(true);
	}

	/**
	 * Sets the debug settings for deployment mode for the given application.
	 *
	 * @param application
	 *            the new debug settings for development
	 * @deprecated use instead {@link ApplicationUtils#setDeploymentModeConfiguration(Application)}
	 */
	@Deprecated
	public static void setDebugSettingsForDeployment(final Application application)
	{
		setDeploymentModeConfiguration(application);
	}

	/**
	 * Sets the debug settings for deployment mode for the given application.
	 *
	 * @param application
	 *            the application to set the settings
	 */
	public static void setDeploymentModeConfiguration(final Application application)
	{
		application.getMarkupSettings().setStripComments(true);
		// The resources are never polled. This are properties, html,
		// css, js files.
		application.getResourceSettings().setResourcePollFrequency(null);
		application.getResourceSettings()
			.setJavaScriptCompressor(new DefaultJavaScriptCompressor());
		// set the behavior if an missing resource is found...
		application.getResourceSettings().setThrowExceptionOnMissingResource(false);
		// debug settings...
		application.getDebugSettings().setComponentUseCheck(false);
		application.getDebugSettings().setAjaxDebugModeEnabled(false);
		application.getDebugSettings().setDevelopmentUtilitiesEnabled(false);

		application.getDebugSettings().setOutputComponentPath(false);
		application.getDebugSettings().setOutputMarkupContainerClassName(false);
		application.getDebugSettings().setLinePreciseReportingOnAddComponentEnabled(false);
		application.getDebugSettings().setLinePreciseReportingOnNewComponentEnabled(false);
	}

	/**
	 * Sets the settings for deployment mode for the given application.
	 *
	 * @param application
	 *            the new settings for deployment
	 * @deprecated use instead {@link ApplicationUtils#setDeploymentModeConfiguration(Application)}
	 */
	@Deprecated
	public static void setSettingsForDeployment(final Application application)
	{
		setDeploymentModeConfiguration(application);
	}

	/**
	 * Sets the deployment exception settings for the given application.
	 *
	 * @param application
	 *            the application
	 * @param applicationRequestCycleListener
	 *            the application request cycle listener
	 */
	public static void setExceptionSettingsForDeployment(final Application application,
		final AbstractRequestCycleListener applicationRequestCycleListener)
	{
		// show the exception page from us...
		application.getExceptionSettings().setUnexpectedExceptionDisplay(
			IExceptionSettings.SHOW_INTERNAL_ERROR_PAGE);
		// In case of unhandled exception redirect it to a custom page
		application.getRequestCycleListeners().add(applicationRequestCycleListener);
	}

	/**
	 * Sets the exception settings for development mode for the given application.
	 *
	 * @param application
	 *            the new exception settings for development
	 */
	public static void setExceptionSettingsForDevelopment(final Application application)
	{
		// show the exception page from wicket...
		application.getExceptionSettings().setUnexpectedExceptionDisplay(
			IExceptionSettings.SHOW_EXCEPTION_PAGE);
	}

	/**
	 * Adds the given file patterns to package resource guard from the given application.
	 *
	 * @param application
	 *            the application
	 * @param patterns
	 *            the patterns
	 */
	public static void addFilePatternsToPackageResourceGuard(final Application application,
		String... patterns)
	{
		IPackageResourceGuard packageResourceGuard = application.getResourceSettings()
			.getPackageResourceGuard();
		if (packageResourceGuard instanceof SecurePackageResourceGuard)
		{
			SecurePackageResourceGuard guard = (SecurePackageResourceGuard)packageResourceGuard;
			for (String pattern : patterns)
			{
				guard.addPattern(pattern);
			}
		}
	}

	/**
	 * Sets the root request mapper for the given application from the given httpPort and httpsPort.
	 *
	 * @param application
	 *            the application
	 * @param httpPort
	 *            the http port
	 * @param httpsPort
	 *            the https port
	 */
	public static void setRootRequestMapper(final Application application, final int httpPort,
		final int httpsPort)
	{
		application.setRootRequestMapper(new HttpsMapper(application.getRootRequestMapper(),
			new HttpsConfig(httpPort, httpsPort)));
	}

	/**
	 * Sets the RootRequestMapper for the given application from the given httpPort and httpsPort.
	 * Note: if the configuration type is RuntimeConfigurationType.DEVELOPMENT then only HTTP scheme
	 * will be returned.
	 *
	 * @param application
	 *            the application
	 * @param httpPort
	 *            the http port
	 * @param httpsPort
	 *            the https port
	 */
	public static void setRootRequestMapperForDevelopment(final Application application,
		final int httpPort, final int httpsPort)
	{
		application.setRootRequestMapper(new HttpsMapper(application.getRootRequestMapper(),
			new HttpsConfig(httpPort, httpsPort))
		{
			@Override
			protected Scheme getDesiredSchemeFor(Class<? extends IRequestablePage> pageClass)
			{
				if (application.getConfigurationType().equals(RuntimeConfigurationType.DEVELOPMENT))
				{
					// is in development mode, returning Scheme.HTTP...
					return Scheme.HTTP;
				}
				else
				{
					// not in development mode, letting the mapper decide
					return super.getDesiredSchemeFor(pageClass);
				}
			}
		});
	}

	/**
	 * Use this method to enable hot deploy of your html templates on development. Works only with
	 * jetty. Only for
	 *
	 * @param application
	 *            the new html hot deploy
	 */
	public static void setHtmlHotDeploy(final WebApplication application)
	{
		application.getResourceSettings().setResourcePollFrequency(Duration.ONE_SECOND);
		String slash = "/";
		String realPath = application.getServletContext().getRealPath(slash);
		if (realPath != null && !realPath.endsWith(slash))
		{
			realPath += slash;
		}
		String javaSourcePath = realPath + "../java";
		String resourcesPath = realPath + "../resources";
		addResourceFinder(application, javaSourcePath);
		addResourceFinder(application, resourcesPath);
	}

	/**
	 * Adds the given resourcePath to the resource finder from the given application.
	 *
	 * @see IResourceSettings#getResourceFinders()
	 *
	 * @param application
	 *            the application
	 * @param resourcePath
	 *            the resource path
	 */
	public static void addResourceFinder(final WebApplication application, String resourcePath)
	{
		application.getResourceSettings().getResourceFinders()
			.add(new WebApplicationPath(application.getServletContext(), resourcePath));
	}


	/**
	 * Can be used to set the global settings for development and deployment mode for the given
	 * application.
	 *
	 * @param application
	 *            the application
	 * @param httpPort
	 *            the http port
	 * @param httpsPort
	 *            the https port
	 * @param footerFilterName
	 *            the footer filter name
	 * @param encoding
	 *            the encoding
	 * @param patterns
	 *            the patterns
	 */
	public static void setGlobalSettings(final WebApplication application, final int httpPort,
		final int httpsPort, final String footerFilterName, final String encoding,
		final String... patterns)
	{
		// Standard-Encoding for Markup-Files
		application.getMarkupSettings().setDefaultMarkupEncoding(encoding);
		// Sets the Response-Header to Character encoding
		// this means Content-Type text/html;charset=<encoding>
		application.getRequestCycleSettings().setResponseRequestEncoding(encoding);
		// set footer scripts...
		ApplicationUtils.setHeaderResponseDecorator(application, footerFilterName);
		// set up ports for http and https...
		ApplicationUtils.setRootRequestMapper(application, httpPort, httpsPort);
		// add file patterns to the resource guard...
		ApplicationUtils.addFilePatternsToPackageResourceGuard(application, patterns);
		// Removes(strips) all wicket tags like <wicket:panel>, <wicket:extend> and <wicket:child>.
		// This is very important to set to true if you use the library wicket-jquery-ui
		application.getMarkupSettings().setStripWicketTags(true);
	}
}