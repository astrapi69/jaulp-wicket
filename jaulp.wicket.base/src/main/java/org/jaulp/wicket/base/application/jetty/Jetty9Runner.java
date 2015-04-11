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
package org.jaulp.wicket.base.application.jetty;

import java.io.File;
import java.lang.management.ManagementFactory;
import java.util.EnumSet;
import java.util.Map;
import java.util.Map.Entry;

import javax.management.MBeanServer;
import javax.servlet.DispatcherType;

import net.sourceforge.jaulp.file.search.PathFinder;

import org.apache.wicket.Application;
import org.apache.wicket.protocol.http.ContextParamWebApplicationFactory;
import org.apache.wicket.protocol.http.WicketFilter;
import org.apache.wicket.util.lang.Generics;
import org.eclipse.jetty.jmx.MBeanContainer;
import org.eclipse.jetty.server.HttpConfiguration;
import org.eclipse.jetty.server.HttpConnectionFactory;
import org.eclipse.jetty.server.SecureRequestCustomizer;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.server.SslConnectionFactory;
import org.eclipse.jetty.servlet.DefaultServlet;
import org.eclipse.jetty.servlet.FilterHolder;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.util.resource.Resource;
import org.eclipse.jetty.util.ssl.SslContextFactory;
import org.eclipse.jetty.webapp.WebAppContext;

public class Jetty9Runner
{

	public static void run(Class<? extends Application> applicationClass, File webapp)
	{
		run(applicationClass, webapp, 8080, 8443, "wicket");
	}

	public static void run(Class<? extends Application> applicationClass, File webapp,
		int httpPort, int httpsPort, String keyStorePassword)
	{
		runWithNewServer(getServletContextHandler(applicationClass, webapp), httpPort, httpsPort);
	}

	public static void runWithNewServer(ServletContextHandler servletContextHandler, int httpPort,
		int httpsPort)
	{
		runWithNewServer(servletContextHandler, httpPort, httpsPort, "wicket");
	}

	public static void run(Jetty9RunConfiguration config)
	{
		Server server = new Server();
		run(server, config);
	}

	public static void runWithNewServer(ServletContextHandler servletContextHandler, int httpPort,
		int httpsPort, String keyStorePassword)
	{
		Server server = new Server();
		run(server, servletContextHandler, httpPort, httpsPort, keyStorePassword, "/keystore");
	}

	public static void run(ServletContextHandler servletContextHandler, int httpPort,
		int httpsPort, String keyStorePassword)
	{
		Server server = new Server();
		run(server, servletContextHandler, httpPort, httpsPort, keyStorePassword, "/keystore");
	}

	public static void run(Server server, Jetty9RunConfiguration config)
	{
		HttpConfiguration http_config = new HttpConfiguration();
		http_config.setSecureScheme("https");
		http_config.setSecurePort(config.getHttpsPort());
		http_config.setOutputBufferSize(32768);

		ServerConnector http = new ServerConnector(server, new HttpConnectionFactory(http_config));
		http.setPort(config.getHttpPort());
		http.setIdleTimeout(1000 * 60 * 60);

		server.addConnector(http);

		Resource keystore = Resource.newClassPathResource(config.getKeyStorePathResource());
		if (keystore != null && keystore.exists())
		{
			// if a keystore for a SSL certificate is available, start a SSL
			// connector on port 'httpsPort'.
			// By default, the quickstart comes with a Apache Wicket Quickstart
			// Certificate that expires about half way september 2021. Do not
			// use this certificate anywhere important as the passwords are
			// available in the source.

			SslContextFactory sslContextFactory = new SslContextFactory();
			sslContextFactory.setKeyStoreResource(keystore);
			sslContextFactory.setKeyStorePassword(config.getKeyStorePassword());
			sslContextFactory.setKeyManagerPassword(config.getKeyStorePassword());

			HttpConfiguration https_config = new HttpConfiguration(http_config);
			https_config.addCustomizer(new SecureRequestCustomizer());

			ServerConnector https = new ServerConnector(server, new SslConnectionFactory(
				sslContextFactory, "http/1.1"), new HttpConnectionFactory(https_config));
			https.setPort(config.getHttpsPort());
			https.setIdleTimeout(500000);

			server.addConnector(https);
			System.out.println("SSL access to the examples has been enabled on port "
				+ config.getHttpsPort());
			System.out.println("You can access the application using SSL on https://localhost:"
				+ config.getHttpsPort());
			System.out.println();
		}

		server.setHandler(config.getServletContextHandler());

		MBeanServer mBeanServer = ManagementFactory.getPlatformMBeanServer();
		MBeanContainer mBeanContainer = new MBeanContainer(mBeanServer);
		server.addEventListener(mBeanContainer);
		server.addBean(mBeanContainer);

		try
		{
			server.start();
			server.join();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			System.exit(100);
		}
	}

