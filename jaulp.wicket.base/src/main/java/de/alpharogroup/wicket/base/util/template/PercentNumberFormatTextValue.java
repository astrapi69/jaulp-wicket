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

import java.text.NumberFormat;

import org.apache.commons.lang3.StringUtils;
import org.apache.wicket.util.lang.Args;
import org.apache.log4j.Logger;

/**
 * A StringTextValue that formats the given Integer to percent. Posible values are between 0 to 100.
 */
public class PercentNumberFormatTextValue extends StringTextValue<String>
{

	/** The serialVersionUID. */
	private static final long serialVersionUID = 1L;
	/** The Constant logger. */
	protected static final Logger LOGGER = Logger.getLogger(PercentNumberFormatTextValue.class
		.getName());

	/**
	 * Instantiates a new {@link PercentNumberFormatTextValue} object.
	 *
	 * @param name the name
	 */
	public PercentNumberFormatTextValue(final String name)
	{
		super(name, null);
	}

	/**
	 * Instantiates a new {@link PercentNumberFormatTextValue} object.
	 *
	 * @param name the name
	 * @param value the value
	 */
	public PercentNumberFormatTextValue(final String name, final Integer value)
	{
		super(name, NumberFormat.getPercentInstance().format(
			(double)checkQuietly(name, value) / 100), StringTextType.STRING_INTEGER);
	}

	/**
	 * Checks the given value if it is between 0 to 100 quietly. If not a default value from 50 will be set.
	 *
	 * @param name the name
	 * @param value the value
	 * @return the integer
	 */
	private static Integer checkQuietly(final String name, final Integer value)
	{
		Integer val = 50;
		try
		{
			val = Args.withinRange(0, 100, value, name);
		}
		catch (IllegalArgumentException e)
		{
			LOGGER
				.error(String
					.format(
						"Given argument '%s' must have a value within [%s,%s], but was %s. Default value 50% will be set.",
						name, 0, 100, value));
		}
		return val;

	}

	/**
	 * Check string.
	 *
	 * @param value the value
	 * @return the integer
	 */
	private Integer checkString(final String value)
	{
		Integer val = 50;
		if (value != null && !value.isEmpty())
		{
			if (value.endsWith("%"))
			{
				String sVal = value.substring(0, value.length() - 1);
				if (StringUtils.isNumeric(sVal))
				{
					val = Integer.valueOf(sVal);
				}
			}
			else
			{
				if (StringUtils.isNumeric(value))
				{
					val = Integer.valueOf(value);
				}

			}
		}
		return val;
	}

	/**
	 * Sets the value.
	 *
	 * @param value
	 *            the value
	 * @return the string text value
	 */
	public StringTextValue<String> setValue(final Integer value)
	{
		String percentFormatted = getPercentFormatted(value);
		return setValue(percentFormatted);
	}

	/**
	 * Gets the percent formatted.
	 *
	 * @param value the value
	 * @return the percent formatted
	 */
	private String getPercentFormatted(final Integer value)
	{
		Integer val = checkQuietly(getName(), value);
		return NumberFormat.getPercentInstance().format((double)val / 100);
	}

	/** 
	 * {@inheritDoc}
	 */
	@Override
	public StringTextValue<String> setValue(final String value)
	{
		String val = getPercentFormatted(checkString(value));
		return super.setValue(val);
	}
}
