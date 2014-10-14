package de.alpharogroup.wicket.components.examples.urls;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.Model;
import org.jaulp.wicket.base.BasePanel;
import org.jaulp.wicket.base.util.WicketComponentUtils;

import de.alpharogroup.wicket.util.WicketUrlResolver;

public class WicketUrlPanel extends BasePanel<Object> {
	private static final long serialVersionUID = 1L;

	public WicketUrlPanel(String id) {
		super(id);

		add(new Label("urlForForgottenPassword", Model.of(WicketUrlResolver.getUrlForForgottenPassword("bla", "1HE23WE", WicketUrlPage.class, true))));
		add(new Label("urlForForgottenPassword2", Model.of(WicketUrlResolver.getUrlForForgottenPassword("bla", "1HE23WE", WicketUrlPage.class, false))));
		add(new Label("urlForForgottenPassword3", Model.of(WicketUrlResolver.getUrlForForgottenPassword(WicketComponentUtils.getRequestURL(), "bla", "1HE23WE", WicketUrlPage.class))));
		
		add(new Label("toFullUrl", Model.of(WicketUrlResolver.toFullUrl(false))));
		add(new Label("toFullUrl1", Model.of(WicketUrlResolver.toFullUrl(true))));

	}
}
