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
package org.jaulp.wicket.behaviors.popupoverlay;

import lombok.Getter;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.MarkupContainer;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.panel.GenericPanel;
import org.apache.wicket.model.IModel;

public abstract class PopupoverlayPanel<T> extends GenericPanel<T>
{

	/**
	 * The serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	@Getter
	private MarkupContainer overlayReference;

	@Getter
	private Button openButton;	

	public PopupoverlayPanel(String id, IModel<T> model)
	{
		super(id, model);

		add(openButton = newOpenButton("openButton"));

		add(overlayReference = newOverlayReference("overlayReference", model));
		overlayReference.setOutputMarkupId(true);
		// add class attributte with the markup id from the overlay with the
		// suffix '_open'
		// that indicates that the overlay shell open...
		openButton.add(new AttributeModifier("class", overlayReference.getMarkupId() + "_open"));
		//
		// Button button = new Button("button");
		// overlayReference.add(button);
		// // add class attributte with the markup id from the overlay with the
		// // suffix '_close'
		// // that indicates that the overlay shell close...
		// button.add(new AttributeModifier("class", overlayReference
		// .getMarkupId() + "_close"));
	}

	public void onBeforeRender() {

		addOrReplace(openButton = newOpenButton("openButton"));

		addOrReplace(overlayReference = newOverlayReference("overlayReference", getModel()));
		overlayReference.setOutputMarkupId(true);
		// add class attributte with the markup id from the overlay with the
		// suffix '_open'
		// that indicates that the overlay shell open...
		openButton.add(new AttributeModifier("class", overlayReference.getMarkupId() + "_open"));
		//
		// Button button = new Button("button");
		// overlayReference.add(button);
		// // add class attributte with the markup id from the overlay with the
		// // suffix '_close'
		// // that indicates that the overlay shell close...
		// button.add(new AttributeModifier("class", overlayReference
		// .getMarkupId() + "_close"));
		super.onBeforeRender();
	}

	protected Button newOpenButton(String id)
	{
		return new Button(id);
	}

	protected abstract MarkupContainer newOverlayReference(String id, IModel<T> model);

	protected PopupoverlaySettings newPopupoverlaySettings()
	{
		PopupoverlaySettings settings = new PopupoverlaySettings();
		return settings;
	}

	protected PopupoverlayBehavior newPopupoverlayBehavior()
	{
		return new PopupoverlayBehavior(newPopupoverlaySettings());
	}

}
