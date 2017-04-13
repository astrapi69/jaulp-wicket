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
package de.alpharogroup.wicket.components.sign.in.password.reset;

import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import de.alpharogroup.wicket.base.BasePanel;

/**
 * The Class {@link AbstractResetPasswordPanel}.
 *
 * @author Asterios Raptis
 */
public abstract class AbstractResetPasswordPanel extends BasePanel<ResetPasswordBean>
{

	/**
	 * The serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new {@link AbstractResetPasswordPanel}.
	 *
	 * @param id
	 *            the id
	 * @param model
	 *            the model
	 */
	public AbstractResetPasswordPanel(final String id, final IModel<ResetPasswordBean> model)
	{
		super(id, model);
		onReset(model.getObject().getUsername(), model.getObject().getConfirmationCode());
	}

	/**
	 * Instantiates a new {@link AbstractResetPasswordPanel}.
	 *
	 * @param id
	 *            the id
	 * @param parameters
	 *            the parameters
	 */
	public AbstractResetPasswordPanel(final String id, final PageParameters parameters)
	{
		this(id, Model.of(ResetPasswordBean.getResetPasswordBean(parameters)));
	}

	/**
	 * Abstract callback method that have to be overwritten to provide the action for reset the
	 * password.
	 *
	 * @param username
	 *            the username
	 * @param confirmationCode
	 *            the confirmation code
	 */
	protected abstract void onReset(final String username, final String confirmationCode);
}
