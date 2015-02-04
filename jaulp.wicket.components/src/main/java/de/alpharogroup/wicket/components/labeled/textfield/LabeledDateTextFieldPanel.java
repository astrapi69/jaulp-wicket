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
package de.alpharogroup.wicket.components.labeled.textfield;

import java.util.Date;
import java.util.Locale;

import org.apache.wicket.datetime.StyleDateConverter;
import org.apache.wicket.datetime.markup.html.form.DateTextField;
import org.apache.wicket.extensions.yui.calendar.DatePicker;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;
import de.alpharogroup.wicket.components.labeled.LabeledFormComponentPanel;

/**
 * Convenience class for labeled DateTextfield.
 *
 * @param <T>
 *            the generic type
 */
public class LabeledDateTextFieldPanel<T> extends LabeledFormComponentPanel<T>
{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The text field. */
	private final DateTextField dateTextField;

	public DateTextField getDateTextField()
	{
		return dateTextField;
	}

	/**
	 * Instantiates a new LabeledDateTextfieldPanel.
	 *
	 * @param id
	 *            the id
	 */
	public LabeledDateTextFieldPanel(String id)
	{
		this(id, null, null);
	}

	/**
	 * Instantiates a new LabeledDateTextfieldPanel.
	 *
	 * @param id
	 *            the id
	 * @param model
	 *            the model
	 * @param labelModel
	 *            the label model
	 */
	public LabeledDateTextFieldPanel(String id, IModel<T> model, IModel<String> labelModel)
	{
		super(id, model, labelModel);
		add(dateTextField = newDateTextField("dateTextField", model));

		add(feedback = newComponentFeedbackPanel("feedback", dateTextField));

		String markupId = dateTextField.getMarkupId();
		add(label = newLabel("label", markupId, getLabel()));
	}

	/**
	 * {@inheritDoc}
	 */
	public void convertInput()
	{
		setConvertedInput(getModel().getObject());
	}

	/**
	 * {@inheritDoc}
	 */
	public String getInput()
	{
		return dateTextField.getInput();
	}

	/**
	 * Factory method for creating the DateTextField. This method is invoked in the constructor from
	 * the derived classes and can be overridden so users can provide their own version of a
	 * DateTextField.
	 *
	 * @param id
	 *            the id
	 * @param model
	 *            the model
	 * @return the text field
	 */
	protected DateTextField newDateTextField(String id, IModel<T> model)
	{
		PropertyModel<Date> textFieldModel = new PropertyModel<Date>(model.getObject(), getId());

		DateTextField dateTextField = new DateTextField(id, textFieldModel, new StyleDateConverter(
			"S-", true))
		{
			/**
			 * The serialVersionUID.
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public Locale getLocale()
			{
				return getSession().getLocale();
			}
		};
		DatePicker datePicker = new DatePicker()
		{
			/**
			 * The serialVersionUID.
			 */
			private static final long serialVersionUID = 1L;

			protected boolean enableMonthYearSelection()
			{
				return true;
			}
		};
		datePicker.setShowOnFieldClick(true);
		dateTextField.add(datePicker);
		dateTextField.setOutputMarkupId(true);
		return dateTextField;
	}

	/**
	 * {@inheritDoc}
	 */
	protected void onBeforeRender()
	{
		dateTextField.setRequired(isRequired());
		super.onBeforeRender();
	}
}
