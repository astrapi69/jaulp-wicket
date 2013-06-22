package org.jaulp.wicket.components.buttons;

import java.util.Locale;

import org.apache.wicket.markup.html.form.ImageButton;
import org.apache.wicket.request.resource.ResourceReference;

/**
 * The Class LocaleImageButton.
 * 
 * @author Asterios Raptis
 */
public class LocaleImageButton extends ImageButton {

	/**
	 * The serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;

	/** The locale. */
	private Locale locale;

	/**
	 * Instantiates a new locale image button.
	 * 
	 * @param id
	 *            the id
	 * @param resourceReference
	 *            the resource reference
	 * @param locale
	 *            the locale
	 */
	public LocaleImageButton(final String id,
			final ResourceReference resourceReference, final Locale locale) {
		super(id, resourceReference);
		if (locale == null) {
			throw new IllegalArgumentException(
					"Locale argument should not be null.");
		}
		this.locale = locale;
	}

	/**
	 * {@inheritDoc}.
	 * 
	 * @see org.apache.wicket.markup.html.form.Button#onSubmit()
	 */
	@Override
	public void onSubmit() {
		if (null != locale) {
			getSession().setLocale(locale);
		}
	}

}
