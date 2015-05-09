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

import java.io.Serializable;

import lombok.Getter;
import net.tanesha.recaptcha.ReCaptcha;
import net.tanesha.recaptcha.ReCaptchaFactory;
import net.tanesha.recaptcha.ReCaptchaImpl;
import net.tanesha.recaptcha.ReCaptchaResponse;

import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.MarkupStream;
import org.apache.wicket.markup.html.form.FormComponent;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import de.alpharogroup.locale.ResourceBundleKey;
import de.alpharogroup.wicket.base.util.ComponentFinder;
import de.alpharogroup.wicket.base.util.WicketComponentUtils;
import de.alpharogroup.wicket.base.util.parameter.PageParametersUtils;
import de.alpharogroup.wicket.base.util.resource.ResourceModelFactory;

public abstract class ReCaptchaPanel extends Panel
{
	private static final long serialVersionUID = 1L;
	private static final String PARAMETER_KEY_RECAPTCHA_RESPONSE_FIELD = "recaptcha_response_field";
	private static final String PARAMETER_KEY_RECAPTCHA_CHALLENGE_FIELD = "recaptcha_challenge_field";
	private static final String RECAPTCHA_SERVER_URL = "https://www.google.com/recaptcha/api";
	@Getter
	private final FormComponent<Serializable> captcha;

	public ReCaptchaPanel(String id)
	{
		super(id);
		add(captcha = newCaptchaFormComponent("captcha", new Model<Serializable>()));
	}

	protected FormComponent<Serializable> newCaptchaFormComponent(final String id,
		final IModel<Serializable> model)
	{
		return new FormComponent<Serializable>(id, model)
		{
			private static final long serialVersionUID = 1L;

			@Override
			public void onComponentTagBody(final MarkupStream markupStream,
				final ComponentTag openTag)
			{
				replaceComponentTagBody(
					markupStream,
					openTag,
					newReCaptcha(getPublicKey(), getPrivateKey(), false).createRecaptchaHtml(
						"errorText", "clean", null));
			}

			@Override
			public void validate()
			{
				final ReCaptchaImpl reCaptcha = new ReCaptchaImpl();
				reCaptcha.setPrivateKey(getPrivateKey());
				final String challenge = PageParametersUtils
					.getParameter(PARAMETER_KEY_RECAPTCHA_CHALLENGE_FIELD);
				String uresponse = PageParametersUtils
					.getParameter(PARAMETER_KEY_RECAPTCHA_RESPONSE_FIELD);
				if (uresponse == null)
				{
					uresponse = "";
				}
				String remoteAddress = WicketComponentUtils.getHttpServletRequest().getRemoteAddr();
				final ReCaptchaResponse reCaptchaResponse = reCaptcha.checkAnswer(remoteAddress,
					challenge, uresponse);

				if (!reCaptchaResponse.isValid())
				{
					error(ResourceModelFactory.newResourceModel(
						ResourceBundleKey.builder().key("kaptcha.invalid.label")
							.defaultValue("Incorrect answer, type the words from the image again!")
							.build(), this).getObject());
				}
			}
		};
	}

	private ReCaptcha newReCaptcha(String publicKey, String privateKey, boolean includeNoscript)
	{
		if (WicketComponentUtils.isSecure(ComponentFinder.getCurrentPage()))
		{
			ReCaptcha reCaptcha = ReCaptchaFactory.newSecureReCaptcha(getPublicKey(),
				getPrivateKey(), includeNoscript);
			((ReCaptchaImpl)reCaptcha).setRecaptchaServer(RECAPTCHA_SERVER_URL);
			return reCaptcha;
		}
		return ReCaptchaFactory.newReCaptcha(getPublicKey(), getPrivateKey(), includeNoscript);
	}

	public abstract String getPublicKey();

	public abstract String getPrivateKey();

}