package org.jaulp.wicket.base.examples;

import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.model.Model;

public class SubmenuPage extends WebPage {
	private static final long serialVersionUID = 1L;

    public SubmenuPage(final PageParameters parameters) {
	super(parameters);
	
	add(new MenubarPanel("menubarPanel"));
	
	add(new Label("text", Model.of("This is a submenu page.")));

    }
}
