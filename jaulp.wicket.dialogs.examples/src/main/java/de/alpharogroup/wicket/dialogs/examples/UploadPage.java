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
package de.alpharogroup.wicket.dialogs.examples;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.extensions.ajax.markup.html.modal.ModalWindow;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import de.alpharogroup.wicket.dialogs.examples.panel.UploadFilePanel;

/**
 * @author admin
 */
public class UploadPage extends WebPage
{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	public UploadPage(final PageParameters parameters)
	{
		super(parameters);
		final ModalWindow uploadFileDialog = new ModalWindow("uploadFileDialog");

		add(uploadFileDialog);

		final UploadFilePanel uploadFilePanel = new UploadFilePanel("content");
		uploadFileDialog.setContent(uploadFilePanel);

		add(new AjaxLink<Void>("showUpdoadFileDialog")
		{
			/**
			 * The serialVersionUID.
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void onClick(final AjaxRequestTarget target)
			{
				uploadFileDialog.show(target);
			}
		});
	}


}
