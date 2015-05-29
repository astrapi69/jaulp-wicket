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
package de.alpharogroup.wicket.components.examples.labeled;

import java.util.Date;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.extensions.yui.calendar.DateTimeField;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;

import de.alpharogroup.test.objects.Gender;
import de.alpharogroup.test.objects.Member;
import de.alpharogroup.wicket.base.BasePanel;
import de.alpharogroup.wicket.base.util.ComponentFinder;
import de.alpharogroup.wicket.component.search.ComponentExpression;
import de.alpharogroup.wicket.component.search.ComponentExpressionFinder;
import de.alpharogroup.wicket.components.examples.area.publicly.PubliclyBasePage;
import de.alpharogroup.wicket.components.examples.fragment.swapping.HomeAddress;
import de.alpharogroup.wicket.components.examples.labeled.address.AddressPanel;
import de.alpharogroup.wicket.components.form.input.TwoFormComponentBean;
import de.alpharogroup.wicket.components.labeled.LabeledTwoFormComponentPanel;
import de.alpharogroup.wicket.components.labeled.checkbox.LabeledCheckboxPanel;
import de.alpharogroup.wicket.components.labeled.label.LabeledEnumLabelPanel;
import de.alpharogroup.wicket.components.labeled.label.LabeledLabelPanel;
import de.alpharogroup.wicket.components.labeled.textarea.LabeledTextAreaPanel;
import de.alpharogroup.wicket.components.labeled.textfield.LabeledDateTextFieldPanel;
import de.alpharogroup.wicket.components.labeled.textfield.LabeledDateTimeFieldPanel;
import de.alpharogroup.wicket.components.labeled.textfield.LabeledTextFieldPanel;

public class LabeledComponentsPanel extends BasePanel<Object>
{
	private static final long serialVersionUID = 1L;
	private FeedbackPanel feedbackPanel;
	
	AddressPanel addressPanel;

	public LabeledComponentsPanel(String id)
	{
		super(id);
		final Member person = new Member();
		person.setGender(Gender.UNDEFINED);
		person.setName("foo");
		person.setAbout("");
		person.setMarried(false);
		person.setDateofbirth(new Date());
		person.setDateofMarriage(new Date());

		final CompoundPropertyModel<Member> cpm = new CompoundPropertyModel<Member>(person);

		final Form<Member> form = new Form<Member>("form", cpm);

		add(form);

		LabeledEnumLabelPanel<Member> genderLabel = new LabeledEnumLabelPanel<Member>("gender",
			cpm, Model.of("Gender:"));
		form.add(genderLabel);

		LabeledLabelPanel<Member> nameLabel = new LabeledLabelPanel<Member>("name", cpm,
			Model.of("Name:"));
		form.add(nameLabel);

		LabeledTextFieldPanel<Member> nameTextField = new LabeledTextFieldPanel<Member>("nickname",
			cpm, Model.of("Input your nickname:"));
		form.add(nameTextField);

		LabeledTextAreaPanel<Member> about = new LabeledTextAreaPanel<Member>("about", cpm,
			Model.of("About:"));
		form.add(about);

		LabeledCheckboxPanel<Member> married = new LabeledCheckboxPanel<Member>("married", cpm,
			Model.of("Married:"));

		form.add(married);

		LabeledDateTextFieldPanel<Member> dateofbirth = new LabeledDateTextFieldPanel<Member>(
			"dateofbirth", cpm, Model.of("Date of birth:"));
		form.add(dateofbirth);


		LabeledDateTimeFieldPanel<Member> dateofMarriage = new LabeledDateTimeFieldPanel<Member>(
			"dateofMarriage", cpm, Model.of("Date of marriage:"))
		{
			private static final long serialVersionUID = 1L;

			@Override
			protected DateTimeField newDateTimeField(String id, IModel<Member> model)
			{
				PropertyModel<Date> textFieldModel = new PropertyModel<Date>(model.getObject(),
					getId());
				DateTimeField dateTextField = new DateTimeField(id, textFieldModel);
				return dateTextField;
			}

		};
		form.add(dateofMarriage);

		IModel<TwoFormComponentBean<String, String>> twoFormCompModel = Model
			.of(new TwoFormComponentBean<String, String>());
		form.add(new LabeledTwoFormComponentPanel<String, String>("twoFormComponentPanel",
			twoFormCompModel, Model.of("Street / number:")));
		final IModel<HomeAddress> addressModel = Model.of(HomeAddress.builder().street("").localNumber("").code("").city("").build());
		// Create submit button for the form
		final AjaxButton submitButton = new AjaxButton("submitButton", form)
		{
			/**
			 * The serialVersionUID.
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void onSubmit(AjaxRequestTarget target, Form<?> form)
			{
				info("Member:" + person.toString());
				info(":::Address::" + addressModel.toString());
				Component comp = ComponentExpression.findComponent(LabeledComponentsPanel.this, "addressPanel");
				target.add(comp);
			}
		};

		form.add(submitButton);
		
		add(addressPanel = new AddressPanel("addressPanel", addressModel));

		add(feedbackPanel = new FeedbackPanel("feedbackpanel"));
		feedbackPanel.setOutputMarkupId(true);
	}

	protected Component getFeedback()
	{
		PubliclyBasePage<?> basePage = (PubliclyBasePage<?>)getPage();
		return basePage.getFeedback();
	}
}
