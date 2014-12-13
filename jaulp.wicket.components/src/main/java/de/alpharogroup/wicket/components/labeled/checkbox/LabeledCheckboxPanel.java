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
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;

import de.alpharogroup.wicket.components.factory.ComponentFactory;
import de.alpharogroup.wicket.components.labeled.LabeledFormComponentPanel;

/**
 * Convenience class for labeled checkbox.
 */
public class LabeledCheckboxPanel<T> extends LabeledFormComponentPanel<T> {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
		
	/** The CheckBox component. */
	private CheckBox checkBox;

	/**
	 * Instantiates a new LabeledCheckboxPanel object.
	 *
	 * @param id the id
	 * @param model the model
	 */
	public LabeledCheckboxPanel(String id, IModel<T> model, IModel<String> labelModel) {
		super(id, model, labelModel);
		PropertyModel<Boolean> propertyModel = new PropertyModel<Boolean>(model.getObject(), id);
        add(checkBox = newCheckBox("checkBox", propertyModel));

		add(feedback = newComponentFeedbackPanel("feedback", checkBox));

		String markupId = checkBox.getMarkupId();
		add(label = newLabel("label", markupId, getLabel()));
  
	}
	
	/** 
	 * Factory method for creating the CheckBox. This method is invoked in the
	 * constructor from this class and can be overridden so users can
	 * provide their own version of a CheckBox.
	 *
	 * @param id the id
	 * @param model the model
	 * @return the created CheckBox
	 */
	protected CheckBox newCheckBox(String id, IModel<Boolean> model) {
		return ComponentFactory.newCheckBox(id, model);
	}

	/**
	 * {@inheritDoc}
	 */
	public String getInput() {
		return checkBox.getInput();
	}

	/**
	 * {@inheritDoc}
	 */
	public void convertInput() {
		setConvertedInput(getModel().getObject());
	}

	/**
	 * {@inheritDoc}
	 */
	protected void onBeforeRender() {
		checkBox.setRequired(isRequired());
		super.onBeforeRender();
	}

}
