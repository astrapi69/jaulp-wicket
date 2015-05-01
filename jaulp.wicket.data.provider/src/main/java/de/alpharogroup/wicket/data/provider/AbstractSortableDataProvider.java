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

import org.apache.wicket.extensions.markup.html.repeater.data.sort.ISortState;
import org.apache.wicket.extensions.markup.html.repeater.data.sort.SortOrder;
import org.apache.wicket.extensions.markup.html.repeater.data.table.ISortableDataProvider;
import org.apache.wicket.extensions.markup.html.repeater.util.SingleSortState;
import org.apache.wicket.extensions.markup.html.repeater.util.SortParam;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

/**
 * The Class AbstractSortableDataProvider.
 *
 * @param <T>
 *            the generic type of the Model for the DataProvider.
 * @param <S>
 *            the generic type for the SortState.
 * @author Asterios Raptis
 */
public class AbstractSortableDataProvider<T extends Serializable, S extends Serializable>
	implements
		ISortableDataProvider<T, S>
{

	/**
	 * The serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;

	/** The data for this DataProvider. */
	private List<T> data;

	/** The sort state. */
	private final SingleSortState<S> sortState = new SingleSortState<>();

	/**
	 * Default constructor.
	 */
	public AbstractSortableDataProvider()
	{
		this(new ArrayList<T>());
	}

	/**
	 * Instantiates a new abstract data provider.
	 *
	 * @param data
	 *            the data
	 */
	public AbstractSortableDataProvider(final List<T> data)
	{
		this.data = data;
	}

	/**
	 * Gets the sort state.
	 *
	 * @return the sort state
	 * @see ISortableDataProvider#getSortState()
	 */
	@Override
	public final ISortState<S> getSortState()
	{
		return sortState;
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
	 * {@inheritDoc}
	 */
	@Override
	public void detach()
	{
		this.data = null;
	}

	/**
	 * Gets the data.
	 *
	 * @return the data
	 */
	public List<T> getData()
	{
		return this.data;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Iterator<? extends T> iterator(final long first, final long count)
	{
		List<T> result = new ArrayList<>(sort());
		if (result.size() > first + count)
		{
			result = result.subList((int)first, (int)first + (int)count);
		}
		else
		{
			result = result.subList((int)first, result.size());
		}
		return result.iterator();
	}

	/**
	 * Sorts the given list by getting the {@link SortParam#getProperty()} and if not null the given
	 * list will be sort.
	 *
	 * @param unsortedList
	 *            the unsorted list
	 * @return the same list but sorted.
	 */
	protected List<T> sort(List<T> unsortedList)
	{
		SortParam<S> sortParam = getSort();
		if (sortParam != null)
		{
			String property = (String)sortParam.getProperty();
			boolean ascending = sortParam.isAscending();
			SortCollectionUtils.sortList(unsortedList, property, ascending);
		}
		return unsortedList;
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
	 * {@inheritDoc}
	 */
	@Override
	public IModel<T> model(final T object)
	{
		return Model.of(object);
	}

	/**
	 * Sets the data.
	 *
	 * @param data
	 *            the new data
	 */
	protected void setData(final List<T> data)
	{
		this.data = data;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public long size()
	{
		return getData().size();
	}

}
