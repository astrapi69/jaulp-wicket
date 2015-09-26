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
package de.alpharogroup.wicket.dialogs.ajax.modal;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.markup.html.panel.GenericPanel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;

import de.alpharogroup.wicket.components.factory.ComponentFactory;
import lombok.Getter;

/**
 * The Class {@link BaseModalPanel}.
 *
 * @param <T>
 *            the generic type
 */
public abstract class BaseModalPanel<T> extends GenericPanel<T>
{
	/**
	 * The serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;

	/** The form. */
	private Form<T> form;

	/** The note. */
	private TextArea<String> note;

	/** The cancel. */
	@Getter
	private AjaxButton cancel;

	/** The ok. */
	@Getter
	private AjaxButton ok;

	/**
	 * Instantiates a new {@link BaseModalPanel}.
	 *
	 * @param id
	 *            the id
	 * @param model
	 *            the model
	 */
	public BaseModalPanel(final String id, final IModel<T> model)
	{
		super(id, model);
		setOutputMarkupId(true);
		add(form = newForm("form", model));
		form.add(note = newTextArea("messageContent", model));
		form.add(cancel = newCancelButton("cancelButton"));
		form.add(ok = newOkButton("okButton"));
	}

	/**
	 * Factory method for creating the new cancel {@link AjaxButton}. This method is invoked in the
	 * constructor from the derived classes and can be overridden so users can provide their own
	 * version of a new cancel {@link AjaxButton}.
	 *
	 * @param id
	 *            the id
	 * @return the new cancel {@link AjaxButton}
	 */
	protected AjaxButton newCancelButton(final String id)
	{
		final AjaxButton close = new AjaxButton(id)
		{
			/**
			 * The serialVersionUID.
			 */
			private static final long serialVersionUID = 1L;

			@Override
			protected void onError(final AjaxRequestTarget target, final Form<?> form)
			{
				target.add(note);
				onCancel(target);
			}

			@Override
			public void onSubmit(final AjaxRequestTarget target, final Form<?> form)
			{
				target.add(note);
				onCancel(target);
			}
		};
		return close;
	}

	/**
	 * Factory method for create the new {@link Form}. This method is invoked in the constructor
	 * from the derived classes and can be overridden so users can provide their own version of a
	 * new {@link Form}.
	 *
	 * @param id
	 *            the id
	 * @param model
	 *            the model
	 * @return the new {@link Form}
	 */
	protected Form<T> newForm(final String id, final IModel<T> model)
	{
		return ComponentFactory.newForm(id, model);
	}


	/**
	 * Factory method for creating the new ok {@link AjaxButton}. This method is invoked in the
	 * constructor from the derived classes and can be overridden so users can provide their own
	 * version of a new ok {@link AjaxButton}.
	 *
	 * @param id
	 *            the id
	 * @return the new ok {@link AjaxButton}
	 */
	protected AjaxButton newOkButton(final String id)
	{
		final AjaxButton ok = new AjaxButton(id)
		{
			/**
			 * The serialVersionUID.
			 */
			private static final long serialVersionUID = 1L;

			/**
			 * {@inheritDoc}
			 */
			@Override
			protected void onError(final AjaxRequestTarget target, final Form<?> form)
			{
				final T obj = BaseModalPanel.this.getModelObject();
				onSelect(target, obj);
			}

			/**
			 * {@inheritDoc}
			 */
			@Override
			protected void onSubmit(final AjaxRequestTarget target, final Form<?> form)
			{
				final T obj = BaseModalPanel.this.getModelObject();
				onSelect(target, obj);
			}
		};
		return ok;
	}

	/**
	 * Factory method for create the new {@link TextArea}. This method is invoked in the constructor
	 * from the derived classes and can be overridden so users can provide their own version of a
	 * new {@link TextArea}.
	 *
	 * @param id
	 *            the id
	 * @param model
	 *            the model
	 * @return the new {@link TextArea}
	 */
	protected TextArea<String> newTextArea(final String id, final IModel<T> model)
	{
		return ComponentFactory.newTextArea(id, new PropertyModel<String>(model, "messageContent"));
	}

	/**
	 * Abstract callback method that have to be overwritten to provide specific action for cancel.
	 *
	 * @param target
	 *            the target
	 */
	protected abstract void onCancel(final AjaxRequestTarget target);

	/**
	 * Abstract callback method that have to be overwritten to provide specific action for select.
	 *
	 * @param target
	 *            the target
	 * @param object
	 *            the object
	 */
	protected abstract void onSelect(final AjaxRequestTarget target, final T object);

}
