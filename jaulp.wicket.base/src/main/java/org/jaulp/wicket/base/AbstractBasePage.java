package org.jaulp.wicket.base;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.model.IModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.jaulp.wicket.base.util.WicketComponentUtils;

/**
 * The Class BasePage.
 */
public abstract class AbstractBasePage extends WebPage {

    /**
     * Instantiates a new base page with an IModel object.
     *
     * @param model the model
     */
    public AbstractBasePage(IModel<?> model) {
		super(model);
	}

	/**
	 * The serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;

	/**
     * Instantiates a new base page with no parameters.
     */
    public AbstractBasePage() {
    }

    /**
     * Instantiates a new base page with parameters.
     *
     * @param parameters the parameters
     */
    public AbstractBasePage( final PageParameters parameters ) {
        super( parameters );
    }

	/**
	 * Gets the request url.
	 *
	 * @return the request url
	 */
	public String getRequestURL() {
		return WicketComponentUtils.getRequestURL();
	}
	
	/**
	 * Sets the given parameter to the page.
	 *
	 * @param key
	 *            the key
	 * @param value
	 *            the value
	 */
	public void setParameter(final String key, final Object value) {
		getPageParameters().add(key, value);
	}

}