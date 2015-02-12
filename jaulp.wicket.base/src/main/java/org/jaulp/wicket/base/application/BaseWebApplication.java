package org.jaulp.wicket.base.application;

import org.apache.log4j.Logger;
import org.apache.wicket.RuntimeConfigurationType;
import org.apache.wicket.protocol.http.WebApplication;

/**
 * The Class BaseWebApplication have factory methods for the application settings that should be
 * overwritten from the subclasses to provide they own settings and configurations.
 */
public abstract class BaseWebApplication extends WebApplication
{

	/** The Constant DEFAULT_HTTP_PORT. */
	public static final int DEFAULT_HTTP_PORT = 9090;

	/** The Constant DEFAULT_HTTPS_PORT. */
	public static final int DEFAULT_HTTPS_PORT = 9443;
	/** The Constant logger. */
	protected static final Logger LOGGER = Logger.getLogger(BaseWebApplication.class.getName());

	/**
	 * Inits the application configuration for global, development and deployment.
	 *
	 * @see org.apache.wicket.Application#init()
	 */
	@Override
	public void init()
	{
		super.init();
		// set application configuration...
		setApplicationConfigurations();
	}

	/**
	 * Sets the application configurations.
	 */
	protected void setApplicationConfigurations()
	{
		// set global settings for both development and deployment mode...
		newGlobalSettings(this, newHttpPort(), newHttpsPort());
		// set configuration for development...
		if (RuntimeConfigurationType.DEVELOPMENT.equals(this.getConfigurationType()))
		{
			newDevelopmentModeSettings();
		}
		// set configuration for deployment...
		if (RuntimeConfigurationType.DEPLOYMENT.equals(this.getConfigurationType()))
		{
			newDeploymentModeSettings();
		}
	}

	/**
	 * Factory method that can be overwritten to provide other http port than the default one.
	 *
	 * @return the int
	 */
	protected int newHttpPort()
	{
		return BaseWebApplication.DEFAULT_HTTP_PORT;
	}

	/**
	 * Factory method that can be overwritten to provide other https port than the default one.
	 *
	 * @return the int
	 */
	protected int newHttpsPort()
	{
		return BaseWebApplication.DEFAULT_HTTPS_PORT;
	}

	/**
	 * Factory method that can be overwritten to provide application specific deployment mode settings.
	 */
	protected void newDeploymentModeSettings()
	{
	}

	/**
	 * Factory method that can be overwritten to provide application specific development mode settings.
	 */
	protected void newDevelopmentModeSettings()
	{
	}

	/**
	 * Factory method that can be overwritten to provide application specific global settings.
	 *
	 * @param application
	 *            the application
	 * @param httpPort
	 *            the http port
	 * @param httpsPort
	 *            the https port
	 */
	protected void newGlobalSettings(final WebApplication application, final int httpPort,
		final int httpsPort)
	{
	}

}
