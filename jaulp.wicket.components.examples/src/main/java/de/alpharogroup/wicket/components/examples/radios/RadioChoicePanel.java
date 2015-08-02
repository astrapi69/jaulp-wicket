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

import static org.wicketeer.modelfactory.ModelFactory.from;
import static org.wicketeer.modelfactory.ModelFactory.model;

import java.util.Arrays;
import java.util.List;

import lombok.Getter;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormChoiceComponentUpdatingBehavior;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.IChoiceRenderer;
import org.apache.wicket.markup.html.form.RadioChoice;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;

import de.alpharogroup.test.objects.enums.Brands;
import de.alpharogroup.wicket.components.examples.area.publicly.PubliclyBasePage;
import de.alpharogroup.wicket.components.factory.ComponentFactory;

public class RadioChoicePanel extends Panel
{

	/**
	 * The serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	private static final List<Brands> TYPES = Arrays.asList(Brands.values());

	@Getter
	private Brands selected;

	public RadioChoicePanel(final String id, final IModel<?> model)
	{
		super(id, model);
		final IChoiceRenderer<Brands> renderer = new IChoiceRenderer<Brands>()
		{
			private static final long serialVersionUID = 1L;

			@Override
			public Object getDisplayValue(final Brands object)
			{
				return object.getValue();
			}

			@Override
			public String getIdValue(final Brands object, final int index)
			{
				return object.getValue();
			}

			@SuppressWarnings("unused")
			public Brands getObject(final String id,
				final IModel<? extends List<? extends Brands>> choices)
			{
				for (final Brands brand : choices.getObject())
				{
					if (brand.getValue().equals(id))
					{
						return brand;
					}
				}
				return null;
			}

		};
		final RadioChoice<Brands> brandingType = ComponentFactory.newRadioChoice("branding",
			model(from(this).getSelected()), TYPES, renderer);
		// dont add a <br> after every radio...
		brandingType.setSuffix("");
		brandingType.setOutputMarkupId(true);
		brandingType.add(new AjaxFormChoiceComponentUpdatingBehavior()
		{
			private static final long serialVersionUID = 1L;

			@Override
			protected void onUpdate(final AjaxRequestTarget target)
			{
				target.add(getFeedback());
				info("Selected Type : " + selected.getValue());
			}
		});
		final Form<?> form = new Form<Void>("form");

		add(form);
		form.add(brandingType);
	}

	protected Component getFeedback()
	{
		final PubliclyBasePage<?> basePage = (PubliclyBasePage<?>)getPage();
		return basePage.getFeedback();
	}
}
