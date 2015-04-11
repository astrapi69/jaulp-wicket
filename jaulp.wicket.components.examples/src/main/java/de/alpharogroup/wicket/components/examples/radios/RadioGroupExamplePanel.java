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

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormChoiceComponentUpdatingBehavior;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.Radio;
import org.apache.wicket.markup.html.form.RadioGroup;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.util.ListModel;
import org.jaulp.test.objects.Company;
import org.jaulp.wicket.base.BasePanel;

import de.alpharogroup.wicket.components.examples.area.publicly.PubliclyBasePage;
import de.alpharogroup.wicket.components.factory.ComponentFactory;
import de.alpharogroup.wicket.components.radio.RadioGroupModel;

public class RadioGroupExamplePanel extends BasePanel<Company>
{
	private static final long serialVersionUID = 1L;

	public RadioGroupExamplePanel(String id, IModel<Company> model)
	{
		super(id, model);
		// Radio buttons must be part of a Form component.
		Form<?> form = new Form<>("form");
		add(form);
		final RadioGroupModel<Company> radioGroupModel = new RadioGroupModel<>();
		setModel(model);
		// create list...
		List<Company> comps = Arrays.asList(Company.builder().name("Ferrari").build(), Company
			.builder().name("Lamborgini").build(), Company.builder().name("Mazerati").build(),
			Company.builder().name("Porsche").build());
		// we can set the selected radio from the start or leave it blank...
		// radioGroupModel.setSelected(comps.get(0));
		radioGroupModel.setRadios(comps);

		IModel<List<Company>> companies = new ListModel<Company>(comps);

		final RadioGroup<Company> group = new RadioGroup<Company>("group", model(from(
			radioGroupModel).getSelected()));
		group.add(new AjaxFormChoiceComponentUpdatingBehavior()
		{
			private static final long serialVersionUID = 1L;

			@Override
			protected void onUpdate(AjaxRequestTarget target)
			{
				target.add(getFeedback());
				info("Selected Type : " + radioGroupModel.getSelected());
			}
		});
		form.add(group);
		// Construct a radio button and label for each company.
		group.add(new ListView<Company>("choice", companies)
		{
			/**
			 * The serialVersionUID.
			 */
			private static final long serialVersionUID = 1L;

			@Override
			protected void populateItem(ListItem<Company> it)
			{
				Radio<Company> radio = new Radio<Company>("radio", it.getModel(), group);
				radio.setOutputMarkupId(true);
				it.add(radio);
				it.add(ComponentFactory.newLabel("label", radio.getMarkupId(),
					Model.of(it.getModelObject().getName())));
			}
		});
	}

	protected Component getFeedback()
	{
		PubliclyBasePage<?> basePage = (PubliclyBasePage<?>)getPage();
		return basePage.getFeedback();
	}

}
