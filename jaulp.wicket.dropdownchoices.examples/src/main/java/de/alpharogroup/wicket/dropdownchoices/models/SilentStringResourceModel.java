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
package de.alpharogroup.wicket.dropdownchoices.models;

import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;

import org.apache.wicket.Component;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.StringResourceModel;

import de.alpharogroup.lang.PropertiesUtils;

/**
 * The Class SilentStringResourceModel.
 * 
 * @deprecated use instead Label from wicket and create IModel with the
 *             {@link de.alpharogroup.wicket.base.util.resource.ResourceModelFactory}.
 */
@Deprecated
public class SilentStringResourceModel extends StringResourceModel
{
	/**
	 * The serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;

	/** The relative component used for lookups. */
	private Component component;

	/** The component class. */
	private Class<?> componentClass;

	/** The locale. */
	private Locale locale;

	/**
	 * Instantiates a new silent string resource model.
	 *
	 * @param resourceKey
	 *            the resource key
	 * @param componentClass
	 *            the component class
	 * @param locale
	 *            the locale
	 */
	public SilentStringResourceModel(final String resourceKey, final Class<?> componentClass,
		final Locale locale)
	{
		this(resourceKey, null, null, null, null, locale);
		this.componentClass = componentClass;
	}

	/**
	 * Instantiates a new silent string resource model.
	 *
	 * @param resourceKey
	 *            the resource key
	 * @param component
	 *            the component
	 * @param componentClass
	 *            the component class
	 * @param locale
	 *            the locale
	 */
	public SilentStringResourceModel(final String resourceKey, final Component component,
		final Class<?> componentClass, final Locale locale)
	{
		this(resourceKey, component, null, null, null, locale);
		this.componentClass = componentClass;
	}

	/**
	 * Instantiates a new silent string resource model.
	 *
	 * @param resourceKey
	 *            the resource key
	 * @param component
	 *            the component
	 * @param model
	 *            the model
	 */
	public SilentStringResourceModel(final String resourceKey, final Component component,
		final IModel<?> model)
	{
		this(resourceKey, component, model, null, null, null);
	}

	/**
	 * Instantiates a new silent string resource model.
	 *
	 * @param resourceKey
	 *            the resource key
	 * @param component
	 *            the component
	 * @param model
	 *            the model
	 * @param locale
	 *            the locale
	 */
	public SilentStringResourceModel(final String resourceKey, final Component component,
		final IModel<?> model, final Locale locale)
	{
		this(resourceKey, component, model, null, null, locale);
	}

	/**
	 * Instantiates a new silent string resource model.
	 *
	 * @param resourceKey
	 *            the resource key
	 * @param component
	 *            the component
	 * @param model
	 *            the model
	 * @param parameters
	 *            the parameters
	 */
	public SilentStringResourceModel(final String resourceKey, final Component component,
		final IModel<?> model, final Object[] parameters)
	{
		this(resourceKey, component, model, parameters, null, null);
	}

	/**
	 * Instantiates a new silent string resource model.
	 *
	 * @param resourceKey
	 *            the resource key
	 * @param component
	 *            the component
	 * @param model
	 *            the model
	 * @param parameters
	 *            the parameters
	 * @param locale
	 *            the locale
	 */
	public SilentStringResourceModel(final String resourceKey, final Component component,
		final IModel<?> model, final Object[] parameters, final Locale locale)
	{
		this(resourceKey, component, model, parameters, null, locale);
	}

	/**
	 * Instantiates a new silent string resource model.
	 *
	 * @param resourceKey
	 *            the resource key
	 * @param component
	 *            the component
	 * @param model
	 *            the model
	 * @param parameters
	 *            the parameters
	 * @param defaultValue
	 *            the default value
	 */
	public SilentStringResourceModel(final String resourceKey, final Component component,
		final IModel<?> model, final Object[] parameters, final String defaultValue)
	{
		this(resourceKey, component, model, parameters, defaultValue, null);
	}

	/**
	 * Instantiates a new silent string resource model.
	 *
	 * @param resourceKey
	 *            the resource key
	 * @param component
	 *            the component
	 * @param model
	 *            the model
	 * @param parameters
	 *            the parameters
	 * @param defaultValue
	 *            the default value
	 * @param locale
	 *            the locale
	 */
	public SilentStringResourceModel(final String resourceKey, final Component component,
		final IModel<?> model, final Object[] parameters, final String defaultValue,
		final Locale locale)
	{
		super(resourceKey, component, model, parameters, defaultValue);
		this.component = component;
		this.locale = locale;
	}

