package de.alpharogroup.wicket.components.footer;

import org.apache.wicket.Component;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.panel.Panel;

public abstract class FooterMenuPanel extends Panel {

	/**
	 * The serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;
	
	private Component copyrightLink;	
	private Component imprintLink;
	private Component termOfUseLink;

	public FooterMenuPanel(final String id) {
		super(id);		
		add(copyrightLink = newCopyrightLink("copyrightLink"));
		add(imprintLink = newImprintLink("imprintLink"));
		add(termOfUseLink = newTermOfUseLink("termOfUseLink"));
	}
	protected abstract Component newCopyrightLink(String id);
	protected abstract Component newImprintLink(String id);
	protected abstract Component newTermOfUseLink(String id);
	
	protected abstract String getCopyrightDefaultValue();

	protected abstract Class<? extends WebPage> getImprintPageClass();
	
	protected abstract Class<? extends WebPage> getTermOfUsePageClass();

	protected abstract String getCopyrightUrl(); 

	public Component getCopyrightLink() {
		return copyrightLink;
	}

	public Component getImprintLink() {
		return imprintLink;
	}

	public Component getTermOfUseLink() {
		return termOfUseLink;
	}

}
