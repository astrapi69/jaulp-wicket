package org.jaulp.wicket.behaviors.toastr;

import lombok.Getter;

/**
 * The Enum HideMethod.
 */
public enum HideMethod
{
	HIDE("hide"), FADE_OUT("fadeOut"), SLIDE_UP("slideUp");

	/**
	 * The value of the easing.
	 */
	@Getter
	private final String value;

	/**
	 * Constructor with a given value.
	 *
	 * @param value
	 *            the value
	 */
	private HideMethod(String value)
	{
		this.value = value;
	}
}
