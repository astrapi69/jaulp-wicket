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
package org.jaulp.wicket.data.provider.examples.refreshingview;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import net.sourceforge.jaulp.date.DatePatterns;
import net.sourceforge.jaulp.date.ParseDateUtils;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.SubmitLink;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.OddEvenItem;
import org.apache.wicket.markup.repeater.RefreshingView;
import org.apache.wicket.markup.repeater.ReuseIfModelsEqualStrategy;
import org.apache.wicket.markup.repeater.util.ModelIteratorAdapter;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.jaulp.wicket.data.provider.examples.data.provider.Person;

public class RefreshingViewPanel extends Panel
{
	/**
	 * The serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;
	final Form<?> form;
	private Person selected;

	public RefreshingViewPanel(String id, IModel<?> model)
	{
		super(id, model);

		form = new Form<Person>("form");
		add(form);

		// create a repeater that will display the list of contacts.
		RefreshingView<Person> refreshingView = new RefreshingView<Person>("simple")
		{
			/**
			 * The serialVersionUID.
			 */
			private static final long serialVersionUID = 1L;

			@Override
			protected Iterator<IModel<Person>> getItemModels()
			{
				// for simplicity we only show the first 10 contacts
				// SortParam<String> sort = new SortParam<String>("firstname",
				// true);
				Iterator<Person> contacts = getPersons().iterator();

				// the iterator returns contact objects, but we need it to
				// return models, we use this handy adapter class to perform
				// on-the-fly conversion.
				return new ModelIteratorAdapter<Person>(contacts)
				{
					@Override
					protected IModel<Person> model(Person object)
					{
						return new CompoundPropertyModel<Person>(object);
					}
				};
			}

			@Override
			protected void populateItem(final Item<Person> item)
			{
				// populate the row of the repeater
				IModel<Person> contact = item.getModel();
				item.add(new ActionPanel("actions", contact));
				item.add(new TextField<String>("firstname"));
				item.add(new TextField<String>("lastname"));
			}

			@Override
			protected Item<Person> newItem(String id, int index, IModel<Person> model)
			{
				// this item sets markup class attribute to either 'odd' or
				// 'even' for decoration
				return new OddEvenItem<Person>(id, index, model);
			}
		};

		// because we are in a form we need to preserve state of the component
		// hierarchy (because it might contain things like form errors that
		// would be lost if the hierarchy for each item was recreated every
		// request by default), so we use an item reuse strategy.
		refreshingView.setItemReuseStrategy(ReuseIfModelsEqualStrategy.getInstance());

		form.add(refreshingView);
	}

	/**
	 * Panel that houses row-actions
	 */
	private class ActionPanel extends Panel
	{
		/**
		 * The serialVersionUID.
		 */
		private static final long serialVersionUID = 1L;

		/**
		 * @param id
		 *            component id
		 * @param model
		 *            model for contact
		 */
		public ActionPanel(String id, IModel<Person> model)
		{
			super(id, model);
			add(new ModalDialogWithStylePanel("modalDialog"));
			add(new Link<Object>("select")
			{
				/**
				 * The serialVersionUID.
				 */
				private static final long serialVersionUID = 1L;

				@Override
				public void onClick()
				{
					setSelected((Person)ActionPanel.this.getDefaultModelObject());
				}
			});

			SubmitLink removeLink = new SubmitLink("remove", form)
			{
				/**
				 * The serialVersionUID.
				 */
				private static final long serialVersionUID = 1L;

				@Override
				public void onSubmit()
				{
					Person contact = (Person)ActionPanel.this.getDefaultModelObject();
					info("Removed Person " + contact);
					// DatabaseLocator.getDatabase().delete(contact);
				}
			};
			removeLink.setDefaultFormProcessing(false);
			add(removeLink);
		}
	}

	/**
	 * @return selected contact
	 */
	public Person getSelected()
	{
		return selected;
	}

	/**
	 * sets selected Person
	 * 
	 * @param selected
	 */
	public void setSelected(Person selected)
	{
		addStateChange();
		this.selected = selected;
	}

	private List<Person> persons;

	/**
	 * Gets the persons.
	 * 
	 * @return the persons
	 */
	protected List<Person> getPersons()
	{
		if (persons == null)
		{
			persons = new ArrayList<Person>();
			try
			{
				persons.add(new Person("Jamie", "Curtis", ParseDateUtils.parseToDate("12.12.1960",
					DatePatterns.DOT_DD_MM_YYYY)));
				persons.add(new Person("Toni", "Montana", ParseDateUtils.parseToDate("02.12.1950",
					DatePatterns.DOT_DD_MM_YYYY)));
				persons.add(new Person("Anton", "Pitt", ParseDateUtils.parseToDate("13.12.1960",
					DatePatterns.DOT_DD_MM_YYYY)));
				persons.add(new Person("Willy", "Lee", ParseDateUtils.parseToDate("03.12.1950",
					DatePatterns.DOT_DD_MM_YYYY)));
				persons.add(new Person("Bruce", "Willis", ParseDateUtils.parseToDate("14.12.1960",
					DatePatterns.DOT_DD_MM_YYYY)));
				persons.add(new Person("Henning", "Presley", ParseDateUtils.parseToDate(
					"04.12.1950", DatePatterns.DOT_DD_MM_YYYY)));
				persons.add(new Person("Michael", "Jackson", ParseDateUtils.parseToDate(
					"15.12.1960", DatePatterns.DOT_DD_MM_YYYY)));
				persons.add(new Person("Marco", "William", ParseDateUtils.parseToDate("05.12.1950",
					DatePatterns.DOT_DD_MM_YYYY)));
				persons.add(new Person("Gabriel", "Spears", ParseDateUtils.parseToDate(
					"16.12.1960", DatePatterns.DOT_DD_MM_YYYY)));
				persons.add(new Person("Kurt", "Russell", ParseDateUtils.parseToDate("06.12.1950",
					DatePatterns.DOT_DD_MM_YYYY)));
				persons.add(new Person("Ralph", "Crow", ParseDateUtils.parseToDate("17.12.1960",
					DatePatterns.DOT_DD_MM_YYYY)));
				persons.add(new Person("Peter", "Reilly", ParseDateUtils.parseToDate("07.12.1950",
					DatePatterns.DOT_DD_MM_YYYY)));
				persons.add(new Person("Asterix", "Nulty", ParseDateUtils.parseToDate("08.12.1950",
					DatePatterns.DOT_DD_MM_YYYY)));
				persons.add(new Person("Obelix", "Bond", ParseDateUtils.parseToDate("18.12.1960",
					DatePatterns.DOT_DD_MM_YYYY)));
				persons.add(new Person("Miraculix", "James", ParseDateUtils.parseToDate(
					"09.12.1950", DatePatterns.DOT_DD_MM_YYYY)));
				persons.add(new Person("Darth", "Schnyder", ParseDateUtils.parseToDate(
					"19.12.1960", DatePatterns.DOT_DD_MM_YYYY)));
				persons.add(new Person("Angela", "De Niro", ParseDateUtils.parseToDate(
					"10.12.1950", DatePatterns.DOT_DD_MM_YYYY)));
				persons.add(new Person("Brad", "Pacino", ParseDateUtils.parseToDate("21.12.1960",
					DatePatterns.DOT_DD_MM_YYYY)));
			}
			catch (ParseException e)
			{
				e.printStackTrace();
			}
		}
		return persons;
	}

}
