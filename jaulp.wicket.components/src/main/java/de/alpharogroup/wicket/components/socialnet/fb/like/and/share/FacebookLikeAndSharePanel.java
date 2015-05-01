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
package de.alpharogroup.wicket.components.socialnet.fb.like.and.share;

import java.util.HashMap;

import org.apache.wicket.core.util.resource.UrlResourceStream;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.resource.PackageResourceReference;
import org.apache.wicket.velocity.markup.html.VelocityPanel;

import de.alpharogroup.wicket.base.BasePanel;

public class FacebookLikeAndSharePanel extends BasePanel<FacebookLikeAndShareModel>
{

	private static final long serialVersionUID = 1L;

	public FacebookLikeAndSharePanel(String id)
	{
		this(id, null);
	}

	public FacebookLikeAndSharePanel(String id, IModel<FacebookLikeAndShareModel> model)
	{
		super(id, model);
		HashMap<String, String> values = new HashMap<String, String>();
		values.put("data-share", model.getObject().getDataShare().toString());
		values.put("data-width", model.getObject().getDataWith().toString());
		values.put("data-show-faces", model.getObject().getDataShowFaces().toString());
		Model<HashMap<String, String>> context = Model.of(values);

		UrlResourceStream template = new UrlResourceStream(
			FacebookLikeAndSharePanel.class.getResource("fbLikeShare.vm"));
		add(VelocityPanel.forTemplateResource("velocityPanel", context, template));
	}

	@Override
	public void renderHead(IHeaderResponse response)
	{
		super.renderHead(response);
		PackageResourceReference resourceReference = new PackageResourceReference(getClass(),
			"fbLikeShare.js");
		response.render(JavaScriptHeaderItem.forReference(resourceReference, "fbLikeShare"));
	}

}
