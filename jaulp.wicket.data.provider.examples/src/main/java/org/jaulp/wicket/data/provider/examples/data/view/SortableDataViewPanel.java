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
package org.jaulp.wicket.data.provider.examples.data.view;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.extensions.markup.html.repeater.data.sort.OrderByBorder;
import org.apache.wicket.extensions.markup.html.repeater.data.sort.SortOrder;
import org.apache.wicket.extensions.markup.html.repeater.data.table.NavigatorLabel;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.navigation.paging.PagingNavigator;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.model.CompoundPropertyModel;
import org.jaulp.wicket.data.provider.examples.data.provider.Person;
import org.jaulp.wicket.data.provider.examples.data.provider.SortablePersonDataProvider;

/**
 * The Class SortableDataViewPanel.
 *
 * @author admin
 */
public class SortableDataViewPanel extends Panel {

	/**
	 * Instantiates a new sortable data view panel.
	 *
	 * @param id the id
	 */
	public SortableDataViewPanel(String id) {
		super(id);
		List<Person> persons = getPersons();

		SortablePersonDataProvider dataProvider = new SortablePersonDataProvider(
				persons);

		dataProvider.setSort("firstname", SortOrder.ASCENDING);

		final DataView<Person> dataView = new DataView<Person>("dataView",
				dataProvider) {
			/**
			 * The serialVersionUID.
			 */
			private static final long serialVersionUID = 1L;

			@Override
			protected void populateItem(Item<Person> item) {
				item.setDefaultModel(new CompoundPropertyModel<Person>(item
						.getModel()));
				item.add(new Label("firstname"));
				item.add(new Label("lastname"));
				item.add(new Label("dateOfBirth"));
			}
		};

		dataView.setItemsPerPage(10);
		add(dataView);

		add(new OrderByBorder("orderByFirstname", "firstname", dataProvider));
		add(new OrderByBorder("orderByLastname", "lastname", dataProvider));
		add(new OrderByBorder("orderByDateOfBirth", "dateOfBirth", dataProvider));
		add(new NavigatorLabel("label", dataView));
		add(new PagingNavigator("topNavigator", dataView));
		add(new PagingNavigator("footernavigator", dataView));
	}


	/**
	 * Gets the persons.
	 *
	 * @return the persons
	 */
	private List<Person> getPersons() {
		List<Person> persons = new ArrayList<Person>();
		persons.add(new Person("Jamie", "Curtis", "11.12.1960"));
		persons.add(new Person("Toni", "Montana", "01.12.1950"));
		persons.add(new Person("Anton", "Pitt", "11.12.1960"));
		persons.add(new Person("Willy", "Lee", "01.12.1950"));
		persons.add(new Person("Bruce", "Willis", "11.12.1960"));
		persons.add(new Person("Henning", "Presley", "01.12.1950"));
		persons.add(new Person("Michael", "Jackson", "11.12.1960"));
		persons.add(new Person("Marco", "William", "01.12.1950"));
		persons.add(new Person("Gabriel", "Spears", "11.12.1960"));
		persons.add(new Person("Kurt", "Russell", "01.12.1950"));
		persons.add(new Person("Ralph", "Crow", "11.12.1960"));
		persons.add(new Person("Peter", "Reilly", "01.12.1950"));
		persons.add(new Person("Asterix", "Nulty", "11.12.1960"));
		persons.add(new Person("Obelix", "Bond", "01.12.1950"));
		persons.add(new Person("Miraculix", "James", "11.12.1960"));
		persons.add(new Person("Darth", "Schnyder", "01.12.1950"));
		persons.add(new Person("Angela", "De Niro", "11.12.1960"));
		persons.add(new Person("Brad", "Pacino", "01.12.1950"));
		return persons;
	}

	/**
	 * The serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;
}
