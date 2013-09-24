package org.jaulp.wicket.behaviors;

import org.apache.wicket.Component;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.StringHeaderItem;

/**
 * The Class FaviconBehavior adds a StringHeaderItem for the Favicon.
 */
public class FaviconBehavior extends Behavior  {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
     * {@inheritDoc}
     */
	public void renderHead(Component component, IHeaderResponse response) {
		response.render(new StringHeaderItem(
				"<link type=\"image/x-icon\" rel=\"shortcut icon\" href=\"favicon.ico\" />"));

	}
}
