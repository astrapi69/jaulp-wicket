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
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.request.resource.IResource;

import de.alpharogroup.wicket.base.BasePanel;
import de.alpharogroup.wicket.components.factory.ComponentFactory;

/**
 * The class {@link CaptchaPanel}.
 *
 * @author Asterios Raptis
 */
public class CaptchaPanel extends BasePanel<CaptchaModelBean>
{

	/**
	 * The serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;
	/** The captcha image. */
	@Getter
	private Image captchaImage;
	/** The text field for the captcha input. */
	@Getter
	private RequiredTextField<String> captchaInput;

	/**
	 * Instantiates a new {@link CaptchaPanel}.
	 *
	 * @param id
	 *            the component id
	 * @param model
	 *            the model
	 */
	public CaptchaPanel(final String id, final IModel<CaptchaModelBean> model)
	{
		super(id, model);
		// Add the image to the panel...
		add(captchaImage = newImage("captchaImage", model));
		// Add the TextField to the panel...
		add(captchaInput = newRequiredTextField("captchaInput", model));
	}

	/**
	 * Factory method for creating a new {@link Image}. This method is invoked in the constructor
	 * from the derived classes and can be overridden so users can provide their own version of a
	 * Button.
	 *
	 * @param id
	 *            the wicket id
	 * @param model
	 *            the model.
	 * @return the new {@link Image}
	 */
	protected Image newImage(final String id, final IModel<CaptchaModelBean> model)
	{
		return newImage(id, model.getObject().getCaptchaImageResource());
	}

	/**
	 * Factory method for creating a new {@link Image}. This method is invoked in the constructor
	 * from the derived classes and can be overridden so users can provide their own version of a
	 * Button.
	 *
	 * @param id
	 *            the wicket id
	 * @param imageResource
	 *            the image resource.
	 * @return the new {@link Image}
	 */
	protected Image newImage(final String id, final IResource imageResource)
	{
		return ComponentFactory.newImage(id, imageResource);
	}

	/**
	 * Factory method for creating a new RequiredTextField. This method is invoked in the
	 * constructor from the derived classes and can be overridden so users can provide their own
	 * version of a RequiredTextField.
	 *
	 * @param id
	 *            the id
	 * @param model
	 *            the captchaModelBean
	 * @return the new RequiredTextField
	 */
	protected RequiredTextField<String> newRequiredTextField(final String id,
		final IModel<CaptchaModelBean> model)
	{
		// Create an TextField for the input...
		final RequiredTextField<String> captchaInput = new RequiredTextField<String>(
			"captchaInput", new PropertyModel<>(model, "captchaInput"))
		{

			/**
			 * The serialVersionUID.
			 */
			private static final long serialVersionUID = 1L;

			/**
			 * {@inheritDoc}
			 */
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
