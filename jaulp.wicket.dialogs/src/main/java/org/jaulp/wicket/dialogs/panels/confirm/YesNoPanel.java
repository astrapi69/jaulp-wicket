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
package org.jaulp.wicket.dialogs.panels.confirm;

import java.util.EventObject;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.event.Broadcast;
import org.apache.wicket.event.IEventSink;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.StringResourceModel;
import org.jaulp.wicket.dialogs.panels.DialogPanel;

/**
 * The Class YesNoPanel.
 *
 * @param <T>
 *            the generic type
 */
public abstract class YesNoPanel<T> extends DialogPanel<T>
{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The Label component. */
	private final Label label;

	/** The no button. */
	private final AjaxButton noButton;

	/** The yes button. */
	private final AjaxButton yesButton;

	/**
	 * Instantiates a new yes no panel.
	 *
	 * @param id
	 *            the id
	 * @param model
	 *            the model
	 * @param labelModel
	 *            the label model
	 */
	public YesNoPanel(String id, final IModel<T> model, final IModel<String> labelModel)
	{
		super(id, model, labelModel);
		add(label = newLabel(labelModel));

		yesButton = new AjaxButton("yesButton")
		{
			/**
			 * The serialVersionUID.
			 */
			private static final long serialVersionUID = 1L;

			@Override
			protected void onError(AjaxRequestTarget target, Form<?> form)
			{
			}

			@Override
			protected void onSubmit(final AjaxRequestTarget target, final Form<?> form)
			{
				final T object = model.getObject();
				onYes(target, object);
			}

			@SuppressWarnings("unused")
			public <E extends EventObject> void send(IEventSink sink, Broadcast broadcast, E payload)
			{
			}
		};
		IModel<String> yesLabelModel = new StringResourceModel("global.yes.label", this, null);
		yesButton.add(new Label("yesLabel", yesLabelModel));
		add(yesButton);

		noButton = new AjaxButton("noButton")
		{
			/**
			 * The serialVersionUID.
			 */
			private static final long serialVersionUID = 1L;

			@Override
			protected void onError(AjaxRequestTarget target, Form<?> form)
			{
			}

			@Override
			protected void onSubmit(final AjaxRequestTarget target, final Form<?> form)
			{
				final T object = model.getObject();
				onNo(target, object);
			}

			@SuppressWarnings("unused")
			public <E extends EventObject> void send(IEventSink sink, Broadcast broadcast, E payload)
			{
			}
		};
		IModel<String> noLabelModel = new StringResourceModel("global.no.label", this, null);
		noButton.add(new Label("noLabel", noLabelModel));
		add(noButton);
	}

	/**
	 * Gets the label.
	 *
	 * @return the label
	 */
	public Label getLabel()
	{
		return label;
	}

	/**
	 * Gets the no button.
	 *
	 * @return the no button
	 */
	public AjaxButton getNoButton()
	{
		return noButton;
	}

	/**
	 * Gets the yes button.
	 *
	 * @return the yes button
	 */
	public AjaxButton getYesButton()
	{
		return yesButton;
	}

	/**
	 * Factory method for creating the Label. This method is invoked in the constructor from the
	 * derived classes and can be overridden so users can provide their own version of a Label.
	 *
	 * @param model
	 *            the model
	 * @return the label
	 */
	protected Label newLabel(IModel<String> model)
	{
		return super.newLabel("message", model);
	}

	/**
	 * On no.
	 *
	 * @param target
	 *            the target
	 * @param object
	 *            the object
	 */
	public abstract void onNo(final AjaxRequestTarget target, final T object);

	/**
	 * On yes.
	 *
	 * @param target
	 *            the target
	 * @param object
	 *            the object
	 */
	public abstract void onYes(final AjaxRequestTarget target, final T object);

}
