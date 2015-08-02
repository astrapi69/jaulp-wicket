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
package de.alpharogroup.wicket.components.examples.notifications;

import org.apache.wicket.Application;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.extensions.ajax.markup.html.IndicatingAjaxButton;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import de.alpharogroup.locale.ResourceBundleKey;
import de.alpharogroup.wicket.base.util.resource.ResourceModelFactory;
import de.alpharogroup.wicket.components.buttons.ButtonPanel;
import de.alpharogroup.wicket.js.addon.pnotify.PnotifyJsGenerator;
import de.alpharogroup.wicket.js.addon.pnotify.PnotifyJsReference;
import de.alpharogroup.wicket.js.addon.pnotify.PnotifySettings;
import de.alpharogroup.wicket.js.addon.toastr.Position;
import de.alpharogroup.wicket.js.addon.toastr.ToastJsGenerator;
import de.alpharogroup.wicket.js.addon.toastr.ToastrResourceReference;
import de.alpharogroup.wicket.js.addon.toastr.ToastrSettings;

public class NotificationExamplesPanel extends Panel
{

	/**
	 * The serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	public NotificationExamplesPanel(final String id, final IModel<?> model)
	{
		super(id, model);
		final IModel<String> labelModel = ResourceModelFactory
			.newResourceModel(
				ResourceBundleKey.builder().key("button.toastr.label")
					.defaultValue("Show toastr notice.").build(), this);
		final Form<Object> form = new Form<Object>("form");
		add(form);
		form.add(new ButtonPanel("toastrButtonPanel", labelModel, form)
		{

			/**
			 * The serialVersionUID
			 */
			private static final long serialVersionUID = 1L;

			@Override
			protected Button newButton(final String id)
			{
				final IndicatingAjaxButton indicatingAjaxButton = new IndicatingAjaxButton(id,
					getForm())
				{
					/**
					 * The serialVersionUID.
					 */
					private static final long serialVersionUID = 1L;

					@Override
					protected void onError(final AjaxRequestTarget target, final Form<?> form)
					{
					}

					@Override
					protected void onSubmit(final AjaxRequestTarget target, final Form<?> form)
					{
						target.add(form);
						final ToastJsGenerator jsGenerator = new ToastJsGenerator();

						final ToastrSettings settings = jsGenerator.getSettings();
						settings.getPositionClass().setValue(Position.TOP_RIGHT);
						settings.getNotificationContent().setValue("This is a notification");
						final String js = jsGenerator.generateJs();
						target.prependJavaScript(js);
					}
				};
				indicatingAjaxButton.add(new AttributeAppender("class", Model
					.of(" btn btn-primary")));
				return indicatingAjaxButton;
			}
		});
		final IModel<String> pnotifyLabelModel = ResourceModelFactory.newResourceModel(
			ResourceBundleKey.builder().key("button.label").defaultValue("Show pnotify notice.")
				.build(), this);
		form.add(new ButtonPanel("pnotifyButtonPanel", pnotifyLabelModel, form)
		{

			/**
			 * The serialVersionUID
			 */
			private static final long serialVersionUID = 1L;

			@Override
			protected Button newButton(final String id)
			{
				final IndicatingAjaxButton indicatingAjaxButton = new IndicatingAjaxButton(id,
					getForm())
				{
					/**
					 * The serialVersionUID.
					 */
					private static final long serialVersionUID = 1L;

					@Override
					protected void onError(final AjaxRequestTarget target, final Form<?> form)
					{
					}

					@Override
					protected void onSubmit(final AjaxRequestTarget target, final Form<?> form)
					{
						target.add(form);
						final PnotifySettings pnotifySettings = PnotifySettings.builder().build();
						pnotifySettings.getTitle().setValue("Test title");
						pnotifySettings.getText().setValue("a text");
						final PnotifyJsGenerator pnotifyJsGenerator = new PnotifyJsGenerator(
							pnotifySettings);
						final String js = pnotifyJsGenerator.generateJs();
						target.prependJavaScript(js);
					}
				};
				indicatingAjaxButton.add(new AttributeAppender("class", Model
					.of(" btn btn-primary")));
				return indicatingAjaxButton;
			}
		});
	}

	@Override
	public void renderHead(final IHeaderResponse response)
	{
		super.renderHead(response);
		response.render(JavaScriptHeaderItem.forReference(Application.get()
			.getJavaScriptLibrarySettings().getJQueryReference()));
		response.render(JavaScriptHeaderItem.forReference(ToastrResourceReference.INSTANCE));
		response.render(JavaScriptHeaderItem.forReference(PnotifyJsReference.INSTANCE));
	}

}
