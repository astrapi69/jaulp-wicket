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
package de.alpharogroup.wicket.dropdownchoices.panel;

import org.apache.wicket.markup.html.form.IChoiceRenderer;
import org.apache.wicket.model.IModel;

import de.alpharogroup.wicket.components.i18n.dropdownchoice.panels.TwoDropDownChoicesPanel;
import de.alpharogroup.wicket.model.dropdownchoices.TwoDropDownChoicesModel;

/**
 * The Class TrademarksModelsPanel.
 *
 * @author admin
 */
public class TrademarksModelsPanel extends TwoDropDownChoicesPanel<String>
{

	/**
	 * The Constant serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new trademarks models panel.
	 *
	 * @param id
	 *            the id
	 * @param model
	 *            the string two drop down choices model
	 * @param rootRenderer
	 *            the root renderer
	 * @param childRenderer
	 *            the child renderer
	 */
	public TrademarksModelsPanel(final String id,
		final IModel<TwoDropDownChoicesModel<String>> model,
		final IChoiceRenderer<String> rootRenderer, final IChoiceRenderer<String> childRenderer)
	{
		super(id, model, rootRenderer, childRenderer);
	}

}
