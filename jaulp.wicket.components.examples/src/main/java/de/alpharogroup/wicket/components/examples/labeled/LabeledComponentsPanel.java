package de.alpharogroup.wicket.components.examples.labeled;

import java.util.Date;

import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.Model;
import org.jaulp.test.objects.Gender;
import org.jaulp.test.objects.Member;
import org.jaulp.wicket.base.BasePanel;

import de.alpharogroup.wicket.components.labeled.checkbox.LabeledCheckboxPanel;
import de.alpharogroup.wicket.components.labeled.textarea.LabeledTextAreaPanel;
import de.alpharogroup.wicket.components.labeled.textfield.LabeledDateTextFieldPanel;
import de.alpharogroup.wicket.components.labeled.textfield.LabeledTextFieldPanel;

public class LabeledComponentsPanel extends BasePanel<Object> {
	private static final long serialVersionUID = 1L;

	public LabeledComponentsPanel(String id) {
		super(id);
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

		LabeledTextFieldPanel<Member> nameTextField = new LabeledTextFieldPanel<Member>("name", cpm, Model.of("Name:"));

		form.add(nameTextField);
			
		LabeledTextAreaPanel<Member> about = new LabeledTextAreaPanel<Member>("about", cpm,  Model.of("About:"));
		form.add(about);
		
		LabeledCheckboxPanel<Member> married = new LabeledCheckboxPanel<Member>("married", cpm, Model.of("Married:"));
		
		form.add(married);
		
		LabeledDateTextFieldPanel<Member> dateofbirth = new LabeledDateTextFieldPanel<Member>("dateofbirth", cpm, Model.of("Date of birth:"));
		form.add(dateofbirth);
		
//		LabeledAutoCompleteTextfieldPanel<Member> actf = new LabeledAutoCompleteTextfieldPanel<Member>("", cpm, Model.of("city")) {
	//
//			@Override
//			protected AutoCompleteTextField<Member> newAutoCompleteTextField(
//					String id, IModel<Member> model) {
//				return new DefaultCssAutoCompleteTextField<Member>(id, model) {
//					/**
//					 * 
//					 */
//					private static final long serialVersionUID = 1L;
	//
//					@Override
//					protected Iterator<Member> getChoices(String input) {
//						if (Strings.isEmpty(input)) {
//							List<Member> emptyList = Collections.emptyList();
//							return emptyList.iterator();
//						}
	//
//						List<Member> choices = new ArrayList<Member>(10);
	//
//						// TODO return a list of Members...
//						return null;
//					}
//				};
//			}
//		};

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
