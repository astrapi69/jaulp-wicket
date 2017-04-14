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
package de.alpharogroup.wicket.base.util;

import org.apache.wicket.core.request.handler.PageProvider;
import org.apache.wicket.request.component.IRequestablePage;
import org.apache.wicket.request.mapper.parameter.PageParameters;

/**
 * A factory for creating PageProvider objects.
 */
public class PageProviderFactory
{

	/**
	 * Factory method to create a new Page provider with the given page.
	 *
	 * @param pageClass
	 *            the page class
	 * @return the page provider
	 */
	public static PageProvider newPageProvider(final Class<? extends IRequestablePage> pageClass)
	{
		return newPageProvider(pageClass, null);
	}

	/**
	 * Factory method to create a new Page provider with the given page.
	 *
	 * @param pageClass
	 *            the page class
	 * @param pageParameters
	 *            the page parameters
	 * @return the page provider
	 */
	public static PageProvider newPageProvider(final Class<? extends IRequestablePage> pageClass,
		final PageParameters pageParameters)
	{
		return new PageProvider(pageClass, pageParameters);
	}

	/**
	 * Factory method to create a new Page provider with the given page.
	 *
	 * @param page
	 *            the page
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
