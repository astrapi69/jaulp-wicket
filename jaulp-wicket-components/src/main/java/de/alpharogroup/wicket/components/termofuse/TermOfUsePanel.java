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
package de.alpharogroup.wicket.components.termofuse;

import org.apache.wicket.Component;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import de.alpharogroup.wicket.components.i18n.list.HeaderContentListModelBean;
import de.alpharogroup.wicket.components.termofuse.cancellation.CancellationPanel;
import de.alpharogroup.wicket.components.termofuse.contract.ContractPanel;
import de.alpharogroup.wicket.components.termofuse.copyright.CopyrightPanel;
import de.alpharogroup.wicket.components.termofuse.dataprotection.DataProtectionPanel;
import de.alpharogroup.wicket.components.termofuse.disclaimer.DisclaimerPanel;
import de.alpharogroup.wicket.components.termofuse.fulfilmentandjurisdiction.FulfilmentAndJurisdictionPlacePanel;
import de.alpharogroup.wicket.components.termofuse.general.GeneralTermsAndConditionsPanel;
import de.alpharogroup.wicket.components.termofuse.legalreferences.LegalReferencesPanel;
import de.alpharogroup.wicket.components.termofuse.liability.LiabilityPanel;
import de.alpharogroup.wicket.components.termofuse.modificationsclause.ModificationsClausePanel;
import de.alpharogroup.wicket.components.termofuse.rightsandduties.RightsAndDutiesModelBean;
import de.alpharogroup.wicket.components.termofuse.rightsandduties.RightsAndDutiesPanel;
import de.alpharogroup.wicket.components.termofuse.salvatoriusclause.SalvatoriusClausePanel;
import lombok.Getter;

/**
 * The class {@link TermOfUsePanel}.
 * 
 * @author Asterios Raptis
 */
public abstract class TermOfUsePanel extends Panel
{

	/**
	 * The serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;

	/** The general terms and conditions panel. */
	@Getter
	private Component generalTermsAndConditionsPanel;

	/** The legal references panel. */
	@Getter
	private Component legalReferencesPanel;

	/** The contract panel. */
	@Getter
	private Component contractPanel;

	/** The data protection panel. */
	@Getter
	private Component dataProtectionPanel;

	/** The copyright panel. */
	@Getter
	private Component copyrightPanel;

	/** The cancellation panel. */
	@Getter
	private Component cancellationPanel;

	/** The liability panel. */
	@Getter
	private Component liabilityPanel;

	/** The rights and duties panel. */
	@Getter
	private Component rightsAndDutiesPanel;

	/** The modifications clause panel. */
	@Getter
	private Component modificationsClausePanel;

	/** The salvatorius clause panel. */
	@Getter
	private Component salvatoriusClausePanel;

	/** The fulfilment and jurisdiction place panel. */
	@Getter
	private Component fulfilmentAndJurisdictionPlacePanel;

	/** The disclaimer panel. */
	@Getter
	private Component disclaimerPanel;

	/**
	 * Instantiates a new {@link TermOfUsePanel}.
	 *
	 * @param id
	 *            the id
	 */
	public TermOfUsePanel(final String id)
	{
		this(id, null);
	}

