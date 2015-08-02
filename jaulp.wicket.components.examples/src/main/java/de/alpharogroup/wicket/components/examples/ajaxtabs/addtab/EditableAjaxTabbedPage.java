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
package de.alpharogroup.wicket.components.examples.ajaxtabs.addtab;

import java.util.ArrayList;

import org.apache.wicket.Component;
import org.apache.wicket.model.Model;
import org.wicketstuff.annotation.mount.MountPath;

import de.alpharogroup.io.annotations.ImportResource;
import de.alpharogroup.io.annotations.ImportResources;
import de.alpharogroup.wicket.components.examples.ajaxtabs.addable.AddableTabbedPanel;
import de.alpharogroup.wicket.components.examples.ajaxtabs.tabpanels.TabModel;
import de.alpharogroup.wicket.components.examples.ajaxtabs.tabpanels.TabbedPanelModels;
import de.alpharogroup.wicket.components.examples.area.publicly.PubliclyBasePage;


/**
 * Ajax Tabbed panel demo to add or remove a tab.
 */
@ImportResources(resources = { @ImportResource(resourceName = "TabbedPanelPage.css", resourceType = "css", index = 1) })
@MountPath("public/ajaxtabs")
public class EditableAjaxTabbedPage extends PubliclyBasePage<Object>
{
	private static final long serialVersionUID = 1L;


	@Override
	public Component getContainerPanel()
	{
		final TabbedPanelModels<String> tabmodels = new TabbedPanelModels<String>();
		tabmodels.setTabModels(new ArrayList<TabModel<String>>());
		final TabModel<String> firstTabModel = new TabModel<>(Model.of("tab 1"), Model.of("TAB_1"),
			Model.of("x"));
		final TabModel<String> secondTabModel = new TabModel<>(Model.of("tab 2"),
			Model.of("TAB_2"), Model.of("x"));
		final TabModel<String> thirdTabModel = new TabModel<>(Model.of("tab 3"), Model.of("TAB_3"),
			Model.of("x"));
		tabmodels.add(firstTabModel).add(secondTabModel).add(thirdTabModel);
		return new AddableTabbedPanel(CONTAINER_PANEL_ID, Model.of(tabmodels));
	}

}