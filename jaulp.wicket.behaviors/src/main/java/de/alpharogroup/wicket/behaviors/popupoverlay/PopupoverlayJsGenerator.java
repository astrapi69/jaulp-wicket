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
package de.alpharogroup.wicket.behaviors.popupoverlay;

import org.apache.wicket.util.lang.Args;

import de.alpharogroup.wicket.base.util.template.JavascriptGenerator;

/**
 * The Class PopupoverlayJsGenerator generates the javascript with a PopupoverlaySettings object.
 */
public class PopupoverlayJsGenerator extends JavascriptGenerator<PopupoverlaySettings>
{

	/**
	 * The serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new {@link PopupoverlayJsGenerator}.
	 *
	 * @param componentId
	 *            the component id
	 */
	public PopupoverlayJsGenerator(final String componentId)
	{
		this(PopupoverlaySettings.builder().build(), componentId);
	}

	/**
	 * Instantiates a new {@link PopupoverlayJsGenerator} with the given
	 * {@link PopupoverlaySettings}.
	 *
	 * @param settings
	 *            the settings for the jquery-popup-overlay plugin.
	 *
	 * @param componentId
	 *            the component id
	 */
	public PopupoverlayJsGenerator(PopupoverlaySettings settings, final String componentId)
	{
		super(settings);
		setComponentId(Args.notEmpty(componentId, "componentId"));
		setWithComponentId(true);
		setMethodName("popup");
	}

}
