package org.dropdownchoices.panel;

import java.util.Arrays;

import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.markup.html.panel.Panel;
import org.jaulp.wicket.components.i18n.dropdownchoice.LocalisedDropDownChoice;
import org.jaulp.wicket.model.dropdownchoices.SelectOptionModel;

/**
 *
 */
public class WicketWikiExamplePanel extends Panel {

    public WicketWikiExamplePanel( final String id ) {
        super( id );
        SelectOptionModel [] options = new SelectOptionModel [] {
                new SelectOptionModel( "and", "AND" ), new SelectOptionModel( "|", "OR" ) };
        ChoiceRenderer choiceRenderer = new ChoiceRenderer( "value", "key" );
        add( new LocalisedDropDownChoice( "connective",
                Arrays.asList( options ), choiceRenderer ) );
    }

}
