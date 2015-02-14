package org.jaulp.wicket.base.application;

import lombok.Getter;

import org.apache.log4j.Logger;
import org.apache.wicket.RuntimeConfigurationType;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.util.time.Duration;
import org.apache.wicket.util.time.Time;
import org.joda.time.DateTime;

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
	/** The Constant logger. */
	@Getter
	private DateTime startupDate;

	/**
	 * Inits the application configuration for global, development and deployment.
	 *
	 * @see org.apache.wicket.Application#init()
	 */
	@Override
	public void init()
	{
		this.startupDate = new DateTime();
		super.init();
		// set configuration before the application configuration...
		onBeforeApplicationConfigurations();
		// set application configuration...
		onApplicationConfigurations();
	}

	/**
	 * Gets the elapsed duration since this application was initialized.
	 */
	public Duration getUptime()
	{
		DateTime startup = getStartupDate();
		if (null != startup)
		{
			return Duration.elapsed(Time.valueOf(startup.toDate()));
		}
		return Duration.NONE;
	}

	/**
	 * Called just before a the application configurations.
	 */
	protected void onBeforeApplicationConfigurations()
	{
	}

	/**
	 * Sets the application configurations.
	 */
	protected void onApplicationConfigurations()
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
	 * Factory method that can be overwritten to provide application specific deployment mode
	 * settings.
	 */
	protected void newDeploymentModeSettings()
	{
	}

	/**
	 * Factory method that can be overwritten to provide application specific development mode
	 * settings.
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
