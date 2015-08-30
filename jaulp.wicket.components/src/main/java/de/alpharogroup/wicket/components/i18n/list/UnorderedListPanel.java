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

import org.apache.wicket.Component;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.IModel;

import de.alpharogroup.locale.ResourceBundleKey;
import de.alpharogroup.wicket.base.util.resource.ResourceModelFactory;
import de.alpharogroup.wicket.components.listview.ListViewPanel;

/**
 * The Class UnorderedListPanel takes a {@link ListView} of {@link ResourceBundleKey}s content
 * resource keys that should be in a resource bundle for i18n.
 */
public abstract class UnorderedListPanel extends ListViewPanel<ResourceBundleKey>
{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new {@link UnorderedListPanel}.
	 *
	 * @param id
	 *            the id
	 * @param model
	 *            the model
	 */
	public UnorderedListPanel(final String id, final IModel<List<ResourceBundleKey>> model)
	{
		super(id, model);
	}

	/**
	 * Instantiates a new {@link UnorderedListPanel}.
	 *
	 * @param id
	 *            the id
	 * @param list
	 *            the list
	 */
	public UnorderedListPanel(final String id, final List<ResourceBundleKey> list)
	{
		super(id, list);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected Component newListComponent(final String id, final ListItem<ResourceBundleKey> item)
	{
		return new Label(id, ResourceModelFactory.newResourceModel(item.getModel().getObject(),
			this));
	}

}