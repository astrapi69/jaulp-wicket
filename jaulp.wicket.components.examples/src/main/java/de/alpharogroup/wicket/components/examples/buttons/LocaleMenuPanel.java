package de.alpharogroup.wicket.components.examples.buttons;

import java.util.Locale;

import net.sourceforge.jaulp.io.annotations.ImportResource;
import net.sourceforge.jaulp.io.annotations.ImportResources;
import net.sourceforge.jaulp.locale.Locales;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.request.resource.PackageResourceReference;
import org.jaulp.wicket.base.BasePanel;

import de.alpharogroup.wicket.components.buttons.LocaleImageButton;

/**
 * The Class LocaleMenuPanel.
 * 
 * @author Asterios Raptis
 */
@ImportResources(resources = { @ImportResource(resourceName = "LocaleMenuPanel.css", resourceType = "css", index = 1) })
public class LocaleMenuPanel extends BasePanel<Object>
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
		super(id);

		Form<LocaleImageButton> form = new Form<LocaleImageButton>("form");
		add(form);

		LocaleImageButton germanyLocaleButton = new LocaleImageButton("germanyLocaleButton",
			new PackageResourceReference(LocaleMenuPanel.class, "germany.gif"), Locale.GERMANY);
		form.add(germanyLocaleButton);

		LocaleImageButton englishLocaleButton = new LocaleImageButton("englishLocaleButton",
			new PackageResourceReference(LocaleMenuPanel.class, "britain.gif"), Locale.ENGLISH);
		form.add(englishLocaleButton);

		LocaleImageButton greekLocaleButton = new LocaleImageButton("greekLocaleButton",
			new PackageResourceReference(LocaleMenuPanel.class, "hellas.gif"), Locales.HELLENIC);
		form.add(greekLocaleButton);

	}

}
