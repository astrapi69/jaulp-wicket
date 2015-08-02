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
package de.alpharogroup.wicket.dropdownchoices.three.choices;

import java.util.ArrayList;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormComponentUpdatingBehavior;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;

import de.alpharogroup.wicket.components.i18n.dropdownchoice.LocalisedDropDownChoice;
import de.alpharogroup.wicket.components.i18n.dropdownchoice.renderers.PropertiesChoiceRenderer;
import de.alpharogroup.wicket.components.i18n.dropdownchoice.renderers.SelectedValuesChoiceRenderer;
import de.alpharogroup.wicket.dropdownchoices.pages.DatabaseManager;
import de.alpharogroup.wicket.model.dropdownchoices.StringThreeDropDownChoicesModel;

/**
 * The Class TwoDropDownChoicesPanel.
 *
 * @author Asterios Raptis
 */
public class ThreeDropDownChoicesPanel extends Panel
{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new two drop down choices panel.
	 *
	 * @param id
	 *            the id
	 */
	public ThreeDropDownChoicesPanel(final String id)
	{
		super(id);

		final StringThreeDropDownChoicesModel stringThreeDropDownChoicesModel = new StringThreeDropDownChoicesModel(
			"trademark.audi", DatabaseManager.initializeModelMap(), new ArrayList<String>());

		final CompoundPropertyModel<StringThreeDropDownChoicesModel> boundOptionModel = new CompoundPropertyModel<StringThreeDropDownChoicesModel>(
			stringThreeDropDownChoicesModel);

		final Form<StringThreeDropDownChoicesModel> selectOptionForm = new Form<StringThreeDropDownChoicesModel>(
			"selectOptionForm", boundOptionModel);

		add(selectOptionForm);

		IModel<String> selectedRootOptionModel = null;
		// selectedRootOptionModel =
		// model(from(stringThreeDropDownChoicesModel).getSelectedRootOption());
		selectedRootOptionModel = new PropertyModel<String>(stringThreeDropDownChoicesModel,
			"selectedRootOption");
		final LocalisedDropDownChoice<String> trademarks = new LocalisedDropDownChoice<String>(
			"trademarks", selectedRootOptionModel,
			stringThreeDropDownChoicesModel.getRootChoices(), new PropertiesChoiceRenderer(this,
				this.getClass()));
		trademarks.setDefaultChoice(true);
		trademarks.setOutputMarkupId(true);

		IModel<String> selectedChildOptionModel = null;
		// selectedChildOptionModel =
		// model(from(stringThreeDropDownChoicesModel).getSelectedChildOption());
		selectedChildOptionModel = new PropertyModel<String>(stringThreeDropDownChoicesModel,
			"selectedChildOption");
		final LocalisedDropDownChoice<String> models = new LocalisedDropDownChoice<String>(
			"models", selectedChildOptionModel, stringThreeDropDownChoicesModel.getChildChoices(),
			new PropertiesChoiceRenderer(this, this.getClass()));
		models.setDefaultChoice(true);
		models.setOutputMarkupId(true);

		IModel<String> selectedValueOptionModel = null;
		// selectedValueOptionModel =
		// model(from(stringThreeDropDownChoicesModel).getSelectedValueOption());
		selectedValueOptionModel = new PropertyModel<String>(stringThreeDropDownChoicesModel,
			"selectedValueOption");

		final LocalisedDropDownChoice<String> selectedModels = new LocalisedDropDownChoice<String>(
			"selectedModels", selectedValueOptionModel,
			stringThreeDropDownChoicesModel.getSelectedValuesChoices(),
			new SelectedValuesChoiceRenderer(this, this.getClass()));
		selectedModels.setDefaultChoice(true);
		selectedModels.setOutputMarkupId(true);

		selectOptionForm.add(trademarks);
		selectOptionForm.add(models);

		selectOptionForm.add(selectedModels);

		trademarks.add(new AjaxFormComponentUpdatingBehavior("onchange")
		{

			/** The Constant serialVersionUID. */
			private static final long serialVersionUID = 1L;

			@Override
			protected void onUpdate(final AjaxRequestTarget target)
			{
				target.add(models);
			}
		});

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
				final String selectedRootOption = stringThreeDropDownChoicesModel
					.getSelectedRootOption();
				final String selectedChildOption = stringThreeDropDownChoicesModel
					.getSelectedChildOption();
				System.out.println("RootOption:" + selectedRootOption);
				System.out.println("ChildOption:" + selectedChildOption);
				if (selectedRootOption != null && selectedChildOption != null)
				{
					stringThreeDropDownChoicesModel
						.addSelectedValue(stringThreeDropDownChoicesModel.getSelectedRootOption()
							+ "=>" + stringThreeDropDownChoicesModel.getSelectedChildOption());
				}
				else
				{
					getPage().error("Select trademark and model.");
				}

			}
		};

		selectOptionForm.add(entryButton);
	}
}
