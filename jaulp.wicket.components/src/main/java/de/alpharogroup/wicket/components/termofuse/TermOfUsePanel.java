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
import de.alpharogroup.wicket.components.termofuse.rightsandduties.RightsAndDutiesModel;
import de.alpharogroup.wicket.components.termofuse.rightsandduties.RightsAndDutiesPanel;
import de.alpharogroup.wicket.components.termofuse.salvatoriusclause.SalvatoriusClausePanel;

/**
 * The class TermOfUsePanel.
 * 
 * @author Asterios Raptis
 */
public abstract class TermOfUsePanel extends Panel
{

	/**
	 * The serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;

	private Component generalTermsAndConditionsPanel;

	private Component legalReferencesPanel;

	private Component contractPanel;

	private Component dataProtectionPanel;

	private Component copyrightPanel;

	private Component cancellationPanel;

	private Component liabilityPanel;

	private Component rightsAndDutiesPanel;

	private Component modificationsClausePanel;

	private Component salvatoriusClausePanel;

	private Component fulfilmentAndJurisdictionPlacePanel;

	private Component disclaimerPanel;

	public TermOfUsePanel(final String id)
	{
		this(id, null);
	}

	public TermOfUsePanel(final String id, final IModel<TermOfUseModel> model)
	{
		super(id, model);

		add(generalTermsAndConditionsPanel = newGeneralTermsAndConditionsPanel(
			"generalTermsAndConditionsPanel",
			Model.of(model.getObject().getGeneralTermsAndConditionsModel())));

		add(legalReferencesPanel = newLegalReferencesPanel("legalReferencesPanel",
			Model.of(model.getObject().getLegalReferencesModel())));

		add(contractPanel = newContractPanel("contractPanel",
			Model.of(model.getObject().getContractModel())));

		add(dataProtectionPanel = newDataProtectionPanel("dataProtectionPanel",
			Model.of(model.getObject().getDataProtectionModel())));

		add(copyrightPanel = newCopyrightPanel("copyrightPanel",
			Model.of(model.getObject().getCopyrightModel())));

		add(cancellationPanel = newCancellationPanel("cancellationPanel",
			Model.of(model.getObject().getCancellationModel())));

		add(liabilityPanel = newLiabilityPanel("liabilityPanel",
			Model.of(model.getObject().getLiabilityModel())));

		add(rightsAndDutiesPanel = newRightsAndDutiesPanel("rightsAndDutiesPanel",
			Model.of(model.getObject().getRightsAndDutiesModel())));

		add(modificationsClausePanel = newModificationsClausePanel("modificationsClausePanel",
			Model.of(model.getObject().getModificationsClauseModel())));

		add(salvatoriusClausePanel = newSalvatoriusClausePanel("salvatoriusClausePanel",
			Model.of(model.getObject().getSalvatoriusClauseModel())));

		add(fulfilmentAndJurisdictionPlacePanel = newFulfilmentAndJurisdictionPlacePanel(
			"fulfilmentAndJurisdictionPlacePanel",
			Model.of(model.getObject().getFulfilmentAndJurisdictionPlaceModel())));

		add(disclaimerPanel = newDisclaimerPanel("disclaimerPanel",
			Model.of(model.getObject().getDisclaimerModel())));

	}

	public Component getCancellationPanel()
	{
		return cancellationPanel;
	}

	public Component getContractPanel()
	{
		return contractPanel;
	}

	public Component getCopyrightPanel()
	{
		return copyrightPanel;
	}

	public Component getDataProtectionPanel()
	{
		return dataProtectionPanel;
	}

	public Component getDisclaimerPanel()
	{
		return disclaimerPanel;
	}

	public Component getFulfilmentAndJurisdictionPlacePanel()
	{
		return fulfilmentAndJurisdictionPlacePanel;
	}

	public Component getGeneralTermsAndConditionsPanel()
	{
		return generalTermsAndConditionsPanel;
	}

	public Component getLegalReferencesPanel()
	{
		return legalReferencesPanel;
	}

	public Component getLiabilityPanel()
	{
		return liabilityPanel;
	}

	public Component getModificationsClausePanel()
	{
		return modificationsClausePanel;
	}

	public Component getRightsAndDutiesPanel()
	{
		return rightsAndDutiesPanel;
	}

	public Component getSalvatoriusClausePanel()
	{
		return salvatoriusClausePanel;
	}

	protected Component newCancellationPanel(final String id,
		final IModel<HeaderContentListModelBean> model)
	{
		return new CancellationPanel(id, Model.of(model.getObject()));
	}

	protected Component newContractPanel(final String id, final IModel<HeaderContentListModelBean> model)
	{
		return new ContractPanel(id, Model.of(model.getObject()));
	}

	protected Component newCopyrightPanel(final String id,
		final IModel<HeaderContentListModelBean> model)
	{
		return new CopyrightPanel(id, Model.of(model.getObject()));
	}

	protected Component newDataProtectionPanel(final String id,
		final IModel<HeaderContentListModelBean> model)
	{
		return new DataProtectionPanel(id, Model.of(model.getObject()));
	}

	protected Component newDisclaimerPanel(final String id,
		final IModel<HeaderContentListModelBean> model)
	{
		return new DisclaimerPanel(id, Model.of(model.getObject()));
	}

	protected Component newFulfilmentAndJurisdictionPlacePanel(final String id,
		final IModel<HeaderContentListModelBean> model)
	{
		return new FulfilmentAndJurisdictionPlacePanel(id, Model.of(model.getObject()));
	}

	protected Component newGeneralTermsAndConditionsPanel(final String id,
		final IModel<HeaderContentListModelBean> model)
	{
		return new GeneralTermsAndConditionsPanel(id, Model.of(model.getObject()));
	}

	protected Component newLegalReferencesPanel(final String id,
		final IModel<HeaderContentListModelBean> model)
	{
		return new LegalReferencesPanel(id, Model.of(model.getObject()));
	}

	protected Component newLiabilityPanel(final String id,
		final IModel<HeaderContentListModelBean> model)
	{
		return new LiabilityPanel(id, Model.of(model.getObject()));
	}

	protected Component newModificationsClausePanel(final String id,
		final IModel<HeaderContentListModelBean> model)
	{
		return new ModificationsClausePanel(id, Model.of(model.getObject()));
	}

	protected Component newRightsAndDutiesPanel(final String id,
		final IModel<RightsAndDutiesModel> model)
	{
		return new RightsAndDutiesPanel(id, model);
	}

	protected Component newSalvatoriusClausePanel(final String id,
		final IModel<HeaderContentListModelBean> model)
	{
		return new SalvatoriusClausePanel(id, Model.of(model.getObject()));
	}

}