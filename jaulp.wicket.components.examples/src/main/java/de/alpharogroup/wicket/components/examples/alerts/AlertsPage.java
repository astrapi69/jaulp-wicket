package de.alpharogroup.wicket.components.examples.alerts;

import org.apache.wicket.Component;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import de.alpharogroup.wicket.components.examples.area.publicly.PubliclyBasePage;

public class AlertsPage extends PubliclyBasePage<Object> {
	private static final long serialVersionUID = 1L;

	@Override
	public Component getContainerPanel() {
		return new AlertsPanel(CONTAINER_PANEL_ID);
	}
	
	public AlertsPage(final PageParameters parameters) {
		super(parameters);
	}
}