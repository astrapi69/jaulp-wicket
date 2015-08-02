/**
 * Copyright (C) 2010 Asterios Raptis
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package de.alpharogroup.wicket.behaviors.resource.references;

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
		final List<HeaderItem> dependencies = new ArrayList<HeaderItem>();
		dependencies.add(JavaScriptHeaderItem.forReference(new UrlResourceReference(Url
			.parse("https://www.google.com/jsapi"))));
		return dependencies;
	}

	public String newJavascriptFile()
	{
		return "timeline.js";
	}
}
