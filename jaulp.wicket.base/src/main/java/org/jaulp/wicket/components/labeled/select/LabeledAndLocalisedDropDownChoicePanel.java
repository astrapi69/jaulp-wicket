package org.jaulp.wicket.components.labeled.select;

import java.util.List;

import org.apache.wicket.model.IModel;
import org.jaulp.wicket.components.i18n.dropdownchoice.LocalisedDropDownChoice;
import org.jaulp.wicket.components.labeled.LabeledFormComponentPanel;

public class LabeledAndLocalisedDropDownChoicePanel<T> extends LabeledFormComponentPanel<T> {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	private LocalisedDropDownChoice<T> dropDownChoice;

	public LabeledAndLocalisedDropDownChoicePanel(String id) {
		this(id, null, null, null);
	}
	
	public LabeledAndLocalisedDropDownChoicePanel(String id, IModel<T> model, final IModel< ? extends List< ? extends T >> choices, IModel<String> labelModel) {
		super(id, model, labelModel);
		// TODO provide factory methods for all constructors from LocalisedDropDownChoice...
		add(dropDownChoice = newLocalisedDropDownChoice("dropdown", choices));

		add(feedback = newComponentFeedbackPanel("feedback", dropDownChoice));

		String markupId = dropDownChoice.getMarkupId();
		add(label = newLabel("label", markupId, this.labelModel));
	}
	


	/**
	 * Factory method for creating the LocalisedDropDownChoice. This method is invoked in the
	 * constructor from this class and can be overridden so users can
	 * provide their own version of a LocalisedDropDownChoice.
	 *
	 * @param id the id
	 * @param model the model
	 * @return the text area
	 */
	protected LocalisedDropDownChoice<T> newLocalisedDropDownChoice(String id, final IModel< ? extends List< ? extends T >> model) {
		LocalisedDropDownChoice<T> dropDownChoice = new LocalisedDropDownChoice<T>(id, model);
		dropDownChoice.setOutputMarkupId(true);
		return dropDownChoice;
	}

	/**
	 * {@inheritDoc}
	 */
	public String getInput() {
		return dropDownChoice.getInput();
	}

	/**
	 * {@inheritDoc}
	 */
	protected void convertInput() {
		setConvertedInput(dropDownChoice.getConvertedInput());
	}

	/**
	 * {@inheritDoc}
	 */
	protected void onBeforeRender() {
		dropDownChoice.setRequired(isRequired());
		super.onBeforeRender();
	}

}
