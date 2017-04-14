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
import org.apache.wicket.request.cycle.AbstractRequestCycleListener;
import org.apache.wicket.request.cycle.RequestCycle;
import org.apache.wicket.request.http.WebResponse;
import org.apache.wicket.util.lang.Args;

import de.alpharogroup.wicket.base.util.WicketComponentExtensions;

/**
 * If you install the {@link SecuritySettingsPlugin} you enable the appropriate settings for the
 * development mode.
 *
 * <h2>Installation</h2>
 *
 * You can install the {@link SecuritySettingsPlugin} is very simple. You only have to add a one
 * line to the {@code init} method:
 *
 * <pre>
 * public class MyApplication extends WebApplication
 * {
 * 	&#064;Override
 * 	protected void init()
 * 	{
 * 		super.init();
 * 		new SecuritySettingsPlugin().install(this);
 * 	}
 * }
 * </pre>
 *
 * @author Asterios Raptis
 */
public class SecuritySettingsPlugin implements Serializable
{

	/**
	 * The serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;

	/** The Constant DEBUG_SETTINGS_PLUGIN_KEY. */
	private static final MetaDataKey<SecuritySettingsPlugin> SECURITY_SETTINGS_PLUGIN_KEY = new MetaDataKey<SecuritySettingsPlugin>()
	{
		/**
		 * The serialVersionUID.
		 */
		private static final long serialVersionUID = 1L;
	};

	/**
	 * Returns the {@link SecuritySettingsPlugin} instance that has been installed in the current
	 * Wicket application. This is a convenience method that only works within a Wicket thread, and
	 * it assumes that {@link #install install()} has already been called.
	 *
	 * @return the {@link SecuritySettingsPlugin} instance that has been installed in the current
	 *         Wicket application.
	 * @throws IllegalStateException
	 *             is thrown if no Wicket application bound to the current thread, or if a
	 *             {@code DebugSettingsPlugin} has not been installed.
	 */
	public static SecuritySettingsPlugin get()
	{
		final Application app = Application.get();
		if (null == app)
		{
			throw new IllegalStateException(
				"No wicket application is bound to the current thread.");
		}
		final SecuritySettingsPlugin plugin = app.getMetaData(SECURITY_SETTINGS_PLUGIN_KEY);
		if (null == plugin)
		{
			final String pluginClassName = SecuritySettingsPlugin.class.getSimpleName();
			throw new IllegalStateException("A " + pluginClassName
				+ " has not been installed in this Wicket application. You have to call "
				+ pluginClassName + ".install() in " + "your application init().");
		}
		return plugin;
	}

	/**
	 * Install this plugin to the given {@link WebApplication}.
	 *
	 * @param application
	 *            the application to install.
	 * @return this for chaining.
	 */
	public SecuritySettingsPlugin install(final WebApplication application)
	{
		Args.notNull(application, "app");
		onConfigure(application);
		return this;
	}

	/**
	 * Factory method for that can be used to add additional security configuration to this plugin.
	 * <p>
	 * Overrides should call {@code super.onConfigure()}.
	 *
	 * @param application
	 *            the application
	 */
	protected void onConfigure(final WebApplication application)
	{
		set(application, this);
		application.getRequestCycleListeners().add(new AbstractRequestCycleListener()
		{
			@Override
			public void onBeginRequest(final RequestCycle cycle)
			{
				super.onBeginRequest(cycle);
				final WebResponse response = (WebResponse)cycle.getResponse();
				// Category: Framing
				WicketComponentExtensions.setSecurityFramingHeaders(response);
				// Category: Transport
				WicketComponentExtensions.setSecurityTransportHeaders(response);
				// Category: XSS
				WicketComponentExtensions.setSecurityXSSHeaders(response);
				// Category: Caching
				WicketComponentExtensions.setSecurityCachingHeaders(response);
			}
		});

	}

	/**
	 * Sets the specified {@link SecuritySettingsPlugin} in the application metadata.
	 *
	 * @param app
	 *            the app
	 * @param plugin
	 *            the plugin
	 */
	public void set(final Application app, final SecuritySettingsPlugin plugin)
	{
		app.setMetaData(SECURITY_SETTINGS_PLUGIN_KEY, plugin);
	}

}
