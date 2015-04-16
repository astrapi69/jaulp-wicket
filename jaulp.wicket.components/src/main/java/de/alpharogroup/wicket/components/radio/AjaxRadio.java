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

import org.apache.wicket.ajax.AjaxEventBehavior;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.attributes.AjaxRequestAttributes;
import org.apache.wicket.markup.html.form.Radio;
import org.apache.wicket.markup.html.form.RadioGroup;
import org.apache.wicket.model.IModel;

/**
 * The Class AjaxRadio extends the Radio component and adds an onclick {@link AjaxEventBehavior}.
 *
 * @param <T>
 *            the generic type
 */
public abstract class AjaxRadio<T> extends Radio<T>
{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new ajax radio.
	 *
	 * @param id
	 *            the id
	 */
	public AjaxRadio(final String id)
	{
		super(id);
		commonInit();
	}

	/**
	 * Instantiates a new ajax radio.
	 *
	 * @param id
	 *            the id
	 * @param model
	 *            the model
	 */
	public AjaxRadio(final String id, final IModel<T> model)
	{
		super(id, model);
		commonInit();
	}

	/**
	 * Instantiates a new ajax radio.
	 *
	 * @param id
	 *            the id
	 * @param group
	 *            the group
	 */
	public AjaxRadio(final String id, final RadioGroup<T> group)
	{
		super(id, group);
		commonInit();
	}

	/**
	 * Instantiates a new ajax radio.
	 *
	 * @param id
	 *            the id
	 * @param model
	 *            the model
	 * @param group
	 *            the group
	 */
	public AjaxRadio(final String id, final IModel<T> model, final RadioGroup<T> group)
	{
		super(id, model, group);
		commonInit();
	}


	/**
	 * Common init.
	 */
	protected void commonInit()
	{
		setOutputMarkupId(true);
		add(newAjaxEventBehavior("click"));
	}

	/**
	 * Listener method invoked on the ajax request generated when the user clicks the radio.
	 *
	 * @param target
	 *            the target
	 */
	public abstract void onClick(final AjaxRequestTarget target);


	/**
	 * New ajax event behavior.
	 *
	 * @param event
	 *            the name of the default event on which this link will listen to
	 * @return the ajax behavior which will be executed when the user clicks the link
	 */
	protected AjaxEventBehavior newAjaxEventBehavior(String event)
	{
		return new AjaxEventBehavior(event)
		{
			private static final long serialVersionUID = 1L;

			@Override
			protected void onEvent(AjaxRequestTarget target)
			{
				RadioGroup<T> radioGroup = getGroup();
				radioGroup.processInput();
				onClick(target);
			}

			@Override
			protected void updateAjaxAttributes(AjaxRequestAttributes attributes)
			{
				super.updateAjaxAttributes(attributes);
				AjaxRadio.this.updateAjaxAttributes(attributes);
			}
		};
	}

	/**
	 * Update ajax attributes.
	 *
	 * @param attributes
	 *            the attributes
	 */
	protected void updateAjaxAttributes(AjaxRequestAttributes attributes)
	{
	}

}
