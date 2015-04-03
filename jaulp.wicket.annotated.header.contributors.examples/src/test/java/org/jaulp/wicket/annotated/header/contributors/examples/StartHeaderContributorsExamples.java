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
package org.jaulp.wicket.annotated.header.contributors.examples;

import java.io.File;

import net.sourceforge.jaulp.file.search.PathFinder;

import org.eclipse.jetty.servlet.ServletContextHandler;
import org.jaulp.wicket.base.application.jetty.Jetty9RunConfiguration;
import org.jaulp.wicket.base.application.jetty.Jetty9Runner;
import org.jaulp.wicket.base.application.jetty.ServletContextHandlerConfiguration;

public class StartHeaderContributorsExamples
{
	public static void main(String[] args) throws Exception
	{
		System.setProperty("wicket.configuration", "development");
		String projectname = "jaulp.wicket.annotated.header.contributors.examples";
		File projectDirectory = PathFinder.getProjectDirectory();
		File webapp = PathFinder.getRelativePath(projectDirectory, projectname, "src", "main",
			"webapp");
		ServletContextHandler servletContextHandler = Jetty9Runner.getServletContextHandler(ServletContextHandlerConfiguration.builder()
				.applicationClass(WicketApplication.class)
				.contextPath("/")
				.webapp(webapp)
				.maxInactiveInterval(300)
				.filterPath("/*")
				.build());
		Jetty9Runner.run(Jetty9RunConfiguration.builder()
			.servletContextHandler(servletContextHandler)
			.httpPort(WicketApplication.DEFAULT_HTTP_PORT)
			.httpsPort(WicketApplication.DEFAULT_HTTPS_PORT)
			.keyStorePassword("wicket")
			.keyStorePathResource("/keystore")
			.build());
	}
}
