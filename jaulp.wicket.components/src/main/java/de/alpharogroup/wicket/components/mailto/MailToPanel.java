package de.alpharogroup.wicket.components.mailto;

import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.StringResourceModel;
import org.jaulp.wicket.behaviors.components.MailtoLabel;
import org.jaulp.wicket.behaviors.models.MailtoModel;

/**
 * The Class MailToPanel.
 * 
 * @author Asterios Raptis
 */
public abstract class MailToPanel extends Panel
{

	/**
	 * The serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new mail to panel.
	 * 
	 * @param id
	 *            the id
	 */
	public MailToPanel(final String id)
	{
		super(id);
		final Object[] params = { getDomainName() };
		add(new MailtoLabel("mailtoLabel", newMailtoModel(params)));
	}

	/**
	 * Hook method for implement the specific domain name.
	 * 
	 * @return the domain name
	 */
	protected abstract String getDomainName();

	/**
	 * Factory method to create a MailtoModel. This method is invoked in the constructor from this
	 * class and can be overridden so users can provide their own version of a MailtoModel.
	 * 
	 * @param params
	 *            the params
	 * @return the mailto model
	 */
	protected MailtoModel newMailtoModel(final Object[] params)
	{
		final MailtoModel model = new MailtoModel(newMailToAddressModel(params),
			newMailToViewModel(params));
		return model;
	}

	/**
	 * Factory method to create a IModel<String> for the MailtoViewModel. This method is invoked in
	 * the constructor from this class and can be overridden so users can provide their own version
	 * of a MailtoViewModel.
	 * 
	 * @param params
	 *            the params
	 * @return the mail to view model
	 */
	protected IModel<String> newMailToViewModel(final Object[] params)
	{
		return new StringResourceModel("global.compamy.mailto.view", this, null, params);
	}

	/**
	 * Factory method to create a IModel<String> for the MailtoAddressModel. This method is invoked
	 * in the constructor from this class and can be overridden so users can provide their own
	 * version of a MailtoAddressModel.
	 * 
	 * @param params
	 *            the params
	 * @return the mail to address model
	 */
	protected IModel<String> newMailToAddressModel(final Object[] params)
	{
		return new StringResourceModel("global.mailto.address", this, null, params);
	}

}