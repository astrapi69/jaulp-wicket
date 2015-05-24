package de.alpharogroup.wicket.behaviors.pnotify;

import java.util.HashSet;
import java.util.Set;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import de.alpharogroup.wicket.base.util.template.QuotationMarkType;
import de.alpharogroup.wicket.base.util.template.StringTextType;
import de.alpharogroup.wicket.base.util.template.StringTextValue;

/**
 * This class encapsulates various settings for the pnotify js lib for use with wicket. See the
 * documentation for the pnotify for further information.
 */
@Getter
@EqualsAndHashCode
@ToString
public class PnotifySettings
{

	/**
	 * Gets all settings in a {@link java.util.Set}.
	 * 
	 * @return all settings in a {@link java.util.Set}.
	 */
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

	public static final String VAR_DEFAULT_STACK = "var default_stack = {\n" + "dir1: \"down\",\n"
		+ "dir2: \"left\",\n" + "push: \"bottom\",\n" + "spacing1: 25,\n" + "spacing2: 25,\n"
		+ "context: $(\"body\")\n" + "};";
	/**
	 * The notice's title. Default: null
	 */
	private StringTextValue<String> title = new StringTextValue<String>("title",
		StringTextType.STRING);

	/**
	 * Whether to escape the content of the title. (Not allow HTML.) Default: false
	 */
	private StringTextValue<Boolean> title_escape = new StringTextValue<Boolean>("title_escape",
		StringTextType.BOOLEAN);

	/**
	 * The notice's text. Default: null
	 */
	private StringTextValue<String> text = new StringTextValue<String>("text",
		StringTextType.STRING);

	/**
	 * Whether to escape the content of the text. (Not allow HTML.) Default: false
	 */
	private StringTextValue<Boolean> text_escape = new StringTextValue<Boolean>("text_escape",
		StringTextType.BOOLEAN);

	/**
	 * What styling classes to use. Default: bootstrap3
	 */
	private StringTextValue<String> styling = new StringTextValue<String>("styling", "bootstrap3",
		StringTextType.STRING);

	/**
	 * Additional classes to be added to the notice. (For custom styling.) Default: empty string
	 */
	private StringTextValue<String> addclass = new StringTextValue<String>("addclass", "",
		StringTextType.STRING);

	/**
	 * Class to be added to the notice for corner styling. Default: empty string
	 */
	private StringTextValue<String> cornerclass = new StringTextValue<String>("cornerclass", "",
		StringTextType.STRING);

	/**
	 * Display the notice when it is created. Default: true
	 */
	private StringTextValue<Boolean> auto_display = new StringTextValue<Boolean>("auto_display",
		true, StringTextType.BOOLEAN);

	/**
	 * Width of the notice. Default: 300px
	 */
	private StringTextValue<String> width = new StringTextValue<String>("width", "300px",
		StringTextType.STRING);

	/**
	 * Minimum height of the notice. It will expand to fit content. Default: 16px
	 */
	private StringTextValue<String> min_height = new StringTextValue<String>("min_height", "16px",
		StringTextType.STRING);

	/**
	 * The type of the notice. Possible values are "notice", "info", "success", or "error". Default:
	 * notice
	 */
	private StringTextValue<String> type = new StringTextValue<String>("type", "notice",
		StringTextType.STRING);

	/**
	 * Set icon to true to use the default icon for the selected style/type, false for no icon, or a
	 * string for your own icon class. Default: true
	 */
	private StringTextValue<Boolean> icon = new StringTextValue<Boolean>("icon", true,
		StringTextType.BOOLEAN);

	/**
	 * The opacity of the notice. Default: 1
	 */
	private StringTextValue<Integer> opacity = new StringTextValue<Integer>("opacity", 1,
		StringTextType.INTEGER);

	/**
	 * Determines how the notification will animate in and out. Use an object with effect_in and
	 * effect_out to use different effects. Default: 'fade' Posible values: fade, grow, swing,
	 * slide, fall
	 */
	private StringTextValue<String> animation = new StringTextValue<String>("animation", "fade",
		StringTextType.STRING);

	/**
	 * Determines how the notification will animate in and out. Default: 'fade' Posible values:
	 * "slow", "def" or "normal", "fast" or number of milliseconds.
	 */
	private StringTextValue<String> animation_speed = new StringTextValue<String>(
		"animation_speed", "slow", StringTextType.STRING);

	/**
	 * Specify a specific duration of position animation. Default: 500
	 */
	private StringTextValue<Integer> position_animate_speed = new StringTextValue<Integer>(
		"position_animate_speed", 500, StringTextType.INTEGER);

	/**
	 * Display a drop shadow. Default: true
	 */
	private StringTextValue<Boolean> shadow = new StringTextValue<Boolean>("shadow", true,
		StringTextType.BOOLEAN);

	/**
	 * After a delay, remove the notice. Default: true
	 */
	private StringTextValue<Boolean> hide = new StringTextValue<Boolean>("hide", true,
		StringTextType.BOOLEAN);

	/**
	 * Delay in milliseconds before the notice is removed. Default: 8000
	 */
	private StringTextValue<Integer> delay = new StringTextValue<Integer>("delay", 8000,
		StringTextType.INTEGER);

	/**
	 * Reset the hide timer if the mouse moves over the notice. Default: true
	 */
	private StringTextValue<Boolean> mouse_reset = new StringTextValue<Boolean>("mouse_reset",
		true, StringTextType.BOOLEAN);

	/**
	 * Remove the notice's elements from the DOM after it is removed. Default: true
	 */
	private StringTextValue<Boolean> remove = new StringTextValue<Boolean>("remove", true,
		StringTextType.BOOLEAN);

	/**
	 * Change new lines to br tags. Default: true
	 */
	private StringTextValue<Boolean> insert_brs = new StringTextValue<Boolean>("insert_brs", true,
		StringTextType.BOOLEAN);

	/**
	 * Whether to remove notices from the global array. Default: true
	 */
	private StringTextValue<Boolean> destroy = new StringTextValue<Boolean>("destroy", true,
		StringTextType.BOOLEAN);

	/**
	 * The stack on which the notices will be placed. Also controls the direction the notices stack.
	 * Default: default_stack Posible values: js array
	 */
	private StringTextValue<String> stack = new StringTextValue<String>("stack",
		new StackSettings().asJavascriptArray(), StringTextType.STRING)
		.setQuotationMarkType(QuotationMarkType.NONE);

}
