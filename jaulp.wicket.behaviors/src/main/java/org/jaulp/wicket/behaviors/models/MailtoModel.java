package org.jaulp.wicket.behaviors.models;

import org.apache.wicket.model.IModel;
import org.apache.wicket.model.StringResourceModel;
import org.apache.wicket.util.io.IClusterable;

/**
 * The Class MailtoModel.
 * 
 * @author Asterios Raptis
 */
public class MailtoModel implements IClusterable {

	/**
	 * The serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;

	/** The mailto addres model. */
	private IModel<String> mailtoAddresModel;

	/** The mailto view model. */
	private IModel<String> mailtoViewModel;

	/**
	 * Instantiates a new mailto model.
	 */
	public MailtoModel() {
		super();
	}

	/**
	 * Instantiates a new mailto model.
	 * 
	 * @param mailtoAddresModel
	 *            the mailto addres model
	 * @param mailtoViewModel
	 *            the mailto view model
	 */
	public MailtoModel(final IModel<String> mailtoAddresModel,
			final IModel<String> mailtoViewModel) {
		super();
		this.mailtoAddresModel = mailtoAddresModel;
		this.mailtoViewModel = mailtoViewModel;
	}

	/**
	 * Gets the mailto addres model.
	 * 
	 * @return the mailto addres model
	 */
	public IModel<String> getMailtoAddresModel() {
		return mailtoAddresModel;
	}

	/**
	 * Gets the mailto view model.
	 * 
	 * @return the mailto view model
	 */
	public IModel<String> getMailtoViewModel() {
		return mailtoViewModel;
	}

	/**
	 * Sets the mailto addres model.
	 * 
	 * @param mailtoAddresModel
	 *            the new mailto addres model
	 */
	public void setMailtoAddresModel(final StringResourceModel mailtoAddresModel) {
		this.mailtoAddresModel = mailtoAddresModel;
	}

	/**
	 * Sets the mailto view model.
	 * 
	 * @param mailtoViewModel
	 *            the new mailto view model
	 */
	public void setMailtoViewModel(final StringResourceModel mailtoViewModel) {
		this.mailtoViewModel = mailtoViewModel;
	}

}
