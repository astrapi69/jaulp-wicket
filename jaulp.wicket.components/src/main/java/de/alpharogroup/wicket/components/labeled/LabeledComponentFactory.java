package de.alpharogroup.wicket.components.labeled;

import org.apache.wicket.model.IModel;

import de.alpharogroup.wicket.components.labeled.checkbox.LabeledCheckboxPanel;
import de.alpharogroup.wicket.components.labeled.textfield.LabeledTextFieldPanel;

/**
 * A factory for creating LabeledComponent objects.
 */
public class LabeledComponentFactory {
	
	/**
	 *Factory method for create a new {@link LabeledCheckboxPanel}.
	 *
	 * @param <T> the generic type
	 * @param id the id
	 * @param model the model
	 * @param labelModel the label model
	 * @return the new {@link LabeledCheckboxPanel}
	 */
	public static<T> LabeledCheckboxPanel<T> newLabeledCheckboxPanel(String id, IModel<T> model, IModel<String> labelModel) {
		LabeledCheckboxPanel<T> labeledCheckboxPanel = new LabeledCheckboxPanel<T>(id, model, labelModel);
		labeledCheckboxPanel.setOutputMarkupId(true);
		return labeledCheckboxPanel;
		
	}

	/**
	 * Factory method for create a new {@link LabeledTextFieldPanel}.
	 *
	 * @param <T> the generic type
	 * @param id            the id
	 * @param model            the model
	 * @param labelModel the label model
	 * @return the new {@link LabeledTextFieldPanel}
	 */
	public static<T> LabeledTextFieldPanel<T> newLabeledTextFieldPanel(String id, IModel<T> model, IModel<String> labelModel) {
		LabeledTextFieldPanel<T> labeledTextField = new LabeledTextFieldPanel<T>(id, model, labelModel);
		labeledTextField.setOutputMarkupId(true);
		return labeledTextField;
	}

}
