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
 * The Class {@link AbstractModalWindow}.
 *
 * @param <T>
 *            the generic type of the model object
 *
 */
public abstract class AbstractModalWindow<T> extends ModalWindow
{

	/**
	 * The serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new {@link AbstractModalWindow}.
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
	 * @param component
	 *            the component
	 */
	public AbstractModalWindow(final String id, final IModel<T> model, final String title,
		final int initialWidth, final int initialHeight, final Component component)
	{
		super(id, model);
		init(title, initialWidth, initialHeight, component);
	}

	/**
	 * Instantiates a new {@link AbstractModalWindow}.
	 *
	 * @param id
	 *            the id
	 * @param initialWidth
	 *            the initial width
	 * @param initialHeight
	 *            the initial height
	 * @param component
	 *            the component
	 */
	public AbstractModalWindow(final String id, final int initialWidth, final int initialHeight,
		final Component component)
	{
		this(id, null, initialWidth, initialHeight, component);
	}

	/**
	 * Instantiates a new {@link AbstractModalWindow}.
	 *
	 * @param id
	 *            the id
	 * @param title
	 *            the title
	 * @param initialWidth
	 *            the initial width
	 * @param initialHeight
	 *            the initial height
	 * @param component
	 *            the component
	 */
	public AbstractModalWindow(final String id, final String title, final int initialWidth,
		final int initialHeight, final Component component)
	{
		super(id);
		init(title, initialWidth, initialHeight, component);
	}


	/**
	 * Initialize the given fields.
	 *
	 * @param title
	 *            the title
	 * @param initialWidth
	 *            the initial width
	 * @param initialHeight
	 *            the initial height
	 * @param component
	 *            the component
	 */
	private void init(final String title, final int initialWidth, final int initialHeight,
		final Component component)
	{
		if (title != null)
		{
			setTitle(title);
		}
		setInitialWidth(initialWidth);
		setInitialHeight(initialHeight);
		setContent(component);
	}

	/**
	 * Abstract callback method that have to be overwritten to provide specific action for cancel.
	 *
	 * @param target
	 *            the target
	 */
	protected abstract void onCancel(final AjaxRequestTarget target);

	/**
	 * Abstract callback method that have to be overwritten to provide specific action for select.
	 *
	 * @param target
	 *            the target
	 * @param object
	 *            the object
	 */
	protected abstract void onSelect(final AjaxRequestTarget target, final T object);

}
