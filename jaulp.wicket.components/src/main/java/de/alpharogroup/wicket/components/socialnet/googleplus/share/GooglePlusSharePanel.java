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
package de.alpharogroup.wicket.components.socialnet.googleplus.share;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import de.alpharogroup.wicket.base.BasePanel;
import de.alpharogroup.wicket.components.factory.ComponentFactory;

public class GooglePlusSharePanel extends BasePanel<GooglePlusShareModel>
{

	private static final long serialVersionUID = 1L;

	private Label googleScriptLabel;

	private WebMarkupContainer googlePlusButton;

	public GooglePlusSharePanel(String id)
	{
		this(id, null);
	}

	public GooglePlusSharePanel(String id, IModel<GooglePlusShareModel> model)
	{
		super(id, model);
		add(googleScriptLabel = newLabel("googleScriptLabel", model));
		add(googlePlusButton = newWebMarkupContainer("googlePlusButton", model));
	}

	protected WebMarkupContainer newWebMarkupContainer(String id, IModel<GooglePlusShareModel> model)
	{
		WebMarkupContainer googlePlusButton = ComponentFactory.newWebMarkupContainer(id, model);
		googlePlusButton.add(new AttributeModifier("class", model.getObject().getCssClass()));
		googlePlusButton.add(new AttributeModifier("data-annotation", model.getObject()
			.getDataAnnotation()));
		googlePlusButton.add(new AttributeModifier("data-width", model.getObject().getDataWith()));
		googlePlusButton.add(new AttributeModifier("data-href", model.getObject().getDataHref()));
		return googlePlusButton;
	}

	protected Label newLabel(String id, IModel<GooglePlusShareModel> model)
	{
		Label googleScriptLabel = new Label(id, Model.of("{lang: '" + model.getObject().getLocale()
			+ "'}"));
		googleScriptLabel.setEscapeModelStrings(false);
		googleScriptLabel.add(new AttributeModifier("src", model.getObject().getScriptSrc()));
		return googleScriptLabel;
	}

	public Label getGoogleScriptLabel()
	{
		return googleScriptLabel;
	}

	public WebMarkupContainer getGooglePlusButton()
	{
		return googlePlusButton;
	}

}
