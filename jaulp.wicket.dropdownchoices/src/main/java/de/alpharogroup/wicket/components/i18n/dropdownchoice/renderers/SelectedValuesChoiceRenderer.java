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
 * The Class SelectedValuesChoiceRenderer.
 *
 * @author Asterios Raptis
 */
public class SelectedValuesChoiceRenderer implements IChoiceRenderer<String>
{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The relative component used for lookups. */
	private final Component component;

	/** The component class. */
	private final Class<?> componentClass;

	public Class<?> getComponentClass()
	{
		return componentClass;
	}

	/**
	 * Instantiates a new selected values choice renderer.
	 *
	 * @param component
	 *            the component
	 * @param componentClass
	 *            the component class
	 */
	public SelectedValuesChoiceRenderer(final Component component, final Class<?> componentClass)
	{
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
		String splitString = "=>";
		String[] splittedValue = object.split(splitString);
		StringBuilder sb = new StringBuilder();
		if (splittedValue.length == 1)
		{
			IModel<String> resourceModel = ResourceModelFactory.newResourceModel(ResourceBundleKey
				.builder().key(splittedValue[0]).defaultValue("").build(), component);
			sb.append(resourceModel.getObject());
		}
		else
		{
			IModel<String> resourceModel = ResourceModelFactory.newResourceModel(ResourceBundleKey
				.builder().key(splittedValue[0]).defaultValue("").build(), component);
			sb.append(resourceModel.getObject());
			sb.append(splitString);
			resourceModel = ResourceModelFactory.newResourceModel(
				ResourceBundleKey.builder().key(splittedValue[1]).defaultValue("").build(),
				component);
			sb.append(resourceModel.getObject());
		}
		return sb.toString();
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
		return null;
	}

}
