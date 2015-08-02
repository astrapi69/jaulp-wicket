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
package de.alpharogroup.wicket.data.provider.examples.datatable;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.extensions.markup.html.repeater.data.table.DefaultDataTable;
import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.PropertyColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.filter.FilterForm;
import org.apache.wicket.extensions.markup.html.repeater.data.table.filter.FilterToolbar;
import org.apache.wicket.extensions.markup.html.repeater.data.table.filter.TextFilteredPropertyColumn;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;

import de.alpharogroup.wicket.data.provider.examples.data.provider.Person;
import de.alpharogroup.wicket.data.provider.examples.data.provider.PersonDatabaseManager;
import de.alpharogroup.wicket.data.provider.examples.data.provider.PersonFilter;
import de.alpharogroup.wicket.data.provider.examples.data.provider.SortableFilterPersonDataProvider;

public class DefaultDataTablePanel extends Panel
{

	/**
	 * The serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	public DefaultDataTablePanel(final String id)
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
		final List<IColumn<Person, String>> columns = new ArrayList<IColumn<Person, String>>();
		columns.add(new TextFilteredPropertyColumn<Person, PersonFilter, String>(Model
			.of("First name"), "firstname", "firstname"));
		columns.add(new TextFilteredPropertyColumn<Person, PersonFilter, String>(Model
			.of("Last Name"), "lastname", "lastname"));
		columns.add(new PropertyColumn<Person, String>(Model.of("Date of birth"), "dateOfBirth",
			"dateOfBirth"));

		final FilterForm<PersonFilter> form = new FilterForm<PersonFilter>("form", dataProvider);
		form.add(new TextField<>("firstname", PropertyModel.of(dataProvider,
			"filterState.firstname")));

		final DefaultDataTable<Person, String> dataTable = new DefaultDataTable<>("dataTable",
			columns, dataProvider, 10);
		dataTable.addTopToolbar(new FilterToolbar(dataTable, form, dataProvider));
		form.add(dataTable);

		add(form);
	}

}
