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

import org.apache.wicket.markup.html.form.FormComponent;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import de.alpharogroup.wicket.components.factory.ComponentFactory;
import de.alpharogroup.wicket.components.form.input.TwoFormComponentBean;
import de.alpharogroup.wicket.components.form.input.TwoFormComponentPanel;
import lombok.Getter;

/**
 * The Class LabeledTwoFormComponentPanel is a container for two FormComponent. Default they are
 * TextField objects but can be overwritten by the factory methods to return any other input field.
 *
 * @param <L>
 *            the generic type of the model from the left FormComponent
 * @param <R>
 *            the generic type of the model from the left FormComponent
 */
public class LabeledTwoFormComponentPanel<L extends Serializable, R extends Serializable, M>
	extends
		LabeledFormComponentPanel<TwoFormComponentBean<L, R>, M>
{

	/** The serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * The left text field.
	 */
	@Getter
	private TwoFormComponentPanel<L, R> twoFormComponent;

	@Getter
	private final IModel<TwoFormComponentBean<L, R>> formComponentModel;

	/**
	 * Instantiates a new {@link LabeledTwoFormComponentPanel}.
	 *
	 * @param id
	 *            the id
	 * @param model
	 *            the model
	 * @param labelModel
	 *            the model of the label
	 */
	public LabeledTwoFormComponentPanel(final String id,
		final IModel<M> model, final IModel<TwoFormComponentBean<L, R>> formComponentModel, final IModel<String> labelModel)
	{
		super(id, model, labelModel);
		this.formComponentModel = formComponentModel;
		add(twoFormComponent = newTwoFormComponentPanel("twoFormComponent", model, formComponentModel));

		add(feedback = newComponentFeedbackPanel("feedback", twoFormComponent));

		final String markupId = twoFormComponent.getMarkupId();
		add(label = newLabel("label", markupId, getLabel()));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public FormComponent<TwoFormComponentBean<L, R>> getFormComponent()
	{
		return twoFormComponent;
	}

	/**
	 * Factory method for create a new {@link IModel} for the Label of what characters will be
	 * between the two components.
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
	 * Factory method for create the new left {@link FormComponent}. This method is invoked in the
	 * constructor from the derived classes and can be overridden so users can provide their own
	 * version of a new left {@link FormComponent}.
	 *
	 * @param id
	 *            the id
	 * @param model
	 *            the model
	 * @return the new left {@link FormComponent}
	 */
	public FormComponent<L> newLeftFormComponent(final String id, final IModel<L> model)
	{
		return ComponentFactory.newTextField(id, model);
	}

	/**
	 * Factory method for create the new right {@link FormComponent}. This method is invoked in the
	 * constructor from the derived classes and can be overridden so users can provide their own
	 * version of a new right {@link FormComponent}.
	 *
	 * @param id
	 *            the id
	 * @param model
	 *            the model
	 * @return the new right {@link FormComponent}
	 */
	public FormComponent<R> newRightFormComponent(final String id, final IModel<R> model)
	{
		return ComponentFactory.newTextField(id, model);
	}

	/**
	 * Factory method for create the new {@link TwoFormComponentPanel}. This method is invoked in
	 * the constructor from the derived classes and can be overridden so users can provide their own
	 * version of a new {@link TwoFormComponentPanel}.
	 *
	 * @param id
	 *            the id
	 * @param model
	 *            the model
	 * @return the new {@link TwoFormComponentPanel}
	 */
	protected TwoFormComponentPanel<L, R> newTwoFormComponentPanel(final String id,
		final IModel<M> model, final IModel<TwoFormComponentBean<L, R>> formComponentModel) {
		final TwoFormComponentPanel<L, R> twoFormComponent = new TwoFormComponentPanel<L, R>(id, formComponentModel);
		return twoFormComponent;
	}

}
