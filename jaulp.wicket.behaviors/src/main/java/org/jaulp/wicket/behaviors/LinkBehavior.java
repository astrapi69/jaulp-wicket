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
package org.jaulp.wicket.behaviors;

import org.apache.wicket.Component;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.request.cycle.RequestCycle;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.util.lang.Args;
import org.jaulp.wicket.base.util.WicketComponentUtils;

/**
 * The Class LinkBehavior adds the ability to a component to act as a link,  i.e. like a
 * tablerow tag( &lt;tr&gt; ) or a list item (&lt;li&gt;) the ability to act as a link. For
 * instance in repeaters like the DataView.
 */
public class LinkBehavior extends Behavior
{

	/**
	 * The serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;

	/** The onmouseover color. */
	private final String onmouseoverColor;

	/** The onmouseout color. */
	private final String onmouseoutColor;

	/** The absolute path. */
	private final String absolutePath;

	/**
	 * Instantiates a new tablerow link behavior.
	 * 
	 * @param onmouseoverColor
	 *            the onmouseover color
	 * @param onmouseoutColor
	 *            the onmouseout color
	 * @param absolutePath
	 *            the absolute path
	 */
	public LinkBehavior(final String onmouseoverColor, final String onmouseoutColor,
		final String absolutePath)
	{
		super();
		Args.notNull(onmouseoverColor, "onmouseoverColor");
		Args.notNull(onmouseoutColor, "onmouseoutColor");
		Args.notNull(absolutePath, "absolutePath");
		this.onmouseoverColor = onmouseoverColor;
		this.onmouseoutColor = onmouseoutColor;
		this.absolutePath = absolutePath;
	}

	/**
	 * Instantiates a new tablerow link behavior.
	 *
	 * @param onmouseoverColor
	 *            the onmouseover color
	 * @param onmouseoutColor
	 *            the onmouseout color
	 * @param targetPage
	 *            the target page
	 * @param pageParameters
	 *            the page parameters
	 */
	public LinkBehavior(final String onmouseoverColor, final String onmouseoutColor,
		final Class<? extends WebPage> targetPage, final PageParameters pageParameters)
	{
		super();
		Args.notNull(onmouseoverColor, "onmouseoverColor");
		Args.notNull(onmouseoutColor, "onmouseoutColor");
		Args.notNull(targetPage, "targetPage");
		this.onmouseoverColor = onmouseoverColor;
		this.onmouseoutColor = onmouseoutColor;
		String url = RequestCycle.get().urlFor(targetPage, pageParameters).toString();
		this.absolutePath = WicketComponentUtils.toAbsolutePath(url);
	}

	/**
	 * Instantiates a new tablerow link behavior.
	 *
	 * @param onmouseoverColor
	 *            the onmouseover color
	 * @param onmouseoutColor
	 *            the onmouseout color
	 * @param targetPage
	 *            the target page
	 * @param pageParameters
	 *            the page parameters
	 */
	public LinkBehavior(final String onmouseoverColor, final String onmouseoutColor,
		final WebPage targetPage, final PageParameters pageParameters)
	{
		super();
		Args.notNull(onmouseoverColor, "onmouseoverColor");
		Args.notNull(onmouseoutColor, "onmouseoutColor");
		Args.notNull(targetPage, "targetPage");
		this.onmouseoverColor = onmouseoverColor;
		this.onmouseoutColor = onmouseoutColor;
		String url = RequestCycle.get().urlFor(targetPage.getClass(), pageParameters).toString();
		this.absolutePath = WicketComponentUtils.toAbsolutePath(url);
	}

	/**
	 * (non-Javadoc)
	 */
	@Override
	public void onComponentTag(final Component component, final ComponentTag tag)
	{

		tag.put("onmouseover", "this.style.backgroundColor = '" + onmouseoverColor
			+ "';this.style.cursor = 'pointer';");

		tag.put("onmouseout", "this.style.backgroundColor = '" + onmouseoutColor
			+ "';this.style.cursor ='default';");

		tag.put("onclick", "document.location.href = '" + absolutePath + "';");

	}

}
