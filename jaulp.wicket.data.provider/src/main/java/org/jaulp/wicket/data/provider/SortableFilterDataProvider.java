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
package org.jaulp.wicket.data.provider;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.wicket.extensions.markup.html.repeater.data.sort.ISortState;
import org.apache.wicket.extensions.markup.html.repeater.data.sort.SortOrder;
import org.apache.wicket.extensions.markup.html.repeater.data.table.ISortableDataProvider;
import org.apache.wicket.extensions.markup.html.repeater.data.table.filter.IFilterStateLocator;
import org.apache.wicket.extensions.markup.html.repeater.util.SingleSortState;
import org.apache.wicket.extensions.markup.html.repeater.util.SortParam;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

/**
 * The Class AbstractDataProvider is an abstract generic implementation for the
 * ISortableDataProvider and the IFilterStateLocator interface.
 *
 * @author Asterios Raptis
 * @param <M> the generic type for the Model.
 * @param <S> the generic type for the SortState. 
 * @param <F> the generic type for the Filter.
 */
public abstract class SortableFilterDataProvider< T extends Serializable, S, F extends Serializable >
        implements ISortableDataProvider< T, S >, IFilterStateLocator< F > {

    /**
     * The serialVersionUID.
     */
    private static final long serialVersionUID = 1L;

    /** The filter. */
    private F filterState;

    /** The data. */
    private List< T > data;

    /** The sort state. */
	private final SingleSortState<S> state = new SingleSortState<S>();

    /**
     * Default constructor.
     */
    public SortableFilterDataProvider() {
        this(new ArrayList<T>());
    }

    /**
     * Instantiates a new abstract data provider.
     *
     * @param data the data
     */
    public SortableFilterDataProvider( final List< T > data ) {
    	setData(data);
    }

	/**
	 * Gets the sort state.
	 *
	 * @return the sort state
	 * @see ISortableDataProvider#getSortState()
	 */
	public final ISortState<S> getSortState()
	{
		return state;
	}

	/**
	 * Returns current sort state.
	 *
	 * @return current sort state
	 */
	public SortParam<S> getSort()
	{
		return state.getSort();
	}

	/**
	 * Sets the current sort state.
	 *
	 * @param param            parameter containing new sorting information
	 */
	public void setSort(final SortParam<S> param)
	{
		state.setSort(param);
	}

	/**
	 * Sets the current sort state.
	 *
	 * @param property            sort property
	 * @param order            sort order
	 */
	public void setSort(final S property, final SortOrder order)
	{
		state.setPropertySortOrder(property, order);
	}
    
    /**
     * Filter the given list. Override this method to implement a filter.
     *
     * @param found the found
     * @return the list
     */
    protected List<T> filter(List<T> found) {
    	return found;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void detach() {
    	setData(null);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public F getFilterState() {
        return this.filterState;
    }

    /**
     * Gets the data.
     *
     * @return the data
     */
    public List< T > getData() {
        return this.data;
    }

    /**
     * {@inheritDoc}
     */
    public Iterator< ? extends T > iterator( final long first, final long count ) {
        List< T > ret = new ArrayList<T>(filter(sort()));
        if ( ret.size() > ( first + count ) ) {
            ret = ret.subList( (int)first, (int)first + (int)count );
        } else {
        	ret = ret.subList((int)first, ret.size());
        }
        return ret.iterator();
    }

	/**
	 * Sorts the given list by getting the {@link SortParam#getProperty()} and
	 * if not null the given list will be sort.
	 *
	 * @param unsortedList
	 *            the unsorted list
	 * @return the same list but sorted.
	 */
	protected List< T > sort(List<T> unsortedList) {
		SortParam<S> sortParam = getSort();
		if(sortParam != null) {
			String property = (String)sortParam.getProperty();
			boolean ascending = sortParam.isAscending();
			SortCollectionUtils.sortList(unsortedList, property, ascending);			
		}
		return unsortedList;
	}

	/**
	 * Sorts the given list by getting the {@link SortParam#getProperty()} and
	 * if not null the given list will be sort.
	 * @return the same list but sorted.
	 */
	protected List< T > sort() {
		return sort(getData());
	}

    /**
     * {@inheritDoc}
     */
    @Override
    public IModel< T > model( final T object ) {
        return Model.of( object );
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setFilterState( final F filterState ) {
        this.filterState = filterState;

    }

    /**
     * Sets the data.
     *
     * @param data the new data
     */
    protected void setData( final List< T > data ) {
        this.data = data;
    }

    /**
     * {@inheritDoc}
     */
    public long size() {
        return filter(getData()).size();
    }

}