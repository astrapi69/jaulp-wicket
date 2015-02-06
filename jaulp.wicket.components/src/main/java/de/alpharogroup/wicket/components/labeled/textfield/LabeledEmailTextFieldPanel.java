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

import org.apache.wicket.markup.html.form.EmailTextField;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;

import de.alpharogroup.wicket.components.factory.ComponentFactory;
import de.alpharogroup.wicket.components.labeled.LabeledFormComponentPanel;

/**
 * Convenience class for labeled TextField.
 *
 * @param <T>
 *            the generic type
 */
public class LabeledEmailTextFieldPanel<T> extends LabeledFormComponentPanel<T>
{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The EmailTextField. */
	private final EmailTextField emailTextField;

	/**
	 * Instantiates a new LabeledEmailTextFieldPanel.
	 *
	 * @param id
	 *            the id
	 */
	public LabeledEmailTextFieldPanel(String id)
	{
		this(id, null, null);
	}

	/**
	 * Instantiates a new LabeledEmailTextFieldPanel.
	 *
	 * @param id
	 *            the id
	 * @param model
	 *            the model
	 * @param labelModel
	 *            the label model
	 */
	public LabeledEmailTextFieldPanel(String id, IModel<T> model, IModel<String> labelModel)
	{
		super(id, model, labelModel);

		add(emailTextField = newEmailTextField("emailTextField", model));

		add(feedback = newComponentFeedbackPanel("feedback", emailTextField));

		String markupId = emailTextField.getMarkupId();
		add(label = newLabel("label", markupId, getLabel()));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void convertInput()
	{
		setConvertedInput(getModel().getObject());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getInput()
	{
		return emailTextField.getInput();
	}

	/**
	 * Gets the EmailTextField.
	 *
	 * @return the EmailTextField
	 */
	public EmailTextField getEmailTextField()
	{
		return emailTextField;
	}

	/**
	 * Factory method for creating the EmailTextField. This method is invoked in the constructor
	 * from the derived classes and can be overridden so users can provide their own version of a
	 * EmailTextField.
	 *
	 * @param id
	 *            the id
	 * @param model
	 *            the model
	 * @return the text field
	 */
	protected EmailTextField newEmailTextField(String id, IModel<T> model)
	{
		return ComponentFactory.newEmailTextField(id, new PropertyModel<String>(model.getObject(),
			getId()));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void onBeforeRender()
	{
		emailTextField.setRequired(isRequired());
		super.onBeforeRender();
	}
}
