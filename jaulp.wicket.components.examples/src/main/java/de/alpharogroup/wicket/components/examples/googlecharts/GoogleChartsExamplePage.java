package de.alpharogroup.wicket.components.examples.googlecharts;

import org.apache.wicket.Component;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.jaulp.wicket.behaviors.resource.references.TimelineChartPanel;
import org.wicketstuff.annotation.mount.MountPath;

import de.alpharogroup.wicket.components.examples.area.publicly.PubliclyBasePage;

@MountPath("public/googlecharts")
public class GoogleChartsExamplePage extends PubliclyBasePage<Object>
{
	private static final long serialVersionUID = 1L;

	public GoogleChartsExamplePage(final PageParameters parameters)
	{
		super(parameters);
	}

	@Override
	public Component getContainerPanel()
	{
		return new TimelineChartPanel(CONTAINER_PANEL_ID);
	}
}