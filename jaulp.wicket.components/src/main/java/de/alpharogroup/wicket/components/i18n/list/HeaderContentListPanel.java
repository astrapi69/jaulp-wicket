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

import lombok.Getter;

import org.apache.wicket.Component;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import de.alpharogroup.locale.ResourceBundleKey;
import de.alpharogroup.wicket.base.util.resource.ResourceModelFactory;
import de.alpharogroup.wicket.components.factory.ComponentFactory;

/**
 * The Class HeaderContentListPanel takes a header resource key and a list of content resource keys
 * that should be in a resource bundle for i18n.
 */
public abstract class HeaderContentListPanel extends Panel
{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The header. */
	@Getter
	private final Component header;

	/** The list panel. */
	@Getter
	private final Component listPanel;

	/**
	 * Instantiates a new header content list panel.
	 *
	 * @param id
	 *            the id
	 */
	public HeaderContentListPanel(final String id)
	{
		this(id, null);
	}

	/**
	 * Instantiates a new header content list panel.
	 *
	 * @param id
	 *            the id
	 * @param model
	 *            the model
	 */
	public HeaderContentListPanel(final String id, final IModel<HeaderContentListModelBean> model)
	{
		super(id, model);
		add(header = newHeaderLabel("header", newHeaderModel(model.getObject()
			.getHeaderResourceKey())));
		add(listPanel = newListPanel(
			"listPanel",
			Model.of(ContentListModelBean.builder()
				.contentResourceKeys(model.getObject().getContentResourceKeys()).build())));
	}

	/**
	 * New content resource model.
	 *
	 * @param resourceKey
	 *            the resource key
	 * @return the i model
	 */
	protected IModel<String> newContentResourceModel(final IModel<ResourceBundleKey> resourceKey)
	{
		return ResourceModelFactory.newResourceModel(resourceKey.getObject(), this);

	}

	/**
	 * New header label.
	 *
	 * @param id
	 *            the id
	 * @param model
	 *            the model
	 * @return the component
	 */
	protected Component newHeaderLabel(final String id, final IModel<String> model)
	{
		return ComponentFactory.newLabel(id, model);
	}

	/**
	 * New header model.
	 *
	 * @param resourceKey
	 *            the resource key
	 * @return the i model
	 */
	protected IModel<String> newHeaderModel(final ResourceBundleKey resourceKey)
	{
		return ResourceModelFactory.newResourceModel(resourceKey, this);
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
	protected abstract Component newListComponent(final String id,
		final ListItem<ResourceBundleKey> item);

	protected Component newListPanel(final String id, final IModel<ContentListModelBean> model)
	{
		return new ResourceBundleKeysPanel(id, model.getObject().getContentResourceKeys())
		{

			private static final long serialVersionUID = 1L;

			@Override
			protected Component newListComponent(final String id,
				final ListItem<ResourceBundleKey> item)
			{
				return HeaderContentListPanel.this.newListComponent(id, item);
			}
		};
	}

}
