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
package org.jaulp.wicket.base.components.viewmode.examples;


import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.jaulp.test.objects.Gender;
import org.jaulp.test.objects.Person;
import org.jaulp.wicket.base.BasePage;
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
		final EditableTextField nameTextField = new EditableTextField("name",
			new PropertyModel<String>(person, "name"));
		form.add(nameTextField);
		IModel<String> taModel = new PropertyModel<String>(person, "about");
		final EditableTextArea about = new EditableTextArea("about", taModel);
		form.add(about);


		LabeledCheckboxPanel<Person> married = new LabeledCheckboxPanel<Person>("married", cpm,
			Model.of("Married:"));

		form.add(married);

		// Create submit button for the form
		final Button submitButton = new Button("submitButton")
		{
			/**
			 * The serialVersionUID.
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void onSubmit()
			{
				info("Person:" + getDefaultModelObjectAsString());
				enableFields = !enableFields;
				about.setEditable(enableFields);
				nameTextField.setEditable(enableFields);


			}
		};

		form.add(submitButton);

		add(new FeedbackPanel("feedbackpanel"));


	}
}
