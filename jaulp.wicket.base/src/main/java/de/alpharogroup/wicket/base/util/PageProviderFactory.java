package de.alpharogroup.wicket.base.util;

import org.apache.wicket.core.request.handler.PageProvider;
import org.apache.wicket.request.component.IRequestablePage;
import org.apache.wicket.request.mapper.parameter.PageParameters;

/**
 * A factory for creating PageProvider objects.
 */
public class PageProviderFactory {

	/**
	 * Factory method to create a new Page provider with the given page.
	 *
	 * @param page            the page
	 * @return the page provider
	 */
	public static PageProvider newPageProvider(final IRequestablePage page) 
	{
		return new PageProvider(page);
	}
	
	/**
	 * Factory method to create a new Page provider with the given page.
	 *
	 * @param pageClass the page class
	 * @return the page provider
	 */
	public static PageProvider newPageProvider(final Class<? extends IRequestablePage> pageClass) 
	{
		return newPageProvider(pageClass, null);
	}
	
	/**
	 * Factory method to create a new Page provider with the given page.
	 *
	 * @param pageClass the page class
	 * @param pageParameters the page parameters
	 * @return the page provider
	 */
	public static PageProvider newPageProvider(final Class<? extends IRequestablePage> pageClass, PageParameters pageParameters) 
	{
		return new PageProvider(pageClass, pageParameters);
	}

}
