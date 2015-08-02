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
package de.alpharogroup.wicket.base.examples.velocity;

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
import org.wicketstuff.annotation.mount.MountPath;

import de.alpharogroup.wicket.base.examples.MenubarPanel;
import de.alpharogroup.wicket.components.velocity.VelocityFieldsPanel;
import de.alpharogroup.wicket.components.velocity.WicketField;

@MountPath("/velocity")
public class DynamicPage extends WebPage
{
	private static final String WICKET_ID = "wicket:id";

	private static final long serialVersionUID = 1L;

	private List<WicketField<?>> fields;

	public DynamicPage(final PageParameters parameters)
	{
		super(parameters);
		add(new MenubarPanel("menubarPanel"));
		initialize();
		final IModel<List<WicketField<?>>> model = new CompoundPropertyModel<List<WicketField<?>>>(
			fields);
		final VelocityFieldsPanel velocityFieldsPanel = new VelocityFieldsPanel(
			"velocityFieldsPanel", model);
		add(velocityFieldsPanel);
	}

	public void initialize()
	{
		fields = new ArrayList<WicketField<?>>();
		final WicketField<Form<Void>> formWicketField = new WicketField<Form<Void>>();
		fields.add(formWicketField);
		formWicketField.addAttribute(WICKET_ID, "form");
		final Form<Void> form = new Form<Void>(formWicketField.getAttributes().get(WICKET_ID));
		formWicketField.setComponent(form);
		formWicketField.setName("form");
		formWicketField.setContent("");
		formWicketField.setEndTag(true);

		final WicketField<Label> labelWicketField = new WicketField<Label>();
		labelWicketField.addAttribute(WICKET_ID, "textLabel");
		final Label label = new Label(labelWicketField.getAttributes().get(WICKET_ID));
		labelWicketField.setComponent(label);
		labelWicketField.getComponent().setDefaultModel(Model.of("Name:"));
		labelWicketField.setName("label");
		labelWicketField.setEndTag(true);
		formWicketField.addChild(labelWicketField);

		final WicketField<TextField<String>> textWicketField = new WicketField<TextField<String>>();
		textWicketField.addAttribute(WICKET_ID, "inputLabel");
		textWicketField.addAttribute("type", "text");
		textWicketField.setComponent(new TextField<String>(textWicketField.getAttributes().get(
			WICKET_ID)));
		textWicketField.getComponent().setDefaultModel(Model.of(""));
		textWicketField.setName("input");
		textWicketField.setEndTag(false);
		formWicketField.addChild(textWicketField);

		final WicketField<AjaxButton> buttonWicketField = new WicketField<AjaxButton>();
		buttonWicketField.addAttribute(WICKET_ID, "button");
		buttonWicketField.setName("button");
		buttonWicketField.setEndTag(true);
		final AjaxButton ajaxButton = new AjaxButton(buttonWicketField.getWicketId())
		{

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			protected void onSubmit(final AjaxRequestTarget target, final Form<?> f)
			{
				System.out.println(textWicketField.getComponent().getDefaultModelObjectAsString());
				super.onSubmit(target, form);
			}

		};
		buttonWicketField.setContent("");
		buttonWicketField.setComponent(ajaxButton);
		formWicketField.addChild(buttonWicketField);


		final WicketField<Label> buttonLabelWicketField = new WicketField<Label>();
		buttonLabelWicketField.addAttribute(WICKET_ID, "textLabel");
		final Label buttonLabel = new Label(labelWicketField.getAttributes().get(WICKET_ID));
		buttonLabelWicketField.setComponent(buttonLabel);
		buttonLabelWicketField.getComponent().setDefaultModel(Model.of("Send"));
		buttonLabelWicketField.setName("span");
		buttonLabelWicketField.setEndTag(true);
		buttonWicketField.addChild(buttonLabelWicketField);
	}
}
