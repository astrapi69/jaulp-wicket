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
package de.alpharogroup.wicket.components.i18n.dropdownchoice.renderers;

import java.util.List;

import org.apache.wicket.Component;
import org.apache.wicket.markup.html.form.IChoiceRenderer;
import org.apache.wicket.model.IModel;

import de.alpharogroup.locale.ResourceBundleKey;
import de.alpharogroup.wicket.base.util.resource.ResourceModelFactory;

/**
 * The Class {@link PropertiesChoiceRenderer}.
 *
 * @author Asterios Raptis
 */
public class PropertiesChoiceRenderer implements IChoiceRenderer<String>
{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The relative component used for lookups. */
	private final Component component;

	/** The component class. */
	private final Class<?> componentClass;

	/**
	 * Instantiates a new {@link PropertiesChoiceRenderer}.
	 *
	 * @param component
	 *            the component
	 * @param componentClass
	 *            the component class
	 */
	public PropertiesChoiceRenderer(final Component component, final Class<?> componentClass)
	{
		this.component = component;
		this.componentClass = componentClass;
	}

	public Class<?> getComponentClass()
	{
		return componentClass;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Object getDisplayValue(final String object)
	{
		final IModel<String> resourceModel = ResourceModelFactory.newResourceModel(
			ResourceBundleKey.builder().key(object).defaultValue("").build(), component);
		final String value = resourceModel.getObject();
		return value;
	}


	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getIdValue(final String object, final int index)
	{
		return object;
	}

	/**
	 * Gets the object.
	 *
	 * @param id
	 *            the id
	 * @param choices
	 *            the choices
	 * @return the object
	 */
	public String getObject(final String id, final IModel<? extends List<? extends String>> choices)
	{
		// override this method...
		return null;
	}
}
