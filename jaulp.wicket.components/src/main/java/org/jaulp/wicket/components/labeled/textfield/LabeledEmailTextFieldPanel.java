package org.jaulp.wicket.components.labeled.textfield;

import org.apache.wicket.markup.html.form.EmailTextField;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;
import org.jaulp.wicket.components.labeled.LabeledFormComponentPanel;

/**
 * Convenience class for labeled TextField.
 *
 * @param <T> the generic type
 */
public class LabeledEmailTextFieldPanel<T> extends LabeledFormComponentPanel<T> {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
		
	/** The EmailTextField. */
	private final EmailTextField emailTextField;

	/**
	 * Instantiates a new LabeledEmailTextFieldPanel.
	 *
	 * @param id the id
	 */
	public LabeledEmailTextFieldPanel(String id) {
		this(id, null, null);
	}

	/**
	 * Instantiates a new LabeledEmailTextFieldPanel.
	 *
	 * @param id the id
	 * @param model the model
	 * @param labelModel the label model
	 */
	public LabeledEmailTextFieldPanel(String id, IModel<T> model, IModel<String> labelModel) {
		super(id, model, labelModel);		
		
		add(emailTextField = newEmailTextField("emailTextField", model));

		add(feedback = newComponentFeedbackPanel("feedback", emailTextField));

		String markupId = emailTextField.getMarkupId();
		add(label = newLabel("label", markupId, getLabel()));
	}

	/**
	 * {@inheritDoc}
	 */
	protected void convertInput() {
		setConvertedInput(getModel().getObject());
	}

	/**
	 * {@inheritDoc}
	 */
	public String getInput() {
		return emailTextField.getInput();
	}

	/**
	 * Gets the EmailTextField.
	 *
	 * @return the EmailTextField
	 */
	public EmailTextField getEmailTextField() {
		return emailTextField;
	}

	/**
	 * Factory method for creating the EmailTextField. This method is invoked in the
	 * constructor from the derived classes and can be overridden so users can
	 * provide their own version of a EmailTextField.
	 *
	 * @param id the id
	 * @param model the model
	 * @return the text field
	 */
	protected EmailTextField newEmailTextField(String id, IModel<T> model) {
		IModel<String> textFieldModel = new PropertyModel<String>(model.getObject(), getId());
		EmailTextField emailTextField = new EmailTextField(id, textFieldModel);
		emailTextField.setOutputMarkupId(true);
		return emailTextField;
	}

	/**
	 * {@inheritDoc}
	 */
	protected void onBeforeRender() {
		emailTextField.setRequired(isRequired());
		super.onBeforeRender();
	}
}
