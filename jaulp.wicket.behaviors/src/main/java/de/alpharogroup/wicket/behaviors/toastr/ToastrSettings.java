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

import java.util.HashSet;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import de.alpharogroup.wicket.base.util.template.Settings;
import de.alpharogroup.wicket.base.util.template.StringTextType;
import de.alpharogroup.wicket.base.util.template.StringTextValue;

/**
 * This class encapsulates various settings for the toastr jquery plugin. See the documentation for
 * the jquery toastr plugin for further information.
 */
@Getter
@Setter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ToastrSettings implements Settings
{

	/** The serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Shows a close button if true. Default: false
	 */
	private StringTextValue<Boolean> closeButton = new StringTextValue<Boolean>(
		"toastr.options.closeButton", StringTextType.BOOLEAN);


	/**
	 * Shows a debug messages if true. Default: false
	 */
	private StringTextValue<Boolean> debug = new StringTextValue<Boolean>("toastr.options.debug",
		StringTextType.BOOLEAN);

	/**
	 * Shows the newest notification if true. Default: false
	 */
	private StringTextValue<Boolean> newestOnTop = new StringTextValue<Boolean>(
		"toastr.options.newestOnTop", StringTextType.BOOLEAN);

	/**
	 * Shows a progress bar if true. Default: false
	 */
	private StringTextValue<Boolean> progressBar = new StringTextValue<Boolean>(
		"toastr.options.progressBar", StringTextType.BOOLEAN);

	/**
	 * The position where to show the notifications. Default: toast-top-center
	 */
	private StringTextValue<Position> positionClass = new StringTextValue<Position>(
		"toastr.options.positionClass", Position.TOP_CENTER, StringTextType.ENUM);

	/**
	 * If true duplicate notifications will be prevent. Default: false
	 */
	private StringTextValue<Boolean> preventDuplicates = new StringTextValue<Boolean>(
		"toastr.options.preventDuplicates", StringTextType.BOOLEAN);

	/**
	 * Document please. Default: false
	 */
	private StringTextValue<Boolean> tapToDismiss = new StringTextValue<Boolean>(
		"toastr.options.tapToDismiss", StringTextType.BOOLEAN);

	/**
	 * on click. Default: null
	 */
	private StringTextValue<String> onclick = new StringTextValue<String>("toastr.options.onclick",
		StringTextType.STRING);

	/**
	 * The duration to show. Default: 300
	 */
	private StringTextValue<Integer> showDuration = new StringTextValue<Integer>(
		"toastr.options.showDuration", 300, StringTextType.INTEGER);

	/**
	 * The duration to hide. Default: 1000
	 */
	private StringTextValue<Integer> hideDuration = new StringTextValue<Integer>(
		"toastr.options.hideDuration", 1000, StringTextType.INTEGER);

	/**
	 * The timeOut. Default: 5000
	 */
	private StringTextValue<Integer> timeOut = new StringTextValue<Integer>(
		"toastr.options.timeOut", 5000, StringTextType.INTEGER);

	/**
	 * The extended timeOut. Default: 1000
	 */
	private StringTextValue<Integer> extendedTimeOut = new StringTextValue<Integer>(
		"toastr.options.extendedTimeOut", 1000, StringTextType.INTEGER);

	/**
	 * The value of the easing when show. Default: swing
	 */
	private StringTextValue<Easing> showEasing = new StringTextValue<Easing>(
		"toastr.options.showEasing", Easing.SWING, StringTextType.ENUM);

	/**
	 * The value of the easing when hide. Default: linear
	 */
	private StringTextValue<Easing> hideEasing = new StringTextValue<Easing>(
		"toastr.options.hideEasing", Easing.LINEAR, StringTextType.ENUM);

	/**
	 * The method when show. Default: fadeIn
	 */
	private StringTextValue<ShowMethod> showMethod = new StringTextValue<>(
		"toastr.options.showMethod", ShowMethod.FADE_IN, StringTextType.ENUM);

	/**
	 * The method when hide. Default: fadeOut
	 */
	private StringTextValue<HideMethod> hideMethod = new StringTextValue<>(
		"toastr.options.hideMethod", HideMethod.FADE_OUT, StringTextType.ENUM);

	/**
	 * The type of the notification. Default: success
	 */
	private StringTextValue<ToastrType> toastrType = new StringTextValue<>(
		"toastr.options.toastrType", ToastrType.SUCCESS, StringTextType.ENUM);

	/**
	 * The content of the notification. Default: null
	 */
	private StringTextValue<String> notificationContent = new StringTextValue<String>(
		"toastr.options.notificationContent", StringTextType.STRING);

	/**
	 * The title of the notification. Default: null
	 */
	private StringTextValue<String> notificationTitle = new StringTextValue<String>(
		"toastr.options.notificationTitle", StringTextType.STRING);

	@Override
	public Set<StringTextValue<?>> asSet()
	{
		Set<StringTextValue<?>> allSettings = new HashSet<>();
		allSettings.add(getCloseButton());
		allSettings.add(getDebug());
		allSettings.add(getExtendedTimeOut());
		allSettings.add(getHideDuration());
		allSettings.add(getHideEasing());
		allSettings.add(getHideMethod());
		allSettings.add(getNewestOnTop());
		allSettings.add(getOnclick());
		allSettings.add(getPositionClass());
		allSettings.add(getPreventDuplicates());
		allSettings.add(getProgressBar());
		allSettings.add(getShowDuration());
		allSettings.add(getShowEasing());
		allSettings.add(getShowMethod());
		allSettings.add(getTapToDismiss());
		allSettings.add(getTimeOut());
		allSettings.add(getToastrType());
		return allSettings;
	}
}
