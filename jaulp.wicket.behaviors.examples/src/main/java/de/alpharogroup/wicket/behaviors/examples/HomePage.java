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
package de.alpharogroup.wicket.behaviors.examples;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.IModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import de.alpharogroup.locale.ResourceBundleKey;
import de.alpharogroup.wicket.base.util.application.ApplicationExtensions;
import de.alpharogroup.wicket.base.util.resource.ResourceModelFactory;
import de.alpharogroup.wicket.behaviors.FaviconBehavior;
import de.alpharogroup.wicket.behaviors.JavascriptAppenderBehavior;
import de.alpharogroup.wicket.behaviors.JavascriptResourceReferenceAppenderBehavior;
import de.alpharogroup.wicket.behaviors.components.MailtoLabel;
import de.alpharogroup.wicket.behaviors.models.MailtoModel;

/**
 * Homepage
 */
public class HomePage extends WebPage
{

	private static final long serialVersionUID = 1L;

	// Add any page properties or variables here

	/**
	 * Constructor that is invoked when page is invoked without a session.
	 *
	 * @param parameters
	 *            Page parameters
	 */
	public HomePage(final PageParameters parameters)
	{
		final IModel<String> mailtoAddresModel = ResourceModelFactory.newResourceModel(
			ResourceBundleKey.builder().key("mailtoAddresModel.value").defaultValue("").build(),
			this);

		final IModel<String> mailtoViewModel = ResourceModelFactory
			.newResourceModel(ResourceBundleKey.builder().key("mailtoViewModel.value")
				.defaultValue("").build(), this);

		final MailtoModel mailtoModel = new MailtoModel(mailtoAddresModel, mailtoViewModel);

		add(new MailtoLabel("mailtoLabel", mailtoModel));

		final Button button = new Button("button")
		{

			/**
			 *
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void onSubmit()
			{

			}

		};
		add(new JavascriptResourceReferenceAppenderBehavior(this.getClass(), "functions.js", "func"));
		add(JavascriptAppenderBehavior.builder().id("xy").javascript("alertnow();").build());

		add(new Link<String>("focusRequestExamplePage")
		{

			/**
			 *
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void onClick()
			{
				setResponsePage(FocusRequestExamplePage.class);
			}
		});

		add(button);
		add(new FaviconBehavior());

		final AjaxLink<Void> link01 = new AjaxLink<Void>("link01")
		{
			private static final long serialVersionUID = 1L;

			@Override
			public void onClick(final AjaxRequestTarget target)
			{

			}
		};
		link01.add(new AttributeAppender("class", "navbarlink"));
		add(link01);
		final AjaxLink<Void> link02 = new AjaxLink<Void>("link02")
		{
			private static final long serialVersionUID = 1L;

			@Override
			public void onClick(final AjaxRequestTarget target)
			{

			}
		};
		add(link02);

	}

	@Override
	public void renderHead(final IHeaderResponse response)
	{
		super.renderHead(response);
		response.render(JavaScriptHeaderItem.forReference(ApplicationExtensions
			.getJQueryReference()));

	}


}
