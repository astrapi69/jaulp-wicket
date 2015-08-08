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
package de.alpharogroup.wicket.components.ajax.editable.tabs;

import lombok.Getter;

import org.apache.wicket.extensions.markup.html.tabs.ITab;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.model.IModel;


/**
 * Convenience class that takes care of common ITab functionality.
 *
 * @param <T>
 *            the generic type
 * @see ITab
 */
public abstract class AbstractContentTab<T> extends CloseableTab
{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The content. */
	@Getter
	private final IModel<T> content;

	/**
	 * Instantiates a new {@link AbstractContentTab}.
	 *
	 * @param title
	 *            IModel used to represent the title of the tab. Must contain a string
	 * @param content
	 *            IModel used to represent the content of the tab.
	 * @param closeTitle
	 *            title of close
	 */
	public AbstractContentTab(final IModel<String> title, final IModel<T> content,
		final IModel<String> closeTitle)
	{
		super(title, closeTitle);
		this.content = content;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public abstract WebMarkupContainer getPanel(final String panelId);

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isVisible()
	{
		return true;
	}
}