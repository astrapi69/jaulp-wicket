package org.jaulp.wicket.behaviors.examples;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.StringResourceModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.jaulp.wicket.behaviors.AddJavascriptBehavior;
import org.jaulp.wicket.behaviors.FocusRequestBehavior;
import org.jaulp.wicket.behaviors.WicketComponentTreeBehavior;
import org.jaulp.wicket.behaviors.components.MailtoLabel;
import org.jaulp.wicket.behaviors.models.MailtoModel;
/**
 * Homepage
 */
public class FocusRequestExamplePage extends WebPage {

	private static final long serialVersionUID = 1L;

	// Add any page properties or variables here

    /**
	 * Constructor that is invoked when page is invoked without a session.
	 *
	 * @param parameters
	 *            Page parameters
	 */
    public FocusRequestExamplePage(final PageParameters parameters) {

    	// message field
		final TextField<String> messageField = new TextField<String>("message");
		messageField.add(new FocusRequestBehavior());
    	Button button = new Button("button"){

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void onSubmit() {
				
			}
    		
    	};
		add(messageField);
    	add(button);
    	
    }
}
