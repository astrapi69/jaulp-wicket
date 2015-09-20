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
package de.alpharogroup.wicket.components.termofuse.contract;

import org.apache.wicket.Component;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.model.IModel;

import de.alpharogroup.locale.ResourceBundleKey;
import de.alpharogroup.wicket.behaviors.JQueryJsAppenderBehavior;
import de.alpharogroup.wicket.components.i18n.list.HeaderContentListModelBean;
import de.alpharogroup.wicket.components.i18n.list.HeaderContentListPanel;

/**
 * The Class {@link ContractPanel}.
 */
public class ContractPanel extends HeaderContentListPanel
{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new {@link ContractPanel}.
	 *
	 * @param id
	 *            the id
	 * @param model
	 *            the model
	 */
	public ContractPanel(final String id, final IModel<HeaderContentListModelBean> model)
	{
		super(id, model);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected Component newHeaderLabel(final String id, final IModel<String> model)
	{
		return super.newHeaderLabel(id, model).add(
			new JQueryJsAppenderBehavior("wrap", "<h2></h2>"));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected Component newListComponent(final String id, final ListItem<ResourceBundleKey> item)
	{
		return new Label(id, newContentResourceModel(item.getModel()))
			.add(new JQueryJsAppenderBehavior("wrap", "<p></p>"));
	}

}
