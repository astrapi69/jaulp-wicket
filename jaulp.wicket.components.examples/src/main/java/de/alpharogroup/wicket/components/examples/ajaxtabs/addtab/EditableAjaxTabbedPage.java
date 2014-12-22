package de.alpharogroup.wicket.components.examples.ajaxtabs.addtab;

import java.util.ArrayList;

import net.sourceforge.jaulp.io.annotations.ImportResource;
import net.sourceforge.jaulp.io.annotations.ImportResources;

import org.apache.wicket.Component;
import org.apache.wicket.model.Model;
import org.wicketstuff.annotation.mount.MountPath;

import de.alpharogroup.wicket.components.examples.ajaxtabs.addable.AddableTabbedPanel;
import de.alpharogroup.wicket.components.examples.ajaxtabs.tabpanels.TabModel;
import de.alpharogroup.wicket.components.examples.ajaxtabs.tabpanels.TabbedPanelModels;
import de.alpharogroup.wicket.components.examples.area.publicly.PubliclyBasePage;


/**
 * Ajax Tabbed panel demo to add or remove a tab.
 */
@ImportResources(resources = { @ImportResource(resourceName = "TabbedPanelPage.css", resourceType = "css", index = 1) })
@MountPath("public/ajaxtabs")
public class EditableAjaxTabbedPage  extends PubliclyBasePage<Object> {
	private static final long serialVersionUID = 1L;


	@Override
	public Component getContainerPanel() {
		TabbedPanelModels<String> tabmodels = new TabbedPanelModels<String>();		
		tabmodels.setTabModels(new ArrayList<TabModel<String>>());
		TabModel<String> firstTabModel = new TabModel<>(
				Model.of("tab 1"), Model.of("TAB_1"), Model.of("x"));
		TabModel<String> secondTabModel = new TabModel<>(
				Model.of("tab 2"), Model.of("TAB_2"), Model.of("x"));
		TabModel<String> thirdTabModel = new TabModel<>(
				Model.of("tab 3"), Model.of("TAB_3"), Model.of("x"));
		tabmodels.add(firstTabModel).add(secondTabModel).add(thirdTabModel);
		return new AddableTabbedPanel(CONTAINER_PANEL_ID, Model.of(tabmodels));
	}

}