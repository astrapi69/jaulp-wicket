package org.jaulp.wicket.dialogs.panels;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;

public abstract class DialogPanel<T> extends Panel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DialogPanel(String id, final IModel<T> model, final IModel<String> labelModel) {
		super(id);
	}
	
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
	protected Label newLabel(String id, IModel<String> model) {
		Label label = new Label(id, model);
		return label;
	}

}
