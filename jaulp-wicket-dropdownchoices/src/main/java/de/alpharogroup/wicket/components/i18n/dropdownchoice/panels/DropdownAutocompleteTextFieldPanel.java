package de.alpharogroup.wicket.components.i18n.dropdownchoice.panels;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormComponentUpdatingBehavior;
import org.apache.wicket.extensions.ajax.markup.html.autocomplete.AutoCompleteTextField;
import org.apache.wicket.extensions.ajax.markup.html.autocomplete.DefaultCssAutoCompleteTextField;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.FormComponentPanel;
import org.apache.wicket.markup.html.form.IChoiceRenderer;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.util.lang.Args;
import org.apache.wicket.util.string.Strings;

import de.alpharogroup.wicket.components.factory.ComponentFactory;
import de.alpharogroup.wicket.components.i18n.dropdownchoice.LocalisedDropDownChoice;
import de.alpharogroup.wicket.model.dropdownchoices.TwoDropDownChoicesBean;
import lombok.Getter;

/**
 * The class {@link DropdownAutocompleteTextFieldPanel}.
 *
 * @param <String>
 *            the generic type
 */
public abstract class DropdownAutocompleteTextFieldPanel<T> extends FormComponentPanel<TwoDropDownChoicesBean<T>> {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The Constant ROOT_CHOICE_ID. */
	public static final String ROOT_CHOICE_ID = "rootChoice";

	/** The Constant CHILD_TEXTFIELD_ID. */
	public static final String CHILD_TEXTFIELD_ID = "childTextField";

	/** The root choice. */
	@Getter
	private final DropDownChoice<T> rootChoice;

	/** The root renderer. */
	@Getter
	private final IChoiceRenderer<T> rootRenderer;

	/** The wmc root choice. */
	@Getter
	private WebMarkupContainer wmcRootChoice;

	/** The wmc child choice. */
	@Getter
	private WebMarkupContainer wmcChildChoice;

	/** The Label for root component. */
	@Getter
	protected Label rootLabel;

	/** The Label for child component. */
	@Getter
	protected Label childLabel;

	/** The child {@link AutoCompleteTextField}. */
	@Getter
	private final AutoCompleteTextField<T> childTextField;

	/**
	 * Instantiates a new dropdown autocomplete text field panel.
	 *
	 * @param id
	 *            the id
	 * @param model
	 *            the model
	 * @param rootRenderer
	 *            the root renderer
	 * @param rootLabelModel
	 *            the root label model
	 * @param childLabelModel
	 *            the child label model
	 * @param locationModel
	 *            the location model
	 */
	public DropdownAutocompleteTextFieldPanel(final String id, final IModel<TwoDropDownChoicesBean<T>> model,
			final IChoiceRenderer<T> rootRenderer, final IModel<String> rootLabelModel,
			final IModel<String> childLabelModel) {
		super(id, Args.notNull(model, "model"));
		this.rootRenderer = rootRenderer;

		rootChoice = newRootChoice(ROOT_CHOICE_ID, getModel());

		add(wmcRootChoice = ComponentFactory.newWebMarkupContainer("wmcRootChoice"));
		wmcRootChoice.add(rootLabel = newRootLabel(rootChoice.getMarkupId(), rootLabelModel));
		wmcRootChoice.add(rootChoice);

		childTextField = newAutoCompleteTextField(CHILD_TEXTFIELD_ID, getModel());

		add(wmcChildChoice = ComponentFactory.newWebMarkupContainer("wmcChildChoice", getModel()));
		wmcChildChoice.add(childLabel = newChildLabel(childTextField.getMarkupId(), childLabelModel));
		wmcChildChoice.add(childTextField);

	}

