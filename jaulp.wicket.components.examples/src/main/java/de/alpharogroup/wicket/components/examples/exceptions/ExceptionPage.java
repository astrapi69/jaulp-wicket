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
package de.alpharogroup.wicket.components.examples.exceptions;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.component.IRequestablePage;
import org.wicketstuff.annotation.mount.MountPath;

import de.alpharogroup.wicket.components.examples.area.publicly.PubliclyBasePage;
import de.alpharogroup.wicket.components.examples.home.HomePage;
import de.alpharogroup.wicket.components.report.ReportThrowablePanel;

@MountPath("public/exception")
public class ExceptionPage extends PubliclyBasePage<Exception>
{
	private static final long serialVersionUID = 1L;
	private final Exception exception;

	public ExceptionPage()
	{
		this(new IllegalArgumentException("exception example..."));
	}

	public ExceptionPage(final Exception exception)
	{
		super(Model.of(exception));
		setModelObject(exception);
		this.exception = exception;
	}

	@Override
	public Panel getContainerPanel()
	{

		return new ReportThrowablePanel(CONTAINER_PANEL_ID, getModelObject())
		{
			private static final long serialVersionUID = 1L;

			@Override
			protected String newAffectedUsername()
			{
				return "Guest";
			}

			@Override
			protected Class<? extends IRequestablePage> newResponsePageClass()
			{
				return HomePage.class;
			}

			@Override
			protected String newRootUsername()
			{
				return "rootUser";
			}

			@Override
			protected void onSubmitError(final AjaxRequestTarget target)
			{
				// the description of the user...
				// Object model = getDefaultModelObject();
				// the real exception...
				// Exception exception = PubliclyExcptionPage.this.getModelObject();
				// send an email with the descripton...
				setResponsePage(HomePage.class);
			}

		};
	}

	public Exception getException()
	{
		return exception;
	}
}