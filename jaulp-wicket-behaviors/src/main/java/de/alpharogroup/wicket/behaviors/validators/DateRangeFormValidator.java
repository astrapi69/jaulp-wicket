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
package de.alpharogroup.wicket.behaviors.validators;

import java.util.Date;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.FormComponent;
import org.apache.wicket.markup.html.form.validation.AbstractFormValidator;
import org.apache.wicket.util.lang.Args;

/**
 * The class {@link DateRangeFormValidator} can validate two {@link Date} objects from the two
 * {@link FormComponent}'s given to this validator.
 * 
 * <br><br><br>
 * Example: <br><br>
 * <code>
 * 		form.add(new DateRangeFormValidator(startDateFormComponent, endDateFormComponent));
 * </code>
 */
public class DateRangeFormValidator extends AbstractFormValidator
{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The form component that represents the start date . */
	private final FormComponent<Date> startDateFormComponent;

	/** The form component that represents the end date . */
	private final FormComponent<Date> endDateFormComponent;

	/**
	 * Instantiates a new {@link DateRangeFormValidator}.
	 *
	 * @param startDateFormComponent
	 *            the start date form component
	 * @param endDateFormComponent
	 *            the end date form component
	 */
	public DateRangeFormValidator(final FormComponent<Date> startDateFormComponent,
		final FormComponent<Date> endDateFormComponent)
	{
		this.startDateFormComponent = Args.notNull(startDateFormComponent,
			"startDateFormComponent");
		this.endDateFormComponent = Args.notNull(endDateFormComponent, "endDateFormComponent");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public FormComponent<?>[] getDependentFormComponents()
	{
		return new FormComponent[] { startDateFormComponent, endDateFormComponent };
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void validate(Form<?> form)
	{
		Date startDate = startDateFormComponent.getConvertedInput();
		Date endDate = endDateFormComponent.getConvertedInput();

		if (endDate.before(startDate))
		{
			error(endDateFormComponent, resourceKey());
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected String resourceKey()
	{
		return "global.validation.error.enddate.before.startdate";
	}

}
