package org.jaulp.wicket.dropdownchoices.panel.base;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.IChoiceRenderer;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.jaulp.wicket.dropdownchoices.models.Country;


/**
 * @author Asterios Raptis
 */
public class BaseDropDownChoicePanel extends Panel {

    public BaseDropDownChoicePanel( final String id ) {
        super( id );
        IModel countries = new LoadableDetachableModel() {
            @Override
            public Object load() {
                List l = new ArrayList();
                l.add( new Country( "ar", "Argentina" ) );
                l.add( new Country( "br", "Brazil" ) );
                l.add( new Country( "cl", "Chile" ) );
                return l;
            }
        };
        IChoiceRenderer renderer = new IChoiceRenderer() {
            public Object getDisplayValue( final Object obj ) {
                Country c = ( Country ) obj;
                return c.getName();
            }

            public String getIdValue( final Object obj, final int index ) {
                Country c = ( Country ) obj;
                return c.getDigraph();
            }
        };

        DropDownChoice country = new DropDownChoice( "country", countries );
        add( country );
        country.setChoiceRenderer( renderer );

    }

}
