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
package de.alpharogroup.wicket.components.examples.area.publicly;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.wicket.Component;
import org.apache.wicket.Page;
import org.apache.wicket.markup.html.link.AbstractLink;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import de.agilecoders.wicket.core.Bootstrap;
import de.agilecoders.wicket.core.markup.html.bootstrap.button.dropdown.DropDownButton;
import de.agilecoders.wicket.core.markup.html.bootstrap.button.dropdown.MenuBookmarkablePageLink;
import de.agilecoders.wicket.core.markup.html.bootstrap.button.dropdown.MenuDivider;
import de.agilecoders.wicket.core.markup.html.bootstrap.button.dropdown.MenuHeader;
import de.agilecoders.wicket.core.markup.html.bootstrap.image.GlyphIconType;
import de.agilecoders.wicket.core.markup.html.bootstrap.navbar.Navbar;
import de.agilecoders.wicket.core.markup.html.bootstrap.navbar.NavbarButton;
import de.agilecoders.wicket.core.markup.html.bootstrap.navbar.NavbarComponents;
import de.agilecoders.wicket.core.markup.html.bootstrap.navbar.NavbarDropDownButton;
import de.agilecoders.wicket.core.settings.IBootstrapSettings;
import de.agilecoders.wicket.core.settings.ITheme;
import de.alpharogroup.wicket.base.util.resource.ResourceModelFactory;
import de.alpharogroup.wicket.components.examples.ajaxtabs.addtab.EditableAjaxTabbedPage;
import de.alpharogroup.wicket.components.examples.alerts.AlertsPage;
import de.alpharogroup.wicket.components.examples.animate.AnimationPage;
import de.alpharogroup.wicket.components.examples.basepage.ApplicationBasePage;
import de.alpharogroup.wicket.components.examples.beaneditor.example.BeanEditorExamplePage;
import de.alpharogroup.wicket.components.examples.buttons.ButtonsPage;
import de.alpharogroup.wicket.components.examples.captcha.ReCaptchaPage;
import de.alpharogroup.wicket.components.examples.captcha.SslReCaptchaPage;
import de.alpharogroup.wicket.components.examples.checkbox.CheckboxesPage;
import de.alpharogroup.wicket.components.examples.deregistration.DeregistrationPage;
import de.alpharogroup.wicket.components.examples.exceptions.ExceptionPage;
import de.alpharogroup.wicket.components.examples.fragment.replacewith.ReplaceWithPage;
import de.alpharogroup.wicket.components.examples.fragment.swapping.AddressPage;
import de.alpharogroup.wicket.components.examples.fragment.swapping.person.PersonPage;
import de.alpharogroup.wicket.components.examples.googlecharts.GoogleChartsExamplePage;
import de.alpharogroup.wicket.components.examples.home.HomePage;
import de.alpharogroup.wicket.components.examples.imprint.ImprintPage;
import de.alpharogroup.wicket.components.examples.labeled.LabeledComponentsPage;
import de.alpharogroup.wicket.components.examples.notifications.NotificationExamplesPage;
import de.alpharogroup.wicket.components.examples.pdfdownload.PdfDownloadPage;
import de.alpharogroup.wicket.components.examples.popupoverlay.PopupoverlayPage;
import de.alpharogroup.wicket.components.examples.radios.RadioComponentsExamplePage;
import de.alpharogroup.wicket.components.examples.resource.loading.ResourceLoadingExamplesPage;
import de.alpharogroup.wicket.components.examples.sign.in.SigninPage;
import de.alpharogroup.wicket.components.examples.sign.up.SignupPage;
import de.alpharogroup.wicket.components.examples.socialnet.SocialNetworksExamplePage;
import de.alpharogroup.wicket.components.examples.termofuse.TermOfUsePage;
import de.alpharogroup.wicket.components.examples.tooltips.TooltipsExamplePage;
import de.alpharogroup.wicket.components.examples.urls.WicketUrlPage;

/**
 * The Class BasePage.
 *
 * @author Asterios Raptis
 * @param <T>
 *            the generic type
 */
public abstract class PubliclyBasePage<T> extends ApplicationBasePage<T>
{


	/** The Constant logger. */
	protected static final Logger LOGGER = Logger.getLogger(PubliclyBasePage.class.getName());

	/**
	 * The serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new base page.
	 */
	public PubliclyBasePage()
	{
		this(new PageParameters());
	}

