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
package de.alpharogroup.wicket.components.imprint;

import lombok.Getter;
import de.alpharogroup.locale.ResourceBundleKey;

import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.IModel;

import de.alpharogroup.wicket.base.BasePanel;
import de.alpharogroup.wicket.base.util.resource.ResourceModelFactory;
import de.alpharogroup.wicket.components.factory.ComponentFactory;
import de.alpharogroup.wicket.components.mailto.MailToPanel;
import de.alpharogroup.wicket.components.termofuse.disclaimer.DisclaimerPanel;

/**
 * The Class ImprintPanel.
 *
 * @author Asterios Raptis
 */
public abstract class ImprintPanel extends BasePanel<ImprintModel>
{

	/**
	 * The serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;
	@Getter
	private final WebMarkupContainer disclaimerContainer;
	@Getter
	private final WebMarkupContainer imprintContainer;

	/**
	 * Instantiates a new imprint panel.
	 *
	 * @param id
	 *            the id
	 */
	public ImprintPanel(final String id)
	{
		super(id);

		add(imprintContainer = newImprintContainer("imprintContainer"));

		final Object[] domainNameParams = { getDomainName() };
		imprintContainer.add(new Label("imprintHeaderLbl", newImprintHeaderModel()));

		imprintContainer.add(new Label("urlWithSloganLbl", newSloganModel(domainNameParams)));

		imprintContainer.add(new Label("serviceFromLbl", newServiceFromModel()));

		imprintContainer.add(new Label("companyNameLbl", newCompanyNameModel()));

		imprintContainer.add(new Label("streetAndNumberLbl", newStreetAndNumberModel()));

		imprintContainer.add(new Label("zipAndCityLbl", newZipAndCityModel()));

		imprintContainer.add(new Label("state", newStateModel()));

		imprintContainer.add(new Label("authRepresentLabel", newAuthRepresentLabelModel()));

		imprintContainer.add(new Label("authRepresentContent", newAuthRepresentContentModel()));

		imprintContainer.add(new Label("companyRegisterEntryHeader",
			newCompanyRegisterEntryHeaderModel()));

		imprintContainer.add(new Label("companyRegisterEntryContent",
			newCompanyRegisterEntryContentModel()));

		imprintContainer.add(new Label("companyRegisterEntryNumber",
			newCompanyRegisterEntryNumberModel()));

		imprintContainer.add(new Label("companyRegisterIndedificationHeader",
			newCompanyRegisterIndedificationHeaderModel()));

		imprintContainer.add(new Label("companyRegisterIndedificationContent",
			newCompanyRegisterIndedificationContentModel()));

		imprintContainer.add(new Label("companyRegisterIndedificationNumber",
			newCompanyRegisterEntryNumberModel()));

		imprintContainer.add(new Label("companyEntryCommercialRegisterHeader", ResourceModelFactory
			.newResourceModel(
				ResourceBundleKey.builder()
					.key("main.global.company.entry.commercial.register.header.label")
					.defaultValue("").build(), this)));

		imprintContainer.add(new Label("companyEntryCommercialRegisterContent",
			ResourceModelFactory.newResourceModel(
				ResourceBundleKey.builder()
					.key("main.global.company.entry.commercial.register.label").defaultValue("")
					.build(), this)));

		imprintContainer.add(new Label("companyEntryCommercialRegisterNumber", ResourceModelFactory
			.newResourceModel(
				ResourceBundleKey.builder()
					.key("main.global.company.entry.commercial.register.number").defaultValue("")
					.build(), this)));

		disclaimerContainer = newDisclaimerContainer("disclaimerContainer");
		add(disclaimerContainer);
		disclaimerContainer.add(new DisclaimerPanel("disclaimerPanel"));

		disclaimerContainer.add(new MailToPanel("mailToPanel")
		{
			private static final long serialVersionUID = 1L;

			@Override
			protected String getDomainName()
			{
				return ImprintPanel.this.getDomainName();
			}
		});
	}


	/**
	 * Factory method to create a WebMarkupContainer for the disclaimer content. This method is
	 * invoked in the constructor from this class and can be overridden so users can provide their
	 * own version of a disclaimer content.
	 *
	 * @param id
	 *            the id
	 * @return the new {@link WebMarkupContainer} for the disclaimer content.
	 */
	protected WebMarkupContainer newDisclaimerContainer(final String id)
	{
		return ComponentFactory.newWebMarkupContainer(id);
	}


	/**
	 * Factory method to create a WebMarkupContainer for the imprint content. This method is invoked
	 * in the constructor from this class and can be overridden so users can provide their own
	 * version of a imprint content.
	 *
	 * @param id
	 *            the id
	 * @return the new {@link WebMarkupContainer} for the imprint content.
	 */
	protected WebMarkupContainer newImprintContainer(final String id)
	{
		return ComponentFactory.newWebMarkupContainer(id);
	}

	/**
	 * Hook method for implement the specific domain name.
	 *
	 * @return the domain name
	 */
	protected abstract String getDomainName();

	/**
	 * Creates a new IModel from the given key.
	 *
	 * @param key
	 *            the key
	 * @return the i model
	 */
	protected IModel<String> newIModel(String key)
	{
		return ResourceModelFactory.newResourceModel(ResourceBundleKey.builder().key(key)
			.defaultValue("").build(), this);
	}

