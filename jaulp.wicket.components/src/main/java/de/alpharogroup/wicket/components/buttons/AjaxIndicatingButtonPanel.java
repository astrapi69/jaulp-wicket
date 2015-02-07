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

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.extensions.ajax.markup.html.IndicatingAjaxButton;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.model.IModel;

/**
 * The Class ActionButtonPanel.
 */
public abstract class AjaxIndicatingButtonPanel extends ButtonPanel
{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new action button panel.
	 *
	 * @param id
	 *            the id
	 * @param labelModel
	 *            the label model
	 * @param form
	 *            the form
	 */
	public AjaxIndicatingButtonPanel(String id, IModel<String> labelModel, final Form<?> form)
	{
		super(id, labelModel, form);
	}

	/**
	 * Factory method for creating the Button. This method is invoked in the constructor from the
	 * derived classes and can be overridden so users can provide their own version of a Button.
	 * 
	 * @param id
	 *            the wicket id
	 * @return the Button
	 */
	@Override
	protected Button newButton(String id)
	{
		return new IndicatingAjaxButton(id, getForm())
		{
			/**
			 * The serialVersionUID.
			 */
			private static final long serialVersionUID = 1L;

			@Override
			protected void onSubmit(final AjaxRequestTarget target, final Form<?> form)
			{
				target.add(form);
				AjaxIndicatingButtonPanel.this.onSubmit(target, form);
			}

			@Override
			protected void onError(AjaxRequestTarget target, Form<?> form)
			{
			}
		};
	}

	/**
	 * Hook method.
	 *
	 * @param target
	 *            the target
	 * @param form
	 *            the form
	 */
	protected abstract void onSubmit(final AjaxRequestTarget target, final Form<?> form);

}
