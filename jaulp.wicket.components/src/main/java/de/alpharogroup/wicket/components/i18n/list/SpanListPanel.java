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
package de.alpharogroup.wicket.components.i18n.list;

import java.util.List;

import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.IModel;

/**
 * The Class SpanListPanel that holds the ListView in a span. This is made by overwrite the
 * onComponentTag of the ListView and set the tagname to span.
 *
 * @param <T>
 *            the generic type of the list
 */
public abstract class SpanListPanel<T> extends DivListPanel<T>
{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new header content list panel.
	 *
	 * @param id
	 *            the id
	 * @param content
	 *            the model
	 */
	public SpanListPanel(final String id, final IModel<List<T>> content)
	{
		super(id, content);
	}

	/**
	 * Instantiates a new div list panel.
	 *
	 * @param id
	 *            the id
	 * @param list
	 *            the list
	 */
	public SpanListPanel(final String id, final List<T> list)
	{
		super(id, list);
	}


	/**
	 * New list view.
	 *
	 * @param id
	 *            the id
	 * @param model
	 *            the model
	 * @return the list view
	 */
	@Override
	protected ListView<T> newListView(final String id, final IModel<List<T>> model)
	{
		final ListView<T> listView = new ListView<T>(id, model)
		{
			/** The Constant serialVersionUID. */
			private static final long serialVersionUID = 1L;

			@Override
			protected void onComponentTag(final ComponentTag tag)
			{
				super.onComponentTag(tag);
				// Turn the div tag to a span
				tag.setName("span");
			}

			@Override
			protected void populateItem(final ListItem<T> item)
			{
				item.add(newListComponent("item", item));
			}

		};
		return listView;
	}

}
