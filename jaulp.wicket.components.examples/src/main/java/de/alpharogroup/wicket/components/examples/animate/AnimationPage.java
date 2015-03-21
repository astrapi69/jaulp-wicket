package de.alpharogroup.wicket.components.examples.animate;

import org.apache.wicket.Component;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.wicketstuff.annotation.mount.MountPath;

import de.alpharogroup.wicket.components.examples.area.publicly.PubliclyBasePage;

@MountPath("public/animations")
public class AnimationPage extends PubliclyBasePage<Object>
{


	/**
	 * The serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	public AnimationPage(PageParameters parameters)
	{
		super(parameters);
	}

	@Override
	public Component getContainerPanel()
	{
		return new AnimationPanel(CONTAINER_PANEL_ID, getDefaultModel());
	}

}
