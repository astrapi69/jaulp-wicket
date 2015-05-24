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
package de.alpharogroup.wicket.behaviors.tooltipster;

import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.apache.wicket.Component;
import org.apache.wicket.request.resource.ResourceReference;
import org.apache.wicket.util.lang.Args;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.alpharogroup.wicket.base.util.template.Settings;
import de.alpharogroup.wicket.base.util.template.StringTextTemplate;
import de.alpharogroup.wicket.base.util.template.TextTemplateUtils;
import lombok.Getter;

/**
 * The Class TooltipsterJsGenerator generates the javascript with a TooltipsterSettings object.
 */
public class TooltipsterJsGenerator implements Serializable
{

	/**
	 * The serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	/** The LOGGER. */
	static final Logger LOGGER = LoggerFactory.getLogger(TooltipsterJsGenerator.class.getName());

	/**
	 * The {@link org.apache.wicket.request.resource.ResourceReference} constant for the js and
	 * css-file from the tooltipster plugin.
	 */
	public static final ResourceReference TOOLTIPSTER_PLUGIN_REFERENCE = new TooltipsterResourceReference();

	public static void main(String... args)
	{
		TooltipsterSettings tooltipsterSettings = new TooltipsterSettings();
		tooltipsterSettings.getAnimation().setValue("grow");
		tooltipsterSettings.getArrow().setValue(false);
		tooltipsterSettings.getContent().setValue("Loading...");
		TooltipsterJsGenerator generator = new TooltipsterJsGenerator(tooltipsterSettings, null);
		String result = generator.generateJs(tooltipsterSettings, "foo");
		System.out.println(result);
	}

	/**
	 * The settings.
	 */
	@Getter
	private final TooltipsterSettings settings;

	/**
	 * The component.
	 */
	private Component component;

	/**
	 * Instantiates a new tooltipster js generator.
	 *
	 * @param settings
	 *            the settings
	 */
	public TooltipsterJsGenerator(final TooltipsterSettings settings, final Component component)
	{
		this.settings = Args.notNull(settings, "settings");
		this.component = component;
	}

	/**
	 * Generates javascript from the settings and the component.
	 * 
	 * @return The generated javascript from the settings and the component.
	 */
	public String generateJs()
	{
		return generateJs(this.settings, component.getMarkupId());
	}

	/**
	 * Generate tooltipster js.
	 *
	 * @param settings
	 *            the settings
	 * @return the string
	 */
	public String generateJs(Settings settings, String componentId, final String methodName)
	{
		// 1. Create an empty map...
		final Map<String, Object> variables = new HashMap<>();
		// 2. put the component id that is the initiator for the js code...
		variables.put(TextTemplateUtils.COMPONENT_ID, componentId);
		// 3. Initialize the map with the settings...
		TextTemplateUtils.initializeVariables(variables, settings.asSet());
		// 4. Generate the js template with the map and the method name...
		String stringTemplateContent = TextTemplateUtils.generateJavascriptTemplateContent(
			variables, methodName);
		// 5. Create the StringTextTemplate with the generated template...
		StringTextTemplate stringTextTemplate = new StringTextTemplate(stringTemplateContent);
		// 6. Interpolate the template with the values of the map...
		stringTextTemplate.interpolate(variables);
		try
		{
			// 7. return it as String...
			return stringTextTemplate.asString();

		}
		finally
		{
			try
			{
				stringTextTemplate.close();
			}
			catch (IOException e)
			{
				LOGGER.error(e.getMessage(), e);
			}
		}
	}

	/**
	 * Generate tooltipster js.
	 *
	 * @param settings
	 *            the settings
	 * @return the string
	 */
	public String generateJs(Settings settings, String componentId)
	{
		return generateJs(settings, componentId, "tooltipster");
	}

}
