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

import de.alpharogroup.wicket.behaviors.util.TextTemplateUtils;

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
		TextTemplateUtils.setVariableWithSingleQuotationMarks("type", this.settings.getType(),
			variables);
		variables.put("autoopen", this.settings.isAutoopen());
		variables.put("scrolllock", this.settings.isScrolllock());
		variables.put("background", this.settings.isBackground());
		variables.put("backgroundactive", this.settings.isBackgroundactive());
		TextTemplateUtils.setVariableWithSingleQuotationMarks("color", this.settings.getColor(),
			variables);
		TextTemplateUtils.setVariableWithSingleQuotationMarks("opacity",
			this.settings.getOpacity(), variables);
		TextTemplateUtils.setVariableWithSingleQuotationMarks("horizontal",
			this.settings.getHorizontal(), variables);
		TextTemplateUtils.setVariableWithSingleQuotationMarks("vertical",
			this.settings.getVertical(), variables);
		variables.put("offsettop", this.settings.getOffsettop());
		variables.put("offsetleft", this.settings.getOffsetleft());
		variables.put("escape", this.settings.isEscape());
		variables.put("blur", this.settings.isBlur());
		variables.put("setzindex", this.settings.isSetzindex());
		variables.put("autozindex", this.settings.isAutozindex());
		variables.put("keepfocus", this.settings.isKeepfocus());
		TextTemplateUtils.setVariableWithSingleQuotationMarks("focuselement",
			this.settings.getFocuselement(), variables);
		variables.put("focusdelay", this.settings.getFocusdelay());
		TextTemplateUtils.setVariableWithSingleQuotationMarks("pagecontainer",
			this.settings.getPagecontainer(), variables);
		variables.put("outline", this.settings.isOutline());
		variables.put("detach", this.settings.isDetach());
		TextTemplateUtils.setVariableWithSingleQuotationMarks("openelement",
			this.settings.getOpenelement(), variables);
		TextTemplateUtils.setVariableWithSingleQuotationMarks("closeelement",
			this.settings.getCloseelement(), variables);
		TextTemplateUtils.setVariableWithSingleQuotationMarks("transition",
			this.settings.getTransition(), variables);
		TextTemplateUtils.setVariableWithSingleQuotationMarks("tooltipanchor",
			this.settings.getTooltipanchor(), variables);
		TextTemplateUtils.setVariable("beforeopen", this.settings.getBeforeopen(), variables);
		TextTemplateUtils.setVariable("onopen", this.settings.getOnopen(), variables);
		TextTemplateUtils.setVariable("onclose", this.settings.getOnclose(), variables);
		TextTemplateUtils.setVariable("opentransitionend", this.settings.getOpentransitionend(),
			variables);
		TextTemplateUtils.setVariable("closetransitionend", this.settings.getClosetransitionend(),
			variables);
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