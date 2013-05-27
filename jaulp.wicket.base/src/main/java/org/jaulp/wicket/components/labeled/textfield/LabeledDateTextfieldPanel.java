package org.jaulp.wicket.components.labeled.textfield;

import java.util.Date;
import java.util.Locale;

import org.apache.wicket.datetime.StyleDateConverter;
import org.apache.wicket.datetime.markup.html.form.DateTextField;
import org.apache.wicket.extensions.yui.calendar.DatePicker;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;
import org.jaulp.wicket.components.labeled.LabeledFormComponentPanel;

/**
 * Convenience class for labeled DateTextfield.
 *
 * @param <T> the generic type
 */
public class LabeledDateTextfieldPanel<T> extends LabeledFormComponentPanel<T> {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
		
	/** The text field. */
	private final DateTextField dateTextField;
	
	public DateTextField getDateTextField() {
		return dateTextField;
	}

	/**
	 * Instantiates a new LabeledDateTextfieldPanel.
	 *
	 * @param id the id
	 */
	public LabeledDateTextfieldPanel(String id) {
		this(id, null, null);
	}

	/**
	 * Instantiates a new LabeledDateTextfieldPanel.
	 *
	 * @param id the id
	 * @param model the model
	 * @param labelModel the label model
	 */
	public LabeledDateTextfieldPanel(String id, IModel<T> model, IModel<String> labelModel) {
		super(id, model, labelModel);		
		PropertyModel<Date> textFieldModel = new PropertyModel<Date>(model.getObject(), id);
		add(dateTextField = newDateTextField("dateTextField", textFieldModel));

		add(feedback = newComponentFeedbackPanel("feedback", dateTextField));

		String markupId = dateTextField.getMarkupId();
		add(label = newLabel("label", markupId, getLabel()));
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
	public String getInput() {
		return dateTextField.getInput();
	}

	/**
	 * Factory method for creating the DateTextField. This method is invoked in the
	 * constructor from the derived classes and can be overridden so users can
	 * provide their own version of a DateTextField.
	 *
	 * @param id the id
	 * @param model the model
	 * @return the text field
	 */
	protected DateTextField newDateTextField(String id, IModel<Date> model) {
		
		DateTextField dateTextField = new DateTextField(id, model,new StyleDateConverter("S-", true)) {
			/**
			 * The serialVersionUID.
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public Locale getLocale() {
				return getSession().getLocale();
			}
		};
		DatePicker datePicker = new DatePicker();
		datePicker.setShowOnFieldClick(true);
		dateTextField.add(datePicker);
		dateTextField.setOutputMarkupId(true);
		return dateTextField;
	}

	/**
	 * {@inheritDoc}
	 */
	protected void onBeforeRender() {
		dateTextField.setRequired(isRequired());
		super.onBeforeRender();
	}
}
