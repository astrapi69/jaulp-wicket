package org.dropdownchoices.panel.simple;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;

/**
 * @author Asterios Raptis
 */
public class SimpleDropDownChoicePanel extends Panel {

    public SimpleDropDownChoicePanel( final String id ) {
        super( id );
        IModel countries = new LoadableDetachableModel() {
            @Override
            public Object load() {
                List l = new ArrayList();
                l.add( "Argentina" );
                l.add( "Brazil" );
                l.add( "Chile" );
                return l;
            }
        };

        DropDownChoice country = new DropDownChoice( "country", countries );
        add( country );
    }

}
