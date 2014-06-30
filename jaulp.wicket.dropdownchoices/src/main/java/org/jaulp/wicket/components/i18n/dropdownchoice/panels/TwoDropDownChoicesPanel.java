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
package org.jaulp.wicket.components.i18n.dropdownchoice.panels;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormComponentUpdatingBehavior;
import org.apache.wicket.markup.html.form.IChoiceRenderer;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.PropertyModel;
import org.jaulp.wicket.components.i18n.dropdownchoice.LocalisedDropDownChoice;
import org.jaulp.wicket.model.dropdownchoices.StringTwoDropDownChoicesModel;

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
