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
package de.alpharogroup.wicket.components.examples.application;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import net.ftlines.wicketsource.WicketSource;

import org.apache.log4j.Logger;
import org.apache.wicket.Application;
import org.apache.wicket.IApplicationListener;
import org.apache.wicket.RuntimeConfigurationType;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.protocol.http.WebApplication;
import org.jaulp.wicket.PackageResourceReferences;
import org.jaulp.wicket.base.util.application.ApplicationUtils;

import de.alpharogroup.wicket.bootstrap3.application.WicketBootstrap3Application;
import de.alpharogroup.wicket.components.examples.home.HomePage;


/**
 * Application object for your web application. If you want to run this application without
 * deploying, run the Start class.
 */
public class WicketApplication extends WicketBootstrap3Application
{
	public static final int DEFAULT_HTTP_PORT = 9090;
	public static final int DEFAULT_HTTPS_PORT = 9443;
	/** The Constant logger. */
	private static final Logger LOGGER = Logger.getLogger(WicketApplication.class.getName());

	/**
	 * @see org.apache.wicket.Application#getHomePage()
	 */
	@Override
	public Class<? extends WebPage> getHomePage()
	{
		return HomePage.class;
	}

	/**
	 * @see org.apache.wicket.Application#init()
	 */
	@Override
	public void init()
	{
		super.init();
		// initialize all header contributors
		initializeAllHeaderContributors();
		// set application configuration...
		setApplicationConfiguration();
	}

	protected void setApplicationConfiguration()
	{
		// set global settings for both development and deployment mode...
		setGlobalSettings(this, getHttpPort(), getHttpsPort());
		// set configuration for development...
		if (RuntimeConfigurationType.DEVELOPMENT.equals(this.getConfigurationType()))
		{
			setDevelopmentModeSettings(this);
		}
		// set configuration for deployment...
		if (RuntimeConfigurationType.DEPLOYMENT.equals(this.getConfigurationType()))
		{
			setDeploymentModeSettings(this);
		}
	}

	protected void setDeploymentModeSettings(final WebApplication application)
	{
		// set exception handling for custom error page...
		ApplicationUtils.setExceptionSettingsForDeployment(application,
			new ApplicationRequestCycleListener());
		ApplicationUtils.setDeploymentModeConfiguration(application);
	}

	protected void setDevelopmentModeSettings(final WebApplication application)
	{
		// Adds the references from source code to the browser to reference in eclipse....
		WicketSource.configure(application);
		ApplicationUtils.setHtmlHotDeploy(application);
		ApplicationUtils.setDebugSettingsForDevelopment(application);
		ApplicationUtils.setExceptionSettingsForDevelopment(application);
		// set the behavior if an missing resource is found...
		application.getResourceSettings().setThrowExceptionOnMissingResource(true);
		// add an applicationListener...
		application.getApplicationListeners().add(new IApplicationListener()
		{
			@Override
			public void onBeforeDestroyed(Application application)
			{
				LOGGER.info("Wicket application is destroyed");
				// here can comes code that is needed before the application
				// been destroyed...
			}

			@Override
			public void onAfterInitialized(Application application)
			{
				LOGGER.info("Wicket application is initialized");
				// here can comes code that is needed after the application
				// initialization...
			}
		});
	}

	protected void setGlobalSettings(final WebApplication application, final int httpPort,
		final int httpsPort)
	{
		ApplicationUtils.setGlobalSettings(application, httpPort, httpsPort, FOOTER_FILTER_NAME,
			"UTF-8", "+*.css", "+*.png");
	}

	@Override
	public RuntimeConfigurationType getConfigurationType()
	{
		RuntimeConfigurationType configType = super.getConfigurationType();
		return configType;
	}

	/**
	 * Checks if is on development mode.
	 * 
	 * @return true, if is on development mode
	 */
	public boolean isOnDevelopmentMode()
	{
		return getConfigurationType().equals(RuntimeConfigurationType.DEVELOPMENT);
	}

	protected int getHttpPort()
	{
		if (getProperties().containsKey("application.http.port"))
		{
			String httpPortString = getProperties().getProperty("application.http.port");
			try
			{
				int httpPort = Integer.valueOf(httpPortString);
				return httpPort;
			}
			catch (NumberFormatException e)
			{
				return WicketApplication.DEFAULT_HTTP_PORT;
			}
		}
		return WicketApplication.DEFAULT_HTTP_PORT;
	}

	protected int getHttpsPort()
	{
		if (getProperties().containsKey("application.https.port"))
		{
			String httpsPortString = getProperties().getProperty("application.https.port");
			try
			{
				int httpsPort = Integer.valueOf(httpsPortString);
				return httpsPort;
			}
			catch (NumberFormatException e)
			{
				return WicketApplication.DEFAULT_HTTPS_PORT;
			}
		}
		return WicketApplication.DEFAULT_HTTPS_PORT;
	}

	/**
	 * Initialize all header contributors.
	 */
	private void initializeAllHeaderContributors()
	{
		try
		{
			initializeResources();
		}
		catch (final ClassNotFoundException e)
		{
			LOGGER
				.error(
					"ClassNotFoundException in the initializeResources-Method from the WicketApplication.",
					e);
		}
		catch (final IOException e)
		{
			LOGGER.error(
				"IOException in the initializeResources-Method from the WicketApplication.", e);
		}
	}

	public String getDomainName()
	{
		return "jaulp-wicket-components.com";
	}

	/**
	 * Initialize resources.
	 * 
	 * @throws ClassNotFoundException
	 *             the class not found exception
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public void initializeResources() throws ClassNotFoundException, IOException
	{
		PackageResourceReferences prr = PackageResourceReferences.getInstance();
		prr.initializeResources(getPackageToScan());
	}

	/**
	 * Gets the WicketApplication.
	 * 
	 * @return the WicketApplication object.
	 */
	public static WicketApplication get()
	{
		return ((WicketApplication)Application.get());
	}

	public String getPackageToScan()
	{
		return "de.alpharogroup.wicket.components.examples";
	}

	public List<String> getPackagesToScan()
	{
		return Arrays.asList();
	}
}
