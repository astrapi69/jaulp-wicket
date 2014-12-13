package de.alpharogroup.wicket.components.labeled;

import org.apache.wicket.model.IModel;

import de.alpharogroup.wicket.components.labeled.checkbox.LabeledCheckboxPanel;

public class LabeledComponentFactory {
	
	public static<T> LabeledCheckboxPanel<T> newLabeledCheckboxPanel(String id, IModel<T> model, IModel<String> labelModel) {
		return new LabeledCheckboxPanel<T>(id, model, labelModel);
		
	}

}
