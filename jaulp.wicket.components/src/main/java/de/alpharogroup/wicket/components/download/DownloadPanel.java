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

import static org.wicketeer.modelfactory.ModelFactory.from;
import static org.wicketeer.modelfactory.ModelFactory.model;

import java.io.IOException;

import lombok.Getter;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.model.IModel;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.util.lang.Args;
import org.apache.wicket.util.resource.IResourceStream;
import org.jaulp.wicket.base.BasePanel;
import org.jaulp.wicket.base.util.application.ApplicationUtils;
import org.jaulp.wicket.behaviors.AjaxDownloadBehavior;

import de.alpharogroup.wicket.components.factory.ComponentFactory;

public abstract class DownloadPanel extends BasePanel<DownloadModel>
{

	private static final long serialVersionUID = 1L;

	@Getter
	private Component fileNameLabel;
	@Getter
	private AjaxLink<Void> downloadLink;

	public DownloadPanel(String id, final IModel<DownloadModel> model)
	{
		super(id, model);
		Args.notNull(model, "model");
	}

	@Override
	protected void onInitialize()
	{
		super.onInitialize();
		fileNameLabel = newFileNameLabel("fileName", model(from(getModelObject()).getFilename()));
		downloadLink = newDownloadLink("downloadLink", getModel());
		downloadLink.addOrReplace(fileNameLabel);
		addOrReplace(downloadLink);
	}

	protected abstract WebApplication getWebApplication();

	protected AjaxLink<Void> newDownloadLink(final String id, final IModel<DownloadModel> model)
	{
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
		AjaxLink<Void> downloadLink = new AjaxLink<Void>("downloadLink")
		{
			private static final long serialVersionUID = 1L;

			@Override
			public void onClick(AjaxRequestTarget target)
			{
				download.initiate(target);
			}
		};
		downloadLink.add(download);
		return downloadLink;
	}

	protected Component newFileNameLabel(final String id, IModel<String> model)
	{
		return ComponentFactory.newLabel(id, model);
	}

}
