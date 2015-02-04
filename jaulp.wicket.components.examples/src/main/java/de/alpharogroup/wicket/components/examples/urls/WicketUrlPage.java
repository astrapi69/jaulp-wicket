package de.alpharogroup.wicket.components.examples.urls;

import org.apache.wicket.Component;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.wicketstuff.annotation.mount.MountPath;

import de.alpharogroup.wicket.components.examples.area.publicly.PubliclyBasePage;


@MountPath("public/wicketurls")
public class WicketUrlPage extends PubliclyBasePage<Object>
{
	private static final long serialVersionUID = 1L;

	@Override
	public Component getContainerPanel()
	{
		return new WicketUrlPanel(CONTAINER_PANEL_ID);
	}

	public WicketUrlPage(final PageParameters parameters)
	{
		super(parameters);
	}
}
