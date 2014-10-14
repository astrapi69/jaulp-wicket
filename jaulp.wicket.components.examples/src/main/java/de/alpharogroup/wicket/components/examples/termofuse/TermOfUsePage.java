package de.alpharogroup.wicket.components.examples.termofuse;

import org.apache.wicket.Component;
import org.apache.wicket.model.Model;
import org.wicketstuff.annotation.mount.MountPath;

import de.alpharogroup.wicket.components.examples.area.publicly.PubliclyBasePage;

@MountPath("public/term/of/use")
public class TermOfUsePage extends PubliclyBasePage<Object> {
	private static final long serialVersionUID = 1L;

	@Override
	public Component getContainerPanel() {
		return new ApplicationTermOfUsePanel(CONTAINER_PANEL_ID,
				Model.of(ApplicationTermOfUseModel.getInstance().getModel()));
	}
}
