
/**
 * Copyright (C) 2007 Asterios Raptis
 *
 * This program is open source software; you can redistribute it and/or modify
 * it under the terms of the Apache License V2.0 as published by
 * the Apache Foundation.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY.
 */
package org.jaulp.wicket.base;

import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.model.IModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.jaulp.wicket.base.utils.WicketComponentUtils;

/**
 * The Class BasePage.
 */
public abstract class BasePage extends WebPage {

    /**
     * Instantiates a new base page with an IModel object.
     *
     * @param model the model
     */
    public BasePage(IModel<?> model) {
		super(model);
	}

	/**
	 * The serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;

	/**
     * Instantiates a new base page with no parameters.
     */
    public BasePage() {
    }

    /**
     * Instantiates a new base page with parameters.
     *
     * @param parameters the parameters
     */
    public BasePage( final PageParameters parameters ) {
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

    /**
     * {@inheritDoc}
     */
    public void renderHead(IHeaderResponse response) {
    	super.renderHead(response);    	
    	WicketComponentUtils.renderHeaderResponse(response, this.getClass());
    }

}