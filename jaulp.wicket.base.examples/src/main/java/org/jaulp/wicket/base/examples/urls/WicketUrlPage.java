package org.jaulp.wicket.base.examples.urls;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.Model;
import org.apache.wicket.protocol.http.RequestUtils;
import org.apache.wicket.request.Url;
import org.apache.wicket.request.cycle.RequestCycle;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.jaulp.wicket.base.examples.MenubarPanel;
import org.jaulp.wicket.base.examples.WicketApplication;
import org.jaulp.wicket.base.utils.WicketComponentUtils;
import org.jaulp.wicket.base.utils.WicketUrlUtils;

public class WicketUrlPage extends WebPage {
	private static final long serialVersionUID = 1L;

    public WicketUrlPage(final PageParameters parameters) {
	super(parameters);
	
	add(new MenubarPanel("menubarPanel"));

	add(new Label("baseUrlPage", Model.of(WicketUrlUtils.getBaseUrl(this))));
	add(new Label("baseUrlClass", Model.of(WicketUrlUtils.getBaseUrl(WicketUrlPage.class))));
	add(new Label("contextPath", Model.of(WicketUrlUtils.getContextPath())));
	add(new Label("contextPathPage", Model.of(WicketUrlUtils.getContextPath(this))));
	add(new Label("pageUrlClass", Model.of(WicketUrlUtils.getPageUrl(WicketUrlPage.class))));
	add(new Label("urlClass", Model.of(WicketUrlUtils.getPageUrl(this))));
	add(new Label("urlAsStringClass", Model.of(WicketUrlUtils.getUrlAsString(WicketUrlPage.class))));
	add(new Label("urlAsStringPage", Model.of(WicketUrlUtils.getUrlAsString(this))));
	add(new Label("contextPathApplication", Model.of(WicketComponentUtils.getContextPath(WicketApplication.get()))));
	add(new Label("requestURL", Model.of(WicketComponentUtils.getRequestURL())));
	add(new Label("remoteHost", Model.of(WicketComponentUtils.getIpAddress())));
	add(new Label("remoteAddr", Model.of(WicketComponentUtils.getRemoteAddr())));
	add(new Label("localName", Model.of(WicketUrlUtils.getLocalName())));
	add(new Label("serverName", Model.of(WicketUrlUtils.getServerName())));
	add(new Label("domainUrl", Model.of(WicketUrlUtils.getDomainUrl())));
    }
}
