package org.jaulp.wicket.components.labeled.textfield;

import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;
import org.jaulp.wicket.components.labeled.LabeledFormComponentPanel;

/**
 * Convenience class for labeled PasswordTextField.
 * 
 */
public class LabeledPasswordTextFieldPanel extends
		LabeledFormComponentPanel<String> {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The text field. */
	private PasswordTextField passwordTextField;

	/**
	 * Instantiates a new LabeledPasswordTextFieldPanel.
	 * 
	 * @param id
	 *            the id
	 */
	public LabeledPasswordTextFieldPanel(String id) {
		this(id, null, null);
	}

	/**
	 * Instantiates a new LabeledPasswordTextFieldPanel.
	 * 
	 * @param id
	 *            the id
	 * @param model
	 *            the model
	 */
	public LabeledPasswordTextFieldPanel(String id, IModel<String> model,
			IModel<String> labelModel) {
		super(id, model, labelModel);
		PropertyModel<String> passwordTextFieldModel = new PropertyModel<String>(
				model.getObject(), id);
		add(passwordTextField = newPasswordTextField("passwordTextField",
				passwordTextFieldModel));

		add(feedback = newComponentFeedbackPanel("feedback", passwordTextField));

		String markupId = passwordTextField.getMarkupId();
		add(label = newLabel("label", markupId, this.labelModel));
	}

	/**
	 * Factory method for creating the PasswordTextField. This method is invoked
	 * in the constructor from the derived classes and can be overridden so
	 * users can provide their own version of a PasswordTextField.
	 * 
	 * @param id
	 *            the id
	 * @param model
	 *            the model
	 * @return the text field
	 */
	protected PasswordTextField newPasswordTextField(String id,
			IModel<String> model) {
		PasswordTextField passwordTextField = new PasswordTextField(id, model);
		passwordTextField.setOutputMarkupId(true);
		return passwordTextField;
	}

	/**
	 * {@inheritDoc}
	 */
	public String getInput() {
		return passwordTextField.getInput();
	}

	/**
	 * {@inheritDoc}
	 */
	protected void convertInput() {
		setConvertedInput(passwordTextField.getConvertedInput());
	}

	/**
	 * {@inheritDoc}
	 */
	protected void onBeforeRender() {
		passwordTextField.setRequired(isRequired());
		super.onBeforeRender();
	}
}