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

import org.apache.wicket.markup.html.panel.GenericPanel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;


/**
 * The class ResetPasswordPanel.
 * 
 * @author Asterios Raptis
 */
public abstract class AbstractResetPasswordPanel extends GenericPanel<ResetPasswordBean>
{

	/**
	 * The serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;

	public AbstractResetPasswordPanel(final String id, final PageParameters parameters)
	{
		this(id, Model.of(ResetPasswordBean.getResetPasswordBean(parameters)));
	}

	public AbstractResetPasswordPanel(final String id, final IModel<ResetPasswordBean> model)
	{
		super(id, model);
		onReset(model.getObject().getUsername(), model.getObject().getConfirmationCode());
	}

	protected abstract void onReset(String username, String confirmationCode);
}
