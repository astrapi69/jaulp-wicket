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
package de.alpharogroup.wicket.base.components.viewmode.examples;


import static org.wicketeer.modelfactory.ModelFactory.from;
import static org.wicketeer.modelfactory.ModelFactory.model;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import de.alpharogroup.test.objects.Gender;
import de.alpharogroup.test.objects.Person;
import de.alpharogroup.wicket.base.BasePage;
import de.alpharogroup.wicket.components.editable.textarea.EditableTextArea;
import de.alpharogroup.wicket.components.editable.textfield.EditableTextField;
import de.alpharogroup.wicket.components.labeled.checkbox.LabeledCheckboxPanel;

public class ViewOrEditPage extends BasePage
{
	private static final long serialVersionUID = 1L;

	private boolean enableFields = true;

	public ViewOrEditPage(final PageParameters parameters)
	{
		super(parameters);

		final Person person = new Person();
		person.setGender(Gender.UNDEFINED);
		person.setName("");
		person.setAbout("bla");
		person.setMarried(false);
		setDefaultModel(Model.of(person));


		final CompoundPropertyModel<Person> cpm = new CompoundPropertyModel<Person>(person);

		final Form<Person> form = new Form<Person>("form", cpm);

		add(form);
		final EditableTextField nameTextField = new EditableTextField("name", model(from(person)
			.getName()), Model.of("Name"));
		form.add(nameTextField);

		final EditableTextArea about = new EditableTextArea("about",
			model(from(person).getAbout()), Model.of("About"));
		form.add(about);


		final LabeledCheckboxPanel<Person> married = new LabeledCheckboxPanel<Person>("married",
			cpm, Model.of("Married:"));

		form.add(married);

		// Create submit button for the form
		final Button submitButton = new AjaxButton("submitButton", form)
		{
			/**
			 * The serialVersionUID.
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void onSubmit(final AjaxRequestTarget target, final Form<?> form)
			{
				info("Person:" + getDefaultModelObjectAsString());
				enableFields = !enableFields;
				if (enableFields)
				{
					about.getSwapPanel().onSwapToEdit(target, form);
					nameTextField.getSwapPanel().onSwapToEdit(target, form);
				}
				else
				{
					about.getSwapPanel().onSwapToView(target, form);
					nameTextField.getSwapPanel().onSwapToView(target, form);
				}


			}
		};

		form.add(submitButton);

		add(new FeedbackPanel("feedbackpanel"));


	}
}
