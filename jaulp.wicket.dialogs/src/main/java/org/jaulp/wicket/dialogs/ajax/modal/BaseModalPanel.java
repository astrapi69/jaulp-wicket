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

import java.util.EventObject;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.OnChangeAjaxBehavior;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.event.Broadcast;
import org.apache.wicket.event.IEventSink;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;

/**
 * The Class BaseModalPanel.
 *
 * @param <T> the generic type
 */
public abstract class BaseModalPanel< T > extends Panel {
    /**
     * The serialVersionUID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Instantiates a new base modal panel.
     *
     * @param id the id
     * @param model the model
     */
    public BaseModalPanel( final String id,
            final CompoundPropertyModel< T > model ) {
        super( id );

        final Form< T > form = new Form< T >( "form", model );
        form.setOutputMarkupId( true );
        form.clearInput();
        add( form );

        final TextArea< String > note = new TextArea< String >(
                "messageContent" );

        note.clearInput();
        // IT IS VERY IMPORTANT TO SET THE OUTPUTMARKUPID TO TRUE...
        note.setOutputMarkupId( true );
        note.add( new OnChangeAjaxBehavior() {
            /**
             * The serialVersionUID.
             */
            private static final long serialVersionUID = 1L;

            @Override
            public void onUpdate( final AjaxRequestTarget target ) {
                // Do nothing...
            }
        } );
        form.add( note );

        final AjaxButton close = new AjaxButton( "cancelButton" ) {
            /**
             * The serialVersionUID.
             */
            private static final long serialVersionUID = 1L;

            @Override
            public void onSubmit( final AjaxRequestTarget target,
                    final Form< ? > form ) {
                target.add( note );
                onCancel( target );

            }

			@SuppressWarnings("unused")
			public <E extends EventObject>void send(final IEventSink sink, final Broadcast broadcast, final E payload) {
				// TODO Auto-generated method stub

			}

			@Override
			protected void onError(AjaxRequestTarget target, Form<?> form) {
				// TODO Auto-generated method stub

			}
        };
        form.add( close );

        final AjaxButton selectionButton = new AjaxButton( "okButton" ) {
            /**
             * The serialVersionUID.
             */
            private static final long serialVersionUID = 1L;

            @Override
            protected void onSubmit( final AjaxRequestTarget target,
                    final Form< ? > form ) {
                final T obj = model.getObject();
                onSelect( target, obj );
            }

			@SuppressWarnings("unused")
			public <E extends EventObject> void send(IEventSink sink, Broadcast broadcast, E payload) {
				// TODO Auto-generated method stub

			}

			@Override
			protected void onError(AjaxRequestTarget target, Form<?> form) {
				// TODO Auto-generated method stub

			}
        };
        form.add( selectionButton );

    }

    /**
     * On cancel.
     *
     * @param target the target
     */
    abstract void onCancel( AjaxRequestTarget target );

    /**
     * On select.
     *
     * @param target the target
     * @param object the object
     */
    abstract void onSelect( AjaxRequestTarget target, T object );

}
