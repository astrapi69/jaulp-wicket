package org.jaulp.wicket.behaviors.toastr;

import lombok.Getter;

/**
 * The Enum Position.
 */
public enum Position
{

	/** The top. */
	TOP_RIGHT("toast-top-right"),
	/** The bottom. */
	BOTTOM_RIGHT("toast-bottom-right"),
	/** The bottom. */
	BOTTOM_LEFT("toast-bottom-left"),
	/** The top. */
	TOP_LEFT("toast-top-left"),
	/** The top. */
	TOP_FULL_WIDTH("toast-top-full-width"),
	/** The top. */
	BOTTOM_FULL_WIDTH("toast-bottom-full-width"),
	/** The center. */
	TOP_CENTER("toast-top-center"),
	/** The center. */
	BOTTOM_CENTER("toast-bottom-center");

	/**
	 * The value of the vertical position.
	 */
	@Getter
	private final String position;

	/**
	 * Constructor with a given vertical position.
	 *
	 * @param position
	 *            the position
	 */
	private Position(String position)
	{
		this.position = position;
	}
}
