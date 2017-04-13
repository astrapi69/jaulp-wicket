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
