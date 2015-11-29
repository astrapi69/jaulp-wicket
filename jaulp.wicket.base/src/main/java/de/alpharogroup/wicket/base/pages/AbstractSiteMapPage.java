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
package de.alpharogroup.wicket.base.pages;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;
import org.apache.wicket.markup.MarkupType;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.PropertyListView;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.wicketstuff.annotation.mount.MountPath;

import de.alpharogroup.lang.AnnotationExtensions;
import de.alpharogroup.wicket.base.util.url.WicketUrlExtensions;

/**
 * The Class AbstractSiteMapPage is the base class for subclass a SiteMapPage.
 */
public abstract class AbstractSiteMapPage extends WebPage
{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The Constant logger. */
	private static final Logger LOGGER = Logger.getLogger(AbstractSiteMapPage.class.getName());

	/** The Constant PATTERN. */
	private static final Pattern PATTERN = Pattern.compile("^\\./");


	/**
	 * Instantiates a new abstract site map page.
	 */
	public AbstractSiteMapPage()
	{
		this.add(new PropertyListView<SiteUrl>("urls", this.newListModel())
		{

			private static final long serialVersionUID = 1L;

			@Override
			protected void populateItem(final ListItem<SiteUrl> item)
			{
				item.add(new Label("loc"));
				item.add(new Label("lastmod", "2014-05-08"));
			}

		});
	}

	/**
	 * Gets the all page classes.
	 *
	 * @return the all page classes
	 */
	protected abstract List<? extends Class<? extends WebPage>> getAllPageClasses();

	/**
	 * Gets the all page classes quietly.
	 *
	 * @return the all page classes quietly
	 */
	@SuppressWarnings("unchecked")
	protected List<? extends Class<? extends WebPage>> getAllPageClassesQuietly()
	{
		final List<Class<? extends WebPage>> pages = new ArrayList<>();
		try
		{
			final Set<Class<?>> set = AnnotationExtensions.getAllAnnotatedClasses(getPackageName(),
				MountPath.class);
			for (final Class<?> class1 : set)
			{
				pages.add((Class<? extends WebPage>)class1);
			}
		}
		catch (final ClassCastException e)
		{
			LOGGER.error(e.getClass().getName()
				+ " occured while scanning for MountPath annotations.", e);
		}
		catch (final ClassNotFoundException e)
		{
			LOGGER.error(e.getClass().getName()
				+ " occured while scanning for MountPath annotations.", e);
		}
		catch (final IOException e)
		{
			LOGGER.error(e.getClass().getName()
				+ " occured while scanning for MountPath annotations.", e);
		}
		return pages;
	}

	/**
	 * Gets the base url.
	 *
	 * @return the base url
	 */
	protected String getBaseUrl()
	{
		return WicketUrlExtensions.getDomainUrl(false);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public MarkupType getMarkupType()
	{
		return new MarkupType("html", "text/xml");
	}

	/**
	 * Gets the package name where to search for page classes.
	 *
	 * @return the package name
	 */
	protected abstract String getPackageName();

	/**
	 * New list model.
	 *
	 * @return the i model
	 */
	private IModel<List<SiteUrl>> newListModel()
	{
		return new LoadableDetachableModel<List<SiteUrl>>()
		{

			private static final long serialVersionUID = 1L;

			@Override
			protected List<SiteUrl> load()
			{
				final List<SiteUrl> list = new ArrayList<>();

				for (final Class<? extends WebPage> type : getAllPageClasses())
				{
					String loc = PATTERN.matcher(AbstractSiteMapPage.this.urlFor(type, null))
						.replaceFirst(getBaseUrl());

					if (loc.endsWith("/."))
					{
						loc = loc.replace("/.", "");
					}

					list.add(new SiteUrl(loc));
				}

				return list;
			}
		};
	}
}