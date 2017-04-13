package de.alpharogroup.wicket.components.link;

import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.model.IModel;
import org.apache.wicket.util.lang.Args;

/**
 * The Class {@link PhoneLink} can create a link for a phone number.
 */
public abstract class PhoneLink extends AjaxLink<CharSequence>
{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new {@link PhoneLink}.
	 *
	 * @param id
	 *            the id
	 * @param model
	 *            the model
	 */
	public PhoneLink(final String id, final IModel<CharSequence> model)
	{
		super(id, model);
		Args.notNull(model, "model");
		Args.notNull(model.getObject(), "model");
		Args.notEmpty(model.getObject(), "model");
		onValidate(model);
	}

	/**
	 * Gets the url to use for this link.
	 * 
	 * @return The URL that this link links to
	 */
	protected CharSequence getURL()
	{
		return getModelObject();
	}

	/**
	 * Callback method for opportunity to validate the given phone number.
	 *
	 * @param model
	 *            the model
	 */
	protected void onValidate(final IModel<CharSequence> model)
	{
	}

}