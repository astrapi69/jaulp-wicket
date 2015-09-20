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
package de.alpharogroup.wicket.components.sign.in.password.change;

import static org.wicketeer.modelfactory.ModelFactory.from;
import static org.wicketeer.modelfactory.ModelFactory.model;
import lombok.Getter;

import org.apache.wicket.Component;
import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;

import de.alpharogroup.wicket.base.util.resource.ResourceModelFactory;
import de.alpharogroup.wicket.components.labeled.textfield.LabeledPasswordTextFieldPanel;


/**
 * The Class {@link ChangePasswordPanel}.
 */
public class ChangePasswordPanel extends Panel
{

	/**
	 * The serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;

	/** The current password. */
	@Getter
	private final Component currentPassword;

	/** The new password. */
	@Getter
	private final Component newPassword;

	/** The repeat new password. */
	@Getter
	private final Component repeatNewPassword;

	/**
	 * Instantiates a new {@link ChangePasswordPanel}.
	 *
	 * @param id
	 *            the id
	 * @param model
	 *            the model
	 */
	public ChangePasswordPanel(final String id, final IModel<ChangePasswordModel> model)
	{
		super(id);
		add(currentPassword = newCurrentPasswordTextField("currentPassword", model));
		add(newPassword = newPasswordTextField("newPassword", model));
		add(repeatNewPassword = newRepeatPasswordTextField("repeatNewPassword", model));
	}

	/**
	 * Factory method for creating the new PasswordTextField for the current password. This method
	 * is invoked in the constructor from the derived classes and can be overridden so users can
	 * provide their own version of a new PasswordTextField for the current password.
	 *
	 * @param id
	 *            the id
	 * @param model
	 *            the model
	 * @return the new PasswordTextField for the current password.
	 */
	protected Component newCurrentPasswordTextField(final String id,
		final IModel<ChangePasswordModel> model)
	{
		final IModel<String> labelModel = ResourceModelFactory.newResourceModel(
			"change.pw.current.password.label", this);
		final IModel<String> placeholderModel = ResourceModelFactory.newResourceModel(
			"global.enter.current.password.label", this);
		final LabeledPasswordTextFieldPanel<ChangePasswordModel> pwTextField = new LabeledPasswordTextFieldPanel<ChangePasswordModel>(
			id, model, labelModel)
		{
			private static final long serialVersionUID = 1L;

			@Override
			protected PasswordTextField newPasswordTextField(final String id,
				final IModel<ChangePasswordModel> model)
			{
				final PasswordTextField pwTextField = new PasswordTextField(id, model(from(model)
					.getCurrentPassword()));
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
	 * Factory method for creating the new PasswordTextField for the new password. This method is
	 * invoked in the constructor from the derived classes and can be overridden so users can
	 * provide their own version of a new PasswordTextField for the new password.
	 *
	 * @param id
	 *            the id
	 * @param model
	 *            the model
	 * @return the new PasswordTextField for the new password.
	 */
	protected Component newPasswordTextField(final String id,
		final IModel<ChangePasswordModel> model)
	{
		final IModel<String> labelModel = ResourceModelFactory.newResourceModel(
			"change.pw.new.password.label", this);
		final IModel<String> placeholderModel = ResourceModelFactory.newResourceModel(
			"global.enter.new.password.label", this);
		final LabeledPasswordTextFieldPanel<ChangePasswordModel> pwTextField = new LabeledPasswordTextFieldPanel<ChangePasswordModel>(
			id, model, labelModel)
		{
			private static final long serialVersionUID = 1L;

			@Override
			protected PasswordTextField newPasswordTextField(final String id,
				final IModel<ChangePasswordModel> model)
			{
				final PasswordTextField pwTextField = new PasswordTextField(id, model(from(model)
					.getNewPassword()));
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
	 * Factory method for creating the PasswordTextField for the repeat password. This method is
	 * invoked in the constructor from the derived classes and can be overridden so users can
	 * provide their own version of a PasswordTextField for the repeat password.
	 *
	 * @param id
	 *            the id
	 * @param model
	 *            the model
	 * @return the text field
	 */
	protected Component newRepeatPasswordTextField(final String id,
		final IModel<ChangePasswordModel> model)
	{
		final IModel<String> labelModel = ResourceModelFactory.newResourceModel(
			"change.pw.new.password.repeat.label", this);
		final IModel<String> placeholderModel = ResourceModelFactory.newResourceModel(
			"global.enter.new.password.repeat.label", this);
		final LabeledPasswordTextFieldPanel<ChangePasswordModel> pwTextField = new LabeledPasswordTextFieldPanel<ChangePasswordModel>(
			id, model, labelModel)
		{
			private static final long serialVersionUID = 1L;

			@Override
			protected PasswordTextField newPasswordTextField(final String id,
				final IModel<ChangePasswordModel> model)
			{
				final PasswordTextField pwTextField = new PasswordTextField(id, model(from(model)
					.getRepeatNewPassword()));
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

}
