package de.alpharogroup.wicket.base.mainbase;

import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.model.IModel;

import de.alpharogroup.wicket.base.BasePanel;
import de.alpharogroup.wicket.base.examples.application.WicketApplication;
import de.alpharogroup.wicket.base.util.WicketComponentExtensions;
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
	 * Instantiates a new base panel.
	 * 
	 * @param id
	 *            the id
	 * @param model
	 *            the model
	 */
	public ApplicationBasePanel(final String id, final IModel<T> model)
	{
		super(id, model);
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
	@Override
	public void renderHead(final IHeaderResponse response)
	{
		super.renderHead(response);
		HeaderResponseExtensions.renderHeaderResponse(response, this.getClass());
	}


}