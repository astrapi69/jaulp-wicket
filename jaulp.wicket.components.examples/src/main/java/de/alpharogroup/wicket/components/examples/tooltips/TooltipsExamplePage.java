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
package de.alpharogroup.wicket.components.examples.tooltips;

import org.apache.wicket.Component;
import org.apache.wicket.model.Model;

import de.alpharogroup.wicket.components.examples.area.publicly.PubliclyBasePage;

/**
 * Example page for the several tooltips like tooltipster or bootstrap tooltip.
 */
public class TooltipsExamplePage extends PubliclyBasePage<Object>
{

	/**
	 * The serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public Component getContainerPanel()
	{
		return new TooltipsExamplePanel(CONTAINER_PANEL_ID, Model.of(""));
	}
}
