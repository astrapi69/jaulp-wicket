package de.alpharogroup.wicket.behaviors.resource.references;

import de.alpharogroup.wicket.base.util.template.JavascriptGenerator;

/**
 * The Class TimelineChartJsGenerator generates the javascript with a TimelineChartSettings object.
 */
public class TimelineChartJsGenerator extends JavascriptGenerator<TimelineChartSettings>
{

	/**
	 * The serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new javascript generator.
	 * 
	 * @param settings
	 *            the settings
	 */
	public TimelineChartJsGenerator(final TimelineChartSettings settings)
	{
		super(settings);
	}
}
