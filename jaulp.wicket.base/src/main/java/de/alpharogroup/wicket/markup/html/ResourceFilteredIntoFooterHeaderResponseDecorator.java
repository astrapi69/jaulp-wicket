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
	 * The filter name.
	 */
	private String filterName;

	/**
	 * The constant for the default filter name.
	 */
	private static final String DEFAULT_FILTER_NAME = "footer-container";

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