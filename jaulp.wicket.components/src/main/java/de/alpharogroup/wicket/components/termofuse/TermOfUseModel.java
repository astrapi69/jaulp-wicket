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

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import de.alpharogroup.wicket.components.i18n.list.HeaderContentListModelBean;
import de.alpharogroup.wicket.components.termofuse.rightsandduties.RightsAndDutiesModel;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TermOfUseModel implements Serializable
{

	private static final long serialVersionUID = 1L;

	private HeaderContentListModelBean generalTermsAndConditionsModel;

	private HeaderContentListModelBean cancellationModel;

	private HeaderContentListModelBean contractModel;

	private HeaderContentListModelBean copyrightModel;

	private HeaderContentListModelBean dataProtectionModel;

	private HeaderContentListModelBean fulfilmentAndJurisdictionPlaceModel;

	private HeaderContentListModelBean legalReferencesModel;

	private HeaderContentListModelBean liabilityModel;

	private HeaderContentListModelBean modificationsClauseModel;

	private RightsAndDutiesModel rightsAndDutiesModel;

	private HeaderContentListModelBean salvatoriusClauseModel;

	private HeaderContentListModelBean disclaimerModel;

}
