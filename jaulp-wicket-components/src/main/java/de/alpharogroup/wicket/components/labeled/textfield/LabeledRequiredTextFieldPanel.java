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

import org.apache.wicket.markup.html.form.FormComponent;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;

import de.alpharogroup.wicket.components.factory.ComponentFactory;
import de.alpharogroup.wicket.components.labeled.LabeledFormComponentPanel;
import lombok.Getter;

/**
 * Convenience class for labeled {@link RequiredTextField}.
 *
 * @param <T>
 *            the generic type of model object from the {@link RequiredTextField}
 * @param <M>
 *            the generic type of model object
 */
public class LabeledRequiredTextFieldPanel<T, M> extends LabeledFormComponentPanel<T, M>
{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The {@link RequiredTextField}. */
	@Getter
	private final RequiredTextField<T> textField;

	/**
	 * Instantiates a new {@link LabeledRequiredTextFieldPanel}.
	 *
	 * @param id
	 *            the id
	 * @param model
	 *            the model
	 * @param labelModel
	 *            the label model
	 */
	public LabeledRequiredTextFieldPanel(final String id, final IModel<M> model,
		final IModel<String> labelModel)
	{
		super(id, model, labelModel);

		setOutputMarkupId(true);

		add(textField = newRequiredTextField("textField", model));

		add(feedback = newComponentFeedbackPanel("feedback", textField));

		final String markupId = textField.getMarkupId();
		add(label = newLabel("label", markupId, getLabel()));
	}

	/**
	 * Factory method for create the new {@link RequiredTextField}. This method is invoked in the
	 * constructor from the derived classes and can be overridden so users can provide their own
	 * version of a new {@link RequiredTextField}.
	 *
	 * @param id
	 *            the id
	 * @param model
	 *            the model
	 * @return the new {@link RequiredTextField}
	 */
	protected RequiredTextField<T> newRequiredTextField(final String id, final IModel<M> model)
	{
		return ComponentFactory.newRequiredTextField(id, new PropertyModel<T>(model.getObject(),
			getId()));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public FormComponent<T> getFormComponent()
	{
		return this.textField;
	}

}
