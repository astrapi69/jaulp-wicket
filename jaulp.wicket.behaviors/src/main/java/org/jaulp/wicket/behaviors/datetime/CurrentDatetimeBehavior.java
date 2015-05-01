package org.jaulp.wicket.behaviors.datetime;

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
import org.apache.wicket.util.template.PackageTextTemplate;
import org.apache.wicket.util.template.TextTemplate;
import org.jaulp.wicket.behaviors.popupoverlay.PopupoverlaySettings;

/**
 * TODO Document
 */
public class CurrentDatetimeBehavior extends Behavior
{

	/**
	 * The serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * The {@link org.apache.wicket.request.resource.ResourceReference} constant for the js-file
	 * from the jquery-datetime-plugin.
	 */
	public static final ResourceReference DATETIME_PLUGIN_REFERENCE = new JavaScriptResourceReference(
		CurrentDatetimeBehavior.class, "jquery-datetime-plugin.js");

	/** The component. */
	private Component component;

	/** The settings. */
	@Setter
	private PopupoverlaySettings settings = new PopupoverlaySettings();

	/** The popupoverlay template. */
	private final TextTemplate datetimeTemplate = new PackageTextTemplate(
		CurrentDatetimeBehavior.class, "datetime-plugin.js.tmpl");

	/**
	 * Default constructor.
	 */
	public CurrentDatetimeBehavior()
	{
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
			.forReference(CurrentDatetimeBehavior.DATETIME_PLUGIN_REFERENCE));
		String js = generateJS(datetimeTemplate);
		System.out.println(js);
		response.render(OnLoadHeaderItem.forScript(generateJS(datetimeTemplate)));
	}

}
