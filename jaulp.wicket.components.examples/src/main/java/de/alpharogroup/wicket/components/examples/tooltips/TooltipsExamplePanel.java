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
package de.alpharogroup.wicket.components.examples.tooltips;

import org.apache.wicket.Application;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import de.alpharogroup.wicket.behaviors.JavascriptAppenderBehavior;
import de.alpharogroup.wicket.components.factory.ComponentFactory;
import de.alpharogroup.wicket.js.addon.tooltipster.TooltipsterJsGenerator;
import de.alpharogroup.wicket.js.addon.tooltipster.TooltipsterResourceReference;
import de.alpharogroup.wicket.js.addon.tooltipster.TooltipsterSettings;

/**
 * The Panel for the example page with the several tooltips like tooltipster or bootstrap tooltip.
 */
public class TooltipsExamplePanel extends Panel
{

	/**
	 * The serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	public TooltipsExamplePanel(final String id, final IModel<?> model)
	{
		super(id, model);
		final Label label = ComponentFactory.newLabel("tooltipTestLabel",
			Model.of("Im example for tooltipster."));
		final TooltipsterSettings tooltipsterSettings = TooltipsterSettings.builder().build();
		tooltipsterSettings.getAnimation().setValue("grow");
		tooltipsterSettings.getArrow().setValue(false);
		tooltipsterSettings.getContent().setValue("Loading foo...");
		final TooltipsterJsGenerator tooltipsterJsGenerator = new TooltipsterJsGenerator(
			tooltipsterSettings, label.getMarkupId());
		final String js = tooltipsterJsGenerator.generateJs();
		label.add(JavascriptAppenderBehavior.builder().id("tooltip_" + label.getMarkupId())
			.javascript(js).build());
		add(label);
	}


	/**
	 * {@inheritDoc}
	 */
	@Override
	public void renderHead(final IHeaderResponse response)
	{
		super.renderHead(response);
		response.render(JavaScriptHeaderItem.forReference(Application.get()
			.getJavaScriptLibrarySettings().getJQueryReference()));
		response.render(JavaScriptHeaderItem.forReference(TooltipsterResourceReference.INSTANCE));
	}
}
