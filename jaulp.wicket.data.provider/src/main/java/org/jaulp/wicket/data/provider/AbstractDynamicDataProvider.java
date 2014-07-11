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

import java.util.Iterator;
import java.util.List;

import org.apache.wicket.extensions.markup.html.repeater.util.SortParam;
import org.apache.wicket.extensions.markup.html.repeater.util.SortableDataProvider;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;

/**
 * The Class AbstractDynamicDataProvider.
 *
 * @param <T> the
 * @author Asterios Raptis
 */
public abstract class AbstractDynamicDataProvider<T, S> extends SortableDataProvider<T, S> {

	/**
	 * The serialVersionUID.
	 */
	private static final long serialVersionUID = 58836096610932614L;

	/**
	 * Instantiates a new abstract dynamic data provider.
	 */
	public AbstractDynamicDataProvider(){
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public Iterator<? extends T> iterator(long first, long count) {
		SortParam<S> sortParam = getSort();
		String property = (String)sortParam.getProperty();
		boolean ascending = sortParam.isAscending();
		SortCollectionUtils.sortList(getData(), property, ascending);
		long index = first + count;
		if(size() < index){
			index = size();
		}
		return getData().subList((int)first, (int)index).listIterator();
	}

	/**
	 * {@inheritDoc}
	 */
	public long size() {
		return getData().size();
	}

	/**
	 * Gets the data.
	 *
	 * @return the data
	 */
	public abstract List<T> getData();

    /**
     * {@inheritDoc}
     */
	@Override
    public IModel< T > model( final T object ) {
        return new CompoundPropertyModel<T>( object );
    }


}
