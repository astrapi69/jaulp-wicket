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
package de.alpharogroup.wicket.data.provider.examples;

import org.apache.wicket.Page;
import org.apache.wicket.protocol.http.WebApplication;

import de.alpharogroup.wicket.data.provider.examples.pages.home.HomePage;

/**
 * Application object for your web application. If you want to run this application without
 * deploying, run the Start class.
 */
public class WicketApplication extends WebApplication
{
	public static final int DEFAULT_HTTP_PORT = 9090;
	public static final int DEFAULT_HTTPS_PORT = 9443;

	/**
	 * Constructor
	 */
	public WicketApplication()
	{
	}

	@Override
	public Class<? extends Page> getHomePage()
	{
		return HomePage.class;
	}

}
