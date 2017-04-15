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
import lombok.Getter;

/**
 * The Class {@link GooglePlusSharePanel}.
 */
public class GooglePlusSharePanel extends BasePanel<GooglePlusShareModelBean>
{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The google script label. */
	@Getter
	private Label googleScriptLabel;

	/** The google plus button. */
	@Getter
	private WebMarkupContainer googlePlusButton;

	/**
	 * Instantiates a new {@link GooglePlusSharePanel}.
	 *
	 * @param id
	 *            the id
	 */
	public GooglePlusSharePanel(final String id)
	{
		this(id, null);
	}

	/**
	 * Instantiates a new {@link GooglePlusSharePanel}.
	 *
	 * @param id
	 *            the id
	 * @param model
	 *            the model
	 */
	public GooglePlusSharePanel(final String id, final IModel<GooglePlusShareModelBean> model)
	{
		super(id, model);
		add(googleScriptLabel = newLabel("googleScriptLabel", model));
		add(googlePlusButton = newWebMarkupContainer("googlePlusButton", model));
	}

	/**
	 * Factory method for creating the new {@link Label}. This method is invoked in the constructor
	 * from the derived classes and can be overridden so users can provide their own version of a
	 * new {@link Label}.
	 *
	 * @param id
	 *            the id
	 * @param model
	 *            the model
	 * @return the new {@link Label}
	 */
	protected Label newLabel(final String id, final IModel<GooglePlusShareModelBean> model)
	{
		final Label googleScriptLabel = ComponentFactory.newLabel(id,
			Model.of("{lang: '" + model.getObject().getLocale() + "'}"));
		googleScriptLabel.setEscapeModelStrings(false);
		googleScriptLabel.add(new AttributeModifier("src", model.getObject().getScriptSrc()));
		return googleScriptLabel;
	}

	/**
	 * Factory method for creating the new {@link WebMarkupContainer}. This method is invoked in the
	 * constructor from the derived classes and can be overridden so users can provide their own
	 * version of a new {@link WebMarkupContainer}.
	 *
	 * @param id
	 *            the id
	 * @param model
	 *            the model
	 * @return the new {@link WebMarkupContainer}
	 */
	protected WebMarkupContainer newWebMarkupContainer(final String id,
		final IModel<GooglePlusShareModelBean> model)
	{
		final WebMarkupContainer googlePlusButton = ComponentFactory.newWebMarkupContainer(id,
			model);
		googlePlusButton.add(new AttributeModifier("class", model.getObject().getCssClass()));
		googlePlusButton
			.add(new AttributeModifier("data-annotation", model.getObject().getDataAnnotation()));
		googlePlusButton.add(new AttributeModifier("data-width", model.getObject().getDataWith()));
		googlePlusButton.add(new AttributeModifier("data-href", model.getObject().getDataHref()));
		return googlePlusButton;
	}

}
