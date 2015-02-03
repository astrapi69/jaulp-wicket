package de.alpharogroup.wicket.components.examples.urls;

import java.util.HashMap;
import java.util.Map;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.jaulp.wicket.base.BasePanel;
import org.jaulp.wicket.base.util.parameter.PageParametersUtils;
import org.jaulp.wicket.base.util.url.WicketUrlUtils;

import de.alpharogroup.wicket.components.examples.fragment.swapping.AddressPage;


public class WicketUrlPanel extends BasePanel<Object> {
	private static final long serialVersionUID = 1L;

	public WicketUrlPanel(String id) {
		super(id);
		Map<String, String> parameters = new HashMap<>();
		parameters.put("foo", "123");
		parameters.put("bar", "abc");
		PageParameters pageParameters = PageParametersUtils.toPageParameters(parameters);
		AddressPage addressPage = new AddressPage(pageParameters);

		add(new Label("absoluteUrlFor", Model.of(WicketUrlUtils.absoluteUrlFor(AddressPage.class))));
		
		add(new Label("absoluteUrlForWithPort", Model.of(WicketUrlUtils.absoluteUrlFor(AddressPage.class, true))));
		
		add(new Label("absoluteUrlForWithPortAndPageParameters", Model.of(WicketUrlUtils.absoluteUrlFor(AddressPage.class, pageParameters, true))));
		
		add(new Label("absoluteUrlForWithoutPortAndPageParameters", Model.of(WicketUrlUtils.absoluteUrlFor(AddressPage.class, pageParameters, false))));
		
		add(new Label("getPageUrl", Model.of(WicketUrlUtils.getPageUrl(new AddressPage(new PageParameters())))));
		
		add(new Label("getPageUrlWithPageParameters", Model.of(WicketUrlUtils.getPageUrl(addressPage, pageParameters))));
		
		add(new Label("getPageUrlWithPageClass", Model.of(WicketUrlUtils.getPageUrl(AddressPage.class))));
		
		add(new Label("getPageUrlWithPageClassAndPageParameters", Model.of(WicketUrlUtils.getPageUrl(AddressPage.class, pageParameters))));
		
		add(new Label("getCanonicalPageUrlWithPageClass", Model.of(WicketUrlUtils.getCanonicalPageUrl(AddressPage.class))));
		
		add(new Label("getCanonicalPageUrlWithPageClassAndPageParameters", Model.of(WicketUrlUtils.getCanonicalPageUrl(AddressPage.class, pageParameters))));
		
		add(new Label("getBaseUrl", Model.of(WicketUrlUtils.getBaseUrl())));
		
		add(new Label("getBaseUrlWithPageObject", Model.of(WicketUrlUtils.getBaseUrl(addressPage))));
		
		add(new Label("getBaseUrlWithPageClass", Model.of(WicketUrlUtils.getBaseUrl(AddressPage.class))));
		
		add(new Label("getBaseUrlWithPageClassAndPageParameters", Model.of(WicketUrlUtils.getBaseUrl(AddressPage.class, pageParameters))));
		
		add(new Label("getContextPath", Model.of(WicketUrlUtils.getContextPath())));
		
		add(new Label("getContextPathWithPageObject", Model.of(WicketUrlUtils.getContextPath(addressPage))));

		add(new Label("getUrlAsStringWithPageObject", Model.of(WicketUrlUtils.getUrlAsString(addressPage))));

		add(new Label("getUrlAsStringWithPageClass", Model.of(WicketUrlUtils.getUrlAsString(AddressPage.class))));
		
		add(new Label("getLocalName", Model.of(WicketUrlUtils.getLocalName())));
		
		add(new Label("getServerName", Model.of(WicketUrlUtils.getServerName())));
		
		add(new Label("getDomainUrl", Model.of(WicketUrlUtils.getDomainUrl())));
		
		add(new Label("getDomainUrlWithoutPort", Model.of(WicketUrlUtils.getDomainUrl(false))));
		
		add(new Label("getDomainUrlWithoutPortAndSlash", Model.of(WicketUrlUtils.getDomainUrl(false, false))));
		
		add(new Label("getDomainUrlWithSslAndPortAndSlash", Model.of(WicketUrlUtils.getDomainUrl(true, true, true))));
		
		add(new Label("getDomainUrlWithSslWithoutPortAndWithSlash", Model.of(WicketUrlUtils.getDomainUrl(true, false, true))));
		
	}
}