	public PubliclyBasePage(final IModel<T> model)
	{
		super(model);
		initializeComponents();
	}

	/**
	 * Instantiates a new base page.
	 *
	 * @param parameters
	 *            the parameters
	 */
	public PubliclyBasePage(final PageParameters parameters)
	{
		super(parameters);
		initializeComponents();
	}

	/**
	 * Gets the Navbar panel.
	 *
	 * @return 's the Navbar panel.
	 */
	public Component getNavbarPanel()
	{
		return newNavbar(NAVBAR_PANEL_ID);
	}

	private void initializeComponents()
	{
		add(getNavbarPanel());
		add(feedback = newFeedbackPanel("feedback"));
		add(getContainerPanel());
		add(newFooterPanel("footer"));
	}

	protected Component newFeaturesDropDownButton()
	{
		final IModel<String> featuresMainModel = ResourceModelFactory.newResourceModel(
			"global.menu.features.label", this);
		final IModel<String> swapModel = ResourceModelFactory.newResourceModel(
			"global.menu.swap.label", this);
		final IModel<String> swapPersonModel = ResourceModelFactory.newResourceModel(
			"global.menu.swap.person.label", this);
		final IModel<String> replaceWithPanelModel = ResourceModelFactory.newResourceModel(
			"global.menu.replace.with.panel.label", this);
		final IModel<String> popupoverlayPanelModel = ResourceModelFactory.newResourceModel(
			"global.menu.popupoverlay.label", this);
		final IModel<String> tabsModel = ResourceModelFactory.newResourceModel(
			"global.menu.tabs.label", this);
		final IModel<String> signInModel = ResourceModelFactory.newResourceModel(
			"global.menu.sign.in.label", this);
		final IModel<String> signUpModel = ResourceModelFactory.newResourceModel(
			"global.menu.sign.up.label", this);
		final IModel<String> downloadFileModel = ResourceModelFactory.newResourceModel(
			"global.menu.download.pdf.label", this);
		final IModel<String> recaptchaModel = ResourceModelFactory.newResourceModel(
			"global.menu.recaptcha.label", this);
		final IModel<String> sslRecaptchaModel = ResourceModelFactory.newResourceModel(
			"global.menu.ssl.recaptcha.label", this);
		final IModel<String> wicketUrlsModel = ResourceModelFactory.newResourceModel(
			"global.menu.wicket.urls.label", this);
		final IModel<String> alertsModel = ResourceModelFactory.newResourceModel(
			"global.menu.alerts.label", this);
		final IModel<String> labeledModel = ResourceModelFactory.newResourceModel(
			"global.menu.labeled.label", this);
		final IModel<String> buttonsModel = ResourceModelFactory.newResourceModel(
			"global.menu.buttons.label", this);
		final IModel<String> checkboxesModel = ResourceModelFactory.newResourceModel(
			"global.menu.checkboxes.label", this);
		final IModel<String> radiosModel = ResourceModelFactory.newResourceModel(
			"global.menu.radios.label", this);
		final IModel<String> deregistrationModel = ResourceModelFactory.newResourceModel(
			"global.menu.deregistration.label", this);
		final IModel<String> exceptionModel = ResourceModelFactory.newResourceModel(
			"global.menu.exception.label", this);
		final IModel<String> beanEditorExampleModel = ResourceModelFactory.newResourceModel(
			"global.menu.bean.editor.label", this);
		final IModel<String> animationExampleModel = ResourceModelFactory.newResourceModel(
			"global.menu.animation.label", this);
		final IModel<String> googlechartsExampleModel = ResourceModelFactory.newResourceModel(
			"global.menu.googlecharts.label", this);
		final IModel<String> toastrExampleModel = ResourceModelFactory.newResourceModel(
			"global.menu.toastr.label", this);
		final IModel<String> socialNetExampleModel = ResourceModelFactory.newResourceModel(
			"global.menu.social.network.label", this);
		final IModel<String> resourceLoadingExampleModel = ResourceModelFactory.newResourceModel(
			"global.menu.resource.loading.label", this);
		final IModel<String> tooltipsExampleModel = ResourceModelFactory.newResourceModel(
			"global.menu.tooltips.label", this);
		return new NavbarDropDownButton(featuresMainModel)
		{
			private static final long serialVersionUID = 1L;

			@Override
			protected List<AbstractLink> newSubMenuButtons(final String buttonMarkupId)
			{
				final List<AbstractLink> subMenu = new ArrayList<AbstractLink>();
				subMenu.add(new MenuBookmarkablePageLink<AddressPage>(AddressPage.class, swapModel)
					.setIconType(GlyphIconType.eyeopen));
				subMenu.add(new MenuBookmarkablePageLink<PersonPage>(PersonPage.class,
					swapPersonModel).setIconType(GlyphIconType.eyeopen));
				subMenu.add(new MenuBookmarkablePageLink<BeanEditorExamplePage>(
					BeanEditorExamplePage.class, beanEditorExampleModel)
					.setIconType(GlyphIconType.eyeopen));
				subMenu.add(new MenuBookmarkablePageLink<AnimationPage>(AnimationPage.class,
					animationExampleModel).setIconType(GlyphIconType.eyeopen));
				subMenu.add(new MenuBookmarkablePageLink<GoogleChartsExamplePage>(
					GoogleChartsExamplePage.class, googlechartsExampleModel)
					.setIconType(GlyphIconType.eyeopen));
				subMenu.add(new MenuBookmarkablePageLink<ReplaceWithPage>(ReplaceWithPage.class,
					replaceWithPanelModel).setIconType(GlyphIconType.eyeopen));
				subMenu.add(new MenuBookmarkablePageLink<PopupoverlayPage>(PopupoverlayPage.class,
					popupoverlayPanelModel).setIconType(GlyphIconType.eyeopen));
				subMenu.add(new MenuBookmarkablePageLink<EditableAjaxTabbedPage>(
					EditableAjaxTabbedPage.class, tabsModel).setIconType(GlyphIconType.picture));
				subMenu.add(new MenuBookmarkablePageLink<SigninPage>(SigninPage.class, signInModel)
					.setIconType(GlyphIconType.lock));
				subMenu.add(new MenuBookmarkablePageLink<SignupPage>(SignupPage.class, signUpModel)
					.setIconType(GlyphIconType.zoomin));
				subMenu.add(new MenuBookmarkablePageLink<PdfDownloadPage>(PdfDownloadPage.class,
					downloadFileModel).setIconType(GlyphIconType.download));
				subMenu.add(new MenuBookmarkablePageLink<ReCaptchaPage>(ReCaptchaPage.class,
					recaptchaModel).setIconType(GlyphIconType.check));
				subMenu.add(new MenuBookmarkablePageLink<SslReCaptchaPage>(SslReCaptchaPage.class,
					sslRecaptchaModel).setIconType(GlyphIconType.bullhorn));
				subMenu.add(new MenuBookmarkablePageLink<WicketUrlPage>(WicketUrlPage.class,
					wicketUrlsModel).setIconType(GlyphIconType.file));
				subMenu.add(new MenuBookmarkablePageLink<AlertsPage>(AlertsPage.class, alertsModel)
					.setIconType(GlyphIconType.bell));
				subMenu.add(new MenuBookmarkablePageLink<LabeledComponentsPage>(
					LabeledComponentsPage.class, labeledModel).setIconType(GlyphIconType.leaf));
				subMenu.add(new MenuBookmarkablePageLink<ButtonsPage>(ButtonsPage.class,
					buttonsModel).setIconType(GlyphIconType.book));
				subMenu.add(new MenuBookmarkablePageLink<CheckboxesPage>(CheckboxesPage.class,
					checkboxesModel).setIconType(GlyphIconType.book));
				subMenu.add(new MenuBookmarkablePageLink<RadioComponentsExamplePage>(
					RadioComponentsExamplePage.class, radiosModel).setIconType(GlyphIconType.book));
				subMenu.add(new MenuBookmarkablePageLink<DeregistrationPage>(
					DeregistrationPage.class, deregistrationModel).setIconType(GlyphIconType.book));
				subMenu.add(new MenuBookmarkablePageLink<NotificationExamplesPage>(
					NotificationExamplesPage.class, toastrExampleModel)
					.setIconType(GlyphIconType.barcode));
				subMenu.add(new MenuBookmarkablePageLink<SocialNetworksExamplePage>(
					SocialNetworksExamplePage.class, socialNetExampleModel)
					.setIconType(GlyphIconType.eyeopen));
				subMenu.add(new MenuBookmarkablePageLink<ResourceLoadingExamplesPage>(
					ResourceLoadingExamplesPage.class, resourceLoadingExampleModel)
					.setIconType(GlyphIconType.pencil));
				subMenu.add(new MenuBookmarkablePageLink<TooltipsExamplePage>(
					TooltipsExamplePage.class, tooltipsExampleModel)
					.setIconType(GlyphIconType.bancircle));
				subMenu.add(new MenuBookmarkablePageLink<ExceptionPage>(ExceptionPage.class,
					exceptionModel).setIconType(GlyphIconType.fire));

				return subMenu;
			}
		}.setIconType(GlyphIconType.folderopen)
		// .setInverted(true)
		;
	}

