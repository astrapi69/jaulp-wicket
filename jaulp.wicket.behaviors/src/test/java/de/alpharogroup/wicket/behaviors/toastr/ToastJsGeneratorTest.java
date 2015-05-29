package de.alpharogroup.wicket.behaviors.toastr;

import org.junit.Test;

/**
 * Unit test class for class ToastJsGenerator.
 */
public class ToastJsGeneratorTest {
  @Test
  public void testGenerator()
  {
    ToastrSettings settings = new ToastrSettings();
    settings.getPositionClass().setValue(Position.TOP_RIGHT);
    settings.getNotificationContent().setValue("This is a notification");
    settings.getNotificationTitle().setValue("This is the title");
    ToastJsGenerator generator = new ToastJsGenerator(settings);

    String result = generator.generateJs(settings, "bla");
    System.out.println(result);
  }
}
