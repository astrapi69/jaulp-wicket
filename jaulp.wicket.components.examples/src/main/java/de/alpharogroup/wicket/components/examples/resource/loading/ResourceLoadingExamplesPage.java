package de.alpharogroup.wicket.components.examples.resource.loading;

import org.apache.log4j.Logger;
import org.apache.wicket.Component;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import de.alpharogroup.wicket.components.examples.area.publicly.PubliclyBasePage;

/**
 * Example page examples how to load resources from IStringResourceLoader.
 */
public class ResourceLoadingExamplesPage extends PubliclyBasePage<ResourceLoadingBean>
{

	/** The Constant logger. */
	protected static final Logger LOGGER = Logger.getLogger(ResourceLoadingExamplesPage.class
		.getName());

	private static final long serialVersionUID = 1L;

	public ResourceLoadingExamplesPage(final PageParameters parameters)
	{
		super(parameters);
	}

	public ResourceLoadingExamplesPage()
	{
	}

	@Override
	public Component getContainerPanel()
	{
		return new ResourceLoadingExamplesPanel(CONTAINER_PANEL_ID);
	}
}
