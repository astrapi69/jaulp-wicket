package org.jaulp.wicket.base.util.properties;

import java.util.List;

import net.sourceforge.jaulp.locale.PropertiesKeysListResolver;
import net.sourceforge.jaulp.locale.ResourceBundleKey;

import org.apache.wicket.Component;
import org.jaulp.wicket.base.util.resource.ResourceModelFactory;

/**
 * The Class ComponentPropertiesKeysListResolver creates a list with the properties keys
 * from the given list with the given prefix and optionally a suffix. This class
 * is usefull for get Properties that have a properties key prefix for instance:
 * properties key prefix='infringement.list.entry' and the properties key prefix='label'.
 * The values list contains:
 * a ResourceBundleKey object with the key '1' and with parameters '7'
 * and a second ResourceBundleKey object with the key '2' with no parameters.
 * The properties file could look something like this: 
 * infringement.list.entry.1.label = foo {0}
 * infringement.list.entry.2.label = bar
 */
public class ComponentPropertiesKeysListResolver extends
		PropertiesKeysListResolver<ResourceBundleKey> {

	/** The relative component used for lookups. */
	private Component component;

	/**
	 * Instantiates a new properties list view renderer.
	 * 
	 * @param propertiesKeyPrefix
	 *            the properties key prefix
	 * @param component
	 *            the component
	 * @param values
	 *            the values
	 */
	public ComponentPropertiesKeysListResolver(String propertiesKeyPrefix,
			Component component, List<ResourceBundleKey> values) {
		this(propertiesKeyPrefix, null, component, values);
	}

	/**
	 * Instantiates a new properties list view renderer.
	 * 
	 * @param propertiesKeyPrefix
	 *            the properties key prefix
	 * @param propertiesKeySuffix
	 *            the properties key suffix
	 * @param component
	 *            the component
	 * @param values
	 *            the values
	 */
	public ComponentPropertiesKeysListResolver(String propertiesKeyPrefix,
			String propertiesKeySuffix, Component component,
			List<ResourceBundleKey> values) {
		super(propertiesKeyPrefix, propertiesKeySuffix, values);
		this.component = component;
	}

	/**
	 * Gets the display value.
	 * 
	 * @param resourceBundleKey
	 *            the {@link ResourceBundleKey} object
	 * @return the display value
	 */
	public String getDisplayValue(final ResourceBundleKey resourceBundleKey) {
		return ResourceModelFactory.newResourceModel(
				getPropertiesKey(resourceBundleKey.getKey()), 
				resourceBundleKey.getParameters(),
				component, 
				resourceBundleKey.getDefaultValue())
				.getObject();
	}
}