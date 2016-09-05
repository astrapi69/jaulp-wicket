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

import org.apache.wicket.markup.html.form.CheckBox;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.IModel;

import de.alpharogroup.wicket.components.form.input.TwoFormComponentBean;
import de.alpharogroup.wicket.components.form.input.TwoFormComponentPanel;
import de.alpharogroup.wicket.components.labeled.checkbox.LabeledCheckboxPanel;
import de.alpharogroup.wicket.components.labeled.textfield.LabeledTextFieldPanel;

/**
 * A factory for creating LabeledComponent objects.
 */
public class LabeledComponentFactory
{

	/**
	 * Factory method for create a new {@link LabeledCheckboxPanel}.
	 *
	 * @param <T>
	 *            the generic type of model object from the {@link CheckBox}
	 * @param <M>
	 *            the generic type of model object
	 * @param id
	 *            the id
	 * @param model
	 *            the model
	 * @param labelModel
	 *            the label model
	 * @return the new {@link LabeledCheckboxPanel}
	 */
	public static <T, M> LabeledCheckboxPanel<T, M> newLabeledCheckboxPanel(final String id,
		final IModel<M> model, final IModel<String> labelModel)
	{
		final LabeledCheckboxPanel<T, M> labeledCheckboxPanel = new LabeledCheckboxPanel<>(id,
			model, labelModel);
		labeledCheckboxPanel.setOutputMarkupId(true);
		return labeledCheckboxPanel;

	}

	/**
	 * Factory method for create a new {@link LabeledTextFieldPanel}.
	 *
	 * @param <T>
	 *            the generic type of model object from the {@link TextField}
	 * @param <M>
	 *            the generic type of model object
	 * @param id
	 *            the id
	 * @param model
	 *            the model
	 * @param labelModel
	 *            the label model
	 * @return the new {@link LabeledTextFieldPanel}
	 */
	public static <T, M> LabeledTextFieldPanel<T, M> newLabeledTextFieldPanel(final String id,
		final IModel<M> model, final IModel<String> labelModel)
	{
		final LabeledTextFieldPanel<T, M> labeledTextField = new LabeledTextFieldPanel<>(id, model,
			labelModel);
		labeledTextField.setOutputMarkupId(true);
		return labeledTextField;
	}

	/**
	 * Factory method for create a new {@link TwoFormComponentPanel}.
	 *
	 * @param <L>
	 *            the generic type
	 * @param <R>
	 *            the generic type
	 * @param id
	 *            the id
	 * @param model
	 *            the model
	 * @return the new {@link TwoFormComponentPanel}
	 */
	public static <L extends Serializable, R extends Serializable> TwoFormComponentPanel<L, R> newTwoFormComponentPanel(
		final String id, final IModel<TwoFormComponentBean<L, R>> model)
	{
		final TwoFormComponentPanel<L, R> twoFormComponentPanel = new TwoFormComponentPanel<>(id,
			model);
		return twoFormComponentPanel;
	}

}
