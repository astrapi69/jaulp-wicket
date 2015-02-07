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

import org.apache.wicket.model.IModel;

import de.alpharogroup.wicket.components.listview.ListViewPanel;

/**
 * The Class DivListPanel.
 * 
 * @param <T>
 *            the generic type of the list
 */
public abstract class DivListPanel<T> extends ListViewPanel<T>
{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new div list panel.
	 *
	 * @param id
	 *            the id
	 * @param list
	 *            the list
	 */
	public DivListPanel(String id, List<T> list)
	{
		super(id, list);
	}

	/**
	 * Instantiates a new header content list panel.
	 *
	 * @param id
	 *            the id
	 * @param content
	 *            the model
	 */
	public DivListPanel(String id, IModel<List<T>> content)
	{
		super(id, content);
	}

}