	public static void run(Server server, ServletContextHandler servletContextHandler,
		int httpPort, int httpsPort, String keyStorePassword, String keyStorePathResource)
	{
		run(server, Jetty9RunConfiguration.builder().servletContextHandler(servletContextHandler)
			.httpPort(httpPort).httpsPort(httpsPort).keyStorePassword(keyStorePassword)
			.keyStorePathResource(keyStorePathResource).build());
	}

	public static ServletContextHandler getServletContextHandler(
		Class<? extends Application> applicationClass, String contextPath, File webapp,
		int maxInactiveInterval, String filterPath)
	{
		Map<String, String> initParameters = Generics.newHashMap();
		initParameters.put(WicketFilter.FILTER_MAPPING_PARAM, filterPath);
		return getServletContextHandler(ServletContextHandlerConfiguration.builder()
			.applicationClass(applicationClass).contextPath(contextPath).webapp(webapp)
			.maxInactiveInterval(maxInactiveInterval)
			.initParameter(WicketFilter.FILTER_MAPPING_PARAM, filterPath).filterPath(filterPath)
			.build());
	}

	public static ServletContextHandler getServletContextHandler(
		ServletContextHandlerConfiguration configuration)
	{
		final ServletContextHandler context = new ServletContextHandler(
			ServletContextHandler.SESSIONS);
		context.setContextPath(configuration.getContextPath());

		context.setResourceBase(configuration.getWebapp().getAbsolutePath());

		final FilterHolder filter = new FilterHolder(WicketFilter.class);
		filter.setInitParameter(ContextParamWebApplicationFactory.APP_CLASS_PARAM, configuration
			.getApplicationClass().getName());
		for (Entry<String, String> initParameter : configuration.getInitParameters().entrySet())
		{
			filter.setInitParameter(initParameter.getKey(), initParameter.getValue());
		}
		context.addFilter(filter, configuration.getFilterPath(),
			EnumSet.of(DispatcherType.REQUEST, DispatcherType.ERROR));
		context.addServlet(DefaultServlet.class, configuration.getFilterPath());

		context.getSessionHandler().getSessionManager()
			.setMaxInactiveInterval(configuration.getMaxInactiveInterval());
		return context;
	}

	public static ServletContextHandler getServletContextHandler(
		Class<? extends Application> applicationClass, File webapp)
	{
		return getServletContextHandler(applicationClass, "/", webapp, 300, "/*");
	}

	public static ServletContextHandler getServletContextHandler(
		Class<? extends Application> applicationClass)
	{
		return getServletContextHandler(applicationClass, "/", PathFinder.getSrcMainJavaDir(), 300,
			"/*");
	}

	public static WebAppContext getWebAppContext(Server server, String projectname)
	{
		File webapp = PathFinder.getProjectDirectory();
		File wa = PathFinder.getRelativePath(webapp, projectname, "src", "main", "webapp");
		WebAppContext webAppContext = new WebAppContext();
		webAppContext.setServer(server);
		webAppContext.setContextPath("/");
		webAppContext.setWar(wa.getAbsolutePath());
		return webAppContext;
	}
}
