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
package org.jaulp.wicket.data.provider.examples.datatable;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import net.sourceforge.jaulp.date.DatePatterns;
import net.sourceforge.jaulp.date.ParseDateUtils;

import org.apache.wicket.extensions.markup.html.repeater.data.sort.SortOrder;
import org.apache.wicket.extensions.markup.html.repeater.data.table.DataTable;
import org.apache.wicket.extensions.markup.html.repeater.data.table.HeadersToolbar;
import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.NavigationToolbar;
import org.apache.wicket.extensions.markup.html.repeater.data.table.PropertyColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.filter.FilterForm;
import org.apache.wicket.extensions.markup.html.repeater.data.table.filter.FilterToolbar;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.jaulp.wicket.data.provider.examples.data.provider.Person;
import org.jaulp.wicket.data.provider.examples.data.provider.PersonFilter;
import org.jaulp.wicket.data.provider.examples.data.provider.SortableFilterPersonDataProvider;

public class DataTablePanel extends Panel
{

	/**
	 * The serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	public DataTablePanel(String id)
	{
		super(id);

		final SortableFilterPersonDataProvider dataProvider = new SortableFilterPersonDataProvider(
			getPersons())
		{
			private static final long serialVersionUID = 1L;

			@Override
			public List<Person> getData()
			{
				List<Person> persons = getPersons();
				setData(persons);
				return persons;
			}
		};
		dataProvider.setSort("firstname", SortOrder.ASCENDING);

		List<IColumn<Person, String>> columns = new ArrayList<>();

		columns.add(new PropertyColumn<Person, String>(Model.of("First name"), "firstname",
			"firstname"));
		columns.add(new PropertyColumn<Person, String>(Model.of("Last Name"), "lastname",
			"lastname")
		{
			private static final long serialVersionUID = 1L;

			@Override
			public String getCssClass()
			{
				return "last-name";
			}
		});
		columns.add(new PropertyColumn<Person, String>(Model.of("Date of birth"), "dateOfBirth",
			"dateOfBirth"));

		DataTable<Person, String> tableWithFilterForm = new DataTable<Person, String>(
			"tableWithFilterForm", columns, dataProvider, 10);
		tableWithFilterForm.setOutputMarkupId(true);

		FilterForm<PersonFilter> filterForm = new FilterForm<>("filterForm", dataProvider);
		filterForm.add(new TextField<>("dateFrom", PropertyModel.of(dataProvider,
			"filterState.dateFrom")));
		filterForm.add(new TextField<>("dateTo", PropertyModel.of(dataProvider,
			"filterState.dateTo")));
		add(filterForm);


//		FilterToolbar filterToolbar = new FilterToolbar(tableWithFilterForm, filterForm,
//			dataProvider);
//		tableWithFilterForm.addTopToolbar(filterToolbar);
		tableWithFilterForm.addTopToolbar(new NavigationToolbar(tableWithFilterForm));
		tableWithFilterForm.addTopToolbar(new HeadersToolbar<>(tableWithFilterForm, dataProvider));
		filterForm.add(tableWithFilterForm);
	}


	private List<Person> persons;

	/**
	 * Gets the persons.
	 * 
	 * @return the persons
	 */
	protected List<Person> getPersons()
	{
		if (persons == null)
		{
			persons = new ArrayList<Person>();
			try
			{
				persons.add(new Person("Jamie", "Curtis", ParseDateUtils.parseToDate("12.12.1960",
					DatePatterns.DOT_DD_MM_YYYY)));
				persons.add(new Person("Toni", "Montana", ParseDateUtils.parseToDate("02.12.1950",
					DatePatterns.DOT_DD_MM_YYYY)));
				persons.add(new Person("Anton", "Pitt", ParseDateUtils.parseToDate("13.12.1960",
					DatePatterns.DOT_DD_MM_YYYY)));
				persons.add(new Person("Willy", "Lee", ParseDateUtils.parseToDate("03.12.1950",
					DatePatterns.DOT_DD_MM_YYYY)));
				persons.add(new Person("Bruce", "Willis", ParseDateUtils.parseToDate("14.12.1960",
					DatePatterns.DOT_DD_MM_YYYY)));
				persons.add(new Person("Henning", "Presley", ParseDateUtils.parseToDate(
					"04.12.1950", DatePatterns.DOT_DD_MM_YYYY)));
				persons.add(new Person("Michael", "Jackson", ParseDateUtils.parseToDate(
					"15.12.1960", DatePatterns.DOT_DD_MM_YYYY)));
				persons.add(new Person("Marco", "William", ParseDateUtils.parseToDate("05.12.1950",
					DatePatterns.DOT_DD_MM_YYYY)));
				persons.add(new Person("Gabriel", "Spears", ParseDateUtils.parseToDate(
					"16.12.1960", DatePatterns.DOT_DD_MM_YYYY)));
				persons.add(new Person("Kurt", "Russell", ParseDateUtils.parseToDate("06.12.1950",
					DatePatterns.DOT_DD_MM_YYYY)));
				persons.add(new Person("Ralph", "Crow", ParseDateUtils.parseToDate("17.12.1960",
					DatePatterns.DOT_DD_MM_YYYY)));
				persons.add(new Person("Peter", "Reilly", ParseDateUtils.parseToDate("07.12.1950",
					DatePatterns.DOT_DD_MM_YYYY)));
				persons.add(new Person("Asterix", "Nulty", ParseDateUtils.parseToDate("08.12.1950",
					DatePatterns.DOT_DD_MM_YYYY)));
				persons.add(new Person("Obelix", "Bond", ParseDateUtils.parseToDate("18.12.1960",
					DatePatterns.DOT_DD_MM_YYYY)));
				persons.add(new Person("Miraculix", "James", ParseDateUtils.parseToDate(
					"09.12.1950", DatePatterns.DOT_DD_MM_YYYY)));
				persons.add(new Person("Darth", "Schnyder", ParseDateUtils.parseToDate(
					"19.12.1960", DatePatterns.DOT_DD_MM_YYYY)));
				persons.add(new Person("Angela", "De Niro", ParseDateUtils.parseToDate(
					"10.12.1950", DatePatterns.DOT_DD_MM_YYYY)));
				persons.add(new Person("Brad", "Pacino", ParseDateUtils.parseToDate("21.12.1960",
					DatePatterns.DOT_DD_MM_YYYY)));
			}
			catch (ParseException e)
			{
				e.printStackTrace();
			}
		}
		return persons;
	}

}
