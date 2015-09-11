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
package de.alpharogroup.wicket.components.examples.basepage;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.Component;
import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.markup.head.filter.HeaderResponseContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.AbstractLink;
import org.apache.wicket.markup.html.link.ExternalLink;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.util.ListModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.settings.IJavaScriptLibrarySettings;
import org.apache.wicket.util.string.StringValue;
import org.apache.wicket.util.time.Duration;
import org.odlabs.wiquery.core.javascript.JsUtils;

import de.agilecoders.wicket.core.Bootstrap;
import de.agilecoders.wicket.core.markup.html.bootstrap.behavior.BootstrapBaseBehavior;
import de.agilecoders.wicket.core.markup.html.bootstrap.common.NotificationPanel;
import de.agilecoders.wicket.core.settings.IBootstrapSettings;
import de.alpharogroup.io.annotations.ImportResource;
import de.alpharogroup.io.annotations.ImportResources;
import de.alpharogroup.locale.ResourceBundleKey;
import de.alpharogroup.wicket.base.GenericBasePage;
import de.alpharogroup.wicket.base.util.parameter.PageParametersExtensions;
import de.alpharogroup.wicket.base.util.resource.ResourceModelFactory;
import de.alpharogroup.wicket.behaviors.BuildableChainableStatement;
import de.alpharogroup.wicket.behaviors.FaviconBehavior;
import de.alpharogroup.wicket.behaviors.JavascriptAppenderBehavior;
import de.alpharogroup.wicket.behaviors.JqueryStatementsBehavior;
import de.alpharogroup.wicket.bootstrap3.application.WicketBootstrap3Application;
import de.alpharogroup.wicket.components.examples.application.WicketApplication;
import de.alpharogroup.wicket.components.examples.application.WicketSession;
import de.alpharogroup.wicket.components.examples.imprint.ImprintPage;
import de.alpharogroup.wicket.components.examples.termofuse.TermOfUsePage;
import de.alpharogroup.wicket.components.footer.FooterMenuPanel;
import de.alpharogroup.wicket.components.footer.FooterPanel;
import de.alpharogroup.wicket.components.i18n.list.LinkListPanel;
import de.alpharogroup.wicket.components.link.DefaultTargets;
import de.alpharogroup.wicket.components.link.LinkItem;
import de.alpharogroup.wicket.header.contributors.HeaderResponseExtensions;
import de.alpharogroup.wicket.js.addon.sessiontimeout.BootstrapSessionTimeoutResourceReference;
import de.alpharogroup.wicket.js.addon.sessiontimeout.SessionTimeoutJsGenerator;
import de.alpharogroup.wicket.js.addon.sessiontimeout.SessionTimeoutSettings;

/**
 * The Class ApplicationBasePage.
 *
 * @param <T>
 *            the generic type
 */
@ImportResources(resources = {
		@ImportResource(resourceName = "main.css", resourceType = "css", index = 1),
		@ImportResource(resourceName = "bootstrap_alert.js", resourceType = "js", index = 2) })
public abstract class ApplicationBasePage<T> extends GenericBasePage<T>
{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The Constant COPYRIGHT_URL. */
	protected static final String COPYRIGHT_URL = "http://www.alpharogroup.de/";

	/** The Constant NAVBAR_PANEL_ID. */
	protected static final String NAVBAR_PANEL_ID = "navbar";

	/** The Constant CONTAINER_PANEL_ID. */
	protected static final String CONTAINER_PANEL_ID = "container";

	/** The Constant FOOTER_PANEL_ID. */
	protected static final String FOOTER_PANEL_ID = "footer";

	/** The feedback. */
	protected FeedbackPanel feedback;

	/**
	 * Instantiates a new application base page.
	 */
	public ApplicationBasePage()
	{
		this(new PageParameters());
	}

	/**
	 * Instantiates a new application base page.
	 *
	 * @param model
	 *            the model
	 */
	public ApplicationBasePage(final IModel<T> model)
	{
		super(model);
	}

