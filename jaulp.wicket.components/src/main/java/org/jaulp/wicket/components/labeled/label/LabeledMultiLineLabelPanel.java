package org.jaulp.wicket.components.labeled.label;

import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.basic.MultiLineLabel;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;

/**
 * Convenience class for labeled MultiLineLabel for form uneditable components.
 * 
 * @param <T>
 *            the generic type
 */
public class LabeledMultiLineLabelPanel<T> extends Panel {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The left side Label component. */
	private final Label label;

	/**
	 * Gets the label.
	 *
	 * @return the label
	 */
	public Label getLabel() {
		return label;
	}

	/** The right side label. */
	private final MultiLineLabel viewableLabel;

	public MultiLineLabel getViewableLabel() {
		return viewableLabel;
	}

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
	public LabeledMultiLineLabelPanel(String id, IModel<T> model,
			IModel<String> labelModel) {
		super(id, model);

		PropertyModel<T> viewableLabelModel = new PropertyModel<T>(
				model.getObject(), id);

		add(viewableLabel = newMultiLineLabelLabel("viewableLabel", viewableLabelModel));

		String markupId = viewableLabel.getMarkupId();
		add(label = newLabel("label", markupId, labelModel));
	}

	protected MultiLineLabel newMultiLineLabelLabel(String string, PropertyModel<T> viewableLabelModel) {
		MultiLineLabel label = new MultiLineLabel(string, viewableLabelModel);
		return label;
	}

	/**
	 * Factory method for creating the Label. This method is invoked in the
	 * constructor from the derived classes and can be overridden so users can
	 * provide their own version of a Label.
	 * 
	 * @param id
	 *            the id
	 * @param forId
	 *            the for id
	 * @param model
	 *            the model
	 * @return the label
	 */
	protected Label newLabel(String id, String forId, IModel<String> model) {
		Label label = new Label(id, model);
		label.add(new AttributeAppender("for", Model.of(forId), " "));
		return label;
	}
}
