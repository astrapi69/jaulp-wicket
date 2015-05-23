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
package de.alpharogroup.wicket.behaviors.toastr;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import lombok.Getter;

import org.apache.commons.lang.StringUtils;
import org.apache.wicket.request.resource.ResourceReference;
import org.apache.wicket.util.lang.Args;
import org.apache.wicket.util.template.PackageTextTemplate;
import org.apache.wicket.util.template.TextTemplate;

import de.alpharogroup.wicket.base.util.template.TextTemplateUtils;

/**
 * The Class ToastJsGenerator generates the javascript with a ToastrSettings object.
 */
public class ToastJsGenerator implements Serializable
{

	/**
	 * The serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	/** The {@link ResourceReference} constant for the js and css-file from the toastr plugin. */
	public static final ResourceReference TOASTR_PLUGIN_REFERENCE = new ToastrResourceReference();

	/** The toastr template. */
	public static final TextTemplate TOASTR_TEMPLATE = new PackageTextTemplate(
		ToastJsGenerator.class, "toastr-template.js.tmpl");

	/**
	 * The settings.
	 */
	@Getter
	private ToastrSettings settings;

	/**
	 * Instantiates a new toast js generator.
	 */
	public ToastJsGenerator()
	{
	}

	/**
	 * Instantiates a new toast js generator.
	 *
	 * @param settings
	 *            the settings
	 */
	public ToastJsGenerator(ToastrSettings settings)
	{
		this.settings = Args.notNull(settings, "settings");
	}

	/**
	 * Generate toastr js.
	 *
	 * @param settings
	 *            the settings
	 * @return the string
	 */
	public String generateToastrJs(ToastrSettings settings)
	{
		return generateToastrJs(TOASTR_TEMPLATE, settings);		
//		return null;
	}

	/**
	 * Generate toastr js.
	 *
	 * @param textTemplate
	 *            the text template
	 * @param settings
	 *            the settings
	 * @return the string
	 */
	public String generateToastrJs(final TextTemplate textTemplate, ToastrSettings settings)
	{
		final Map<String, Object> variables = new HashMap<>();
		variables.put("command", getCommand(settings));
		variables.put("closeButton", settings.getCloseButton().getValue());
		variables.put("debug", settings.getDebug().getValue());
		variables.put("newestOnTop", settings.getNewestOnTop().getValue());
		variables.put("progressBar", settings.getProgressBar().getValue());
		TextTemplateUtils.setVariableWithSingleQuotationMarks("positionClass", settings
			.getPositionClass().getValue(), variables);
		variables.put("preventDuplicates", settings.getPreventDuplicates().getValue());
		TextTemplateUtils.setVariableWithSingleQuotationMarks("onclick", settings.getOnclick().getValue(),
			variables);
		TextTemplateUtils.setVariableWithSingleQuotationMarks("showDuration",
			settings.getShowDuration().getValue(), variables);
		TextTemplateUtils.setVariableWithSingleQuotationMarks("hideDuration",
			settings.getHideDuration().getValue(), variables);
		TextTemplateUtils.setVariableWithSingleQuotationMarks("timeOut", settings.getTimeOut().getValue(),
			variables);
		TextTemplateUtils.setVariableWithSingleQuotationMarks("extendedTimeOut",
			settings.getExtendedTimeOut().getValue(), variables);
		TextTemplateUtils.setVariableWithSingleQuotationMarks("showEasing", settings
			.getShowEasing().getValue(), variables);
		TextTemplateUtils.setVariableWithSingleQuotationMarks("hideEasing", settings
			.getHideEasing().getValue(), variables);
		TextTemplateUtils.setVariableWithSingleQuotationMarks("showMethod", settings
			.getShowMethod().getValue(), variables);
		TextTemplateUtils.setVariableWithSingleQuotationMarks("hideMethod", settings
			.getHideMethod().getValue(), variables);
		variables.put("tapToDismiss", settings.getTapToDismiss().getValue());
		textTemplate.interpolate(variables);
		return textTemplate.asString();
	}

	/**
	 * Gets the command.
	 *
	 * @param settings
	 *            the settings
	 * @return the command
	 */
	public String getCommand(ToastrSettings settings)
	{
		StringBuilder sb = new StringBuilder();
		sb.append("toastr.");
		sb.append(settings.getToastrType().getValue());
		sb.append("('");
		sb.append(settings.getNotificationContent().getValue());
		sb.append("'");
		if (StringUtils.isNotEmpty(settings.getNotificationTitle().getValue()))
		{
			sb.append(", '");
			sb.append(settings.getNotificationTitle().getValue());
			sb.append("'");
		}
		sb.append(")");
		return sb.toString();
	}
}
