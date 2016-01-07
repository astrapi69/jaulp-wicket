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
import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.IChoiceRenderer;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;

import de.alpharogroup.wicket.components.labeled.LabeledFormComponentPanel;

/**
 * The Class EnumLocalizedDropdownChoicePanel.
 *
 * @param <T>
 *            the generic type for the enum
 * @param <M>
 *            the generic type for the model
 */
public class EnumLocalizedDropdownChoicePanel<T extends Enum<T>, M>
	extends
		LabeledFormComponentPanel<M>
{

	/** The serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * the dropdown choice
	 */
	@Getter
	private final DropDownChoice<T> dropdownChoice;

	/**
	 * Instantiates a new {@link EnumLocalizedDropdownChoicePanel}.
	 *
	 * @param id
	 *            the id
	 * @param model
	 *            the model
	 * @param labelModel
	 *            the label model
	 * @param enumValues
	 *            the enum values
	 */
	public EnumLocalizedDropdownChoicePanel(final String id, final IModel<M> model,
		final IModel<String> labelModel, final List<T> enumValues)
	{
		super(id, model, labelModel);
		setDefaultModel(model);
		final ChoiceRenderer<T> choiceRenderer = new ChoiceRenderer<>("name", "name");

		add(dropdownChoice = newLocalisedDropDownChoice("dropdownChoice", model, enumValues,
			choiceRenderer));

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
		final M modelObject = getModel().getObject();
		setConvertedInput(modelObject);
	}

	/**
	 * {@inheritDoc}
	 */
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
	 * @param renderer
	 *            the renderer
	 * @return the new {@link DropDownChoice}
	 */
	protected DropDownChoice<T> newLocalisedDropDownChoice(final String id, final IModel<M> model,
		final List<? extends T> data, final IChoiceRenderer<? super T> renderer)
	{
		final PropertyModel<T> pm = new PropertyModel<>(model.getObject(), this.getId());
		final DropDownChoice<T> ddc = new LocalisedDropDownChoice<>(id, pm, data, renderer);
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
