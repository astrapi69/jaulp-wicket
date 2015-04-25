package org.jaulp.wicket.behaviors.util;

import java.util.Map;

public class TextTemplateUtils
{


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
}
