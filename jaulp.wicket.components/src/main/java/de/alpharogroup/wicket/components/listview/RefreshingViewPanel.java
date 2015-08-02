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
import java.util.Iterator;
import java.util.List;

import lombok.Getter;

import org.apache.wicket.Component;
import org.apache.wicket.markup.html.panel.GenericPanel;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.RefreshingView;
import org.apache.wicket.markup.repeater.util.ModelIteratorAdapter;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.util.ListModel;
import org.apache.wicket.util.lang.Args;

/**
 * The Class RefreshingViewPanel takes a {@link org.apache.wicket.markup.repeater.RefreshingView} of
 * a generic type.
 *
 * @param <T>
 *            the generic type
 */
public abstract class RefreshingViewPanel<T extends Serializable> extends GenericPanel<List<T>>
{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The list view. */
	@Getter
	private final RefreshingView<T> refreshingView;

	/**
	 * Instantiates a new {@link de.alpharogroup.wicket.components.listview.RefreshingViewPanel}.
	 *
	 * @param id
	 *            the id
	 * @param model
	 *            the model
	 */
	public RefreshingViewPanel(final String id, final IModel<List<T>> model)
	{
		super(id, Args.notNull(model, "model"));
		add(refreshingView = newRefreshingView("refreshingView", model));
	}

	/**
	 * Instantiates a new {@link de.alpharogroup.wicket.components.listview.RefreshingViewPanel}.
	 *
	 * @param id
	 *            the id
	 * @param list
	 *            the list
	 */
	public RefreshingViewPanel(final String id, final List<T> list)
	{
		this(id, new ListModel<>(list));
	}

	protected Item<T> newItem(final String id, final int index, final IModel<T> model)
	{
		return new Item<T>(id, index, model);
	}

	/**
	 * New list component.
	 *
	 * @param id
	 *            the id
	 * @param item
	 *            the item
	 * @return the component
	 */
	protected abstract Component newListComponent(final String id, final Item<T> item);

	/**
	 * New list view.
	 *
	 * @param id
	 *            the id
	 * @param model
	 *            the model
	 * @return the list view
	 */
	protected RefreshingView<T> newRefreshingView(final String id, final IModel<List<T>> model)
	{
		final RefreshingView<T> listView = new RefreshingView<T>(id, model)
		{
			/** The Constant serialVersionUID. */
			private static final long serialVersionUID = 1L;

			@Override
			protected Iterator<IModel<T>> getItemModels()
			{
				return new ModelIteratorAdapter<T>(getModelObject().iterator())
				{
					@Override
					protected IModel<T> model(final T object)
					{
						return Model.of(object);
					}
				};
			}

			@Override
			protected Item<T> newItem(final String id, final int index, final IModel<T> model)
			{
				return RefreshingViewPanel.this.newItem(id, index, model);
			}

			@Override
			protected void populateItem(final Item<T> item)
			{
				item.add(newListComponent("item", item));
			}

		};
		return listView;
	}
}
