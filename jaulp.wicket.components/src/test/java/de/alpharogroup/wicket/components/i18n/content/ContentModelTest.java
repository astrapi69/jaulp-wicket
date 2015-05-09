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
package de.alpharogroup.wicket.components.i18n.content;

import org.testng.annotations.Test;

import de.alpharogroup.locale.ResourceBundleKey;


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
