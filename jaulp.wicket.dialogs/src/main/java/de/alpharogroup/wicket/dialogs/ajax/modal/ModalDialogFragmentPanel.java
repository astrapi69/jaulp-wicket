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

import org.apache.wicket.Component;
import org.apache.wicket.MarkupContainer;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.extensions.ajax.markup.html.modal.ModalWindow;
import org.apache.wicket.markup.html.panel.Fragment;
import org.apache.wicket.markup.html.panel.GenericPanel;
import org.apache.wicket.model.IModel;

import de.alpharogroup.wicket.components.factory.ComponentFactory;
import lombok.Getter;

/**
 * The Class {@link ModalDialogFragmentPanel} is a panel that holds a modal window with a fragment
 * as it content.
 *
 * @param <T>
 *            the generic type of the model object.
 */
public abstract class ModalDialogFragmentPanel<T> extends GenericPanel<T>
{

	/**
	 * The serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * The constant for the javascript to prevent to show confirmation dialog from browser(IE and
	 * Firefox). This is how to prevent IE and Firefox dialog popup when trying to setResponsePage()
	 * or set an info message from a wicket modalWindow per below. Dialog popup demands an answer
	 * to:
	 * "This page is asking you to confirm that you want to leave - data you have entered may not be saved."
	 **/
	public static final String WICKET_WINDOW_UNLOAD_CONFIRMATION_FALSE_JS = "Wicket.Window.unloadConfirmation = false;";

	/** The modal window. */
	@Getter
	private ModalWindow modalWindow;

	/** The modal fragment. */
	private Fragment modalFragment;

	/** The open modal link. */
	@Getter
	private MarkupContainer openModalLink;

	/**
	 * Instantiates a new {@link ModalDialogFragmentPanel}.
	 *
	 * @param id
	 *            the id
	 * @param model
	 *            the model
	 */
	public ModalDialogFragmentPanel(final String id, final IModel<T> model)
	{
		super(id, model);
	}

	/**
	 * Abstract factory method for create a new {@link Component} that will be added to the
	 * fragment. This method is invoked in the constructor from the derived classes and have to be
	 * implemented so users can provide their own version of a new {@link Component}.
	 *
	 * @param id
	 *            the id
	 * @param model
	 *            the model
	 * @return the new {@link Component} that will be added to the fragment.
	 */
	protected abstract Component newFragmentContent(final String id, final IModel<T> model);

	/**
	 * Factory method for creating a new {@link Fragment} for the content of the modal dialog. This
	 * method is invoked in the constructor from the derived classes and can be overridden so users
	 * can provide their own version of a new {@link Fragment} for the content of the modal dialog.
	 *
	 * @param id
	 *            the id
	 * @param markupId
	 *            The associated id of the associated markup fragment
	 * @param markupProvider
	 *            The component whose markup contains the fragment's markup
	 * @param model
	 *            The model for this fragment
	 * @return the new {@link Fragment} for the content of the modal dialog.
	 */
	protected Fragment newModalFragment(final String id, final String markupId,
		final MarkupContainer markupProvider, final IModel<T> model)
	{
		final Fragment fragment = ComponentFactory.newFragment(id, markupId, markupProvider, model);
		return fragment;
	}

	/**
	 * Factory method for creating a new {@link ModalWindow}. This method is invoked in the
	 * constructor from the derived classes and can be overridden so users can provide their own
	 * version of a new {@link ModalWindow}.
	 *
	 * @param id
	 *            the wicket id
	 * @param model
	 *            the model
	 * @return the new {@link ModalWindow}.
	 */
	protected ModalWindow newModalWindow(final String id, final IModel<T> model)
	{
		final ModalWindow modal = new ModalWindow(id, model);
		return modal;
	}

	/**
	 * Factory method for creating a new {@link Component} to open the {@link ModalWindow}. This
	 * method is invoked in the constructor from the derived classes and can be overridden so users
	 * can provide their own version of a new {@link Component} to open the {@link ModalWindow}.
	 *
	 * @param id
	 *            the wicket id
	 * @param model
	 *            the model
	 * @return the new {@link Component} to open the {@link ModalWindow}.
	 */
	protected MarkupContainer newOpenModalLink(final String id, final IModel<T> model)
	{
		return new AjaxLink<Void>(id)
		{

			/** The Constant serialVersionUID. */
			private static final long serialVersionUID = 1L;

			/**
			 * {@inheritDoc}
			 */
			@Override
			public void onClick(final AjaxRequestTarget target)
			{
				ModalDialogFragmentPanel.this.onShow(target);
			}
		};
	}

	/**
	 * Abstract callback method that have to be overwritten to provide specific action for close the
	 * {@link ModalWindow}.
	 *
	 * @param target
	 *            the target
	 */
	protected void onClose(final AjaxRequestTarget target)
	{
		getModalWindow().close(target);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void onInitialize()
	{
		super.onInitialize();
		add(modalWindow = newModalWindow("modal", getModel()));
		modalWindow.setContent(modalFragment = newModalFragment(modalWindow.getContentId(),
			"modalContent", this, getModel()));
		modalFragment.add(newFragmentContent("fragmentContent", getModel()));
		add(openModalLink = newOpenModalLink("openModal", getModel()));
	}

	/**
	 * Callback method to hang on when the dialog is open.
	 *
	 * @param target
	 *            the ajax request target.
	 */
	protected void onShow(final AjaxRequestTarget target)
	{
		/**
		 * This is how to prevent IE and Firefox dialog popup when trying to setResponsePage() or
		 * set an info message from a wicket modalWindow per below. Dialog popup demands an answer
		 * to:
		 * "This page is asking you to confirm that you want to leave - data you have entered may not be saved."
		 **/
		target.prependJavaScript(WICKET_WINDOW_UNLOAD_CONFIRMATION_FALSE_JS);
		getModalWindow().show(target);
	}
}
