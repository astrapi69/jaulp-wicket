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
package de.alpharogroup.wicket.components.velocity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.wicket.Component;
import org.apache.wicket.MarkupContainer;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.util.MapModel;
import org.apache.wicket.util.resource.IResourceStream;
import org.apache.wicket.util.resource.StringResourceStream;
import org.apache.wicket.velocity.markup.html.VelocityPanel;

import de.alpharogroup.xml.tag.SimpleTag;

/**
 * The Class VelocityFieldsPanel.
 */
public class VelocityFieldsPanel extends Panel
{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new velocity fields panel.
	 *
	 * @param id
	 *            the id
	 * @param model
	 *            the model
	 */
	public VelocityFieldsPanel(String id, final IModel<List<WicketField<?>>> model)
	{
		super(id, model);
		final Map<String, List<WicketField<?>>> map = new HashMap<>();
		map.put("fields", model.getObject());
		StringBuilder sb = new StringBuilder();

		for (SimpleTag tag : model.getObject())
		{
			sb.append(tag.toString());
		}
		String tmp = sb.toString();

		final IResourceStream template = new StringResourceStream(tmp);
		VelocityPanel velocityPanel = new VelocityPanel("velocityPanel", new MapModel<>(map))
		{

			private static final long serialVersionUID = 1L;

			@Override
			protected IResourceStream getTemplateResource()
			{
				return template;
			}

			@Override
			protected boolean parseGeneratedMarkup()
			{
				return true;
			}
		};
		add(velocityPanel);
		for (WicketField<?> field : model.getObject())
		{
			addChildComponent(field);
			velocityPanel.add(field.getComponent());
		}
	}

	/**
	 * Adds recursive the child components.
	 *
	 * @param parent
	 *            the parent
	 */
	public void addChildComponent(WicketField<?> parent)
	{
		if (parent.getChildren() != null && !parent.getChildren().isEmpty())
		{
			for (SimpleTag iterable_element : parent.getChildren())
			{
				WicketField<?> field = (WicketField<?>)iterable_element;
				Component c = parent.getComponent();
				if (c instanceof MarkupContainer)
				{
					MarkupContainer mc = (MarkupContainer)c;
					mc.add(field.getComponent());
				}
				addChildComponent(field);
			}
		}
	}

	/**
	 * Instantiates a new velocity fields panel.
	 *
	 * @param id
	 *            the id
	 * @param model
	 *            the model
	 * @param templateResource
	 *            the template resource
	 */
	@SuppressWarnings("rawtypes")
	public VelocityFieldsPanel(String id, final IModel<? extends Map> model,
		final IResourceStream templateResource)
	{
		super(id, model);
		add(VelocityPanel.forTemplateResource("velocityPanel", model, templateResource));
	}

}
