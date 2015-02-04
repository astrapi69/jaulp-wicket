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
package org.jaulp.wicket.behaviors.models;

import java.io.Serializable;

import org.apache.wicket.model.IModel;
import org.apache.wicket.model.StringResourceModel;

/**
 * The Class MailtoModel.
 * 
 * @author Asterios Raptis
 */
public class MailtoModel implements Serializable
{

	/**
	 * The serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;

	/** The mailto addres model. */
	private IModel<String> mailtoAddresModel;

	/** The mailto view model. */
	private IModel<String> mailtoViewModel;

	/**
	 * Instantiates a new mailto model.
	 */
	public MailtoModel()
	{
		super();
	}

	/**
	 * Instantiates a new mailto model.
	 * 
	 * @param mailtoAddresModel
	 *            the mailto addres model
	 * @param mailtoViewModel
	 *            the mailto view model
	 */
	public MailtoModel(final IModel<String> mailtoAddresModel, final IModel<String> mailtoViewModel)
	{
		super();
		this.mailtoAddresModel = mailtoAddresModel;
		this.mailtoViewModel = mailtoViewModel;
	}

	/**
	 * Gets the mailto addres model.
	 * 
	 * @return the mailto addres model
	 */
	public IModel<String> getMailtoAddresModel()
	{
		return mailtoAddresModel;
	}

	/**
	 * Gets the mailto view model.
	 * 
	 * @return the mailto view model
	 */
	public IModel<String> getMailtoViewModel()
	{
		return mailtoViewModel;
	}

	/**
	 * Sets the mailto addres model.
	 * 
	 * @param mailtoAddresModel
	 *            the new mailto addres model
	 */
	public void setMailtoAddresModel(final StringResourceModel mailtoAddresModel)
	{
		this.mailtoAddresModel = mailtoAddresModel;
	}

	/**
	 * Sets the mailto view model.
	 * 
	 * @param mailtoViewModel
	 *            the new mailto view model
	 */
	public void setMailtoViewModel(final StringResourceModel mailtoViewModel)
	{
		this.mailtoViewModel = mailtoViewModel;
	}

}
