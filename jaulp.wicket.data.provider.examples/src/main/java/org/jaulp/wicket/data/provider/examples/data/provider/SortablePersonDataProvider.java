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
package org.jaulp.wicket.data.provider.examples.data.provider;

import java.util.List;

import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.jaulp.wicket.data.provider.AbstractSortableDataProvider;

/**
 * The Class SortablePersonDataProvider.
 */
public class SortablePersonDataProvider extends
		AbstractSortableDataProvider<Person, String> {

	/**
	 * The serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new sortable person data provider.
	 *
	 * @param data the data
	 */
	public SortablePersonDataProvider(List<Person> data) {
		super(data);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public IModel<Person> model(Person person) {
		return new CompoundPropertyModel<Person>(person);
	}

}
