package org.jaulp.wicket.base.util.resource;

import org.apache.wicket.Component;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.StringResourceModel;

/**
 * A factory for creating StringResourceModel objects.
 */
public final class ResourceModelFactory {

	/**
	 * Factory method to create a new StringResourceModel from the given resource key.
	 *
	 * @param resourceKey the resource key
	 * @param component the component
	 * @return a new StringResourceModel as an IModel<String>
	 */
	public static IModel<String> newResourceModel(final String resourceKey,
			final Component component) {
		return newResourceModel(resourceKey, component, null, "");
	}
	
	/**
	 * Factory method to create a new StringResourceModel from the given resource key.
	 *
	 * @param resourceKey the resource key
	 * @param component the component
	 * @param defaultValue the default value
	 * @return the i model
	 */
	public static IModel<String> newResourceModel(final String resourceKey,
			final Component component, String defaultValue) {
		return newResourceModel(resourceKey, component, null, defaultValue);
	}
	
	/**
	 * Factory method to create a new StringResourceModel from the given resource key.
	 *
	 * @param resourceKey the resource key
	 * @param component the component
	 * @param parameters the parameters
	 * @return the i model
	 */
	public static IModel<String> newResourceModel(final String resourceKey, final Component component, final Object... parameters) {
		return newResourceModel(resourceKey, component, null, null, parameters);
	}
	
	/**
	 * Factory method to create a new StringResourceModel from the given resource key.
	 *
	 * @param resourceKey the resource key
	 * @param defaultValue the default value
	 * @param component the component
	 * @param parameters the parameters
	 * @return the i model
	 */
	public static IModel<String> newResourceModel(final String resourceKey, String defaultValue, final Component component, final Object... parameters) {
		return newResourceModel(resourceKey, component, null, null, parameters);
	}

	/**
	 * Factory method to create a new StringResourceModel from the given resource key.
	 *
	 * @param resourceKey the resource key
	 * @param model the model
	 * @param parameters the parameters
	 * @return the i model
	 */
	public static IModel<String> newResourceModel(final String resourceKey,
			final IModel<?> model, final Object... parameters) {
		return newResourceModel(resourceKey, null, model, null, parameters);
	}

	/**
	 * Factory method to create a new StringResourceModel from the given resource key.
	 *
	 * @param resourceKey the resource key
	 * @param component the component
	 * @param model the model
	 * @param parameters the parameters
	 * @return the i model
	 */
	public static IModel<String> newResourceModel(final String resourceKey,
			final Component component, final IModel<?> model,
			final Object... parameters) {
		return newResourceModel(resourceKey, component, model, null, parameters);
	}

	/**
	 * Factory method to create a new StringResourceModel from the given resource key.
	 *
	 * @param resourceKey the resource key
	 * @param model the model
	 * @param defaultValue the default value
	 * @param parameters the parameters
	 * @return the i model
	 */
	public static IModel<String> newResourceModel(final String resourceKey,
			final IModel<?> model, final String defaultValue,
			final Object... parameters) {
		return newResourceModel(resourceKey, null, model, defaultValue, parameters);
	}

	/**
	 * Factory method to create a new StringResourceModel from the given resource key.
	 *
	 * @param resourceKey the resource key
	 * @param component the component
	 * @param model the model
	 * @param defaultValue the default value
	 * @param parameters the parameters
	 * @return the i model
	 */
	public static IModel<String> newResourceModel(final String resourceKey,
			final Component component, final IModel<?> model,
			final String defaultValue, final Object... parameters) {
		return new StringResourceModel(resourceKey, component, model,
				defaultValue, parameters);
	}

}
