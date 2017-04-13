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
package de.alpharogroup.wicket.components.captcha.draw;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

import org.apache.wicket.extensions.markup.html.captcha.CaptchaImageResource;

import de.alpharogroup.random.Constants;
import de.alpharogroup.random.RandomExtensions;

/**
 * The Class {@link CaptchaModelBean} holds captcha data.
 *
 * @author Asterios Raptis
 */
public class CaptchaModelBean implements Serializable
{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The captcha input. */
	@Getter
	@Setter
	private String captchaInput;

	/** Random captcha password to match against. */
	@Getter
	private String randomCaptchaString;

	/** The captcha image resource. */
	@Getter
	private final CaptchaImageResource captchaImageResource;

	/**
	 * Instantiates a new {@link CaptchaModelBean}.
	 */
	public CaptchaModelBean()
	{
		captchaImageResource = new CaptchaImageResource(
			randomCaptchaString = RandomExtensions.getRandomString(Constants.UCCHARSWN, 7));
	}

}
