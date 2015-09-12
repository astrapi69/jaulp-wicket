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
package de.alpharogroup.wicket.components.labeled.label;

import lombok.Getter;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;

import de.alpharogroup.wicket.base.BasePanel;
import de.alpharogroup.wicket.components.factory.ComponentFactory;

/**
 * Convenience class for labeled Label for form uneditable components.
 *
 * @param <T>
 *            the generic type of model object
 */
public class LabeledLabelPanel<T> extends BasePanel<T>
{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The left side Label component. */
	@Getter
	private final Label label;

	/** The right side label. */
	@Getter
	private final Label viewableLabel;

	/**
	 * Instantiates a new {@link LabeledLabelPanel}.
	 *
	 * @param id
	 *            the id
	 * @param model
	 *            the model
	 * @param labelModel
	 *            the label model
	 */
	public LabeledLabelPanel(final String id, final IModel<T> model, final IModel<String> labelModel)
	{
		super(id, model);
		setOutputMarkupId(true);
		add(viewableLabel = newViewableLabel("viewableLabel", model));

		final String markupId = viewableLabel.getMarkupId();
		add(label = newLabel("label", markupId, labelModel));
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
	protected Label newLabel(final String id, final String forId, final IModel<String> model)
	{
		return ComponentFactory.newLabel(id, forId, model);
	}

	/**
	 * Factory method for creating the new {@link Label}. This method is invoked in the constructor
	 * from the derived classes and can be overridden so users can provide their own version of a
	 * new {@link Label}.
	 *
	 * @param id
	 *            the id
	 * @param model
	 *            the model
	 * @return the new {@link Label}
	 */
	protected Label newViewableLabel(final String id, final IModel<T> model)
	{
		final PropertyModel<T> viewableLabelModel = new PropertyModel<>(model.getObject(),
			this.getId());
		return ComponentFactory.newLabel(id, viewableLabelModel);
	}
}
