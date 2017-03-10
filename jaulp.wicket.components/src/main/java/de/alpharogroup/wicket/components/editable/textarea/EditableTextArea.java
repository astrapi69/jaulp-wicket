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
package de.alpharogroup.wicket.components.editable.textarea;

import org.apache.wicket.Component;
import org.apache.wicket.markup.html.basic.MultiLineLabel;
import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;

import de.alpharogroup.wicket.base.BasePanel;
import de.alpharogroup.wicket.base.util.ComponentFinder;
import de.alpharogroup.wicket.components.factory.ComponentFactory;
import de.alpharogroup.wicket.components.labeled.label.LabeledMultiLineLabelPanel;
import de.alpharogroup.wicket.components.labeled.textarea.LabeledTextAreaPanel;
import de.alpharogroup.wicket.components.swap.ModeContext;
import de.alpharogroup.wicket.components.swap.SwapComponentsFragmentPanel;
import lombok.Getter;
import lombok.Setter;

/**
 * An editable TextArea that can be switched to a MultilineLabel.
 *
 * @author Asterios Raptis
 */
public class EditableTextArea<T> extends BasePanel<T>
{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The ModeContext shows if the view mode or edit mode is visible. */
	@Getter
	@Setter
	private ModeContext modeContext = ModeContext.VIEW_MODE;
	/** The swap panel. */
	@Getter
	private SwapComponentsFragmentPanel<T> swapPanel;
	/** The model of the label. */
	@Getter
	private final IModel<String> labelModel;

	/** The MultiLineLabel. */
	@Getter
	private MultiLineLabel label;

	/** The text area. */
	@Getter
	private TextArea<String> textArea;

	/**
	 * Instantiates a new {@link EditableTextArea}.
	 *
	 * @param id
	 *            the id
	 * @param model
	 *            the model
	 * @param labelModel
	 *            the label model
	 */
	public EditableTextArea(final String id, final IModel<T> model,
		final IModel<String> labelModel)
	{
		this(id, model, labelModel, ModeContext.EDIT_MODE);
	}

	/**
	 * Instantiates a new {@link EditableTextArea}.
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
	public EditableTextArea(final String id, final IModel<T> model,
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
				return new LabeledTextAreaPanel<String, T>(id, model, getLabelModel())
				{
					/** The serialVersionUID. */
					private static final long serialVersionUID = 1L;

					/**
					 * Factory method for creating the TextArea. This method is invoked in the
					 * constructor from this class and can be overridden so users can provide their
					 * own version of a TextArea.
					 *
					 * @param id
					 *            the id
					 * @param model
					 *            the model
					 * @return the text area
					 */
					@Override
					protected TextArea<String> newTextArea(final String id,
						final IModel<T> model)
					{
						final IModel<String> textAreaModel = new PropertyModel<>(model.getObject(), EditableTextArea.this.getId());
						return ComponentFactory.newTextArea(id, textAreaModel);
					}
				};
			}

			/**
			 * {@inheritDoc}
			 */
			@Override
			protected Component newViewComponent(final String id, final IModel<T> model)
			{
				return new LabeledMultiLineLabelPanel<T>(id, model, getLabelModel())
				{
					/** The serialVersionUID. */
					private static final long serialVersionUID = 1L;

					/**
					 * Factory method for creating the MultiLineLabel. This method is invoked in the
					 * constructor from the derived classes and can be overridden so users can
					 * provide their own version of a MultiLineLabel.
					 *
					 * @param id
					 *            the id
					 * @param model
					 *            the model
					 * @return the label
					 */
					@Override
					protected MultiLineLabel newMultiLineLabelLabel(final String id,
						final IModel<T> model)
					{
						final IModel<T> viewableLabelModel = new PropertyModel<>(model.getObject(), EditableTextArea.this.getId());
						return ComponentFactory.newMultiLineLabel(id, viewableLabelModel);
					}
				};
			}
		});
		if (modeContext.equals(ModeContext.EDIT_MODE))
		{
			this.swapPanel.onSwapToEdit(ComponentFinder.findOrCreateNewAjaxRequestTarget(), null);
		}
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

}
