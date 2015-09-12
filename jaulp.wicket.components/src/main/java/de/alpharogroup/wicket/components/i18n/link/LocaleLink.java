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
package de.alpharogroup.wicket.components.i18n.link;

import java.util.Locale;

import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.util.lang.Args;

/**
 * The Class {@link LocaleLink} can set a specific {@link Locale}.
 * 
 * @param <T>
 *            the generic type of model object
 * @author Asterios Raptis
 */
public class LocaleLink<T> extends Link<T>
{
	/**
	 * The serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;

	/** The locale. */
	private Locale locale;

	/**
	 * Instantiates a new {@link LocaleLink}.
	 * 
	 * @param id
	 *            the id
	 * @param locale
	 *            the locale to set.
	 */
	public LocaleLink(final String id, final Locale locale)
	{
		super(id);
		Args.notNull(locale, "locale");
		this.locale = locale;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void onClick()
	{
		if (null != locale)
		{
			getSession().setLocale(locale);
		}
	}

}