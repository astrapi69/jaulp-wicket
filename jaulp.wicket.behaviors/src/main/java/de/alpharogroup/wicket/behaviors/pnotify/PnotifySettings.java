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
package de.alpharogroup.wicket.behaviors.pnotify;

import java.util.HashSet;
import java.util.Set;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import de.alpharogroup.wicket.base.util.template.QuotationMarkType;
import de.alpharogroup.wicket.base.util.template.Settings;
import de.alpharogroup.wicket.base.util.template.StringTextType;
import de.alpharogroup.wicket.base.util.template.StringTextValue;

/**
 * This class encapsulates various settings for the pnotify js lib for use with wicket. See the
 * documentation for the pnotify for further information.
 */
@Getter
@EqualsAndHashCode
@ToString
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class PnotifySettings implements Settings
{

	/**
	 * The serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * The notice's title. Default: null
	 */
	private final StringTextValue<String> title = new StringTextValue<>("title",
		StringTextType.STRING);

	/**
	 * Whether to escape the content of the title. (Not allow HTML.) Default: false
	 */
	private final StringTextValue<Boolean> title_escape = new StringTextValue<>("title_escape",
		StringTextType.BOOLEAN);

	/**
	 * The notice's text. Default: null
	 */
	private final StringTextValue<String> text = new StringTextValue<>("text",
		StringTextType.STRING);

	/**
	 * Whether to escape the content of the text. (Not allow HTML.) Default: false
	 */
	private final StringTextValue<Boolean> text_escape = new StringTextValue<>("text_escape",
		StringTextType.BOOLEAN);

	/**
	 * What styling classes to use. Default: bootstrap3
	 */
	private final StringTextValue<String> styling = new StringTextValue<>("styling", "bootstrap3",
		StringTextType.STRING);

	/**
	 * Additional classes to be added to the notice. (For custom styling.) Default: empty string
	 */
	private final StringTextValue<String> addclass = new StringTextValue<>("addclass", "",
		StringTextType.STRING);

	/**
	 * Class to be added to the notice for corner styling. Default: empty string
	 */
	private final StringTextValue<String> cornerclass = new StringTextValue<>("cornerclass", "",
		StringTextType.STRING);

	/**
	 * Display the notice when it is created. Default: true
	 */
	private final StringTextValue<Boolean> auto_display = new StringTextValue<>("auto_display",
		true, StringTextType.BOOLEAN);

	/**
	 * Width of the notice. Default: 300px
	 */
	private final StringTextValue<String> width = new StringTextValue<>("width", "300px",
		StringTextType.STRING);

	/**
	 * Minimum height of the notice. It will expand to fit content. Default: 16px
	 */
	private final StringTextValue<String> min_height = new StringTextValue<>("min_height", "16px",
		StringTextType.STRING);

	/**
	 * The type of the notice. Possible values are "notice", "info", "success", or "error". Default:
	 * notice
	 */
	private final StringTextValue<String> type = new StringTextValue<>("type", "notice",
		StringTextType.STRING);

	/**
	 * Set icon to true to use the default icon for the selected style/type, false for no icon, or a
	 * string for your own icon class. Default: true
	 */
	private final StringTextValue<Boolean> icon = new StringTextValue<>("icon", true,
		StringTextType.BOOLEAN);

	/**
	 * The opacity of the notice. Default: 1
	 */
	private final StringTextValue<Integer> opacity = new StringTextValue<>("opacity", 1,
		StringTextType.INTEGER);

	/**
	 * Determines how the notification will animate in and out. Use an object with effect_in and
	 * effect_out to use different effects. Default: 'fade' Posible values: fade, grow, swing,
	 * slide, fall
	 */
	private final StringTextValue<String> animation = new StringTextValue<>("animation", "fade",
		StringTextType.STRING);

	/**
	 * Determines how the notification will animate in and out. Default: 'fade' Posible values:
	 * "slow", "def" or "normal", "fast" or number of milliseconds.
	 */
	private final StringTextValue<String> animation_speed = new StringTextValue<>(
		"animation_speed", "slow", StringTextType.STRING);

	/**
	 * Specify a specific duration of position animation. Default: 500
	 */
	private final StringTextValue<Integer> position_animate_speed = new StringTextValue<>(
		"position_animate_speed", 500, StringTextType.INTEGER);

	/**
	 * Display a drop shadow. Default: true
	 */
	private final StringTextValue<Boolean> shadow = new StringTextValue<>("shadow", true,
		StringTextType.BOOLEAN);

	/**
	 * After a delay, remove the notice. Default: true
	 */
	private final StringTextValue<Boolean> hide = new StringTextValue<>("hide", true,
		StringTextType.BOOLEAN);

	/**
	 * Delay in milliseconds before the notice is removed. Default: 8000
	 */
	private final StringTextValue<Integer> delay = new StringTextValue<>("delay", 8000,
		StringTextType.INTEGER);

	/**
	 * Reset the hide timer if the mouse moves over the notice. Default: true
	 */
	private final StringTextValue<Boolean> mouse_reset = new StringTextValue<>("mouse_reset",
		true, StringTextType.BOOLEAN);

	/**
	 * Remove the notice's elements from the DOM after it is removed. Default: true
	 */
	private final StringTextValue<Boolean> remove = new StringTextValue<>("remove", true,
		StringTextType.BOOLEAN);

	/**
	 * Change new lines to br tags. Default: true
	 */
	private final StringTextValue<Boolean> insert_brs = new StringTextValue<>("insert_brs", true,
		StringTextType.BOOLEAN);

	/**
	 * Whether to remove notices from the global array. Default: true
	 */
	private final StringTextValue<Boolean> destroy = new StringTextValue<>("destroy", true,
		StringTextType.BOOLEAN);

	/**
	 * The stack on which the notices will be placed. Also controls the direction the notices stack.
	 * Default: default_stack Posible values: js array
	 */
	private final StringTextValue<String> stack = new StringTextValue<>("stack",
		new StackSettings().asJavascriptArray(), StringTextType.STRING)
		.setQuotationMarkType(QuotationMarkType.NONE);

  /**
   * {@inheritDoc}
   */
  @Override
  public Set<StringTextValue<?>> asSet()
  {
    Set<StringTextValue<?>> allSettings = new HashSet<>();
    allSettings.add(getAddclass());
    allSettings.add(getAnimation());
    allSettings.add(getAnimation_speed());
    allSettings.add(getAuto_display());
    allSettings.add(getCornerclass());
    allSettings.add(getDelay());
    allSettings.add(getDestroy());
    allSettings.add(getHide());
    allSettings.add(getIcon());
    allSettings.add(getInsert_brs());
    allSettings.add(getMin_height());
    allSettings.add(getMouse_reset());
    allSettings.add(getOpacity());
    allSettings.add(getPosition_animate_speed());
    allSettings.add(getRemove());
    allSettings.add(getShadow());
    allSettings.add(getStack());
    allSettings.add(getStyling());
    allSettings.add(getText());
    allSettings.add(getText_escape());
    allSettings.add(getTitle());
    allSettings.add(getTitle_escape());
    allSettings.add(getType());
    allSettings.add(getWidth());
    return allSettings;
  }
}
