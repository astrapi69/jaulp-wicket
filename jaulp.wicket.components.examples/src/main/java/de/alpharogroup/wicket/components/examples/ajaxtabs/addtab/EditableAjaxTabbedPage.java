package de.alpharogroup.wicket.components.examples.ajaxtabs.addtab;

import net.sourceforge.jaulp.io.annotations.ImportResource;
import net.sourceforge.jaulp.io.annotations.ImportResources;

import org.apache.wicket.Component;
import org.apache.wicket.model.Model;
import org.wicketstuff.annotation.mount.MountPath;

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
		return new EditableAjaxTabbedPanelDecorator(CONTAINER_PANEL_ID, Model.of(new TabbedPanelModel()));
	}

}