package org.jaulp.wicket.behaviors.examples;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.StringResourceModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.jaulp.wicket.behaviors.AddJavascriptBehavior;
import org.jaulp.wicket.behaviors.AddJsResourceReferenceBehavior;
import org.jaulp.wicket.behaviors.FaviconBehavior;
import org.jaulp.wicket.behaviors.components.MailtoLabel;
import org.jaulp.wicket.behaviors.models.MailtoModel;
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
    	
    	add(new MailtoLabel("mailtoLabel", mailtoModel) );
    	
    	Button button = new Button("button"){

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void onSubmit() {
				
			}
    		
    	};
    	 add(new AddJavascriptBehavior("alert('foo bar');", "xy"));
    	
    	add(new Link<String>("focusRequestExamplePage") {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void onClick() {
				 setResponsePage(FocusRequestExamplePage.class);
			}
		});

    	add(button);
    	add(new FaviconBehavior());
    	add(new AddJsResourceReferenceBehavior(this.getClass(), "functions.js", "func"));
    	
    }
}
