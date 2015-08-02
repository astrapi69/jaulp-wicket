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
package de.alpharogroup.wicket.base.mainbase;

import java.util.Set;

import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.CssReferenceHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.markup.head.JavaScriptReferenceHeaderItem;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.request.resource.CssResourceReference;
import org.apache.wicket.request.resource.JavaScriptResourceReference;

import de.alpharogroup.io.annotations.ImportResource;
import de.alpharogroup.io.annotations.ImportResources;
import de.alpharogroup.wicket.PackageResourceReferenceWrapper;
import de.alpharogroup.wicket.PackageResourceReferences;
import de.alpharogroup.wicket.base.BasePage;
import de.alpharogroup.wicket.base.enums.ResourceReferenceType;

/**
 * The Class BasePage.
 * 
 * @author Asterios Raptis
 */
@ImportResources(resources = {
		@ImportResource(resourceName = "BaseMainPage.js", resourceType = "js"),
		@ImportResource(resourceName = "BaseMainPage.css", resourceType = "css") })
public abstract class ApplicationBasePage extends BasePage
{

	/**
	 * The serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;


	/**
	 * Instantiates a new base page.
	 */
	public ApplicationBasePage()
	{
		super();
	}

	/**
	 * Instantiates a new base page.
	 * 
	 * @param parameters
	 *            the parameters
	 */
	public ApplicationBasePage(final PageParameters parameters)
	{
		super(parameters);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void renderHead(final IHeaderResponse response)
	{
		super.renderHead(response);
		final Set<PackageResourceReferenceWrapper> headerContributors = PackageResourceReferences
			.getInstance().getPackageResourceReference(ApplicationBasePage.class);
		if (null != headerContributors && !headerContributors.isEmpty())
		{
			for (final PackageResourceReferenceWrapper packageResourceReference : headerContributors)
			{
				if (packageResourceReference.getType().equals(ResourceReferenceType.JS))
				{
					final JavaScriptResourceReference reference = new JavaScriptResourceReference(
						ApplicationBasePage.class, packageResourceReference
							.getPackageResourceReference().getName());
					if (!response.wasRendered(reference))
					{
						final JavaScriptReferenceHeaderItem headerItem = JavaScriptHeaderItem
							.forReference(reference);
						response.render(headerItem);
					}
				}
				if (packageResourceReference.getType().equals(ResourceReferenceType.CSS))
				{
					final CssResourceReference reference = new CssResourceReference(
						ApplicationBasePage.class, packageResourceReference
							.getPackageResourceReference().getName());
					if (!response.wasRendered(reference))
					{
						final CssReferenceHeaderItem headerItem = CssHeaderItem
							.forReference(reference);
						response.render(headerItem);
					}
				}
			}
		}
	}

}