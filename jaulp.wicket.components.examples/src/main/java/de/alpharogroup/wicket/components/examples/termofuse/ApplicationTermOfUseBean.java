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
package de.alpharogroup.wicket.components.examples.termofuse;

import java.io.Serializable;

import com.google.common.collect.ImmutableList;

import de.alpharogroup.collections.ListExtensions;
import de.alpharogroup.locale.ResourceBundleKey;
import de.alpharogroup.wicket.components.examples.application.WicketApplication;
import de.alpharogroup.wicket.components.i18n.list.ContentListModel;
import de.alpharogroup.wicket.components.i18n.list.HeaderContentListModel;
import de.alpharogroup.wicket.components.termofuse.TermOfUseModel;
import de.alpharogroup.wicket.components.termofuse.rightsandduties.RightsAndDutiesModel;


public final class ApplicationTermOfUseBean implements Serializable
{

	private static final long serialVersionUID = 1L;

	private static ApplicationTermOfUseBean instance;


	public static synchronized ApplicationTermOfUseBean getInstance()
	{
		if (instance == null)
		{
			instance = new ApplicationTermOfUseBean();
		}
		return instance;
	}

	public static final ResourceBundleKey CANCELLATION_HEADER_RESOURCE_KEY = ResourceBundleKey
		.builder().key("term.of.use.cancellation.head.label").build();

	public static final ImmutableList<ResourceBundleKey> CANCELLATION_CONTENT_RESOURCE_KEYS = new ImmutableList.Builder<ResourceBundleKey>()
		.add(
			ResourceBundleKey.builder()
				.key("term.of.use.cancellation.content.paragraph.first.label").build())
		.add(
			ResourceBundleKey.builder()
				.key("term.of.use.cancellation.content.paragraph.second.label").build())
		.add(
			ResourceBundleKey.builder()
				.key("term.of.use.cancellation.content.paragraph.third.label").build()).build();

	public static final ResourceBundleKey CONTRACT_HEADER_RESOURCE_KEY = ResourceBundleKey
		.builder().key("term.of.use.contract.head.label").build();

	public static final ImmutableList<ResourceBundleKey> CONTRACT_CONTENT_RESOURCE_KEYS = new ImmutableList.Builder<ResourceBundleKey>()
		.add(
			ResourceBundleKey.builder().key("term.of.use.contract.content.paragraph.first.label")
				.build()).build();

	public static final ResourceBundleKey COPYRIGHT_HEADER_RESOURCE_KEY = ResourceBundleKey
		.builder().key("term.of.use.copyright.head.label").build();

	public static final ImmutableList<ResourceBundleKey> COPYRIGHT_CONTENT_RESOURCE_KEYS = new ImmutableList.Builder<ResourceBundleKey>()
		.add(ResourceBundleKey.builder().key("term.of.use.copyright.paragraph.first.label").build())
		.build();

	public static final ResourceBundleKey DATA_PROTECTION_HEADER_RESOURCE_KEY = ResourceBundleKey
		.builder().key("term.of.use.data.protection.head.label").build();

	public static final ImmutableList<ResourceBundleKey> DATA_PROTECTION_CONTENT_RESOURCE_KEYS = new ImmutableList.Builder<ResourceBundleKey>()
		.add(
			ResourceBundleKey.builder()
				.key("term.of.use.data.protection.head.content.paragraph.first.label").build())
		.build();

	public static final ResourceBundleKey FULFILMENT_PLACE_HEADER_RESOURCE_KEY = ResourceBundleKey
		.builder()
		.key(
			"term.of.use.place.of.fulfilment.and.place.of.jurisdiction.and.other.regulations.head.label")
		.build();

	public static final ResourceBundleKey GENERAL_TERMS_HEADER_RESOURCE_KEY = ResourceBundleKey
		.builder().key("term.of.use.main.head.label").build();


	public static final ResourceBundleKey LEGAL_REFS_HEADER_RESOURCE_KEY = ResourceBundleKey
		.builder().key("term.of.use.legal.references.head.label").build();

	public static final ImmutableList<ResourceBundleKey> LEGAL_REFS_CONTENT_RESOURCE_KEYS = new ImmutableList.Builder<ResourceBundleKey>()
		.add(
			ResourceBundleKey.builder()
				.key("term.of.use.legal.references.content.paragraph.first.label").build())
		.add(
			ResourceBundleKey.builder()
				.key("term.of.use.legal.references.content.paragraph.second.label").build())
		.build();

	public static final ResourceBundleKey LIABILITY_HEADER_RESOURCE_KEY = ResourceBundleKey
		.builder().key("term.of.use.liability.head.label").build();

