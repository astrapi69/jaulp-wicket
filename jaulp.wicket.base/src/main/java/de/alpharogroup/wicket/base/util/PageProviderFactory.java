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
	public static PageProvider newPageProvider(final Class<? extends IRequestablePage> pageClass, final PageParameters pageParameters)
	{
		return new PageProvider(pageClass, pageParameters);
	}

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
	 * @param page
	 *            the page
	 * @param parameters
	 *            the page parameters
	 * @return the page provider
	 */
	public static PageProvider newPageProvider(final IRequestablePage page,
		final PageParameters parameters)
	{
		final PageProvider pageProvider = new PageProvider(page)
		{
			/**
			 * The serialVersionUID
			 */
			private static final long serialVersionUID = 1L;

			/**
			 * {@inheritDoc}
			 */
			@Override
			protected void setPageParameters(final PageParameters pageParameters)
			{
				super.setPageParameters(parameters);
			}
		};
		return pageProvider;
	}

}
