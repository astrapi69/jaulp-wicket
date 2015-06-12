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
package de.alpharogroup.wicket.behaviors.datetime;

import java.util.HashMap;
import java.util.Map;

import org.apache.wicket.Application;
import org.apache.wicket.Component;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.markup.head.OnLoadHeaderItem;
import org.apache.wicket.request.resource.JavaScriptResourceReference;
import org.apache.wicket.request.resource.ResourceReference;
import org.apache.wicket.util.template.PackageTextTemplate;
import org.apache.wicket.util.template.TextTemplate;

/**
 * This behavior adds the current time to a component.
 */
public class CurrentDatetimeBehavior extends Behavior
{

	/**
	 * The serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * The {@link org.apache.wicket.request.resource.ResourceReference} constant for the js-file
	 * from the jquery-datetime-plugin.
	 */
	public static final ResourceReference DATETIME_PLUGIN_REFERENCE = new JavaScriptResourceReference(
		CurrentDatetimeBehavior.class, "jquery-datetime-plugin.js");

	/** The component. */
	private Component component;

	/** The popupoverlay template. */
	private final TextTemplate datetimeTemplate = new PackageTextTemplate(
		CurrentDatetimeBehavior.class, "datetime-plugin.js.tmpl");

	/**
	 * Default constructor.
	 */
	public CurrentDatetimeBehavior()
	{
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void bind(final Component component)
	{
		super.bind(component);
		this.component = component;
		this.component.setOutputMarkupId(true);
	}

	/**
	 * Generate js.
	 *
	 * @param textTemplate
	 *            the text template
	 * @return the string
	 */
	protected String generateJS(final TextTemplate textTemplate)
	{
		final Map<String, Object> variables = new HashMap<>();
		variables.put("componentId", this.component.getMarkupId());
		textTemplate.interpolate(variables);
		return textTemplate.asString();
	}


	/**
	 * {@inheritDoc}
	 */
	@Override
	public void renderHead(Component component, final IHeaderResponse response)
	{
		super.renderHead(component, response);
		response.render(JavaScriptHeaderItem.forReference(Application.get()
			.getJavaScriptLibrarySettings().getJQueryReference()));
		response.render(JavaScriptHeaderItem
			.forReference(CurrentDatetimeBehavior.DATETIME_PLUGIN_REFERENCE));
		String js = generateJS(datetimeTemplate);
		System.out.println(js);
		response.render(OnLoadHeaderItem.forScript(generateJS(datetimeTemplate)));
	}

}
