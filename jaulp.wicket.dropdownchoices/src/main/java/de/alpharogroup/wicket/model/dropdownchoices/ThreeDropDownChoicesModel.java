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
package de.alpharogroup.wicket.model.dropdownchoices;

import java.util.List;
import java.util.Map;

import lombok.Getter;

/**
 * The Class {@link ThreeDropDownChoicesModel}.
 *
 * @param <T>
 *            the generic type of the model object
 * @author Asterios Raptis
 */
public class ThreeDropDownChoicesModel<T> extends TwoDropDownChoicesModel<T>
{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The selected option. */
	@Getter
	private T selectedValueOption;

	/** The child choices. */
	@Getter
	private final List<T> selectedValuesChoices;

	/**
	 * Instantiates a new {@link ThreeDropDownChoicesModel}.
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
		return getSelectedValuesChoices().add(selectedValue);
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
		return getSelectedValuesChoices().contains(selectedValue);
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
		return getSelectedValuesChoices().remove(selectedValue);
	}

}
