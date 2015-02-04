package org.jaulp.wicket.behaviors;

import org.apache.wicket.markup.html.WebPage;

/**
 * The Class GoogleAnalyticsBehavior adds the application specific js script.
 */
public class GoogleAnalyticsBehavior extends AddJsResourceReferenceBehavior
{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new google analytics behavior.
	 *
	 * @param pageClass
	 *            the page class
	 */
	public GoogleAnalyticsBehavior(final Class<? extends WebPage> pageClass)
	{
		super(pageClass, "gaq.js", "gaq");
	}

}