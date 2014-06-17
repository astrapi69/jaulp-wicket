package org.jaulp.wicket.base;

import org.apache.wicket.markup.html.GenericWebPage;
import org.apache.wicket.model.IModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.jaulp.wicket.base.util.WicketComponentUtils;

/**
 * The Class AbstractGenericBasePage.
 */
public abstract class AbstractGenericBasePage<T> extends GenericWebPage<T> {

    /**
     * Instantiates a new base page with an IModel object.
     *
     * @param model the model
     */
    public AbstractGenericBasePage(IModel<T> model) {
		super(model);
        if(model != null) {
        	setModel(model);
        }
	}

	/**
	 * The serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;

	/**
     * Instantiates a new base page with no parameters.
     */
    public AbstractGenericBasePage() {
    }

    /**
     * Instantiates a new base page with parameters.
     *
     * @param parameters the parameters
     */
    public AbstractGenericBasePage( final PageParameters parameters ) {
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