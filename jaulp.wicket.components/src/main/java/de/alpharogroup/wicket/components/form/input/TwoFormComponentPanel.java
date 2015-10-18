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
package de.alpharogroup.wicket.components.form.input;

import java.io.Serializable;

import lombok.Getter;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.FormComponent;
import org.apache.wicket.markup.html.form.FormComponentPanel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;

import de.alpharogroup.wicket.components.factory.ComponentFactory;

/**
 * The Class {@link TwoFormComponentPanel} is a container for two FormComponent. Default they are
 * TextField objects but can be overwritten by the factory methods to return any other input field.
 *
 * @param <L>
 *            the generic type of the model from the left FormComponent
 * @param <R>
 *            the generic type of the model from the left FormComponent
 */
public class TwoFormComponentPanel<L extends Serializable, R extends Serializable>
	extends
		FormComponentPanel<TwoFormComponentBean<L, R>>
{

	/** The serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * The left text field.
	 */
	@Getter
	private FormComponent<L> leftFormComponent;

	/**
	 * The right text field.
	 */
	@Getter
	private FormComponent<R> rightFormComponent;

	/**
	 * Instantiates a new {@link TwoFormComponentPanel}.
	 *
	 * @param id
	 *            the id
	 */
	public TwoFormComponentPanel(final String id)
	{
		this(id, null);
	}

	/**
	 * Instantiates a new {@link TwoFormComponentPanel}.
	 *
	 * @param id
	 *            the id
	 * @param model
	 *            the model
	 */
	public TwoFormComponentPanel(final String id, final IModel<TwoFormComponentBean<L, R>> model)
	{
		super(id, model);
		setOutputMarkupId(true);
		setType(TwoFormComponentBean.class);
		add(
			leftFormComponent = newLeftFormComponent("leftTextField", new PropertyModel<L>(model,
				"leftContent"))).add(
			rightFormComponent = newRightFormComponent("rightTextField", new PropertyModel<R>(
				model, "rightContent")));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void convertInput()
	{
		setConvertedInput(getModelObject());
	}

	/**
	 * Factory method for create a new Label with a {@link IModel}.
	 *
	 * @param id
	 *            the id
	 * @param model
	 *            the {@link IModel} for the label.
	 * @return the label
	 */
	protected Label newBetweenLabel(final String id, final IModel<String> model)
	{
		return ComponentFactory.newLabel(id, model);
	}

	/**
	 * Factory method for create a new {@link IModel} for what characters will be between the two
	 * components.
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
	 * Factory method for creating the new left {@link FormComponent}. This method is invoked in the
	 * constructor from the derived classes and can be overridden so users can provide their own
	 * version of a new left {@link FormComponent}.
	 *
	 * @param id
	 *            the id
	 * @param model
	 *            the model
	 * @return the new left {@link FormComponent}.
	 */
	protected FormComponent<L> newLeftFormComponent(final String id, final IModel<L> model)
	{
		return ComponentFactory.newTextField(id, model);
	}

	/**
	 * Factory method for creating the new right {@link FormComponent}. This method is invoked in
	 * the constructor from the derived classes and can be overridden so users can provide their own
	 * version of a new right {@link FormComponent}.
	 *
	 * @param id
	 *            the id
	 * @param model
	 *            the model
	 * @return the new right {@link FormComponent}.
	 */
	protected FormComponent<R> newRightFormComponent(final String id, final IModel<R> model)
	{
		return ComponentFactory.newTextField(id, model);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void onModelChanged()
	{
		super.onModelChanged();
		leftFormComponent.modelChanged();
		rightFormComponent.modelChanged();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void onModelChanging()
	{
		super.onModelChanging();
		leftFormComponent.modelChanging();
		rightFormComponent.modelChanging();
	}

}
