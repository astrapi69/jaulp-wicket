package org.jaulp.wicket.behaviors.toastr;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import lombok.Getter;

import org.apache.commons.lang.StringUtils;
import org.apache.wicket.request.resource.ResourceReference;
import org.apache.wicket.util.lang.Args;
import org.apache.wicket.util.template.PackageTextTemplate;
import org.apache.wicket.util.template.TextTemplate;
import org.jaulp.wicket.behaviors.util.TextTemplateUtils;

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
	 * Gets the settings.
	 *
	 * @return the settings
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
	 * @param settings the settings
	 */
	public ToastJsGenerator(ToastrSettings settings)
	{		
		this.settings = Args.notNull(settings, "settings");
	}

	/**
	 * Generate toastr js.
	 *
	 * @param settings the settings
	 * @return the string
	 */
	public String generateToastrJs(ToastrSettings settings)
	{
		return generateToastrJs(TOASTR_TEMPLATE, settings);
	}

	/**
	 * Generate toastr js.
	 *
	 * @param textTemplate the text template
	 * @param settings the settings
	 * @return the string
	 */
	public String generateToastrJs(final TextTemplate textTemplate, ToastrSettings settings)
	{
		final Map<String, Object> variables = new HashMap<>();
		variables.put("command", getCommand(settings));
		variables.put("closeButton", settings.isCloseButton());
		variables.put("debug", settings.isDebug());
		variables.put("newestOnTop", settings.isNewestOnTop());
		variables.put("progressBar", settings.isProgressBar());
		TextTemplateUtils.setVariableWithSingleQuotationMarks("positionClass", settings
			.getPositionClass().getPosition(), variables);
		variables.put("preventDuplicates", settings.isPreventDuplicates());
		TextTemplateUtils.setVariableWithSingleQuotationMarks("onclick", settings.getOnclick(),
			variables);
		TextTemplateUtils.setVariableWithSingleQuotationMarks("showDuration",
			settings.getShowDuration(), variables);
		TextTemplateUtils.setVariableWithSingleQuotationMarks("hideDuration",
			settings.getHideDuration(), variables);
		TextTemplateUtils.setVariableWithSingleQuotationMarks("timeOut", settings.getTimeOut(),
			variables);
		TextTemplateUtils.setVariableWithSingleQuotationMarks("extendedTimeOut",
			settings.getExtendedTimeOut(), variables);
		TextTemplateUtils.setVariableWithSingleQuotationMarks("showEasing", settings
			.getShowEasing().getValue(), variables);
		TextTemplateUtils.setVariableWithSingleQuotationMarks("hideEasing", settings
			.getHideEasing().getValue(), variables);
		TextTemplateUtils.setVariableWithSingleQuotationMarks("showMethod", settings
			.getShowMethod().getValue(), variables);
		TextTemplateUtils.setVariableWithSingleQuotationMarks("hideMethod", settings
			.getHideMethod().getValue(), variables);
		variables.put("tapToDismiss", settings.isTapToDismiss());
		textTemplate.interpolate(variables);
		return textTemplate.asString();
	}

	/**
	 * Gets the command.
	 *
	 * @param settings the settings
	 * @return the command
	 */
	public String getCommand(ToastrSettings settings)
	{
		StringBuilder sb = new StringBuilder();
		sb.append("toastr.");
		sb.append(settings.getToastrType().getType());
		sb.append("('");
		sb.append(settings.getNotificationContent());
		sb.append("'");
		if (StringUtils.isNotEmpty(settings.getNotificationTitle()))
		{
			sb.append(", '");
			sb.append(settings.getNotificationTitle());
			sb.append("'");
		}
		sb.append(")");
		return sb.toString();
	}
}
