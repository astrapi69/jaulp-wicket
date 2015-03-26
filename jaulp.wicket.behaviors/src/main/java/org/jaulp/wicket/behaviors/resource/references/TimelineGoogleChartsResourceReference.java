package org.jaulp.wicket.behaviors.resource.references;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.markup.head.HeaderItem;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.request.Url;
import org.apache.wicket.request.resource.JavaScriptResourceReference;
import org.apache.wicket.request.resource.UrlResourceReference;

public class TimelineGoogleChartsResourceReference extends JavaScriptResourceReference
{

	/**
	 * The serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	public TimelineGoogleChartsResourceReference()
	{
		super(TimelineGoogleChartsResourceReference.class, "timeline.js");
	}

	/**
	 * Specify that charts.js depends on Google JS APIs and Wicket WebSocket JavaScript
	 * 
	 * @return a list of dependencies
	 */
	@Override
	public List<HeaderItem> getDependencies()
	{
		List<HeaderItem> dependencies = new ArrayList<HeaderItem>();
		dependencies.add(JavaScriptHeaderItem.forReference(new UrlResourceReference(Url
			.parse("https://www.google.com/jsapi"))));
		return dependencies;
	}

	public String newJavascriptFile()
	{
		return "timeline.js";
	}
}
