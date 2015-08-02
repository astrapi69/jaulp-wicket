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
package de.alpharogroup.wicket.data.provider.examples.dataview;

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

import de.alpharogroup.wicket.data.provider.examples.data.provider.Person;
import de.alpharogroup.wicket.data.provider.examples.data.provider.PersonDatabaseManager;
import de.alpharogroup.wicket.data.provider.examples.data.provider.SortableFilterPersonDataProvider;

/**
 * The Class SortableDataViewPanel.
 *
 * @author admin
 */
public class SortableDataViewPanel extends Panel
{

	/**
	 * The serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new sortable data view panel.
	 *
	 * @param id
	 *            the id
	 */
	public SortableDataViewPanel(final String id)
	{
		super(id);
		final List<Person> persons = PersonDatabaseManager.getInstance().getPersons();

		final SortableFilterPersonDataProvider dataProvider = new SortableFilterPersonDataProvider(
			persons)
		{

			private static final long serialVersionUID = 1L;

			@Override
			public List<Person> getData()
			{
				return PersonDatabaseManager.getInstance().getPersons();
			}
		};

		dataProvider.setSort("firstname", SortOrder.ASCENDING);

		final DataView<Person> dataView = new DataView<Person>("dataView", dataProvider)
		{
			/**
			 * The serialVersionUID.
			 */
			private static final long serialVersionUID = 1L;

			@Override
			protected void populateItem(final Item<Person> item)
			{
				item.setDefaultModel(new CompoundPropertyModel<Person>(item.getModel()));
				item.add(new Label("firstname"));
				item.add(new Label("lastname"));
				item.add(new Label("dateOfBirth"));
			}
		};

		dataView.setItemsPerPage(10);
		add(dataView);

		add(new OrderByBorder<String>("orderByFirstname", "firstname", dataProvider));
		add(new OrderByBorder<String>("orderByLastname", "lastname", dataProvider));
		add(new OrderByBorder<String>("orderByDateOfBirth", "dateOfBirth", dataProvider));
		add(new NavigatorLabel("label", dataView));
		add(new PagingNavigator("topNavigator", dataView));
		add(new PagingNavigator("footernavigator", dataView));
	}
}
