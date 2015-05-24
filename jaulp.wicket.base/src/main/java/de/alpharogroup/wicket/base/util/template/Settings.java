package de.alpharogroup.wicket.base.util.template;

import java.io.Serializable;
import java.util.Set;

/**
 * Interface for javascript, jquery plugins and other settings.
 */
public interface Settings extends Serializable
{

	/**
	 * Returns a set with all settings.
	 *
	 * @return a set with all settings.
	 */
	Set<StringTextValue<?>> asSet();
}
