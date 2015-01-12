package de.alpharogroup.wicket.components.examples.radios;

import org.apache.wicket.Component;
import org.wicketstuff.annotation.mount.MountPath;

import de.alpharogroup.wicket.components.examples.area.publicly.PubliclyBasePage;

@MountPath("public/radio/components")
public class RadioComponentsExamplePage  extends PubliclyBasePage<Object> {
	private static final long serialVersionUID = 1L;

	@Override
	public Component getContainerPanel() {
		return new RadioComponentsPanel(CONTAINER_PANEL_ID);
	}

}
