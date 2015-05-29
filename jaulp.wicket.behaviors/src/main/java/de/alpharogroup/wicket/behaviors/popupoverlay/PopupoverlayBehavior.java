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

import lombok.Setter;

import org.apache.wicket.Application;
import org.apache.wicket.Component;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.markup.head.OnLoadHeaderItem;
import org.apache.wicket.request.resource.JavaScriptResourceReference;
import org.apache.wicket.request.resource.ResourceReference;
import org.apache.wicket.util.lang.Args;

/**
 * The Class PopupoverlayBehavior.
 */
public class PopupoverlayBehavior extends Behavior
{

	/**
	 * The serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;

	/** The {@link ResourceReference} constant for the js-file from the jquery.popupoverlay plugin. */
	public static final ResourceReference POPUPOVERLAY_PLUGIN_REFERENCE = new JavaScriptResourceReference(
		PopupoverlayBehavior.class, "jquery.popupoverlay.js");

	/** The component. */
	private Component component;

	/** The settings. */
	@Setter
	private PopupoverlaySettings settings = new PopupoverlaySettings();

	/**
	 * Default constructor.
	 */
	public PopupoverlayBehavior()
	{
		this(new PopupoverlaySettings());
	}

	/**
	 * Constructor with {@link PopupoverlaySettings}.
	 *
	 * @param settings
	 *            the settings
	 */
	public PopupoverlayBehavior(PopupoverlaySettings settings)
	{
		Args.notNull(settings, "settings");
		this.settings = settings;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void bind(final Component component)
	{
		super.bind(component);
		this.component = component;
		this.component.setOutputMarkupId(true);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void renderHead(Component component, final IHeaderResponse response)
	{
		response.render(JavaScriptHeaderItem.forReference(Application.get()
			.getJavaScriptLibrarySettings().getJQueryReference()));
		response.render(JavaScriptHeaderItem
			.forReference(PopupoverlayBehavior.POPUPOVERLAY_PLUGIN_REFERENCE));

		PopupoverlayJsGenerator generator = new PopupoverlayJsGenerator(this.settings);
		generator.setComponentId(this.component.getMarkupId());
		String javascript = generator.generatePopupoverlayJs(this.settings);

		response.render(OnLoadHeaderItem.forScript(javascript));
	}

}