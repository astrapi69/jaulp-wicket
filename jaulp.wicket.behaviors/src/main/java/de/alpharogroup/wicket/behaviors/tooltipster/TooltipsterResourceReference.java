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
package de.alpharogroup.wicket.behaviors.tooltipster;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.HeaderItem;
import org.apache.wicket.request.resource.CssResourceReference;
import org.apache.wicket.request.resource.JavaScriptResourceReference;

/**
 * The Class TooltipsterResourceReference holds the references(js and css) for the jquery plugin tooltipster.
 */
public class TooltipsterResourceReference extends JavaScriptResourceReference
{

	/** The serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new TooltipsterResourceReference.
	 */
	public TooltipsterResourceReference()
	{
		super(TooltipsterResourceReference.class, "jquery.tooltipster.min.js");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Iterable<? extends HeaderItem> getDependencies()
	{
		List<HeaderItem> dependencies = new ArrayList<HeaderItem>();
		dependencies.add(CssHeaderItem.forReference(new CssResourceReference(
			TooltipsterResourceReference.class, "tooltipster.css")));
		return dependencies;
	}

}
