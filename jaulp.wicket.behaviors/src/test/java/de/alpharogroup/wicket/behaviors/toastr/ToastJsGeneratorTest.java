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
package de.alpharogroup.wicket.behaviors.toastr;

import org.junit.Test;

/**
 * Unit test class for class ToastJsGenerator.
 */
public class ToastJsGeneratorTest
{
	@Test
	public void testGenerator()
	{
		ToastrSettings settings = ToastrSettings.builder().build();
		settings.getPositionClass().setValue(Position.TOP_RIGHT);
		settings.getNotificationContent().setValue("This is a notification");
		settings.getNotificationTitle().setValue("This is the title");
		ToastJsGenerator generator = new ToastJsGenerator(settings);

		String result = generator.generateJs(settings, "bla");
		System.out.println(result);
	}
}
