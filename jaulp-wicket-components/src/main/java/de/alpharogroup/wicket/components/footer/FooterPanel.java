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
package de.alpharogroup.wicket.components.footer;

import org.apache.wicket.Component;
import org.apache.wicket.model.IModel;

import de.alpharogroup.wicket.base.BasePanel;
import lombok.Getter;

/**
 * A component that can be used for the footer area.
 */
public abstract class FooterPanel<T> extends BasePanel<T>
{

	/**
	 * The serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * the footer menu panel
	 */
	@Getter
	private Component footerMenuPanel;


	/**
	 * Instantiates a new {@link FooterPanel}.
	 *
	 * @param id
	 *            the component id
	 * @param model
	 *            the model
	 */
	public FooterPanel(final String id, final IModel<T> model)
	{
		super(id, model);
		add(footerMenuPanel = newFooterMenuPanel("footerMenuPanel", model));
	}


	/**
	 * Factory method for creating a new {@link Component} for the footer area. This method is
	 * invoked in the constructor from the derived classes and can be overridden so users can
	 * provide their own version of a {@link Component} for the footer area..
	 *
	 * @param id
	 *            the id
	 * @param model
	 *            the model
	 * @return the new {@link Component} for the footer area.
	 */
	protected abstract Component newFooterMenuPanel(final String id, final IModel<T> model);

}
