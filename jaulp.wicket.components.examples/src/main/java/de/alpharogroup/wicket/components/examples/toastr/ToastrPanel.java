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
package de.alpharogroup.wicket.components.examples.toastr;

import net.sourceforge.jaulp.locale.ResourceBundleKey;

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
import org.jaulp.wicket.base.util.resource.ResourceModelFactory;
import org.jaulp.wicket.behaviors.toastr.Position;
import org.jaulp.wicket.behaviors.toastr.ToastJsGenerator;
import org.jaulp.wicket.behaviors.toastr.ToastrSettings;

import de.alpharogroup.wicket.components.buttons.ButtonPanel;

public class ToastrPanel extends Panel
{

	/**
	 * The serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	public ToastrPanel(String id, IModel<?> model)
	{
		super(id, model);
		IModel<String> labelModel = ResourceModelFactory.newResourceModel(ResourceBundleKey
			.builder().key("button.label").defaultValue("Show Toast").build(), this);
		Form<Object> form = new Form<Object>("form");
		add(form);
		form.add(new ButtonPanel("buttonPanel", labelModel, form)
		{

			/**
			 * The serialVersionUID
			 */
			private static final long serialVersionUID = 1L;

			@Override
			protected Button newButton(String id)
			{
				IndicatingAjaxButton indicatingAjaxButton = new IndicatingAjaxButton(id, getForm())
				{
					/**
					 * The serialVersionUID.
					 */
					private static final long serialVersionUID = 1L;

					@Override
					protected void onSubmit(final AjaxRequestTarget target, final Form<?> form)
					{
						target.add(form);
						ToastJsGenerator jsGenerator = new ToastJsGenerator();

						ToastrSettings settings = new ToastrSettings();
						settings.setPositionClass(Position.TOP_RIGHT);
						settings.setNotificationContent("This is a notification");
						String js = jsGenerator.generateToastrJs(settings);
						target.prependJavaScript(js);
					}

					@Override
					protected void onError(AjaxRequestTarget target, Form<?> form)
					{
					}
				};
				indicatingAjaxButton.add(new AttributeAppender("class", Model
					.of(" btn btn-primary")));
				return indicatingAjaxButton;
			}
		});
	}

	@Override
	public void renderHead(IHeaderResponse response)
	{
		super.renderHead(response);
		response.render(JavaScriptHeaderItem.forReference(Application.get()
			.getJavaScriptLibrarySettings().getJQueryReference()));
		response
			.render(JavaScriptHeaderItem.forReference(ToastJsGenerator.TOASTR_PLUGIN_REFERENCE));
	}

}
