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
package de.alpharogroup.wicket.data.provider.examples.data.provider;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.wicket.extensions.markup.html.repeater.data.sort.SortOrder;

import de.alpharogroup.wicket.data.provider.AbstractSortFilterDataProvider;

public class SortableFilterPersonDataProvider
	extends
		AbstractSortFilterDataProvider<Person, String, PersonFilter>
{

	/**
	 * The serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	public SortableFilterPersonDataProvider()
	{
		this(new ArrayList<Person>());
	}

	public SortableFilterPersonDataProvider(final List<Person> data)
	{
		super(data);
		setFilterState(new PersonFilter());
		setSort("firstname", SortOrder.ASCENDING);
	}

	@Override
	protected List<Person> filter(final List<Person> personsFound)
	{
		return filterByFirstname(personsFound);
	}

	protected List<Person> filterByDateOfBirth(final List<Person> personsFound)
	{
		final ArrayList<Person> result = new ArrayList<>();
		final Date dateFrom = getFilterState().getDateFrom();
		final Date dateTo = getFilterState().getDateTo();
		for (final Person person : personsFound)
		{
			final Date bornDate = person.getDateOfBirth();
			if (dateFrom != null && bornDate.before(dateFrom))
			{
				continue;
			}
			if (dateTo != null && bornDate.after(dateTo))
			{
				continue;
			}
			result.add(person);
		}
		return result;
	}

	protected List<Person> filterByFirstname(final List<Person> personsFound)
	{
		final ArrayList<Person> result = new ArrayList<>();
		final String filter = getFilterState().getFirstname();
		if (filter != null)
		{
			for (final Person person : personsFound)
			{
				if (person != null && person.getFirstname() != null
					&& person.getFirstname().matches(filter + "(.*)"))
				{
					result.add(person);
				}
			}
			return result;
		}
		return personsFound;
	}

}