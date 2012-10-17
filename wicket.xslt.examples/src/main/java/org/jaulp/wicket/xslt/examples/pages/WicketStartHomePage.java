package org.jaulp.wicket.xslt.examples.pages;

import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.WebPage;

public class WicketStartHomePage extends WebPage {
	private static final long serialVersionUID = 1L;

    public WicketStartHomePage(final PageParameters parameters) {
		add(new Label("version", getApplication().getFrameworkSettings().getVersion()));
        // TODO Add your page's components here
    }
}
