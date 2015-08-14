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
package de.alpharogroup.wicket.base.application.plugins;

import java.io.Serializable;

import org.apache.wicket.Application;
import org.apache.wicket.MetaDataKey;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.util.lang.Args;

import de.alpharogroup.wicket.base.util.application.ApplicationExtensions;

/**
 * If you install the {@link ApplicationDebugSettingsPlugin} you enable the appropriate settings for
 * the development mode.
 * 
 * <h2>Installation</h2>
 * 
 * You can install the {@link ApplicationDebugSettingsPlugin} is very simple. You only have to add a
 * one line to the {@code init} method:
 * 
 * <pre>
 * public class MyApplication extends WebApplication
 * {
 * 	&#064;Override
 * 	protected void init()
 * 	{
 * 		super.init();
 * 		new ApplicationDebugSettingsPlugin().install(this);
 * 	}
 * }
 * </pre>
 * 
 * @author Asterios Raptis
 */
public class ApplicationDebugSettingsPlugin implements Serializable
{

	/**
	 * The serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;

	/** The Constant DEBUG_SETTINGS_PLUGIN_KEY. */
	private static final MetaDataKey<ApplicationDebugSettingsPlugin> DEBUG_SETTINGS_PLUGIN_KEY = new MetaDataKey<ApplicationDebugSettingsPlugin>()
	{
		/**
		 * The serialVersionUID.
		 */
		private static final long serialVersionUID = 1L;
	};

	/**
	 * Returns the {@link ApplicationDebugSettingsPlugin} instance that has been installed in the
	 * current Wicket application. This is a convenience method that only works within a Wicket
	 * thread, and it assumes that {@link #install install()} has already been called.
	 *
	 * @return the {@link ApplicationDebugSettingsPlugin} instance that has been installed in the
	 *         current Wicket application.
	 * @throws IllegalStateException
	 *             is thrown if no Wicket application bound to the current thread, or if a
	 *             {@code DebugSettingsPlugin} has not been installed.
	 */
	public static ApplicationDebugSettingsPlugin get()
	{
		final Application app = Application.get();
		if (null == app)
		{
			throw new IllegalStateException("No wicket application is bound to the current thread.");
		}
		final ApplicationDebugSettingsPlugin plugin = app.getMetaData(DEBUG_SETTINGS_PLUGIN_KEY);
		if (null == plugin)
		{
			throw new IllegalStateException(
				"A DebugSettingsPlugin has not been installed in this Wicket "
					+ "application. You have to call DebugSettingsPlugin.install() in "
					+ "your application init().");
		}
		return plugin;
	}

	/**
	 * Install this plugin to the given {@link WebApplication}.
	 *
	 * @param application
	 *            the application to install.
	 */
	public ApplicationDebugSettingsPlugin install(final WebApplication application)
	{
		Args.notNull(application, "app");
		onConfigure(application);
		return this;
	}

	/**
	 * Factory method for that can be used to add additional configuration to this plugin.
	 * <p>
	 * Overrides should call {@code super.onConfigure()}.
	 *
	 * @param application
	 *            the application
	 */
	protected void onConfigure(final WebApplication application)
	{
		set(application, this);
		ApplicationExtensions.setDefaultDebugSettingsForDevelopment(application);
		// Adds the references from source code to the browser to reference in eclipse....
		// If you want to add WicketSource capabilities overwrite this method with a super call and
		// add the following...
		// WicketSource.configure(application);
	}

	/**
	 * Sets the specified {@link ApplicationDebugSettingsPlugin} in the application metadata.
	 *
	 * @param app
	 *            the app
	 * @param plugin
	 *            the plugin
	 */
	public void set(final Application app, final ApplicationDebugSettingsPlugin plugin)
	{
		app.setMetaData(DEBUG_SETTINGS_PLUGIN_KEY, plugin);
	}

}
