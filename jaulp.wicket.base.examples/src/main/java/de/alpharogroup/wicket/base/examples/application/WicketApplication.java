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
package de.alpharogroup.wicket.base.examples.application;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.apache.wicket.Application;
import org.apache.wicket.core.request.mapper.CryptoMapper;
import org.apache.wicket.core.util.crypt.KeyInSessionSunJceCryptFactory;
import org.apache.wicket.protocol.http.WebApplication;
import org.wicketstuff.annotation.scan.AnnotatedMountScanner;

import de.alpharogroup.wicket.PackageResourceReferences;
import de.alpharogroup.wicket.base.examples.HomePage;
import de.alpharogroup.wicket.base.request.mapper.HighScoreRequestMapper;

/**
 * Application object for your web application. If you want to run this application without
 * deploying, run the Start class.
 */
public class WicketApplication extends WebApplication
{
	public static final int DEFAULT_HTTP_PORT = 9090;
	public static final int DEFAULT_HTTPS_PORT = 9443;
	/** The Constant logger. */
	private static final Logger logger = Logger.getLogger(WicketApplication.class.getName());

	/**
	 * Gets the WicketApplication.
	 *
	 * @return the WicketApplication object.
	 */
	public static WicketApplication get()
	{
		return (WicketApplication)Application.get();
	}

	/**
	 * @see org.apache.wicket.Application#getHomePage()
	 */
	@Override
	public Class<HomePage> getHomePage()
	{
		return HomePage.class;
	}

	public String getPackageToScan()
	{
		return "de.alpharogroup.wicket.base";
	}

	/**
	 * @see org.apache.wicket.Application#init()
	 */
	@Override
	public void init()
	{
		super.init();

		// install crypto mapper to encrypt all application urls
		getSecuritySettings().setCryptFactory(new KeyInSessionSunJceCryptFactory());
		setRootRequestMapper(new HighScoreRequestMapper().register(new CryptoMapper(
			getRootRequestMapper(), this)));
		initializeAllHeaderContributors();
		new AnnotatedMountScanner().scanPackage(getPackageToScan()).mount(this);
		// add your configuration here
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
			logger
				.error(
					"ClassNotFoundException in the initializeResources-Method from the WicketApplication.",
					e);
		}
		catch (final IOException e)
		{
			logger.error(
				"IOException in the initializeResources-Method from the WicketApplication.", e);
		}
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
		final PackageResourceReferences prr = PackageResourceReferences.getInstance();
		prr.initializeResources(getPackageToScan());
	}
}
