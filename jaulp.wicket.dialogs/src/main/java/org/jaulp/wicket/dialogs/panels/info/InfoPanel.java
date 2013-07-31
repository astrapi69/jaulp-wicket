package org.jaulp.wicket.dialogs.panels.info;

import java.util.EventObject;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.event.Broadcast;
import org.apache.wicket.event.IEventSink;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.model.IModel;
import org.jaulp.wicket.dialogs.panels.DialogPanel;

public abstract class InfoPanel<T> extends DialogPanel<T> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InfoPanel(String id, final IModel<T> model, final IModel<String> labelModel) {
		super(id, model, labelModel);
		add(newLabel(labelModel));

		final AjaxButton closeButton = new AjaxButton("closeButton") {
			/**
			 * The serialVersionUID.
			 */
			private static final long serialVersionUID = 1L;

			@Override
			protected void onSubmit(final AjaxRequestTarget target,
					final Form<?> form) {
				final T object = model.getObject();
				onClose(target, object);
			}

			@SuppressWarnings("unused")
			public <E extends EventObject> void send(IEventSink sink,
					Broadcast broadcast, E payload) {
			}

			@Override
			protected void onError(AjaxRequestTarget target, Form<?> form) {
			}
		};
		add(closeButton);
	}
	
	public abstract void onClose(final AjaxRequestTarget target,final T object);
	


	/**
	 * Factory method for creating the Label. This method is invoked in the
	 * constructor from the derived classes and can be overridden so users can
	 * provide their own version of a Label.
	 * 
	 * @param id
	 *            the id
	 * @param model
	 *            the model
	 * @return the label
	 */
	protected Label newLabel(IModel<String> model) {
		return super.newLabel("message", model);
	}

}
