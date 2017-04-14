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
package de.alpharogroup.wicket.data.provider;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.wicket.extensions.markup.html.repeater.data.table.filter.IFilterStateLocator;

import lombok.Getter;
import lombok.Setter;

/**
 * The Class {@link AbstractSortFilterDataProvider}.
 *
 * @param <T>
 *            the generic type
 * @param <S>
 *            the generic type
 * @param <F>
 *            the generic type
 */
public class AbstractSortFilterDataProvider<T extends Serializable, S extends Serializable, F extends Serializable>
	extends
		AbstractSortableDataProvider<T, S>
	implements
		IFilterStateLocator<F>
{

	/**
	 * The serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;

	/** The filter. */
	@Getter
	@Setter
	private F filterState;

	/**
	 * Instantiates a new {@link AbstractSortFilterDataProvider}.
	 */
	public AbstractSortFilterDataProvider()
	{
		super();
	}

	/**
	 * Instantiates a new {@link AbstractSortFilterDataProvider}.
	 *
	 * @param data
	 *            the data
	 */
	public AbstractSortFilterDataProvider(final List<T> data)
	{
		super(data);
	}

	/**
	 * Filter the given list. Override this method to implement a filter.
	 *
	 * @param found
	 *            the found
	 * @return the list
	 */
	protected List<T> filter(final List<T> found)
	{
		return found;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Iterator<? extends T> iterator(final long first, final long count)
	{
		List<T> ret = new ArrayList<>(filter(sort()));
		if (ret.size() > (first + count))
		{
			ret = ret.subList((int)first, (int)first + (int)count);
		}
		else
		{
			ret = ret.subList((int)first, ret.size());
		}
		return ret.iterator();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public long size()
	{
		return filter(getData()).size();
	}

}