	/**
	 * Factory method to create a IModel for the 'service from'. This method is invoked in the
	 * constructor from this class and can be overridden so users can provide their own version of a
	 * 'service from'.
	 *
	 * @return the i model
	 */
	protected IModel<String> newServiceFromModel()
	{
		return newIModel("imprint.service.from.label");
	}

	/**
	 * Factory method to create a IModel for the 'slogan'. This method is invoked in the constructor
	 * from this class and can be overridden so users can provide their own version of a 'slogan'.
	 *
	 * @param domainNameParams
	 *            the domain name params
	 * @return the i model
	 */
	protected IModel<String> newSloganModel(final Object[] domainNameParams)
	{
		return ResourceModelFactory.newResourceModel(
			ResourceBundleKey.builder().key("main.global.company.url.and.slogan.label")
				.parameters(domainNameParams).defaultValue("").build(), this);
	}

	/**
	 * Factory method to create a IModel for the 'imprint header'. This method is invoked in the
	 * constructor from this class and can be overridden so users can provide their own version of a
	 * 'imprint header'.
	 *
	 * @return the i model
	 */
	protected IModel<String> newImprintHeaderModel()
	{
		return newIModel("main.global.company.masthead.label");
	}

	/**
	 * Factory method to create a IModel for the 'company name'. This method is invoked in the
	 * constructor from this class and can be overridden so users can provide their own version of a
	 * 'company name'.
	 *
	 * @return the i model
	 */
	protected IModel<String> newCompanyNameModel()
	{
		return newIModel("main.global.company.name.label");
	}

	/**
	 * Factory method to create a IModel for the 'street and number'. This method is invoked in the
	 * constructor from this class and can be overridden so users can provide their own version of a
	 * 'street and number'.
	 *
	 * @return the i model
	 */
	protected IModel<String> newStreetAndNumberModel()
	{
		return newIModel("main.global.company.street.and.number.label");
	}

	/**
	 * Factory method to create a IModel for the 'zip and city'. This method is invoked in the
	 * constructor from this class and can be overridden so users can provide their own version of a
	 * 'zip and city'.
	 *
	 * @return the i model
	 */
	protected IModel<String> newZipAndCityModel()
	{
		return newIModel("main.global.company.zipcode.and.city.label");
	}

	/**
	 * Factory method to create a IModel for the 'state'. This method is invoked in the constructor
	 * from this class and can be overridden so users can provide their own version of a 'state'.
	 *
	 * @return the i model
	 */
	protected IModel<String> newStateModel()
	{
		return newIModel("main.global.company.state.label");
	}

	/**
	 * Factory method to create a IModel for the 'authorised representative label'. This method is
	 * invoked in the constructor from this class and can be overridden so users can provide their
	 * own version of a 'authorised representative label'.
	 *
	 * @return the i model
	 */
	protected IModel<String> newAuthRepresentLabelModel()
	{
		return newIModel("main.global.company.authorised.representative.label");
	}

	/**
	 * Factory method to create a IModel for the 'authorised representative content'. This method is
	 * invoked in the constructor from this class and can be overridden so users can provide their
	 * own version of a 'authorised representative content'.
	 *
	 * @return the i model
	 */
	protected IModel<String> newAuthRepresentContentModel()
	{
		return newIModel("main.global.company.authorised.representative");
	}

	/**
	 * Factory method to create a IModel for the 'company register entry header'. This method is
	 * invoked in the constructor from this class and can be overridden so users can provide their
	 * own version of a 'company register entry header'.
	 *
	 * @return the i model
	 */
	protected IModel<String> newCompanyRegisterEntryHeaderModel()
	{
		return newIModel("main.global.company.register.entry.header.label");
	}

	/**
	 * Factory method to create a IModel for the 'company register entry content'. This method is
	 * invoked in the constructor from this class and can be overridden so users can provide their
	 * own version of a 'company register entry content'.
	 *
	 * @return the i model
	 */
	protected IModel<String> newCompanyRegisterEntryContentModel()
	{
		return newIModel("main.global.company.register.entry.label");
	}

	/**
	 * Factory method to create a IModel for the 'company register entry number'. This method is
	 * invoked in the constructor from this class and can be overridden so users can provide their
	 * own version of a 'company register entry number'.
	 *
	 * @return the i model
	 */
	protected IModel<String> newCompanyRegisterEntryNumberModel()
	{
		return newIModel("main.global.company.register.entry.court.number");
	}

	/**
	 * Factory method to create a IModel for the 'company register indedification header'. This
	 * method is invoked in the constructor from this class and can be overridden so users can
	 * provide their own version of a 'company register indedification header'.
	 *
	 * @return the i model
	 */
	protected IModel<String> newCompanyRegisterIndedificationHeaderModel()
	{
		return newIModel("main.global.company.register.identification.header.label");
	}

	/**
	 * Factory method to create a IModel for the 'company register indedification content'. This
	 * method is invoked in the constructor from this class and can be overridden so users can
	 * provide their own version of a 'company register indedification content'.
	 *
	 * @return the i model
	 */
	protected IModel<String> newCompanyRegisterIndedificationContentModel()
	{
		return newIModel("main.global.company.register.identification.label");
	}
}
