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
package org.jaulp.wicket.dialogs.ajax.modal;

import org.apache.wicket.Component;
import org.apache.wicket.extensions.ajax.markup.html.modal.ModalWindow;

/**
 * The Class AbstractModalWindow.
 *
 */
public class AbstractModalWindow extends ModalWindow {

	/**
     * The serialVersionUID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Instantiates a new base modal window.
     *
     * @param id the id
     * @param title the title
     * @param initialWidth the initial width
     * @param initialHeight the initial height
     * @param model the model
     */
    public AbstractModalWindow( final String id, final String title,
            final int initialWidth, final int initialHeight,
            final Component component ) {
        super( id );
        setInitialWidth( initialWidth );
        setInitialHeight( initialHeight );
        setTitle( title );
        setContent( component );
    }

}
