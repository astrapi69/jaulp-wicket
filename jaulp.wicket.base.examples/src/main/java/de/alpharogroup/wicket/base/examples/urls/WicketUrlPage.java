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
package de.alpharogroup.wicket.base.examples.urls;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.wicketstuff.annotation.mount.MountPath;

import de.alpharogroup.wicket.base.examples.MenubarPanel;
import de.alpharogroup.wicket.base.examples.application.WicketApplication;
import de.alpharogroup.wicket.base.util.WicketComponentExtensions;
import de.alpharogroup.wicket.base.util.application.ApplicationExtensions;
import de.alpharogroup.wicket.base.util.url.WicketUrlExtensions;

@MountPath("/urlpageexample")
public class WicketUrlPage extends WebPage
{
	private static final long serialVersionUID = 1L;

	public WicketUrlPage(final PageParameters parameters)
	{
		super(parameters);

		add(new MenubarPanel("menubarPanel"));
		add(new Label("baseUrl", Model.of(WicketUrlExtensions.getBaseUrl())));
		add(new Label("baseUrlPage", Model.of(WicketUrlExtensions.getBaseUrl(this))));
		add(new Label("baseUrlClass", Model.of(WicketUrlExtensions.getBaseUrl(WicketUrlPage.class))));
		add(new Label("contextPath", Model.of(WicketUrlExtensions.getContextPath())));
		add(new Label("contextPathPage", Model.of(WicketUrlExtensions.getContextPath(this))));
		add(new Label("pageUrlClass", Model.of(WicketUrlExtensions.getPageUrl(WicketUrlPage.class))));
		add(new Label("canonicalPageUrlClass", Model.of(WicketUrlExtensions
			.getCanonicalPageUrl(WicketUrlPage.class))));
		add(new Label("urlClass", Model.of(WicketUrlExtensions.getPageUrl(this))));
		add(new Label("urlAsStringClass", Model.of(WicketUrlExtensions
			.getUrlAsString(WicketUrlPage.class))));
		add(new Label("urlAsStringPage", Model.of(WicketUrlExtensions.getUrlAsString(this))));
		add(new Label("contextPathApplication", Model.of(ApplicationExtensions
			.getContextPath(WicketApplication.get()))));
		add(new Label("requestURL", Model.of(WicketComponentExtensions.getRequestURL())));
		add(new Label("remoteHost", Model.of(WicketComponentExtensions.getIpAddress())));
		add(new Label("remoteAddr", Model.of(WicketComponentExtensions.getRemoteAddr())));
		add(new Label("localName", Model.of(WicketUrlExtensions.getLocalName())));
		add(new Label("serverName", Model.of(WicketUrlExtensions.getServerName())));
		add(new Label("domainUrl", Model.of(WicketUrlExtensions.getDomainUrl())));

		add(new Label("domainUrl2", Model.of(WicketUrlExtensions.getDomainUrl(false))));
		add(new Label("domainUrl3", Model.of(WicketUrlExtensions.getDomainUrl(false, false))));


		final PageParameters params = new PageParameters();
		params.add("USERNAME", "bla");
		params.add("CONFIRMATION_CODE", "1HE23WE");
		add(new Label("absoluteUrlFor", Model.of(WicketUrlExtensions.absoluteUrlFor(
			WicketUrlPage.class, params, true))));
		add(new Label("absoluteUrlFor2", Model.of(WicketUrlExtensions.absoluteUrlFor(
			WicketUrlPage.class, params, false))));
		add(new Label("absoluteUrlFor3", Model.of(WicketUrlExtensions.absoluteUrlFor(
			WicketUrlPage.class, true))));
		add(new Label("absoluteUrlFor4", Model.of(WicketUrlExtensions.absoluteUrlFor(
			WicketUrlPage.class, false))));

	}
}
