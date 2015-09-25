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
import java.io.IOException;

import org.apache.log4j.Logger;
import org.apache.wicket.protocol.http.ContextParamWebApplicationFactory;
import org.apache.wicket.protocol.http.WicketFilter;
import org.apache.wicket.util.time.Duration;
import org.eclipse.jetty.deploy.DeploymentManager;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.ContextHandlerCollection;
import org.eclipse.jetty.servlet.DefaultServlet;
import org.eclipse.jetty.servlet.ServletContextHandler;

import de.alpharogroup.file.delete.DeleteFileUtils;
import de.alpharogroup.file.search.PathFinder;
import de.alpharogroup.jetty9.runner.Jetty9Runner;
import de.alpharogroup.jetty9.runner.config.FilterHolderConfiguration;
import de.alpharogroup.jetty9.runner.config.Jetty9RunConfiguration;
import de.alpharogroup.jetty9.runner.config.ServletContextHandlerConfiguration;
import de.alpharogroup.jetty9.runner.config.ServletHolderConfiguration;
import de.alpharogroup.jetty9.runner.factories.DeploymentManagerFactory;
import de.alpharogroup.jetty9.runner.factories.ServletContextHandlerFactory;
import de.alpharogroup.jetty9.runner.log.LoggerExtensions;
import de.alpharogroup.wicket.components.examples.application.WicketApplication;
import lombok.experimental.ExtensionMethod;

@ExtensionMethod(LoggerExtensions.class)
public class StartComponentExamples
{
	public static void main(final String[] args)
	{
		final int sessionTimeout = (int)Duration.minutes(1).seconds();// set timeout to 30min(60sec
																		// *
																		// 30min=1800sec)...
		System.setProperty("wicket.configuration", "development");
		final String projectname = "jaulp.wicket.components.examples";
		final File projectDirectory = PathFinder.getProjectDirectory();
		final File webapp;
		if (projectDirectory.getAbsolutePath().endsWith(projectname))
		{
			webapp = PathFinder.getRelativePath(projectDirectory, "src", "main", "webapp");
		}
		else
		{
			webapp = PathFinder.getRelativePath(projectDirectory, projectname, "src", "main",
				"webapp");
		}
		final File logfile = new File(projectDirectory, "application.log");
		if (logfile.exists())
		{
			try
			{
				DeleteFileUtils.delete(logfile);
			}
			catch (final IOException e)
			{
				Logger.getRootLogger().error("logfile could not deleted.", e);
			}
		}
		final String absolutePathFromLogfile = logfile.getAbsolutePath();
		final String filterPath = "/*";
		// Add a file appender to the logger programatically
		LoggerExtensions.addFileAppender(Logger.getRootLogger(),
			LoggerExtensions.newFileAppender(absolutePathFromLogfile));

		final ContextHandlerCollection contexts = new ContextHandlerCollection();

		final ServletContextHandler servletContextHandler = ServletContextHandlerFactory
			.getNewServletContextHandler(
				ServletContextHandlerConfiguration.builder().parent(contexts)
					.filterHolderConfiguration(FilterHolderConfiguration.builder()
						.filterClass(WicketFilter.class).filterPath(filterPath)
						.initParameter(WicketFilter.FILTER_MAPPING_PARAM, filterPath)
						.initParameter(ContextParamWebApplicationFactory.APP_CLASS_PARAM,
							WicketApplication.class.getName())
						.build())
				.servletHolderConfiguration(ServletHolderConfiguration.builder()
					.servletClass(DefaultServlet.class).pathSpec(filterPath).build())
				.contextPath("/").webapp(webapp).maxInactiveInterval(sessionTimeout)
				.filterPath(filterPath).build());

		final DeploymentManager deployer = DeploymentManagerFactory.newDeploymentManager(contexts,
			webapp.getAbsolutePath(), null);

		final Jetty9RunConfiguration config = newJetty9RunConfiguration(servletContextHandler,
			contexts, deployer);
		final Server server = new Server();
		Jetty9Runner.runServletContextHandler(server, config);
	}


	private static Jetty9RunConfiguration newJetty9RunConfiguration(
		final ServletContextHandler servletContextHandler, final ContextHandlerCollection contexts,
		final DeploymentManager deployer)
	{
		final Jetty9RunConfiguration config = Jetty9RunConfiguration.builder()
			.servletContextHandler(servletContextHandler).contexts(contexts).deployer(deployer)
			.httpPort(WicketApplication.DEFAULT_HTTP_PORT)
			.httpsPort(WicketApplication.DEFAULT_HTTPS_PORT).keyStorePassword("wicket")
			.keyStorePathResource("/keystore").build();
		return config;
	}
}
