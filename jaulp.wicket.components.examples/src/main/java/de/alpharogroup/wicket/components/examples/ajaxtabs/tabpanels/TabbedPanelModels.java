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
package de.alpharogroup.wicket.components.examples.ajaxtabs.tabpanels;

import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import org.apache.log4j.Logger;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class TabbedPanelModels<T> implements Serializable
{

	/** The Constant logger. */
	private static final Logger LOGGER = Logger.getLogger(TabbedPanelModels.class.getName());

	private static final long serialVersionUID = 1L;
	List<TabModel<T>> tabModels;

	public TabbedPanelModels<T> add(final TabModel<T> tabModel)
	{
		if (getTabModels() == null)
		{
			LOGGER
				.info("The list tabModels is null! Please set the list tabModels and add than the Given tabModel.");
			return this;
		}
		getTabModels().add(tabModel);
		return this;
	}

	public TabbedPanelModels<T> remove(final TabModel<T> tabModel)
	{
		if (getTabModels() == null)
		{
			LOGGER.info("The list tabModels is null! Given tabModel can not be removed.");
			return this;
		}
		getTabModels().remove(tabModel);
		return this;
	}
}