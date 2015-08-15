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
package de.alpharogroup.wicket.components.examples.pdfdownload;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.wicketstuff.annotation.mount.MountPath;

import de.alpharogroup.wicket.components.download.DownloadModelBean;
import de.alpharogroup.wicket.components.download.DownloadPanel;
import de.alpharogroup.wicket.components.examples.application.WicketApplication;
import de.alpharogroup.wicket.components.examples.area.publicly.PubliclyBasePage;


@MountPath("public/download/pdf")
public class PdfDownloadPage extends PubliclyBasePage<Object>
{
	private static final long serialVersionUID = 1L;

	public PdfDownloadPage(final PageParameters parameters)
	{
		super(parameters);
	}

	@Override
	public Component getContainerPanel()
	{
		final DownloadModelBean downloadModelBean = DownloadModelBean.builder().filename("download.pdf")
			.path("pdf/download.pdf").contentType("application/pdf").build();
		final DownloadPanel downloadPanel = new DownloadPanel(CONTAINER_PANEL_ID,
			Model.of(downloadModelBean))
		{
			private static final long serialVersionUID = 1L;

			@Override
			protected WebApplication getWebApplication()
			{
				return WicketApplication.get();
			}

			@Override
			protected AjaxLink<Void> newDownloadLink(final String id,
				final IModel<DownloadModelBean> model)
			{
				final AjaxLink<Void> downloadLink = super.newDownloadLink(id, model);
				downloadLink.add(new AttributeAppender("class", " btn btn-primary"));
				return downloadLink;
			}

		};
		return downloadPanel;
	}

}
