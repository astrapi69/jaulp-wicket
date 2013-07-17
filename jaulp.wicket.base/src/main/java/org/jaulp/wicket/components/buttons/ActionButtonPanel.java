package org.jaulp.wicket.components.buttons;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.jaulp.wicket.components.actions.Action;

/**
 * The Class ActionButtonPanel.
 */
public abstract class ActionButtonPanel extends Panel {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The button component. */
	private final Button button;

	/** The Label component. */
	private final Label label;

	/**
	 * Instantiates a new action button panel.
	 *
	 * @param id the id
	 * @param labelModel the label model
	 */
	public ActionButtonPanel(String id, IModel<String> labelModel) {
		super(id);
		add(button = newButton("button"));
		button.add(label = newLabel("label", labelModel));
	}

	/**
	 * Callback method that must be implemented from derived classes.
	 *
	 * @return the action
	 */
	protected abstract Action getAction();

	/**
	 * Gets the button.
	 *
	 * @return the button
	 */
	public Button getButton() {
		return button;
	}

	/**
	 * Gets the label component.
	 * 
	 * @return the label component
	 */
	public Label getLabel() {
		return label;
	}

	/**
	 * Factory method for creating the Button. This method is invoked in the
	 * constructor from the derived classes and can be overridden so users can
	 * provide their own version of a Button.
	 * 
	 * @param id
	 *            the wicket id
	 * @return the Button
	 */
	protected Button newButton(String id) {
		return new Button(id) {
			/**
			 * The serialVersionUID.
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void onSubmit() {
				getAction().execute();
			}
		};
	}

	/**
	 * Factory method for creating the Label. This method is invoked in the
	 * constructor from the derived classes and can be overridden so users can
	 * provide their own version of a Label.
	 *
	 * @param id the id
	 * @param model the model
	 * @return the label
	 */
	protected Label newLabel(String id, IModel<String> model) {
		return new Label(id, model);
	}

}
