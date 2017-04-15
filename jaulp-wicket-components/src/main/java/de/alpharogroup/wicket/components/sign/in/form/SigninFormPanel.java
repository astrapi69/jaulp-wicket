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
package de.alpharogroup.wicket.components.sign.in.form;

import org.apache.wicket.Component;
import org.apache.wicket.MarkupContainer;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.IModel;

import de.alpharogroup.auth.models.SignInModel;
import de.alpharogroup.resourcebundle.locale.ResourceBundleKey;
import de.alpharogroup.wicket.base.BasePanel;
import de.alpharogroup.wicket.base.util.resource.ResourceModelFactory;
import de.alpharogroup.wicket.components.factory.ComponentFactory;
import de.alpharogroup.wicket.components.link.LinkPanel;
import de.alpharogroup.wicket.components.sign.in.SigninPanel;
import lombok.Getter;

/**
 * The Class SinginFormPanel.
 *
 * @param <T>
 *            the generic type
 */
public abstract class SigninFormPanel<T extends SignInModel> extends BasePanel<T>
{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The button label. */
	@Getter
	private Label buttonLabel;
	/** The form. */
	@Getter
	private Form<?> form;

	/** The signin panel. */
	@Getter
	private Component signinPanel;

	/** The submit button. */
	@Getter
	private Button submitButton;

	/** The password forgotten link. */
	@Getter
	private MarkupContainer passwordForgottenLink;

	/**
	 * Instantiates a new {@link SigninFormPanel}.
	 *
	 * @param id
	 *            the component id
	 * @param model
	 *            the component model
	 */
	public SigninFormPanel(final String id, final IModel<T> model)
	{
		super(id, model);
	}

	/**
	 * Factory method for creating the new {@link Button}. This method is invoked in the constructor
	 * from the derived classes and can be overridden so users can provide their own version of a
	 * new {@link Button}.
	 *
	 * @param id
	 *            the id
	 * @return the new {@link Button}
	 */
	protected Button newButton(final String id)
	{
		return new AjaxButton(id)
		{
			/**
			 * The serialVersionUID.
			 */
			private static final long serialVersionUID = 1L;

			/**
			 * Listener method invoked on form submit with errors
			 *
			 * @param target
			 * @param form
			 */
			@Override
			protected void onError(final AjaxRequestTarget target, final Form<?> form)
			{
				SigninFormPanel.this.onSignin(target, SigninFormPanel.this.form);
			}


			/**
			 * {@inheritDoc}
			 */
			@Override
			public void onSubmit(final AjaxRequestTarget target, final Form<?> form)
			{
				SigninFormPanel.this.onSignin(target, SigninFormPanel.this.form);
			}
		};
	};

	/**
	 * Factory method for creating the new {@link Label} for the button. This method is invoked in
	 * the constructor from the derived classes and can be overridden so users can provide their own
	 * version of a new {@link Label} for the button.
	 *
	 * @param id
	 *            the id
	 * @param resourceKey
	 *            the resource key
	 * @param defaultValue
	 *            the default value
	 * @return the new {@link Label} for the button.
	 */
	protected Label newButtonLabel(final String id, final String resourceKey,
		final String defaultValue)
	{
		final IModel<String> labelModel = ResourceModelFactory.newResourceModel(resourceKey, this,
			defaultValue);

		return ComponentFactory.newLabel(id, labelModel);
	}

	/**
	 * Factory method for create the new {@link Form}. This method is invoked in the constructor
	 * from the derived classes and can be overridden so users can provide their own version of a
	 * new {@link Form}.
	 *
	 * @param id
	 *            the id
	 * @param model
	 *            the model
	 * @return the new {@link Form}
	 */
	protected Form<?> newForm(final String id, final IModel<?> model)
	{
		return ComponentFactory.newForm(id, model);
	}

	/**
	 * Factory method for create the new {@link MarkupContainer} of the {@link Link} for the
	 * password forgotten. This method is invoked in the constructor from the derived classes and
	 * can be overridden so users can provide their own version of a new {@link MarkupContainer} of
	 * the {@link Link} for the password forgotten.
	 *
	 * @param id
	 *            the id
	 * @param model
	 *            the model
	 * @return the new {@link MarkupContainer} of the {@link Link} for the password forgotten.
	 */
	protected MarkupContainer newPasswordForgottenLink(final String id, final IModel<T> model)
	{
		final LinkPanel linkPanel = new LinkPanel(id,
			ResourceModelFactory.newResourceModel(ResourceBundleKey.builder()
				.key("password.forgotten.label").defaultValue("Password forgotten").build()))
		{
			/**
			 * The serialVersionUID
			 */
			private static final long serialVersionUID = 1L;

			/**
			 * {@inheritDoc}
			 */
			@Override
			public void onClick(final AjaxRequestTarget target)
			{
				onPasswordForgotten(target, form);
			}

		};
		return linkPanel;
	}

	/**
	 * Factory method for creating the new {@link SigninPanel} that contains the TextField for the
	 * email and password. This method is invoked in the constructor from the derived classes and
	 * can be overridden so users can provide their own version of a Component that contains the
	 * TextField for the email and password.
	 *
	 * @param id
	 *            the id
	 * @param model
	 *            the model
	 * @return the new {@link SigninPanel} that contains the TextField for the email and password.
	 */
	protected Component newSigninPanel(final String id, final IModel<T> model)
	{
		final Component component = new SigninPanel<>(id, model);
		return component;
	}


	/**
	 * Abstract callback method that have to be overwritten to provide the action for signin.
	 *
	 * @param target
	 *            the target
	 * @param form
	 *            the form
	 */
	protected abstract void onError(final AjaxRequestTarget target, final Form<?> form);

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void onInitialize()
	{
		super.onInitialize();
		add(form = newForm("form", getModel()));
		form.add(signinPanel = newSigninPanel("signinPanel", getModel()));
		// Create submit button for the form
		submitButton = newButton("submitButton");
		submitButton.add(
			buttonLabel = newButtonLabel("buttonLabel", "global.button.sign.in.label", "Sign In"));
		form.add(submitButton);
		passwordForgottenLink = newPasswordForgottenLink("passwordForgottenLink", getModel());
		add(passwordForgottenLink);
	}

	/**
	 * Abstract callback method that have to be overwritten to provide the action for password
	 * forgotten.
	 *
	 * @param target
	 *            the target
	 * @param form
	 *            the form
	 */
	protected abstract void onPasswordForgotten(final AjaxRequestTarget target, final Form<?> form);

	/**
	 * Abstract callback method that have to be overwritten to provide the action for signin.
	 *
	 * @param target
	 *            the target
	 * @param form
	 *            the form
	 */
	protected abstract void onSignin(final AjaxRequestTarget target, final Form<?> form);

}
