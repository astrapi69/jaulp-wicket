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

import lombok.Getter;

import org.apache.wicket.Component;
import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.alpharogroup.auth.models.BaseUsernameSignUpModel;
import de.alpharogroup.wicket.base.BasePanel;
import de.alpharogroup.wicket.base.util.resource.ResourceModelFactory;
import de.alpharogroup.wicket.components.labeled.textfield.LabeledPasswordTextFieldPanel;
import de.alpharogroup.wicket.components.labeled.textfield.LabeledTextFieldPanel;
import de.alpharogroup.wicket.components.sign.in.SigninPanel;

/**
 * The Class SignupPanel.
 */
public class SignupPanel<T extends BaseUsernameSignUpModel> extends BasePanel<T>
{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The Constant LOGGER. */
	protected static final Logger LOGGER = LoggerFactory.getLogger(SignupPanel.class);

	/** The username. */
	@Getter
	private Component username;

	/** The signin panel. */
	@Getter
	private Component signinPanel;

	/** The repeat password. */
	@Getter
	private Component repeatPassword;

	/**
	 * Instantiates a new signup panel.
	 *
	 * @param id
	 *            the id
	 * @param model
	 *            the model
	 */
	public SignupPanel(final String id, final IModel<T> model)
	{
		super(id, model);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@SuppressWarnings("unchecked")
	protected void onInitialize() {
		super.onInitialize();
		add(username = newUsernameTextField("username", getModel()));
		add(signinPanel = newSigninPanel("signinPanel", getModel()));
		add(repeatPassword = newRepeatPasswordTextField("repeatPassword",
			(IModel<BaseUsernameSignUpModel>)getModel()));
	};

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
	protected Component newRepeatPasswordTextField(final String id,
		final IModel<BaseUsernameSignUpModel> model)
	{
		final IModel<String> labelModel = ResourceModelFactory.newResourceModel(
			"global.repeat.password.label", this);
		final IModel<String> placeholderModel = ResourceModelFactory.newResourceModel(
			"global.enter.your.password.again.label", this);
		final LabeledPasswordTextFieldPanel<BaseUsernameSignUpModel> pwTextField = new LabeledPasswordTextFieldPanel<BaseUsernameSignUpModel>(
			id, model, labelModel)
		{
			private static final long serialVersionUID = 1L;

			@Override
			protected PasswordTextField newPasswordTextField(final String id,
				final IModel<BaseUsernameSignUpModel> modelSuper)
			{
				final PasswordTextField pwTextField = new PasswordTextField(id,
					new PropertyModel<>(model, "repeatPassword"));
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
	protected Component newSigninPanel(final String id, final IModel<T> model)
	{
		return new SigninPanel<>(id, model);
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
	protected Component newUsernameTextField(final String id, final IModel<T> model)
	{
		final IModel<String> labelModel = ResourceModelFactory.newResourceModel(
			"global.username.label", this);
		final IModel<String> placeholderModel = ResourceModelFactory.newResourceModel(
			"global.enter.your.username.label", this);
		final LabeledTextFieldPanel<T> nameTextField = new LabeledTextFieldPanel<T>(id, model,
			labelModel)
		{

			private static final long serialVersionUID = 1L;

			@Override
			@SuppressWarnings({ "rawtypes", "unchecked" })
			protected TextField newTextField(final String id, final IModel<T> modelSuper)
			{
				final TextField<String> textField = new TextField<String>(id, new PropertyModel<>(
					model, "username"));
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

}
