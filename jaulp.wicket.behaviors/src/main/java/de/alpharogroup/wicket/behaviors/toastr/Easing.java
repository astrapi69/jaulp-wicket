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
 * The Enum Easing.
 */
public enum Easing
{

	/** The constant for the easing type 'linear'. */
	LINEAR("linear"),
	/** The constant for the easing type 'swing'. */
	SWING("swing"),
	/** The constant for the easing type 'jswing'. */
	JSWING("jswing"),
	/** The constant for the easing type 'easeInQuad'. */
	EASE_IN_QUAD("easeInQuad"),
	/** The constant for the easing type 'easeInCubic'. */
	EASE_IN_CUBIC("easeInCubic"),
	/** The constant for the easing type 'easeInQuart'. */
	EASE_IN_QUART("easeInQuart"),
	/** The constant for the easing type 'easeInQuint'. */
	EASE_IN_QUINT("easeInQuint"),
	/** The constant for the easing type 'easeInSine'. */
	EASE_IN_SINE("easeInSine"),
	/** The constant for the easing type 'easeInExpo'. */
	EASE_IN_EXPO("easeInExpo"),
	/** The constant for the easing type 'easeInCirc'. */
	EASE_IN_CIRC("easeInCirc"),
	/** The constant for the easing type 'easeInElastic'. */
	EASE_IN_ELASTIC("easeInElastic"),
	/** The constant for the easing type 'easeInBack'. */
	EASE_IN_BACK("easeInBack"),
	/** The constant for the easing type 'easeInBounce'. */
	EASE_IN_BOUNCE("easeInBounce"),
	/** The constant for the easing type 'easeOutQuad'. */
	EASE_OUT_QUAD("easeOutQuad"),
	/** The constant for the easing type 'easeOutCubic'. */
	EASE_OUT_CUBIC("easeOutCubic"),
	/** The constant for the easing type 'easeOutQuart'. */
	EASE_OUT_QUART("easeOutQuart"),
	/** The constant for the easing type 'easeOutQuint'. */
	EASE_OUT_QUINT("easeOutQuint"),
	/** The constant for the easing type 'easeOutSine'. */
	EASE_OUT_SINE("easeOutSine"),
	/** The constant for the easing type 'easeOutExpo'. */
	EASE_OUT_EXPO("easeOutExpo"),
	/** The constant for the easing type 'easeOutCirc'. */
	EASE_OUT_CIRC("easeOutCirc"),
	/** The constant for the easing type 'easeOutElastic'. */
	EASE_OUT_ELASTIC("easeOutElastic"),
	/** The constant for the easing type 'easeOutBack'. */
	EASE_OUT_BACK("easeOutBack"),
	/** The constant for the easing type 'easeOutBounce'. */
	EASE_OUT_BOUNCE("easeOutBounce"),
	/** The constant for the easing type 'easeInOutQuad'. */
	EASE_IN_OUT_QUAD("easeInOutQuad"),
	/** The constant for the easing type 'easeInOutCubic'. */
	EASE_IN_OUT_CUBIC("easeInOutCubic"),
	/** The constant for the easing type 'easeInOutQuart'. */
	EASE_IN_OUT_QUART("easeInOutQuart"),
	/** The constant for the easing type 'easeInOutQuint'. */
	EASE_IN_OUT_QUINT("easeInOutQuint"),
	/** The constant for the easing type 'easeInOutSine'. */
	EASE_IN_OUT_SINE("easeInOutSine"),
	/** The constant for the easing type 'easeInOutExpo'. */
	EASE_IN_OUT_EXPO("easeInOutExpo"),
	/** The constant for the easing type 'easeInOutCirc'. */
	EASE_IN_OUT_CIRC("easeInOutCirc"),
	/** The constant for the easing type 'easeInOutElastic'. */
	EASE_IN_OUT_ELASTIC("easeInOutElastic"),
	/** The constant for the easing type 'easeInOutBack'. */
	EASE_IN_OUT_BACK("easeInOutBack"),
	/** The constant for the easing type 'easeInOutBounce'. */
	EASE_IN_OUT_BOUNCE("easeInOutBounce");

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
