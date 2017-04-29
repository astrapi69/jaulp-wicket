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
package de.alpharogroup.wicket.behaviors.animations;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;

import de.alpharogroup.wicket.behaviors.DisplayNoneBehavior;

/**
 * The class {@link Animate} is an extension for add animation to components.
 */
public class Animate
{

	/**
	 * Replace the given component with animation.
	 *
	 * @param target
	 *            the target
	 * @param component
	 *            the component
	 */
	public static void slideUpAndDown(final Component component, final AjaxRequestTarget target )
	{
		Animate.slideUpAndDown(component, target, 400, 400);
	}

	/**
	 * Replace the given component with animation.
	 *
	 * @param target
	 *            the target
	 * @param component
	 *            the component
	 * @param slideUpDuration
	 *            the slide up duration
	 * @param slideDownDuration
	 *            the slide down duration
	 */
	public static void slideUpAndDown(final Component component, final AjaxRequestTarget target, 
		int slideUpDuration, int slideDownDuration)
	{
		component.add(new DisplayNoneBehavior());
		target.prependJavaScript("notify|jQuery('#" + component.getMarkupId() + "').slideUp("
			+ slideUpDuration + ", notify);");
		target.add(component);
		target.appendJavaScript(
			"jQuery('#" + component.getMarkupId() + "').slideDown(" + slideDownDuration + ");");
	}
}
