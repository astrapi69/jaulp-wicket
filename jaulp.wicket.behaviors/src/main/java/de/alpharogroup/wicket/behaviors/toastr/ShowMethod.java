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

import de.alpharogroup.wicket.base.util.template.ValueEnum;
import lombok.Getter;

/**
 * The Enum ShowMethod.
 */
public enum ShowMethod implements ValueEnum
{

	/** The show. */
	SHOW("show"),
	/** The fade in. */
	FADE_IN(EffectMethods.FADE_IN.getValue()),
	/** The slide down. */
	SLIDE_DOWN(EffectMethods.SLIDE_DOWN.getValue());

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
