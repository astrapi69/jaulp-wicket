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
package de.alpharogroup.wicket.dialogs.examples;

import org.apache.wicket.Application;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.util.file.Folder;

/**
 * Application object for your web application. If you want to run this application without
 * deploying, run the Start class.
 */
public class WicketApplication extends WebApplication
{
	public static final int DEFAULT_HTTP_PORT = 9090;
	public static final int DEFAULT_HTTPS_PORT = 9443;

	/**
	 * Gets the.
	 *
	 * @return the wicket application
	 */
	public static WicketApplication get()
	{
		return (WicketApplication)Application.get();
	}

	/** The upload folder. */
	private Folder uploadFolder = null;

	/**
	 * Constructor.
	 */
	public WicketApplication()
	{


	}

	/**
	 * Gets the home page.
	 *
	 * @return the home page
	 * @see org.apache.wicket.Application#getHomePage()
	 */
	@Override
	public Class<HomePage> getHomePage()
	{
		return HomePage.class;
	}

	/**
	 * @return the folder for uploads
	 */
	public Folder getUploadFolder()
	{
		return this.uploadFolder;
	}

	@Override
	public void init()
	{
		// Create the upload folder...
		uploadFolder = new Folder(System.getProperty("java.io.tmpdir"), "wicket-uploads");
		// Ensure folder exists
		uploadFolder.mkdirs();
	}

}
