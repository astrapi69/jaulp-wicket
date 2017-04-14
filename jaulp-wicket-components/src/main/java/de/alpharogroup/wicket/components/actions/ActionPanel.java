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
package de.alpharogroup.wicket.components.actions;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.html.link.AbstractLink;
import org.apache.wicket.model.IModel;

/**
 * The Class {@link ActionPanel} for an specific action.
 *
 * @param <T>
 *            the generic type of the model
 */
public abstract class ActionPanel<T> extends AbstractActionPanel<T>
{

	/** The serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new {@link ActionPanel}.
	 *
	 * @param id
	 *            the component id
	 * @param model
	 *            the model
	 */
	public ActionPanel(final String id, final IModel<T> model)
	{
		super(id, model);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected AbstractLink newActionLink(final String id)
	{
		final AjaxLink<String> link = new AjaxLink<String>(id)
		{
			/**
			 * The serialVersionUID
			 */
			private static final long serialVersionUID = 1L;

			/**
			 * {@inheritDoc}
			 */
			@Override
			public void onClick(final AjaxRequestTarget target)
			{
				ActionPanel.this.onAction(target);
			}
		};
		return link;
	}

	/**
	 * Abstract callback method that have to be overwritten to provide specific action.
	 *
	 * @param target
	 *            the target
	 */
	protected abstract void onAction(final AjaxRequestTarget target);

}
