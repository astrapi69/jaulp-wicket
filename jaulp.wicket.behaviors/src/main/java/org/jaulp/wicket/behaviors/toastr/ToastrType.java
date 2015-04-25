package org.jaulp.wicket.behaviors.toastr;

import lombok.Getter;

/**
 * The Enum ToastrType.
 */
public enum ToastrType
{
	/** The success value. */
	SUCCESS("success"),
	/** The info value. */
	INFO("info"),
	/** The warning value. */
	WARNING("warning"),
	/** The error value. */
	ERROR("error");

	/**
	 * The value of the type.
	 */
	@Getter
	private final String type;

	/**
	 * Constructor with a given type.
	 *
	 * @param type
	 *            the type
	 */
	private ToastrType(String type)
	{
		this.type = type;
	}
}
