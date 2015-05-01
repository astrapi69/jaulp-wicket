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

import de.alpharogroup.locale.ResourceBundleKey;

import org.apache.wicket.model.IModel;

/**
 * The Class ResourceBundleKeysPanel takes a list of content resource keys that should be in a
 * resource bundle for i18n.
 */
public abstract class ResourceBundleKeysPanel extends DivListPanel<ResourceBundleKey>
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
	public ResourceBundleKeysPanel(String id, List<ResourceBundleKey> list)
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
	public ResourceBundleKeysPanel(String id, IModel<List<ResourceBundleKey>> content)
	{
		super(id, content);
	}

}