	/**
	 * Instantiates a new {@link TermOfUsePanel}.
	 *
	 * @param id
	 *            the id
	 * @param model
	 *            the model
	 */
	public TermOfUsePanel(final String id, final IModel<TermOfUseModelBean> model)
	{
		super(id, model);

		add(generalTermsAndConditionsPanel = newGeneralTermsAndConditionsPanel(
			"generalTermsAndConditionsPanel",
			Model.of(model.getObject().getGeneralTermsAndConditionsModelBean())));

		add(legalReferencesPanel = newLegalReferencesPanel("legalReferencesPanel",
			Model.of(model.getObject().getLegalReferencesModelBean())));

		add(contractPanel = newContractPanel("contractPanel",
			Model.of(model.getObject().getContractModelBean())));

		add(dataProtectionPanel = newDataProtectionPanel("dataProtectionPanel",
			Model.of(model.getObject().getDataProtectionModelBean())));

		add(copyrightPanel = newCopyrightPanel("copyrightPanel",
			Model.of(model.getObject().getCopyrightModelBean())));

		add(cancellationPanel = newCancellationPanel("cancellationPanel",
			Model.of(model.getObject().getCancellationModelBean())));

		add(liabilityPanel = newLiabilityPanel("liabilityPanel",
			Model.of(model.getObject().getLiabilityModelBean())));

		add(rightsAndDutiesPanel = newRightsAndDutiesPanel("rightsAndDutiesPanel",
			Model.of(model.getObject().getRightsAndDutiesModelBean())));

		add(modificationsClausePanel = newModificationsClausePanel("modificationsClausePanel",
			Model.of(model.getObject().getModificationsClauseModelBean())));

		add(salvatoriusClausePanel = newSalvatoriusClausePanel("salvatoriusClausePanel",
			Model.of(model.getObject().getSalvatoriusClauseModelBean())));

		add(fulfilmentAndJurisdictionPlacePanel = newFulfilmentAndJurisdictionPlacePanel(
			"fulfilmentAndJurisdictionPlacePanel",
			Model.of(model.getObject().getFulfilmentAndJurisdictionPlaceModelBean())));

		add(disclaimerPanel = newDisclaimerPanel("disclaimerPanel",
			Model.of(model.getObject().getDisclaimerModelBean())));

	}

	/**
	 * Factory method for creating the new {@link Component} for the cancellation. This method is
	 * invoked in the constructor from the derived classes and can be overridden so users can
	 * provide their own version of a new {@link Component} for the cancellation.
	 *
	 * @param id
	 *            the id
	 * @param model
	 *            the model
	 * @return the new {@link Component} for the cancellation
	 */
	protected Component newCancellationPanel(final String id,
		final IModel<HeaderContentListModelBean> model)
	{
		return new CancellationPanel(id, Model.of(model.getObject()));
	}

	/**
	 * Factory method for creating the new {@link Component} for the contract. This method is
	 * invoked in the constructor from the derived classes and can be overridden so users can
	 * provide their own version of a new {@link Component} for the contract.
	 *
	 * @param id
	 *            the id
	 * @param model
	 *            the model
	 * @return the new {@link Component} for the contract
	 */
	protected Component newContractPanel(final String id,
		final IModel<HeaderContentListModelBean> model)
	{
		return new ContractPanel(id, Model.of(model.getObject()));
	}

	/**
	 * Factory method for creating the new {@link Component} for the copyright. This method is
	 * invoked in the constructor from the derived classes and can be overridden so users can
	 * provide their own version of a new {@link Component} for the copyright.
	 *
	 * @param id
	 *            the id
	 * @param model
	 *            the model
	 * @return the new {@link Component} for the copyright
	 */
	protected Component newCopyrightPanel(final String id,
		final IModel<HeaderContentListModelBean> model)
	{
		return new CopyrightPanel(id, Model.of(model.getObject()));
	}

	/**
	 * Factory method for creating the new {@link Component} for the data protection. This method is
	 * invoked in the constructor from the derived classes and can be overridden so users can
	 * provide their own version of a new {@link Component} for the data protection.
	 *
	 * @param id
	 *            the id
	 * @param model
	 *            the model
	 * @return the new {@link Component} for the data protection
	 */
	protected Component newDataProtectionPanel(final String id,
		final IModel<HeaderContentListModelBean> model)
	{
		return new DataProtectionPanel(id, Model.of(model.getObject()));
	}

	/**
	 * Factory method for creating the new {@link Component} for the disclaimer. This method is
	 * invoked in the constructor from the derived classes and can be overridden so users can
	 * provide their own version of a new {@link Component} for the disclaimer.
	 *
	 * @param id
	 *            the id
	 * @param model
	 *            the model
	 * @return the new {@link Component} for the disclaimer
	 */
	protected Component newDisclaimerPanel(final String id,
		final IModel<HeaderContentListModelBean> model)
	{
		return new DisclaimerPanel(id, Model.of(model.getObject()));
	}

