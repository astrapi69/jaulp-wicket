package org.jaulp.wicket.base.examples.velocity;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.jaulp.wicket.base.examples.MenubarPanel;
import org.jaulp.wicket.components.velocity.VelocityFieldsPanel;
import org.jaulp.wicket.components.velocity.WicketField;

public class DynamicPage extends WebPage {
	private static final String WICKET_ID = "wicket:id";

	private static final long serialVersionUID = 1L;

	private List<WicketField<?>> fields;

	public DynamicPage(final PageParameters parameters) {
		super(parameters);
		add(new MenubarPanel("menubarPanel"));
		init();
		IModel<List<WicketField<?>>> model = new CompoundPropertyModel<List<WicketField<?>>>(fields);
		VelocityFieldsPanel velocityFieldsPanel = new VelocityFieldsPanel("velocityFieldsPanel", model);
		add(velocityFieldsPanel);
	}

	public void init() {
		fields = new ArrayList<WicketField<?>>();
		WicketField<Form<Void>> formWicketField = new WicketField<Form<Void>>();
		fields.add(formWicketField);
		formWicketField.addAttribute(WICKET_ID, "form");
		final Form<Void> form = new Form<Void>(formWicketField.getAttributes().get(WICKET_ID));
		formWicketField.setComponent(form);
		formWicketField.setName("form");
		formWicketField.setContent("");
		formWicketField.setEndTag(true);

		WicketField<Label> labelWicketField = new WicketField<Label>();
		labelWicketField.addAttribute(WICKET_ID, "textLabel");
		Label label = new Label(labelWicketField.getAttributes().get(WICKET_ID)	);
		labelWicketField.setComponent(label);
		labelWicketField.getComponent().setDefaultModel(Model.of("Name:"));
		labelWicketField.setName("label");
		labelWicketField.setEndTag(true);
		formWicketField.addChild(labelWicketField);

		final WicketField<TextField<String>> textWicketField = new WicketField<TextField<String>>();
		textWicketField.addAttribute(WICKET_ID, "inputLabel");
		textWicketField.addAttribute("type", "text");
		textWicketField.setComponent(new TextField<String>(textWicketField
				.getAttributes().get(WICKET_ID)));
		textWicketField.getComponent().setDefaultModel(Model.of(""));
		textWicketField.setName("input");
		textWicketField.setEndTag(false);
		formWicketField.addChild(textWicketField);
		
		WicketField<AjaxButton> buttonWicketField = new WicketField<AjaxButton>();
		buttonWicketField.addAttribute(WICKET_ID, "button");
		buttonWicketField.setName("button");
		buttonWicketField.setEndTag(true);
		AjaxButton ajaxButton = new AjaxButton(buttonWicketField.getWicketId()) {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> f) {
				// TODO Auto-generated method stub
				System.out.println(textWicketField.getComponent().getDefaultModelObjectAsString());
				super.onSubmit(target, form);
			}
			
		};
		buttonWicketField.setContent("");
		buttonWicketField.setComponent(ajaxButton);		
		formWicketField.addChild(buttonWicketField);
		

		WicketField<Label> buttonLabelWicketField = new WicketField<Label>();
		buttonLabelWicketField.addAttribute(WICKET_ID, "textLabel");
		Label buttonLabel = new Label(labelWicketField.getAttributes().get(WICKET_ID)	);
		buttonLabelWicketField.setComponent(buttonLabel);
		buttonLabelWicketField.getComponent().setDefaultModel(Model.of("Send"));
		buttonLabelWicketField.setName("span");
		buttonLabelWicketField.setEndTag(true);
		buttonWicketField.addChild(buttonLabelWicketField);
	}
}
