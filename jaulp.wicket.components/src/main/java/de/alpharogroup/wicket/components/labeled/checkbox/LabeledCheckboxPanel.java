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
package de.alpharogroup.wicket.components.labeled.checkbox;

import org.apache.wicket.markup.html.form.CheckBox;
import org.apache.wicket.markup.html.form.FormComponent;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;

import de.alpharogroup.wicket.components.factory.ComponentFactory;
import de.alpharogroup.wicket.components.labeled.LabeledFormComponentPanel;
import lombok.Getter;

/**
 * Convenience class for labeled {@link CheckBox}.
 *
 * @param <T>
 *            the generic type of model object from the {@link CheckBox}
 * @param <M>
 *            the generic type of model object
 */
public class LabeledCheckboxPanel<T, M> extends LabeledFormComponentPanel<T, M>
{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The CheckBox component. */
	@Getter
	private final CheckBox checkBox;

	/**
	 * Instantiates a new {@link LabeledCheckboxPanel}.
	 *
	 * @param id
	 *            the id
	 * @param model
	 *            the model
	 * @param labelModel
	 *            the model of the label
	 */
	public LabeledCheckboxPanel(final String id, final IModel<M> model,
		final IModel<String> labelModel)
	{
		super(id, model, labelModel);

		setOutputMarkupId(true);

		add(checkBox = newCheckBox("checkBox", model));

		add(feedback = newComponentFeedbackPanel("feedback", checkBox));

		final String markupId = checkBox.getMarkupId();
		add(label = newLabel("label", markupId, getLabel()));

	}

	/**
	 * Factory method for create a new {@link CheckBox}. This method is invoked in the constructor
	 * from this class and can be overridden so users can provide their own version of a new
	 * {@link CheckBox}.
	 *
	 * @param id
	 *            the id
	 * @param model
	 *            the model
	 * @return the new {@link CheckBox}.
	 */
	protected CheckBox newCheckBox(final String id, final IModel<M> model)
	{
		final IModel<Boolean> propertyModel = new PropertyModel<>(model.getObject(), this.getId());
		return ComponentFactory.newCheckBox(id, propertyModel);
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	@Override
	public FormComponent<T> getFormComponent()
	{
		return (FormComponent<T>)this.checkBox;
	}

}
