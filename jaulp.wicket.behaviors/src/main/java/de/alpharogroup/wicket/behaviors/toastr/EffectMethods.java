package de.alpharogroup.wicket.behaviors.toastr;

import lombok.Getter;

/**
 * An enum for a few of the effect methods in jquery.
 */
public enum EffectMethods
{
	SLIDE_TOGGLE("slideToggle"), FADE_TOGGLE("fadeToggle"), SLIDE_UP("slideUp"), SLIDE_DOWN(
		"slideDown"), FADE_OUT("fadeOut"), FADE_IN("fadeIn");

	/**
	 * The value of the easing.
	 */
	@Getter
	private final String value;

	EffectMethods(String value)
	{
		this.value = value;
	}
}
