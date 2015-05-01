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
package de.alpharogroup.wicket.components.termofuse.modificationsclause;

import de.alpharogroup.locale.ResourceBundleKey;

import org.apache.wicket.Component;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.model.IModel;
import org.jaulp.wicket.behaviors.AddJsQueryBehavior;

import de.alpharogroup.wicket.components.i18n.list.HeaderContentListModel;
import de.alpharogroup.wicket.components.i18n.list.HeaderContentListPanel;

public class ModificationsClausePanel extends HeaderContentListPanel
{

	private static final long serialVersionUID = 1L;

	public ModificationsClausePanel(String id)
	{
		this(id, null);

	}

	public ModificationsClausePanel(String id, IModel<HeaderContentListModel> model)
	{
		super(id, model);
	}

	@Override
	protected Component newListComponent(String id, ListItem<ResourceBundleKey> item)
	{
		return new Label(id, newContentResourceModel(item.getModel())).add(new AddJsQueryBehavior(
			"wrap", "<p></p>"));
	}

	@Override
	protected Component newHeaderLabel(String id, IModel<String> model)
	{
		return super.newHeaderLabel(id, model).add(new AddJsQueryBehavior("wrap", "<h2></h2>"));
	}

}
