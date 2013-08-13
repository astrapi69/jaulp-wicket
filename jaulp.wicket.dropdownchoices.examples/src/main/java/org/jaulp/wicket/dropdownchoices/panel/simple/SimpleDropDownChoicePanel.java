package org.jaulp.wicket.dropdownchoices.panel.simple;

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

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SimpleDropDownChoicePanel( final String id ) {
        super( id );
        IModel<List<String>> countries = new LoadableDetachableModel<List<String>>() {
            /**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
            public List<String> load() {
                List<String> l = new ArrayList<String>();
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
