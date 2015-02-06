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
package org.jaulp.wicket.components.i18n.dropdownchoice.renderers;


import java.util.List;

import org.apache.wicket.Component;
import org.apache.wicket.markup.html.form.IChoiceRenderer;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.StringResourceModel;

/**
 * The Class LocalisedChoiceRenderer.
 * 
 * @author Asterios Raptis
 */
public class LocalisedChoiceRenderer implements IChoiceRenderer<String>
{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The properties key prefix. */
	private final String propertiesKeyPrefix;

	/** The relative component used for lookups. */
	private final Component component;

	/** The component class. */
	private final Class<?> componentClass;

	/**
	 * Instantiates a new localised choice renderer.
	 * 
	 * @param propertiesKeyPrefix
	 *            the properties key prefix
	 * @param component
	 *            the component
	 * @param componentClass
	 *            the component class
	 */
	public LocalisedChoiceRenderer(final String propertiesKeyPrefix, final Component component,
		final Class<?> componentClass)
	{
		this.propertiesKeyPrefix = propertiesKeyPrefix;
		this.component = component;
		this.componentClass = componentClass;
	}

	/**
	 * {@inheritDoc}.
	 * 
	 * @param object
	 *            the object
	 * @return the display value
	 * @see org.apache.wicket.markup.html.form.IChoiceRenderer#getDisplayValue(java.lang.Object)
	 */
	@Override
	public Object getDisplayValue(final String object)
	{
		StringResourceModel resourceModel = new StringResourceModel(propertiesKeyPrefix + "."
			+ object, component, null);
		String value = resourceModel.getObject();
		return value;

	}

	/**
	 * {@inheritDoc}.
	 * 
	 * @param object
	 *            the object
	 * @param index
	 *            the index
	 * @return the id value
	 * @see org.apache.wicket.markup.html.form.IChoiceRenderer#getIdValue(java.lang.Object, int)
	 */
	@Override
	public String getIdValue(final String object, final int index)
	{
		return object;
	}


	public String getObject(String id, IModel<? extends List<? extends String>> choices)
	{
		// override this method...
		return null;
	}

	public Class<?> getComponentClass()
	{
		return componentClass;
	}
}