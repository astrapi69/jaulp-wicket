package org.jaulp.wicket.components.i18n.link;
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