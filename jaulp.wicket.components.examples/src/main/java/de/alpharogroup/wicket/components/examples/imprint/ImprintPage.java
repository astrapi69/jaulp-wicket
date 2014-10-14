package de.alpharogroup.wicket.components.examples.imprint;

import org.apache.wicket.Component;
import org.wicketstuff.annotation.mount.MountPath;

import de.alpharogroup.wicket.components.examples.area.publicly.PubliclyBasePage;

/**
 * The Class ImprintPage.
 * 
 * @author Asterios Raptis
 */
@MountPath("public/imprint")
public class ImprintPage extends PubliclyBasePage<Object> {
	private static final long serialVersionUID = 1L;

	@Override
	public Component getContainerPanel() {
		return new ApplicationImprintPanel(CONTAINER_PANEL_ID);
	}

}