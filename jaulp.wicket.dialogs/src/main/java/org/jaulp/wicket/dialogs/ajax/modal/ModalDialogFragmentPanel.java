package org.jaulp.wicket.dialogs.ajax.modal;

import lombok.Getter;

import org.apache.wicket.Component;
import org.apache.wicket.MarkupContainer;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.extensions.ajax.markup.html.modal.ModalWindow;
import org.apache.wicket.markup.html.panel.Fragment;
import org.apache.wicket.markup.html.panel.GenericPanel;
import org.apache.wicket.model.IModel;

import de.alpharogroup.wicket.components.factory.ComponentFactory;

/**
 * A panel that holds a modal window with a fragment as it content.
 */
public abstract class ModalDialogFragmentPanel<T> extends GenericPanel<T>
{

	/**
	 * The serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	@Getter
	private ModalWindow modalWindow;

	private Fragment modalFragment;
	@Getter
	private Component openModalLink;

	public ModalDialogFragmentPanel(final String id, final IModel<T> model)
	{
		super(id, model);
	}

	/**
	 * {@inheritDoc}
	 */
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
	 * Factory method for creating a new Component to open the modal dialog. This method is invoked
	 * in the constructor from the derived classes and can be overridden so users can provide their
	 * own version of a new Component to open the modal dialog.
	 *
	 * @param id
	 *            the wicket id
	 * @param model
	 *            the model
	 * @return the Component to open the modal dialog.
	 */
	protected Component newOpenModalLink(final String id, final IModel<T> model)
	{
		return new AjaxLink<Void>(id)
		{
			private static final long serialVersionUID = 1L;

			@Override
			public void onClick(AjaxRequestTarget target)
			{
				/**
				 * This is how to prevent IE and Firefox dialog popup when trying to
				 * setResponsePage() or set an info message from a wicket modalWindow per below.
				 * Dialog popup demands an answer to:
				 * "This page is asking you to confirm that you want to leave - data you have entered may not be saved."
				 **/
				target.prependJavaScript("Wicket.Window.unloadConfirmation = false;");
				modalWindow.show(target);
			}
		};
	}

	/**
	 * Factory method for creating a new Fragment for the content of the modal dialog. This method
	 * is invoked in the constructor from the derived classes and can be overridden so users can
	 * provide their own version of a new Fragment for the content of the modal dialog.
	 *
	 * @param id
	 *            the id
	 * @param markupId
	 *            The associated id of the associated markup fragment
	 * @param markupProvider
	 *            The component whose markup contains the fragment's markup
	 * @param model
	 *            The model for this fragment
	 * @return the Fragment for the content of the modal dialog.
	 */
	protected Fragment newModalFragment(final String id, final String markupId,
		final MarkupContainer markupProvider, final IModel<T> model)
	{
		final Fragment fragment = ComponentFactory.newFragment(id, markupId, markupProvider, model);
		return fragment;
	}

	/**
	 * Abstract factory method for a new Component that will be added to the fragment.
	 *
	 * @param id
	 *            the id
	 * @param model
	 *            the model
	 * @return the new Component that will be added to the fragment.
	 */
	protected abstract Component newFragmentContent(final String id, final IModel<T> model);

	/**
	 * Factory method for creating a new modal dialog. This method is invoked in the constructor
	 * from the derived classes and can be overridden so users can provide their own version of a
	 * new modal dialog.
	 *
	 * @param id
	 *            the wicket id
	 * @param model
	 *            the model
	 * @return the new modal dialog.
	 */
	protected ModalWindow newModalWindow(final String id, final IModel<T> model)
	{
		final ModalWindow modal = new ModalWindow(id, model);
		return modal;
	}
}
