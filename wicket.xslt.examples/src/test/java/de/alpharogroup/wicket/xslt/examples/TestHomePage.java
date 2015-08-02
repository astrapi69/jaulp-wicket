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
package de.alpharogroup.wicket.xslt.examples;

import org.apache.wicket.util.tester.WicketTester;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import de.alpharogroup.wicket.xslt.examples.pages.HomePage;

/**
 * Simple test using the WicketTester
 */
public class TestHomePage
{
	private WicketTester tester;

	@Test
	public void homepageRendersSuccessfully()
	{
		// start and render the test page
		tester.startPage(HomePage.class);

		// assert rendered page class
		tester.assertRenderedPage(HomePage.class);
	}

	@BeforeMethod
	public void setUp()
	{
		tester = new WicketTester(new WicketApplication());
	}
}
