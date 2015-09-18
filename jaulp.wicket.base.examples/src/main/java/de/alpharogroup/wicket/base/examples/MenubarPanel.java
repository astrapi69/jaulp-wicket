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
package de.alpharogroup.wicket.base.examples;

import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import de.alpharogroup.io.annotations.ImportResource;
import de.alpharogroup.io.annotations.ImportResources;
import de.alpharogroup.wicket.base.components.labeled.examples.LabeledHomePage;
import de.alpharogroup.wicket.base.components.viewmode.examples.ViewOrEditPage;
import de.alpharogroup.wicket.base.examples.sitemap.SiteMapPage;
import de.alpharogroup.wicket.base.examples.urls.WicketUrlPage;
import de.alpharogroup.wicket.base.examples.velocity.DynamicPage;
import de.alpharogroup.wicket.base.mainbase.ApplicationBasePanel;
import de.alpharogroup.wicket.components.menu.suckerfish.MenuItem;
import de.alpharogroup.wicket.components.menu.suckerfish.MenuPanel;
import de.alpharogroup.wicket.components.menu.suckerfish.MenuItemFactory;


@ImportResources(resources = { // This css file is currently loaded...
@ImportResource(resourceName = "MenubarPanel.css", resourceType = "css") })
public class MenubarPanel extends ApplicationBasePanel<Object>
{

	private static final long serialVersionUID = 1L;

	public MenubarPanel(final String id)
	{
		this(id, null);
	}

	public MenubarPanel(final String id, final IModel<Object> model)
	{
		super(id, model);
		// Another way to load your css file is to override the renderHead method...
		final MenuPanel menuBar = new MenuPanel("menuBar");
		add(menuBar);

		initializeMenu(menuBar);
	}

	private void initializeMenu(final MenuPanel menuBar)
	{

		final MenuItem miHome = MenuItemFactory.newMenuItem(HomePage.class, "top.menu.home", this);

		menuBar.addMenu(miHome);

		final MenuItem miAnother = MenuItemFactory.newMenuItem(AnotherPage.class, "top.menu.another",
			this);

		menuBar.addMenu(miAnother);

		final MenuItem subMenuFromMiAnother = MenuItemFactory.newMenuItem(SubmenuPage.class,
			"top.menu.sub.another.overview", this);
		miAnother.addMenu(subMenuFromMiAnother);

		final MenuItem subMenuDynamic = MenuItemFactory.newMenuItem(DynamicPage.class,
			"top.menu.sub.dynamic.overview", this);
		miAnother.addMenu(subMenuDynamic);

		final MenuItem textMenu = MenuItemFactory.newMenuItem(Model.of("Only text"));
		miAnother.addMenu(textMenu);

		final MenuItem miViewOrEdit = MenuItemFactory.newMenuItem(ViewOrEditPage.class,
			"top.menu.viewmode", this);
		menuBar.addMenu(miViewOrEdit);

		final MenuItem miUrls = MenuItemFactory.newMenuItem(WicketUrlPage.class, "top.menu.urls", this);
		menuBar.addMenu(miUrls);

		final MenuItem miLabeled = MenuItemFactory.newMenuItem(LabeledHomePage.class, "top.menu.labeled",
			this);

		menuBar.addMenu(miLabeled);

		final MenuItem miSitemap = MenuItemFactory.newMenuItem(SiteMapPage.class, "top.menu.sitemap",
			this);

		menuBar.addMenu(miSitemap);

		final MenuItem miModel = MenuItemFactory.newMenuItem(ModelPage.class, "top.menu.model", this);

		menuBar.addMenu(miModel);
	}

}
