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
package de.alpharogroup.wicket.behaviors.toastr;

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
