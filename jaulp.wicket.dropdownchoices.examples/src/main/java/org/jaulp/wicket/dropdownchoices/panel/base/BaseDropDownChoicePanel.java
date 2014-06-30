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

			public Object getObject(String id, IModel choices) {
				return null;
			}
        };

        DropDownChoice country = new DropDownChoice( "country", countries );
        add( country );
        country.setChoiceRenderer( renderer );

    }

}
