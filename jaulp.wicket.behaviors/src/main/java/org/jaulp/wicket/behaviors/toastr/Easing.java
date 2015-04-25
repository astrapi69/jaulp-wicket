package org.jaulp.wicket.behaviors.toastr;

import lombok.Getter;

/**
 * The Enum Easing.
 */
public enum Easing
{

	/** The swing. */
	SWING("swing"),
	/** The linear. */
	LINEAR("linear");

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
	private Easing(String value)
	{
		this.value = value;
	}
}
