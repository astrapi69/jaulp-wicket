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

import java.io.Serializable;

import org.apache.wicket.util.lang.Args;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.alpharogroup.wicket.base.util.template.JavascriptGenerator;

/**
 * The Class TooltipsterJsGenerator generates the javascript with a TooltipsterSettings object and a
 * given component id.
 */
public class TooltipsterJsGenerator extends JavascriptGenerator<TooltipsterSettings>
	implements
		Serializable
{

	/**
	 * The serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	/** The LOGGER. */
	protected static final Logger LOGGER = LoggerFactory.getLogger(TooltipsterJsGenerator.class
		.getName());

	/**
	 * Instantiates a new {@link TooltipsterJsGenerator}.
	 *
	 * @param componentId
	 *            the component id
	 */
	public TooltipsterJsGenerator(final String componentId)
	{
		this(TooltipsterSettings.builder().build(), componentId);
	}

	/**
	 * Instantiates a new {@link TooltipsterJsGenerator} with the given {@link TooltipsterSettings}.
	 *
	 * @param settings
	 *            the settings for the tooltipster plugin.
	 *
	 * @param componentId
	 *            the component id
	 */
	public TooltipsterJsGenerator(final TooltipsterSettings settings, final String componentId)
	{
		super(settings);
		setComponentId(Args.notEmpty(componentId, "componentId"));
		setWithComponentId(true);
		setMethodName("tooltipster");
	}

}
