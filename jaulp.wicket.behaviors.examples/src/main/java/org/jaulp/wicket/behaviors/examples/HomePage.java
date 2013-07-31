package org.jaulp.wicket.behaviors.examples;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.model.StringResourceModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.jaulp.wicket.behaviors.AddJavascriptBehavior;
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
    	Button button = new Button("button"){

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void onSubmit() {
				
			}
    		
    	};
    	String jsCodeNewAlert = 
    			"function newAlert(type, message, delay) {	$(\"#alert-area\").append(" +
    			"			$(\"<div class='alert-message \" + type + \" fade in' data-alert><p> \"" +
    			"				+ message + \" </p></div>\"));" +
    			"	$(\".alert-message\").delay(delay).fadeOut(\"slow\", function() {" +
    			"		$(this).remove();	" +
    			"});}";
    	add(new AddJavascriptBehavior("alert('foo bar');", "xy"));
    	add(button);
    	
    }
}
