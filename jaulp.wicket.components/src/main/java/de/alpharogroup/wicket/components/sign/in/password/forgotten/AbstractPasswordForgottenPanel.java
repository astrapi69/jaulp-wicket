package de.alpharogroup.wicket.components.sign.in.password.forgotten;

import static org.wicketeer.modelfactory.ModelFactory.from;
import static org.wicketeer.modelfactory.ModelFactory.model;

import org.apache.wicket.Component;
import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.EmailTextField;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.jaulp.wicket.base.util.resource.ResourceModelFactory;

import de.alpharogroup.wicket.components.labeled.textfield.LabeledEmailTextFieldPanel;

/**
 * The class PasswordForgottenPanel.
 * 
 * @author Asterios Raptis
 */
public abstract class AbstractPasswordForgottenPanel extends Panel {

	/**
	 * The serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;

	/** The button label. */
	private final Label buttonLabel;

	/** The captcha panel. */
	private final Component captchaPanel;

	/** The email. */
	private final Component email;

	/** The form. */
	private final Form<?> form;

	/** The header. */
	private final Label header;

	/** The submit button. */
	private final Button submitButton;

	/**
	 * Instantiates a new password forgotten panel.
	 * 
	 * @param id
	 *            the id
	 */
	@SuppressWarnings("unchecked")
	public AbstractPasswordForgottenPanel(final String id) {
		super(id);
		setDefaultModel(new CompoundPropertyModel<PasswordForgottenModel>(
				new PasswordForgottenModel()));

		add(form = newForm("form", getDefaultModel()));

		form.add(header = newHeaderLabel("header", "password.forgotten.label",
				"Password forgotten help", this));

		email = newEmailTextField("email",
				(IModel<PasswordForgottenModel>) getDefaultModel());

		form.add(email);

		form.add(captchaPanel = newCaptcha("captchaPanel"));
		// Create submit button for the form
		submitButton = newButton("submitButton");
		buttonLabel = newButtonLabel("buttonLabel",
				"global.button.send.email.label", "Send email", this);
		submitButton.add(buttonLabel);
		form.add(submitButton);
	}

	/**
	 * Factory method for creating the EmailTextField for the email. This method
	 * is invoked in the constructor from the derived classes and can be
	 * overridden so users can provide their own version of a EmailTextField for
	 * the email.
	 *
	 * @param id
	 *            the id
	 * @param model
	 *            the model
	 * @return the text field
	 */
	protected Component newEmailTextField(String id,
			final IModel<PasswordForgottenModel> model) {
		final IModel<String> labelModel = ResourceModelFactory
				.newResourceModel("password.forgotten.content.label", this,
						"Give email in the Textfield");
		final IModel<String> placeholderModel = ResourceModelFactory
				.newResourceModel("global.enter.your.email.label", this,
						"Enter your email here");
		LabeledEmailTextFieldPanel<PasswordForgottenModel> emailTextField = new LabeledEmailTextFieldPanel<PasswordForgottenModel>(
				id, model, labelModel) {

			private static final long serialVersionUID = 1L;

			@Override
			protected EmailTextField newEmailTextField(String id,
					IModel<PasswordForgottenModel> model) {
				EmailTextField emailTextField = new EmailTextField(id,
						model(from(model).getEmail()));
				emailTextField.setOutputMarkupId(true);
				emailTextField.setRequired(true);
				if(placeholderModel != null) {
					emailTextField.add(new AttributeAppender("placeholder", placeholderModel));
				}
				return emailTextField;
			}
		};
		return emailTextField;
	}

	/**
	 * Gets the button label.
	 *
	 * @return the button label
	 */
	public Label getButtonLabel() {
		return buttonLabel;
	}

	/**
	 * Gets the captcha panel.
	 * 
	 * @return the captcha panel
	 */
	public Component getCaptchaPanel() {
		return captchaPanel;
	}

	/**
	 * Gets the email.
	 * 
	 * @return the email
	 */
	public Component getEmail() {
		return email;
	}

	/**
	 * Gets the form.
	 * 
	 * @return the form
	 */
	public Form<?> getForm() {
		return form;
	}

	/**
	 * Gets the header.
	 * 
	 * @return the header
	 */
	public Label getHeader() {
		return header;
	}

	/**
	 * Gets the submit button.
	 * 
	 * @return the submit button
	 */
	public Button getSubmitButton() {
		return submitButton;
	}

	/**
	 * New button.
	 * 
	 * @param id
	 *            the id
	 * @return the component
	 */
	protected Button newButton(String id) {
		return new Button(id) {
			/**
			 * The serialVersionUID.
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void onSubmit() {
				onSend();
			}
		};
	}

	/**
	 * Factory method for creating the Label. This method is invoked in the
	 * constructor from the derived classes and can be overridden so users can
	 * provide their own version of a Label.
	 * 
	 * @param id
	 *            the id
	 * @param resourceKey
	 *            the resource key
	 * @param defaultValue
	 *            the default value
	 * @param component
	 *            the component
	 * @return the label
	 */
	protected Label newButtonLabel(String id, final String resourceKey,
			final String defaultValue, final Component component) {
		Label label = new Label(id, ResourceModelFactory.newResourceModel(
				resourceKey, component, defaultValue));
		label.setOutputMarkupId(true);
		return label;
	}

	/**
	 * New captcha.
	 * 
	 * @param id
	 *            the id
	 * @return the component
	 */
	protected abstract Component newCaptcha(String id);

	/**
	 * Factory method for creating the Label. This method is invoked in the
	 * constructor from the derived classes and can be overridden so users can
	 * provide their own version of a Label.
	 * 
	 * @param id
	 *            the id
	 * @param forId
	 *            the for id
	 * @param resourceKey
	 *            the resource key
	 * @param defaultValue
	 *            the default value
	 * @param component
	 *            the component
	 * @return the label
	 */
	protected Label newEmailLabel(String id, String forId,
			final String resourceKey, final String defaultValue,
			final Component component) {
		Label label = new Label(id, ResourceModelFactory.newResourceModel(
				resourceKey, component, defaultValue));
		label.add(new AttributeAppender("for", Model.of(forId), " "));
		label.setOutputMarkupId(true);
		return label;
	}

	/**
	 * New form.
	 * 
	 * @param id
	 *            the id
	 * @param model
	 *            the model
	 * @return the form
	 */
	@SuppressWarnings("unchecked")
	protected Form<?> newForm(String id, IModel<?> model) {
		return new Form<PasswordForgottenModel>(id,
				(IModel<PasswordForgottenModel>) model);
	}

	/**
	 * New header label.
	 * 
	 * @param id
	 *            the id
	 * @param resourceKey
	 *            the resource key
	 * @param defaultValue
	 *            the default value
	 * @param component
	 *            the component
	 * @return the label
	 */
	protected Label newHeaderLabel(String id, final String resourceKey,
			final String defaultValue, final Component component) {
		Label label = new Label(id, ResourceModelFactory.newResourceModel(
				resourceKey, component, defaultValue));
		label.setOutputMarkupId(true);
		return label;
	}

	// Hook method for implement the action...
	/**
	 * On send.
	 */
	protected abstract void onSend();

}