	public static final ImmutableList<ResourceBundleKey> LIABILITY_CONTENT_RESOURCE_KEYS = new ImmutableList.Builder<ResourceBundleKey>()
		.add(
			ResourceBundleKey.builder().key("term.of.use.liability.content.paragraph.first.label")
				.build())
		.add(
			ResourceBundleKey.builder().key("term.of.use.liability.content.paragraph.second.label")
				.build())
		.add(
			ResourceBundleKey.builder().key("term.of.use.liability.content.paragraph.third.label")
				.build())
		.add(
			ResourceBundleKey.builder().key("term.of.use.liability.content.paragraph.fourth.label")
				.build()).build();

	public static final ResourceBundleKey MOD_CLAUSE_HEADER_RESOURCE_KEY = ResourceBundleKey
		.builder().key("term.of.use.modifications.clause.head.label").build();

	public static final ImmutableList<ResourceBundleKey> MOD_CLAUSE_CONTENT_RESOURCE_KEYS = new ImmutableList.Builder<ResourceBundleKey>()
		.add(
			ResourceBundleKey.builder()
				.key("term.of.use.modifications.clause.content.paragraph.first.label").build())
		.add(
			ResourceBundleKey.builder()
				.key("term.of.use.modifications.clause.content.paragraph.second.label").build())
		.add(
			ResourceBundleKey.builder()
				.key("term.of.use.modifications.clause.content.paragraph.third.label").build())
		.build();


	public static final ResourceBundleKey RIGHTS_AND_DUTIES_HEADER_RESOURCE_KEY = ResourceBundleKey
		.builder().key("term.of.use.rights.and.duties.of.the.user.head.label").build();

	public static final ImmutableList<ResourceBundleKey> RIGHTS_AND_DUTIES_CONTENT_RESOURCE_KEYS = new ImmutableList.Builder<ResourceBundleKey>()
		.add(
			ResourceBundleKey.builder()
				.key("term.of.use.rights.and.duties.of.the.user.content.introduction.label")
				.build())
		.add(
			ResourceBundleKey.builder()
				.key("term.of.use.rights.and.duties.of.the.user.content.to.satisfy.label").build())
		.build();

	public static final ImmutableList<ResourceBundleKey> RIGHTS_AND_DUTIES_CONTENT_LIST_RESOURCE_KEYS = new ImmutableList.Builder<ResourceBundleKey>()
		.add(
			ResourceBundleKey.builder()
				.key("term.of.use.rights.and.duties.of.the.user.content.paragraph.first.label")
				.build())
		.add(
			ResourceBundleKey.builder()
				.key("term.of.use.rights.and.duties.of.the.user.content.paragraph.second.label")
				.build())
		.add(
			ResourceBundleKey.builder()
				.key("term.of.use.rights.and.duties.of.the.user.content.paragraph.third.label")
				.build())
		.add(
			ResourceBundleKey.builder()
				.key("term.of.use.rights.and.duties.of.the.user.content.paragraph.fourth.label")
				.build())
		.add(
			ResourceBundleKey.builder()
				.key("term.of.use.rights.and.duties.of.the.user.content.paragraph.fifth.label")
				.build())
		.add(
			ResourceBundleKey.builder()
				.key("term.of.use.rights.and.duties.of.the.user.content.paragraph.sixth.label")
				.build())
		.add(
			ResourceBundleKey.builder()
				.key("term.of.use.rights.and.duties.of.the.user.content.paragraph.seventh.label")
				.build())
		.add(
			ResourceBundleKey.builder()
				.key("term.of.use.rights.and.duties.of.the.user.content.paragraph.eighth.label")
				.build())
		.add(
			ResourceBundleKey.builder()
				.key("term.of.use.rights.and.duties.of.the.user.content.paragraph.ninth.label")
				.build())
		.add(
			ResourceBundleKey.builder()
				.key("term.of.use.rights.and.duties.of.the.user.content.paragraph.tenth.label")
				.build())
		.add(
			ResourceBundleKey.builder()
				.key("term.of.use.rights.and.duties.of.the.user.content.paragraph.eleventh.lable")
				.build()).build();

	public static final ImmutableList<ResourceBundleKey> RIGHTS_AND_DUTIES_CONTENT_SUMMARY_RESOURCE_KEYS = new ImmutableList.Builder<ResourceBundleKey>()
		.add(
			ResourceBundleKey
				.builder()
				.key(
					"term.of.use.rights.and.duties.of.the.user.content.closure.paragraph.first.label")
				.build())
		.add(
			ResourceBundleKey
				.builder()
				.key(
					"term.of.use.rights.and.duties.of.the.user.content.closure.paragraph.second.label")
				.build()).build();

	public static final ResourceBundleKey SALVA_CLAUSE_HEADER_RESOURCE_KEY = ResourceBundleKey
		.builder().key("term.of.use.salvatorius.clause.head.label").build();

	public static final ImmutableList<ResourceBundleKey> SALVA_CLAUSE_CONTENT_RESOURCE_KEYS = new ImmutableList.Builder<ResourceBundleKey>()
		.add(
			ResourceBundleKey.builder()
				.key("term.of.use.salvatorius.clause.content.paragraph.first.label").build())
		.build();

