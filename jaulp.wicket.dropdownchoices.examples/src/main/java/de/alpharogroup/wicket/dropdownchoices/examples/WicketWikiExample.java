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
package de.alpharogroup.wicket.dropdownchoices.examples;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import de.alpharogroup.wicket.components.i18n.dropdownchoice.panels.TwoDropDownChoicesPanel;
import de.alpharogroup.wicket.components.i18n.dropdownchoice.renderers.PropertiesChoiceRenderer;
import de.alpharogroup.wicket.dropdownchoices.pages.DatabaseManager;
import de.alpharogroup.wicket.dropdownchoices.panel.TrademarksModelsPanel;
import de.alpharogroup.wicket.dropdownchoices.panel.WicketWikiExamplePanel;
import de.alpharogroup.wicket.dropdownchoices.panel.base.BaseDropDownChoicePanel;
import de.alpharogroup.wicket.dropdownchoices.panel.localised.LocalisedDropDownChoicePanel;
import de.alpharogroup.wicket.dropdownchoices.panel.simple.SimpleDropDownChoicePanel;
import de.alpharogroup.wicket.dropdownchoices.three.choices.ThreeDropDownChoicesPanel;
import de.alpharogroup.wicket.model.dropdownchoices.StringTwoDropDownChoicesModel;
import de.alpharogroup.wicket.model.dropdownchoices.TwoDropDownChoicesModel;

/**
 * @author Asterios Raptis
 */
public class WicketWikiExample extends WebPage
{

	private static final long serialVersionUID = 1L;
	private final FeedbackPanel feedback;

	public WicketWikiExample(final PageParameters params)
	{
		super(params);

		// Construct form and feedback panel and hook them up
		feedback = new FeedbackPanel("feedback");
		add(feedback);

		final SimpleDropDownChoicePanel simpleDropDownChoicePanel = new SimpleDropDownChoicePanel(
			"simpleDropDownChoicePanel");

		add(simpleDropDownChoicePanel);

		final BaseDropDownChoicePanel baseDropDownChoicePanel = new BaseDropDownChoicePanel(
			"baseDropDownChoicePanel");

		add(baseDropDownChoicePanel);

		final WicketWikiExamplePanel wicketWikiExamplePanel = new WicketWikiExamplePanel(
			"wicketWikiExamplePanel");

		add(wicketWikiExamplePanel);

		final IModel<TwoDropDownChoicesModel<String>> stringTwoDropDownChoicesModel = Model
			.of(new StringTwoDropDownChoicesModel("trademark.audi", DatabaseManager
				.initializeModelMap()));

		final TwoDropDownChoicesPanel<String> twoDropDownChoicesPanel = new TrademarksModelsPanel(
			"twoDropDownChoicesPanel", stringTwoDropDownChoicesModel, new PropertiesChoiceRenderer(
				this, this.getClass()), new PropertiesChoiceRenderer(this, this.getClass()));
		add(twoDropDownChoicesPanel);

		final LocalisedDropDownChoicePanel localisedDropDownChoicePanel = new LocalisedDropDownChoicePanel(
			"localisedDropDownChoicePanel");
		add(localisedDropDownChoicePanel);

		final ThreeDropDownChoicesPanel threeDropDownChoicesPanel = new ThreeDropDownChoicesPanel(
			"threeDropDownChoicesPanel");
		add(threeDropDownChoicesPanel);
	}

	public FeedbackPanel getFeedback()
	{
		return feedback;
	}

}
