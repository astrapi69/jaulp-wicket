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
package de.alpharogroup.wicket.components.examples.buttons;

import java.util.Locale;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.request.resource.PackageResourceReference;

import de.alpharogroup.io.annotations.ImportResource;
import de.alpharogroup.io.annotations.ImportResources;
import de.alpharogroup.locale.Locales;
import de.alpharogroup.wicket.components.buttons.LocaleImageButton;
import de.alpharogroup.wicket.components.examples.basepage.ApplicationBasePanel;

/**
 * The Class LocaleMenuPanel.
 * 
 * @author Asterios Raptis
 */
@ImportResources(resources = { @ImportResource(resourceName = "LocaleMenuPanel.css", resourceType = "css", index = 1) })
public class LocaleMenuPanel extends ApplicationBasePanel<Object>
{

	/**
	 * The serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new locale menu panel.
	 * 
	 * @param id
	 *            the id
	 */
	public LocaleMenuPanel(final String id)
	{
		super(id, null);

		final Form<LocaleImageButton> form = new Form<LocaleImageButton>("form");
		add(form);

		final LocaleImageButton germanyLocaleButton = new LocaleImageButton("germanyLocaleButton",
			new PackageResourceReference(LocaleMenuPanel.class, "germany.gif"), Locale.GERMANY);
		form.add(germanyLocaleButton);

		final LocaleImageButton englishLocaleButton = new LocaleImageButton("englishLocaleButton",
			new PackageResourceReference(LocaleMenuPanel.class, "britain.gif"), Locale.ENGLISH);
		form.add(englishLocaleButton);

		final LocaleImageButton greekLocaleButton = new LocaleImageButton("greekLocaleButton",
			new PackageResourceReference(LocaleMenuPanel.class, "hellas.gif"), Locales.HELLENIC);
		form.add(greekLocaleButton);

	}

}
