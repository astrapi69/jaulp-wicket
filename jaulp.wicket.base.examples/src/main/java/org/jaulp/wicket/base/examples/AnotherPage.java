package org.jaulp.wicket.base.examples;

import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.WebPage;

public class AnotherPage extends WebPage {
	private static final long serialVersionUID = 1L;

    public AnotherPage(final PageParameters parameters) {
	super(parameters);
	
	add(new MenubarPanel("menubarPanel"));

	add(new Label("version", getApplication().getFrameworkSettings().getVersion()));

        // TODO Add your page's components here

    }
}
