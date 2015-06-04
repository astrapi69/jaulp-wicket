package de.alpharogroup.wicket.behaviors.spin;

import org.junit.Test;

/**
 * Unit test class for class SpinJsGenerator.
 */
public class SpinJsGeneratorTest {

  @Test
  public void testGenerator()
  {
    SpinSettings spinSettings = new SpinSettings();
    spinSettings.getClassName().setValue("fancy");
    spinSettings.getTop().setValue("3");
    SpinJsGenerator generator = new SpinJsGenerator(spinSettings, "aComponent");
    String result = generator.generateJs();
    System.out.println(result);
  }
}