	/**
	 * Instantiates a new application base page.
	 *
	 * @param parameters
	 *            the parameters
	 */
	public ApplicationBasePage(final PageParameters parameters)
	{
		super(parameters);
	}

	/**
	 * Change theme.
	 *
	 * @param themeParameter
	 *            the theme parameter
	 */
	protected void changeTheme(final String themeParameter)
	{
		if ((themeParameter != null) && !themeParameter.isEmpty())
		{
			final IBootstrapSettings settings = Bootstrap.getSettings(getWicketApplication());
			settings.getActiveThemeProvider().setActiveTheme(themeParameter);
		}
	}

	/**
	 * sets the theme for the current user.
	 *
	 * @param pageParameters
	 *            current page parameters
	 */
	private void configureTheme(final PageParameters pageParameters)
	{
		newTheme(pageParameters.get("theme"));
	}

	/**
	 * Gets the Container panel.
	 *
	 * @return 's the Container panel.
	 */
	public abstract Component getContainerPanel();

	/**
	 * Gets the feedback.
	 *
	 * @return the feedback
	 */
	public FeedbackPanel getFeedback()
	{
		return feedback;
	}

	/**
	 * Gets the wicket application.
	 *
	 * @return the wicket application
	 */
	public WicketApplication getWicketApplication()
	{
		return WicketApplication.get();
	}

	/**
	 * Initialize components.
	 */
	private void initializeComponents()
	{
		add(new FaviconBehavior());
		add(new BootstrapBaseBehavior());
		final HeaderResponseContainer headerResponseContainer = new HeaderResponseContainer(
			WicketBootstrap3Application.FOOTER_FILTER_NAME,
			WicketBootstrap3Application.FOOTER_FILTER_NAME);
		add(headerResponseContainer);

		final int sessionTimeout = WicketSession.get().getSessionTimeout();
		if (0 < sessionTimeout)
		{
			final int oneThirdOfWarnAfter = (sessionTimeout * 1000) / 3;
			final int twoThirdOfWarnAfter = oneThirdOfWarnAfter * 2;
			final SessionTimeoutSettings settings = SessionTimeoutSettings.builder().build();
			settings.getTitle().setValue("Session timeout warning");
			settings.getMessage().setValue("Your session will be timeouted...");
			settings.getWarnAfter().setValue(oneThirdOfWarnAfter);
			settings.getRedirAfter().setValue(twoThirdOfWarnAfter);
			settings.getRedirUrl().setValue("/public/imprint");
			settings.getLogoutUrl().setValue("/public/imprint");

			final SessionTimeoutJsGenerator generator = new SessionTimeoutJsGenerator(settings);
			final String jsCode = generator.generateJs();
			add(JavascriptAppenderBehavior.builder().id("sessionTimeoutNotification")
				.javascript(jsCode).build());
		}
	}

	/**
	 * New feedback panel.
	 *
	 * @param id
	 *            the id
	 * @return the feedback panel
	 */
	protected FeedbackPanel newFeedbackPanel(final String id)
	{
		final NotificationPanel notificationPanel = new NotificationPanel(id);
		notificationPanel.setOutputMarkupId(true);
		notificationPanel.setOutputMarkupPlaceholderTag(true);
		notificationPanel.hideAfter(Duration.seconds(5));
		return notificationPanel;
	}

