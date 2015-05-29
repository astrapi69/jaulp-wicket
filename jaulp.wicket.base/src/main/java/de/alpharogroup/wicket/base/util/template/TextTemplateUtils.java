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
	 * Converts the given {@link Set} of {@link StringTextValue} to a javascript array.
	 *
	 * @param settings the settings
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
