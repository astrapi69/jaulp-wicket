package org.jaulp.wicket.xslt.examples.pages;
import org.apache.wicket.markup.html.WebPage;
import org.jaulp.wicket.xslt.examples.panels.HomePanel;

public class HomePage extends WebPage {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public HomePage() {
		super();
		add(new HomePanel("homePanel"));
	}

}