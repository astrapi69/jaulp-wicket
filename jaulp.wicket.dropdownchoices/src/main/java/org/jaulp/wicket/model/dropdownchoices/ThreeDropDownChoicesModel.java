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
package org.jaulp.wicket.model.dropdownchoices;

import java.util.List;
import java.util.Map;

/**
 * The Class ThreeDropDownChoicesModel.
 *
 * @param <T>
 *            the generic type
 * @author Asterios Raptis
 */
public class ThreeDropDownChoicesModel<T> extends TwoDropDownChoicesModel<T>
{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The selected option. */
	private T selectedValueOption;

	/** The child choices. */
	private List<T> selectedValuesChoices;

	/**
	 * Instantiates a new generic three drop down choices model.
	 *
	 * @param selectedOption
	 *            the selected option
	 * @param modelsMap
	 *            the models map
	 * @param selectedValuesChoices
	 *            the selected values choices
	 */
	public ThreeDropDownChoicesModel(final T selectedOption, final Map<T, List<T>> modelsMap,
		final List<T> selectedValuesChoices)
	{
		super(selectedOption, modelsMap);
		this.selectedValuesChoices = selectedValuesChoices;
	}

	/**
	 * Adds the selected value.
	 *
	 * @param selectedValue
	 *            the selected value
	 * @return true, if successful
	 */
	public boolean addSelectedValue(final T selectedValue)
	{
		List<T> selectedValues = getSelectedValuesChoicesObject();
		return selectedValues.add(selectedValue);
	}

	/**
	 * Contains selected value.
	 *
	 * @param selectedValue
	 *            the selected value
	 * @return true, if successful
	 */
	public boolean containsSelectedValue(final T selectedValue)
	{
		return getSelectedValuesChoicesObject().contains(selectedValue);
	}

	/**
	 * Gets the selected value option.
	 *
	 * @return the selected value option
	 */
	public T getSelectedValueOption()
	{
		return selectedValueOption;
	}

	/**
	 * Gets the selected values choices.
	 *
	 * @return the selected values choices
	 */
	public List<T> getSelectedValuesChoices()
	{
		return selectedValuesChoices;
	}

	/**
	 * Gets the selected values choices object.
	 *
	 * @return the selected values choices object
	 */
	public List<T> getSelectedValuesChoicesObject()
	{
		return this.selectedValuesChoices;
	}

	/**
	 * Removes the selected value.
	 *
	 * @param selectedValue
	 *            the selected value
	 * @return true, if successful
	 */
	public boolean removeSelectedValue(final T selectedValue)
	{
		List<T> selectedValues = getSelectedValuesChoicesObject();
		return selectedValues.remove(selectedValue);
	}

}
