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

import java.util.Properties;

import de.alpharogroup.resourcebundle.config.ConfigurationPropertiesResolver;

/**
 * The class {@link WicketConfigurationPropertiesResolver} resolves the configuration properties for
 * an wicket application like the http, https ports.
 */
public class WicketConfigurationPropertiesResolver extends ConfigurationPropertiesResolver
{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new {@link WicketConfigurationPropertiesResolver} with the default settings.
	 */
	public WicketConfigurationPropertiesResolver()
	{
		super(DEFAULT_HTTP_PORT, DEFAULT_HTTPS_PORT, DEFAULT_CONFIGURATION_PROPERTIES_FILENAME);
	}

	/**
	 * Instantiates a new {@link WicketConfigurationPropertiesResolver}.
	 *
	 * @param defaultHttpPort
	 *            the default http port
	 * @param defaultHttpsPort
	 *            the default https port
	 * @param propertiesFilename
	 *            the properties filename
	 */
	public WicketConfigurationPropertiesResolver(final Integer defaultHttpPort,
		final Integer defaultHttpsPort, final String propertiesFilename)
	{
		super(defaultHttpPort, defaultHttpsPort, propertiesFilename);
	}

	/**
	 * Try to load the configuration properties file from disk.
	 *
	 * @return configuration properties
	 */
	@Override
	protected Properties loadProperties()
	{
		final Properties properties;
		properties = null;
//					TODO PropertiesExtensions.loadProperties(getPropertiesFilename());
		return properties;
	}

}
