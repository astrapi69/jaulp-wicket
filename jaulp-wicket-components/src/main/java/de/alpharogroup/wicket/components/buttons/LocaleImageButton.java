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
package de.alpharogroup.wicket.components.buttons;

import java.util.Locale;

import org.apache.wicket.markup.html.form.ImageButton;
import org.apache.wicket.request.resource.ResourceReference;

/**
 * The Class {@link LocaleImageButton} sets the locale to the given locale.
 * 
 * @author Asterios Raptis
 */
public class LocaleImageButton extends ImageButton
{

	/**
	 * The serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;

	/** The locale. */
	private Locale locale;

	/**
	 * Instantiates a new {@link LocaleImageButton}.
	 * 
	 * @param id
	 *            the id
	 * @param resourceReference
	 *            the resource reference
	 * @param locale
	 *            the locale
	 */
	public LocaleImageButton(final String id, final ResourceReference resourceReference,
		final Locale locale)
	{
		super(id, resourceReference);
		if (locale == null)
		{
			throw new IllegalArgumentException("Locale argument should not be null.");
		}
		this.locale = locale;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void onSubmit()
	{
		if (locale != null)
		{
			getSession().setLocale(locale);
		}
	}

}
