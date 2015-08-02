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
package de.alpharogroup.wicket.components.radio;

import org.apache.wicket.markup.html.form.RadioGroup;
import org.apache.wicket.model.IModel;

/**
 * The Class AjaxEnumRadio extends the class AjaxRadio It restricts the Model to an enum type.
 * 
 * @param <T>
 *            the generic type that must extends enum.
 * @author Asterios Raptis
 */
public abstract class AjaxEnumRadio<T extends Enum<?>> extends AjaxRadio<T>
{

	/**
	 * The Constant serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new enum radio.
	 * 
	 * @param id
	 *            the id
	 */
	public AjaxEnumRadio(final String id)
	{
		super(id);
	}

	/**
	 * Instantiates a new enum radio.
	 * 
	 * @param id
	 *            the id
	 * @param model
	 *            the model
	 */
	public AjaxEnumRadio(final String id, final IModel<T> model)
	{
		super(id, model);
	}

	/**
	 * Instantiates a new enum radio.
	 * 
	 * @param id
	 *            the id
	 * @param model
	 *            the model
	 * @param group
	 *            the group
	 */
	public AjaxEnumRadio(final String id, final IModel<T> model, final RadioGroup<T> group)
	{
		super(id, model, group);
	}

	/**
	 * Instantiates a new enum radio.
	 * 
	 * @param id
	 *            the id
	 * @param group
	 *            the group
	 */
	public AjaxEnumRadio(final String id, final RadioGroup<T> group)
	{
		super(id, group);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getValue()
	{
		return getModelObject().name();
	}

}