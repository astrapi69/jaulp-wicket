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

import lombok.Getter;

import org.apache.wicket.Component;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;

import de.alpharogroup.wicket.components.factory.ComponentFactory;
import de.alpharogroup.wicket.components.labeled.LabeledFormComponentPanel;

/**
 * Convenience class for labeled {@link TextField}.
 *
 * @param <T>
 *            the generic type of model object
 */
public class LabeledTextFieldPanel<T> extends LabeledFormComponentPanel<T>
{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The {@link TextField}. */
	@Getter
	private final TextField<T> textField;

	/**
	 * Instantiates a new {@link LabeledTextFieldPanel}.
	 *
	 * @param id
	 *            the id
	 * @param model
	 *            the model
	 * @param labelModel
	 *            the label model
	 */
	public LabeledTextFieldPanel(final String id, final IModel<T> model,
		final IModel<String> labelModel)
	{
		super(id, model, labelModel);

		add(textField = newTextField("textField", model));

		add(feedback = newComponentFeedbackPanel("feedback", textField));

		final String markupId = textField.getMarkupId();
		add(label = newLabel("label", markupId, getLabel()));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void convertInput()
	{
		setConvertedInput(textField.getConvertedInput());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Component getFormComponent()
	{
		return this.textField;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getInput()
	{
		return textField.getInput();
	}

	/**
	 * Factory method for create the new {@link TextField}. This method is invoked in the
	 * constructor from the derived classes and can be overridden so users can provide their own
	 * version of a new {@link TextField}.
	 *
	 * @param id
	 *            the id
	 * @param model
	 *            the model
	 * @return the new {@link TextField}
	 */
	protected TextField<T> newTextField(final String id, final IModel<T> model)
	{
		return ComponentFactory.newTextField(id, new PropertyModel<T>(model.getObject(), getId()));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void onBeforeRender()
	{
		textField.setRequired(isRequired());
		super.onBeforeRender();
	}

}
