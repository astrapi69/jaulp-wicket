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
package de.alpharogroup.wicket.components.socialnet.twitter.share;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.markup.html.link.ExternalLink;
import org.apache.wicket.model.IModel;
import org.apache.wicket.request.resource.PackageResourceReference;

import de.alpharogroup.wicket.base.BasePanel;

public class TwitterSharePanel extends BasePanel<TwitterShareModel>
{

	private static final long serialVersionUID = 1L;

	public TwitterSharePanel(final String id)
	{
		this(id, null);
	}

	public TwitterSharePanel(final String id, final IModel<TwitterShareModel> model)
	{
		super(id, model);
		final ExternalLink twitterShareLink = new ExternalLink("shareUrl", model.getObject()
			.getShareUrl());
		if (model.getObject().getDataUrl() != null)
		{
			twitterShareLink.add(new AttributeModifier("data-url", model.getObject().getDataUrl()));
		}
		if (model.getObject().getVia() != null)
		{
			twitterShareLink.add(new AttributeModifier("data-via", model.getObject().getVia()));
		}
		if (model.getObject().getCounturl() != null)
		{
			twitterShareLink.add(new AttributeModifier("data-counturl", model.getObject()
				.getCounturl()));
		}
		twitterShareLink.add(new AttributeModifier("data-show-count", model.getObject()
			.getShowCount().toString()));

		if (model.getObject().getCountAlign() != null)
		{
			// if the count should go at the top of the twitter icon use "vertical"...
			// twitterShareLink.add(new AttributeModifier("data-count", "vertical"));
			twitterShareLink.add(new AttributeModifier("data-count", model.getObject()
				.getCountAlign()));
		}
		add(twitterShareLink);
	}

	@Override
	public void renderHead(final IHeaderResponse response)
	{
		super.renderHead(response);
		final PackageResourceReference resourceReference = new PackageResourceReference(getClass(),
			"widgets.js");
		response.render(JavaScriptHeaderItem.forReference(resourceReference, "twitterShare"));
	}
}
