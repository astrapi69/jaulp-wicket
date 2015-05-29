package de.alpharogroup.wicket.behaviors.pnotify;

import org.junit.Test;

/**
 * Unit test class for class PopupoverlayJsGenerator.
 */
public class PnotifyJsGeneratorTest {
  @Test
  public void testGenerator()
  {
    PnotifySettings pnotifySettings = new PnotifySettings();
    pnotifySettings.getTitle().setValue("Test title");
    pnotifySettings.getText().setValue("a text");

    PnotifyJsGenerator pnotifyJsGenerator = new PnotifyJsGenerator(pnotifySettings);
    String result = pnotifyJsGenerator.generateJs();

    System.out.println(result);
    System.out.println("================================");

    StackSettings stackSettings = new StackSettings();
    stackSettings.getDir2().setValue("right");
    pnotifySettings.getStack().setValue(stackSettings.asJavascriptArray());

    result = pnotifyJsGenerator.generateJs();

    System.out.println(result);
  }
}
