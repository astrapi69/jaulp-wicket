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

import java.util.List;

import lombok.Getter;

import org.apache.wicket.Component;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.util.ListModel;
import org.apache.wicket.util.lang.Args;

import de.alpharogroup.wicket.base.BasePanel;

/**
 * The Class ListViewPanel takes a {@link ListView} of a generic type.
 *
 * @param <T>
 *            the generic type of model object
 */
public abstract class ListViewPanel<T> extends BasePanel<List<T>>
{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The list view. */
	@Getter
	private final ListView<T> listView;

	/**
	 * Instantiates a new {@link ListViewPanel}.
	 *
	 * @param id
	 *            the id
	 * @param model
	 *            the model
	 */
	public ListViewPanel(final String id, final IModel<List<T>> model)
	{
		super(id, Args.notNull(model, "model"));
		add(listView = newListView("listView", model));
	}

	/**
	 * Instantiates a new {@link ListViewPanel}.
	 *
	 * @param id
	 *            the id
	 * @param list
	 *            the list
	 */
	public ListViewPanel(final String id, final List<T> list)
	{
		this(id, new ListModel<>(list));
	}

	/**
	 * Abstract factory method for creating the new {@link Component} in the list. This method is
	 * invoked in the {@link ListView#populateItem(ListItem)} from the derived classes and can be
	 * overridden so users can provide their own version of a new {@link Component} in the list.
	 *
	 * @param id
	 *            the id
	 * @param item
	 *            the item
	 * @return the new {@link Component} in the list.
	 */
	protected abstract Component newListComponent(final String id, final ListItem<T> item);

	/**
	 * Factory method for creating a new {@link ListView}. This method is invoked in the constructor
	 * from the derived classes and can be overridden so users can provide their own version of the
	 * new {@link ListView}.
	 *
	 * @param id
	 *            the id
	 * @param model
	 *            the model
	 * @return the new {@link ListView}.
	 */
	protected ListView<T> newListView(final String id, final IModel<List<T>> model)
	{
		final ListView<T> listView = new ListView<T>(id, model)
		{
			/** The Constant serialVersionUID. */
			private static final long serialVersionUID = 1L;

			/**
			 * {@inheritDoc}
			 */
			@Override
			protected void populateItem(final ListItem<T> item)
			{
				item.add(newListComponent("item", item));
			}
		};
		listView.setReuseItems(true);
		return listView;
	}

}
