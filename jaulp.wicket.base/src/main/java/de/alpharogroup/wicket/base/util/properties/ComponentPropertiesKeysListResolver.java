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
package de.alpharogroup.wicket.base.util.properties;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.Component;

import de.alpharogroup.locale.PropertiesKeysListResolver;
import de.alpharogroup.locale.ResourceBundleKey;
import de.alpharogroup.wicket.base.util.resource.ResourceModelFactory;

/**
 * The Class ComponentPropertiesKeysListResolver creates a list with the properties keys from the
 * given list with the given prefix and optionally a suffix. This class is usefull for get
 * Properties that have a properties key prefix for instance: properties key
 * prefix='infringement.list.entry' and the properties key prefix='label'. The values list contains:
 * a ResourceBundleKey object with the key '1' and with parameters '7' and a second
 * ResourceBundleKey object with the key '2' with no parameters. The properties file could look
 * something like this: infringement.list.entry.1.label = foo {0} infringement.list.entry.2.label =
 * bar
 */
public class ComponentPropertiesKeysListResolver
	extends
		PropertiesKeysListResolver<ResourceBundleKey>
{

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
	public ComponentPropertiesKeysListResolver(final String propertiesKeyPrefix,
		final Component component, final List<ResourceBundleKey> values)
	{
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
	public ComponentPropertiesKeysListResolver(final String propertiesKeyPrefix,
		final String propertiesKeySuffix, final Component component,
		final List<ResourceBundleKey> values)
	{
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
	@Override
	public String getDisplayValue(final ResourceBundleKey resourceBundleKey)
	{
		return ResourceModelFactory.newResourceModel(getPropertiesKey(resourceBundleKey.getKey()),
			resourceBundleKey.getParameters(), component, resourceBundleKey.getDefaultValue())
			.getObject();
	}

	/**
	 * Gets the display values with the full properties keys as a List of {@link ResourceBundleKey}.
	 *
	 * @return the display values
	 */
	public List<ResourceBundleKey> getDisplayValues()
	{
		final List<ResourceBundleKey> rbk = new ArrayList<>();
		for (final ResourceBundleKey key : getValues())
		{
			final ResourceBundleKey clone = (ResourceBundleKey)key.clone();
			clone.setKey(getPropertiesKey(key.getKey()));
			rbk.add(clone);
		}
		return rbk;
	}
}