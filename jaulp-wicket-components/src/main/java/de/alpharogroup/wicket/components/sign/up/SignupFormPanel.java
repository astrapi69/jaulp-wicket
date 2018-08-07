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

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.validation.EqualPasswordInputValidator;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.util.lang.Args;

import de.alpharogroup.auth.sign.up.BaseUsernameSignUpModel;
import de.alpharogroup.wicket.base.BasePanel;
import de.alpharogroup.wicket.base.util.ComponentFinder;
import de.alpharogroup.wicket.base.util.resource.ResourceModelFactory;
import lombok.Getter;

/**
 * The Class {@link SignupFormPanel}.
 */
public abstract class SignupFormPanel extends BasePanel<BaseUsernameSignUpModel>
{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The button label. */
	@Getter
	private Label buttonLabel;

	/** The submit button. */
	@Getter
	private Button submitButton;

	/** The form. */
	@Getter
	private Form<?> form;

	/** The signup panel. */
	@Getter
	private SignupPanel<BaseUsernameSignUpModel> signupPanel;

	/**
	 * Instantiates a new {@link SignupFormPanel}.
	 *
	 * @param id
	 *            the id
	 */
	public SignupFormPanel(final String id)
	{
		this(id, Model.of(new BaseUsernameSignUpModel()));
	}

	/**
	 * Instantiates a new {@link SignupFormPanel}.
	 *
	 * @param id
	 *            the id
	 * @param model
	 *            the model
	 */
	public SignupFormPanel(final String id, final IModel<BaseUsernameSignUpModel> model)
	{
		super(id, Args.notNull(model, "model"));
	}

	/**
	 * Inits the component.
	 */
	protected void initComponent()
	{
		addOrReplace(form = newForm("form", getModel()));
		form.addOrReplace(signupPanel = newSignupPanel("signupPanel", getModel()));

		form.addOrReplace(submitButton = newButton("signupButton"));
		submitButton.add(
			buttonLabel = newButtonLabel("buttonLabel", "global.button.sign.up.label", "Sign up"));
		form.add(submitButton);

		form.add(new EqualPasswordInputValidator(
			signupPanel.getSigninPanel().getPassword().getPasswordTextField(),
			signupPanel.getRepeatPassword().getPasswordTextField()));
	}

	/**
	 * Factory method for creating the Button. This method is invoked in the constructor from the
	 * derived classes and can be overridden so users can provide their own version of a Button.
	 *
	 * @param id
	 *            the wicket id
	 * @return the Button
	 */
	protected Button newButton(final String id)
	{
		return new Button(id)
		{
			/** The serialVersionUID. */
			private static final long serialVersionUID = 1L;

			@Override
			public void onSubmit()
			{
				onSignup(ComponentFinder.findOrCreateNewAjaxRequestTarget(), getForm());
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
	protected Label newButtonLabel(final String id, final String resourceKey,
		final String defaultValue)
	{
		final IModel<String> labelModel = ResourceModelFactory.newResourceModel(resourceKey, this,
			defaultValue);
		final Label label = new Label(id, labelModel);
		label.setOutputMarkupId(true);
		return label;
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
	protected Form<?> newForm(final String id,
		final IModel<? extends BaseUsernameSignUpModel> model)
	{
		return new Form(id, model);
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
	protected SignupPanel<BaseUsernameSignUpModel> newSignupPanel(final String id,
		final IModel<BaseUsernameSignUpModel> model)
	{
		return new SignupPanel<>(id, model);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void onInitialize()
	{
		super.onInitialize();
		initComponent();
	}

	/**
	 * Abstract callback method that have to be overwritten to provide the action on sign up.
	 *
	 * @param target
	 *            the target
	 * @param form
	 *            the form
	 */
	protected abstract void onSignup(final AjaxRequestTarget target, final Form<?> form);
}
