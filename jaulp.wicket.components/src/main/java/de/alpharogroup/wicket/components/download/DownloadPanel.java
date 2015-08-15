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

import lombok.Getter;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.util.lang.Args;
import org.apache.wicket.util.resource.IResourceStream;

import de.alpharogroup.wicket.base.BasePanel;
import de.alpharogroup.wicket.base.util.application.ApplicationExtensions;
import de.alpharogroup.wicket.behaviors.AjaxDownloadBehavior;
import de.alpharogroup.wicket.components.factory.ComponentFactory;

/**
 * The component DownloadPanel have a download link with a download label from the filename.
 */
public abstract class DownloadPanel extends BasePanel<DownloadModelBean>
{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Gets the file name label.
	 *
	 * @return the file name label
	 */
	@Getter
	private Component fileNameLabel;

	/**
	 * Gets the download link.
	 *
	 * @return the download link
	 */
	@Getter
	private AjaxLink<Void> downloadLink;

	/**
	 * Instantiates a new download panel.
	 *
	 * @param id
	 *            the id
	 * @param model
	 *            the model
	 */
	public DownloadPanel(final String id, final IModel<DownloadModelBean> model)
	{
		super(id, model);
		Args.notNull(model, "model");
	}

	/**
	 * Gets the web application.
	 *
	 * @return the web application
	 */
	protected abstract WebApplication getWebApplication();

	/**
	 * New download link.
	 *
	 * @param id
	 *            the id
	 * @param model
	 *            the model
	 * @return the ajax link
	 */
	protected AjaxLink<Void> newDownloadLink(final String id, final IModel<DownloadModelBean> model)
	{
		final AjaxDownloadBehavior download = new AjaxDownloadBehavior()
		{
			/** The Constant serialVersionUID. */
			private static final long serialVersionUID = 1L;

			/**
			 * {@inheritDoc}
			 */
			@Override
			protected String getFileName()
			{
				return model.getObject().getFilename();
			}

			/**
			 * {@inheritDoc}
			 */
			@Override
			protected IResourceStream getResourceStream()
			{
				try
				{
					return ApplicationExtensions.getResourceStream(getWebApplication(), model
						.getObject().getPath(), model.getObject().getContentType());
				}
				catch (final IOException e)
				{
					getSession().info("Error: " + e.getLocalizedMessage());
				}
				return null;
			}
		};
		final AjaxLink<Void> downloadLink = new AjaxLink<Void>("downloadLink")
		{
			/** The Constant serialVersionUID. */
			private static final long serialVersionUID = 1L;

			/**
			 * {@inheritDoc}
			 */
			@Override
			public void onClick(final AjaxRequestTarget target)
			{
				download.initiate(target);
			}
		};
		downloadLink.add(download);
		return downloadLink;
	}

	/**
	 * New file name label.
	 *
	 * @param id
	 *            the id
	 * @param model
	 *            the model
	 * @return the component
	 */
	protected Component newFileNameLabel(final String id, final IModel<String> model)
	{
		return ComponentFactory.newLabel(id, model);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void onInitialize()
	{
		super.onInitialize();
		fileNameLabel = newFileNameLabel("fileName", new PropertyModel<String>(getModelObject(),
			"filename"));
		downloadLink = newDownloadLink("downloadLink", getModel());
		downloadLink.addOrReplace(fileNameLabel);
		addOrReplace(downloadLink);
	}

}
