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
package org.jaulp.wicket;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import net.sourceforge.jaulp.io.annotations.ImportResource;
import net.sourceforge.jaulp.io.annotations.ImportResourcesUtils;

import org.apache.wicket.request.resource.PackageResourceReference;
import org.jaulp.wicket.base.enums.ResourceReferenceType;

/**
 * The Class PackageResourceReferences.
 */
public class PackageResourceReferences
{

	/** The Constant instance. */
	private final static PackageResourceReferences instance = new PackageResourceReferences();

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
	 * Instantiates a new package resource references.
	 */
	private PackageResourceReferences()
	{
		super();
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
			packageResourceReferences = addFoundPackageResourceReferences(
				packageResourceReferences, iface);
		}
		return packageResourceReferences;
	}

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
		if (packageResourceReferences != null && !packageResourceReferences.isEmpty())
		{
			if (prr != null && !prr.isEmpty())
			{
				packageResourceReferences.addAll(prr);
			}
			else
			{

			}
		}
		else
		{
			if (prr != null && !prr.isEmpty())
			{
				packageResourceReferences = prr;
			}
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
	public Set<PackageResourceReferenceWrapper> getPackageResourceReference(Class<?> componentClass)
	{
		Set<PackageResourceReferenceWrapper> packageResourceReference = PackageResourceReferences
			.getInstance().getPackageResourceReferenceMap().get(componentClass);
		packageResourceReference = addPackageResourceReferenceFromInterfaces(
			packageResourceReference, componentClass);
		return packageResourceReference;
	}

	/**
	 * Initialize resources.
	 *
	 * @param packageName
	 *            the package name
	 * @throws ClassNotFoundException
	 *             the class not found exception
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public void initializeResources(String packageName) throws ClassNotFoundException, IOException
	{
		final Map<Class<?>, ImportResource[]> resourcesMap = ImportResourcesUtils
			.getImportResources(packageName);

		for (Entry<Class<?>, ImportResource[]> entry : resourcesMap.entrySet())
		{
			final Class<?> key = entry.getKey();
			final ImportResource[] value = entry.getValue();
			final Set<PackageResourceReferenceWrapper> packageResourceReferences = new LinkedHashSet<>();
			for (final ImportResource importResource : value)
			{
				if (importResource.resourceType().equalsIgnoreCase("js"))
				{
					PackageResourceReference t = new PackageResourceReference(key,
						importResource.resourceName());

					packageResourceReferences.add(new PackageResourceReferenceWrapper(t,
						ResourceReferenceType.JS));
				}
				else if (importResource.resourceType().equalsIgnoreCase("css"))
				{
					PackageResourceReference t = new PackageResourceReference(key,
						importResource.resourceName());
					packageResourceReferences.add(new PackageResourceReferenceWrapper(t,
						ResourceReferenceType.CSS));
				}
			}
			PackageResourceReferences.getInstance().getPackageResourceReferenceMap()
				.put(key, packageResourceReferences);
		}

	}

}
