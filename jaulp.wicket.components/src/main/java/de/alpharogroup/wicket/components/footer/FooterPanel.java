package de.alpharogroup.wicket.components.footer;

import org.apache.wicket.Component;
import org.apache.wicket.markup.html.panel.Panel;

public abstract class FooterPanel extends Panel {

	/**
	 * The serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;
	
	private Component footerMenuPanel;

	public FooterPanel(String id) {
		super(id);
		add(footerMenuPanel = newFooterMenuPanel("footerMenuPanel"));
	}

	protected abstract Component newFooterMenuPanel(String id);

	public Component getFooterMenuPanel() {
		return footerMenuPanel;
	}

}
