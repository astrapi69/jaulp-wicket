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

import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.model.IModel;

public abstract class CloseableTab implements ICloseableTab
{
	private static final long serialVersionUID = 1L;


	/** The title of the tab. */
	@Getter
	IModel<String> title;

	/** The close title. */
	@Getter
	IModel<String> closeTitle;

	/**
	 * Constructor
	 * 
	 * @param title
	 *            IModel used to represent the title of the tab. Must contain a string
	 * @param closeTitle
	 *            title of close
	 */
	public CloseableTab(final IModel<String> title, final IModel<String> closeTitle)
	{
		this.title = title;
		this.closeTitle = closeTitle;
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
