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
