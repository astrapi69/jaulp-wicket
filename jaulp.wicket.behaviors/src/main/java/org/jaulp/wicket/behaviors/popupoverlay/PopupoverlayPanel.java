package org.jaulp.wicket.behaviors.popupoverlay;

import lombok.Getter;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.MarkupContainer;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;

public abstract class PopupoverlayPanel<T> extends Panel
{

	/**
	 * The serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	@Getter
	private MarkupContainer overlayReference;

	Button openButton;

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
