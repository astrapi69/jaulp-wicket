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
package de.alpharogroup.wicket.components.sign.in.password.forgotten;

import static org.wicketeer.modelfactory.ModelFactory.from;
import static org.wicketeer.modelfactory.ModelFactory.model;
import lombok.Getter;

import org.apache.wicket.Component;
import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.EmailTextField;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;

import de.alpharogroup.wicket.base.util.resource.ResourceModelFactory;
import de.alpharogroup.wicket.components.factory.ComponentFactory;
import de.alpharogroup.wicket.components.labeled.textfield.LabeledEmailTextFieldPanel;

/**
 * The class PasswordForgottenPanel.
 * 
 * @author Asterios Raptis
 */
public abstract class AbstractPasswordForgottenPanel extends Panel
{

	/**
	 * The serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;

	/** The button label. */
	@Getter
	private final Label buttonLabel;

	/** The captcha panel. */
	@Getter
	private final Component captchaPanel;

	/** The email. */
	@Getter
	private final Component email;

	/** The form. */
	@Getter
	private final Form<?> form;

	/** The header. */
	@Getter
	private final Label header;

	/** The submit button. */
	@Getter
	private final Button submitButton;

	/**
	 * Instantiates a new password forgotten panel.
	 * 
	 * @param id
	 *            the id
	 */
	@SuppressWarnings("unchecked")
	public AbstractPasswordForgottenPanel(final String id)
	{
		super(id);
		setDefaultModel(new CompoundPropertyModel<>(new PasswordForgottenModel()));

		add(form = newForm("form", getDefaultModel()));

		form.add(header = newHeaderLabel("header", "password.forgotten.label",
			"Password forgotten help", this));

		email = newEmailTextField("email", (IModel<PasswordForgottenModel>)getDefaultModel());

		form.add(email);

		form.add(captchaPanel = newCaptcha("captchaPanel"));
		// Create submit button for the form
		submitButton = newButton("submitButton");
		buttonLabel = newButtonLabel("buttonLabel", "global.button.send.email.label", "Send email");
		submitButton.add(buttonLabel);
		form.add(submitButton);
	}

	/**
	 * Factory method for creating the EmailTextField for the email. This method is invoked in the
	 * constructor from the derived classes and can be overridden so users can provide their own
	 * version of a EmailTextField for the email.
	 *
	 * @param id
	 *            the id
	 * @param model
	 *            the model
	 * @return the text field
	 */
	protected Component newEmailTextField(String id, final IModel<PasswordForgottenModel> model)
	{
		final IModel<String> labelModel = ResourceModelFactory.newResourceModel(
			"password.forgotten.content.label", this, "Give email in the Textfield");
		final IModel<String> placeholderModel = ResourceModelFactory.newResourceModel(
			"global.enter.your.email.label", this, "Enter your email here");
		LabeledEmailTextFieldPanel<PasswordForgottenModel> emailTextField = new LabeledEmailTextFieldPanel<PasswordForgottenModel>(
			id, model, labelModel)
		{

			private static final long serialVersionUID = 1L;

			@Override
			protected EmailTextField newEmailTextField(String id,
				IModel<PasswordForgottenModel> model)
			{
				EmailTextField emailTextField = new EmailTextField(id,
					model(from(model).getEmail()));
				emailTextField.setOutputMarkupId(true);
				emailTextField.setRequired(true);
				if (placeholderModel != null)
				{
					emailTextField.add(new AttributeAppender("placeholder", placeholderModel));
				}
				return emailTextField;
			}
		};
		return emailTextField;
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
			/**
			 * The serialVersionUID.
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void onSubmit()
			{
				onSend();
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
	protected Label newButtonLabel(String id, final String resourceKey, final String defaultValue)
	{
		return ComponentFactory.newLabel(id, resourceKey, defaultValue, this);
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
	 * Factory method for creating the Label. This method is invoked in the constructor from the
	 * derived classes and can be overridden so users can provide their own version of a Label.
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
	protected Label newEmailLabel(String id, String forId, final String resourceKey,
		final String defaultValue, final Component component)
	{
		return ComponentFactory.newLabel(id, forId,
			ResourceModelFactory.newResourceModel(resourceKey, component, defaultValue));
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
	protected Form<?> newForm(String id, IModel<?> model)
	{
		return ComponentFactory.newForm(id, model);
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
	protected Label newHeaderLabel(String id, final String resourceKey, final String defaultValue,
		final Component component)
	{
		return ComponentFactory.newLabel(id,
			ResourceModelFactory.newResourceModel(resourceKey, component, defaultValue));
	}

	// Hook method for implement the action...
	/**
	 * On send.
	 */
	protected abstract void onSend();

}
