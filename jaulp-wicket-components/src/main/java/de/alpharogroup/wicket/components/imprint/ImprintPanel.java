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

import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.IModel;

import de.alpharogroup.resourcebundle.locale.ResourceBundleKey;
import de.alpharogroup.wicket.base.BasePanel;
import de.alpharogroup.wicket.base.util.resource.ResourceModelFactory;
import de.alpharogroup.wicket.components.factory.ComponentFactory;
import de.alpharogroup.wicket.components.mailto.MailToPanel;
import de.alpharogroup.wicket.components.termofuse.disclaimer.DisclaimerPanel;
import lombok.Getter;

/**
 * The Class ImprintPanel.
 *
 * @author Asterios Raptis
 */
public abstract class ImprintPanel extends BasePanel<ImprintModelBean>
{

	/**
	 * The serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * The container for the disclaimer.
	 */
	@Getter
	private final WebMarkupContainer disclaimerContainer;

	/**
	 * The container for the imprint.
	 */
	@Getter
	private final WebMarkupContainer imprintContainer;

	/**
	 * Instantiates a new {@link ImprintPanel}.
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
	 * Hook method for implement the specific domain name.
	 *
	 * @return the domain name
	 */
	protected abstract String getDomainName();


	/**
	 * Factory method to create a new {@link IModel} for the 'authorised representative content'.
	 * This method is invoked in the constructor from this class and can be overridden so users can
	 * provide their own version of a new {@link IModel} for the 'authorised representative
	 * content'.
	 *
	 * @return the new {@link IModel} for the 'authorised representative content'.
	 */
	protected IModel<String> newAuthRepresentContentModel()
	{
		return newIModel("main.global.company.authorised.representative");
	}

	/**
	 * Factory method to create a new {@link IModel} for the 'authorised representative label'. This
	 * method is invoked in the constructor from this class and can be overridden so users can
	 * provide their own version of a new {@link IModel} for the 'authorised representative label'.
	 *
	 * @return the new {@link IModel} for the 'authorised representative label'.
	 */
	protected IModel<String> newAuthRepresentLabelModel()
	{
		return newIModel("main.global.company.authorised.representative.label");
	}

	/**
	 * Factory method to create a new {@link IModel} for the 'company name'. This method is invoked
	 * in the constructor from this class and can be overridden so users can provide their own
	 * version of a new {@link IModel} for the 'company name'.
	 *
	 * @return the new {@link IModel} for the 'company name'.
	 */
	protected IModel<String> newCompanyNameModel()
	{
		return newIModel("main.global.company.name.label");
	}

	/**
	 * Factory method to create a new {@link IModel} for the 'company register entry content'. This
	 * method is invoked in the constructor from this class and can be overridden so users can
	 * provide their own version of a new {@link IModel} for the 'company register entry content'.
	 *
	 * @return the new {@link IModel} for the 'company register entry content'.
	 */
	protected IModel<String> newCompanyRegisterEntryContentModel()
	{
		return newIModel("main.global.company.register.entry.label");
	}

	/**
	 * Factory method to create a new {@link IModel} for the 'company register entry header'. This
	 * method is invoked in the constructor from this class and can be overridden so users can
	 * provide their own version of a new {@link IModel} for the 'company register entry header'.
	 *
	 * @return the new {@link IModel} for the 'company register entry header'.
	 */
	protected IModel<String> newCompanyRegisterEntryHeaderModel()
	{
		return newIModel("main.global.company.register.entry.header.label");
	}

	/**
	 * Factory method to create a new {@link IModel} for the 'company register entry number'. This
	 * method is invoked in the constructor from this class and can be overridden so users can
	 * provide their own version of a new {@link IModel} for the 'company register entry number'.
	 *
	 * @return the new {@link IModel} for the 'company register entry number'.
	 */
	protected IModel<String> newCompanyRegisterEntryNumberModel()
	{
		return newIModel("main.global.company.register.entry.court.number");
	}

	/**
	 * Factory method to create a new {@link IModel} for the 'company register identification
	 * content'. This method is invoked in the constructor from this class and can be overridden so
	 * users can provide their own version of a new {@link IModel} for the 'company register
	 * identification content'.
	 *
	 * @return the new {@link IModel} for the 'company register identification content'.
	 */
	protected IModel<String> newCompanyRegisterIndedificationContentModel()
	{
		return newIModel("main.global.company.register.identification.label");
	}

