package de.alpharogroup.wicket.behaviors.popupoverlay;

import org.junit.Test;

/**
 * Unit test class for class PopupoverlayJsGenerator.
 */
public class PopupoverlayJsGeneratorTest {
  @Test
  public void testGenerator()
  {
    PopupoverlaySettings popupoverlaySettings = new PopupoverlaySettings();
    popupoverlaySettings.getEscape().setValue(false);
    popupoverlaySettings.getFocusdelay().setValue(400);
    popupoverlaySettings.getHorizontal().setValue(HorizontalPosition.LEFT);
    PopupoverlayJsGenerator generator = new PopupoverlayJsGenerator(popupoverlaySettings, "aComponent");
    generator.setComponentId("aComponent");
    String result = generator.generateJs();

    System.out.println(result);
  }
}
