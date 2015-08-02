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
package de.alpharogroup.wicket.components.examples.ajaxtabs.tabpanels;

import java.io.Serializable;

import org.apache.wicket.model.IModel;

public class TabModel<T> implements Serializable
{

	private static final long serialVersionUID = 1L;
	private IModel<String> title;
	private IModel<T> content;
	private IModel<String> closeTitle;

	public TabModel()
	{
	}

	public TabModel(final IModel<String> title, final IModel<T> content,
		final IModel<String> closeTitle)
	{
		this.title = title;
		this.content = content;
		this.closeTitle = closeTitle;
	}

	public IModel<String> getCloseTitle()
	{
		return closeTitle;
	}

	public IModel<T> getContent()
	{
		return content;
	}

	public IModel<String> getTitle()
	{
		return title;
	}

	public void setCloseTitle(final IModel<String> closeTitle)
	{
		this.closeTitle = closeTitle;
	}

	public void setContent(final IModel<T> content)
	{
		this.content = content;
	}

	public void setTitle(final IModel<String> title)
	{
		this.title = title;
	}
}
