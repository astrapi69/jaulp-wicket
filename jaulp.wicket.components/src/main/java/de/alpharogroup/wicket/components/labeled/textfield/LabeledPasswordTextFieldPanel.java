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
package de.alpharogroup.wicket.components.labeled.textfield;

import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;
import de.alpharogroup.wicket.components.labeled.LabeledFormComponentPanel;

/**
 * Convenience class for labeled PasswordTextField.
 * 
 * @param <T>
 *            the generic type
 * 
 */
public class LabeledPasswordTextFieldPanel<T> extends LabeledFormComponentPanel<T>
{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The text field. */
	private final PasswordTextField passwordTextField;

	/**
	 * Gets the password text field.
	 *
	 * @return the password text field
	 */
	public PasswordTextField getPasswordTextField()
	{
		return passwordTextField;
	}

	/**
	 * Instantiates a new LabeledPasswordTextFieldPanel.
	 * 
	 * @param id
	 *            the id
	 */
	public LabeledPasswordTextFieldPanel(String id)
	{
		this(id, null, null);
	}

	/**
	 * Instantiates a new LabeledPasswordTextFieldPanel.
	 *
	 * @param id
	 *            the id
	 * @param model
	 *            the model
	 * @param labelModel
	 *            the label model
	 */
	public LabeledPasswordTextFieldPanel(String id, IModel<T> model, IModel<String> labelModel)
	{
		super(id, model, labelModel);

		add(passwordTextField = newPasswordTextField("passwordTextField", model));

		add(feedback = newComponentFeedbackPanel("feedback", passwordTextField));

		String markupId = passwordTextField.getMarkupId();
		add(label = newLabel("label", markupId, getLabel()));
	}

	/**
	 * Factory method for creating the PasswordTextField. This method is invoked in the constructor
	 * from the derived classes and can be overridden so users can provide their own version of a
	 * PasswordTextField.
	 * 
	 * @param id
	 *            the id
	 * @param model
	 *            the model
	 * @return the text field
	 */
	protected PasswordTextField newPasswordTextField(String id, IModel<T> model)
	{
		PropertyModel<String> passwordTextFieldModel = new PropertyModel<String>(model.getObject(),
			getId());
		PasswordTextField passwordTextField = new PasswordTextField(id, passwordTextFieldModel);
		passwordTextField.setOutputMarkupId(true);
		return passwordTextField;
	}

	/**
	 * {@inheritDoc}
	 */
	public String getInput()
	{
		return passwordTextField.getInput();
	}

	/**
	 * {@inheritDoc}
	 */
	public void convertInput()
	{
		setConvertedInput(getModel().getObject());
	}

	/**
	 * {@inheritDoc}
	 */
	protected void onBeforeRender()
	{
		passwordTextField.setRequired(isRequired());
		super.onBeforeRender();
	}
}