package org.jaulp.wicket.base.util.properties;

import java.util.List;

import net.sourceforge.jaulp.locale.PropertiesKeysListResolver;

import org.apache.wicket.Component;
import org.apache.wicket.model.IModel;
import org.jaulp.wicket.base.util.resource.ResourceModelFactory;

/**
 * The Class ComponentPropertiesKeysListResolver creates a list with the properties keys
 * from the given list with the given prefix and optionally a suffix. This class
 * is usefull for get Properties that have a properties key prefix for instance:
 * properties key prefix='infringement.list.entry' and the properties key prefix='label'.
 * The values list contains the String object '1' and '2'.
 * The properties file could look something like this: 
 * infringement.list.entry.1.label = foo
 * infringement.list.entry.2.label = bar
 */
public class ComponentPropertiesKeysListResolver extends
		PropertiesKeysListResolver {

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
			Component component, List<String> values) {
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
			String propertiesKeySuffix, Component component, List<String> values) {
		super(propertiesKeyPrefix, propertiesKeySuffix, values);
		this.component = component;
	}

	/**
	 * Gets the display value.
	 * 
	 * @param object
	 *            the object
	 * @return the display value
	 */
	public String getDisplayValue(final String object) {
		IModel<String> resourceModel = ResourceModelFactory.newResourceModel(
				getPropertiesKey(object), component);
		String value = resourceModel.getObject();
		return value;
	}
}