	protected Component newLegalDropDownButton()
	{
		final IModel<String> legacyMainModel = ResourceModelFactory.newResourceModel(
			"global.menu.legacy.label", this);
		final IModel<String> imprintModel = ResourceModelFactory.newResourceModel(
			"global.menu.imprint.label", this);
		final IModel<String> termOfUseModel = ResourceModelFactory.newResourceModel(
			"global.menu.termofuse.label", this);
		final IModel<String> copyrightModel = ResourceModelFactory.newResourceModel(
			"global.menu.copyright.label", this);
		return new NavbarDropDownButton(legacyMainModel)
		{
			private static final long serialVersionUID = 1L;

			@Override
			protected List<AbstractLink> newSubMenuButtons(final String buttonMarkupId)
			{
				final List<AbstractLink> subMenu = new ArrayList<AbstractLink>();
				subMenu.add(new MenuBookmarkablePageLink<ImprintPage>(ImprintPage.class,
					imprintModel).setIconType(GlyphIconType.eyeopen));
				subMenu.add(new MenuBookmarkablePageLink<TermOfUsePage>(TermOfUsePage.class,
					termOfUseModel).setIconType(GlyphIconType.picture));
				subMenu.add(new MenuBookmarkablePageLink<ImprintPage>(ImprintPage.class,
					copyrightModel).setIconType(GlyphIconType.lock));

				return subMenu;
			}
		}.setIconType(GlyphIconType.folderopen)
		// .setInverted(true)
		;
	}

