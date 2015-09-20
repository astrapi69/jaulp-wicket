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
import de.alpharogroup.wicket.components.termofuse.rightsandduties.RightsAndDutiesModelBean;

/**
 * The Class {@link TermOfUseModelBean} captures the data for the term of use.
 * 
 * @author Asterios Raptis
 */
@Getter
@Setter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TermOfUseModelBean implements Serializable
{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The general terms and conditions model. */
	private HeaderContentListModelBean generalTermsAndConditionsModelBean;

	/** The cancellation model. */
	private HeaderContentListModelBean cancellationModelBean;

	/** The contract model. */
	private HeaderContentListModelBean contractModelBean;

	/** The copyright model. */
	private HeaderContentListModelBean copyrightModelBean;

	/** The data protection model. */
	private HeaderContentListModelBean dataProtectionModelBean;

	/** The fulfilment and jurisdiction place model. */
	private HeaderContentListModelBean fulfilmentAndJurisdictionPlaceModelBean;

	/** The legal references model. */
	private HeaderContentListModelBean legalReferencesModelBean;

	/** The liability model. */
	private HeaderContentListModelBean liabilityModelBean;

	/** The modifications clause model. */
	private HeaderContentListModelBean modificationsClauseModelBean;

	/** The rights and duties model bean. */
	private RightsAndDutiesModelBean rightsAndDutiesModelBean;

	/** The salvatorius clause model. */
	private HeaderContentListModelBean salvatoriusClauseModelBean;

	/** The disclaimer model. */
	private HeaderContentListModelBean disclaimerModelBean;

}
