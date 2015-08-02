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

import lombok.Getter;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.markup.html.panel.GenericPanel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;

import de.alpharogroup.wicket.components.factory.ComponentFactory;

/**
 * The Class BaseModalPanel.
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
	private Form<T> form;
	private TextArea<String> note;
	@Getter
	private AjaxButton cancel;
	@Getter
	private AjaxButton ok;

	/**
	 * Instantiates a new base modal panel.
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
	 * Factory method for creating a new cancel Button. This method is invoked in the constructor
	 * from the derived classes and can be overridden so users can provide their own version of a
	 * new cancel Button.
	 *
	 * @param id
	 *            the wicket id
	 * @return the Button
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
	 * Factory method for creating the Form. This method is invoked in the constructor from the
	 * derived classes and can be overridden so users can provide their own version of a Form.
	 *
	 * @param id
	 *            the id
	 * @param model
	 *            the model
	 * @return the form
	 */
	protected Form<T> newForm(final String id, final IModel<T> model)
	{
		return ComponentFactory.newForm(id, model);
	}

	/**
	 * Factory method for creating a new ok Button. This method is invoked in the constructor from
	 * the derived classes and can be overridden so users can provide their own version of a new ok
	 * Button.
	 *
	 * @param id
	 *            the wicket id
	 * @return the Button
	 */
	protected AjaxButton newOkButton(final String id)
	{
		final AjaxButton ok = new AjaxButton(id)
		{
			/**
			 * The serialVersionUID.
			 */
			private static final long serialVersionUID = 1L;

			@Override
			protected void onError(final AjaxRequestTarget target, final Form<?> form)
			{
				final T obj = BaseModalPanel.this.getModelObject();
				onSelect(target, obj);
			}

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
	 * Factory method for creating a new TextArea. This method is invoked in the constructor from
	 * this class and can be overridden so users can provide their own version of a new TextArea.
	 *
	 * @param id
	 *            the id
	 * @param model
	 *            the model
	 * @return the text area
	 */
	protected TextArea<String> newTextArea(final String id, final IModel<T> model)
	{
		return ComponentFactory.newTextArea(id, new PropertyModel<String>(model, "messageContent"));
	}

	/**
	 * On cancel.
	 *
	 * @param target
	 *            the target
	 */
	abstract void onCancel(final AjaxRequestTarget target);

	/**
	 * On select.
	 *
	 * @param target
	 *            the target
	 * @param object
	 *            the object
	 */
	abstract void onSelect(final AjaxRequestTarget target, final T object);

}
