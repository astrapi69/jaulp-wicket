package de.alpharogroup.wicket.components.search;

import org.apache.wicket.util.tester.WicketTester;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Test for the class SimpleSearchPanel.
 */
public class SimpleSearchPanelTest
{
	private WicketTester tester;
	private SimpleSearchPanel simpleSearchPanel;

	@BeforeMethod
	public void setUp()
	{
		tester = new WicketTester();
		simpleSearchPanel = new SimpleSearchPanel("simpleSearchPanel")
		{
			@Override
			protected void onSearch()
			{
			}
		};
	}

	@Test
	public void homepageRendersSuccessfully()
	{
		// start and render the test page
		tester.startComponentInPage(simpleSearchPanel);

		// assert rendered panel class
		tester.assertNoErrorMessage();

		tester.startComponent(simpleSearchPanel);
	}


}
