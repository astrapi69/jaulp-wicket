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
package de.alpharogroup.wicket.components.footer;

import java.util.List;

import org.apache.wicket.Component;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.util.ListModel;

import de.alpharogroup.wicket.base.BasePanel;
import de.alpharogroup.wicket.components.link.LinkItem;
import lombok.Getter;

/**
 * The Class FooterMenuPanel have a listview with the footer menu.
 */
public abstract class FooterMenuPanel extends BasePanel<List<LinkItem>>
{

	/**
	 * The serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * the link list panel
	 */
	@Getter
	private Component linkListPanel;

	/**
	 * Instantiates a new {@link FooterMenuPanel}.
	 *
	 * @param id
	 *            the component id
	 * @param model
	 *            the model
	 */
	public FooterMenuPanel(final String id, final IModel<List<LinkItem>> model)
	{
		super(id, model);
		add(linkListPanel = newLinkListPanel("linkListPanel", model));
	}

	/**
	 * Instantiates a new {@link FooterMenuPanel}.
	 *
	 * @param id
	 *            the component id
	 * @param list
	 *            the list with the menu items
	 */
	public FooterMenuPanel(final String id, final List<LinkItem> list)
	{
		this(id, new ListModel<>(list));
	}

	/**
	 * Factory method for creating a new Component for the menu items. This method is invoked in the
	 * constructor from the derived classes and have to be overridden so users can provide their own
	 * version of a new Component for the menu items.
	 *
	 * @param id
	 *            the id
	 * @param model
	 *            the model
	 * @return the new RequiredTextField
	 */
	protected abstract Component newLinkListPanel(final String id,
		final IModel<List<LinkItem>> model);

}
