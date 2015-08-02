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

import java.util.List;

import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;

import de.alpharogroup.wicket.data.provider.AbstractSortableDataProvider;

/**
 * The Class SortablePersonDataProvider.
 */
public class SortablePersonDataProvider extends AbstractSortableDataProvider<Person, String>
{

	/**
	 * The serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new sortable person data provider.
	 *
	 * @param data
	 *            the data
	 */
	public SortablePersonDataProvider(final List<Person> data)
	{
		super(data);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public IModel<Person> model(final Person person)
	{
		return new CompoundPropertyModel<Person>(person);
	}

}
