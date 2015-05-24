package de.alpharogroup.wicket.behaviors.pnotify;

import java.util.HashSet;
import java.util.Set;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import de.alpharogroup.wicket.base.util.template.QuotationMarkType;
import de.alpharogroup.wicket.base.util.template.StringTextType;
import de.alpharogroup.wicket.base.util.template.StringTextValue;
import de.alpharogroup.wicket.base.util.template.TextTemplateUtils;

/**
 * This class encapsulates various settings for the pnotify stack. See the documentation for the
 * pnotify for further information.
 */
@Getter
@EqualsAndHashCode
@ToString
public class StackSettings
{

	/**
	 * The dir1 value of the stack. Default: down
	 */
	private StringTextValue<String> dir1 = new StringTextValue<String>("dir1", "down",
		StringTextType.STRING);

	/**
	 * The dir2 value of the stack. Default: down
	 */
	private StringTextValue<String> dir2 = new StringTextValue<String>("dir2", "left",
		StringTextType.STRING);

	/**
	 * The push value of the stack. Default: bottom
	 */
	private StringTextValue<String> push = new StringTextValue<String>("push", "bottom",
		StringTextType.STRING);

	/**
	 * The spacing1 of the stack. Default: 25
	 */
	private StringTextValue<Integer> spacing1 = new StringTextValue<Integer>("spacing1", 25,
		StringTextType.INTEGER);

	/**
	 * The spacing2 of the stack. Default: 25
	 */
	private StringTextValue<Integer> spacing2 = new StringTextValue<Integer>("spacing2", 25,
		StringTextType.INTEGER);

	/**
	 * The push value of the stack. Default: bottom
	 */
	private StringTextValue<String> context = new StringTextValue<String>("context", "$(\"body\")",
		StringTextType.STRING).setQuotationMarkType(QuotationMarkType.NONE);

	/**
	 * Gets all settings in a {@link java.util.Set}.
	 * 
	 * @return all settings in a {@link java.util.Set}.
	 */
	public Set<StringTextValue<?>> asSet()
	{
		Set<StringTextValue<?>> allSettings = new HashSet<>();
		allSettings.add(getContext());
		allSettings.add(getDir1());
		allSettings.add(getDir2());
		allSettings.add(getPush());
		allSettings.add(getSpacing1());
		allSettings.add(getSpacing2());
		return allSettings;
	}

	/**
	 * This settings as a javascript array.
	 *
	 * @return This settings as a javascript array.
	 */
	public String asJavascriptArray()
	{
		return TextTemplateUtils.asJavascriptArray(asSet());
	}
}
