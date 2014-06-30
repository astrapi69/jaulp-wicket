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
package org.jaulp.wicket.base.examples;

import net.sourceforge.jaulp.io.annotations.ImportResource;
import net.sourceforge.jaulp.io.annotations.ImportResources;

import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.CssReferenceHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.model.IModel;
import org.apache.wicket.request.resource.CssResourceReference;
import org.jaulp.wicket.base.BasePanel;
import org.jaulp.wicket.base.components.labeled.examples.LabeledHomePage;
import org.jaulp.wicket.base.components.viewmode.examples.ViewOrEditPage;
import org.jaulp.wicket.base.examples.urls.WicketUrlPage;
import org.jaulp.wicket.base.examples.velocity.DynamicPage;
import org.jaulp.wicket.components.menu.suckerfish.MenuItem;
import org.jaulp.wicket.components.menu.suckerfish.MenuPanel;
import org.jaulp.wicket.components.menu.suckerfish.MenuUtils;


@ImportResources(resources = { // This css file is currently loaded...
		@ImportResource(resourceName = "MenubarPanel.css", resourceType = "css") })
public class MenubarPanel extends BasePanel {

	
	public MenubarPanel(String id) {
		super(id);
		MenuPanel menuBar = new MenuPanel("menuBar");
		add(menuBar);
		
		initializeMenu(menuBar);
	}
	
	public MenubarPanel(String id, IModel<?> model) {
		super(id, model);
		// Another way to load your css file is to override the renderHead method...
		MenuPanel menuBar = new MenuPanel("menuBar"){
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			/**
			 * {@inheritDoc}
			 */
			@Override
			public void renderHead(IHeaderResponse response) {
				super.renderHead(response);
				// add your css file here...
				
				CssResourceReference reference = new CssResourceReference(
						MenubarPanel.class, "MenubarPanel.css");
				if (!response.wasRendered(reference)) {
					CssReferenceHeaderItem headerItem = CssHeaderItem
							.forReference(reference);
					response.render(headerItem);
				}
			}
		};
		add(menuBar);
		
		initializeMenu(menuBar);
	}

	private void initializeMenu(MenuPanel menuBar) {
		
		MenuItem miHome = MenuUtils.createMenuItem(
				HomePage.class, "top.menu.home", this);

		menuBar.addMenu(miHome);
		
		MenuItem miAnother = MenuUtils.createMenuItem(
				AnotherPage.class, "top.menu.another", this);

		menuBar.addMenu(miAnother);

		MenuItem subMenuFromMiAnother =  MenuUtils.createMenuItem(
				SubmenuPage.class, "top.menu.sub.another.overview", this);
		miAnother.addMenu(subMenuFromMiAnother);

		MenuItem subMenuDynamic =  MenuUtils.createMenuItem(
				DynamicPage.class, "top.menu.sub.dynamic.overview", this);
		miAnother.addMenu(subMenuDynamic);

		MenuItem miViewOrEdit = MenuUtils.createMenuItem(
				ViewOrEditPage.class, "top.menu.viewmode", this);
		menuBar.addMenu(miViewOrEdit);
		
		MenuItem miUrls = MenuUtils.createMenuItem(
				WicketUrlPage.class, "top.menu.urls", this);
		menuBar.addMenu(miUrls);
		
		MenuItem miLabeled = MenuUtils.createMenuItem(
				LabeledHomePage.class, "top.menu.labeled", this);

		menuBar.addMenu(miLabeled);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

}
