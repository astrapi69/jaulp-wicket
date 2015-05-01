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

import de.alpharogroup.io.annotations.ImportResource;
import de.alpharogroup.io.annotations.ImportResources;

import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import de.alpharogroup.wicket.base.BasePanel;
import de.alpharogroup.wicket.base.components.labeled.examples.LabeledHomePage;
import de.alpharogroup.wicket.base.components.viewmode.examples.ViewOrEditPage;
import de.alpharogroup.wicket.base.examples.sitemap.SiteMapPage;
import de.alpharogroup.wicket.base.examples.urls.WicketUrlPage;
import de.alpharogroup.wicket.base.examples.velocity.DynamicPage;
import de.alpharogroup.wicket.components.menu.suckerfish.MenuItem;
import de.alpharogroup.wicket.components.menu.suckerfish.MenuPanel;
import de.alpharogroup.wicket.components.menu.suckerfish.MenuUtils;


@SuppressWarnings("rawtypes")
@ImportResources(resources = { // This css file is currently loaded...
@ImportResource(resourceName = "MenubarPanel.css", resourceType = "css") })
public class MenubarPanel extends BasePanel
{

	private static final long serialVersionUID = 1L;

	public MenubarPanel(String id)
	{
		super(id);
		MenuPanel menuBar = new MenuPanel("menuBar");
		add(menuBar);

		initializeMenu(menuBar);
	}

	public MenubarPanel(String id, IModel<?> model)
	{
		super(id);
		// Another way to load your css file is to override the renderHead method...
		MenuPanel menuBar = new MenuPanel("menuBar");
		add(menuBar);

		initializeMenu(menuBar);
	}

	private void initializeMenu(MenuPanel menuBar)
	{

		MenuItem miHome = MenuUtils.newMenuItem(HomePage.class, "top.menu.home", this);

		menuBar.addMenu(miHome);

		MenuItem miAnother = MenuUtils.newMenuItem(AnotherPage.class, "top.menu.another", this);

		menuBar.addMenu(miAnother);

		MenuItem subMenuFromMiAnother = MenuUtils.newMenuItem(SubmenuPage.class,
			"top.menu.sub.another.overview", this);
		miAnother.addMenu(subMenuFromMiAnother);

		MenuItem subMenuDynamic = MenuUtils.newMenuItem(DynamicPage.class,
			"top.menu.sub.dynamic.overview", this);
		miAnother.addMenu(subMenuDynamic);

		MenuItem textMenu = MenuUtils.newMenuItem(Model.of("Only text"));
		miAnother.addMenu(textMenu);

		MenuItem miViewOrEdit = MenuUtils.newMenuItem(ViewOrEditPage.class, "top.menu.viewmode",
			this);
		menuBar.addMenu(miViewOrEdit);

		MenuItem miUrls = MenuUtils.newMenuItem(WicketUrlPage.class, "top.menu.urls", this);
		menuBar.addMenu(miUrls);

		MenuItem miLabeled = MenuUtils.newMenuItem(LabeledHomePage.class, "top.menu.labeled", this);

		menuBar.addMenu(miLabeled);

		MenuItem miSitemap = MenuUtils.newMenuItem(SiteMapPage.class, "top.menu.sitemap", this);

		menuBar.addMenu(miSitemap);

		MenuItem miModel = MenuUtils.newMenuItem(ModelPage.class, "top.menu.model", this);

		menuBar.addMenu(miModel);
	}

}
