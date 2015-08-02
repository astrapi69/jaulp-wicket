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
package de.alpharogroup.wicket.components.examples.fragment.replacewith;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormChoiceComponentUpdatingBehavior;
import org.apache.wicket.ajax.markup.html.AjaxFallbackLink;
import org.apache.wicket.ajax.markup.html.form.AjaxFallbackButton;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.RadioChoice;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import de.alpharogroup.wicket.components.examples.fragment.swapping.person.EditPersonPanel;
import de.alpharogroup.wicket.components.examples.fragment.swapping.person.PersonBean;
import de.alpharogroup.wicket.components.examples.fragment.swapping.person.ViewPersonPanel;
import de.alpharogroup.wicket.components.factory.ComponentFactory;

public class ReplaceWithPanel extends Panel
{

	/**
	 * The serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	private static final String PLACEHOLDER_ID = "placeholderComponent";
	private Component placeholderComponent;
	private Component viewComponent;
	private Component editComponent;
	private String selectedPanel = "View person";

	public ReplaceWithPanel(final String id, final IModel<PersonBean> model)
	{
		super(id, model);
		setDefaultModel(model);
		viewComponent = newViewPersonPanel(PLACEHOLDER_ID, model);
		editComponent = newEditPersonPanel(PLACEHOLDER_ID, model);
		final List<String> choices = new ArrayList<String>();
		choices.add("View person");
		choices.add("Edit person");
		final RadioChoice<String> radioChoice = ComponentFactory.newRadioChoice("radioChoice",
			new Model<String>(selectedPanel), choices);

		radioChoice.add(new AjaxFormChoiceComponentUpdatingBehavior()
		{
			private static final long serialVersionUID = 1L;

			@Override
			protected void onUpdate(final AjaxRequestTarget target)
			{
				Component newComponent = null;
				if ("View person".equals(radioChoice.getModelObject()))
				{
					newComponent = viewComponent;
				}
				else
				{
					newComponent = editComponent;
				}
				newComponent.setOutputMarkupId(true);
				placeholderComponent.replaceWith(newComponent);
				target.add(newComponent);
				placeholderComponent = newComponent;
			}
		});
		placeholderComponent = newViewPersonPanel(PLACEHOLDER_ID, model);
		placeholderComponent.setOutputMarkupId(true);
		final Form<String> form = new Form<String>("form");
		form.add(radioChoice);
		form.add(placeholderComponent);
		this.add(form);
	}

	protected Component newEditPersonPanel(final String id, final IModel<PersonBean> model)
	{
		return new EditPersonPanel(id, model)
		{
			private static final long serialVersionUID = 1L;

			@Override
			protected Component newSubmitButton(final String id, final Form<?> form)
			{
				return new AjaxFallbackButton(id, form)
				{
					private static final long serialVersionUID = 1L;

					@Override
					protected void onConfigure()
					{
						super.onConfigure();
						setVisibilityAllowed(false);
					};
				}.setOutputMarkupId(true);
			}
		};
	}

	protected Component newViewPersonPanel(final String id, final IModel<PersonBean> model)
	{
		return new ViewPersonPanel(id, model)
		{
			private static final long serialVersionUID = 1L;

			@Override
			protected AjaxFallbackLink<Object> newEditLink(final String id)
			{
				return new AjaxFallbackLink<Object>(id)
				{
					private static final long serialVersionUID = 1L;

					@Override
					public void onClick(final AjaxRequestTarget target)
					{
					}

					@Override
					protected void onConfigure()
					{
						super.onConfigure();
						setVisibilityAllowed(false);
					};
				};
			}
		};
	}

}
