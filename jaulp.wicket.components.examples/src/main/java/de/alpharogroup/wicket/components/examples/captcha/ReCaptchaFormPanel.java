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
package de.alpharogroup.wicket.components.examples.captcha;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.FeedbackPanel;

import de.alpharogroup.wicket.base.BasePanel;
import de.alpharogroup.wicket.components.captcha.recaptcha.ReCaptchaPanel;

public class ReCaptchaFormPanel extends BasePanel<Object>
{
	private static final long serialVersionUID = 1L;

	public ReCaptchaFormPanel(final String id)
	{
		super(id);
		final Form<?> form = new Form<Void>("form")
		{
			private static final long serialVersionUID = 1L;

			@Override
			protected void onSubmit()
			{
				info("Image words are valid!");
			};
		};

		form.add(new ReCaptchaPanel("reCaptchaPanel")
		{
			private static final long serialVersionUID = 1L;
			private final static String PUBLIC_KEY = "6LehBPwSAAAAAFtG28FJgzpkGhONCYDOmAzzopxz";
			private final static String PRIVATE_KEY = "6LehBPwSAAAAAN78oe6tLj2NqKhupf6vxhy8kkFJ";

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
		add(form);
		add(new FeedbackPanel("feedback"));
	}
}
