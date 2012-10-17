/**
 * Copyright (C) 2007 Asterios Raptis
 *
 * This program is open source software; you can redistribute it and/or modify
 * it under the terms of the Apache License V2.0 as published by
 * the Apache Foundation.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY.
 */
package org.jaulp.wicket.data.provider;

import java.util.Iterator;
import java.util.List;

import org.apache.wicket.extensions.markup.html.repeater.util.SortParam;
import org.apache.wicket.extensions.markup.html.repeater.util.SortableDataProvider;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;

/**
 * The Class AbstractSortableDataProvider.
 *
 * @param <T> the
 * @author Asterios Raptis
 */
public class AbstractSortableDataProvider<T, S> extends SortableDataProvider<T, S> {

	/**
	 * The serialVersionUID.
	 */
	private static final long serialVersionUID = -1172477145578508268L;

	/** The data. */
	private List<T> data;

	/**
	 * Instantiates a new abstract sortable data provider.
	 *
	 * @param data the data
	 */
	public AbstractSortableDataProvider(List<T> data){
		this.data = data;
	}
	
	/**
	 * Instantiates a new abstract sortable data provider.
	 *
	 * @param data the data
	 */
	public AbstractSortableDataProvider(){
		super();
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
	public List<T> getData() {
		return data;
	}

    /**
     * {@inheritDoc}
     */
	@Override
    public IModel< T > model( final T object ) {
        return new CompoundPropertyModel<T>( object );
    }


	/**
	 * {@inheritDoc}
	 */
	@Override
	public Iterator<? extends T> iterator(long first, long count) {
		SortParam sortParam = getSort();
		SortCollectionUtils.sortList(getData(), (String)sortParam.getProperty(), sortParam.isAscending());
		long index = first + count;
		if(size() < index){
			index = size();
		}
		return getData().subList((int)first, (int)index).listIterator();
	}


}
