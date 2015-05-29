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
package de.alpharogroup.wicket.base.util.template;

import java.util.Map;
import java.util.Set;

/**
 * The Class TextTemplateUtils.
 */
public class TextTemplateUtils
{

	/** The Constant COMPONENT_ID. */
	public static final String COMPONENT_ID = "componentId";

	/** The Constant DOCUMENT_READY_FUNCTION_PREFIX. */
	public static final String DOCUMENT_READY_FUNCTION_PREFIX = "$(document).ready(function() {";

	/** The Constant DOCUMENT_READY_FUNCTION_SUFFIX. */
	public static final String DOCUMENT_READY_FUNCTION_SUFFIX = "})";

	/**
	 * Sets the variable.
	 *
	 * @param variablename
	 *            the variablename
	 * @param object
	 *            the object
	 * @param variables
	 *            the variables
	 */
	public static void setVariable(String variablename, Object object,
		final Map<String, Object> variables)
	{
		if (object != null)
		{
			variables.put(variablename, object);
		}
		else
		{
			variables.put(variablename, "null");
		}
	}

	/**
	 * Sets a boolean variable without quotation marks.
	 *
	 * @param variablename
	 *            the variablename
	 * @param value
	 *            the boolean value to set.
	 * @param variables
	 *            the variables
	 */
	public static void setBooleanVariable(String variablename, boolean value,
		final Map<String, Object> variables)
	{
		variables.put(variablename, value);
	}

	/**
	 * Sets the variable with singe quotation marks.
	 *
	 * @param variablename
	 *            the variablename
	 * @param object
	 *            the object
	 * @param variables
	 *            the variables
	 */
	public static void setVariableWithSingleQuotationMarks(String variablename, Object object,
		final Map<String, Object> variables)
	{
		if (object != null)
		{
			variables.put(variablename, "'" + object + "'");
		}
		else
		{
			variables.put(variablename, "null");
		}
	}

	/**
	 * Sets the variable with singe quotation marks.
	 *
	 * @param variablename
	 *            the variablename
	 * @param object
	 *            the object
	 * @param variables
	 *            the variables
	 */
	public static void setVariableWithDoubleQuotationMarks(String variablename, Object object,
		final Map<String, Object> variables)
	{
		if (object != null)
		{
			variables.put(variablename, "\"" + object + "\"");
		}
		else
		{
			variables.put(variablename, "null");
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
	public static String generateJavascriptTemplateContent(final Map<String, Object> variables,
		String methodName)
	{
		StringBuilder sb = new StringBuilder();
		sb.append(DOCUMENT_READY_FUNCTION_PREFIX);
		sb.append("\n");
		sb.append("$('#${" + COMPONENT_ID + "}')");
		sb.append(".");
		sb.append(methodName);
		sb.append("(");
		if (1 < variables.size())
		{
			sb.append("{\n");
			int count = 1;
			Object componentId = variables.get(COMPONENT_ID);
			variables.remove(COMPONENT_ID);
			for (Map.Entry<String, Object> entry : variables.entrySet())
			{
				String key = entry.getKey();
				sb.append(key + ": ${" + key + "}");
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
			variables.put(COMPONENT_ID, componentId);
			sb.append("}");
		}
		sb.append(");");
		sb.append("\n");
		sb.append(DOCUMENT_READY_FUNCTION_SUFFIX);
		return sb.toString();
	}

	/**
	 * Sets the values to the map. If the default value is set than it will not be added to the map
	 * for later not to generate js for it.
	 * 
	 * @param variables
	 *            the map with the javascript options.
	 * @param allSettings
	 *            All settings as a list of StringTextValue(s).
	 */
	@SuppressWarnings("rawtypes")
	public static void initializeVariables(final Map<String, Object> variables,
		final Set<StringTextValue<?>> allSettings)
	{
		for (StringTextValue textValue : allSettings)
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
	}

	/**
	 * Converts the given {@link Set} of {@link StringTextValue} to a javascript array.
	 *
	 * @param settings
	 *            the settings
	 * @return the generated javascript array.
	 */
	public static String asJavascriptArray(Set<StringTextValue<?>> settings)
	{
		StringBuilder sb = new StringBuilder();
		sb.append("{\n");
		int count = 1;
		for (StringTextValue<?> textValue : settings)
		{
			switch (textValue.getType())
			{
				case STRING :
					if (textValue.getQuotationMarkType().equals(QuotationMarkType.NONE))
					{
						sb.append(textValue.getName());
						sb.append(":");
						sb.append(textValue.getValue());
						break;
					}
					sb.append(textValue.getName());
					sb.append(":");
					sb.append("\"" + textValue.getValue() + "\"");
					break;
				default :
					sb.append(textValue.getName());
					sb.append(":");
					sb.append(textValue.getValue());
					break;
			}
			if (count < settings.size())
			{
				sb.append(",\n");
			}
			count++;
		}
		sb.append("\n}");
		return sb.toString();
	}
}
