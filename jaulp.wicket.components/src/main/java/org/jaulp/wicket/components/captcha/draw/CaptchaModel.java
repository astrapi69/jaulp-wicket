package org.jaulp.wicket.components.captcha.draw;

import java.io.Serializable;

import net.sourceforge.jaulp.random.Constants;
import net.sourceforge.jaulp.random.RandomUtils;

import org.apache.wicket.extensions.markup.html.captcha.CaptchaImageResource;
import org.apache.wicket.util.value.ValueMap;

public class CaptchaModel implements Serializable {

	private static final String CAPTCHA_INPUT = "captchaInput";

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	private final ValueMap properties = new ValueMap();

	/** Random captcha password to match against. */
	private final String randomCaptchaString = RandomUtils.getRandomString(
			Constants.UCCHARSWN, 7);

	private final CaptchaImageResource captchaImageResource;

	public CaptchaModel() {
		captchaImageResource = new CaptchaImageResource(randomCaptchaString);
	}

	public CaptchaImageResource getCaptchaImageResource() {
		return captchaImageResource;
	}

	public String getCaptchaInput() {
		return properties.getString(CAPTCHA_INPUT);
	}

	public ValueMap getProperties() {
		return properties;
	}

	public String getRandomCaptchaString() {
		return randomCaptchaString;
	}
}
