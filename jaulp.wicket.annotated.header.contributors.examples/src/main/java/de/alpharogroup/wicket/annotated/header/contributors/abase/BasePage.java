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
package de.alpharogroup.wicket.annotated.header.contributors.abase;

import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.IModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import de.alpharogroup.io.annotations.ImportResource;
import de.alpharogroup.io.annotations.ImportResources;
import de.alpharogroup.locale.ResourceBundleKey;
import de.alpharogroup.wicket.base.AbstractBasePage;
import de.alpharogroup.wicket.base.util.resource.ResourceModelFactory;
import de.alpharogroup.wicket.header.contributors.HeaderResponseExtensions;

/**
 * The Class BasePage.
 *
 * @author Asterios Raptis
 */
@ImportResources(resources = { @ImportResource(resourceName = "BasePage.js", resourceType = "js"),
		@ImportResource(resourceName = "BasePage.css", resourceType = "css") })
public abstract class BasePage extends AbstractBasePage
{

	/**
	 * The serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;

	/** The title. */
	private IModel<String> title;

	/**
	 * Instantiates a new base page.
	 */
	public BasePage()
	{
		this(new PageParameters());
	}

	/**
	 * Instantiates a new base page.
	 *
	 * @param parameters
	 *            the parameters
	 */
	public BasePage(final PageParameters parameters)
	{
		super(parameters);
		title = ResourceModelFactory.newResourceModel(ResourceBundleKey.builder().key("page.title")
			.defaultValue("").build(), this);
		add(new Label("title", title));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void renderHead(final IHeaderResponse response)
	{
		super.renderHead(response);
		HeaderResponseExtensions.renderHeaderResponse(response, BasePage.class);
	}

}
