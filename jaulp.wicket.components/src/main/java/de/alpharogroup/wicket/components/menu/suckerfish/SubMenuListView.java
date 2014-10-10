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
package de.alpharogroup.wicket.components.menu.suckerfish;

import java.util.List;

import org.apache.wicket.MarkupContainer;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.IModel;

/**
 * The Class SubMenuListView.
 * 
 * @author Asterios Raptis
 */
public final class SubMenuListView extends ListView<MenuItem> {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 0L;
	private final MarkupContainer markupProvider;

	/**
	 * Instantiates a new sub menu list view.
	 * 
	 * @param id
	 *            the id
	 * @param model
	 *            the model
	 */
	public SubMenuListView(final String id,
			final IModel<List<MenuItem>> model,
			final MarkupContainer markupProvider) {
		super(id, model);
		this.markupProvider = markupProvider;
	}

	/**
	 * Instantiates a new sub menu list view.
	 * 
	 * @param id
	 *            the id
	 * @param list
	 *            the list
	 */
	public SubMenuListView(final String id, final List<MenuItem> list,
			final MarkupContainer markupProvider) {
		super(id, list);
		this.markupProvider = markupProvider;
	}

	/**
	 * {@inheritDoc}.
	 * 
	 * @param item
	 *            the item
	 * @see org.apache.wicket.markup.html.list.ListView#populateItem(org.apache.wicket.markup.html.list.ListItem)
	 */
	@Override
	protected void populateItem(final ListItem<MenuItem> item) {
		final MenuItem menuItem = item.getModelObject();
		item.add(new MenuItemFragment(menuItem, this.markupProvider));
	}
}