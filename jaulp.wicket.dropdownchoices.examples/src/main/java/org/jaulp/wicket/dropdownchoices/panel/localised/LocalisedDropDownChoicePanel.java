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
package org.jaulp.wicket.dropdownchoices.panel.localised;

import static org.wicketeer.modelfactory.ModelFactory.from;
import static org.wicketeer.modelfactory.ModelFactory.model;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import org.apache.wicket.Session;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.jaulp.wicket.components.i18n.dropdownchoice.renderers.LocalisedChoiceRenderer;
import org.jaulp.wicket.dropdownchoices.models.OptionModel;

import de.alpharogroup.wicket.components.i18n.dropdownchoice.LocalisedDropDownChoice;

/**
 * The Class LocalisedDropDownChoicePanel.
 */
public class LocalisedDropDownChoicePanel extends Panel
{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	// private final Map< OptionModel, List< OptionModel >> modelsMap = new HashMap< OptionModel,
	// List< OptionModel >>(); // map:company->model

	/**
	 * Instantiates a new localised drop down choice panel.
	 *
	 * @param id
	 *            the id
	 */
	public LocalisedDropDownChoicePanel(final String id)
	{
		super(id);

		OptionModel optionModel = new OptionModel();

		optionModel.setValue("option.value.1");

		final CompoundPropertyModel<OptionModel> boundOptionModel = new CompoundPropertyModel<OptionModel>(
			optionModel);

		final Form<OptionModel> selectOptionForm = new Form<OptionModel>("selectOptionForm",
			boundOptionModel);

		add(selectOptionForm);
		// This should supplied from the database...
		List<String> values = Arrays.asList("1", "2");

		LocalisedDropDownChoice<String> ddc1 = new LocalisedDropDownChoice<String>("options",
			model(from(optionModel).getValue()), values, new LocalisedChoiceRenderer(
				"option.value", this, this.getClass()));

		selectOptionForm.add(ddc1);

		// LocalisedDropDownChoice< String > ddc2 = new LocalisedDropDownChoice< String >(
		// "options2",
		// model(from(optionModel).getValue()), values,
		// new LocalisedChoiceRenderer( "option.value", this, this
		// .getClass() ) );

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
				boolean english = Session.get().getLocale().equals(Locale.ENGLISH);
				if (english)
				{
					Session.get().setLocale(Locale.GERMAN);
				}
				else
				{
					Session.get().setLocale(Locale.ENGLISH);
				}
			}
		};
		selectOptionForm.add(entryButton);

	}

}
