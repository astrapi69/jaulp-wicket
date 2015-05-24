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
package de.alpharogroup.wicket.behaviors.popupoverlay;

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
 * This class encapsulates various settings for {@link PopupoverlayBehavior}. See the documentation
 * for the jquery popupoverlay plugin for further information.
 */
@Getter
@Setter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class PopupoverlaySettings implements Settings
{

	/** The serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Sets popup type to overlay or tooltip. Default: overlay
	 */
	private StringTextValue<PopupoverlayType> type = new StringTextValue<PopupoverlayType>("type",
		PopupoverlayType.OVERLAY, StringTextType.ENUM);

	/**
	 * Shows the popup when initialized. Default: false
	 */
	private StringTextValue<Boolean> autoopen = new StringTextValue<Boolean>("autoopen",
		StringTextType.BOOLEAN);

	/**
	 * Disables scrolling of background content while the popup is visible. Default: false
	 */
	private StringTextValue<Boolean> scrolllock = new StringTextValue<Boolean>("scrolllock",
		StringTextType.BOOLEAN);

	/**
	 * Enables background cover. Disabled for tooltips. Default: true
	 */
	private StringTextValue<Boolean> background = new StringTextValue<Boolean>("background", true,
		StringTextType.BOOLEAN);

	/**
	 * Disable background cover and keep background elements active. Implies background, blur and
	 * scrolllock to false. Default: false
	 */
	private StringTextValue<Boolean> backgroundactive = new StringTextValue<Boolean>(
		"backgroundactive", StringTextType.BOOLEAN);

	/**
	 * Sets background color. Default: #000
	 */
	private StringTextValue<String> color = new StringTextValue<String>("color", "#000",
		StringTextType.STRING);

	/**
	 * The opacity of the notice. Default: 1
	 */
	private StringTextValue<Float> opacity = new StringTextValue<Float>("opacity", 0.5f,
		StringTextType.FLOAT);

	/**
	 * Sets horizontal position. Options `leftedge` and `rightedge` can be used only for tooltips,
	 * and will align the tooltip to the left or right edge of the opening element (`openelement`).
	 * Default: center
	 */
	private StringTextValue<HorizontalPosition> horizontal = new StringTextValue<HorizontalPosition>(
		"horizontal", HorizontalPosition.CENTER, StringTextType.ENUM);

	/**
	 * Sets vertical position. Options `topedge` and `bottomedge` can be used only for tooltips, and
	 * will align the tooltip to the top or bottom edge of the opening element (`openelement`).
	 * Default: center
	 */
	private StringTextValue<VerticalPosition> vertical = new StringTextValue<VerticalPosition>(
		"vertical", VerticalPosition.CENTER, StringTextType.ENUM);

	/**
	 * Sets top offset to tooltip or overlay. Default: 0
	 */
	private StringTextValue<Integer> offsettop = new StringTextValue<Integer>("offsettop", 0,
		StringTextType.INTEGER);

	/**
	 * Sets left offset to tooltip or overlay. Default: 0
	 */
	private StringTextValue<Integer> offsetleft = new StringTextValue<Integer>("offsetleft", 0,
		StringTextType.INTEGER);

	/**
	 * Closes the popup when Escape key is pressed. Default: true
	 */
	private StringTextValue<Boolean> escape = new StringTextValue<Boolean>("escape", true,
		StringTextType.BOOLEAN);

	/**
	 * Closes the popup when clicked outside of it. Default: true
	 */
	private StringTextValue<Boolean> blur = new StringTextValue<Boolean>("blur", true,
		StringTextType.BOOLEAN);

	/**
	 * Sets default z-index to the popup (2001) and to the background (2000). Default: true
	 */
	private StringTextValue<Boolean> setzindex = new StringTextValue<Boolean>("setzindex", true,
		StringTextType.BOOLEAN);

	/**
	 * Sets highest z-index on the page to the popup. Default: false
	 */
	private StringTextValue<Boolean> autozindex = new StringTextValue<Boolean>("autozindex",
		StringTextType.BOOLEAN);

	/**
	 * Lock keyboard focus inside of popup. Recommended to be enabled. Default: true
	 */
	private StringTextValue<Boolean> keepfocus = new StringTextValue<Boolean>("keepfocus", true,
		StringTextType.BOOLEAN);

	/**
	 * Enables you to specify the element which will be focused upon showing the popup. By default,
	 * the popup element #my_popup will receive the initial focus. Default: my_popup
	 */
	private StringTextValue<String> focuselement = new StringTextValue<String>("focuselement",
		StringTextType.STRING);

	/**
	 * Sets a delay in milliseconds before focusing an element. This is to prevent page scrolling
	 * during opening transition, as browsers will try to move the viewport to an element which
	 * received the focus. Default: 50
	 */
	private StringTextValue<Integer> focusdelay = new StringTextValue<Integer>("focusdelay", 50,
		StringTextType.INTEGER);

	/**
	 * Sets a page container (to help screen reader users). Page container should be the element
	 * that surrounds all the content on the page (e.g. '.container' in the case of this very page).
	 *
	 * It's highly recommended that you set the page container to help some screen readers read the
	 * modal dialog correctly. Doing so, when the popup is visible, aria-hidden="true" will be set
	 * to the page container and aria-hidden="false" to the popup, and vice-versa when the popup
	 * closes. You can set `pagecontainer` once per website (e.g. $.fn.popup.defaults.pagecontainer
	 * = '.container'). Default: not set
	 */
	private StringTextValue<String> pagecontainer = new StringTextValue<String>("pagecontainer",
		StringTextType.STRING);

	/**
	 * Shows a default browser outline on popup element when focused.
	 *
	 * Setting to false is equivalent to #my_popup{outline: none;}; Default: false
	 */
	private StringTextValue<Boolean> outline = new StringTextValue<Boolean>("outline",
		StringTextType.BOOLEAN);

	/**
	 * Removes popup element from the DOM after closing transition. Default: false
	 */
	private StringTextValue<Boolean> detach = new StringTextValue<Boolean>("detach",
		StringTextType.BOOLEAN);

	/**
	 * Enables you to define custom element which will open the popup on click. Default:
	 * .{popup_id}_open
	 */
	private StringTextValue<String> openelement = new StringTextValue<String>("openelement",
		StringTextType.STRING);

	/**
	 * Enables you to define custom element which will close the popup on click. Default:
	 * .{popup_id}_close
	 */
	private StringTextValue<String> closeelement = new StringTextValue<String>("closeelement",
		StringTextType.STRING);

	/**
	 * Sets CSS transition when showing and hiding a popup.
	 *
	 * Use this if you don't need separate transition for background, or different transition for
	 * opening and closing the popup, or if you need to transition only selected properties –
	 * otherwise set custom transitions directly in CSS.
	 *
	 * Simple fade effect $('#my_popup').popup({transition: 'all 0.3s'}) is equivalent to #my_popup,
	 * #my_popup_wrapper, #my_popup_background {transition: all 0.3s;}
	 *
	 * Setting fade effect for all popups on the site: $.fn.popup.defaults.transition = 'all 0.3s';
	 * is equivalent to .popup_content, .popup_wrapper, .popup_background {transition: all 0.3s;}
	 * Default: not set
	 */
	private StringTextValue<String> transition = new StringTextValue<String>("transition",
		StringTextType.STRING);

	/**
	 * Type: object JQuery or DOM object
	 *
	 * Sets an element to be an anchor for tooltip position.
	 *
	 * For example, for multiple opening links using the same tooltip on mouseover:
	 *
	 * $('.my_popup_open').on({ mouseenter: function(event) { $('#my_popup').popup({ tooltipanchor:
	 * event.target, autoopen: true, type: 'tooltip' }); }, mouseleave: function() {
	 * $('#my_popup').popup('hide'); } }); Default: not set
	 */
	private StringTextValue<String> tooltipanchor = new StringTextValue<String>("tooltipanchor",
		StringTextType.STRING);

	/**
	 * Type: function Description: Callback function which will execute before the popup is opened.
	 * Default: not set
	 */
	private StringTextValue<String> beforeopen = new StringTextValue<String>("beforeopen",
		StringTextType.STRING);

	/**
	 * Type: function Description: Callback function which will execute when the popup starts to
	 * open. Default: not set
	 */
	private StringTextValue<String> onopen = new StringTextValue<String>("onopen",
		StringTextType.STRING);

	/**
	 * Type: function Description: Callback function which will execute when the popup starts to
	 * close. Default: not set
	 */
	private StringTextValue<String> onclose = new StringTextValue<String>("onclose",
		StringTextType.STRING);

	/**
	 * Type: function Description: Callback function which will execute after the opening CSS
	 * transition is over, only if transition actually occurs and if supported by the browser.
	 * Default: not set
	 */
	private StringTextValue<String> opentransitionend = new StringTextValue<String>(
		"opentransitionend", StringTextType.STRING);

	/**
	 * Type: function Description: Callback function which will execute after the closing CSS
	 * transition is over, only if transition actually occurs and if supported by the browser.
	 * Default: not set
	 */
	private StringTextValue<String> closetransitionend = new StringTextValue<String>(
		"closetransitionend", StringTextType.STRING);

	@Override
	public Set<StringTextValue<?>> asSet()
	{
		Set<StringTextValue<?>> allSettings = new HashSet<>();
		allSettings.add(getAutoopen());
		allSettings.add(getAutozindex());
		allSettings.add(getBackground());
		allSettings.add(getBackgroundactive());
		allSettings.add(getBeforeopen());
		allSettings.add(getBlur());
		allSettings.add(getCloseelement());
		allSettings.add(getClosetransitionend());
		allSettings.add(getColor());
		allSettings.add(getDetach());
		allSettings.add(getEscape());
		allSettings.add(getFocusdelay());
		allSettings.add(getFocuselement());
		allSettings.add(getHorizontal());
		allSettings.add(getKeepfocus());
		allSettings.add(getOffsetleft());
		allSettings.add(getOffsettop());
		allSettings.add(getOnclose());
		allSettings.add(getOnopen());
		allSettings.add(getOpacity());
		allSettings.add(getOpentransitionend());
		allSettings.add(getOutline());
		allSettings.add(getPagecontainer());
		allSettings.add(getScrolllock());
		allSettings.add(getSetzindex());
		allSettings.add(getTooltipanchor());
		allSettings.add(getTransition());
		allSettings.add(getType());
		allSettings.add(getVertical());
		return allSettings;
	}

}
