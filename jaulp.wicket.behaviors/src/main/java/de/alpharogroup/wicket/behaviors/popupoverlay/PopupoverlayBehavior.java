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

import java.util.HashMap;
import java.util.Map;

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
import org.apache.wicket.util.template.PackageTextTemplate;
import org.apache.wicket.util.template.TextTemplate;

import de.alpharogroup.wicket.base.util.template.TextTemplateUtils;

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

	/** The popupoverlay template. */
	private final TextTemplate popupoverlayTemplate = new PackageTextTemplate(
		PopupoverlayBehavior.class, "popupoverlay-template.js.tmpl");

	/**
	 * Default constructor.
	 */
	public PopupoverlayBehavior()
	{
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
	 * Generate js.
	 *
	 * @param textTemplate
	 *            the text template
	 * @return the string
	 */
	protected String generateJS(final TextTemplate textTemplate)
	{
		final Map<String, Object> variables = new HashMap<>();
		variables.put("componentId", this.component.getMarkupId());
		TextTemplateUtils.setVariableWithSingleQuotationMarks("type", this.settings.getType()
			.getValue(), variables);
		variables.put("autoopen", this.settings.getAutoopen().getValue());
		variables.put("scrolllock", this.settings.getScrolllock().getValue());
		variables.put("background", this.settings.getBackground().getValue());
		variables.put("backgroundactive", this.settings.getBackgroundactive().getValue());
		TextTemplateUtils.setVariableWithSingleQuotationMarks("color", this.settings.getColor()
			.getValue(), variables);
		TextTemplateUtils.setVariableWithSingleQuotationMarks("opacity", this.settings.getOpacity()
			.getValue(), variables);
		TextTemplateUtils.setVariableWithSingleQuotationMarks("horizontal", this.settings
			.getHorizontal().getValue(), variables);
		TextTemplateUtils.setVariableWithSingleQuotationMarks("vertical", this.settings
			.getVertical().getValue(), variables);
		variables.put("offsettop", this.settings.getOffsettop().getValue());
		variables.put("offsetleft", this.settings.getOffsetleft().getValue());
		variables.put("escape", this.settings.getEscape().getValue());
		variables.put("blur", this.settings.getBlur().getValue());
		variables.put("setzindex", this.settings.getSetzindex().getValue());
		variables.put("autozindex", this.settings.getAutozindex().getValue());
		variables.put("keepfocus", this.settings.getKeepfocus().getValue());
		TextTemplateUtils.setVariableWithSingleQuotationMarks("focuselement", this.settings
			.getFocuselement().getValue(), variables);
		variables.put("focusdelay", this.settings.getFocusdelay().getValue());
		TextTemplateUtils.setVariableWithSingleQuotationMarks("pagecontainer", this.settings
			.getPagecontainer().getValue(), variables);
		variables.put("outline", this.settings.getOutline().getValue());
		variables.put("detach", this.settings.getDetach());
		TextTemplateUtils.setVariableWithSingleQuotationMarks("openelement", this.settings
			.getOpenelement().getValue(), variables);
		TextTemplateUtils.setVariableWithSingleQuotationMarks("closeelement", this.settings
			.getCloseelement().getValue(), variables);
		TextTemplateUtils.setVariableWithSingleQuotationMarks("transition", this.settings
			.getTransition().getValue(), variables);
		TextTemplateUtils.setVariableWithSingleQuotationMarks("tooltipanchor", this.settings
			.getTooltipanchor().getValue(), variables);
		TextTemplateUtils.setVariable("beforeopen", this.settings.getBeforeopen().getValue(),
			variables);
		TextTemplateUtils.setVariable("onopen", this.settings.getOnopen().getValue(), variables);
		TextTemplateUtils.setVariable("onclose", this.settings.getOnclose().getValue(), variables);
		TextTemplateUtils.setVariable("opentransitionend", this.settings.getOpentransitionend()
			.getValue(), variables);
		TextTemplateUtils.setVariable("closetransitionend", this.settings.getClosetransitionend()
			.getValue(), variables);
		textTemplate.interpolate(variables);
		return textTemplate.asString();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void renderHead(Component c, final IHeaderResponse response)
	{
		response.render(JavaScriptHeaderItem.forReference(Application.get()
			.getJavaScriptLibrarySettings().getJQueryReference()));
		response.render(JavaScriptHeaderItem
			.forReference(PopupoverlayBehavior.POPUPOVERLAY_PLUGIN_REFERENCE));
		response.render(OnLoadHeaderItem.forScript(generateJS(popupoverlayTemplate)));
	}

}