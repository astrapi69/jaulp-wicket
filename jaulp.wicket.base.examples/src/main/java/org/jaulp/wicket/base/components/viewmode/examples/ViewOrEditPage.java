package org.jaulp.wicket.base.components.viewmode.examples;


import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.jaulp.test.objects.Gender;
import org.jaulp.test.objects.Person;
import org.jaulp.wicket.base.BasePage;
import org.jaulp.wicket.components.editable.textarea.EditableTextArea;
import org.jaulp.wicket.components.labeled.checkbox.LabeledCheckboxPanel;
import org.jaulp.wicket.components.viewmode.ViewOrEdit;

public class ViewOrEditPage extends BasePage {
	private static final long serialVersionUID = 1L;
		
	private boolean enableFields = true;

	public ViewOrEditPage(final PageParameters parameters) {
	super(parameters);	
	
	final Person person = new Person();
	person.setGender(Gender.UNDEFINED);
	person.setName("");
	person.setAbout("");
	person.setMarried(false);
	setDefaultModel(Model.of(person));



	final CompoundPropertyModel<Person> cpm = new CompoundPropertyModel<Person>(
			person);

	final Form<Person> form = 
	        new Form<Person>("form", cpm);

	add(form);
	
	form.add(new ViewOrEdit<String>("name", new TextField<String>(ViewOrEdit.COMP_ID, new PropertyModel<String>(cpm, "name") ){
		 
             /**
		 * 
		 */
		private static final long serialVersionUID = 1L;

			@Override
             public boolean isEnabled() {
                 return enableFields;
             }
	}));
	
//	form.add(new ViewOrEdit<String>("about", new TextArea<String>(ViewOrEdit.COMP_ID, new PropertyModel<String>(cpm, "about") ){
//		 
//        /**
//	 * 
//	 */
//	private static final long serialVersionUID = 1L;
//
//		@Override
//        public boolean isEnabled() {
//            return enableFields;
//        }	
//	}));
	
	final EditableTextArea<String> about = new EditableTextArea<String>("about");
	form.add(about);
	
	
	LabeledCheckboxPanel<Person> married = new LabeledCheckboxPanel<Person>("married", cpm, Model.of("Married:"));
	
	form.add(married);

	// Create submit button for the form
	final Link<String> submitButton = new Link<String>("submitButton") {
		/**
		 * The serialVersionUID.
		 */
		private static final long serialVersionUID = 1L;

		@Override
		public void onClick() {
			info("Person:"+getDefaultModelObjectAsString());
			enableFields = !enableFields;
//			about.setEditable(enableFields);
			
		}
	};

	form.add(submitButton);
	
	add(new FeedbackPanel("feedbackpanel"));
	
	
    }
}