	/**
	 * Factory method for creating the new child {@link AutoCompleteTextField}.
	 * This method is invoked in the constructor from the derived classes and
	 * can be overridden so users can provide their own version of a new child
	 * {@link AutoCompleteTextField}.
	 *
	 * @param id
	 *            the id
	 * @param model
	 *            the model
	 * @return the new child {@link AutoCompleteTextField}.
	 */
	protected AutoCompleteTextField<T> newAutoCompleteTextField(final String id, final IModel<TwoDropDownChoicesBean<T>> model) {

		final IModel<T> selectedChildOptionModel = new PropertyModel<>(model,
			"selectedChildOption");

		final DefaultCssAutoCompleteTextField<T> autoCompleteTextField = new DefaultCssAutoCompleteTextField<T>(
				id, selectedChildOptionModel) {

			private static final long serialVersionUID = 1L;

			@Override
			protected Iterator<T> getChoices(final String input) {
				if (Strings.isEmpty(input)) {
					final List<T> emptyList = Collections.emptyList();
					return emptyList.iterator();
				}

				final List<T> choices = new ArrayList<>(20);

				final List<T> childChoices = DropdownAutocompleteTextFieldPanel.this.getModelObject()
						.getChildChoices();
				for (final T choice : childChoices) {
					onProcessChildChoice(input, childChoices, choice);
				}
				return choices.iterator();
			}

			/**
			 * {@inheritDoc}
			 */
			@Override
			public void convertInput() {
				T convertedInput = getConvertedInput();
				if (convertedInput == null) {
					final String[] inputArray = getInputAsArray();
					convertedInput = convertChoiceValue(inputArray);
					DropdownAutocompleteTextFieldPanel.this.getModelObject().setSelectedChildOption(convertedInput);
					setConvertedInput(
							DropdownAutocompleteTextFieldPanel.this.getModelObject().getSelectedChildOption());
				} else {
					setConvertedInput(convertedInput);
				}
			}

			/**
			 * Converts the given choice value array to the specific type.
			 *
			 * @param value
			 *            the value
			 * @return the converted value to the specific type
			 */
			@SuppressWarnings("unchecked")
			protected T convertChoiceValue(final String[] value) {
				return value != null && value.length > 0 && value[0] != null ? (T)value[0] : null;
			}
		};
		autoCompleteTextField.setOutputMarkupId(true);
		autoCompleteTextField.add(new AjaxFormComponentUpdatingBehavior("change") {
			/** The Constant serialVersionUID. */
			private static final long serialVersionUID = 1L;

			/**
			 * {@inheritDoc}
			 */
			@Override
			protected void onError(final AjaxRequestTarget target, final RuntimeException e) {
				DropdownAutocompleteTextFieldPanel.this.onChildChoiceError(target, e);
			}

			/**
			 * {@inheritDoc}
			 */
			@Override
			protected void onUpdate(final AjaxRequestTarget target) {
				DropdownAutocompleteTextFieldPanel.this.onChildChoiceUpdate(target);
			}
		});
		return autoCompleteTextField;
	}


	/**
	 * Abstracr callback method that must be overwritten to provide an additional action
	 * when child choice has updated.
	 *
	 * @param choice
	 *            the current choice to process
	 */
	protected abstract boolean onProcessChildChoice(final String input, final List<T> choices, final T choice);

	/**
	 * Factory method for creating the root Label. This method is invoked in the
	 * constructor from the derived classes and can be overridden so users can
	 * provide their own version of a Label.
	 *
	 * @param forId
	 *            the for id
	 * @param model
	 *            the model
	 * @return the label
	 */
	protected Label newRootLabel(final String forId, final IModel<String> model) {
		return ComponentFactory.newLabel("rootLabel", forId, model);
	}

	/**
	 * Factory method for creating the root Label. This method is invoked in the
	 * constructor from the derived classes and can be overridden so users can
	 * provide their own version of a Label.
	 *
	 * @param forId
	 *            the for id
	 * @param model
	 *            the model
	 * @return the label
	 */
	protected Label newChildLabel(final String forId, final IModel<String> model) {
		return ComponentFactory.newLabel("childLabel", forId, model);
	}

