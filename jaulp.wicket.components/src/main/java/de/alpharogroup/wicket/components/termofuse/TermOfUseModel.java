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
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Builder;
import de.alpharogroup.wicket.components.i18n.list.HeaderContentListModel;
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

	private HeaderContentListModel generalTermsAndConditionsModel;

	private HeaderContentListModel cancellationModel;

	private HeaderContentListModel contractModel;

	private HeaderContentListModel copyrightModel;

	private HeaderContentListModel dataProtectionModel;

	private HeaderContentListModel fulfilmentAndJurisdictionPlaceModel;

	private HeaderContentListModel legalReferencesModel;

	private HeaderContentListModel liabilityModel;

	private HeaderContentListModel modificationsClauseModel;

	private RightsAndDutiesModel rightsAndDutiesModel;

	private HeaderContentListModel salvatoriusClauseModel;

	private HeaderContentListModel disclaimerModel;

}
