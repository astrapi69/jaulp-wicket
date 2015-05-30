package de.alpharogroup.wicket.behaviors.tooltipster;


import org.junit.Test;

/**
 * Unit test class for class TooltipsterJsGenerator.
 */
public class TooltipsterJsGeneratorTest
{


	@Test
	public void testGenerator()
	{
		TooltipsterSettings tooltipsterSettings = new TooltipsterSettings();
		tooltipsterSettings.getAnimation().setValue("grow");
		tooltipsterSettings.getArrow().setValue(false);
		tooltipsterSettings.getContent().setValue("Loading...");
		TooltipsterJsGenerator generator = new TooltipsterJsGenerator(tooltipsterSettings, "foo");
		String result = generator.generateJs();
		System.out.println(result);
	}
}
