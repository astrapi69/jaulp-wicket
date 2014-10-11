package de.alpharogroup.wicket.components.examples.home;

import java.io.IOException;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.MarkupContainer;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.markup.head.filter.HeaderResponseContainer;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.util.resource.IResourceStream;
import org.jaulp.wicket.base.util.WicketComponentUtils;
import org.jaulp.wicket.behaviors.AjaxDownloadBehavior;
import org.odlabs.wiquery.core.util.WiQueryUtil;

import de.alpharogroup.wicket.components.examples.ajaxtabs.addtab.EditableAjaxTabbedPage;
import de.alpharogroup.wicket.components.examples.application.WicketApplication;
import de.alpharogroup.wicket.components.examples.captcha.KaptchaPage;
import de.alpharogroup.wicket.components.examples.fragment.swapping.AddressPage;
import de.alpharogroup.wicket.components.examples.imprint.ImprintPage;
import de.alpharogroup.wicket.components.examples.resource.references.AlertJsReference;
import de.alpharogroup.wicket.components.examples.sign.in.SigninPage;
import de.alpharogroup.wicket.components.examples.sign.up.SignupPage;
import de.alpharogroup.wicket.components.examples.termofuse.TermOfUsePage;
import de.alpharogroup.wicket.components.examples.urls.WicketUrlPage;
import de.alpharogroup.wicket.components.indicator.AjaxIndicatorLoadingPanel;


public class HomePage extends WebPage {
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("serial")
	public HomePage(final PageParameters parameters) {
		super(parameters);
		AjaxIndicatorLoadingPanel indicator = new AjaxIndicatorLoadingPanel("indicator");
		add(indicator);
		MarkupContainer mc = new WebMarkupContainer("alert-area");
		add(mc);
    	Button button = new Button("button");
    	button.add(new AttributeModifier("onclick", "newAlert('success', 'Oh yeah!', 2000);"));
    	add(button);
    	final String filename = "download.pdf";    	
    	final AjaxDownloadBehavior download = new AjaxDownloadBehavior() {
			private static final long serialVersionUID = 1L;
			
			@Override
			protected IResourceStream getResourceStream() {
				try {
					return WicketComponentUtils.getResourceStream(WicketApplication.get(), "pdf/"+filename, "application/pdf");
				} catch (IOException e) {
					e.printStackTrace();
					getSession().info("Fehler: "+e.getLocalizedMessage());
				} 
				return null;
			}
			
			@Override
			protected String getFileName() {
				return filename;
			}
		};
		
		Label fileNameLabel = new Label("fileName", filename);
		AjaxLink<Void> pdfLink = new AjaxLink<Void>("pdfLink") {
			private static final long serialVersionUID = 1L;
			@Override
			public void onClick(AjaxRequestTarget target) {
				download.initiate(target);
			}
		};
		pdfLink.add(download);
		pdfLink.add(fileNameLabel);
		addOrReplace(pdfLink);
		addOrReplace(new Link<Void>("termOfUseLink"){
			@Override
			public void onClick() {
			setResponsePage(TermOfUsePage.class);
			}
		});
		addOrReplace(new Link<Void>("imprintLink"){
			@Override
			public void onClick() {
			setResponsePage(ImprintPage.class);
			}
		});
		addOrReplace(new Link<Void>("signupLink"){
			@Override
			public void onClick() {
			setResponsePage(SignupPage.class);
			}
		});
		addOrReplace(new Link<Void>("signinLink"){
			@Override
			public void onClick() {
			setResponsePage(SigninPage.class);
			}
		});
		addOrReplace(new Link<Void>("kaptchaLink"){
			@Override
			public void onClick() {
			setResponsePage(KaptchaPage.class);
			}
		});
		addOrReplace(new Link<Void>("urlsLink"){
			@Override
			public void onClick() {
			setResponsePage(WicketUrlPage.class);
			}
		});
		addOrReplace(new Link<Void>("tabsLink"){
			@Override
			public void onClick() {
			setResponsePage(EditableAjaxTabbedPage.class);
			}
		});
		addOrReplace(new Link<Void>("swapFragmentsLink"){
			@Override
			public void onClick() {
			setResponsePage(AddressPage.class);
			}
		});

		HeaderResponseContainer headerResponseContainer = new HeaderResponseContainer(
				WicketApplication.FOOTER_FILTER_NAME,
				WicketApplication.FOOTER_FILTER_NAME);
		add(headerResponseContainer);

	}

	@Override
	public void renderHead(IHeaderResponse response) {
		response.render(JavaScriptHeaderItem
				.forReference(WiQueryUtil.getJQueryResourceReference()));
		response.render(JavaScriptHeaderItem
				.forReference(AlertJsReference.INSTANCE));
	}
}
