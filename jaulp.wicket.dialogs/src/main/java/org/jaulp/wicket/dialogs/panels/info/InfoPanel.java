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
package org.jaulp.wicket.dialogs.panels.info;

import java.util.EventObject;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.event.Broadcast;
import org.apache.wicket.event.IEventSink;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.model.IModel;
import org.jaulp.wicket.dialogs.panels.DialogPanel;

public abstract class InfoPanel<T> extends DialogPanel<T>
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InfoPanel(String id, final IModel<T> model, final IModel<String> labelModel)
	{
		super(id, model, labelModel);
		add(newLabel("message", labelModel));

		final AjaxButton closeButton = new AjaxButton("closeButton")
		{
			/**
			 * The serialVersionUID.
			 */
			private static final long serialVersionUID = 1L;

			@Override
			protected void onSubmit(final AjaxRequestTarget target, final Form<?> form)
			{
				final T object = model.getObject();
				onClose(target, object);
			}

			@SuppressWarnings("unused")
			public <E extends EventObject> void send(IEventSink sink, Broadcast broadcast, E payload)
			{
			}

			@Override
			protected void onError(AjaxRequestTarget target, Form<?> form)
			{
			}
		};
		add(closeButton);
	}

	public abstract void onClose(final AjaxRequestTarget target, final T object);

}