	/**
	 * Factory method for creating the new root {@link DropDownChoice}. This
	 * method is invoked in the constructor from the derived classes and can be
	 * overridden so users can provide their own version of a new root
	 * {@link DropDownChoice}.
	 *
	 * @param id
	 *            the id
	 * @param model
	 *            the model
	 * @return the new root {@link DropDownChoice}.
	 */
	protected DropDownChoice<T> newRootChoice(final String id,
		final IModel<TwoDropDownChoicesBean<T>> model)
	{
		final IModel<T> selectedRootOptionModel = PropertyModel.of(model, "selectedRootOption");
		final IModel<List<T>> rootChoicesModel = PropertyModel.of(model, "rootChoices");

		final DropDownChoice<T> rc = new LocalisedDropDownChoice<T>(id, selectedRootOptionModel,
			rootChoicesModel, this.rootRenderer)
		{

			/** The Constant serialVersionUID. */
			private static final long serialVersionUID = 1L;

			/**
			 * {@inheritDoc}
			 */
			@Override
			public void convertInput()
			{
				T convertedInput = getConvertedInput();
				if (convertedInput == null)
				{
					final String[] inputArray = getInputAsArray();
					convertedInput = convertChoiceValue(inputArray);
					DropdownAutocompleteTextFieldPanel.this.getModelObject().setSelectedRootOption(convertedInput);
					setConvertedInput(
						DropdownAutocompleteTextFieldPanel.this.getModelObject().getSelectedRootOption());
				}
				else
				{
					setConvertedInput(convertedInput);
				}
			}

			/**
			 * Converts the given choice value array to the specific type.
			 *
			 * @param value
			 *            the value
			 * @return the converted value to the specific type
			 */
			@SuppressWarnings("unchecked")
			protected T convertChoiceValue(final String[] value)
			{
				return (T)(value != null && value.length > 0 && value[0] != null
					? trim(value[0])
					: null);
			}
		};
		rc.add(new AjaxFormComponentUpdatingBehavior("change")
		{
			/** The Constant serialVersionUID. */
			private static final long serialVersionUID = 1L;

			/**
			 * {@inheritDoc}
			 */
			@Override
			protected void onError(final AjaxRequestTarget target, final RuntimeException e)
			{
				DropdownAutocompleteTextFieldPanel.this.onRootChoiceError(target, e);
			}

			/**
			 * {@inheritDoc}
			 */
			@Override
			protected void onUpdate(final AjaxRequestTarget target)
			{
				DropdownAutocompleteTextFieldPanel.this.onRootChoiceUpdate(target);
			}
		});
		return rc;
	}

	/**
	 * Callback method that can be overwritten to handle any error resulting
	 * from updating the root choice.
	 *
	 * @param target
	 *            the current request handler
	 * @param e
	 *            the {@link RuntimeException} error that occurred during the
	 *            update of the component.
	 */
	protected void onRootChoiceError(final AjaxRequestTarget target, final RuntimeException e) {
	}

	/**
	 * Callback method that can be overwritten to provide an additional action
	 * when root choice has updated.
	 *
	 * @param target
	 *            the current request handler
	 */
	protected void onRootChoiceUpdate(final AjaxRequestTarget target) {
		childTextField.setModelObject(getModelObject().getSelectedChildOption());
		target.add(DropdownAutocompleteTextFieldPanel.this.childTextField);
	}

	/**
	 * Callback method that can be overwritten to handle any error resulting
	 * from updating the child choice.
	 *
	 * @param target
	 *            the current request handler
	 * @param e
	 *            the {@link RuntimeException} error that occurred during the
	 *            update of the component.
	 */
	protected void onChildChoiceError(final AjaxRequestTarget target, final RuntimeException e) {
	}

	/**
	 * Callback method that can be overwritten to provide an additional action
	 * when child choice has updated.
	 *
	 * @param target
	 *            the current request handler
	 */
	protected void onChildChoiceUpdate(final AjaxRequestTarget target) {
	}

}
