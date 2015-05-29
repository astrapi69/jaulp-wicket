package de.alpharogroup.wicket.components.examples.tooltips;

import org.apache.wicket.Application;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import de.alpharogroup.wicket.behaviors.JavascriptAppenderBehavior;
import de.alpharogroup.wicket.behaviors.tooltipster.TooltipsterJsGenerator;
import de.alpharogroup.wicket.behaviors.tooltipster.TooltipsterResourceReference;
import de.alpharogroup.wicket.behaviors.tooltipster.TooltipsterSettings;
import de.alpharogroup.wicket.components.factory.ComponentFactory;

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
		Label label = ComponentFactory.newLabel("tooltipTestLabel",
			Model.of("Im example for tooltipster."));
		TooltipsterSettings tooltipsterSettings = new TooltipsterSettings();
		tooltipsterSettings.getAnimation().setValue("grow");
		tooltipsterSettings.getArrow().setValue(false);
		tooltipsterSettings.getContent().setValue("Loading foo...");
		TooltipsterJsGenerator tooltipsterJsGenerator = new TooltipsterJsGenerator(
			tooltipsterSettings, label.getMarkupId());
		String js = tooltipsterJsGenerator.generateJs();
		label.add(new JavascriptAppenderBehavior(js, "tooltip_" + label.getMarkupId()));
		add(label);
	}


	/**
	 * {@inheritDoc}
	 */
	@Override
	public void renderHead(IHeaderResponse response)
	{
		super.renderHead(response);
		response.render(JavaScriptHeaderItem.forReference(Application.get()
			.getJavaScriptLibrarySettings().getJQueryReference()));
		response.render(JavaScriptHeaderItem
			.forReference(TooltipsterResourceReference.INSTANCE));
	}
}
