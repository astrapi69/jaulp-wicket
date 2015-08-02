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
package de.alpharogroup.wicket.components.labeled;

import java.io.Serializable;

import lombok.Getter;

import org.apache.wicket.markup.html.form.FormComponent;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import de.alpharogroup.wicket.components.factory.ComponentFactory;
import de.alpharogroup.wicket.components.form.input.TwoFormComponentBean;
import de.alpharogroup.wicket.components.form.input.TwoFormComponentPanel;

/**
 * The Class LabeledTwoFormComponentPanel is a container for two FormComponent. Default they are
 * TextField objects but can be overwritten by the factory methods to return any other input field.
 *
 * @param <L>
 *            the generic type of the model from the left FormComponent
 * @param <R>
 *            the generic type of the model from the left FormComponent
 */
public class LabeledTwoFormComponentPanel<L extends Serializable, R extends Serializable>
	extends
		LabeledFormComponentPanel<TwoFormComponentBean<L, R>>
{

	/** The serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * The left text field.
	 */
	@Getter
	private TwoFormComponentPanel<L, R> twoFormComponent;


	/**
	 * Instantiates a new two text field panel.
	 *
	 * @param id
	 *            the id
	 * @param labelModel
	 *            the model for the label
	 */
	public LabeledTwoFormComponentPanel(final String id, final IModel<String> labelModel)
	{
		this(id, Model.of(new TwoFormComponentBean<L, R>()), labelModel);
	}

	/**
	 * Instantiates a new two text field panel.
	 *
	 * @param id
	 *            the id
	 * @param model
	 *            the model
	 * @param labelModel
	 *            the model of the label
	 */
	public LabeledTwoFormComponentPanel(final String id,
		final IModel<TwoFormComponentBean<L, R>> model, final IModel<String> labelModel)
	{
		super(id, model, labelModel);
		add(twoFormComponent = newTwoFormComponentPanel("twoFormComponent", model));

		add(feedback = newComponentFeedbackPanel("feedback", twoFormComponent));

		final String markupId = twoFormComponent.getMarkupId();
		add(label = newLabel("label", markupId, getLabel()));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void convertInput()
	{
		setConvertedInput(getModelObject());
	}

	/**
	 * Factory method for create a new Label for what characters will be between the two components.
	 *
	 * @param betweenLabel
	 *            the characters
	 * @return the {@link IModel} with the characters.
	 */
	protected IModel<String> newBetweenLabelModel(final String betweenLabel)
	{
		return Model.of(betweenLabel);
	}

	/**
	 * New left text field.
	 *
	 * @param id
	 *            the id
	 * @param model
	 *            the model
	 * @return the form component
	 */
	protected FormComponent<L> newLeftFormComponent(final String id, final IModel<L> model)
	{
		return ComponentFactory.newTextField(id, model);
	}

	/**
	 * New right text field.
	 *
	 * @param id
	 *            the id
	 * @param model
	 *            the model
	 * @return the form component
	 */
	protected FormComponent<R> newRightFormComponent(final String id, final IModel<R> model)
	{
		return ComponentFactory.newTextField(id, model);
	}

	/**
	 * New left text field.
	 *
	 * @param id
	 *            the id
	 * @param model
	 *            the model
	 * @return the form component
	 */
	protected TwoFormComponentPanel<L, R> newTwoFormComponentPanel(final String id,
		final IModel<TwoFormComponentBean<L, R>> model)
	{
		final TwoFormComponentPanel<L, R> twoFormComponentPanel = new TwoFormComponentPanel<L, R>(
			id, model)
		{

			/**
			 * The serialVersionUID
			 */
			private static final long serialVersionUID = 1L;

			@Override
			protected IModel<String> newBetweenLabelModel(final String betweenLabel)
			{
				return LabeledTwoFormComponentPanel.this.newBetweenLabelModel(betweenLabel);
			}

			@Override
			protected FormComponent<L> newLeftFormComponent(final String id, final IModel<L> model)
			{
				return LabeledTwoFormComponentPanel.this.newLeftFormComponent(id, model);
			}

			@Override
			protected FormComponent<R> newRightFormComponent(final String id, final IModel<R> model)
			{
				return LabeledTwoFormComponentPanel.this.newRightFormComponent(id, model);
			}

		};
		return twoFormComponentPanel;
	}

}
