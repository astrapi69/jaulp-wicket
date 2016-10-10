package de.alpharogroup.wicket.base.application;

import java.io.IOException;
import java.io.Serializable;
import java.util.Optional;
import java.util.Properties;

import org.apache.wicket.WicketRuntimeException;
import org.apache.wicket.util.lang.Args;

import de.alpharogroup.resourcebundle.properties.PropertiesExtensions;
import lombok.Getter;

/**
 * The class {@link ConfigurationPropertiesResolver}.
 */
public class ConfigurationPropertiesResolver implements Serializable
{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The constant for the default file name of the configuration properties file. */
	public static final String DEFAULT_CONFIGURATION_PROPERTIES_FILENAME = "config.properties";

	/** The constant for the default http port. */
	public static final int DEFAULT_HTTP_PORT = 8080;

	/** The constant for the default http port. */
	public static final int DEFAULT_HTTPS_PORT = 8443;

	/**
	 * The default http port.
	 */
	@Getter
	private final int defaultHttpPort;

	/**
	 * The default https port.
	 */
	@Getter
	private final int defaultHttpsPort;

	/**
	 * The http port.
	 */
	@Getter
	private final int httpPort;

	/**
	 * The https port.
	 */
	@Getter
	private final int httpsPort;

	/**
	 * The properties filename.
	 */
	@Getter
	private final String propertiesFilename;

	/** The configuration properties. */
	@Getter
	private final Properties properties;

	/**
	 * Instantiates a new {@link ConfigurationPropertiesResolver} with the default settings.
	 */
	public ConfigurationPropertiesResolver()
	{
		this(DEFAULT_HTTP_PORT, DEFAULT_HTTPS_PORT, DEFAULT_CONFIGURATION_PROPERTIES_FILENAME);
	}

	/**
	 * Instantiates a new {@link ConfigurationPropertiesResolver}.
	 *
	 * @param defaultHttpPort the default http port
	 * @param defaultHttpsPort the default https port
	 * @param propertiesFilename the properties filename
	 */
	public ConfigurationPropertiesResolver(final Integer defaultHttpPort, final Integer defaultHttpsPort, final String propertiesFilename)
	{
		this.defaultHttpPort = Args.notNull(defaultHttpPort, "defaultHttpPort");
		this.defaultHttpsPort = Args.notNull(defaultHttpsPort, "defaultHttpsPort");
		this.propertiesFilename =  Args.notNull(propertiesFilename, "propertiesFilename");
		this.properties = loadProperties();
		this.httpPort = resolveHttpPort();
		this.httpsPort = resolveHttpsPort();
	}

	/**
	 * Resolves the http port from the configuration properties file.
	 *
	 * @return the resolved http port or if not found the default http port.
	 */
	private int resolveHttpPort() {
		final Optional<Integer> optionalHttpPort = getOptionalHttpPort();
		if(optionalHttpPort.isPresent()) {
			return optionalHttpPort.get();
		}
		return getDefaultHttpPort();
	}

	/**
	 * Try to get the http port from the properties. If it does not exists an empty {@link Optional} will be returned.
	 *
	 * @return the optional http port
	 */
	private Optional<Integer> getOptionalHttpPort() {
		if (getProperties()!= null && getProperties().containsKey("application.http.port"))
		{
			final String httpPortString = getProperties().getProperty("application.http.port");
			try
			{
				final Integer httpPort = Integer.valueOf(httpPortString);
				return Optional.of( httpPort );
			}
			catch (final NumberFormatException e)
			{
				return Optional.empty();
			}
		}
		return Optional.empty();
	}


	/**
	 * Resolves the https port from the configuration properties file.
	 *
	 * @return the resolved https port or if not found the default https port.
	 */
	private int resolveHttpsPort() {
		final Optional<Integer> optionalHttpsPort = getOptionalHttpsPort();
		if(optionalHttpsPort.isPresent()) {
			return optionalHttpsPort.get();
		}
		return getDefaultHttpsPort();
	}


	/**
	 * Try to get the https port from the properties. If it does not exists an empty {@link Optional} will be returned.
	 *
	 * @return the optional https port
	 */
	private Optional<Integer> getOptionalHttpsPort() {
		if (getProperties()!= null && getProperties().containsKey("application.https.port"))
		{
			final String httpsPortString = getProperties().getProperty("application.https.port");
			try
			{
				final Integer httpsPort = Integer.valueOf(httpsPortString);
				return Optional.of( httpsPort );
			}
			catch (final NumberFormatException e)
			{
				return Optional.empty();
			}
		}
		return Optional.empty();
	}

	/**
	 * Try to load the configuration properties file from disk.
	 *
	 * @return configuration properties
	 */
	private Properties loadProperties()
	{
		final Properties properties;
		try
		{
			properties = PropertiesExtensions.loadProperties(this.propertiesFilename);
		}
		catch (final IOException e)
		{
			throw new WicketRuntimeException(e);
		}
		return properties;
	}

}
