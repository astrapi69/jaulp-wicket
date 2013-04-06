package org.jaulp.wicket.base.examples;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.jaulp.wicket.base.mainbase.BaseMainPage;

public class HomePage extends BaseMainPage {
	private static final long serialVersionUID = 1L;

    public HomePage(final PageParameters parameters) {
	super(parameters);
	
	add(new MenubarPanel("menubarPanel"));
	
	add(new Label("text", Model.of("This is the home page.")));

    }
}