	private TermOfUseModel model;

	private ApplicationTermOfUseBean()
	{
		init();
	}

	private void init()
	{
		ResourceBundleKey parameter = ResourceBundleKey
			.builder()
			.key(
				"term.of.use.place.of.fulfilment.and.place.of.jurisdiction.and.other.regulations.current.place.label")
			.build();
		ImmutableList<ResourceBundleKey> fulfilmentPlaceContentResourceKeys = new ImmutableList.Builder<ResourceBundleKey>()
			.add(
				ResourceBundleKey
					.builder()
					.key(
						"term.of.use.place.of.fulfilment.and.place.of.jurisdiction.and.other.regulations.content.paragraph.firstlabel")
					.parameters(ListExtensions.toObjectArray(parameter)).build()).build();

		ImmutableList<ResourceBundleKey> generalTermsAndConditionsContentResourceKeys = new ImmutableList.Builder<ResourceBundleKey>()
			.add(
				ResourceBundleKey
					.builder()
					.key("term.of.use.main.head.content.paragraph.first.label")
					.parameters(
						ListExtensions.toObjectArray("www."
							+ WicketApplication.get().getDomainName())).build())
			.add(
				ResourceBundleKey.builder()
					.key("term.of.use.main.head.content.paragraph.second.label").build()).build();
		model = TermOfUseModel
			.builder()
			.cancellationModel(
				HeaderContentListModel.builder()
					.headerResourceKey(CANCELLATION_HEADER_RESOURCE_KEY)
					.contentResourceKeys(CANCELLATION_CONTENT_RESOURCE_KEYS).build())
			.contractModel(
				HeaderContentListModel.builder().headerResourceKey(CONTRACT_HEADER_RESOURCE_KEY)
					.contentResourceKeys(CONTRACT_CONTENT_RESOURCE_KEYS).build())
			.copyrightModel(
				HeaderContentListModel.builder().headerResourceKey(COPYRIGHT_HEADER_RESOURCE_KEY)
					.contentResourceKeys(COPYRIGHT_CONTENT_RESOURCE_KEYS).build())
			.dataProtectionModel(
				HeaderContentListModel.builder()
					.headerResourceKey(DATA_PROTECTION_HEADER_RESOURCE_KEY)
					.contentResourceKeys(DATA_PROTECTION_CONTENT_RESOURCE_KEYS).build())
			.fulfilmentAndJurisdictionPlaceModel(
				HeaderContentListModel.builder()
					.headerResourceKey(FULFILMENT_PLACE_HEADER_RESOURCE_KEY)
					.contentResourceKeys(fulfilmentPlaceContentResourceKeys).build())
			.generalTermsAndConditionsModel(
				HeaderContentListModel.builder()
					.headerResourceKey(GENERAL_TERMS_HEADER_RESOURCE_KEY)
					.contentResourceKeys(generalTermsAndConditionsContentResourceKeys).build())
			.legalReferencesModel(
				HeaderContentListModel.builder().headerResourceKey(LEGAL_REFS_HEADER_RESOURCE_KEY)
					.contentResourceKeys(LEGAL_REFS_CONTENT_RESOURCE_KEYS).build())
			.liabilityModel(
				HeaderContentListModel.builder().headerResourceKey(LIABILITY_HEADER_RESOURCE_KEY)
					.contentResourceKeys(LIABILITY_CONTENT_RESOURCE_KEYS).build())
			.modificationsClauseModel(
				HeaderContentListModel.builder().headerResourceKey(MOD_CLAUSE_HEADER_RESOURCE_KEY)
					.contentResourceKeys(MOD_CLAUSE_CONTENT_RESOURCE_KEYS).build())
			.rightsAndDutiesModel(
				RightsAndDutiesModel
					.builder()
					.introductionModel(
						HeaderContentListModel.builder()
							.headerResourceKey(RIGHTS_AND_DUTIES_HEADER_RESOURCE_KEY)
							.contentResourceKeys(RIGHTS_AND_DUTIES_CONTENT_RESOURCE_KEYS).build())
					.listModel(
						ContentListModel.builder()
							.contentResourceKeys(RIGHTS_AND_DUTIES_CONTENT_LIST_RESOURCE_KEYS)
							.build())
					.summaryModel(
						ContentListModel.builder()
							.contentResourceKeys(RIGHTS_AND_DUTIES_CONTENT_SUMMARY_RESOURCE_KEYS)
							.build()).build())
			.salvatoriusClauseModel(
				HeaderContentListModel.builder()
					.headerResourceKey(SALVA_CLAUSE_HEADER_RESOURCE_KEY)
					.contentResourceKeys(SALVA_CLAUSE_CONTENT_RESOURCE_KEYS).build()).build();
	}

	public TermOfUseModel getModel()
	{
		return model;
	}

}
