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
package de.alpharogroup.wicket.data.provider.examples.refreshingview;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.extensions.ajax.markup.html.modal.ModalWindow;
import org.apache.wicket.markup.html.panel.Fragment;
import org.apache.wicket.markup.html.panel.Panel;

public class ModalDialogWithStylePanel extends Panel
{

	/**
	 * The serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	// private static ResourceReference CSS = new PackageResourceReference(
	// CustomModalWindow.class, "cart_global.css");

	public ModalDialogWithStylePanel(final String id)
	{
		super(id);
		final ModalWindow modal = new ModalWindow("modal");
		modal.setCssClassName("w_vegas");
		modal.setTitle("Trivial Modal");

		final AjaxLink<Void> modalLink = new AjaxLink<Void>("modalLink")
		{
			private static final long serialVersionUID = 1L;

			@Override
			public void onClick(final AjaxRequestTarget target)
			{

				target.appendJavaScript("var originalStyle = $('.wicket-modal').attr('style');"
					+ "$('.wicket-modal').attr('style', originalStyle + 'opacity: 0.5;');");
			}
		};
		final Fragment modalFragment = new Fragment(modal.getContentId(), "modalContent", this);
		modalFragment.add(modalLink);
		modal.setContent(modalFragment);

		add(modal);
		add(new AjaxLink<Void>("openModal")
		{
			private static final long serialVersionUID = 1L;

			@Override
			public void onClick(final AjaxRequestTarget target)
			{
				modal.show(target);
			}
		});
	}

	// @Override
	// public void renderHead(IHeaderResponse response) {
	// super.renderHead(response);
	// CssReferenceHeaderItem headerItem = CssHeaderItem
	// .forReference(CSS);
	// response.render(headerItem);
	// }

}
