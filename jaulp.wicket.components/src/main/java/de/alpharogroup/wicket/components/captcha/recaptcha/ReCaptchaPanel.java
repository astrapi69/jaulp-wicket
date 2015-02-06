package de.alpharogroup.wicket.components.captcha.recaptcha;

import java.io.Serializable;

import net.sourceforge.jaulp.locale.ResourceBundleKey;
import net.tanesha.recaptcha.ReCaptcha;
import net.tanesha.recaptcha.ReCaptchaFactory;
import net.tanesha.recaptcha.ReCaptchaImpl;
import net.tanesha.recaptcha.ReCaptchaResponse;

import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.MarkupStream;
import org.apache.wicket.markup.html.form.FormComponent;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;
import org.jaulp.wicket.base.util.ComponentFinder;
import org.jaulp.wicket.base.util.WicketComponentUtils;
import org.jaulp.wicket.base.util.parameter.PageParametersUtils;
import org.jaulp.wicket.base.util.resource.ResourceModelFactory;

public abstract class ReCaptchaPanel extends Panel
{
	private static final long serialVersionUID = 1L;
	private static final String PARAMETER_KEY_RECAPTCHA_RESPONSE_FIELD = "recaptcha_response_field";
	private static final String PARAMETER_KEY_RECAPTCHA_CHALLENGE_FIELD = "recaptcha_challenge_field";
	private static final String RECAPTCHA_SERVER_URL = "https://www.google.com/recaptcha/api";

	public ReCaptchaPanel(String id)
	{
		super(id);

		add(new FormComponent<Serializable>("captcha", new Model<Serializable>())
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
		});
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