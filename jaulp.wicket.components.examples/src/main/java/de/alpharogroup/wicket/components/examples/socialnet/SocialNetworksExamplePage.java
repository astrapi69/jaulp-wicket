package de.alpharogroup.wicket.components.examples.socialnet;

import org.apache.log4j.Logger;
import org.apache.wicket.Component;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import de.alpharogroup.wicket.components.examples.area.publicly.PubliclyBasePage;

/**
 * Example page for social networks like fb, twitter, google+ etc.
 */
public class SocialNetworksExamplePage extends PubliclyBasePage<SocialNetworkBean>
{

	/** The Constant logger. */
	protected static final Logger LOGGER = Logger.getLogger(SocialNetworksExamplePage.class
		.getName());

	private static final long serialVersionUID = 1L;

	public SocialNetworksExamplePage(final PageParameters parameters)
	{
		super(parameters);
	}

	public SocialNetworksExamplePage()
	{
	}

	@Override
	public Component getContainerPanel()
	{
		return new SocialNetworksExamplePanel(CONTAINER_PANEL_ID);
	}
}
