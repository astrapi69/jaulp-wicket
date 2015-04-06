package de.alpharogroup.wicket.components.link;

import lombok.Getter;

/**
 * The Enum DefaultTargets holds the default targets for a link. There is still the possibility with
 * the frame name as value but is not provided in this enum.
 */
public enum DefaultTargets
{

	/** The constant BLANK. It will open the linked document in a new window or tab. */
	BLANK("_blank"),
	/**
	 * The constant SELF. It will open the linked document in the same frame as it was clicked. This
	 * is the default behavior.
	 */
	SELF("_self"),
	/** The constant PARENT. It will open the linked document in the parent frame. */
	PARENT("_parent"),
	/** The constant TOP. It will open the linked document in the full body of the window. */
	TOP("_top");

	/**
	 * Gets the target.
	 *
	 * @return the target
	 */
	@Getter
	private final String target;

	/**
	 * Instantiates a new default targets.
	 *
	 * @param target
	 *            the target
	 */
	private DefaultTargets(final String target)
	{
		this.target = target;
	}
}
