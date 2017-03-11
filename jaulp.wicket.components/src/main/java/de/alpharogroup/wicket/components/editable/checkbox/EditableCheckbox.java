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
package de.alpharogroup.wicket.components.editable.checkbox;

import org.apache.wicket.Component;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.CheckBox;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;

import de.alpharogroup.wicket.base.BasePanel;
import de.alpharogroup.wicket.components.factory.ComponentFactory;
import de.alpharogroup.wicket.components.labeled.checkbox.LabeledCheckboxPanel;
import de.alpharogroup.wicket.components.labeled.label.LabeledLabelPanel;
import de.alpharogroup.wicket.components.swap.ModeContext;
import de.alpharogroup.wicket.components.swap.SwapComponentsFragmentPanel;
import lombok.Getter;
import lombok.Setter;

/**
 * An editable Checkbox that can be switched to a Label.
 *
 * @author Asterios Raptis
 */
public class EditableCheckbox<T> extends BasePanel<T>
{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The ModeContext shows if the view mode or edit mode is visible. */
	@Getter
	@Setter
	private ModeContext modeContext = ModeContext.EDIT_MODE;
	/** The swap panel. */
	@Getter
	private SwapComponentsFragmentPanel<T> swapPanel;
	/** The model of the label. */
	@Getter
	private final IModel<String> labelModel;

	/**
	 * Instantiates a new {@link EditableCheckbox}.
	 *
	 * @param id
	 *            the id
	 * @param model
	 *            the model
	 * @param labelModel
	 *            the label model
	 */
	public EditableCheckbox(final String id, final IModel<T> model,
		final IModel<String> labelModel)
	{
		this(id, model, labelModel, ModeContext.EDIT_MODE);
	}

	/**
	 * Instantiates a new {@link EditableCheckbox}.
	 *
	 * @param id
	 *            the id
	 * @param model
	 *            the model
	 * @param labelModel
	 *            the label model
	 * @param modeContext
	 *            the editable flag
	 */
	public EditableCheckbox(final String id, final IModel<T> model,
		final IModel<String> labelModel, final ModeContext modeContext)
	{
		super(id, model);
		this.setOutputMarkupId(true).setOutputMarkupPlaceholderTag(true);
		this.labelModel = labelModel;
		this.modeContext = modeContext;
	}

	/**
	 * Checks if is editable.
	 *
	 * @return true, if it is editable
	 */
	public boolean isEditable()
	{
		return modeContext.equals(ModeContext.EDIT_MODE);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void onInitialize()
	{
		super.onInitialize();
		add(this.swapPanel = new SwapComponentsFragmentPanel<T>("swapPanel", getModel())
		{
			/**
			 * The serialVersionUID
			 */
			private static final long serialVersionUID = 1L;

			/**
			 * {@inheritDoc}
			 */
			@Override
			protected Component newEditComponent(final String id, final IModel<T> model)
			{
				return new LabeledCheckboxPanel<String, T>(id, model, getLabelModel()){
					/**
					 *
					 */
					private static final long serialVersionUID = 1L;

					/**
					 * {@inheritDoc}
					 */
					@Override
					protected CheckBox newCheckBox(final String id, final IModel<T> model)
					{
						final IModel<Boolean> propertyModel = new PropertyModel<>(model.getObject(), EditableCheckbox.this.getId());
						return ComponentFactory.newCheckBox(id, propertyModel);
					}
				};
			}

			/**
			 * {@inheritDoc}
			 */
			@Override
			protected Component newViewComponent(final String id, final IModel<T> model)
			{
				return new LabeledLabelPanel<T>(id, model, getLabelModel()){

					private static final long serialVersionUID = 1L;
					@Override
					protected Label newViewableLabel(final String id, final IModel<T> model)
					{
						final PropertyModel<T> viewableLabelModel = new PropertyModel<>(model.getObject(),
							EditableCheckbox.this.getId());
						return ComponentFactory.newLabel(id, viewableLabelModel);
					}

				};
			}
		});
	}

	/**
	 * Swap the ModeContext.
	 */
	public void swap()
	{
		if (modeContext.equals(ModeContext.VIEW_MODE))
		{
			modeContext = ModeContext.EDIT_MODE;
		}
		else
		{
			modeContext = ModeContext.VIEW_MODE;
		}
	}

	/**
	 * Factory method for create a new {@link EditableCheckbox} object.
	 *
	 * @param id
	 *            the id
	 * @param model
	 *            the model
	 * @param labelModel
	 *            the label model
	 * @return the new created {@link EditableCheckbox} object.
	 */
	public static<T> EditableCheckbox<T> of(final String id, final IModel<T> model,
		final IModel<String> labelModel)
	{
		return EditableCheckbox.of(id, model, labelModel, ModeContext.EDIT_MODE);
	}

	/**
	 * Factory method for create a new {@link EditableCheckbox} object.
	 *
	 * @param id
	 *            the id
	 * @param model
	 *            the model
	 * @param labelModel
	 *            the label model
	 * @param modeContext
	 *            the editable flag
	 * @return the new created {@link EditableCheckbox} object.
	 */
	public static<T> EditableCheckbox<T> of(final String id, final IModel<T> model,
		final IModel<String> labelModel, final ModeContext modeContext)
	{
		final EditableCheckbox<T> editableCheckbox = new EditableCheckbox<>(id, model, labelModel, modeContext);
		return editableCheckbox;
	}

}