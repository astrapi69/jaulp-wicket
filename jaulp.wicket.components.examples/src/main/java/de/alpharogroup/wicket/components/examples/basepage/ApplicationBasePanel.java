package de.alpharogroup.wicket.components.examples.basepage;

import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.IModel;

import de.alpharogroup.wicket.base.BasePanel;
import de.alpharogroup.wicket.base.util.WicketComponentExtensions;
import de.alpharogroup.wicket.components.examples.application.WicketApplication;
import de.alpharogroup.wicket.components.examples.application.WicketSession;
import de.alpharogroup.wicket.header.contributors.HeaderResponseExtensions;

/**
 * The Class ApplicationBasePanel.
 * 
 * @author Asterios Raptis
 */
public abstract class ApplicationBasePanel<T> extends BasePanel<T>
{

	/**
	 * The serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;


	/**
	 * Instantiates a new {@link ApplicationBasePanel}.
	 *
	 * @param id
	 *            the component id
	 * @param model
	 *            the model
	 */
	public ApplicationBasePanel(final String id, final IModel<T> model)
	{
		super(id, model);
	}

	/**
	 * Gets the page holding this component. Wraps the getPage() method.
	 * 
	 * @return the base page
	 */
	public ApplicationBasePage<?> getApplicationBasePage()
	{
		return (ApplicationBasePage<?>)getPage();

	}

	/**
	 * Gets the feedback from the page.
	 * 
	 * @return the feedback
	 */
	public FeedbackPanel getFeedback()
	{
		final ApplicationBasePage<?> parentPage = getApplicationBasePage();
		if (parentPage != null)
		{
			return parentPage.getFeedback();
		}
		return null;
	}

	/**
	 * Gets the request url.
	 * 
	 * @return the request url
	 */
	public String getRequestURL()
	{
		return WicketComponentExtensions.getRequestURL();
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
	 * {@inheritDoc}
	 */
	public WicketSession getWicketSession()
	{
		return WicketSession.get();
	}


	/**
	 * {@inheritDoc}
	 */
	@Override
	public void renderHead(final IHeaderResponse response)
	{
		super.renderHead(response);
		HeaderResponseExtensions.renderHeaderResponse(response, this.getClass());
	}


}