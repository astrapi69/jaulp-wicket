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
package de.alpharogroup.wicket.components.img;

import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.html.WebComponent;
import org.apache.wicket.model.IModel;
import org.apache.wicket.protocol.http.WebApplication;
import org.jaulp.wicket.base.util.ApplicationUtils;

/**
 * The Class WicketImage.
 * 
 * @author Asterios Raptis
 */
public class WicketImage extends WebComponent
{

	/**
	 * The serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new wicket image.
	 * 
	 * @param id
	 *            the id
	 */
	public WicketImage(final String id)
	{
		super(id);
	}

	/**
	 * Instantiates a new wicket image.
	 * 
	 * @param id
	 *            the id
	 * @param model
	 *            the model
	 */
	public WicketImage(final String id, final IModel<?> model)
	{
		super(id, model);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void onComponentTag(final ComponentTag tag)
	{
		checkComponentTag(tag, "img");
		super.onComponentTag(tag);
		final String modelObjectAsString = getDefaultModelObjectAsString();
		final String contextPath = ApplicationUtils
			.getContextPath(((WebApplication)getApplication()));
		final String imagePath = contextPath + modelObjectAsString;
		tag.put("src", imagePath);
	}
}