	/**
	 * Factory method to create a new {@link IModel} for the 'company register identification
	 * header'. This method is invoked in the constructor from this class and can be overridden so
	 * users can provide their own version of a new {@link IModel} for the 'company register
	 * identification header'.
	 *
	 * @return the new {@link IModel} for the 'company register identification header'.
	 */
	protected IModel<String> newCompanyRegisterIndedificationHeaderModel()
	{
		return newIModel("main.global.company.register.identification.header.label");
	}

	/**
	 * Factory method to create a new {@link WebMarkupContainer} for the disclaimer content. This
	 * method is invoked in the constructor from this class and can be overridden so users can
	 * provide their own version of new {@link WebMarkupContainer} for the disclaimer content.
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
	 * Creates a new {@link IModel} from the given key.
	 *
	 * @param key
	 *            the key
	 * @return the new {@link IModel} from the given key.
	 */
	protected IModel<String> newIModel(final String key)
	{
		return ResourceModelFactory.newResourceModel(ResourceBundleKey.builder().key(key)
			.defaultValue("").build(), this);
	}

	/**
	 * Factory method to create a new {@link WebMarkupContainer} for the imprint content. This
	 * method is invoked in the constructor from this class and can be overridden so users can
	 * provide their own version of a new {@link WebMarkupContainer} for the imprint content.
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
	 * Factory method to create a new {@link IModel} for the 'imprint header'. This method is
	 * invoked in the constructor from this class and can be overridden so users can provide their
	 * own version of a new {@link IModel} for the 'imprint header'.
	 *
	 * @return the new {@link IModel} for the 'imprint header'.
	 */
	protected IModel<String> newImprintHeaderModel()
	{
		return newIModel("main.global.company.masthead.label");
	}

	/**
	 * Factory method to create a new {@link IModel} for the 'service from'. This method is invoked
	 * in the constructor from this class and can be overridden so users can provide their own
	 * version of a new {@link IModel} for the 'service from'.
	 *
	 * @return the new {@link IModel} for the 'service from'.
	 */
	protected IModel<String> newServiceFromModel()
	{
		return newIModel("imprint.service.from.label");
	}

	/**
	 * Factory method to create a new {@link IModel} for the 'slogan'. This method is invoked in the
	 * constructor from this class and can be overridden so users can provide their own version of a
	 * new {@link IModel} for the 'slogan'.
	 *
	 * @param domainNameParams
	 *            the domain name params
	 * @return the new {@link IModel} for the 'slogan'.
	 */
	protected IModel<String> newSloganModel(final Object[] domainNameParams)
	{
		return ResourceModelFactory.newResourceModel(
			ResourceBundleKey.builder().key("main.global.company.url.and.slogan.label")
				.parameters(domainNameParams).defaultValue("").build(), this);
	}

	/**
	 * Factory method to create a new {@link IModel} for the 'state'. This method is invoked in the
	 * constructor from this class and can be overridden so users can provide their own version of a
	 * new {@link IModel} for the 'state'.
	 *
	 * @return the new {@link IModel} for the 'state'.
	 */
	protected IModel<String> newStateModel()
	{
		return newIModel("main.global.company.state.label");
	}

	/**
	 * Factory method to create a new {@link IModel} for the 'street and number'. This method is
	 * invoked in the constructor from this class and can be overridden so users can provide their
	 * own version of a new {@link IModel} for the 'street and number'.
	 *
	 * @return the new {@link IModel} for the 'street and number'.
	 */
	protected IModel<String> newStreetAndNumberModel()
	{
		return newIModel("main.global.company.street.and.number.label");
	}

	/**
	 * Factory method to create a new {@link IModel} for the 'zip and city'. This method is invoked
	 * in the constructor from this class and can be overridden so users can provide their own
	 * version of a new {@link IModel} for the 'zip and city'.
	 *
	 * @return the new {@link IModel} for the 'zip and city'.
	 */
	protected IModel<String> newZipAndCityModel()
	{
		return newIModel("main.global.company.zipcode.and.city.label");
	}
}
