package de.alpharogroup.wicket.components.examples.imprint;
import org.apache.wicket.markup.html.WebPage;

import de.alpharogroup.wicket.components.imprint.ImprintPanel;

//import wicket.app.base.components.panels.imprint.ImprintPanel;

/**
 * The Class ImprintPage.
 * 
 * @author Asterios Raptis
 */
public class ImprintPage extends WebPage {

	/**
	 * The serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;
	@Override
	protected void onInitialize() {
		super.onInitialize();
		add(
				new ApplicationImprintPanel("imprint") {
			private static final long serialVersionUID = 1L;
			@Override
			public String getDomainName() {
				return "lessonslearning.com";
			}			
		});
	}

}