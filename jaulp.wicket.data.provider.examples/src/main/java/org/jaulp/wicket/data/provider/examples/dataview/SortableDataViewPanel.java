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
package org.jaulp.wicket.data.provider.examples.dataview;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.extensions.markup.html.repeater.data.sort.OrderByBorder;
import org.apache.wicket.extensions.markup.html.repeater.data.sort.SortOrder;
import org.apache.wicket.extensions.markup.html.repeater.data.table.NavigatorLabel;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.navigation.paging.PagingNavigator;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.model.CompoundPropertyModel;
import org.jaulp.wicket.data.provider.examples.data.provider.Person;
import org.jaulp.wicket.data.provider.examples.data.provider.SortablePersonDataProvider;

/**
 * @author admin
 */
public class SortableDataViewPanel extends Panel {

    public SortableDataViewPanel(String id) {
		super(id);
		List<Person> persons = new ArrayList<Person>();
		persons.add(new Person("Jamie", "Curtis", "11.12.1960"));
		persons.add(new Person("Toni", "Montana", "01.12.1950"));
		persons.add(new Person("Anton", "Pitt", "11.12.1960"));
		persons.add(new Person("Willy", "Lee", "01.12.1950"));
		persons.add(new Person("Bruce", "Willis", "11.12.1960"));
		persons.add(new Person("Henning", "Presley", "01.12.1950"));
		persons.add(new Person("Michael", "Jackson", "11.12.1960"));
		persons.add(new Person("Marco", "William", "01.12.1950"));
		persons.add(new Person("Gabriel", "Spears", "11.12.1960"));
		persons.add(new Person("Kurt", "Russell", "01.12.1950"));
		persons.add(new Person("Ralph", "Crow", "11.12.1960"));
		persons.add(new Person("Peter", "Reilly", "01.12.1950"));
		persons.add(new Person("Asterix", "Nulty", "11.12.1960"));
		persons.add(new Person("Obelix", "Bond", "01.12.1950"));
		persons.add(new Person("Miraculix", "James", "11.12.1960"));
		persons.add(new Person("Darth", "Schnyder", "01.12.1950"));
		persons.add(new Person("Angela", "De Niro", "11.12.1960"));
		persons.add(new Person("Brad", "Pacino", "01.12.1950"));

		SortablePersonDataProvider dataProvider = new SortablePersonDataProvider(persons);

		dataProvider.setSort("firstname", SortOrder.ASCENDING);

		final DataView< Person > dataView = new DataView< Person >(
                "dataView", dataProvider ) {
            /**
             * The serialVersionUID.
             */
            private static final long serialVersionUID = 1L;
			@Override
			protected void populateItem(Item<Person> item) {
				item.setDefaultModel( new CompoundPropertyModel< Person >(
                        item.getModel() ) );
                item.add( new Label( "firstname" ) );
                item.add( new Label( "lastname" ) );
                item.add( new Label( "dateOfBirth" ) );
			}
        };

        dataView.setItemsPerPage( 10 );
        add(dataView);

		add(new OrderByBorder("orderByFirstname", "firstname", dataProvider));
		add(new OrderByBorder("orderByLastname", "lastname", dataProvider));
		add(new OrderByBorder("orderByDateOfBirth", "dateOfBirth", dataProvider));
		add(new PagingNavigator("topNavigator", dataView));
add(new NavigatorLabel( "label", dataView )
);
		add(new PagingNavigator("footernavigator", dataView));
	}

	/**
     * The serialVersionUID.
     */
    private static final long serialVersionUID = 1L;
}

