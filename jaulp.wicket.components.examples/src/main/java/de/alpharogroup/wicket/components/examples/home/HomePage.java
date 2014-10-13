package de.alpharogroup.wicket.components.examples.home;
import org.apache.wicket.Component;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.wicketstuff.annotation.mount.MountPath;

import de.alpharogroup.wicket.components.examples.area.publicly.PubliclyBasePage;

@MountPath("public/home")
public class HomePage extends PubliclyBasePage<HomeModel> {
	private static final long serialVersionUID = 1L;

	public HomePage(final PageParameters parameters) {
		super(parameters);
	}

	@Override
	public Component getContainerPanel() {
		return new HomePanel(CONTAINER_PANEL_ID);
	}
}