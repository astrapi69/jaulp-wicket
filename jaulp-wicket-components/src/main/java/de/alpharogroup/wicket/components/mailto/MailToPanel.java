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
package de.alpharogroup.wicket.components.mailto;

import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;

import de.alpharogroup.resourcebundle.locale.ResourceBundleKey;
import de.alpharogroup.wicket.base.util.resource.ResourceModelFactory;
import de.alpharogroup.wicket.behaviors.components.MailtoLabel;
import de.alpharogroup.wicket.behaviors.models.MailtoModel;

/**
 * The Class {@link MailToPanel}.
 * 
 * @author Asterios Raptis
 */
public abstract class MailToPanel extends Panel
{

	/**
	 * The serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new {@link MailToPanel}.
	 * 
	 * @param id
	 *            the id
	 */
	public MailToPanel(final String id)
	{
		super(id);
		final Object[] params = { getDomainName() };
		add(new MailtoLabel("mailtoLabel", newMailtoModel(params)));
	}

	/**
	 * Hook method for implement the specific domain name.
	 * 
	 * @return the domain name
	 */
	protected abstract String getDomainName();

	/**
	 * Factory method to create a new {@link IModel} for the MailtoAddressModel. This method is
	 * invoked in the constructor from this class and can be overridden so users can provide their
	 * own version of a new {@link IModel} for the MailtoAddressModel.
	 * 
	 * @param params
	 *            the params
	 * @return the new {@link IModel} for the MailtoAddressModel.
	 */
	protected IModel<String> newMailToAddressModel(final Object[] params)
	{
		return ResourceModelFactory.newResourceModel(
			ResourceBundleKey.builder().key("global.mailto.address").parameters(params).build(),
			this);
	}

	/**
	 * Factory method to create a new {@link MailtoModel}. This method is invoked in the constructor
	 * from this class and can be overridden so users can provide their own version of a new
	 * {@link MailtoModel}.
	 * 
	 * @param params
	 *            the params
	 * @return the new {@link MailtoModel}.
	 */
	protected MailtoModel newMailtoModel(final Object[] params)
	{
		final MailtoModel model = new MailtoModel(newMailToAddressModel(params),
			newMailToViewModel(params));
		return model;
	}

	/**
	 * Factory method to create a new {@link IModel} for the MailtoViewModel. This method is invoked
	 * in the constructor from this class and can be overridden so users can provide their own
	 * version of a new {@link IModel} for the MailtoViewModel.
	 * 
	 * @param params
	 *            the params
	 * @return the new {@link IModel} for the MailtoViewModel.
	 */
	protected IModel<String> newMailToViewModel(final Object[] params)
	{
		return ResourceModelFactory.newResourceModel(
			ResourceBundleKey.builder().key("global.compamy.mailto.view").parameters(params)
				.build(), this);
	}

}