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
package de.alpharogroup.wicket.dropdownchoices.pages;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import de.alpharogroup.io.annotations.ImportResource;
import de.alpharogroup.io.annotations.ImportResources;
import de.alpharogroup.wicket.components.i18n.dropdownchoice.panels.TwoDropDownChoicesPanel;
import de.alpharogroup.wicket.components.i18n.dropdownchoice.renderers.PropertiesChoiceRenderer;
import de.alpharogroup.wicket.dropdownchoices.panel.TrademarksModelsPanel;
import de.alpharogroup.wicket.header.contributors.HeaderResponseExtensions;
import de.alpharogroup.wicket.model.dropdownchoices.StringTwoDropDownChoicesModel;
import de.alpharogroup.wicket.model.dropdownchoices.TwoDropDownChoicesModel;

/**
 * The class TwoDropDownChoicesPage.
 *
 * @author Asterios Raptis
 */
@ImportResources(resources = { @ImportResource(resourceName = "TwoDropDownChoicesPage.css", resourceType = "css") })
public class TwoDropDownChoicesPage extends WebPage
{

	/**
	 * The serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;

	private StringTwoDropDownChoicesModel stringTwoDropDownChoicesModel;

	public TwoDropDownChoicesPage(final PageParameters pageParameters)
	{
		super(pageParameters);

		final IModel<TwoDropDownChoicesModel<String>> boundOptionModel = new CompoundPropertyModel<TwoDropDownChoicesModel<String>>(
			new StringTwoDropDownChoicesModel("trademark.audi",
				DatabaseManager.initializeModelMap()));

		final Form<TwoDropDownChoicesModel<String>> selectOptionForm = new Form<TwoDropDownChoicesModel<String>>(
			"selectOptionForm", boundOptionModel);

		add(selectOptionForm);

		final TwoDropDownChoicesPanel<String> twoDropDownChoicesPanel = new TrademarksModelsPanel(
			"twoDropDownChoicesPanel", boundOptionModel, new PropertiesChoiceRenderer(this,
				this.getClass()), new PropertiesChoiceRenderer(this, this.getClass()));
		final AttributeModifier sam = new AttributeModifier("style",
			"width: 200px; margin-bottom: 20px;");
		final AttributeModifier samClass = new AttributeModifier("class", "nowrap");

		final AttributeModifier samSize = new AttributeModifier("size", "3");

		twoDropDownChoicesPanel.getRootChoice().add(sam);
		twoDropDownChoicesPanel.getRootChoice().add(samSize);
		twoDropDownChoicesPanel.getRootChoice().add(samClass);

		twoDropDownChoicesPanel.getChildChoice().add(sam);
		twoDropDownChoicesPanel.getChildChoice().add(new AttributeModifier("size", "4"));

		selectOptionForm.add(twoDropDownChoicesPanel);

		// Create submit button for the form
		final Button entryButton = new Button("entryButton")
		{
			/**
			 * The serialVersionUID.
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void onSubmit()
			{

				System.out.println("RootOption:"
					+ stringTwoDropDownChoicesModel.getSelectedRootOption());
				System.out.println("ChildOption:"
					+ stringTwoDropDownChoicesModel.getSelectedChildOption());

			}
		};

		selectOptionForm.add(entryButton);

	}


	public StringTwoDropDownChoicesModel getStringTwoDropDownChoicesModel()
	{
		return stringTwoDropDownChoicesModel;
	}


	/**
	 * {@inheritDoc}
	 */
	@Override
	public void renderHead(final IHeaderResponse response)
	{
		HeaderResponseExtensions.renderHeaderResponse(response, this.getClass());
	}


	public void setStringTwoDropDownChoicesModel(
		final StringTwoDropDownChoicesModel stringTwoDropDownChoicesModel)
	{
		this.stringTwoDropDownChoicesModel = stringTwoDropDownChoicesModel;
	}

}
