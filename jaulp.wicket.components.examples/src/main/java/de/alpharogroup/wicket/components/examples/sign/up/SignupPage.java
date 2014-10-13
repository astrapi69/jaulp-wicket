package de.alpharogroup.wicket.components.examples.sign.up;

import org.apache.wicket.Component;

import de.alpharogroup.wicket.components.examples.area.publicly.PubliclyBasePage;

public class SignupPage extends PubliclyBasePage<Object> {
	private static final long serialVersionUID = 1L;

	@Override
	public Component getContainerPanel() {
		return new SignupFormPanel(CONTAINER_PANEL_ID);
	}
}
