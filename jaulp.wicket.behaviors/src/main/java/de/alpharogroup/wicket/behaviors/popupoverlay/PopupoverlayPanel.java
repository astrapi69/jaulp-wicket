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
package de.alpharogroup.wicket.behaviors.popupoverlay;

import lombok.Getter;

import org.apache.wicket.Component;
import org.apache.wicket.MarkupContainer;
import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.panel.GenericPanel;
import org.apache.wicket.model.IModel;

/**
 * The Class PopupoverlayPanel is production ready and is also an example how to use the
 * {@link PopupoverlayBehavior}.
 *
 * @param <T>
 *            the generic type of the model.
 */
public abstract class PopupoverlayPanel<T> extends GenericPanel<T>
{

	/** The serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * The overlay reference.
	 */
	@Getter
	private MarkupContainer overlayReference;

	/**
	 * The open button.
	 */
	@Getter
	private Button openButton;

	/**
	 * Instantiates a new popupoverlay panel.
	 *
	 * @param id
	 *            the id
	 * @param model
	 *            the model
	 */
	public PopupoverlayPanel(String id, IModel<T> model)
	{
		super(id, model);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void onBeforeRender()
	{


		addOrReplace(overlayReference = newOverlayReference("overlayReference", getModel()));
		overlayReference.add(newPopupoverlayBehavior());
		overlayReference.setOutputMarkupId(true);

		addOrReplace(openButton = newOpenButton("openButton"));
		// add class attributte with the markup id from the overlay with the
		// suffix '_open' that indicates that the overlay shell open...
		openButton.add(new AttributeAppender("class", " " + overlayReference.getMarkupId()
			+ "_open"));
		super.onBeforeRender();
	}

	/**
	 * Factory method for create the Button. This method is invoked in the method
	 * {@link Component#onBeforeRender()} from the derived classes and can be overridden so users
	 * can provide their own version of a Button.
	 *
	 * @param id
	 *            the wicket id
	 * @return the Button
	 */
	protected Button newOpenButton(String id)
	{
		return new Button(id);
	}

	/**
	 * Abstract factory method for create the new overlay reference MarkupContainer. This method is
	 * invoked in the method {@link Component#onBeforeRender()} from the derived classes and must be
	 * overridden so users can provide their own version of the overlay component.
	 *
	 * Note: If you have in your settings the attribute 'blur' set to false than you have to provide
	 * a close component. As from the documentation of the popupoverlay plugin this component have
	 * to provide the class attribute with the value 'overlayReference.getMarkupId() + "_close"'.
	 *
	 * For instance:
	 *
	 * <pre>
	 * Button button = new Button(&quot;button&quot;);
	 * getOverlayReference().add(button);
	 * // add class attributte with the markup id from the overlay with the
	 * // suffix '_close' that indicates that the overlay shell close...
	 * button.add(new AttributeModifier(&quot;class&quot;, getOverlayReference().getMarkupId() + &quot;_close&quot;));
	 * </pre>
	 *
	 * @param id
	 *            the id
	 * @param model
	 *            the model
	 * @return the markup container
	 */
	protected abstract MarkupContainer newOverlayReference(String id, IModel<T> model);

	/**
	 * Factory method for create the {@link PopupoverlaySettings}. This method is invoked in the
	 * method {@link #newPopupoverlayBehavior()} from the derived classes and can be overridden so
	 * users can provide their own version of the {@link PopupoverlaySettings}.
	 *
	 * @return the {@link PopupoverlaySettings}.
	 */
	protected PopupoverlaySettings newPopupoverlaySettings()
	{
		PopupoverlaySettings settings = new PopupoverlaySettings();
		return settings;
	}

	/**
	 * Factory method for create the {@link PopupoverlayBehavior}. This method is invoked in the
	 * method {@link Component#onBeforeRender()} from the derived classes and can be overridden so
	 * users can provide their own version of the {@link PopupoverlayBehavior}.
	 *
	 * @return the popupoverlay behavior
	 */
	protected PopupoverlayBehavior newPopupoverlayBehavior()
	{
		return new PopupoverlayBehavior(newPopupoverlaySettings());
	}

}
