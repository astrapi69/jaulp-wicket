package de.alpharogroup.wicket.components.sign.in.password.reset;

import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.jaulp.wicket.base.pageparameters.ParameterKeys;


/**
 * The class ResetPasswordPanel.
 * 
 * @author Asterios Raptis
 */
public abstract class AbstractResetPasswordPanel extends Panel
{

	/**
	 * The serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;

	public AbstractResetPasswordPanel(final String id, final PageParameters parameters)
	{
		super(id);
		ResetPasswordModel model = ResetPasswordModel.builder()
			.username(parameters.get(ParameterKeys.USERNAME).toString())
			.confirmationCode(parameters.get(ParameterKeys.CONFIRMATION_CODE).toString().trim())
			.build();
		onReset(model.getUsername(), model.getConfirmationCode());
	}

	public AbstractResetPasswordPanel(final String id, final IModel<ResetPasswordModel> model)
	{
		super(id, model);
		onReset(model.getObject().getUsername(), model.getObject().getConfirmationCode());
	}

	protected abstract void onReset(String username, String confirmationCode);
}