	/**
	 * creates a new {@link Navbar} instance
	 *
	 * @param markupId
	 *            The components markup id.
	 * @return a new {@link Navbar} instance
	 */
	protected Navbar newNavbar(final String markupId)
	{
		final Navbar navbar = new Navbar(markupId);

		navbar.setPosition(Navbar.Position.TOP);
		final IModel<String> brandNameModel = ResourceModelFactory.newResourceModel(
			"global.slogan.mainhead.label", this);
		final IModel<String> overviewModel = ResourceModelFactory.newResourceModel(
			"global.menu.overview.label", this);
		// show brand name
		navbar.setBrandName(brandNameModel);
		navbar.addComponents(NavbarComponents.transform(Navbar.ComponentPosition.LEFT,
			new NavbarButton<HomePage>(HomePage.class, overviewModel)
				.setIconType(GlyphIconType.home), newFeaturesDropDownButton(),
			newLegalDropDownButton(), newNavbarDropDownButton()));

		return navbar;
	}

	/**
	 * creates a new {@link NavbarDropDownButton} instance
	 *
	 * @return a new {@link NavbarDropDownButton} instance
	 */
	protected DropDownButton newNavbarDropDownButton()
	{
		final DropDownButton dropdown = new NavbarDropDownButton(Model.of("Themes"))
		{
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isActive(final Component item)
			{
				return false;
			}

			@Override
			protected List<AbstractLink> newSubMenuButtons(final String buttonMarkupId)
			{
				final List<AbstractLink> subMenu = new ArrayList<AbstractLink>();
				subMenu.add(new MenuHeader(Model.of("all available themes:")));
				subMenu.add(new MenuDivider());

				final IBootstrapSettings settings = Bootstrap.getSettings(getApplication());
				final List<ITheme> themes = settings.getThemeProvider().available();

				for (final ITheme theme : themes)
				{
					final PageParameters params = new PageParameters();
					params.set("theme", theme.name());

					subMenu.add(new MenuBookmarkablePageLink<Page>(getPageClass(), params, Model
						.of(theme.name())));
				}
				return subMenu;
			}
		}.setIconType(GlyphIconType.book);
		return dropdown;
	}

}
