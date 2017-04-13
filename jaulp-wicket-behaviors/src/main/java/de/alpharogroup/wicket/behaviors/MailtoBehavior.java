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
package de.alpharogroup.wicket.behaviors;

import org.apache.wicket.Component;
import org.apache.wicket.behavior.Behavior;

import de.alpharogroup.wicket.behaviors.models.MailtoModel;

/**
 * The Class MailtoBehavior adds the email address to the a-tag.
 *
 * @param <T>
 *            the generic type
 * @author Asterios Raptis
 */
public class MailtoBehavior<T extends MailtoModel> extends Behavior
{

	/**
	 * The serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;

	/** The mailto model. */
	private final T mailtoModel;

	/**
	 * Instantiates a new mailto behavior.
	 * 
	 * @param mailtoModel
	 *            The mailto model.
	 */
	public MailtoBehavior(final T mailtoModel)
	{
		this.mailtoModel = mailtoModel;
	}

	/**
	 * On rendered.
	 *
	 * @param component
	 *            the component
	 */
	@Override
	public void afterRender(final Component component)
	{
		super.afterRender(component);
		component.getResponse().write("</a>");
	}

	/**
	 * Before render.
	 *
	 * @param component
	 *            the component
	 */
	@Override
	public void beforeRender(final Component component)
	{
		super.beforeRender(component);
		component.getResponse().write(
			"<a href=\"mailto:" + mailtoModel.getMailtoAddresModel().getObject() + "\">");
	}

	/**
	 * Gets the mailto model.
	 *
	 * @return the mailto model
	 */
	public T getMailtoModel()
	{
		return mailtoModel;
	}

}