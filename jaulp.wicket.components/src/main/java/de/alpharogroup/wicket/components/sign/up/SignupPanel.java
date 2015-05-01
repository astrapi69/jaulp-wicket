/**
 * Copyright (C) 2010 Asterios Raptis
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package de.alpharogroup.wicket.components.sign.up;

import static org.wicketeer.modelfactory.ModelFactory.from;
import static org.wicketeer.modelfactory.ModelFactory.model;

import org.apache.wicket.Component;
import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.jaulp.wicket.base.util.resource.ResourceModelFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.alpharogroup.auth.models.BaseUsernameSignUpModel;
import de.alpharogroup.wicket.components.labeled.textfield.LabeledPasswordTextFieldPanel;
import de.alpharogroup.wicket.components.labeled.textfield.LabeledTextFieldPanel;
import de.alpharogroup.wicket.components.sign.in.SigninPanel;

/**
 * The Class SignupPanel.
 */
public class SignupPanel extends Panel
{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The Constant LOGGER. */
	protected static final Logger LOGGER = LoggerFactory.getLogger(SignupPanel.class);

	/** The username. */
	private Component username;

	/** The signin panel. */
	private Component signinPanel;

	/**
	 * Gets the signin panel.
	 *
	 * @return the signin panel
	 */
	public Component getSigninPanel()
	{
		return signinPanel;
	}

	/** The repeat password. */
	private Component repeatPassword;

	/**
	 * Instantiates a new signup panel.
	 *
	 * @param id
	 *            the id
	 * @param model
	 *            the model
	 */
	@SuppressWarnings("unchecked")
	public SignupPanel(String id, final IModel<? extends BaseUsernameSignUpModel> model)
	{
		super(id, model);
		add(username = newUsernameTextField("username", (IModel<BaseUsernameSignUpModel>)model));
		add(signinPanel = newSigninPanel("signinPanel", model));
		add(repeatPassword = newRepeatPasswordTextField("repeatPassword",
			(IModel<BaseUsernameSignUpModel>)model));
	}

	/**
	 * Factory method for creating the SigninPanel that contains the TextField for the email and
	 * password. This method is invoked in the constructor from the derived classes and can be
	 * overridden so users can provide their own version of a Component that contains the TextField
	 * for the email and password.
	 *
	 * @param id
	 *            the id
	 * @param model
	 *            the model
	 * @return the Component
	 */
	protected Component newSigninPanel(String id,
		final IModel<? extends BaseUsernameSignUpModel> model)
	{
		return new SigninPanel(id, model);
	}

	/**
	 * Factory method for creating the TextField for the username. This method is invoked in the
	 * constructor from the derived classes and can be overridden so users can provide their own
	 * version of a TextField for the username.
	 *
	 * @param id
	 *            the id
	 * @param model
	 *            the model
	 * @return the text field
	 */
	protected Component newUsernameTextField(String id, final IModel<BaseUsernameSignUpModel> model)
	{
		IModel<String> labelModel = ResourceModelFactory.newResourceModel("global.username.label",
			this);
		final IModel<String> placeholderModel = ResourceModelFactory.newResourceModel(
			"global.enter.your.username.label", this);
		LabeledTextFieldPanel<BaseUsernameSignUpModel> nameTextField = new LabeledTextFieldPanel<BaseUsernameSignUpModel>(
			id, model, labelModel)
		{

			private static final long serialVersionUID = 1L;

			@Override
			@SuppressWarnings({ "rawtypes", "unchecked" })
			protected TextField newTextField(String id, IModel<BaseUsernameSignUpModel> modelSuper)
			{
				TextField<String> textField = new TextField<String>(id, model(from(model)
					.getUsername()));
				textField.setOutputMarkupId(true);
				textField.setRequired(true);
				if (placeholderModel != null)
				{
					textField.add(new AttributeAppender("placeholder", placeholderModel));
				}
				return textField;
			}
		};
		return nameTextField;
	}

	/**
	 * Factory method for creating the EmailTextField for the repeated password. This method is
	 * invoked in the constructor from the derived classes and can be overridden so users can
	 * provide their own version of a EmailTextField for the repeated password.
	 *
	 * @param id
	 *            the id
	 * @param model
	 *            the model
	 * @return the text field
	 */
	protected Component newRepeatPasswordTextField(String id,
		final IModel<BaseUsernameSignUpModel> model)
	{
		IModel<String> labelModel = ResourceModelFactory.newResourceModel(
			"global.repeat.password.label", this);
		final IModel<String> placeholderModel = ResourceModelFactory.newResourceModel(
			"global.enter.your.password.again.label", this);
		LabeledPasswordTextFieldPanel<BaseUsernameSignUpModel> pwTextField = new LabeledPasswordTextFieldPanel<BaseUsernameSignUpModel>(
			id, model, labelModel)
		{
			private static final long serialVersionUID = 1L;

			@Override
			protected PasswordTextField newPasswordTextField(String id,
				IModel<BaseUsernameSignUpModel> modelSuper)
			{
				PasswordTextField pwTextField = new PasswordTextField(id, model(from(model)
					.getRepeatPassword()));
				pwTextField.setOutputMarkupId(true);
				if (placeholderModel != null)
				{
					pwTextField.add(new AttributeAppender("placeholder", placeholderModel));
				}
				return pwTextField;
			}
		};
		return pwTextField;
	}

	/**
	 * Gets the username.
	 *
	 * @return the username
	 */
	public Component getUsername()
	{
		return username;
	}

	/**
	 * Gets the repeat password.
	 *
	 * @return the repeat password
	 */
	public Component getRepeatPassword()
	{
		return repeatPassword;
	}

}
