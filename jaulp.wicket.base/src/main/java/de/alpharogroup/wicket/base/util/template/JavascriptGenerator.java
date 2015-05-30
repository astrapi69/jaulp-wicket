package de.alpharogroup.wicket.base.util.template;

import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import lombok.Getter;
import lombok.Setter;

import org.apache.wicket.util.lang.Args;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Base class for javascript generation for the
 * {@link de.alpharogroup.wicket.base.util.template.StringTextTemplate}.
 *
 * @param <S>
 *            the generic type of the Settings object
 */
@Getter
@Setter
public class JavascriptGenerator<S extends Settings> implements Serializable
{

	/** The serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The Constant COMPONENT_ID. */
	public static final String COMPONENT_ID = "componentId";

	/** The Constant DOCUMENT_READY_FUNCTION_PREFIX. */
	public static final String DOCUMENT_READY_FUNCTION_PREFIX = "$(document).ready(function() {";

	/** The Constant DOCUMENT_READY_FUNCTION_SUFFIX. */
	public static final String DOCUMENT_READY_FUNCTION_SUFFIX = "})";

	/** The LOGGER. */
	protected static final Logger LOGGER = LoggerFactory.getLogger(JavascriptGenerator.class
		.getName());

	/** The component id. */
	private String componentId;

	/** The with component id. */
	private boolean withComponentId;

	/** The method name. */
	private String methodName;

	/** The settings. */
	private S settings;

	/**
	 * Instantiates a new javascript generator.
	 *
	 * @param settings
	 *            the settings
	 */
	public JavascriptGenerator(S settings)
	{
		this.settings = Args.notNull(settings, "settings");
	}

	public String generateJs()
	{
		return generateJs(getSettings(), getMethodName());
	}

	/**
	 * Generate the javascript code.
	 *
	 * @param settings
	 *            the settings
	 * @param methodName
	 *            the method name
	 * @return the string
	 */
	public String generateJs(final Settings settings, final String methodName)
	{
		// 1. Create an empty map...
		final Map<String, Object> variables = initializeVariables(settings.asSet());
		// 4. Generate the js template with the map and the method name...
		String stringTemplateContent = generateJavascriptTemplateContent(variables, methodName);
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
	 * Generates the javascript template code from the given map and the given method name that will
	 * be used to interpolate with the values of the given map.
	 *
	 * @param variables
	 *            the map with the javascript options.
	 * @param methodName
	 *            The method name.
	 * @return The generated javascript from the given map and the given method name.
	 */
	protected String generateJavascriptTemplateContent(final Map<String, Object> variables,
		String methodName)
	{
		StringBuilder sb = new StringBuilder();
		sb.append(DOCUMENT_READY_FUNCTION_PREFIX).append("\n").append("$('#${")
			.append(COMPONENT_ID).append("}')").append(".").append(methodName).append("(");
		if (1 < variables.size())
		{
			sb.append("{\n");
			int count = 1;
			Object localComponentId = variables.get(COMPONENT_ID);
			variables.remove(COMPONENT_ID);
			for (Map.Entry<String, Object> entry : variables.entrySet())
			{
				String key = entry.getKey();
				sb.append(key).append(": ${").append(key).append("}");
				if (count < variables.size())
				{
					sb.append(",\n");
				}
				else
				{
					sb.append("\n");
				}
				count++;
			}
			variables.put(COMPONENT_ID, localComponentId);
			sb.append("}");
		}
		sb.append(");").append("\n").append(DOCUMENT_READY_FUNCTION_SUFFIX);
		return sb.toString();
	}

	/**
	 * Sets the values to the map. If the default value is set than it will not be added to the map
	 * for later not to generate js for it.
	 *
	 * @param allSettings
	 *            All settings as a list of StringTextValue(s).
	 * @return the map
	 */
	protected Map<String, Object> initializeVariables(final Set<StringTextValue<?>> allSettings)
	{
		final Map<String, Object> variables = new HashMap<>();

		// 2. put the component id that is the initiator for the js code...
		if (withComponentId)
		{
			variables.put(JavascriptGenerator.COMPONENT_ID, componentId);
		}
		for (StringTextValue<?> textValue : allSettings)
		{
			if (!textValue.isDefaultValue())
			{
				switch (textValue.getType())
				{
					case STRING :
						if (textValue.getQuotationMarkType().equals(QuotationMarkType.NONE))
						{
							variables.put(textValue.getName(), textValue.getValue());
							break;
						}
						TextTemplateUtils.setVariableWithSingleQuotationMarks(textValue.getName(),
							textValue.getValue(), variables);
						break;
					case ENUM :
						TextTemplateUtils.setVariableWithSingleQuotationMarks(textValue.getName(),
							((ValueEnum)textValue.getValue()).getValue(), variables);
						break;
					case STRING_INTEGER :
						TextTemplateUtils.setVariableWithSingleQuotationMarks(textValue.getName(),
							textValue.getValue(), variables);
						break;
					default :
						variables.put(textValue.getName(), textValue.getValue());
						break;
				}
			}
		}
		return variables;
	}

}
