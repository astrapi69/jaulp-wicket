package de.alpharogroup.wicket.components.examples.socialnet;

import org.apache.wicket.Component;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import de.alpharogroup.wicket.components.examples.area.publicly.PubliclyBasePage;

/**
 * Example page for social networks like fb, twitter, google+ etc.
 */
public class SocialNetworksExamplePage extends PubliclyBasePage<SocialNetworkBean>
{

	private static final long serialVersionUID = 1L;

	public SocialNetworksExamplePage(final PageParameters parameters)
	{
		super(parameters);
	}

	@Override
	public Component getContainerPanel()
	{
		return new SocialNetworksExamplePanel(CONTAINER_PANEL_ID);
	}
}
