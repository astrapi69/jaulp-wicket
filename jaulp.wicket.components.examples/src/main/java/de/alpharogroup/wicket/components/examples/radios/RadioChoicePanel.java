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
package de.alpharogroup.wicket.components.examples.radios;

import java.util.Arrays;
import java.util.List;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormChoiceComponentUpdatingBehavior;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.IChoiceRenderer;
import org.apache.wicket.markup.html.form.RadioChoice;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;

import de.alpharogroup.wicket.components.examples.area.publicly.PubliclyBasePage;

public class RadioChoicePanel extends Panel
{

	/**
	 * The serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	private static final List<Brands> TYPES = Arrays.asList(Brands.values());

	private Brands selected;

	public RadioChoicePanel(String id, IModel<?> model)
	{
		super(id, model);
		IChoiceRenderer<Brands> renderer = new IChoiceRenderer<Brands>()
		{
			private static final long serialVersionUID = 1L;

			@Override
			public Object getDisplayValue(Brands object)
			{
				return object.getValue();
			}

			@Override
			public String getIdValue(Brands object, int index)
			{
				return object.getValue();
			}

			@SuppressWarnings("unused")
			public Brands getObject(String id, IModel<? extends List<? extends Brands>> choices)
			{
				for (Brands brand : choices.getObject())
				{
					if (brand.getValue().equals(id))
					{
						return brand;
					}
				}
				return null;
			}

		};
		RadioChoice<Brands> brandingType = new RadioChoice<Brands>("branding",
			new PropertyModel<Brands>(this, "selected"), TYPES, renderer);
		// dont add a <br> after every radio...
		brandingType.setSuffix("");
		brandingType.setOutputMarkupId(true);
		brandingType.add(new AjaxFormChoiceComponentUpdatingBehavior()
		{
			private static final long serialVersionUID = 1L;

			@Override
			protected void onUpdate(AjaxRequestTarget target)
			{
				target.add(getFeedback());
				info("Selected Type : " + selected.getValue());
			}
		});
		Form<?> form = new Form<Void>("form");

		add(form);
		form.add(brandingType);
	}

	protected Component getFeedback()
	{
		PubliclyBasePage<?> basePage = (PubliclyBasePage<?>)getPage();
		return basePage.getFeedback();
	}
}
