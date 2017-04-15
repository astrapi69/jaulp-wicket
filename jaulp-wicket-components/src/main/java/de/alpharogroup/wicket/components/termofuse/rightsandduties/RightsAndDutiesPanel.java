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

import org.apache.wicket.Component;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import de.alpharogroup.resourcebundle.locale.ResourceBundleKey;
import de.alpharogroup.wicket.base.util.resource.ResourceModelFactory;
import de.alpharogroup.wicket.behaviors.JQueryJsAppenderBehavior;
import de.alpharogroup.wicket.components.factory.ComponentFactory;
import de.alpharogroup.wicket.components.i18n.list.HeaderContentListPanel;
import de.alpharogroup.wicket.components.i18n.list.ResourceBundleKeysPanel;
import de.alpharogroup.wicket.components.i18n.list.UnorderedListPanel;

/**
 * The Class {@link RightsAndDutiesPanel}.
 */
public class RightsAndDutiesPanel extends Panel
{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new {@link RightsAndDutiesPanel}.
	 *
	 * @param id
	 *            the id
	 */
	public RightsAndDutiesPanel(final String id)
	{
		this(id, null);
	}

	/**
	 * Instantiates a new {@link RightsAndDutiesPanel}.
	 *
	 * @param id
	 *            the id
	 * @param model
	 *            the model
	 */
	public RightsAndDutiesPanel(final String id, final IModel<RightsAndDutiesModelBean> model)
	{
		super(id, model);

		add(new HeaderContentListPanel("introduction",
			Model.of(model.getObject().getIntroductionModel()))
		{

			/** The Constant serialVersionUID. */
			private static final long serialVersionUID = 1L;

			/**
			 * {@inheritDoc}
			 */
			@Override
			protected Component newHeaderLabel(final String id, final IModel<String> model)
			{
				return super.newHeaderLabel(id, model)
					.add(new JQueryJsAppenderBehavior("wrap", "<h2></h2>"));
			}

			/**
			 * {@inheritDoc}
			 */
			@Override
			protected Component newListComponent(final String id,
				final ListItem<ResourceBundleKey> item)
			{
				return ComponentFactory.newLabel(id, newContentResourceModel(item.getModel()))
					.add(new JQueryJsAppenderBehavior("wrap", "<p></p>"));
			}
		});

		add(new UnorderedListPanel("list",
			model.getObject().getListModel().getContentResourceKeys())
		{

			/** The Constant serialVersionUID. */
			private static final long serialVersionUID = 1L;

			/**
			 * {@inheritDoc}
			 */
			@Override
			protected Component newListComponent(final String id,
				final ListItem<ResourceBundleKey> item)
			{
				return ComponentFactory.newLabel(id,
					ResourceModelFactory.newResourceModel(item.getModel().getObject(), this));
			}
		});

		add(new ResourceBundleKeysPanel("summary",
			model.getObject().getSummaryModel().getContentResourceKeys())
		{

			/** The Constant serialVersionUID. */
			private static final long serialVersionUID = 1L;

			/**
			 * {@inheritDoc}
			 */
			@Override
			protected Component newListComponent(final String id,
				final ListItem<ResourceBundleKey> item)
			{
				return ComponentFactory
					.newLabel(id,
						ResourceModelFactory.newResourceModel(item.getModel().getObject(), this))
					.add(new JQueryJsAppenderBehavior("wrap", "<p></p>"));
			}
		});

	}

}
