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

import org.apache.wicket.extensions.markup.html.repeater.data.sort.SortOrder;
import org.apache.wicket.extensions.markup.html.repeater.data.table.ISortableDataProvider;
import org.apache.wicket.extensions.markup.html.repeater.data.table.filter.IFilterStateLocator;
import org.apache.wicket.extensions.markup.html.repeater.util.SingleSortState;
import org.apache.wicket.extensions.markup.html.repeater.util.SortParam;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import de.alpharogroup.collections.list.ListExtensions;
import lombok.Getter;
import lombok.Setter;

/**
 * The Class {@link AbstractSortableFilterDataProvider} is an abstract generic implementation for
 * the ISortableDataProvider and the IFilterStateLocator interface.
 *
 * @author Asterios Raptis
 * @param <T>
 *            the generic type of the Model for the DataProvider.
 * @param <S>
 *            the generic type for the SortState.
 * @param <F>
 *            the generic type for the Filter.
 */
public abstract class AbstractSortableFilterDataProvider<T extends Serializable, S, F extends Serializable>
	implements
		ISortableDataProvider<T, S>,
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

	/** The data. */
	@Getter
	@Setter
	private List<T> data;

	/** The sort state. */
	@Getter
	private final SingleSortState<S> sortState;

	/**
	 * Instantiates a new {@link AbstractSortableFilterDataProvider}.
	 */
	public AbstractSortableFilterDataProvider()
	{
		this(new ArrayList<T>());
	}

	/**
	 * Instantiates a new {@link AbstractSortableFilterDataProvider}.
	 *
	 * @param data
	 *            the data
	 */
	public AbstractSortableFilterDataProvider(final List<T> data)
	{
		setData(data);
		this.sortState = newSortState();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void detach()
	{
		setData(null);
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
	 * Returns current sort state.
	 *
	 * @return current sort state
	 */
	public SortParam<S> getSort()
	{
		return sortState.getSort();
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
	public IModel<T> model(final T object)
	{
		return Model.of(object);
	}

	/**
	 * Factory method for creating the new {@link SingleSortState} for the sort state. This method
	 * is invoked in the constructor from the derived classes and can be overridden so users can
	 * provide their own version of a new {@link SingleSortState} for the sort state.
	 *
	 * @return the new {@link SingleSortState} for the sort state.
	 */
	protected SingleSortState<S> newSortState()
	{
		return new SingleSortState<>();

	}

	/**
	 * Sets the current sort state.
	 *
	 * @param property
	 *            sort property
	 * @param order
	 *            sort order
	 */
	public void setSort(final S property, final SortOrder order)
	{
		sortState.setPropertySortOrder(property, order);
	}

	/**
	 * Sets the current sort state.
	 *
	 * @param param
	 *            parameter containing new sorting information
	 */
	public void setSort(final SortParam<S> param)
	{
		sortState.setSort(param);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public long size()
	{
		return filter(getData()).size();
	}

	/**
	 * Sorts the given list by getting the {@link SortParam#getProperty()} and if not null the given
	 * list will be sort.
	 *
	 * @return the same list but sorted.
	 */
	protected List<T> sort()
	{
		return sort(getData());
	}

	/**
	 * Sorts the given list by getting the {@link SortParam#getProperty()} and if not null the given
	 * list will be sort.
	 *
	 * @param unsortedList
	 *            the unsorted list
	 * @return the same list but sorted.
	 */
	protected List<T> sort(final List<T> unsortedList)
	{
		final SortParam<S> sortParam = getSort();
		if (sortParam != null)
		{
			final String property = (String)sortParam.getProperty();
			final boolean ascending = sortParam.isAscending();
			ListExtensions.sortByProperty(unsortedList, property, ascending);
		}
		return unsortedList;
	}

}