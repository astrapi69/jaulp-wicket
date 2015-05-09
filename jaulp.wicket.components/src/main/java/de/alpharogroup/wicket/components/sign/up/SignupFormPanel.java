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

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import de.alpharogroup.auth.models.BaseUsernameSignUpModel;
import de.alpharogroup.wicket.base.BasePanel;
import de.alpharogroup.wicket.base.util.ComponentFinder;
import de.alpharogroup.wicket.base.util.resource.ResourceModelFactory;

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
		this(id, Model.of(new BaseUsernameSignUpModel()));
	}

	public SignupFormPanel(String id, IModel<BaseUsernameSignUpModel> model)
	{
		super(id, model);
	}

	@Override
	protected void onInitialize()
	{
		super.onInitialize();
		initComponent();
	}

	protected void initComponent()
	{
		getModelObject().setEmail("");
		IModel<BaseUsernameSignUpModel> model = new CompoundPropertyModel<>(getModel());
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
	protected Label newButtonLabel(String id, final String resourceKey, final String defaultValue)
	{
		final IModel<String> labelModel = ResourceModelFactory.newResourceModel(resourceKey, this,
			defaultValue);
		Label label = new Label(id, labelModel);
		label.setOutputMarkupId(true);
		return label;
	}

	protected abstract void onSignup(final AjaxRequestTarget target, final Form<?> form);

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
