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
package de.alpharogroup.wicket.base.application;

import java.io.File;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.wicket.Page;
import org.apache.wicket.RuntimeConfigurationType;
import org.apache.wicket.core.request.mapper.MountedMapper;
import org.apache.wicket.pageStore.DiskDataStore;
import org.apache.wicket.pageStore.IDataStore;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.request.mapper.parameter.IPageParametersEncoder;
import org.apache.wicket.settings.IStoreSettings;
import org.apache.wicket.util.lang.Bytes;
import org.apache.wicket.util.time.Duration;
import org.apache.wicket.util.time.Time;
import org.joda.time.DateTime;

import de.alpharogroup.wicket.base.application.plugins.SecuritySettingsPlugin;
import lombok.Getter;

/**
 * The Class BaseWebApplication have factory methods for the application settings that should be
 * overwritten from the subclasses to provide they own settings and configurations.
 */
public abstract class BaseWebApplication extends WebApplication
{
	/** The logger constant. */
	protected static final Logger LOGGER = Logger.getLogger(BaseWebApplication.class.getName());

	/** The Constant DEFAULT_HTTP_PORT. */
	public static final int DEFAULT_HTTP_PORT = 9090;

	/** The Constant DEFAULT_HTTPS_PORT. */
	public static final int DEFAULT_HTTPS_PORT = 9443;

	/**
	 * Gets the startup date.
	 *
	 * @return the startup date
	 */
	@Getter
	private DateTime startupDate;

	/** The configuration properties. */
	@Getter
	private final Properties properties;

	/** The configuration properties resolver. */
	@Getter
	private final WicketConfigurationPropertiesResolver configurationPropertiesResolver;

	/**
	 * Initialization block.
	 **/
	{
		this.configurationPropertiesResolver = newConfigurationPropertiesResolver(getDefaultHttpPort(), getDefaultHttpsPort(), "config.properties");
		this.properties = this.configurationPropertiesResolver.getProperties();
	}

	/**
	 * Gets the elapsed duration since this application was initialized.
	 *
	 * @return the uptime
	 */
	public Duration getUptime()
	{
		final DateTime startup = getStartupDate();
		if (null != startup)
		{
			return Duration.elapsed(Time.valueOf(startup.toDate()));
		}
		return Duration.NONE;
	}

	/**
	 * Inits the application configuration for global, development and deployment.
	 *
	 * @see org.apache.wicket.Application#init()
	 */
	@Override
	public final void init()
	{
		this.startupDate = new DateTime();
		super.init();
		// set application configuration...
		onApplicationConfigurations();
	}

	/**
	 * Mounts a page class to the given path with the given {@link IPageParametersEncoder}.
	 *
	 * @param <T>
	 *            type of page
	 *
	 * @param path
	 *            the path to mount the page class on
	 * @param pageClass
	 *            the page class to be mounted
	 * @param pageParametersEncoder
	 *            the encoder for the page parameter to be mounted
	 */
	public <T extends Page> void mountPage(final String path, final Class<T> pageClass,
		final IPageParametersEncoder pageParametersEncoder)
	{
		mount(new MountedMapper(path, pageClass, pageParametersEncoder));
	}

	/**
	 * Factory method that can be overwritten to provide an application data store. Here the default
	 * will be returned.
	 *
	 * For instance:
	 *
	 * <pre>
	 * public void init() {
	 * ...
	 * getStoreSettings().setInmemoryCacheSize(30);
	 *
	 * setPageManagerProvider(new DefaultPageManagerProvider(this)
	 * {
	 * 	&#064;Override
	 * 	protected IDataStore newDataStore()
	 * 	{
	 * 		return newApplicationDataStore();
	 * 	}
	 * });
	 * ...
	 * }
	 * </pre>
	 *
	 * @return the IDataStore.
	 */
	protected IDataStore newApplicationDataStore()
	{
		final IStoreSettings storeSettings = getStoreSettings();
		final Bytes maxSizePerSession = storeSettings.getMaxSizePerSession();
		final File fileStoreFolder = storeSettings.getFileStoreFolder();
		return new DiskDataStore(this.getName(), fileStoreFolder, maxSizePerSession);
	}
	/**
	 * Gets the default http port.
	 *
	 * @return the default http port
	 */
	protected int getDefaultHttpPort() {
		return BaseWebApplication.DEFAULT_HTTP_PORT;
	}

	/**
	 *  Factory method to create a new {@link ConfigurationPropertiesResolver}.
	 *
	 * @param defaultHttpPort the default http port
	 * @param defaultHttpsPort the default https port
	 * @param propertiesFilename the properties filename
	 * @return the new {@link ConfigurationPropertiesResolver}.
	 */
	protected WicketConfigurationPropertiesResolver newConfigurationPropertiesResolver(final Integer defaultHttpPort, final Integer defaultHttpsPort, final String propertiesFilename) {
		return new WicketConfigurationPropertiesResolver(defaultHttpPort, defaultHttpsPort, propertiesFilename);
	}

	/**
	 * Factory method that can be overwritten to provide other http port than the default one.
	 *
	 * @return the int
	 */
	protected int newHttpPort()
	{
		return this.configurationPropertiesResolver.getHttpPort();
	}

	/**
	 * Gets the default https port.
	 *
	 * @return the default https port
	 */
	protected int getDefaultHttpsPort() {
		return BaseWebApplication.DEFAULT_HTTPS_PORT;
	}

	/**
	 * Factory method that can be overwritten to provide other https port than the default one.
	 *
	 * @return the int
	 */
	protected int newHttpsPort()
	{
		return this.configurationPropertiesResolver.getHttpsPort();
	}

	/**
	 * Sets the application configurations.
	 */
	protected void onApplicationConfigurations()
	{
		// set configuration before the application configuration...
		onBeforeApplicationConfigurations();
		// set global configurations for both development and deployment mode...
		onGlobalSettings();
		// set configuration for development...
		if (RuntimeConfigurationType.DEVELOPMENT.equals(this.getConfigurationType()))
		{
			onDevelopmentModeSettings();
		}
		// set configuration for deployment...
		if (RuntimeConfigurationType.DEPLOYMENT.equals(this.getConfigurationType()))
		{
			onDeploymentModeSettings();
		}
	}

	/**
	 * Called just before a the application configurations.
	 */
	protected void onBeforeApplicationConfigurations()
	{
		// Set security headers...
		onSecuritySettingsPlugin(this);
	}

	/**
	 * Callback method that can be overwritten to provide application specific deployment mode
	 * settings.
	 */
	protected void onDeploymentModeSettings()
	{
	}

	/**
	 * Callback method that can be overwritten to provide application specific development mode
	 * settings.
	 */
	protected void onDevelopmentModeSettings()
	{
	}

	/**
	 * Callback method that can be overwritten to provide application specific global settings.
	 */
	protected void onGlobalSettings()
	{
	}

	/**
	 * Callback method that can be overwritten to provide application specific security settings.
	 * Now the default will be set.
	 *
	 * @param application
	 *            the application
	 */
	protected void onSecuritySettingsPlugin(final WebApplication application)
	{
		new SecuritySettingsPlugin().install(application);
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

}
