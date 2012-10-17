package org.jaulp.wicket.behaviors.examples;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.model.StringResourceModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.jaulp.wicket.behaviors.WicketComponentTreeBehavior;
import org.jaulp.wicket.behaviors.models.MailtoModel;
import org.jaulp.wicket.behaviors.components.MailtoLabel;
/**
 * Homepage
 */
public class HomePage extends WebPage {

	private static final long serialVersionUID = 1L;

	// Add any page properties or variables here

    /**
	 * Constructor that is invoked when page is invoked without a session.
	 *
	 * @param parameters
	 *            Page parameters
	 */
    public HomePage(final PageParameters parameters) {
    	StringResourceModel mailtoAddresModel = new StringResourceModel("mailtoAddresModel.value", this, null);
    	StringResourceModel mailtoViewModel = new StringResourceModel("mailtoViewModel.value", this, null);
    	MailtoModel mailtoModel = new MailtoModel(mailtoAddresModel, mailtoViewModel );
    	
    	add(new MailtoLabel("mailtoLabel", mailtoModel).add(new WicketComponentTreeBehavior()) );
    	add(new WicketComponentTreeBehavior());
    }
}