	/**
	 * Factory method for creating the new {@link Component} for the fulfilment and jurisdiction
	 * place. This method is invoked in the constructor from the derived classes and can be
	 * overridden so users can provide their own version of a new {@link Component} for the
	 * fulfilment and jurisdiction place.
	 *
	 * @param id
	 *            the id
	 * @param model
	 *            the model
	 * @return the new {@link Component} for the fulfilment and jurisdiction place
	 */
	protected Component newFulfilmentAndJurisdictionPlacePanel(final String id,
		final IModel<HeaderContentListModelBean> model)
	{
		return new FulfilmentAndJurisdictionPlacePanel(id, Model.of(model.getObject()));
	}

	/**
	 * Factory method for creating the new {@link Component} for the general terms and conditions.
	 * This method is invoked in the constructor from the derived classes and can be overridden so
	 * users can provide their own version of a new {@link Component} for the general terms and
	 * conditions.
	 *
	 * @param id
	 *            the id
	 * @param model
	 *            the model
	 * @return the new {@link Component} for the general terms and conditions
	 */
	protected Component newGeneralTermsAndConditionsPanel(final String id,
		final IModel<HeaderContentListModelBean> model)
	{
		return new GeneralTermsAndConditionsPanel(id, Model.of(model.getObject()));
	}

	/**
	 * Factory method for creating the new {@link Component} for the legal references. This method
	 * is invoked in the constructor from the derived classes and can be overridden so users can
	 * provide their own version of a new {@link Component} for the legal references.
	 *
	 * @param id
	 *            the id
	 * @param model
	 *            the model
	 * @return the new {@link Component} for the legal references
	 */
	protected Component newLegalReferencesPanel(final String id,
		final IModel<HeaderContentListModelBean> model)
	{
		return new LegalReferencesPanel(id, Model.of(model.getObject()));
	}

	/**
	 * Factory method for creating the new {@link Component} for the liability. This method is
	 * invoked in the constructor from the derived classes and can be overridden so users can
	 * provide their own version of a new {@link Component} for the liability.
	 *
	 * @param id
	 *            the id
	 * @param model
	 *            the model
	 * @return the new {@link Component} for the liability
	 */
	protected Component newLiabilityPanel(final String id,
		final IModel<HeaderContentListModelBean> model)
	{
		return new LiabilityPanel(id, Model.of(model.getObject()));
	}

	/**
	 * Factory method for creating the new {@link Component} for the modifications clause. This
	 * method is invoked in the constructor from the derived classes and can be overridden so users
	 * can provide their own version of a new {@link Component} for the modifications clause.
	 *
	 * @param id
	 *            the id
	 * @param model
	 *            the model
	 * @return the new {@link Component} for the modifications clause
	 */
	protected Component newModificationsClausePanel(final String id,
		final IModel<HeaderContentListModelBean> model)
	{
		return new ModificationsClausePanel(id, Model.of(model.getObject()));
	}


	/**
	 * Factory method for creating the new {@link Component} for the rights and duties. This method
	 * is invoked in the constructor from the derived classes and can be overridden so users can
	 * provide their own version of a new {@link Component} for the rights and duties.
	 *
	 * @param id
	 *            the id
	 * @param model
	 *            the model
	 * @return the new {@link Component} for the rights and duties
	 */
	protected Component newRightsAndDutiesPanel(final String id,
		final IModel<RightsAndDutiesModelBean> model)
	{
		return new RightsAndDutiesPanel(id, model);
	}

	/**
	 * Factory method for creating the new {@link Component} for the salvatorius clause. This method
	 * is invoked in the constructor from the derived classes and can be overridden so users can
	 * provide their own version of a new {@link Component} for the salvatorius clause.
	 *
	 * @param id
	 *            the id
	 * @param model
	 *            the model
	 * @return the new {@link Component} for the salvatorius clause
	 */
	protected Component newSalvatoriusClausePanel(final String id,
		final IModel<HeaderContentListModelBean> model)
	{
		return new SalvatoriusClausePanel(id, Model.of(model.getObject()));
	}

}