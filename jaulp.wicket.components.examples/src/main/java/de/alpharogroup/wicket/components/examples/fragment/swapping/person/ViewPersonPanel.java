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

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxFallbackLink;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;

public class ViewPersonPanel extends Panel
{

	/**
	 * The serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	public ViewPersonPanel(final String id, final IModel<PersonBean> model)
	{
		super(id, model);
		setOutputMarkupId(true);
		setDefaultModel(model);
		add(newEditLink("editLink"));
		add(new Label("firstName").setOutputMarkupId(true));
		add(new Label("lastName").setOutputMarkupId(true));
		add(new Label("gender").setOutputMarkupId(true));
		add(new Label("age").setOutputMarkupId(true));
	}

	protected AjaxFallbackLink<Object> newEditLink(final String id)
	{
		return new AjaxFallbackLink<Object>(id)
		{
			private static final long serialVersionUID = 1L;

			@Override
			public void onClick(final AjaxRequestTarget target)
			{
				ViewPersonPanel.this.onSubmit(target);
			}
		};
	}

	protected void onSubmit(final AjaxRequestTarget target)
	{
	}

}
