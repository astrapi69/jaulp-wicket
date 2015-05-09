package de.alpharogroup.wicket.components.examples.application;

import org.apache.wicket.application.ReloadingClassLoader;
import org.apache.wicket.protocol.http.ReloadingWicketFilter;

public class MyReloadingFilter extends ReloadingWicketFilter
{
	static
	{
		ReloadingClassLoader.includePattern("de.alpharogroup.wicket.components.examples.home.*");
		ReloadingClassLoader
			.includePattern("de.alpharogroup.wicket.components.examples.application.*");
	}
}
