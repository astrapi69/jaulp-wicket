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
package de.alpharogroup.wicket.header.contributors;

import java.util.Set;

import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.CssReferenceHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.markup.head.JavaScriptReferenceHeaderItem;
import org.apache.wicket.request.resource.CssResourceReference;
import org.apache.wicket.request.resource.JavaScriptResourceReference;

import de.alpharogroup.wicket.PackageResourceReferenceWrapper;
import de.alpharogroup.wicket.PackageResourceReferences;
import de.alpharogroup.wicket.base.enums.ResourceReferenceType;

/**
 * The Class WicketComponentExtensions is a helper class for the migration from wicket-version 1.4.x
 * to 1.5.x or 1.5.x to 6.1.0.
 * 
 * @author Asterios Raptis
 */
public final class HeaderResponseExtensions
{

	/**
	 * Render header response.
	 * 
	 * @param response
	 *            the response
	 * @param componentClass
	 *            the component class
	 */
	public static void renderHeaderResponse(final IHeaderResponse response,
		final Class<?> componentClass)
	{
		final Set<PackageResourceReferenceWrapper> headerContributors = PackageResourceReferences
			.getInstance().getPackageResourceReference(componentClass);
		if ((null != headerContributors) && !headerContributors.isEmpty())
		{
			for (final PackageResourceReferenceWrapper packageResourceReference : headerContributors)
			{
				if (packageResourceReference.getType().equals(ResourceReferenceType.JS))
				{
					final JavaScriptResourceReference reference = new JavaScriptResourceReference(
						componentClass,
						packageResourceReference.getPackageResourceReference().getName());
					if (!response.wasRendered(reference))
					{
						final JavaScriptReferenceHeaderItem headerItem = JavaScriptHeaderItem
							.forReference(reference);
						response.render(headerItem);
					}
				}
				if (packageResourceReference.getType().equals(ResourceReferenceType.CSS))
				{
					final CssResourceReference reference = new CssResourceReference(componentClass,
						packageResourceReference.getPackageResourceReference().getName());
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
