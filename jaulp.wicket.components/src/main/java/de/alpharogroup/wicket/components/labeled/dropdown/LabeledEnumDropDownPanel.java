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
package de.alpharogroup.wicket.components.labeled.dropdown;

import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;

/**
 * The Class {@link LabeledEnumDropDownPanel}.
 */
public class LabeledEnumDropDownPanel extends Panel
{

	/** The serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new {@link LabeledEnumDropDownPanel}.
	 *
	 * @param id
	 *            the id
	 * @param model
	 *            the model
	 * @param labelModel
	 *            the label model
	 * @param choices
	 *            the choices
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public LabeledEnumDropDownPanel(final String id, final IModel<?> model,
		final IModel<String> labelModel, final IModel<?> choices)
	{
		super(id, model);
		add(new DropDownChoice("edit", model, choices).setLabel(labelModel));
	}

}
