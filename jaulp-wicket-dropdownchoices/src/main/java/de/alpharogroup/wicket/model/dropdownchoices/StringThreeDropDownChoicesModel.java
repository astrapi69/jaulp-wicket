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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * The Class {@link StringThreeDropDownChoicesModel}.
 *
 * @author Asterios Raptis
 */
public class StringThreeDropDownChoicesModel extends ThreeDropDownChoicesModel<String>
{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new {@link StringThreeDropDownChoicesModel}.
	 *
	 * @param selectedTrademark
	 *            the selected trademark
	 * @param modelsMap
	 *            the models map
	 */
	public StringThreeDropDownChoicesModel(final String selectedTrademark,
		final Map<String, List<String>> modelsMap)
	{
		this(selectedTrademark, modelsMap, new ArrayList<String>());
	}

	/**
	 * Instantiates a new {@link StringThreeDropDownChoicesModel}.
	 *
	 * @param selectedTrademark
	 *            the selected trademark
	 * @param modelsMap
	 *            the models map
	 * @param selectedValuesChoices
	 *            the selected values choices
	 */
	public StringThreeDropDownChoicesModel(final String selectedTrademark,
		final Map<String, List<String>> modelsMap, final List<String> selectedValuesChoices)
	{
		super(selectedTrademark, modelsMap, selectedValuesChoices);
	}

}