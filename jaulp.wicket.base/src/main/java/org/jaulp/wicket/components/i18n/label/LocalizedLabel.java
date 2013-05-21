package org.jaulp.wicket.components.i18n.label;

import org.apache.wicket.Component;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.StringResourceModel;

/**
 * The Class LocalizedLabel initializes the Label with a StringResourceModel.
 */
public class LocalizedLabel extends Label {

	/**
	 * The serialVersionUID.
	 */
	private static final long serialVersionUID = 5198063608075182201L;

	/**
	 * Instantiates a new localized label.
	 *
	 * @param id the id
	 * @param resourceKey the resource key
	 * @param component the component
	 */
	public LocalizedLabel(final String id, final String resourceKey,
			final Component component) {
		super(id);
		// Get the localized model for the label from the properties file
		final StringResourceModel stringResourceModel = new StringResourceModel(
				resourceKey, component, null);
		setDefaultModel(stringResourceModel);
	}

	/**
	 * Instantiates a new localized label.
	 *
	 * @param id the id
	 * @param resourceKey the resource key
	 * @param component the component
	 * @param defaultValue the default value
	 */
	public LocalizedLabel(final String id, final String resourceKey,
			final Component component, final String defaultValue) {
		super(id);
		// Get the localized model for the label from the properties file
		final StringResourceModel stringResourceModel = new StringResourceModel(
				resourceKey, component, null, defaultValue);
		setDefaultModel(stringResourceModel);
	}

	/**
	 * Instantiates a new localized label.
	 *
	 * @param id the id
	 * @param resourceKey the resource key
	 * @param component the component
	 * @param parameters the parameters
	 */
	public LocalizedLabel(final String id, final String resourceKey,
			 final Component component, final Object... parameters) {
		super(id);
		// Get the localized model for the label from the properties file with
		// object parameters
		final StringResourceModel stringResourceModel = new StringResourceModel(
				resourceKey, component, null, parameters);

		setDefaultModel(stringResourceModel);
	}

	/**
	 * Instantiates a new localized label.
	 *
	 * @param id the id
	 * @param resourceKey the resource key
	 * @param component the component
	 * @param model the model
	 * @param defaultValue the default value
	 * @param parameters the parameters
	 */
	public LocalizedLabel(final String id, final String resourceKey, final Component component,
			final IModel<?> model, final String defaultValue, final Object... parameters) {
		super(id);
		// Get the localized model for the label from the properties file with
		// object parameters
		final StringResourceModel stringResourceModel = new StringResourceModel(
				resourceKey, component, model, defaultValue, parameters);

		setDefaultModel(stringResourceModel);
	}
	
	/**
	 * Instantiates a new localized label.
	 *
	 * @param id the id
	 * @param stringResourceModel the string resource model
	 */
	public LocalizedLabel(final String id, final StringResourceModel stringResourceModel) {
		super(id);
		setDefaultModel(stringResourceModel);
	}

}
