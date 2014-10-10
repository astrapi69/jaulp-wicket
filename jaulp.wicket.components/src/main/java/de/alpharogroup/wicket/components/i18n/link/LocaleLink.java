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

/**
 * The Class LocaleLink.
 * 
 * @param <T>
 *            the generic type
 * @author Asterios Raptis
 */
public class LocaleLink<T> extends Link<T> {
	/**
	 * The serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;

	/** The locale. */
	private Locale locale;

	/**
	 * Instantiates a new locale link.
	 * 
	 * @param id
	 *            the id
	 * @param locale
	 *            the locale to set.
	 */
	public LocaleLink(final String id, final Locale locale) {
		super(id);
		if (null == locale) {
			throw new IllegalArgumentException(
					"Locale argument should not be null.");
		}
		this.locale = locale;
	}

	/**
	 * {@inheritDoc}.
	 * 
	 * @see org.apache.wicket.markup.html.link.Link#onClick()
	 */
	@Override
	public void onClick() {
		if (null != locale) {
			getSession().setLocale(locale);
		}
	}

}