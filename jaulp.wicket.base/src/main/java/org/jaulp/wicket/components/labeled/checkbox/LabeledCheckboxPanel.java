package org.jaulp.wicket.components.labeled.checkbox;

import org.apache.wicket.markup.html.form.CheckBox;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;
import org.jaulp.wicket.components.labeled.LabeledFormComponentPanel;

/**
 * Convenience class for labeled checkbox.
 */
public class LabeledCheckboxPanel<T> extends LabeledFormComponentPanel<T> {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
		
	/** The CheckBox component. */
	private CheckBox checkBox;

	/**
	 * Instantiates a new LabeledCheckboxPanel object.
	 *
	 * @param id the id
	 */
	public LabeledCheckboxPanel(String id) {
		this(id, null, null);
	}

	/**
	 * Instantiates a new LabeledCheckboxPanel object.
	 *
	 * @param id the id
	 * @param model the model
	 */
	public LabeledCheckboxPanel(String id, IModel<T> model, IModel<String> labelModel) {
		super(id, model, labelModel);
		PropertyModel<Boolean> propertyModel = new PropertyModel<Boolean>(model.getObject(), id);
        add(checkBox = newCheckBox("checkBox", propertyModel));

		add(feedback = newComponentFeedbackPanel("feedback", checkBox));

		String markupId = checkBox.getMarkupId();
		add(label = newLabel("label", markupId, getLabel()));
  
	}
	
	/** 
	 * Factory method for creating the CheckBox. This method is invoked in the
	 * constructor from this class and can be overridden so users can
	 * provide their own version of a CheckBox.
	 *
	 * @param id the id
	 * @param model the model
	 * @return the created CheckBox
	 */
	protected CheckBox newCheckBox(String id, IModel<Boolean> model) {
		CheckBox checkBox = new CheckBox(id, model);
		checkBox.setOutputMarkupId(true);
		return checkBox;
	}

	/**
	 * {@inheritDoc}
	 */
	public String getInput() {
		return checkBox.getInput();
	}

	/**
	 * {@inheritDoc}
	 */
	protected void convertInput() {
//		setConvertedInput(checkBox.getConvertedInput());
	}

	/**
	 * {@inheritDoc}
	 */
	protected void onBeforeRender() {
		checkBox.setRequired(isRequired());
		super.onBeforeRender();
	}

}
