package de.alpharogroup.wicket.components.examples.urls;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.jaulp.wicket.base.util.WicketComponentUtils;

import de.alpharogroup.wicket.util.WicketUrlResolver;


public class WicketUrlPage extends WebPage {
	private static final long serialVersionUID = 1L;

    public WicketUrlPage(final PageParameters parameters) {
	super(parameters);

	add(new Label("urlForForgottenPassword", Model.of(WicketUrlResolver.getUrlForForgottenPassword("bla", "1HE23WE", WicketUrlPage.class, true))));
	add(new Label("urlForForgottenPassword2", Model.of(WicketUrlResolver.getUrlForForgottenPassword("bla", "1HE23WE", WicketUrlPage.class, false))));
	add(new Label("urlForForgottenPassword3", Model.of(WicketUrlResolver.getUrlForForgottenPassword(WicketComponentUtils.getRequestURL(), "bla", "1HE23WE", WicketUrlPage.class))));
	
	add(new Label("toFullUrl", Model.of(WicketUrlResolver.toFullUrl(false))));
	add(new Label("toFullUrl1", Model.of(WicketUrlResolver.toFullUrl(true))));
    }
}
