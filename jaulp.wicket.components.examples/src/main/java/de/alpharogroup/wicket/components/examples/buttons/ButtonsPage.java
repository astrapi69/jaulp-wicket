package de.alpharogroup.wicket.components.examples.buttons;

import org.apache.wicket.Component;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.wicketstuff.annotation.mount.MountPath;

import de.alpharogroup.wicket.components.examples.area.publicly.PubliclyBasePage;

@MountPath("public/buttons")
public class ButtonsPage extends PubliclyBasePage<Object>
{
	private static final long serialVersionUID = 1L;

	@Override
	public Component getContainerPanel()
	{
		return new LocaleMenuPanel(CONTAINER_PANEL_ID);
	}

	public ButtonsPage(final PageParameters parameters)
	{
		super(parameters);
	}
}