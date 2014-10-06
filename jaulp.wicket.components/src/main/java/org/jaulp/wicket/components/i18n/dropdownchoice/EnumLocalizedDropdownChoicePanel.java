package org.jaulp.wicket.components.i18n.dropdownchoice;

import java.util.List;

import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.markup.html.form.IChoiceRenderer;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;
import org.jaulp.wicket.components.i18n.dropdownchoice.LocalisedDropDownChoice;
import org.jaulp.wicket.components.labeled.LabeledFormComponentPanel;

public class EnumLocalizedDropdownChoicePanel<T extends Enum<T>, M> extends
		LabeledFormComponentPanel<M> {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	private LocalisedDropDownChoice<T> dropdownChoice;

	public EnumLocalizedDropdownChoicePanel(String id, IModel<M> model,
			IModel<String> labelModel, List<T> enumValues) {
		super(id, model, labelModel);
		setDefaultModel(model);
		PropertyModel<T> pm = new PropertyModel<T>(model.getObject(), id);
		ChoiceRenderer<T> choiceRenderer = new ChoiceRenderer<T>("name", "name");
		
		add(dropdownChoice = newLocalisedDropDownChoice("dropdownChoice", pm,
				enumValues, choiceRenderer));

		add(feedback = newComponentFeedbackPanel("feedback", dropdownChoice));

		String markupId = dropdownChoice.getMarkupId();
		add(label = newLabel("label", markupId, getLabel()));

		// Add bootstrap css...
		getLabelComponent()
				.add(new AttributeAppender("class", "control-label"));

	}

	protected LocalisedDropDownChoice<T> newLocalisedDropDownChoice(
			final String id, final IModel<T> model,
			final List<? extends T> data,
			final IChoiceRenderer<? super T> renderer) {
		LocalisedDropDownChoice<T> ddc = new LocalisedDropDownChoice<T>(id,
				model, data, renderer);
		return ddc;
	}

	public LocalisedDropDownChoice<T> getDropdownChoice() {
		return dropdownChoice;
	}

	/**
	 * {@inheritDoc}
	 */
	public String getInput() {
		return dropdownChoice.getInput();
	}

	/**
	 * {@inheritDoc}
	 */
	protected void convertInput() {
		setConvertedInput(getModel().getObject());
	}

	/**
	 * {@inheritDoc}
	 */
	protected void onBeforeRender() {
		dropdownChoice.setRequired(isRequired());
		super.onBeforeRender();
	}

}
