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
package org.dropdownchoices.panels;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormComponentUpdatingBehavior;
import org.apache.wicket.markup.html.form.IChoiceRenderer;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.PropertyModel;
import org.dropdownchoices.models.StringTwoDropDownChoicesModel;
import org.jaulp.wicket.components.i18n.dropdownchoice.LocalisedDropDownChoice;

/**
 * The Class TwoDropDownChoicesPanel.
 *
 * @author Asterios Raptis
 */
public abstract class TwoDropDownChoicesPanel extends Panel {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;

    private final LocalisedDropDownChoice< String > rootChoice;

    private final LocalisedDropDownChoice< String > childChoice;

	/**
     * Instantiates a new two drop down choices panel.
     *
     * @param id the id
     */
    public TwoDropDownChoicesPanel( final String id,
            final StringTwoDropDownChoicesModel stringTwoDropDownChoicesModel,
            IChoiceRenderer< String > rootRenderer,
            IChoiceRenderer< String > childRenderer  ) {
        super( id );

        rootChoice = new LocalisedDropDownChoice< String >(
                "rootChoice", new PropertyModel< String >(
                        stringTwoDropDownChoicesModel, "selectedRootOption" ),
                stringTwoDropDownChoicesModel.getRootChoices(),
                rootRenderer );

        childChoice = new LocalisedDropDownChoice< String >(
                "childChoice", new PropertyModel< String >(
                        stringTwoDropDownChoicesModel, "selectedChildOption" ),
                stringTwoDropDownChoicesModel.getChildChoices(),
                childRenderer );

        childChoice.setOutputMarkupId( true );

        add( rootChoice );
        add( childChoice );

        rootChoice.add( new AjaxFormComponentUpdatingBehavior( "onchange" ) {

            /** The Constant serialVersionUID. */
            private static final long serialVersionUID = 1L;

            @Override
            protected void onUpdate( final AjaxRequestTarget target ) {
                target.add( childChoice );
            }
        } );
    }

	public LocalisedDropDownChoice<String> getChildChoice() {
		return childChoice;
	}

    public LocalisedDropDownChoice<String> getRootChoice() {
		return rootChoice;
	}
}
