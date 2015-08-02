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
package de.alpharogroup.wicket.components.captcha.recaptcha;

import org.apache.wicket.util.tester.WicketTester;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import de.alpharogroup.wicket.components.examples.application.WicketApplication;


public class ReCaptchaPanelTest
{

	private WicketTester tester;

	@BeforeMethod
	public void setUp() throws Exception
	{
		tester = new WicketTester(new WicketApplication());
	}

	@Test
	public void testRender() throws Exception
	{
		tester.startComponentInPage(new ReCaptchaPanel("panel")
		{
			private static final long serialVersionUID = 1L;
			private final static String PUBLIC_KEY = "6LdVBfwSAAAAAMz07R7X5rmdn3sNiHG2UfM_5IUl";
			private final static String PRIVATE_KEY = "6LdVBfwSAAAAAKsMpZcDlpl_F1pji5JcnmNt-Hon";

			@Override
			public String getPrivateKey()
			{
				return PRIVATE_KEY;
			}

			@Override
			public String getPublicKey()
			{
				return PUBLIC_KEY;
			}

		});
		tester.assertComponent("panel", ReCaptchaPanel.class);
		tester.assertVisible("panel");
	}
}
