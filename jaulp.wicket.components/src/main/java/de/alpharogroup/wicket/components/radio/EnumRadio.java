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

import org.apache.wicket.markup.html.form.Radio;
import org.apache.wicket.markup.html.form.RadioGroup;
import org.apache.wicket.model.IModel;

/**
 * The Class EnumRadio extends the class Radio and takes as parameter an enum type.
 *
 * @param <T>
 *            the generic type that have to extends enum.
 * @author Asterios Raptis
 */
public class EnumRadio<T extends Enum<?>> extends Radio<T>
{

	/**
	 * The Constant serialVersionUID.
	 */
	private static final long serialVersionUID = -8391468655460799084L;

	/**
	 * Instantiates a new {@link EnumRadio}.
	 *
	 * @param id
	 *            the id
	 */
	public EnumRadio(final String id)
	{
		super(id);
		commonInit();
	}

	/**
	 * Instantiates a new {@link EnumRadio}.
	 *
	 * @param id
	 *            the id
	 * @param model
	 *            the model
	 */
	public EnumRadio(final String id, final IModel<T> model)
	{
		super(id, model);
		commonInit();
	}

	/**
	 * Instantiates a new {@link EnumRadio}.
	 *
	 * @param id
	 *            the id
	 * @param model
	 *            the model
	 * @param group
	 *            the group
	 */
	public EnumRadio(final String id, final IModel<T> model, final RadioGroup<T> group)
	{
		super(id, model, group);
		commonInit();
	}

	/**
	 * Instantiates a new {@link EnumRadio}.
	 *
	 * @param id
	 *            the id
	 * @param group
	 *            the group
	 */
	public EnumRadio(final String id, final RadioGroup<T> group)
	{
		super(id, group);
		commonInit();
	}

	/**
	 * Common init.
	 */
	protected void commonInit()
	{
		setOutputMarkupId(true);
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