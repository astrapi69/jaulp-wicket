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
package de.alpharogroup.wicket.components.editable.textfield;

import lombok.Getter;
import lombok.Setter;

import org.apache.wicket.Component;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.GenericPanel;
import org.apache.wicket.model.IModel;
import org.jaulp.wicket.base.util.ComponentFinder;

import de.alpharogroup.wicket.components.factory.ComponentFactory;
import de.alpharogroup.wicket.components.labeled.label.LabeledLabelPanel;
import de.alpharogroup.wicket.components.labeled.textfield.LabeledTextFieldPanel;
import de.alpharogroup.wicket.components.swap.ModeContext;
import de.alpharogroup.wicket.components.swap.SwapComponentsFragmentPanel;

/**
 * An editable TextField that can be switched to a Label.
 */
public class EditableTextField extends GenericPanel<String>
{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The ModeContext shows if the view mode or edit mode is visible. */
	@Getter
	@Setter
	private ModeContext modeContext = ModeContext.VIEW_MODE;
	@Getter
	private SwapComponentsFragmentPanel<String> swapPanel;
	@Getter
	private final IModel<String> labelModel;

	/**
	 * Instantiates a new editable text field.
	 *
	 * @param id
	 *            the id
	 * @param model
	 *            the model
	 */
	public EditableTextField(String id, IModel<String> model, IModel<String> labelModel)
	{
		this(id, model, labelModel, ModeContext.EDIT_MODE);
	}

	/**
	 * Instantiates a new editable text field.
	 *
	 * @param id
	 *            the id
	 * @param model
	 *            the model
	 * @param modeContext
	 *            the editable flag
	 */
	public EditableTextField(String id, IModel<String> model, IModel<String> labelModel,
		ModeContext modeContext)
	{
		super(id, model);
		this.setOutputMarkupId(true).setOutputMarkupPlaceholderTag(true);
		this.labelModel = labelModel;
		this.modeContext = modeContext;
	}

	@Override
	protected void onInitialize()
	{
		super.onInitialize();
		add(this.swapPanel = new SwapComponentsFragmentPanel<String>("swapPanel", getModel())
		{
			/**
			 * The serialVersionUID
			 */
			private static final long serialVersionUID = 1L;

			@Override
			protected Component newViewComponent(String id, IModel<String> model)
			{
				return new LabeledLabelPanel<String>(id, model, getLabelModel())
				{

					/**
					 * The serialVersionUID
					 */
					private static final long serialVersionUID = 1L;

					protected Label newLabel(String id, IModel<String> model)
					{
						return ComponentFactory.newLabel(id, model);
					}
				};
			}

			@Override
			protected Component newEditComponent(String id, IModel<String> model)
			{
				return new LabeledTextFieldPanel<String>(id, model, getLabelModel())
				{

					/**
					 * The serialVersionUID
					 */
					private static final long serialVersionUID = 1L;

					/**
					 * Factory method for creating the TextField. This method is invoked in the
					 * constructor from the derived classes and can be overridden so users can
					 * provide their own version of a TextField.
					 *
					 * @param id
					 *            the id
					 * @param model
					 *            the model
					 * @return the text field
					 */
					protected TextField<String> newTextField(String id, IModel<String> model)
					{
						return ComponentFactory.newTextField(id, model);
					}
				};
			}
		});
		if (modeContext.equals(ModeContext.EDIT_MODE))
		{
			this.swapPanel.onSwapToEdit(ComponentFinder.findOrNewAjaxRequestTarget(), null);
		}
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
