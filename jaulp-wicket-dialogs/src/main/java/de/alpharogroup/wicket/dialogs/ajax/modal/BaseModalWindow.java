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
package de.alpharogroup.wicket.dialogs.ajax.modal;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.extensions.ajax.markup.html.modal.ModalWindow;
import org.apache.wicket.model.IModel;


/**
 * The Class {@link BaseModalWindow}.
 *
 * @param <T>
 *            the generic type of the model object.
 */
public abstract class BaseModalWindow<T> extends ModalWindow
{

	/**
	 * The serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new {@link BaseModalWindow}.
	 *
	 * @param id
	 *            the id
	 * @param model
	 *            the model
	 * @param title
	 *            the title
	 * @param initialWidth
	 *            the initial width
	 * @param initialHeight
	 *            the initial height
	 */
	public BaseModalWindow(final String id, final IModel<T> model, final String title,
		final int initialWidth, final int initialHeight)
	{
		super(id, model);
		setInitialWidth(initialWidth);
		setInitialHeight(initialHeight);
		setTitle(title);
		setContent(newContent(this.getContentId(), model));
	}

	/**
	 * Instantiates a new base modal window.
	 *
	 * @param id
	 *            the id
	 * @param title
	 *            the title
	 * @param initialWidth
	 *            the initial width
	 * @param initialHeight
	 *            the initial height
	 * @param model
	 *            the model
	 */
	public BaseModalWindow(final String id, final String title, final int initialWidth,
		final int initialHeight, final IModel<T> model)
	{
		this(id, model, title, initialWidth, initialHeight);
		setInitialWidth(initialWidth);
		setInitialHeight(initialHeight);
		setTitle(title);
		setContent(newContent(this.getContentId(), model));
	}

	/**
	 * Factory method for create the new {@link Component} for the content. This method is invoked
	 * in the constructor from the derived classes and can be overridden so users can provide their
	 * own version of a new {@link Component} for the content.
	 *
	 * @param contentId
	 *            the content id
	 * @param model
	 *            the model
	 * @return the new {@link Component} for the content.
	 */
	protected Component newContent(final String contentId, final IModel<T> model)
	{
		return new BaseModalPanel<T>(contentId, model)
		{
			/**
			 * The serialVersionUID.
			 */
			private static final long serialVersionUID = 1L;

			/**
			 * {@inheritDoc}
			 */
			@Override
			protected void onCancel(final AjaxRequestTarget target)
			{
				BaseModalWindow.this.onCancel(target);
			}

			/**
			 * {@inheritDoc}
			 */
			@Override
			protected void onSelect(final AjaxRequestTarget target, final T object)
			{
				BaseModalWindow.this.onSelect(target, object);
			}
		};
	}

	/**
	 * Abstract callback method that have to be overwritten to provide specific action for cancel.
	 *
	 * @param target
	 *            the target
	 */
	public abstract void onCancel(final AjaxRequestTarget target);

	/**
	 * Abstract callback method that have to be overwritten to provide specific action for select.
	 *
	 * @param target
	 *            the target
	 * @param object
	 *            the object
	 */
	public abstract void onSelect(final AjaxRequestTarget target, final T object);

}
