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

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.Component;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.FormComponent;
import org.apache.wicket.markup.html.form.FormComponentPanel;
import org.apache.wicket.markup.html.panel.ComponentFeedbackPanel;
import org.apache.wicket.model.IModel;

import de.alpharogroup.wicket.components.factory.ComponentFactory;
import lombok.Getter;

/**
 * The LabeledFormComponentPanel is base class for labeled components.
 *
 * @param <T>
 *            the generic type of model object from the {@link FormComponent}
 * @param <M>
 *            the generic type of model object
 *
 * @see org.apache.wicket.markup.html.form.FormComponentPanel
 */
public abstract class LabeledFormComponentPanel<T, M> extends FormComponentPanel<M>
{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The Label component. */
	protected Component label;

	/** The ComponentFeedbackPanel for validation information. */
	@Getter
	protected ComponentFeedbackPanel feedback;

	/**
	 * Instantiates a new {@link LabeledFormComponentPanel}.
	 *
	 * @param id
	 *            the id
	 * @param model
	 *            the model
	 * @param labelModel
	 *            the label model
	 */
	public LabeledFormComponentPanel(final String id, final IModel<M> model,
		final IModel<String> labelModel)
	{
		super(id, model);
		setLabel(labelModel);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void convertInput()
	{

		final M modelObject = getModel().getObject();
		setConvertedInput(modelObject);
	}

	/**
	 * Abstract method for get the form component.
	 *
	 * @return the form component
	 */
	public abstract FormComponent<T> getFormComponent();

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getInput()
	{
		return getFormComponent().getInput();
	}

	/**
	 * Gets the label component.
	 *
	 * @return the label component
	 */
	public Component getLabelComponent()
	{
		return this.label;
	}

	/**
	 * Factory method for creating the new {@link ComponentFeedbackPanel}. This method is invoked in
	 * the constructor from the derived classes and can be overridden so users can provide their own
	 * version of a new {@link ComponentFeedbackPanel}.
	 *
	 * @param id
	 *            the id
	 * @param filter
	 *            the filter
	 * @return the new {@link ComponentFeedbackPanel}
	 */
	protected ComponentFeedbackPanel newComponentFeedbackPanel(final String id,
		final Component filter)
	{
		return ComponentFactory.newComponentFeedbackPanel(id, filter);
	}

	/**
	 * Factory method for creating the new {@link Label}. This method is invoked in the constructor
	 * from the derived classes and can be overridden so users can provide their own version of a
	 * new {@link Label}.
	 *
	 * @param id
	 *            the id
	 * @param forId
	 *            the for id
	 * @param model
	 *            the model
	 * @return the new {@link Label}
	 */
	protected Component newLabel(final String id, final String forId, final IModel<String> model)
	{
		return ComponentFactory.newLabel(id, forId, model);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void onBeforeRender()
	{
		if (isRequired())
		{
			getFormComponent().add(new AttributeModifier("required", "required"));
		}
		getFormComponent().setRequired(isRequired());
		super.onBeforeRender();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void onModelChanged()
	{
		super.onModelChanged();
		getFormComponent().modelChanged();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void onModelChanging()
	{
		super.onModelChanging();
		getFormComponent().modelChanging();
	}
}
