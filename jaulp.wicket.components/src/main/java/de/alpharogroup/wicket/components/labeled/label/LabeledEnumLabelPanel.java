package de.alpharogroup.wicket.components.labeled.label;

import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.markup.html.basic.EnumLabel;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;

/**
 * Convenience class for labeled EnumLabel.
 * 
 * @param <T>
 *            the generic type
 */
public class LabeledEnumLabelPanel<T> extends Panel {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The right side label. */
	@SuppressWarnings("rawtypes")
	private final EnumLabel enumLabel;

	/** The left side Label component. */
	private final Label label;

	/**
	 * Instantiates a new LabeledEnumLabelPanel.
	 * 
	 * @param id
	 *            the id
	 * @param model
	 *            the model
	 * @param labelModel
	 *            the label model
	 */
	public LabeledEnumLabelPanel(String id, IModel<T> model,
			IModel<String> labelModel) {
		super(id, model);

		PropertyModel<T> viewableLabelModel = new PropertyModel<T>(
				model.getObject(), id);
		add(enumLabel = newEnumLabel("viewableLabel", viewableLabelModel));

		String markupId = enumLabel.getMarkupId();
		add(label = newLabel("label", markupId, labelModel));
	}

	/**
	 * Gets the enum label.
	 *
	 * @return the enum label
	 */
	@SuppressWarnings("rawtypes")
	public EnumLabel getEnumLabel() {
		return enumLabel;
	}

	/**
	 * Gets the label.
	 *
	 * @return the label
	 */
	public Label getLabel() {
		return label;
	}

	/**
	 * New enum label.
	 *
	 * @param id the id
	 * @param viewableLabelModel the viewable label model
	 * @return the enum label
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	protected EnumLabel newEnumLabel(final String id,
			PropertyModel<T> viewableLabelModel) {
		EnumLabel enumLabel = new EnumLabel(id, viewableLabelModel) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			protected String resourceKey(Enum value) {
				return value.name();
			}
		};
		return enumLabel;
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
