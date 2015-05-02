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
package de.alpharogroup.wicket.base.examples.sitemap;

import java.util.List;

import org.apache.wicket.markup.html.WebPage;
import org.wicketstuff.annotation.mount.MountPath;

import de.alpharogroup.wicket.base.pages.AbstractSiteMapPage;


/**
 * The Class SiteMapPage generates a site map for all pages in this WebApplication.
 */
@MountPath("/sitemap.xml")
public class SiteMapPage extends AbstractSiteMapPage
{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected List<? extends Class<? extends WebPage>> getAllPageClasses()
	{
		return getAllPageClassesQuietly();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected String getPackageName()
	{
		return "de.alpharogroup.wicket.base";
	}

}