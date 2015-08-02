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
package de.alpharogroup.wicket.components.listview;

import java.io.Serializable;
import java.util.List;

import lombok.Getter;

import org.apache.wicket.Component;
import org.apache.wicket.markup.html.panel.GenericPanel;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.markup.repeater.data.IDataProvider;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.util.ListModel;
import org.apache.wicket.util.lang.Args;

/**
 * The Class DataViewPanel takes a {@link org.apache.wicket.markup.repeater.data.DataView} of a
 * generic type.
 *
 * @param <T>
 *            the generic type
 */
public abstract class DataViewPanel<T extends Serializable> extends GenericPanel<List<T>>
{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The data view. */
	@Getter
	private final DataView<T> dataView;

	/**
	 * instance block for set the markup id, with other words make this component ajaxifiable.
	 **/
	{
		setOutputMarkupId(true);
		setOutputMarkupPlaceholderTag(true);
	}

	/**
	 * Instantiates a new {@link de.alpharogroup.wicket.components.listview.DataViewPanel}.
	 *
	 * @param id
	 *            the id
	 * @param list
	 *            the list
	 */
	public DataViewPanel(String id, List<T> list)
	{
		this(id, new ListModel<>(list));
	}

	/**
	 * Instantiates a new {@link de.alpharogroup.wicket.components.listview.DataViewPanel}.
	 *
	 * @param id
	 *            the id
	 * @param model
	 *            the model
	 */
	public DataViewPanel(String id, final IModel<List<T>> model)
	{
		super(id, Args.notNull(model, "model"));
		add(dataView = newDataView("dataView", newDataProvider(model)));
	}

	/**
	 * New DataView.
	 *
	 * @param id
	 *            the id
	 * @param dataProvider
	 *            the data provider
	 * @return the DataView
	 */
	protected DataView<T> newDataView(final String id, final IDataProvider<T> dataProvider)
	{
		DataView<T> dataView = new DataView<T>(id, dataProvider)
		{
			/** The Constant serialVersionUID. */
			private static final long serialVersionUID = 1L;

			/**
			 * {@inheritDoc}
			 */
			@Override
			protected void populateItem(Item<T> item)
			{
				item.add(newListComponent("item", item));
			}

		};
		dataView.setItemsPerPage(newItemsPerPage());
		return dataView;
	}

	/**
	 * New items per page.
	 *
	 * @return the long
	 */
	protected long newItemsPerPage()
	{
		return 10;
	}

	/**
	 * Abstract factory method that creates a new list component.
	 *
	 * @param id
	 *            the id
	 * @param item
	 *            the item
	 * @return the new list component.
	 */
	protected abstract Component newListComponent(String id, Item<T> item);

	/**
	 * Abstract factory method that creates a new {@link IDataProvider}. This method is invoked in
	 * the constructor from the derived classes and must be implemented so users can provide their
	 * own version of a {@link IDataProvider}.
	 *
	 * @param model
	 *            the model
	 * @return the new {@link IDataProvider}.
	 */
	protected abstract IDataProvider<T> newDataProvider(final IModel<List<T>> model);

}