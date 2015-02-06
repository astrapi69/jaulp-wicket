package de.alpharogroup.wicket.components.labeled.label;

import lombok.Getter;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;

import de.alpharogroup.wicket.components.factory.ComponentFactory;

/**
 * Convenience class for labeled Label for form uneditable components.
 * 
 * @param <T>
 *            the generic type
 */
public class LabeledLabelPanel<T> extends Panel
{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The left side Label component. */
	@Getter
	private final Label label;

	/** The right side label. */
	@Getter
	private final Label viewableLabel;

	/**
	 * Instantiates a new LabeledDateTextfieldPanel.
	 * 
	 * @param id
	 *            the id
	 * @param model
	 *            the model
	 * @param labelModel
	 *            the label model
	 */
	public LabeledLabelPanel(String id, IModel<T> model, IModel<String> labelModel)
	{
		super(id, model);

		PropertyModel<T> viewableLabelModel = new PropertyModel<>(model.getObject(), id);

		add(viewableLabel = newLabel("viewableLabel", viewableLabelModel));

		String markupId = viewableLabel.getMarkupId();
		add(label = newLabel("label", markupId, labelModel));
	}

	protected Label newLabel(String id, PropertyModel<T> viewableLabelModel)
	{
		return ComponentFactory.newLabel(id, viewableLabelModel);
	}

	/**
	 * Factory method for creating the Label. This method is invoked in the constructor from the
	 * derived classes and can be overridden so users can provide their own version of a Label.
	 * 
	 * @param id
	 *            the id
	 * @param forId
	 *            the for id
	 * @param model
	 *            the model
	 * @return the label
	 */
	protected Label newLabel(String id, String forId, IModel<String> model)
	{
		return ComponentFactory.newLabel(id, forId, model);
	}
}
