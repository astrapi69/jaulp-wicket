package de.alpharogroup.wicket.components.examples.basepage;

import net.sourceforge.jaulp.io.annotations.ImportResource;
import net.sourceforge.jaulp.io.annotations.ImportResources;

import org.apache.wicket.Component;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.filter.HeaderResponseContainer;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.util.string.StringValue;
import org.apache.wicket.util.time.Duration;
import org.jaulp.wicket.base.GenericBasePage;
import org.jaulp.wicket.base.util.WicketComponentUtils;
import org.jaulp.wicket.base.util.parameter.PageParametersUtils;
import org.jaulp.wicket.base.util.resource.ResourceModelFactory;
import org.jaulp.wicket.behaviors.FaviconBehavior;
import org.jaulp.wicket.behaviors.GoogleAnalyticsBehavior;

import de.agilecoders.wicket.core.Bootstrap;
import de.agilecoders.wicket.core.markup.html.bootstrap.behavior.BootstrapBaseBehavior;
import de.agilecoders.wicket.core.markup.html.bootstrap.common.NotificationPanel;
import de.agilecoders.wicket.core.settings.IBootstrapSettings;
import de.alpharogroup.wicket.components.examples.application.WicketApplication;
import de.alpharogroup.wicket.components.footer.FooterMenuPanel;
import de.alpharogroup.wicket.components.footer.FooterPanel;

/**
 * The Class ApplicationBasePage.
 *
 * @param <T> the generic type
 */
@ImportResources(resources = {
		@ImportResource(resourceName = "main.css", resourceType = "css", index=1),
		@ImportResource(resourceName = "bootstrap_alert.js", resourceType = "js", index = 2) })
public abstract class ApplicationBasePage<T> extends GenericBasePage<T> {

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
	 * Gets the feedback.
	 * 
	 * @return the feedback
	 */
	public FeedbackPanel getFeedback() {
		return feedback;
	}

	/**
	 * Instantiates a new application base page.
	 */
	public ApplicationBasePage() {
		this(new PageParameters());
	}

	/**
	 * Instantiates a new application base page.
	 *
	 * @param parameters the parameters
	 */
	public ApplicationBasePage(PageParameters parameters) {
		super(parameters);
		initializeComponents();		
	}

	/**
	 * Instantiates a new application base page.
	 *
	 * @param model the model
	 */
	public ApplicationBasePage(IModel<T> model) {
		super(model);
		initializeComponents();	
	}

	/**
	 * Initialize components.
	 */
	private void initializeComponents() {
		add(new FaviconBehavior());
		add(new GoogleAnalyticsBehavior(ApplicationBasePage.class));
		add(new BootstrapBaseBehavior());
		feedback = newFeedbackPanel("feedback");
		HeaderResponseContainer headerResponseContainer = new HeaderResponseContainer(
				WicketApplication.FOOTER_FILTER_NAME,
				WicketApplication.FOOTER_FILTER_NAME);
		add(headerResponseContainer);
	}
	
	/**
	 * New feedback panel.
	 *
	 * @param id the id
	 * @return the feedback panel
	 */
	protected FeedbackPanel newFeedbackPanel(String id) {
		NotificationPanel notificationPanel = new NotificationPanel(id);
		notificationPanel.setOutputMarkupId(true);
		notificationPanel.setOutputMarkupPlaceholderTag(true);
		notificationPanel.hideAfter(Duration.seconds(5));
		return notificationPanel;
	}

	/**
	 * {@inheritDoc}
	 */
    @Override
    protected void onConfigure() {
        super.onConfigure();
        configureTheme(getPageParameters());
    }

	/**
	 * sets the theme for the current user.
	 *
	 * @param pageParameters
	 *            current page parameters
	 */
	private void configureTheme(PageParameters pageParameters) {
		newTheme(pageParameters.get("theme"));
	}

	/**
	 * New theme.
	 *
	 * @param theme
	 *            the theme
	 */
	protected void newTheme(StringValue theme) {
		changeTheme(PageParametersUtils.getString(theme));
	}

	/**
	 * Change theme.
	 *
	 * @param themeParameter
	 *            the theme parameter
	 */
	protected void changeTheme(String themeParameter) {
		if (themeParameter != null && !themeParameter.isEmpty()) {
			IBootstrapSettings settings = Bootstrap
					.getSettings(getWicketApplication());
			settings.getActiveThemeProvider().setActiveTheme(themeParameter);
		}
	}

	/**
	 * Gets the Container panel.
	 * 
	 * @return 's the Container panel.
	 */
	public abstract Component getContainerPanel();

	/**
	 * Factory method that can be overwritten for new meta tag content for
	 * keywords.
	 * 
	 * @return the new <code>IModel<code>
	 */
	protected IModel<String> newKeywords() {
		return ResourceModelFactory.newResourceModel(
				"page.meta.keywords", 
				this, 
				"wicket, components, examples");
	}

	/**
	 * Factory method that can be overwritten for new meta tag content for the title.
	 * 
	 * @return the new <code>IModel<code>
	 */
	protected IModel<String> newTitle() {
		return ResourceModelFactory.newResourceModel("page.title", this, "jaulp.wicket.components");
	}

	/**
	 * Gets the Footer panel.
	 *
	 * @param id the id
	 * @return 's the Footer panel.
	 */
	protected Panel newFooterPanel(String id) {
		return new FooterPanel("footer") {
			private static final long serialVersionUID = 1L;
			@Override
			protected FooterMenuPanel newFooterMenuPanel(String id) {
				return new ApplicationFooterMenuPanel(id);
			}
		};
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void renderHead(IHeaderResponse response) {
    	super.renderHead(response);
		Bootstrap.renderHead(response);
    	WicketComponentUtils.renderHeaderResponse(response, ApplicationBasePage.class);
	}

	/**
	 * {@inheritDoc}
	 */
	public WicketApplication getWicketApplication() {
		return WicketApplication.get();
	}

}