package de.alpharogroup.wicket.base.application;

import java.io.IOException;
import java.util.Properties;

import org.apache.wicket.WicketRuntimeException;

import de.alpharogroup.resourcebundle.config.ConfigurationPropertiesResolver;
import de.alpharogroup.resourcebundle.properties.PropertiesExtensions;

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
		try
		{
			properties = PropertiesExtensions.loadProperties(getPropertiesFilename());
		}
		catch (final IOException e)
		{
			throw new WicketRuntimeException(e);
		}
		return properties;
	}

}
