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
package de.alpharogroup.wicket.components.captcha.draw;

import lombok.Getter;

import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.markup.html.image.Image;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.request.resource.IResource;

import de.alpharogroup.wicket.components.factory.ComponentFactory;

/**
 * The class CaptchaPanel.
 * 
 * @author Asterios Raptis
 */
public class CaptchaPanel extends Panel
{

	/**
	 * The serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;
	@Getter
	Image captchaImage;
	@Getter
	RequiredTextField<String> captchaInput;

	public CaptchaPanel(String id, IModel<CaptchaModel> captchaModel)
	{
		super(id, captchaModel);		
		// Add the image to the panel...
		add(captchaImage = newImage("captchaImage", captchaModel.getObject().getCaptchaImageResource()));
		// Add the TextField to the panel...
		add(captchaInput = newRequiredTextField("captchaInput", captchaModel));
	}

	protected Image newImage(final String id, final IResource imageResource)
	{
		return ComponentFactory.newImage(id, imageResource);
	}

	protected RequiredTextField<String> newRequiredTextField(final String id,
		IModel<CaptchaModel> captchaModel)
	{
		// Create an TextField for the input...
		RequiredTextField<String> captchaInput = new RequiredTextField<String>("captchaInput",
			new PropertyModel<String>(captchaModel.getObject().getProperties(), "captchaInput"))
		{

			/**
			 * The serialVersionUID.
			 */
			private static final long serialVersionUID = 1L;

			@Override
			protected final void onComponentTag(final ComponentTag tag)
			{
				super.onComponentTag(tag);
				// clear the field after each render
				tag.put("value", "");
			}
		};
		captchaInput.setType(String.class);
		return captchaInput;
	}

}