	/**
	 * Instantiates a new silent string resource model.
	 *
	 * @param resourceKey
	 *            the resource key
	 * @param component
	 *            the component
	 * @param model
	 *            the model
	 * @param defaultValue
	 *            the default value
	 */
	public SilentStringResourceModel(final String resourceKey, final Component component,
		final IModel<?> model, final String defaultValue)
	{
		this(resourceKey, component, model, null, defaultValue, null);
	}

	/**
	 * Instantiates a new silent string resource model.
	 *
	 * @param resourceKey
	 *            the resource key
	 * @param model
	 *            the model
	 */
	public SilentStringResourceModel(final String resourceKey, final IModel<?> model)
	{
		this(resourceKey, null, model, null, null, null);
	}

	/**
	 * Instantiates a new silent string resource model.
	 *
	 * @param resourceKey
	 *            the resource key
	 * @param model
	 *            the model
	 * @param parameters
	 *            the parameters
	 */
	public SilentStringResourceModel(final String resourceKey, final IModel<?> model,
		final Object[] parameters)
	{
		this(resourceKey, null, model, parameters, null, null);
	}

	/**
	 * Instantiates a new silent string resource model.
	 *
	 * @param resourceKey
	 *            the resource key
	 * @param model
	 *            the model
	 * @param parameters
	 *            the parameters
	 * @param defaultValue
	 *            the default value
	 */
	public SilentStringResourceModel(final String resourceKey, final IModel<?> model,
		final Object[] parameters, final String defaultValue)
	{
		this(resourceKey, null, model, parameters, defaultValue, null);
	}

	/**
	 * Instantiates a new silent string resource model.
	 *
	 * @param resourceKey
	 *            the resource key
	 * @param model
	 *            the model
	 * @param defaultValue
	 *            the default value
	 */
	public SilentStringResourceModel(final String resourceKey, final IModel<?> model,
		final String defaultValue)
	{
		this(resourceKey, null, model, null, defaultValue, null);
	}

	/**
	 * Instantiates a new silent string resource model.
	 *
	 * @param resourceKey
	 *            the resource key
	 * @param locale
	 *            the locale
	 */
	public SilentStringResourceModel(final String resourceKey, final Locale locale)
	{
		this(resourceKey, null, null, null, null, locale);
	}

	/**
	 * Gets the from resource bundle.
	 *
	 * @return the from resource bundle
	 */
	protected String getFromResourceBundle()
	{
		String result = null;
		ResourceBundle resourceBundle = null;
		try
		{
			if (component != null)
			{
				resourceBundle = ResourceBundle.getBundle(component.getClass().getName(), locale);
			}
			else if (componentClass != null)
			{
				resourceBundle = ResourceBundle.getBundle(componentClass.getName(), locale);
			}
			if (resourceBundle != null)
			{
				result = resourceBundle.getString(getResourceKey());
			}
		}
		catch (final Exception e)
		{
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * Gets the resource.
	 *
	 * @param componentClass
	 *            the component class
	 * @param defaultClass
	 *            the default class
	 * @param resourceKey
	 *            the resource key
	 * @return the resource
	 * @throws Exception
	 *             the exception
	 */
	protected Properties getResource(final Class<?> componentClass, final Class<?> defaultClass,
		final String resourceKey) throws Exception
	{
		// Try to find the properties file and the resource
		Properties properties = null;
		if (componentClass != null)
		{
			properties = PropertiesUtils.loadPropertiesFromClassObject(componentClass, locale);
		}
		else
		{
			properties = PropertiesUtils.getLocalPropertiesFromClass(componentClass, defaultClass,
				locale);
		}
		return properties;
	}

	/**
	 * Gets the resource.
	 *
	 * @param component
	 *            the component
	 * @param componentClass
	 *            the component class
	 * @param resourceKey
	 *            the resource key
	 * @return the resource
	 */
	protected String getResource(final Component component, final Class<?> componentClass,
		final String resourceKey)
	{
		String result = null;
		// Try to find the properties file and the resource
		Properties properties = null;
		try
		{
			if (null != component)
			{
				properties = PropertiesUtils.loadPropertiesFromClassObject(component.getClass(),
					locale);
			}
			else
			{
				properties = getResource(componentClass, null, resourceKey);
			}
			String value = (String)properties.get(resourceKey);
			if (null != value)
			{
				result = value;
			}
			else
			{
				value = getFromResourceBundle();
				if (null != value)
				{
					result = value;
				}
				else
				{
					result = "!?!" + resourceKey + "!?!";
				}
			}
		}
		catch (final Exception e)
		{
			final String value = getFromResourceBundle();
			if (null != value)
			{
				result = value;
			}
			else
			{
				result = "!?!" + resourceKey + "!?!";
				e.printStackTrace();
			}
		}
		return result;
	}

}
