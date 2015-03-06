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
package org.jaulp.wicket.behaviors.popupoverlay;

import lombok.Getter;

/**
 * This enum encapsulates the five possible values from the attribute 'horizontal' in the
 * jquery.popupoverlay plugin.
 */
public enum HorizontalPosition
{

	/** The center value. */
	CENTER("center"),
	/** The left value. */
	LEFT("left"),
	/** The right value. */
	RIGHT("right"),
	/** The leftedge value. */
	LEFTEDGE("leftedge"),
	/** The rightedge value. */
	RIGHTEDGE("rightedge");

	/**
	 * The value of the horizontal position.
	 */
	@Getter
	private final String position;

	/**
	 * Constructor with a given horizontal position.
	 *
	 * @param position
	 *            the position
	 */
	private HorizontalPosition(String position)
	{
		this.position = position;
	}
}
