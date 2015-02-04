package de.alpharogroup.wicket.components.i18n.content;

import net.sourceforge.jaulp.locale.ResourceBundleKey;

import org.testng.annotations.Test;


public class ContentModelTest
{

	@Test
	public void testContentModelStringString()
	{
		ContentModel.builder()
			.headerResourceKey(ResourceBundleKey.builder().key("header.label").build())
			.contentResourceKey(ResourceBundleKey.builder().key("content.label").build()).build();
	}

	@Test(expectedExceptions = NullPointerException.class)
	public void testContentModelStringString1()
	{
		ContentModel.builder().build();
	}

	@Test(expectedExceptions = NullPointerException.class)
	public void testContentModelStringString2()
	{
		ContentModel.builder()
			.headerResourceKey(ResourceBundleKey.builder().key("header.label").build()).build();
	}

	@Test(expectedExceptions = NullPointerException.class)
	public void testContentModelStringString3()
	{
		ContentModel.builder()
			.contentResourceKey(ResourceBundleKey.builder().key("content.label").build()).build();
	}

}
