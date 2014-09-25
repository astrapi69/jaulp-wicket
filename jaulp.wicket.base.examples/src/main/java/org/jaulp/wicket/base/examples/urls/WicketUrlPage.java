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
import org.apache.wicket.request.Url;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.jaulp.wicket.base.examples.MenubarPanel;
import org.jaulp.wicket.base.examples.WicketApplication;
import org.jaulp.wicket.base.util.WicketComponentUtils;
import org.jaulp.wicket.base.util.WicketUrlUtils;

public class WicketUrlPage extends WebPage {
	private static final long serialVersionUID = 1L;

    public WicketUrlPage(final PageParameters parameters) {
	super(parameters);
	
	add(new MenubarPanel("menubarPanel"));
	Url url = WicketUrlUtils.getBaseUrl();
	boolean full = url.isFull();
	add(new Label("baseUrl", Model.of(WicketUrlUtils.getBaseUrl())));
	url = WicketUrlUtils.getBaseUrl(this);
	full = url.isFull();
	add(new Label("baseUrlPage", Model.of(WicketUrlUtils.getBaseUrl(this))));
	url = WicketUrlUtils.getBaseUrl(WicketUrlPage.class);
	full = url.isFull();
	add(new Label("baseUrlClass", Model.of(WicketUrlUtils.getBaseUrl(WicketUrlPage.class))));
	add(new Label("contextPath", Model.of(WicketUrlUtils.getContextPath())));
	add(new Label("contextPathPage", Model.of(WicketUrlUtils.getContextPath(this))));
	url = WicketUrlUtils.getPageUrl(WicketUrlPage.class);
	full = url.isFull();
	add(new Label("pageUrlClass", Model.of(WicketUrlUtils.getPageUrl(WicketUrlPage.class))));
	add(new Label("canonicalPageUrlClass", Model.of(WicketUrlUtils.getCanonicalPageUrl(WicketUrlPage.class))));
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
