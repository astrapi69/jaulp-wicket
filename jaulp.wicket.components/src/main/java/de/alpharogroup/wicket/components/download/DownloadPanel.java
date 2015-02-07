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
package de.alpharogroup.wicket.components.download;

import java.io.IOException;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.IModel;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.util.lang.Args;
import org.apache.wicket.util.resource.IResourceStream;
import org.jaulp.wicket.base.BasePanel;
import org.jaulp.wicket.base.util.application.ApplicationUtils;
import org.jaulp.wicket.behaviors.AjaxDownloadBehavior;

public abstract class DownloadPanel extends BasePanel<DownloadModel>
{

	private static final long serialVersionUID = 1L;

	public DownloadPanel(String id, final IModel<DownloadModel> model)
	{
		super(id, model);
		Args.notNull(model, "model");
		final AjaxDownloadBehavior download = new AjaxDownloadBehavior()
		{
			private static final long serialVersionUID = 1L;

			@Override
			protected IResourceStream getResourceStream()
			{
				try
				{
					return ApplicationUtils.getResourceStream(getWebApplication(), model
						.getObject().getPath(), model.getObject().getContentType());
				}
				catch (IOException e)
				{
					getSession().info("Error: " + e.getLocalizedMessage());
				}
				return null;
			}

			@Override
			protected String getFileName()
			{
				return model.getObject().getFilename();
			}
		};

		Label fileNameLabel = new Label("fileName", model.getObject().getFilename());
		AjaxLink<Void> downloadLink = new AjaxLink<Void>("pdfLink")
		{
			private static final long serialVersionUID = 1L;

			@Override
			public void onClick(AjaxRequestTarget target)
			{
				download.initiate(target);
			}
		};
		downloadLink.add(download);
		downloadLink.add(fileNameLabel);
		addOrReplace(downloadLink);
	}

	protected abstract WebApplication getWebApplication();

}
