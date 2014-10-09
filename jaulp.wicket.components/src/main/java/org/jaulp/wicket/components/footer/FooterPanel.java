package org.jaulp.wicket.components.footer;

import org.apache.wicket.markup.html.panel.Panel;

public abstract class FooterPanel extends Panel {

	/**
	 * The serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;
	
	private FooterMenuPanel footerMenuPanel;

	public FooterPanel(String id) {
		super(id);
		add(footerMenuPanel = newFooterMenuPanel("footerMenuPanel"));
	}

	protected abstract FooterMenuPanel newFooterMenuPanel(String id);

	public FooterMenuPanel getFooterMenuPanel() {
		return footerMenuPanel;
	}

}
