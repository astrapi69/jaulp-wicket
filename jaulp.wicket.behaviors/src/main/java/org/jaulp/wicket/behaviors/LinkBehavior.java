package org.jaulp.wicket.behaviors;

import org.apache.wicket.Component;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.markup.ComponentTag;

/**
 * The Class LinkBehavior adds the ability to a component to act as a link, </br> i.e.
 * like a tablerow tag( &lt;tr&gt; ) or a list item (&lt;li&gt;) the ability to act as a link. </br>For instance
 * in repeaters like the DataView.
 */
public class LinkBehavior extends Behavior {

	/**
	 * The serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;

	/** The onmouseover color. */
	private String onmouseoverColor;

	/** The onmouseout color. */
	private String onmouseoutColor;

	/** The absolute path. */
	private String absolutePath;

	/**
	 * Instantiates a new tablerow link behavior.
	 * 
	 * @param onmouseoverColor
	 *            the onmouseover color
	 * @param onmouseoutColor
	 *            the onmouseout color
	 * @param absolutePath
	 *            the absolute path
	 */
	public LinkBehavior(final String onmouseoverColor,
			final String onmouseoutColor, final String absolutePath) {
		super();
		this.onmouseoverColor = onmouseoverColor;
		this.onmouseoutColor = onmouseoutColor;
		this.absolutePath = absolutePath;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see org.apache.wicket.behavior.AbstractBehavior#onComponentTag(org.apache.wicket.Component,
	 *      org.apache.wicket.markup.ComponentTag)
	 */
	@Override
	public void onComponentTag(final Component component, final ComponentTag tag) {

		tag.put("onmouseover", "this.style.backgroundColor = '"
				+ onmouseoverColor + "';this.style.cursor = 'pointer';");

		tag.put("onmouseout", "this.style.backgroundColor = '"
				+ onmouseoutColor + "';this.style.cursor ='default';");

		tag.put("onclick", "document.location.href = '" + absolutePath + "';");

	}

}
