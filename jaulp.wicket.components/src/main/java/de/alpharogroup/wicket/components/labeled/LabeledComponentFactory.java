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
	 *            the generic type
	 * @param id
	 *            the id
	 * @param model
	 *            the model
	 * @param labelModel
	 *            the label model
	 * @return the new {@link LabeledCheckboxPanel}
	 */
	public static <T> LabeledCheckboxPanel<T> newLabeledCheckboxPanel(String id, IModel<T> model,
		IModel<String> labelModel)
	{
		LabeledCheckboxPanel<T> labeledCheckboxPanel = new LabeledCheckboxPanel<T>(id, model,
			labelModel);
		labeledCheckboxPanel.setOutputMarkupId(true);
		return labeledCheckboxPanel;

	}

	/**
	 * Factory method for create a new {@link LabeledTextFieldPanel}.
	 *
	 * @param <T>
	 *            the generic type
	 * @param id
	 *            the id
	 * @param model
	 *            the model
	 * @param labelModel
	 *            the label model
	 * @return the new {@link LabeledTextFieldPanel}
	 */
	public static <T> LabeledTextFieldPanel<T> newLabeledTextFieldPanel(String id, IModel<T> model,
		IModel<String> labelModel)
	{
		LabeledTextFieldPanel<T> labeledTextField = new LabeledTextFieldPanel<T>(id, model,
			labelModel);
		labeledTextField.setOutputMarkupId(true);
		return labeledTextField;
	}

	/**
	 * Factory method for create a new {@link TwoFormComponentPanel}.
	 *
	 * @param <L> the generic type
	 * @param <R> the generic type
	 * @param id the id
	 * @param model the model
	 * @return the new {@link TwoFormComponentPanel}
	 */
	public static <L extends Serializable, R extends Serializable> TwoFormComponentPanel<L, R> newTwoFormComponentPanel(
		String id, IModel<TwoFormComponentBean<L, R>> model)
	{
		TwoFormComponentPanel<L, R> twoFormComponentPanel = new TwoFormComponentPanel<L, R>(id,
			model);
		return twoFormComponentPanel;
	}

}
