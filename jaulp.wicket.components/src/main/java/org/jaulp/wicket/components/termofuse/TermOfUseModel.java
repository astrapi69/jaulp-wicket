package org.jaulp.wicket.components.termofuse;

import java.io.Serializable;

import org.jaulp.wicket.components.i18n.list.HeaderContentListModel;
import org.jaulp.wicket.components.termofuse.rightsandduties.RightsAndDutiesModel;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Builder;

@Getter 
@Setter 
@EqualsAndHashCode 
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TermOfUseModel implements Serializable {

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
