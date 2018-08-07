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
package de.alpharogroup.wicket;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.HeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.request.resource.PackageResourceReference;

import de.alpharogroup.io.annotations.ImportResource;
import de.alpharogroup.io.annotations.ImportResourcesExtensions;
import de.alpharogroup.wicket.base.enums.ResourceReferenceType;
import lombok.NoArgsConstructor;

/**
 * The Class PackageResourceReferences.
 */
@NoArgsConstructor
public class PackageResourceReferences
{

	/** The Constant instance. */
	private final static PackageResourceReferences instance = new PackageResourceReferences();

	/**
	 * Adds the given css files to the given response object in the given scope.
	 *
	 * @param response
	 *            the {@link org.apache.wicket.markup.head.IHeaderResponse}
	 * @param scope
	 *            The scope of the css files.
	 * @param cssFilenames
	 *            The css file names.
	 */
	public static void addCssFiles(final IHeaderResponse response, final Class<?> scope,
		final String... cssFilenames)
	{
		for (final String cssFilename : cssFilenames)
		{
			final HeaderItem item = CssHeaderItem
				.forReference(new PackageResourceReference(scope, cssFilename));
			response.render(item);
		}
	}

	/**
	 * Adds the given javascript files to the given response object in the given scope.
	 *
	 * @param response
	 *            the {@link org.apache.wicket.markup.head.IHeaderResponse}
	 * @param scope
	 *            The scope of the javascript files.
	 * @param jsFilenames
	 *            The javascript file names.
	 */
	public static void addJsFiles(final IHeaderResponse response, final Class<?> scope,
		final String... jsFilenames)
	{
		for (final String jsFilename : jsFilenames)
		{
			final HeaderItem item = JavaScriptHeaderItem
				.forReference(new PackageResourceReference(scope, jsFilename));
			response.render(item);
		}
	}

	/**
	 * Gets the single instance of PackageResourceReferences.
	 *
	 * @return single instance of PackageResourceReferences
	 */
	public static PackageResourceReferences getInstance()
	{
		return instance;
	}

	/** The package resource reference map. */
	private final Map<Class<?>, Set<PackageResourceReferenceWrapper>> packageResourceReferenceMap = new LinkedHashMap<>();

	/**
	 * Adds the found package resource references.
	 *
	 * @param packageResourceReferences
	 *            the package resource references
	 * @param iface
	 *            the iface
	 * @return the sets the
	 */
	private Set<PackageResourceReferenceWrapper> addFoundPackageResourceReferences(
		Set<PackageResourceReferenceWrapper> packageResourceReferences, final Class<?> iface)
	{
		final Set<PackageResourceReferenceWrapper> prr = PackageResourceReferences.getInstance()
			.getPackageResourceReferenceMap().get(iface);
		if ((packageResourceReferences != null) && !packageResourceReferences.isEmpty())
		{
			if ((prr != null) && !prr.isEmpty())
			{
				packageResourceReferences.addAll(prr);
			}
			else
			{

			}
		}
		else
		{
			if ((prr != null) && !prr.isEmpty())
			{
				packageResourceReferences = prr;
			}
		}
		return packageResourceReferences;
	}

	/**
	 * Adds the package resource reference from interfaces.
	 *
	 * @param packageResourceReferences
	 *            the package resource references
	 * @param searchClass
	 *            the search class
	 * @return 's a set with the founded interfaces from the given search class.
	 */
	private Set<PackageResourceReferenceWrapper> addPackageResourceReferenceFromInterfaces(
		Set<PackageResourceReferenceWrapper> packageResourceReferences, final Class<?> searchClass)
	{
		final Class<?>[] interfaces = searchClass.getInterfaces();
		for (final Class<?> iface : interfaces)
		{
			packageResourceReferences = addFoundPackageResourceReferences(packageResourceReferences,
				iface);
		}
		return packageResourceReferences;
	}

	/**
	 * Gets the package resource reference.
	 *
	 * @param componentClass
	 *            the component class
	 * @return the package resource reference
	 */
	public Set<PackageResourceReferenceWrapper> getPackageResourceReference(
		final Class<?> componentClass)
	{
		Set<PackageResourceReferenceWrapper> packageResourceReference = PackageResourceReferences
			.getInstance().getPackageResourceReferenceMap().get(componentClass);
		packageResourceReference = addPackageResourceReferenceFromInterfaces(
			packageResourceReference, componentClass);
		return packageResourceReference;
	}

	/**
	 * Gets the package resource reference map.
	 *
	 * @return the package resource reference map
	 */
	public Map<Class<?>, Set<PackageResourceReferenceWrapper>> getPackageResourceReferenceMap()
	{
		return packageResourceReferenceMap;
	}

	/**
	 * Initialize resources from the given packages.
	 *
	 * @param packageNames
	 *            the package names
	 * @throws ClassNotFoundException
	 *             occurs if a given class cannot be located by the specified class loader
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 * @throws URISyntaxException
	 *             is thrown if a string could not be parsed as a URI reference.
	 */
	public void initializeResources(final List<String> packageNames)
		throws ClassNotFoundException, IOException, URISyntaxException
	{
		for (final String packageName : packageNames)
		{
			initializeResources(packageName);
		}
	}

	/**
	 * Initialize resources from the given package.
	 *
	 * @param packageName
	 *            the package name
	 * @throws ClassNotFoundException
	 *             occurs if a given class cannot be located by the specified class loader
	 * @throws IOException
	 *             Signals that an I/O exception has occurred. 
	 * @throws URISyntaxException
	 *             is thrown if a string could not be parsed as a URI reference.
	 */
	public void initializeResources(final String packageName)
		throws ClassNotFoundException, IOException, URISyntaxException
	{
		final Map<Class<?>, ImportResource[]> resourcesMap = ImportResourcesExtensions
			.getImportResources(packageName);

		for (final Entry<Class<?>, ImportResource[]> entry : resourcesMap.entrySet())
		{
			final Class<?> key = entry.getKey();
			final ImportResource[] value = entry.getValue();
			final Set<PackageResourceReferenceWrapper> packageResourceReferences = new LinkedHashSet<>();
			for (final ImportResource importResource : value)
			{
				if (importResource.resourceType().equalsIgnoreCase("js"))
				{
					final PackageResourceReference t = new PackageResourceReference(key,
						importResource.resourceName());

					packageResourceReferences
						.add(new PackageResourceReferenceWrapper(t, ResourceReferenceType.JS));
				}
				else if (importResource.resourceType().equalsIgnoreCase("css"))
				{
					final PackageResourceReference t = new PackageResourceReference(key,
						importResource.resourceName());
					packageResourceReferences
						.add(new PackageResourceReferenceWrapper(t, ResourceReferenceType.CSS));
				}
			}
			PackageResourceReferences.getInstance().getPackageResourceReferenceMap().put(key,
				packageResourceReferences);
		}
	}

	/**
	 * Initialize resources from the given packages.
	 *
	 * @param packageNames
	 *            the package names
	 * @throws ClassNotFoundException
	 *             occurs if a given class cannot be located by the specified class loader
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 * @throws URISyntaxException
	 *             is thrown if a string could not be parsed as a URI reference.
	 */
	public void initializeResources(final String[] packageNames)
		throws ClassNotFoundException, IOException, URISyntaxException
	{
		initializeResources(Arrays.asList(packageNames));
	}

}
