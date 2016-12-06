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
package de.alpharogroup.wicket.components.buttons;

import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.model.IModel;

import de.alpharogroup.wicket.components.actions.Action;

/**
 * The Class {@link ActionButtonPanel}.
 */
public abstract class ActionButtonPanel extends ButtonPanel
{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new {@link ActionButtonPanel}.
	 *
	 * @param id
	 *            the id
	 * @param labelModel
	 *            the label model
	 */
	public ActionButtonPanel(final String id, final IModel<String> labelModel)
	{
		super(id, labelModel);
	}

	/**
	 * Callback method that have to be implemented from derived classes.
	 *
	 * @return the action
	 */
	protected abstract Action<?> getAction();

	/**
	 * Factory method for creating a new {@link Button}. This method is invoked in the constructor from the
	 * derived classes and can be overridden so users can provide their own version of a Button.
	 *
	 * @param id
	 *            the wicket id
	 * @return the Button
	 */
	@Override
	protected Button newButton(final String id)
	{
		return new Button(id)
		{
			/**
			 * The serialVersionUID.
			 */
			private static final long serialVersionUID = 1L;

			/**
			 * {@inheritDoc}
			 */
			@Override
			public void onSubmit()
			{
				getAction().execute();
			}
		};
	}

}
