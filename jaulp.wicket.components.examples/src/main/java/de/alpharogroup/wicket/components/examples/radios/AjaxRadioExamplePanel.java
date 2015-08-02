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

import lombok.Getter;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.resource.CssResourceReference;

import de.alpharogroup.test.objects.Company;
import de.alpharogroup.wicket.base.BasePanel;
import de.alpharogroup.wicket.components.examples.area.publicly.PubliclyBasePage;
import de.alpharogroup.wicket.components.radio.AjaxRadioPanel;
import de.alpharogroup.wicket.components.radio.RadioGroupModel;

public class AjaxRadioExamplePanel extends BasePanel<List<Company>>
{

	/**
	 * The serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;
	@Getter
	AjaxRadioPanel<Company> ajaxRadioPanel;

	public AjaxRadioExamplePanel(final String id, final IModel<List<Company>> model)
	{
		super(id, model);
		// create list...
		final List<Company> items = Arrays.asList(Company.builder().name("Ferrari").build(),
			Company.builder().name("Lamborgini").build(), Company.builder().name("Mazerati")
				.build(), Company.builder().name("Porsche").build());
		final RadioGroupModel<Company> radioGroupModel = new RadioGroupModel<>();
		setModel(model);
		radioGroupModel.setLabelPropertyExpression("name");
		// we can set the selected radio from the start or leave it blank...
		// radioGroupModel.setSelected(items.get(0));
		radioGroupModel.setRadios(items);
		ajaxRadioPanel = new AjaxRadioPanel<Company>("ajaxRadioPanel", Model.of(radioGroupModel))
		{
			private static final long serialVersionUID = 1L;

			@Override
			protected void onRadioSelect(final AjaxRequestTarget target, final Company newSelection)
			{
				info("You have selected " + newSelection.getName());
				target.add(getFeedback());
			}
		};
		add(ajaxRadioPanel);
	}

	protected Component getFeedback()
	{
		final PubliclyBasePage<?> basePage = (PubliclyBasePage<?>)getPage();
		return basePage.getFeedback();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void renderHead(final IHeaderResponse response)
	{
		super.renderHead(response);
		response.render(CssHeaderItem.forReference(new CssResourceReference(
			AjaxRadioExamplePanel.class, "AjaxRadioExamplePanel.css")));

	}
}