	/**
	 * Gets the Footer panel.
	 *
	 * @param id
	 *            the id
	 * @return 's the Footer panel.
	 */
	protected Panel newFooterPanel(final String id)
	{
		final List<LinkItem> linkModel = new ArrayList<LinkItem>();
		linkModel.add(LinkItem
			.builder()
			.url("http://www.alpharogroup.de/")
			.target(DefaultTargets.BLANK.getTarget())
			.linkClass(ExternalLink.class)
			// open in a new tab or window...
			.resourceModelKey(
				ResourceBundleKey.builder().key("main.footer.copyright.label")
					.defaultValue("\u0040 copyright 2012 Design by Alpha Ro Group").build())
			.build());
		linkModel.add(LinkItem
			.builder()
			.pageClass(ImprintPage.class)
			.resourceModelKey(
				ResourceBundleKey.builder().key("main.global.menu.masthead.label")
					.defaultValue("Imprint").build()).build());
		linkModel.add(LinkItem
			.builder()
			.pageClass(TermOfUsePage.class)
			.resourceModelKey(
				ResourceBundleKey.builder().key("main.global.menu.term.of.use.label")
					.defaultValue("AGBs").build()).build());
		final IModel<List<LinkItem>> listModel = new ListModel<>(linkModel);
		return new FooterPanel<List<LinkItem>>(id, listModel)
		{
			private static final long serialVersionUID = 1L;

			@Override
			protected Component newFooterMenuPanel(final String id,
				final IModel<List<LinkItem>> model)
			{

				final FooterMenuPanel footerMenu = new FooterMenuPanel(id, model)
				{
					private static final long serialVersionUID = 1L;

					@Override
					protected Component newLinkListPanel(final String id,
						final IModel<List<LinkItem>> model)
					{
						final LinkListPanel listPanel = new LinkListPanel(id, model)
						{
							private static final long serialVersionUID = 1L;

							@Override
							protected String getCurrentPageCssClass()
							{
								return "active";
							}

							@Override
							protected Component newListComponent(final String id,
								final ListItem<LinkItem> item)
							{
								final LinkItem model = item.getModelObject();
								final Label itemLinkLabel = super.newItemLinkLabel("itemLinkLabel",
									model);
								itemLinkLabel.add(new AttributeAppender("class", " a"));
								final AbstractLink link = super.newAbstractLink(id, model);
								link.add(new AttributeAppender("class", " btn btn-default"));
								link.add(itemLinkLabel);
								return link;
							}
						};
						listPanel.add(new AttributeAppender("class", " btn"));
						return listPanel;
					}
				};
				// Add bootstrap class to ul element...
				add(new JqueryStatementsBehavior().add(
					new BuildableChainableStatement.Builder().label("find")
						.args(JsUtils.quotes("ul")).build()).add(
					new BuildableChainableStatement.Builder().label("addClass")
						.args(JsUtils.quotes("nav navbar-nav list-inline a")).build()));
				return footerMenu;
			}
		};
	}

	/**
	 * Factory method that can be overwritten for new meta tag content for keywords.
	 *
	 * @return the new <code>IModel</code>
	 */
	@Override
	protected IModel<String> newKeywords()
	{
		return ResourceModelFactory.newResourceModel("page.meta.keywords", this,
			"wicket, components, examples");
	}

	/**
	 * New theme.
	 *
	 * @param theme
	 *            the theme
	 */
	protected void newTheme(final StringValue theme)
	{
		changeTheme(PageParametersExtensions.getString(theme));
	}

	/**
	 * Factory method that can be overwritten for new meta tag content for the title.
	 *
	 * @return the new <code>IModel</code>
	 */
	@Override
	protected IModel<String> newTitle()
	{
		return ResourceModelFactory.newResourceModel("page.title", this, "jaulp.wicket.components");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void onConfigure()
	{
		super.onConfigure();
		configureTheme(getPageParameters());
	}

	@Override
	protected void onInitialize()
	{
		super.onInitialize();
		initializeComponents();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void renderHead(final IHeaderResponse response)
	{
		super.renderHead(response);
		final IJavaScriptLibrarySettings javaScriptSettings = getApplication()
			.getJavaScriptLibrarySettings();
		response.render(JavaScriptHeaderItem.forReference(javaScriptSettings.getJQueryReference()));
		Bootstrap.renderHead(response);
		HeaderResponseExtensions.renderHeaderResponse(response, ApplicationBasePage.class);
		// PackageResourceReference gaqResourceReference = new PackageResourceReference(
		// ApplicationBasePage.class, "gaq.js");
		//
		// response.render(JavaScriptHeaderItem.forReference(gaqResourceReference));
		response.render(JavaScriptHeaderItem.forReference(BootstrapSessionTimeoutResourceReference
			.get()));
	}

}
