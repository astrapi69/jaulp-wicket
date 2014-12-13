package de.alpharogroup.wicket.components.sign.in.form;

import net.sourceforge.jaulp.auth.models.SignInModel;

import org.apache.wicket.Component;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.jaulp.wicket.base.util.resource.ResourceModelFactory;

import de.alpharogroup.wicket.components.factory.ComponentFactory;
import de.alpharogroup.wicket.components.sign.in.SigninPanel;


/**
 * The Class SinginFormPanel.
 */
public abstract class SigninFormPanel extends Panel {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The button label. */
	private final Label buttonLabel;
	/** The form. */
	private final Form<?> form;

	/** The signin panel. */
	private final Component signinPanel;

	/** The submit button. */
	private final Button submitButton;

	/**
	 * Instantiates a new singin form panel.
	 *
	 * @param id the id
	 * @param model the model
	 */
	public SigninFormPanel(String id, final IModel<? extends SignInModel> model) {
		super(id, model);		
		add(form = newForm("form", model));
		form.add(signinPanel = new SigninPanel("signinPanel", model));
		// Create submit button for the form
		submitButton = newButton("submitButton");			
		submitButton.add(buttonLabel = newButtonLabel("buttonLabel", 
				"global.button.sign.in.label",
				"Sign In"));	
		form.add(submitButton);
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
	 * Gets the form.
	 *
	 * @return the form
	 */
	public Form<?> getForm() {
		return form;
	}
	
	/**
	 * Gets the signin panel.
	 *
	 * @return the signin panel
	 */
	public Component getSigninPanel() {
		return signinPanel;
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
	 * Factory method for creating the Button. This method is invoked in the
	 * constructor from the derived classes and can be overridden so users can
	 * provide their own version of a Button.
	 * 
	 * @param id
	 *            the wicket id
	 * @return the Button
	 */
	protected Button newButton(String id) {
		return new Button(id) {
			/**
			 * The serialVersionUID.
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void onSubmit() {
				onSignin();
			}
		};
	}

	/**
	 * Factory method for creating the button Label. This method is invoked in
	 * the constructor from the derived classes and can be overridden so users
	 * can provide their own version of a button Label.
	 * 
	 * @param id
	 *            the id
	 * @param resourceKey
	 *            the resource key
	 * @param defaultValue
	 *            the default value
	 * @return the label
	 */
	protected Label newButtonLabel(String id, final String resourceKey,
			final String defaultValue) {
		final IModel<String> labelModel = ResourceModelFactory.newResourceModel(resourceKey, this, defaultValue);		
		
		return ComponentFactory.newLabel(id, labelModel);
	}

	/**
	 * Factory method for creating the Form. This method is invoked in the
	 * constructor from the derived classes and can be overridden so users can
	 * provide their own version of a Form.
	 * 
	 * @param id
	 *            the id
	 * @param model
	 *            the model
	 * @return the form
	 */
	protected Form<?> newForm(String id, IModel<?> model) {
		return ComponentFactory.newForm(id, model);
	}

	
	/**
	 * Factory method for creating the SigninPanel that contains the TextField for the email and password. This method is invoked in the
	 * constructor from the derived classes and can be overridden so users can
	 * provide their own version of a Component that contains the TextField for the email and password.
	 *
	 * @param id the id
	 * @param model the model
	 * @return the Component
	 */
	protected Component newSigninPanel(String id, final IModel<SignInModel> model) {
		return new SigninPanel("signinPanel", model);
	}


	/**
	 * On signin.
	 */
	protected abstract void onSignin();

}
