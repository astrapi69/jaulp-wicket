package org.jaulp.wicket.behaviors.toastr;

import lombok.Getter;

/**
 * The Enum ShowMethod.
 */
public enum ShowMethod
{

	/** The show. */
	SHOW("show"),
	/** The fade in. */
	FADE_IN("fadeIn"),
	/** The slide down. */
	SLIDE_DOWN("slideDown");

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
	private ShowMethod(String value)
	{
		this.value = value;
	}
}
