package de.alpharogroup.wicket.components.sign.up;

import net.sourceforge.jaulp.auth.models.BaseUsernameSignUpModel;

import org.apache.wicket.Component;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.jaulp.wicket.base.BasePanel;
import org.jaulp.wicket.base.util.resource.ResourceModelFactory;

public abstract class SignupFormPanel extends BasePanel<BaseUsernameSignUpModel>
{
	private static final long serialVersionUID = 1L;

	/** The button label. */
	private Label buttonLabel;

	private Button submitButton;

	private Form<?> form;

	private Component signupPanel;

	public SignupFormPanel(String id)
	{
		super(id);
		initComponent();
	}

	protected void initComponent()
	{
		BaseUsernameSignUpModel modelObject = new BaseUsernameSignUpModel();
		modelObject.setEmail("");
		IModel<BaseUsernameSignUpModel> model = new CompoundPropertyModel<>(modelObject);
		setModel(model);
		addOrReplace(form = newForm("form", model));
		form.addOrReplace(signupPanel = newSignupPanel("signupPanel", getModel()));

		form.addOrReplace(submitButton = newButton("signupButton"));
		submitButton.add(buttonLabel = newButtonLabel("buttonLabel", "global.button.sign.up.label",
			"Sign up"));
		form.add(submitButton);
	}

	/**
	 * Factory method for creating the SignupPanel. This method is invoked in the constructor from
	 * the derived classes and can be overridden so users can provide their own version of a
	 * SignupPanel.
	 * 
	 * @param id
	 *            the id
	 * @param model
	 *            the model
	 * @return the SignupPanel
	 */
	protected Component newSignupPanel(String id, IModel<BaseUsernameSignUpModel> model)
	{
		return new SignupPanel(id, model);
	}

	/**
	 * Factory method for creating the Form. This method is invoked in the constructor from the
	 * derived classes and can be overridden so users can provide their own version of a Form.
	 * 
	 * @param id
	 *            the id
	 * @param model
	 *            the model
	 * @return the form
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	protected Form<?> newForm(String id, IModel<? extends BaseUsernameSignUpModel> model)
	{
		return new Form(id, model);
	}

	/**
	 * Factory method for creating the Button. This method is invoked in the constructor from the
	 * derived classes and can be overridden so users can provide their own version of a Button.
	 * 
	 * @param id
	 *            the wicket id
	 * @return the Button
	 */
	protected Button newButton(String id)
	{
		return new Button(id)
		{
			/** The serialVersionUID. */
			private static final long serialVersionUID = 1L;

			@Override
			public void onSubmit()
			{
				onSignup();
			}
		};
	}

	/**
	 * Factory method for creating the Button Label. This method is invoked in the constructor from
	 * the derived classes and can be overridden so users can provide their own version of a Label.
	 * 
	 * @param id
	 *            the id
	 * @param resourceKey
	 *            the resource key
	 * @param defaultValue
	 *            the default value
	 * @return the label
	 */
	protected Label newButtonLabel(String id, final String resourceKey, final String defaultValue)
	{
		final IModel<String> labelModel = ResourceModelFactory.newResourceModel(resourceKey, this,
			defaultValue);
		Label label = new Label(id, labelModel);
		label.setOutputMarkupId(true);
		return label;
	}

	protected abstract void onSignup();

	public Component getSignupPanel()
	{
		return signupPanel;
	}

	public Button getSubmitButton()
	{
		return submitButton;
	}

	public Label getButtonLabel()
	{
		return buttonLabel;
	}

	public Form<?> getForm()
	{
		return form;
	}
}
