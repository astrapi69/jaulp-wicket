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

import org.apache.wicket.Component;
import org.apache.wicket.extensions.ajax.markup.html.AjaxLazyLoadPanel;
import org.apache.wicket.extensions.markup.html.tabs.ITab;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.model.IModel;

public abstract class AbstractAjaxLazyLoadTab<T> implements ITab
{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	/** The title of the tab. */
	@Getter
	private final IModel<String> title;
	/** The model of the content. */
	@Getter
	private final IModel<T> content;

	public AbstractAjaxLazyLoadTab(IModel<String> title, IModel<T> content)
	{
		this.title = title;
		this.content = content;
	}

	@Override
	public WebMarkupContainer getPanel(String panelId)
	{
		return new AjaxLazyLoadPanel(panelId)
		{
			private static final long serialVersionUID = 1L;

			@Override
			public Component getLazyLoadComponent(String markupId)
			{
				return getLazyLoadPanel(markupId);
			}
		};
	}

	public abstract Component getLazyLoadPanel(final String markupId);

}
