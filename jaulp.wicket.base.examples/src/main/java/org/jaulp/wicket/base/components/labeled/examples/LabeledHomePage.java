package org.jaulp.wicket.base.components.labeled.examples;

import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.jaulp.test.objects.Gender;
import org.jaulp.test.objects.Person;
import org.jaulp.wicket.base.BasePage;
import org.jaulp.wicket.components.labeled.checkbox.LabeledCheckboxPanel;
import org.jaulp.wicket.components.labeled.textarea.LabeledTextAreaPanel;
import org.jaulp.wicket.components.labeled.textfield.LabeledTextfieldPanel;

public class LabeledHomePage extends BasePage {
	private static final long serialVersionUID = 1L;
	

    public LabeledHomePage(final PageParameters parameters) {
	super(parameters);	
	
	final Person person = new Person();
	person.setGender(Gender.UNDEFINED);
	person.setName("");
	person.setAbout("");
	person.setMarried(false);



	final CompoundPropertyModel<Person> cpm = new CompoundPropertyModel<Person>(
			person);

	final Form<Person> form = 
	        new Form<Person>("form", cpm);

	add(form);

	LabeledTextfieldPanel<Person> nameTextField = new LabeledTextfieldPanel<Person>("name", cpm, Model.of("Name:"));

	form.add(nameTextField);
		
	LabeledTextAreaPanel<Person> about = new LabeledTextAreaPanel<Person>("about", cpm,  Model.of("About:"));
	form.add(about);
	
	LabeledCheckboxPanel married = new LabeledCheckboxPanel("married", cpm, Model.of("Married:"));
	
	form.add(married);

	// Create submit button for the form
	final Button submitButton = new Button("submitButton") {
		/**
		 * The serialVersionUID.
		 */
		private static final long serialVersionUID = 1L;

		@Override
		public void onSubmit() {
			info("Person:"+person.toString());
		}
	};

	form.add(submitButton);
	
	add(new FeedbackPanel("feedbackpanel"));
	
	
    }

}
