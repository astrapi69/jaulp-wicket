package org.jaulp.wicket.behaviors.components;


import org.apache.wicket.markup.html.basic.Label;
import org.jaulp.wicket.behaviors.MailtoBehavior;
import org.jaulp.wicket.behaviors.models.MailtoModel;

/**
 * The Class MailtoLabel.
 *
 * @author Asterios Raptis
 */
public class MailtoLabel extends Label {

	/**
	 * The serialVersionUID.
	 */
    private static final long serialVersionUID = -3924484332764709856L;

    /**
	 * Instantiates a new mailto label.
	 *
	 * @param id
	 *            the id
	 * @param mailtoModel
	 *            the mailto model
	 */
	public MailtoLabel(final String id, final MailtoModel mailtoModel) {
		super(id);
		final MailtoBehavior<MailtoModel> mailtoBehavior = new MailtoBehavior<MailtoModel>(mailtoModel);
		this.setDefaultModel(mailtoModel.getMailtoViewModel());
		this.add(mailtoBehavior);
	}

}
