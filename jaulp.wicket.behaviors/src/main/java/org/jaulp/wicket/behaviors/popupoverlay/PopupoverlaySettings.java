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

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class PopupoverlaySettings implements Serializable
{

	/**
	 * The serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Sets popup type to overlay or tooltip. defaults to overlay;
	 */
	private String type = PopupoverlayType.OVERLAY.getType();

	/**
	 * Shows the popup when initialized; defaults to false;
	 */
	private boolean autoopen = false;

	/**
	 * Disables scrolling of background content while the popup is visible; defaults to false;
	 */
	private boolean scrolllock = false;

	/**
	 * Enables background cover. Disabled for tooltips.; defaults to true;
	 */
	private boolean background = true;

	/**
	 * Disable background cover and keep background elements active. Implies background, blur and
	 * scrolllock to false; defaults to false;
	 */
	private boolean backgroundactive = false;

	/**
	 * Sets background color. defaults to '#000';
	 */
	private String color = "#000";

	/**
	 * Sets background opacity. defaults to '0.5';
	 */
	private Float opacity = 0.5f;

	/**
	 * Sets horizontal position. Options `leftedge` and `rightedge` can be used only for tooltips,
	 * and will align the tooltip to the left or right edge of the opening element (`openelement`).
	 * defaults to 'center';
	 */
	private String horizontal = HorizontalPosition.CENTER.getPosition();

	/**
	 * Sets vertical position.
	 * 
	 * Options `topedge` and `bottomedge` can be used only for tooltips, and will align the tooltip
	 * to the top or bottom edge of the opening element (`openelement`). defaults to 'center';
	 */
	private String vertical = VerticalPosition.CENTER.getPosition();

	/**
	 * Sets top offset to tooltip. (defaults to 0)
	 */
	private int offsettop = 0;

	/**
	 * Sets left offset to tooltip. (defaults to 0)
	 */
	private int offsetleft = 0;

	/**
	 * Closes the popup when Escape key is pressed; defaults to true;
	 */
	private boolean escape = true;

	/**
	 * Closes the popup when clicked outside of it; defaults to true;
	 */
	private boolean blur = true;

	/**
	 * Sets default z-index to the popup (2001) and to the background (2000); defaults to true;
	 */
	private boolean setzindex = true;

	/**
	 * Sets highest z-index on the page to the popup; defaults to false;
	 */
	private boolean autozindex = false;

	/**
	 * Lock keyboard focus inside of popup. Recommended to be enabled; defaults to true;
	 */
	private boolean keepfocus = true;

	/**
	 * Enables you to specify the element which will be focused upon showing the popup. By default,
	 * the popup element #my_popup will receive the initial focus. defaults to 'my_popup';
	 */
	private String focuselement = null;

	/**
	 * Sets a delay in milliseconds before focusing an element. This is to prevent page scrolling
	 * during opening transition, as browsers will try to move the viewport to an element which
	 * received the focus. (defaults to 50)
	 */
	private int focusdelay = 50;

	/**
	 * Sets a page container (to help screen reader users). Page container should be the element
	 * that surrounds all the content on the page (e.g. '.container' in the case of this very page).
	 * 
	 * It's highly recommended that you set the page container to help some screen readers read the
	 * modal dialog correctly. Doing so, when the popup is visible, aria-hidden="true" will be set
	 * to the page container and aria-hidden="false" to the popup, and vice-versa when the popup
	 * closes. You can set `pagecontainer` once per website (e.g. $.fn.popup.defaults.pagecontainer
	 * = '.container').
	 */
	private String pagecontainer = null;

	/**
	 * Shows a default browser outline on popup element when focused.
	 * 
	 * Setting to false is equivalent to #my_popup{outline: none;}; defaults to false;
	 */
	private boolean outline = false;

	/**
	 * Removes popup element from the DOM after closing transition.; defaults to false;
	 */
	private boolean detach = false;

	/**
	 * Enables you to define custom element which will open the popup on click. By default
	 * '.{popup_id}_open'.
	 */
	private String openelement = null;

	/**
	 * Enables you to define custom element which will close the popup on click. By default
	 * '.{popup_id}_close'.
	 */
	private String closeelement = null;

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
	 */
	private String transition = null;

	/**
	 * Type: object JQuery or DOM object
	 * 
	 * Sets an element to be an anchor for tooltip position.
	 * 
	 * For example, for multiple opening links using the same tooltip on mouseover:
	 * 
	 * $('.my_popup_open').on({ mouseenter: function(event) { $('#my_popup').popup({ tooltipanchor:
	 * event.target, autoopen: true, type: 'tooltip' }); }, mouseleave: function() {
	 * $('#my_popup').popup('hide'); } });
	 */
	private String tooltipanchor = null;

	/**
	 * Type: function Description: Callback function which will execute before the popup is opened.
	 * Default is null.
	 */
	private String beforeopen = null;

	/**
	 * Type: function Description: Callback function which will execute when the popup starts to
	 * open. Default is null.
	 */
	private String onopen = null;

	/**
	 * Type: function Description: Callback function which will execute when the popup starts to
	 * close. Default is null.
	 */
	private String onclose = null;

	/**
	 * Type: function Description: Callback function which will execute after the opening CSS
	 * transition is over, only if transition actually occurs and if supported by the browser.
	 * Default is null.
	 */
	private String opentransitionend = null;

	/**
	 * Type: function Description: Callback function which will execute after the closing CSS
	 * transition is over, only if transition actually occurs and if supported by the browser.
	 * Default is null.
	 */
	private String closetransitionend = null;

}