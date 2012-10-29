package org.jaulp.wicket.components.menu.suckerfish;

import org.apache.wicket.MarkupContainer;
import org.apache.wicket.markup.html.link.AbstractLink;
import org.apache.wicket.markup.html.panel.Fragment;

/**
 * The Class LinkFragment.
 * 
 * @author Asterios Raptis
 */
public final class LinkFragment extends Fragment {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 0L;

	/**
	 * Instantiates a new link fragment.
	 * 
	 * @param link
	 *            the link
	 */
	public LinkFragment(final AbstractLink link, final MarkupContainer markupProvider) {
		super("linkfragment", "LINKFRAGMENT", markupProvider);
		add(link);
	}
}