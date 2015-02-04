package de.alpharogroup.wicket.components.examples.labeled;

import org.apache.wicket.Component;
import org.wicketstuff.annotation.mount.MountPath;

import de.alpharogroup.wicket.components.examples.area.publicly.PubliclyBasePage;

@MountPath("public/labeled/components")
public class LabeledComponentsPage extends PubliclyBasePage<Object>
{
	private static final long serialVersionUID = 1L;

	@Override
	public Component getContainerPanel()
	{
		return new LabeledComponentsPanel(CONTAINER_PANEL_ID);
	}
}
