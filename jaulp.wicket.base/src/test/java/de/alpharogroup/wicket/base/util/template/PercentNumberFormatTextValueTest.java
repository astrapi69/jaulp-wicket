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
package de.alpharogroup.wicket.base.util.template;

import org.junit.Test;
import org.testng.Assert;

/**
 * Unit test class for class PercentNumberFormatTextValue.
 */
public class PercentNumberFormatTextValueTest
{


	@Test
	public void test()
	{

		PercentNumberFormatTextValue textValue = new PercentNumberFormatTextValue("foo", 2);
		String actual = textValue.getValue();
		String expected = "2%";
		Assert.assertEquals(expected, actual);
		// Give a int value...
		textValue.setValue(3);
		actual = textValue.getValue();
		expected = "3%";
		Assert.assertEquals(expected, actual);
		System.out.println(actual);
		// Give a string value...
		textValue.setValue("5");
		actual = textValue.getValue();
		expected = "5%";
		Assert.assertEquals(expected, actual);
		// Give a string value...
		textValue.setValue("5%");
		actual = textValue.getValue();
		expected = "5%";
		Assert.assertEquals(expected, actual);
		// Give a string value that is not conform...
		textValue.setValue("xy");
		actual = textValue.getValue();
		expected = "50%";
		Assert.assertEquals(expected, actual);
		// Give a int value that is not conform...
		textValue.setValue(-1);
		actual = textValue.getValue();
		expected = "50%";
		Assert.assertEquals(expected, actual);
		// Give a int value that is not conform...
		textValue.setValue(100);
		actual = textValue.getValue();
		expected = "100%";
		Assert.assertEquals(expected, actual);
		// Give a int value that is not conform...
		textValue.setValue(0);
		actual = textValue.getValue();
		expected = "0%";
		Assert.assertEquals(expected, actual);
	}
}
