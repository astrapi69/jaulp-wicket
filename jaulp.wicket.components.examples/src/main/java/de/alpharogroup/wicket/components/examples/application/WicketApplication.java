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
import org.apache.wicket.Session;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.request.Request;
import org.apache.wicket.request.Response;
import org.apache.wicket.resource.loader.BundleStringResourceLoader;

import de.alpharogroup.collections.ListExtensions;
import de.alpharogroup.wicket.PackageResourceReferences;
import de.alpharogroup.wicket.base.util.application.ApplicationExtensions;
import de.alpharogroup.wicket.bootstrap3.application.WicketBootstrap3Application;
import de.alpharogroup.wicket.components.examples.home.HomePage;
import de.alpharogroup.wicket.components.examples.resource.loading.MessageSource;


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
	public Class<? extends WebPage> getHomePage()
	{
		return HomePage.class;
	}

	/**
	 * {@inheritDoc}
	 */
	public Session newSession(final Request request, final Response response)
	{
		final WicketSession session = new WicketSession(request);
		session.bind();
		LOGGER.info("new session:" + session.getId());
		return session;
	}

	/**
	 * @see org.apache.wicket.Application#init()
	 */
	public void init()
	{
		super.init();
	}

	/**
	 * Called just before a the application configurations.
	 */
	protected void onBeforeApplicationConfigurations()
	{
		// initialize all header contributors
		initializeAllHeaderContributors();
		getResourceSettings().getStringResourceLoaders().add(
			new BundleStringResourceLoader(MessageSource.class.getName()));
	}


	protected void onDeploymentModeSettings()
	{
		super.onDeploymentModeSettings();
		ApplicationExtensions.setDefaultDeploymentModeConfiguration(this,
			new ApplicationRequestCycleListener());
	}

	protected void onDevelopmentModeSettings()
	{
		super.onDevelopmentModeSettings();
		// Adds the references from source code to the browser to reference in eclipse....
		WicketSource.configure(this);
		ApplicationExtensions.setDefaultDebugSettingsForDevelopment(this);

		// add an applicationListener...
		this.getApplicationListeners().add(new IApplicationListener()
		{
			public void onBeforeDestroyed(Application application)
			{
				LOGGER.info("Wicket application is destroyed");
				// here can comes code that is needed before the application
				// been destroyed...
			}

			public void onAfterInitialized(Application application)
			{
				LOGGER.info("Wicket application is initialized");
				// here can comes code that is needed after the application
				// initialization...
			}
		});
	}

	protected void onGlobalSettings()
	{
		super.onGlobalSettings();
		ApplicationExtensions.setGlobalSettings(this, newHttpPort(), newHttpsPort(),
			FOOTER_FILTER_NAME, "UTF-8", "+*.css", "+*.png", "+*.woff2", "+*.js.map");
	}

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

	protected int newHttpPort()
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

	protected int newHttpsPort()
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
		return (WicketApplication)Application.get();
	}

	public String getPackageToScan()
	{
		return ListExtensions.getFirst(getPackagesToScan());
	}

	public String[] getPackagesToScanAsArray()
	{
		String[] packagesToScan = { "de.alpharogroup.wicket.components.examples" };
		return packagesToScan;
	}

	public List<String> getPackagesToScan()
	{
		return Arrays.asList(getPackagesToScanAsArray());
	}

}
