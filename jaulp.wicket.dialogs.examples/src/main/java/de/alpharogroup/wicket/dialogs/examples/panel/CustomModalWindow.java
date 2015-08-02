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
package de.alpharogroup.wicket.dialogs.examples.panel;

import org.apache.wicket.extensions.ajax.markup.html.modal.ModalWindow;
import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.CssReferenceHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.model.IModel;
import org.apache.wicket.request.resource.PackageResourceReference;
import org.apache.wicket.request.resource.ResourceReference;

public class CustomModalWindow extends ModalWindow
{

	private static final long serialVersionUID = 1L;

	private static ResourceReference CSS = new PackageResourceReference(CustomModalWindow.class,
		"custom-modal.css");

	/**
	 * Creates a new modal window component.
	 * 
	 * @param id
	 *            Id of component
	 */
	public CustomModalWindow(final String id)
	{
		super(id);
	}

	/**
	 * Creates a new modal window component.
	 * 
	 * @param id
	 *            Id of component
	 * @param model
	 *            Model
	 */
	public CustomModalWindow(final String id, final IModel<?> model)
	{
		super(id, model);
	}

	@Override
	public void renderHead(final IHeaderResponse response)
	{
		super.renderHead(response);
		final CssReferenceHeaderItem headerItem = CssHeaderItem.forReference(CSS);
		response.render(headerItem);
	}


}