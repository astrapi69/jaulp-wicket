package org.jaulp.wicket.behaviors.resource.references;

import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.markup.html.panel.Panel;

public class TimelineChartPanel extends Panel
{

	/**
	 * The serialVersionUID
	 */
	private static final long serialVersionUID = -8437630842183347649L;

	public TimelineChartPanel(String id)
	{
		super(id);
	}

	@Override
	public void renderHead(IHeaderResponse response)
	{
		super.renderHead(response);
		response.render(JavaScriptHeaderItem
			.forReference(new TimelineGoogleChartsResourceReference()));
	}
}