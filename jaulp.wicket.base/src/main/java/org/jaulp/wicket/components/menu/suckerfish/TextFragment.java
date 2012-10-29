package org.jaulp.wicket.components.menu.suckerfish;

import org.apache.wicket.MarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Fragment;

/**
 * The Class TextFragment.
 * 
 * @author Asterios Raptis
 */
class TextFragment extends Fragment {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 0L;

	/**
	 * Instantiates a new text fragment.
	 * 
	 * @param label
	 *            the label
	 */
	public TextFragment(final Label label, final MarkupContainer markupProvider) {
		super("linkfragment", "TEXTFRAGMENT", markupProvider);
		add(label);
	}
}