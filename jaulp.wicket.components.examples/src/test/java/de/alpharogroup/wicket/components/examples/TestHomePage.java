package de.alpharogroup.wicket.components.examples;

import org.apache.wicket.util.tester.WicketTester;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import de.alpharogroup.wicket.components.examples.application.WicketApplication;
import de.alpharogroup.wicket.components.examples.home.HomePage;


/**
 * Simple test using the WicketTester
 */
public class TestHomePage
{
	private WicketTester tester;

//	@BeforeMethod
//	public void setUp()
//	{
//		tester = new WicketTester(new WicketApplication());
//	}
//
//	@Test
//	public void homepageRendersSuccessfully()
//	{
//		//start and render the test page
//		tester.startPage(HomePage.class);
//
//		//assert rendered page class
//		tester.assertRenderedPage(HomePage.class);
//	}
}
