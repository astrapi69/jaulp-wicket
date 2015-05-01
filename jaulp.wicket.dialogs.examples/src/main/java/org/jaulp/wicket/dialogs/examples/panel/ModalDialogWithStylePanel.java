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
package org.jaulp.wicket.dialogs.examples.panel;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.extensions.ajax.markup.html.modal.ModalWindow;
import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.CssReferenceHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.html.link.AbstractLink;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.resource.PackageResourceReference;
import org.apache.wicket.request.resource.ResourceReference;
import org.jaulp.wicket.dialogs.ajax.modal.ModalDialogFragmentPanel;

import de.alpharogroup.wicket.components.link.LinkPanel;

public class ModalDialogWithStylePanel extends ModalDialogFragmentPanel<String>
{

	/**
	 * The serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	private static ResourceReference CSS = new PackageResourceReference(CustomModalWindow.class,
		"cart_global.css");

	public ModalDialogWithStylePanel(String id, IModel<String> model)
	{
		super(id, model);
	}

	@Override
	protected Component newFragmentContent(String id, IModel<String> model)
	{
		return new LinkPanel(id, Model.of("Change opacity"))
		{

			/**
			 * The serialVersionUID
			 */
			private static final long serialVersionUID = 1L;

			@Override
			protected AbstractLink newLink(final String id)
			{
				AjaxLink<Void> changeOpacity = new AjaxLink<Void>(id)
				{
					private static final long serialVersionUID = 1L;

					@Override
					public void onClick(AjaxRequestTarget target)
					{
						target
							.appendJavaScript("var originalStyle = $('.wicket-modal').attr('style');"
								+ "$('.wicket-modal').attr('style', originalStyle + 'opacity: 0.5;');");
					}
				};
				return changeOpacity;
			}
		};
	}

	@Override
	protected ModalWindow newModalWindow(String id, IModel<String> model)
	{
		ModalWindow modalWindow = super.newModalWindow(id, model);
		modalWindow.setCssClassName("w_vegas");
		modalWindow.setTitle("Trivial Modal");
		return modalWindow;
	}

	@Override
	protected Component newOpenModalLink(final String id, final IModel<String> model)
	{
		return new LinkPanel(id, Model.of("Press me"))
		{

			/**
			 * The serialVersionUID
			 */
			private static final long serialVersionUID = 1L;

			@Override
			protected AbstractLink newLink(final String id)
			{
				return new AjaxLink<Void>(id)
				{
					private static final long serialVersionUID = 1L;

					@Override
					public void onClick(AjaxRequestTarget target)
					{
						/**
						 * This is how to prevent IE and Firefox dialog popup when trying to
						 * setResponsePage() or set an info message from a wicket modalWindow per
						 * below. Dialog popup demands an answer to:
						 * "This page is asking you to confirm that you want to leave - data you have entered may not be saved."
						 **/
						target.prependJavaScript("Wicket.Window.unloadConfirmation = false;");
						getModalWindow().show(target);
					}
				};
			}
		};
	}

	@Override
	public void renderHead(IHeaderResponse response)
	{
		super.renderHead(response);
		CssReferenceHeaderItem headerItem = CssHeaderItem.forReference(CSS);
		response.render(headerItem);
	}


}
