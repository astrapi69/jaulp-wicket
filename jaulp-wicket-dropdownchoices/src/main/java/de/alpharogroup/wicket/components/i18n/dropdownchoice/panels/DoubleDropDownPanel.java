/**
 * Copyright (C) 2010 Asterios Raptis
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package de.alpharogroup.wicket.components.i18n.dropdownchoice.panels;

import java.util.List;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormComponentUpdatingBehavior;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.FormComponentPanel;
import org.apache.wicket.markup.html.form.IChoiceRenderer;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.util.lang.Args;

import de.alpharogroup.wicket.components.i18n.dropdownchoice.LocalisedDropDownChoice;
import de.alpharogroup.wicket.model.dropdownchoices.TwoDropDownChoicesBean;
import lombok.Getter;

/**
 * The class {@link DoubleDropDownPanel} is a {@link FormComponentPanel} that encapsulates two
 * dropdowns with a root and a child dropdown.
 *
 * @author Asterios Raptis
 * @param <T>
 *            the generic type of the model object
 */
public class DoubleDropDownPanel<T> extends FormComponentPanel<TwoDropDownChoicesBean<T>>
{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The Constant ROOT_CHOICE_ID. */
	public static final String ROOT_CHOICE_ID = "rootChoice";

	/** The Constant CHILD_CHOICE_ID. */
	public static final String CHILD_CHOICE_ID = "childChoice";

	/** The root choice. */
	@Getter
	private DropDownChoice<T> rootChoice;

	/** The child choice. */
	@Getter
	private DropDownChoice<T> childChoice;

	/** The root renderer. */
	@Getter
	private final IChoiceRenderer<T> rootRenderer;

	/** The child renderer. */
	@Getter
	private final IChoiceRenderer<T> childRenderer;

	/**
	 * Instantiates a new {@link DoubleDropDownPanel} component.
	 *
	 * @param id
	 *            the id
	 * @param model
	 *            the model
	 * @param rootRenderer
	 *            the root renderer
	 * @param childRenderer
	 *            the child renderer
	 */
	public DoubleDropDownPanel(final String id, final IModel<TwoDropDownChoicesBean<T>> model,
		final IChoiceRenderer<T> rootRenderer, final IChoiceRenderer<T> childRenderer)
	{
		super(id, Args.notNull(model, "model"));
		this.rootRenderer = Args.notNull(rootRenderer, "rootRenderer");
		this.childRenderer = Args.notNull(childRenderer, "childRenderer");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void onInitialize()
	{
		super.onInitialize();
		add(rootChoice = newRootChoice(ROOT_CHOICE_ID, getModel()));
		add(childChoice = newChildChoice(CHILD_CHOICE_ID, getModel()));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void convertInput()
	{
		final TwoDropDownChoicesBean<T> modelObject = getModelObject();
		setConvertedInput(modelObject);
	}

	/**
	 * Factory method for creating the new child {@link DropDownChoice}. This method is invoked in
	 * the constructor from the derived classes and can be overridden so users can provide their own
	 * version of a new child {@link DropDownChoice}.
	 *
	 * @param id
	 *            the id
	 * @param model
	 *            the model
	 * @return the new child {@link DropDownChoice}.
	 */
	protected DropDownChoice<T> newChildChoice(final String id,
		final IModel<TwoDropDownChoicesBean<T>> model)
	{
		final IModel<T> selectedChildOptionModel = new PropertyModel<>(model,
			"selectedChildOption");
		final IModel<List<T>> childChoicesModel = PropertyModel.of(model, "childChoices");
		final DropDownChoice<T> cc = new LocalisedDropDownChoice<T>(id, selectedChildOptionModel,
			childChoicesModel, this.childRenderer)
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
					DoubleDropDownPanel.this.getModelObject()
						.setSelectedChildOption(convertedInput);
					setConvertedInput(
						DoubleDropDownPanel.this.getModelObject().getSelectedChildOption());
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
		cc.setOutputMarkupId(true);
		cc.add(new AjaxFormComponentUpdatingBehavior("change")
		{
			/** The Constant serialVersionUID. */
			private static final long serialVersionUID = 1L;

			/**
			 * {@inheritDoc}
			 */
			@Override
			protected void onError(final AjaxRequestTarget target, final RuntimeException e)
			{
				DoubleDropDownPanel.this.onChildChoiceError(target, e);
			}

			/**
			 * {@inheritDoc}
			 */
			@Override
			protected void onUpdate(final AjaxRequestTarget target)
			{
				DoubleDropDownPanel.this.onChildChoiceUpdate(target);
			}
		});
		return cc;
	}

	/**
	 * Factory method for creating the new root {@link DropDownChoice}. This method is invoked in
	 * the constructor from the derived classes and can be overridden so users can provide their own
	 * version of a new root {@link DropDownChoice}.
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
					DoubleDropDownPanel.this.getModelObject().setSelectedRootOption(convertedInput);
					setConvertedInput(
						DoubleDropDownPanel.this.getModelObject().getSelectedRootOption());
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
				DoubleDropDownPanel.this.onRootChoiceError(target, e);
			}

			/**
			 * {@inheritDoc}
			 */
			@Override
			protected void onUpdate(final AjaxRequestTarget target)
			{
				DoubleDropDownPanel.this.onRootChoiceUpdate(target);
			}
		});
		return rc;
	}

	/**
	 * Callback method that can be overwritten to handle any error resulting from updating the child
	 * choice.
	 *
	 * @param target
	 *            the current request handler
	 * @param e
	 *            the {@link RuntimeException} error that occurred during the update of the
	 *            component.
	 */
	protected void onChildChoiceError(final AjaxRequestTarget target, final RuntimeException e)
	{
	}

	/**
	 * Callback method that can be overwritten to provide an additional action when child choice has
	 * updated.
	 *
	 * @param target
	 *            the current request handler
	 */
	protected void onChildChoiceUpdate(final AjaxRequestTarget target)
	{
	}

	/**
	 * Callback method that can be overwritten to handle any error resulting from updating the root
	 * choice.
	 *
	 * @param target
	 *            the current request handler
	 * @param e
	 *            the {@link RuntimeException} error that occurred during the update of the
	 *            component.
	 */
	protected void onRootChoiceError(final AjaxRequestTarget target, final RuntimeException e)
	{
	}

	/**
	 * Callback method that can be overwritten to provide an additional action when root choice has
	 * updated.
	 *
	 * @param target
	 *            the current request handler
	 */
	protected void onRootChoiceUpdate(final AjaxRequestTarget target)
	{
		target.add(DoubleDropDownPanel.this.childChoice);
	}

}
