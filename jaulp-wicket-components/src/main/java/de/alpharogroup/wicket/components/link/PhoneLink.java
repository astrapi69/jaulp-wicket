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
package de.alpharogroup.wicket.components.link;

import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.model.IModel;
import org.apache.wicket.util.lang.Args;

/**
 * The Class {@link PhoneLink} can create a link for a phone number.
 */
public abstract class PhoneLink extends AjaxLink<CharSequence>
{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new {@link PhoneLink}.
	 *
	 * @param id
	 *            the id
	 * @param model
	 *            the model
	 */
	public PhoneLink(final String id, final IModel<CharSequence> model)
	{
		super(id, model);
		Args.notNull(model, "model");
		Args.notNull(model.getObject(), "model");
		Args.notEmpty(model.getObject(), "model");
		onValidate(model);
	}

	/**
	 * Gets the url to use for this link.
	 * 
	 * @return The URL that this link links to
	 */
	protected CharSequence getURL()
	{
		return getModelObject();
	}

	/**
	 * Callback method for opportunity to validate the given phone number.
	 *
	 * @param model
	 *            the model
	 */
	protected void onValidate(final IModel<CharSequence> model)
	{
	}

}