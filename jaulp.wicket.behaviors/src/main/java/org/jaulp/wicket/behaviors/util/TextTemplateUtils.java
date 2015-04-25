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
