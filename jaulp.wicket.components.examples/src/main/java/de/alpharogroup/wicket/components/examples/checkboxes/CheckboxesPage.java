package de.alpharogroup.wicket.components.examples.checkboxes;

import org.apache.wicket.Component;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.wicketstuff.annotation.mount.MountPath;

import de.alpharogroup.wicket.components.examples.area.publicly.PubliclyBasePage;
@MountPath("public/checkboxes")
public class CheckboxesPage extends PubliclyBasePage<Object> {
	private static final long serialVersionUID = 1L;

	@Override
	public Component getContainerPanel() {
		return new CheckboxesPanel(CONTAINER_PANEL_ID, null);
	}
	
	public CheckboxesPage(final PageParameters parameters) {
		super(parameters);
	}
}