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
package org.jaulp.wicket.base.components.labeled.examples;

import java.util.Date;

import net.sourceforge.jaulp.io.annotations.ImportResource;
import net.sourceforge.jaulp.io.annotations.ImportResources;

import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.jaulp.test.objects.Gender;
import org.jaulp.test.objects.Member;
import org.jaulp.wicket.base.BasePage;

import de.alpharogroup.wicket.components.labeled.checkbox.LabeledCheckboxPanel;
import de.alpharogroup.wicket.components.labeled.textarea.LabeledTextAreaPanel;
import de.alpharogroup.wicket.components.labeled.textfield.LabeledDateTextFieldPanel;
import de.alpharogroup.wicket.components.labeled.textfield.LabeledDateTimeFieldPanel;
import de.alpharogroup.wicket.components.labeled.textfield.LabeledTextFieldPanel;
@ImportResources(resources = {
		@ImportResource(resourceName = "jquery-1.5.2.js", resourceType = "js", index=1),
		@ImportResource(resourceName = "wrapLabeledFormElements.js", resourceType = "js", index=2)})
public class LabeledHomePage extends BasePage {
	private static final long serialVersionUID = 1L;
	

    public LabeledHomePage(final PageParameters parameters) {
	super(parameters);	
	
	final Member person = new Member();
	person.setGender(Gender.UNDEFINED);
	person.setName("");
	person.setAbout("");
	person.setMarried(false);
	person.setDateofbirth(new Date());
	person.setDateofMarriage(new Date());

	final CompoundPropertyModel<Member> cpm = new CompoundPropertyModel<>(
			person);

	final Form<Member> form = 
	        new Form<>("form", cpm);

	add(form);

	LabeledTextFieldPanel<Member> nameTextField = new LabeledTextFieldPanel<>("name", cpm, Model.of("Name:"));

	form.add(nameTextField);
		
	LabeledTextAreaPanel<Member> about = new LabeledTextAreaPanel<>("about", cpm,  Model.of("About:"));
	form.add(about);
	
	LabeledCheckboxPanel<Member> married = new LabeledCheckboxPanel<>("married", cpm, Model.of("Married:"));
	
	form.add(married);

	LabeledDateTextFieldPanel<Member> dateofbirth = new LabeledDateTextFieldPanel<>("dateofbirth", cpm, Model.of("Date of birth:"));
	form.add(dateofbirth);

	LabeledDateTimeFieldPanel<Member> dateofMarriage = new LabeledDateTimeFieldPanel<>("dateofMarriage", cpm, Model.of("Date of marriage:"));
	form.add(dateofMarriage);
	
//	LabeledAutoCompleteTextfieldPanel<Member> actf = new LabeledAutoCompleteTextfieldPanel<Member>("", cpm, Model.of("city")) {
//
//		@Override
//		protected AutoCompleteTextField<Member> newAutoCompleteTextField(
//				String id, IModel<Member> model) {
//			return new DefaultCssAutoCompleteTextField<Member>(id, model) {
//				/**
//				 * 
//				 */
//				private static final long serialVersionUID = 1L;
//
//				@Override
//				protected Iterator<Member> getChoices(String input) {
//					if (Strings.isEmpty(input)) {
//						List<Member> emptyList = Collections.emptyList();
//						return emptyList.iterator();
//					}
//
//					List<Member> choices = new ArrayList<Member>(10);
//
//					// TODO return a list of Members...
//					return null;
//				}
//			};
//		}
//	};

	// Create submit button for the form
	final Button submitButton = new Button("submitButton") {
		/**
		 * The serialVersionUID.
		 */
		private static final long serialVersionUID = 1L;

		@Override
		public void onSubmit() {
			info("Member:"+person.toString());
		}
	};

	form.add(submitButton);
	
	add(new FeedbackPanel("feedbackpanel"));
	
	
    }

}
