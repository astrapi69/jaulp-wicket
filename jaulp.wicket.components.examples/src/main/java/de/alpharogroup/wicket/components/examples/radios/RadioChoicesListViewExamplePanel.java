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
import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.RadioGroup;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.model.util.ListModel;

import de.alpharogroup.test.objects.Company;
import de.alpharogroup.wicket.base.BasePanel;
import de.alpharogroup.wicket.components.examples.area.publicly.PubliclyBasePage;
import de.alpharogroup.wicket.components.form.RadioChoicesListView;
import de.alpharogroup.wicket.components.radio.RadioGroupModel;

public class RadioChoicesListViewExamplePanel extends BasePanel<Company>
{
	private static final long serialVersionUID = 1L;

	public RadioChoicesListViewExamplePanel(final String id, final IModel<Company> model)
	{
		super(id, model);
		// Radio buttons have to be part of a Form component.
		final Form<?> form = new Form<>("form");
		add(form);
		final RadioGroupModel<Company> radioGroupModel = new RadioGroupModel<>();
		setModel(model);
		// create list...
		final List<Company> comps = Arrays.asList(Company.builder().name("Ferrari").build(),
			Company.builder().name("Lamborgini").build(),
			Company.builder().name("Mazerati").build(), Company.builder().name("Porsche").build());
		// we can set the selected radio from the start or leave it blank...
		// radioGroupModel.setSelected(comps.get(0));
		radioGroupModel.setRadios(comps);

		final IModel<List<Company>> companies = new ListModel<Company>(comps);

		// Tell RadioChoicesListView what properties to use for label
		final ChoiceRenderer<Company> renderer = new ChoiceRenderer<Company>("name");

		// Wire it all up!
		final RadioGroup<Company> firstGroup = new RadioGroup<Company>("firstGroup",
			new PropertyModel<Company>(radioGroupModel, "selected"));
		firstGroup.add(new RadioChoicesListView<Company>("choice", companies, renderer));
		form.add(firstGroup);
		firstGroup.add(new AjaxFormChoiceComponentUpdatingBehavior()
		{
			private static final long serialVersionUID = 1L;

			@Override
			protected void onUpdate(final AjaxRequestTarget target)
			{
				target.add(getFeedback());
				info("Selected Type : " + radioGroupModel.getSelected().getName());
			}
		});

	}

	protected Component getFeedback()
	{
		final PubliclyBasePage<?> basePage = (PubliclyBasePage<?>)getPage();
		return basePage.getFeedback();
	}

}
