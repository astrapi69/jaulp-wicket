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
package de.alpharogroup.wicket.components.examples.fragment.swapping.person;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;

public class EditPersonPanel extends Panel
{

	/**
	 * The serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	public EditPersonPanel(final String id, final IModel<PersonBean> model)
	{
		super(id, model);
		setOutputMarkupId(true);
		setDefaultModel(model);
		final Form<PersonBean> form = new Form<PersonBean>("editPersonForm");
		form.setOutputMarkupId(true);
		add(form);
		form.add(new TextField<String>("firstName").setOutputMarkupId(true));
		form.add(new TextField<String>("lastName").setOutputMarkupId(true));
		form.add(new TextField<String>("gender").setOutputMarkupId(true));
		form.add(new TextField<String>("age").setOutputMarkupId(true));
		form.add(newSubmitButton("submit", form));
	}

	protected Component newSubmitButton(final String id, final Form<?> form)
	{
		return new AjaxButton(id, form)
		{
			private static final long serialVersionUID = 1L;

			@Override
			public void onSubmit(final AjaxRequestTarget target, final Form<?> form)
			{
				EditPersonPanel.this.onSubmit(target, form);
			}
		}.setOutputMarkupId(true);
	}

	protected void onSubmit(final AjaxRequestTarget target, final Form<?> form)
	{
	}

}
