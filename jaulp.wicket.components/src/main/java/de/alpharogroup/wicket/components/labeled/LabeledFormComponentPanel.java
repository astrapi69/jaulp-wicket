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

import org.apache.wicket.Component;
import org.apache.wicket.markup.html.form.FormComponentPanel;
import org.apache.wicket.markup.html.panel.ComponentFeedbackPanel;
import org.apache.wicket.model.IModel;

import de.alpharogroup.wicket.components.factory.ComponentFactory;

/**
 * The LabeledFormComponentPanel is base class for labeled components.
 * 
 * @param <T>
 *            the generic type
 * @see FormComponentPanel
 */
public abstract class LabeledFormComponentPanel<T> extends
		FormComponentPanel<T> {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The Label component. */
	protected Component label;

	/** The ComponentFeedbackPanel for validation information. */
	protected ComponentFeedbackPanel feedback;

	/**
	 * Instantiates a new LabeledFormComponentPanel object.
	 * 
	 * @param id
	 *            the id
	 */
	public LabeledFormComponentPanel(String id) {
		super(id);
	}

	/**
	 * Instantiates a new LabeledFormComponentPanel object.
	 *
	 * @param id the id
	 * @param model the model
	 * @param labelModel the label model
	 */
	public LabeledFormComponentPanel(String id, IModel<T> model, IModel<String> labelModel) {
		super(id, model);
		setLabel(labelModel);
	}

	/**
	 * Gets the label component.
	 *
	 * @return the label component
	 */
	public Component getLabelComponent() {
		return label;
	}

	/**
	 * Factory method for creating the ComponentFeedbackPanel. This method is
	 * invoked in the constructor from the derived classes and can be overridden
	 * so users can provide their own version of a ComponentFeedbackPanel.
	 * 
	 * @param id
	 *            the id
	 * @param filter
	 *            the filter
	 * @return the component feedback panel
	 */
	protected ComponentFeedbackPanel newComponentFeedbackPanel(String id,
			Component filter) {
		return ComponentFactory.newComponentFeedbackPanel(id, filter);
	}

	/**
	 * Factory method for creating the Label. This method is invoked in the
	 * constructor from the derived classes and can be overridden so users can
	 * provide their own version of a Label.
	 * 
	 * @param id
	 *            the id
	 * @param forId
	 *            the for id
	 * @param model
	 *            the model
	 * @return the label
	 */
	protected Component newLabel(String id, String forId, IModel<String> model) {
		return ComponentFactory.newLabel(id, forId, model);
	}
}
