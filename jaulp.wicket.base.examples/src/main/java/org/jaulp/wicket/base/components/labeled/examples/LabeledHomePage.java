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
import org.jaulp.wicket.components.labeled.checkbox.LabeledCheckboxPanel;
import org.jaulp.wicket.components.labeled.textarea.LabeledTextAreaPanel;
import org.jaulp.wicket.components.labeled.textfield.LabeledDateTextfieldPanel;
import org.jaulp.wicket.components.labeled.textfield.LabeledTextfieldPanel;
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



	final CompoundPropertyModel<Member> cpm = new CompoundPropertyModel<Member>(
			person);

	final Form<Member> form = 
	        new Form<Member>("form", cpm);

	add(form);

	LabeledTextfieldPanel<Member> nameTextField = new LabeledTextfieldPanel<Member>("name", cpm, Model.of("Name:"));

	form.add(nameTextField);
		
	LabeledTextAreaPanel<Member> about = new LabeledTextAreaPanel<Member>("about", cpm,  Model.of("About:"));
	form.add(about);
	
	LabeledCheckboxPanel<Member> married = new LabeledCheckboxPanel<Member>("married", cpm, Model.of("Married:"));
	
	form.add(married);
	
	LabeledDateTextfieldPanel<Member> dateofbirth = new LabeledDateTextfieldPanel<Member>("dateofbirth", cpm, Model.of("Date of birth:"));
	form.add(dateofbirth);

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
