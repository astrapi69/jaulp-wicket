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
package de.alpharogroup.wicket.components.examples;

import java.io.File;
import java.util.EnumSet;

import javax.servlet.DispatcherType;

import org.apache.wicket.Application;
import org.apache.wicket.protocol.http.ContextParamWebApplicationFactory;
import org.apache.wicket.protocol.http.WicketFilter;
import org.apache.wicket.util.time.Duration;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.bio.SocketConnector;
import org.eclipse.jetty.server.ssl.SslSocketConnector;
import org.eclipse.jetty.servlet.DefaultServlet;
import org.eclipse.jetty.servlet.FilterHolder;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.util.resource.Resource;
import org.eclipse.jetty.util.ssl.SslContextFactory;
import org.eclipse.jetty.webapp.WebAppContext;

import de.alpharogroup.wicket.components.examples.application.WicketApplication;

public class StartComponentExamples
{
	public static void main(String[] args) throws Exception
	{
		eclipseStart();

		// alternate start with params...
		// File webapp = PathFinder.getSrcMainJavaDir();
		// new File(PathFinder.getSrcMainJavaDir(), "webapp");
		// startWebapp(WicketApplication.class, "/", webapp, WicketApplication.DEFAULT_HTTP_PORT,
		// WicketApplication.DEFAULT_HTTPS_PORT, 300, "/*");
	}


	/**
	 * Starts the given wicket application with the given parameters.
	 * 
	 * @param applicationClass
	 * @param contextPath
	 * @param webapp
	 * @param httpPort
	 * @param httpsPort
	 * @param maxInactiveInterval
	 * @param filterPath
	 * @throws Exception
	 */
	public static void startWebapp(final Class<? extends Application> applicationClass,
		String contextPath, File webapp, int httpPort, int maxInactiveInterval, String filterPath)
		throws Exception
	{
		startWebapp(applicationClass, contextPath, webapp, httpPort, 8443, maxInactiveInterval,
			filterPath);
	}


	/**
	 * Starts the given wicket application with the given parameters.
	 * 
	 * @param applicationClass
	 * @param contextPath
	 * @param webapp
	 * @param httpPort
	 * @param httpsPort
	 * @param maxInactiveInterval
	 * @param filterPath
	 * @throws Exception
	 */
	public static void startWebapp(final Class<? extends Application> applicationClass,
		String contextPath, File webapp, int httpPort, int httpsPort, int maxInactiveInterval,
		String filterPath) throws Exception
	{
		final Server server = new Server(httpPort);

		final ServletContextHandler context = new ServletContextHandler(
			ServletContextHandler.SESSIONS);
		context.setContextPath(contextPath);

		context.setResourceBase(webapp.getAbsolutePath());

		final FilterHolder filter = new FilterHolder(WicketFilter.class);
		filter.setInitParameter(ContextParamWebApplicationFactory.APP_CLASS_PARAM,
			applicationClass.getName());
		filter.setInitParameter(WicketFilter.FILTER_MAPPING_PARAM, filterPath);
		context.addFilter(filter, filterPath,
			EnumSet.of(DispatcherType.REQUEST, DispatcherType.ERROR));
		context.addServlet(DefaultServlet.class, filterPath);

		context.getSessionHandler().getSessionManager().setMaxInactiveInterval(maxInactiveInterval);

		server.setHandler(context);

		server.start();
		server.join();

	}

	private static void eclipseStart()
	{
		int timeout = (int)Duration.ONE_HOUR.getMilliseconds();

		Server server = new Server();
		SocketConnector connector = new SocketConnector();

		// Set some timeout options to make debugging easier.
		connector.setMaxIdleTime(timeout);
		connector.setSoLingerTime(-1);
		connector.setPort(WicketApplication.DEFAULT_HTTP_PORT);
		server.addConnector(connector);

		Resource keystore = Resource.newClassPathResource("/keystore");
		if (keystore != null && keystore.exists())
		{
			// if a keystore for a SSL certificate is available, start a SSL
			// connector on port WicketApplication.DEFAULT_HTTPS_PORT.
			// By default, the quickstart comes with a Apache Wicket Quickstart
			// Certificate that expires about half way september 2021. Do not
			// use this certificate anywhere important as the passwords are
			// available in the source.


			SslContextFactory factory = new SslContextFactory();
			factory.setKeyStoreResource(keystore);
			factory.setKeyStorePassword("wicket");
			factory.setTrustStoreResource(keystore);
			factory.setKeyManagerPassword("wicket");
			SslSocketConnector sslConnector = new SslSocketConnector(factory);
			sslConnector.setMaxIdleTime(timeout);
			sslConnector.setPort(WicketApplication.DEFAULT_HTTPS_PORT);
			connector.setConfidentialPort(WicketApplication.DEFAULT_HTTPS_PORT);
			sslConnector.setAcceptors(4);
			server.addConnector(sslConnector);

			System.out.println("SSL access to the quickstart has been enabled on port "
				+ WicketApplication.DEFAULT_HTTPS_PORT);
			System.out.println("You can access the application using SSL on https://localhost:"
				+ WicketApplication.DEFAULT_HTTPS_PORT);
			System.out.println();
		}

		WebAppContext bb = new WebAppContext();
		bb.setServer(server);
		bb.setContextPath("/");
		bb.setWar("src/main/webapp");

		// START JMX SERVER
		// MBeanServer mBeanServer = ManagementFactory.getPlatformMBeanServer();
		// MBeanContainer mBeanContainer = new MBeanContainer(mBeanServer);
		// server.getContainer().addEventListener(mBeanContainer);
		// mBeanContainer.start();

		server.setHandler(bb);

		try
		{
			System.out.println(">>> STARTING EMBEDDED JETTY SERVER, PRESS ANY KEY TO STOP");
			server.start();
			System.in.read();
			System.out.println(">>> STOPPING EMBEDDED JETTY SERVER");
			server.stop();
			server.join();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			System.exit(1);
		}
	}

}
