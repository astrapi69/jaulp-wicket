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
package de.alpharogroup.wicket.components.examples.urls;

import java.util.HashMap;
import java.util.Map;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import de.alpharogroup.wicket.base.BasePanel;
import de.alpharogroup.wicket.base.util.parameter.PageParametersExtensions;
import de.alpharogroup.wicket.base.util.url.WicketUrlExtensions;
import de.alpharogroup.wicket.components.examples.fragment.swapping.AddressPage;


public class WicketUrlPanel extends BasePanel<Object>
{
	private static final long serialVersionUID = 1L;

	public WicketUrlPanel(final String id)
	{
		super(id);
		final Map<String, String> parameters = new HashMap<>();
		parameters.put("foo", "123");
		parameters.put("bar", "abc");
		final PageParameters pageParameters = PageParametersExtensions.toPageParameters(parameters);
		final AddressPage addressPage = new AddressPage(pageParameters);

		add(new Label("absoluteUrlFor", Model.of(WicketUrlExtensions
			.absoluteUrlFor(AddressPage.class))));

		add(new Label("absoluteUrlForWithPort", Model.of(WicketUrlExtensions.absoluteUrlFor(
			AddressPage.class, true))));

		add(new Label("absoluteUrlForWithPortAndPageParameters", Model.of(WicketUrlExtensions
			.absoluteUrlFor(AddressPage.class, pageParameters, true))));

		add(new Label("absoluteUrlForWithoutPortAndPageParameters", Model.of(WicketUrlExtensions
			.absoluteUrlFor(AddressPage.class, pageParameters, false))));

		add(new Label("getPageUrl", Model.of(WicketUrlExtensions.getPageUrl(new AddressPage(
			new PageParameters())))));

		add(new Label("getPageUrlWithPageParameters", Model.of(WicketUrlExtensions.getPageUrl(
			addressPage, pageParameters))));

		add(new Label("getPageUrlWithPageClass", Model.of(WicketUrlExtensions
			.getPageUrl(AddressPage.class))));

		add(new Label("getPageUrlWithPageClassAndPageParameters", Model.of(WicketUrlExtensions
			.getPageUrl(AddressPage.class, pageParameters))));

		add(new Label("getCanonicalPageUrlWithPageClass", Model.of(WicketUrlExtensions
			.getCanonicalPageUrl(AddressPage.class))));

		add(new Label("getCanonicalPageUrlWithPageClassAndPageParameters",
			Model.of(WicketUrlExtensions.getCanonicalPageUrl(AddressPage.class, pageParameters))));

		add(new Label("getBaseUrl", Model.of(WicketUrlExtensions.getBaseUrl())));

		add(new Label("getBaseUrlWithPageObject", Model.of(WicketUrlExtensions
			.getBaseUrl(addressPage))));

		add(new Label("getBaseUrlWithPageClass", Model.of(WicketUrlExtensions
			.getBaseUrl(AddressPage.class))));

		add(new Label("getBaseUrlWithPageClassAndPageParameters", Model.of(WicketUrlExtensions
			.getBaseUrl(AddressPage.class, pageParameters))));

		add(new Label("getContextPath", Model.of(WicketUrlExtensions.getContextPath())));

		add(new Label("getContextPathWithPageObject", Model.of(WicketUrlExtensions
			.getContextPath(addressPage))));

		add(new Label("getUrlAsStringWithPageObject", Model.of(WicketUrlExtensions
			.getUrlAsString(addressPage))));

		add(new Label("getUrlAsStringWithPageClass", Model.of(WicketUrlExtensions
			.getUrlAsString(AddressPage.class))));

		add(new Label("getLocalName", Model.of(WicketUrlExtensions.getLocalName())));

		add(new Label("getServerName", Model.of(WicketUrlExtensions.getServerName())));

		add(new Label("getDomainUrl", Model.of(WicketUrlExtensions.getDomainUrl())));

		add(new Label("getDomainUrlWithoutPort", Model.of(WicketUrlExtensions.getDomainUrl(false))));

		add(new Label("getDomainUrlWithoutPortAndSlash", Model.of(WicketUrlExtensions.getDomainUrl(
			false, false))));

		add(new Label("getDomainUrlWithSslAndPortAndSlash", Model.of(WicketUrlExtensions
			.getDomainUrl(true, true, true))));

		add(new Label("getDomainUrlWithSslWithoutPortAndWithSlash", Model.of(WicketUrlExtensions
			.getDomainUrl(true, false, true))));

	}
}
