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
package de.alpharogroup.wicket.data.provider.examples.datatable;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.extensions.markup.html.repeater.data.grid.ICellPopulator;
import org.apache.wicket.extensions.markup.html.repeater.data.sort.SortOrder;
import org.apache.wicket.extensions.markup.html.repeater.data.table.AbstractColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.DataTable;
import org.apache.wicket.extensions.markup.html.repeater.data.table.HeadersToolbar;
import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.NavigationToolbar;
import org.apache.wicket.extensions.markup.html.repeater.data.table.PropertyColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.filter.FilterForm;
import org.apache.wicket.extensions.markup.html.repeater.data.table.filter.FilterToolbar;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;

import de.alpharogroup.wicket.base.util.resource.ResourceModelFactory;
import de.alpharogroup.wicket.components.actions.ActionPanel;
import de.alpharogroup.wicket.data.provider.examples.data.provider.Person;
import de.alpharogroup.wicket.data.provider.examples.data.provider.PersonDatabaseManager;
import de.alpharogroup.wicket.data.provider.examples.data.provider.PersonFilter;
import de.alpharogroup.wicket.data.provider.examples.data.provider.SortableFilterPersonDataProvider;

public class DataTablePanel extends Panel
{

	/**
	 * The serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	public DataTablePanel(final String id)
	{
		super(id);

		final SortableFilterPersonDataProvider dataProvider = new SortableFilterPersonDataProvider(
			PersonDatabaseManager.getInstance().getPersons())
		{
			private static final long serialVersionUID = 1L;

			@Override
			public List<Person> getData()
			{
				final List<Person> persons = PersonDatabaseManager.getInstance().getPersons();
				setData(persons);
				return persons;
			}
		};
		dataProvider.setSort("firstname", SortOrder.ASCENDING);

		final List<IColumn<Person, String>> columns = new ArrayList<>();

		columns.add(new AbstractColumn<Person, String>(new Model<String>("Actions"))
		{
			/**
			 * The serialVersionUID
			 */
			private static final long serialVersionUID = 1L;

			/**
			 * {@inheritDoc}
			 */
			@Override
			public void populateItem(final Item<ICellPopulator<Person>> cellItem,
				final String componentId, final IModel<Person> model)
			{
				final ActionPanel<Person> editActionPanel = new ActionPanel<Person>(componentId,
					model)
				{

					/**
					 * The serialVersionUID
					 */
					private static final long serialVersionUID = 1L;

					/**
					 * {@inheritDoc}
					 */
					@Override
					protected IModel<String> newActionLinkLabelModel()
					{
						return ResourceModelFactory
							.newResourceModel("global.main.button.edit.label");
					}

					/**
					 * {@inheritDoc}
					 */
					@Override
					protected void onAction(final AjaxRequestTarget target)
					{
						DataTablePanel.this.onEdit(target);
					}


				};
				cellItem.add(editActionPanel);
			}
		});

		columns.add(new PropertyColumn<Person, String>(Model.of("First name"), "firstname",
			"firstname"));
		columns.add(new PropertyColumn<Person, String>(Model.of("Last Name"), "lastname",
			"lastname")
		{
			private static final long serialVersionUID = 1L;

			@Override
			public String getCssClass()
			{
				return "last-name";
			}
		});
		columns.add(new PropertyColumn<Person, String>(Model.of("Date of birth"), "dateOfBirth",
			"dateOfBirth"));

		final DataTable<Person, String> tableWithFilterForm = new DataTable<Person, String>(
			"tableWithFilterForm", columns, dataProvider, 10);
		tableWithFilterForm.setOutputMarkupId(true);

		final FilterForm<PersonFilter> filterForm = new FilterForm<>("filterForm", dataProvider);
		filterForm.add(new TextField<>("dateFrom", PropertyModel.of(dataProvider,
			"filterState.dateFrom")));
		filterForm.add(new TextField<>("dateTo", PropertyModel.of(dataProvider,
			"filterState.dateTo")));
		add(filterForm);


		final FilterToolbar filterToolbar = new FilterToolbar(tableWithFilterForm, filterForm,
			dataProvider);
		tableWithFilterForm.addTopToolbar(filterToolbar);
		tableWithFilterForm.addTopToolbar(new NavigationToolbar(tableWithFilterForm));
		tableWithFilterForm.addTopToolbar(new HeadersToolbar<>(tableWithFilterForm, dataProvider));
		filterForm.add(tableWithFilterForm);
	}

	protected void onEdit(final AjaxRequestTarget target)
	{
		System.out.println("Edit ...");
	}

}
