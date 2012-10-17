/**
 * Copyright (C) 2007 Asterios Raptis
 *
 * This program is open source software; you can redistribute it and/or modify
 * it under the terms of the Apache License V2.0 as published by
 * the Apache Foundation.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY.
 */
package org.jaulp.wicket.dialogs.examples;

import junit.framework.TestCase;

import org.apache.wicket.util.tester.WicketTester;

/**
 * Simple test using the WicketTester.
 */
public class TestHomePage extends TestCase
{

	/** The tester. */
	private WicketTester tester;

	/* (non-Javadoc)
	 * @see junit.framework.TestCase#setUp()
	 */
	public void setUp()
	{
		tester = new WicketTester();
	}

	/**
	 * Test render my page.
	 */
	public void testRenderMyPage()
	{
		//start and render the test page
		tester.startPage(HomePage.class);

		//assert rendered page class
		tester.assertRenderedPage(HomePage.class);

	}
}
