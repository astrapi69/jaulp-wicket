/**
 * Copyright (C) 2010 Asterios Raptis
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jaulp.wicket.base.examples.urls;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.jaulp.wicket.base.examples.MenubarPanel;
import org.jaulp.wicket.base.examples.application.WicketApplication;
import org.jaulp.wicket.base.util.ApplicationUtils;
import org.jaulp.wicket.base.util.WicketComponentUtils;
import org.jaulp.wicket.base.util.url.WicketUrlUtils;
import org.wicketstuff.annotation.mount.MountPath;

@MountPath("/urlpageexample")
public class WicketUrlPage extends WebPage {
	private static final long serialVersionUID = 1L;

    public WicketUrlPage(final PageParameters parameters) {
	super(parameters);
	
	add(new MenubarPanel("menubarPanel"));
	add(new Label("baseUrl", Model.of(WicketUrlUtils.getBaseUrl())));
	add(new Label("baseUrlPage", Model.of(WicketUrlUtils.getBaseUrl(this))));
	add(new Label("baseUrlClass", Model.of(WicketUrlUtils.getBaseUrl(WicketUrlPage.class))));
	add(new Label("contextPath", Model.of(WicketUrlUtils.getContextPath())));
	add(new Label("contextPathPage", Model.of(WicketUrlUtils.getContextPath(this))));
	add(new Label("pageUrlClass", Model.of(WicketUrlUtils.getPageUrl(WicketUrlPage.class))));
	add(new Label("canonicalPageUrlClass", Model.of(WicketUrlUtils.getCanonicalPageUrl(WicketUrlPage.class))));
	add(new Label("urlClass", Model.of(WicketUrlUtils.getPageUrl(this))));
	add(new Label("urlAsStringClass", Model.of(WicketUrlUtils.getUrlAsString(WicketUrlPage.class))));
	add(new Label("urlAsStringPage", Model.of(WicketUrlUtils.getUrlAsString(this))));
	add(new Label("contextPathApplication", Model.of(ApplicationUtils.getContextPath(WicketApplication.get()))));
	add(new Label("requestURL", Model.of(WicketComponentUtils.getRequestURL())));
	add(new Label("remoteHost", Model.of(WicketComponentUtils.getIpAddress())));
	add(new Label("remoteAddr", Model.of(WicketComponentUtils.getRemoteAddr())));
	add(new Label("localName", Model.of(WicketUrlUtils.getLocalName())));
	add(new Label("serverName", Model.of(WicketUrlUtils.getServerName())));
	add(new Label("domainUrl", Model.of(WicketUrlUtils.getDomainUrl())));
	
	add(new Label("domainUrl2", Model.of(WicketUrlUtils.getDomainUrl(false))));
	add(new Label("domainUrl3", Model.of(WicketUrlUtils.getDomainUrl(false, false))));
	

	PageParameters params = new PageParameters();
	params.add("USERNAME", "bla");
	params.add("CONFIRMATION_CODE", "1HE23WE");
	add(new Label("absoluteUrlFor", Model.of(WicketUrlUtils.absoluteUrlFor(WicketUrlPage.class, params, true))));
	add(new Label("absoluteUrlFor2", Model.of(WicketUrlUtils.absoluteUrlFor(WicketUrlPage.class, params, false))));
	add(new Label("absoluteUrlFor3", Model.of(WicketUrlUtils.absoluteUrlFor(WicketUrlPage.class, true))));
	add(new Label("absoluteUrlFor4", Model.of(WicketUrlUtils.absoluteUrlFor(WicketUrlPage.class, false))));

    }
}
