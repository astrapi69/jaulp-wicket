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
package de.alpharogroup.wicket.components.examples.checkbox;

import static org.wicketeer.modelfactory.ModelFactory.from;
import static org.wicketeer.modelfactory.ModelFactory.model;

import java.util.Arrays;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormChoiceComponentUpdatingBehavior;
import org.apache.wicket.markup.html.form.CheckGroup;
import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.util.ListModel;

import de.alpharogroup.test.objects.Company;
import de.alpharogroup.wicket.components.examples.area.publicly.PubliclyBasePage;
import de.alpharogroup.wicket.components.form.CheckChoicesListView;
import de.alpharogroup.wicket.components.form.checkbox.CheckboxModelBean;

public class CheckChoicesListViewPanel extends Panel
{

	/**
	 * The serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;

	public CheckChoicesListViewPanel(final String id, final IModel<?> model)
	{
		super(id, model);
		final Form<?> form = new Form<>("form");
		add(form);
		final CheckboxModelBean<Company> checkboxModel = new CheckboxModelBean<>();
		checkboxModel.setChoices(Arrays.asList(Company.builder().name("Ferrari").build(), Company
			.builder().name("Lamborgini").build(), Company.builder().name("Mazerati").build(),
			Company.builder().name("Porsche").build()));

		// Tell CheckChoicesListView what properties to use for label
		final ChoiceRenderer<Company> renderer = new ChoiceRenderer<Company>("name");

		final CheckGroup<Company> checkGroup = new CheckGroup<Company>("group", model(from(
			checkboxModel).getSelectedItems()));
		checkGroup.add(new AjaxFormChoiceComponentUpdatingBehavior()
		{
			private static final long serialVersionUID = 1L;

			@Override
			protected void onUpdate(final AjaxRequestTarget target)
			{
				target.add(getFeedback());
				info("Selected Types : " + checkboxModel.getSelectedItems());
			}
		});
		form.add(checkGroup.add(new CheckChoicesListView<Company>("choices",
			new ListModel<Company>(checkboxModel.getChoices()), renderer)));
	}

	protected Component getFeedback()
	{
		final PubliclyBasePage<?> basePage = (PubliclyBasePage<?>)getPage();
		return basePage.getFeedback();
	}

}
