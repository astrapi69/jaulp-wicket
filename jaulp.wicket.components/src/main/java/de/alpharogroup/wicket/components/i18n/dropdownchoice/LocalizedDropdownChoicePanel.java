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
package de.alpharogroup.wicket.components.i18n.dropdownchoice;

import java.util.List;

import lombok.Getter;

import org.apache.wicket.Component;
import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;

import de.alpharogroup.wicket.components.labeled.LabeledFormComponentPanel;

/**
 * The Class LocalizedDropdownChoicePanel.
 *
 * @param <T>
 *            the generic type of the model
 */
public class LocalizedDropdownChoicePanel<T> extends LabeledFormComponentPanel<T>
{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The dropdown choice. */
	@Getter
	private final DropDownChoice<T> dropdownChoice;

	/**
	 * Instantiates a new {@link LocalizedDropdownChoicePanel}.
	 *
	 * @param id
	 *            the id
	 * @param model
	 *            the selected value
	 * @param labelModel
	 *            the label model
	 * @param values
	 *            the values
	 */
	public LocalizedDropdownChoicePanel(final String id, final IModel<T> model,
		final IModel<String> labelModel, final List<T> values)
	{
		super(id, model, labelModel);

		add(dropdownChoice = newDropDownChoice("dropdownChoice", model, values));

		add(feedback = newComponentFeedbackPanel("feedback", dropdownChoice));

		final String markupId = dropdownChoice.getMarkupId();
		add(label = newLabel("label", markupId, getLabel()));

		// Add bootstrap css...
		getLabelComponent().add(new AttributeAppender("class", "control-label"));

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void convertInput()
	{
		setConvertedInput(dropdownChoice.getConvertedInput());
	}

	@Override
	public Component getFormComponent()
	{
		return dropdownChoice;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getInput()
	{
		return dropdownChoice.getInput();
	}

	/**
	 * Factory method for creating the new {@link DropDownChoice}. This method is invoked in the
	 * constructor from the derived classes and can be overridden so users can provide their own
	 * version of a new {@link DropDownChoice}.
	 *
	 * @param id
	 *            the id
	 * @param model
	 *            the model
	 * @param data
	 *            the data
	 * @return the new {@link DropDownChoice}
	 */
	protected DropDownChoice<T> newDropDownChoice(final String id, final IModel<T> model,
		final List<? extends T> data)
	{
		final PropertyModel<T> pm = new PropertyModel<>(model.getObject(), this.getId());
		final DropDownChoice<T> ddc = new LocalisedDropDownChoice<>(id, pm, data);
		return ddc;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void onBeforeRender()
	{
		dropdownChoice.setRequired(isRequired());
		super.onBeforeRender();
	}

}
