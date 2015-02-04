package de.alpharogroup.wicket.components.i18n.dropdownchoice;

import java.util.List;

import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.model.IModel;

import de.alpharogroup.wicket.components.labeled.LabeledFormComponentPanel;

public class LocalizedDropdownChoicePanel<T> extends LabeledFormComponentPanel<T>
{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	private LocalisedDropDownChoice<T> dropdownChoice;

	public LocalizedDropdownChoicePanel(String id, IModel<T> model, IModel<String> labelModel,
		List<T> enumValues)
	{
		super(id, model, labelModel);

		dropdownChoice = new LocalisedDropDownChoice<T>("dropdownChoice", model, enumValues);
		add(dropdownChoice);

		add(feedback = newComponentFeedbackPanel("feedback", dropdownChoice));

		String markupId = dropdownChoice.getMarkupId();
		add(label = newLabel("label", markupId, getLabel()));

		// Add bootstrap css...
		getLabelComponent().add(new AttributeAppender("class", "control-label"));

	}

	public LocalisedDropDownChoice<T> getDropdownChoice()
	{
		return dropdownChoice;
	}

	/**
	 * {@inheritDoc}
	 */
	public String getInput()
	{
		return dropdownChoice.getInput();
	}

	/**
	 * {@inheritDoc}
	 */
	public void convertInput()
	{
		setConvertedInput(dropdownChoice.getConvertedInput());
	}

	/**
	 * {@inheritDoc}
	 */
	protected void onBeforeRender()
	{
		dropdownChoice.setRequired(isRequired());
		super.onBeforeRender();
	}

}
