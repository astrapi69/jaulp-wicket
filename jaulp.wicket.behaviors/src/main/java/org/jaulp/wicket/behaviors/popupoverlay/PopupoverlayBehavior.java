package org.jaulp.wicket.behaviors.popupoverlay;

import java.util.HashMap;
import java.util.Map;

import org.apache.wicket.Application;
import org.apache.wicket.Component;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.markup.head.OnLoadHeaderItem;
import org.apache.wicket.request.resource.JavaScriptResourceReference;
import org.apache.wicket.request.resource.ResourceReference;
import org.apache.wicket.util.template.PackageTextTemplate;
import org.apache.wicket.util.template.TextTemplate;

public class PopupoverlayBehavior extends Behavior {

	/**
	 * The serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;

	public static final ResourceReference pluginReference = new JavaScriptResourceReference(
			PopupoverlayBehavior.class, "jquery.popupoverlay.js");

	private Component component;
	private PopupoverlaySettings settings = new PopupoverlaySettings();

	private final TextTemplate wicketAlertTemplate = new PackageTextTemplate(
			PopupoverlayBehavior.class, "popupoverlay-template.js");

	public PopupoverlayBehavior() {
	}

	public PopupoverlayBehavior(PopupoverlaySettings settings) {
		this.settings = settings;
	}

	@Override
	public void bind(final Component component) {
		super.bind(component);
		this.component = component;
		this.component.setOutputMarkupId(true);
	}

	protected String generateJS(final TextTemplate textTemplate) {
		final Map<String, Object> variables = new HashMap<String, Object>();
		variables.put("componentId", this.component.getMarkupId());
		variables.put("type", "'" + this.settings.getType() + "'");
		variables.put("autoopen", this.settings.isAutoopen());
		variables.put("scrolllock", this.settings.isScrolllock());
		variables.put("background", this.settings.isBackground());
		variables.put("backgroundactive", this.settings.isBackgroundactive());
		variables.put("color", "'" + this.settings.getColor() + "'");
		variables.put("opacity", "'" + this.settings.getOpacity() + "'");
		variables.put("horizontal", "'" + this.settings.getHorizontal() + "'");
		variables.put("vertical", "'" + this.settings.getVertical() + "'");
		variables.put("offsettop", this.settings.getOffsettop());
		variables.put("offsetleft", this.settings.getOffsetleft());
		variables.put("escape", this.settings.isEscape());
		variables.put("blur", this.settings.isBlur());
		variables.put("setzindex", this.settings.isSetzindex());
		variables.put("autozindex", this.settings.isAutozindex());
		variables.put("keepfocus", this.settings.isKeepfocus());
		setVariableWithSingeQuotationMarks("focuselement",
				this.settings.getFocuselement(), variables);
		variables.put("focusdelay", this.settings.getFocusdelay());
		setVariableWithSingeQuotationMarks("pagecontainer",
				this.settings.getPagecontainer(), variables);
		variables.put("outline", this.settings.isOutline());
		variables.put("detach", this.settings.isDetach());
		setVariableWithSingeQuotationMarks("openelement",
				this.settings.getOpenelement(), variables);
		setVariableWithSingeQuotationMarks("closeelement",
				this.settings.getCloseelement(), variables);
		setVariableWithSingeQuotationMarks("transition",
				this.settings.getTransition(), variables);
		setVariableWithSingeQuotationMarks("tooltipanchor",
				this.settings.getTooltipanchor(), variables);
		setVariable("beforeopen", this.settings.getBeforeopen(), variables);
		setVariable("onopen", this.settings.getOnopen(), variables);
		setVariable("onclose", this.settings.getOnclose(), variables);
		setVariable("opentransitionend", this.settings.getOpentransitionend(),
				variables);
		setVariable("closetransitionend",
				this.settings.getClosetransitionend(), variables);
		textTemplate.interpolate(variables);
		return textTemplate.asString();
	}

	protected void setVariable(String variablename, Object object,
			final Map<String, Object> variables) {
		if (object != null) {
			variables.put(variablename, object);
		} else {
			variables.put(variablename, "null");
		}
	}

	protected void setVariableWithSingeQuotationMarks(String variablename,
			Object object, final Map<String, Object> variables) {
		if (object != null) {
			variables.put(variablename, "'" + object + "'");
		} else {
			variables.put(variablename, "null");
		}
	}

	@Override
	public void renderHead(Component c, final IHeaderResponse response) {
		response.render(JavaScriptHeaderItem.forReference(Application.get()
				.getJavaScriptLibrarySettings().getJQueryReference()));
		response.render(JavaScriptHeaderItem.forReference(pluginReference));
		response.render(OnLoadHeaderItem
				.forScript(generateJS(wicketAlertTemplate)));
	}

	public void setSettings(PopupoverlaySettings settings) {
		this.settings = settings;
	}

}