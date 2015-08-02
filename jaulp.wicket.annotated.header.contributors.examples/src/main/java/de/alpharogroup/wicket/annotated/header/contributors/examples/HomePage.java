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
package de.alpharogroup.wicket.annotated.header.contributors.examples;

import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.request.resource.ContextRelativeResourceReference;
import org.apache.wicket.request.resource.ResourceReference;

import de.alpharogroup.io.annotations.ImportResource;
import de.alpharogroup.io.annotations.ImportResources;
import de.alpharogroup.wicket.annotated.header.contributors.abase.BasePage;
import de.alpharogroup.wicket.annotated.header.contributors.examples.panels.footer.FooterPanel;
import de.alpharogroup.wicket.annotated.header.contributors.examples.panels.home.HomePanel;

/**
 * Homepage
 */
@ImportResources(resources = { @ImportResource(resourceName = "HomePage.css", resourceType = "css", index = 0) })
public class HomePage extends BasePage
{

	private static final long serialVersionUID = 1L;
	// 15.5 Context-relative resources
	// https://ci.apache.org/projects/wicket/guide/6.x/guide/resources.html#resources_5
	public static final ResourceReference MAIN_CSS = new ContextRelativeResourceReference(
		"css/main.css", false);

	/**
	 * Constructor that is invoked when page is invoked without a session.
	 *
	 * @param parameters
	 *            Page parameters
	 */
	public HomePage(final PageParameters parameters)
	{
		super(parameters);

		add(new HomePanel("homePanel"));

		add(new FooterPanel("footerPanel"));

	}

	@Override
	public void renderHead(final IHeaderResponse response)
	{
		super.renderHead(response);
		response.render(CssHeaderItem.forReference(MAIN_CSS));
	}

}