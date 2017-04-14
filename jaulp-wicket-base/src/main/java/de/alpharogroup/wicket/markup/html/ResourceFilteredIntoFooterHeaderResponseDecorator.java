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
package de.alpharogroup.wicket.markup.html;

import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.filter.JavaScriptFilteredIntoFooterHeaderResponse;
import org.apache.wicket.markup.html.IHeaderResponseDecorator;

/**
 * This class is a decorator for resources that shell be rendered in the footer section.
 */
public class ResourceFilteredIntoFooterHeaderResponseDecorator implements IHeaderResponseDecorator
{

	/**
	 * The constant for the default filter name.
	 */
	private static final String DEFAULT_FILTER_NAME = "footer-container";

	/**
	 * The filter name.
	 */
	private String filterName;

	/**
	 * The default constructor with the default filter name.
	 */
	public ResourceFilteredIntoFooterHeaderResponseDecorator()
	{
		this(DEFAULT_FILTER_NAME);
	}

	/**
	 * The constructor with a filter name as parameter.
	 * 
	 * @param filterName
	 *            The filter name to set.
	 */
	public ResourceFilteredIntoFooterHeaderResponseDecorator(final String filterName)
	{
		this.filterName = filterName;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public IHeaderResponse decorate(final IHeaderResponse response)
	{
		return new JavaScriptFilteredIntoFooterHeaderResponse(response, this.filterName);
	}

}