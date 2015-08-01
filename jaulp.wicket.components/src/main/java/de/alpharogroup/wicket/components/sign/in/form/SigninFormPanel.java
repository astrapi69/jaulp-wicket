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

import lombok.Getter;

import org.apache.wicket.Component;
import org.apache.wicket.MarkupContainer;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.GenericPanel;
import org.apache.wicket.model.IModel;

import de.alpharogroup.auth.models.SignInModel;
import de.alpharogroup.locale.ResourceBundleKey;
import de.alpharogroup.wicket.base.util.resource.ResourceModelFactory;
import de.alpharogroup.wicket.components.factory.ComponentFactory;
import de.alpharogroup.wicket.components.link.LinkPanel;
import de.alpharogroup.wicket.components.sign.in.SigninPanel;

/**
 * The Class SinginFormPanel.
 *
 * @param <T>
 *            the generic type
 */
public abstract class SigninFormPanel<T extends SignInModel> extends GenericPanel<T>
{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The button label. */
	@Getter
	private final Label buttonLabel;
	/** The form. */
	@Getter
	private final Form<?> form;

	/** The signin panel. */
	@Getter
	private final Component signinPanel;

	/** The submit button. */
	@Getter
	private final Button submitButton;

	/** The password forgotten link. */
	@Getter
	private final MarkupContainer passwordForgottenLink;

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
		add(form = newForm("form", model));
		form.add(signinPanel = newSigninPanel("signinPanel", model));
		// Create submit button for the form
		submitButton = newButton("submitButton");
		submitButton.add(buttonLabel = newButtonLabel("buttonLabel", "global.button.sign.in.label",
			"Sign In"));
		form.add(submitButton);
		passwordForgottenLink = newPasswordForgottenLink("passwordForgottenLink", model);
		add(passwordForgottenLink);

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
		return new AjaxButton(id)
		{
			/**
			 * The serialVersionUID.
			 */
			private static final long serialVersionUID = 1L;

			/**
			 * {@inheritDoc}
			 */
			@Override
			public void onSubmit(final AjaxRequestTarget target, final Form<?> form)
			{
				onSignin(target, getForm());
			}
		};
	}

	/**
	 * Factory method for creating the button Label. This method is invoked in the constructor from
	 * the derived classes and can be overridden so users can provide their own version of a button
	 * Label.
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

		return ComponentFactory.newLabel(id, labelModel);
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
	protected Form<?> newForm(final String id, final IModel<?> model)
	{
		return ComponentFactory.newForm(id, model);
	}

	/**
	 * New password forgotten link.
	 *
	 * @param id
	 *            the id
	 * @param model
	 *            the model
	 * @return the markup container
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
	protected Component newSigninPanel(final String id, final IModel<? extends SignInModel> model)
	{
		final Component component = new SigninPanel(id, model);
		return component;
	}

	/**
	 * Callback method that is called on password forgotten.
	 *
	 * @param target
	 *            the target
	 * @param form
	 *            the form
	 */
	protected abstract void onPasswordForgotten(final AjaxRequestTarget target, final Form<?> form);

	/**
	 * Callback method that is called on signin.
	 *
	 * @param target
	 *            the target
	 * @param form
	 *            the form
	 */
	protected abstract void onSignin(final AjaxRequestTarget target, final Form<?> form);

}
