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
package de.alpharogroup.wicket.components.termofuse.rightsandduties;

import net.sourceforge.jaulp.locale.ResourceBundleKey;

import org.apache.wicket.Component;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.jaulp.wicket.base.util.resource.ResourceModelFactory;
import org.jaulp.wicket.behaviors.AddJsQueryBehavior;

import de.alpharogroup.wicket.components.i18n.list.HeaderContentListPanel;
import de.alpharogroup.wicket.components.i18n.list.ResourceBundleKeysPanel;
import de.alpharogroup.wicket.components.i18n.list.UnorderedListPanel;


public class RightsAndDutiesPanel extends Panel
{

	private static final long serialVersionUID = 1L;

	public RightsAndDutiesPanel(String id)
	{
		this(id, null);
	}

	public RightsAndDutiesPanel(String id, IModel<RightsAndDutiesModel> model)
	{
		super(id, model);

		add(new HeaderContentListPanel("introduction", Model.of(model.getObject()
			.getIntroductionModel()))
		{

			private static final long serialVersionUID = 1L;

			@Override
			protected Component newListComponent(String id, ListItem<ResourceBundleKey> item)
			{
				return new Label(id, newContentResourceModel(item.getModel()))
					.add(new AddJsQueryBehavior("wrap", "<p></p>"));
			}

			@Override
			protected Component newHeaderLabel(String id, IModel<String> model)
			{
				return super.newHeaderLabel(id, model).add(
					new AddJsQueryBehavior("wrap", "<h2></h2>"));
			}
		});

		add(new UnorderedListPanel("list", model.getObject().getListModel()
			.getContentResourceKeys())
		{

			private static final long serialVersionUID = 1L;

			@Override
			protected Component newListComponent(String id, ListItem<ResourceBundleKey> item)
			{
				return new Label(id, ResourceModelFactory.newResourceModel(item.getModel()
					.getObject(), this));
			}
		});

		add(new ResourceBundleKeysPanel("summary", model.getObject().getSummaryModel()
			.getContentResourceKeys())
		{

			private static final long serialVersionUID = 1L;

			@Override
			protected Component newListComponent(String id, ListItem<ResourceBundleKey> item)
			{
				return new Label(id, ResourceModelFactory.newResourceModel(item.getModel()
					.getObject(), this)).add(new AddJsQueryBehavior("wrap", "<p></p>"));
			}
		});

	}

}
