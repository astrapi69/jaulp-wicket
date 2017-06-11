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
import org.apache.wicket.markup.html.form.IChoiceRenderer;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;

import de.alpharogroup.wicket.base.BasePanel;
import de.alpharogroup.wicket.components.i18n.dropdownchoice.LocalisedDropDownChoice;
import de.alpharogroup.wicket.model.dropdownchoices.TwoDropDownChoicesModel;
import lombok.Getter;

/**
 * The Class {@link TwoDropDownChoicesPanel} contains two dropdowns with a root and a child
 * dropdown.
 *
 * @author Asterios Raptis
 * @param <T>
 *            the generic type of the model object
 *            @deprecated since wicket version 7. Use instead new class {@link DoubleDropDownPanel}
 */
public abstract class TwoDropDownChoicesPanel<T> extends BasePanel<TwoDropDownChoicesModel<T>>
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

	/**
	 * Instantiates a new {@link TwoDropDownChoicesPanel}.
	 *
	 * @param id
	 *            the id
	 * @param stringTwoDropDownChoicesModel
	 *            the string two drop down choices model
	 * @param rootRenderer
	 *            the root renderer
	 * @param childRenderer
	 *            the child renderer
	 */
	public TwoDropDownChoicesPanel(final String id,
		final IModel<TwoDropDownChoicesModel<T>> stringTwoDropDownChoicesModel,
		final IChoiceRenderer<T> rootRenderer, final IChoiceRenderer<T> childRenderer)
	{
		super(id, stringTwoDropDownChoicesModel);
		getModelObject().getRootChoices();
		final IModel<T> selectedRootOptionModel = new PropertyModel<>(stringTwoDropDownChoicesModel,
			"selectedRootOption");
		rootChoice = newRootChoice(ROOT_CHOICE_ID, selectedRootOptionModel,
			getModelObject().getRootChoices(), rootRenderer);
		final IModel<T> selectedChildOptionModel = new PropertyModel<>(
			stringTwoDropDownChoicesModel, "selectedChildOption");
		childChoice = newChildChoice(CHILD_CHOICE_ID, selectedChildOptionModel,
			getModelObject().getChildChoices(), childRenderer);

		add(rootChoice);
		add(childChoice);
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
	 * @param choices
	 *            the choices
	 * @param renderer
	 *            the renderer
	 * @return the new child {@link DropDownChoice}.
	 */
	protected DropDownChoice<T> newChildChoice(final String id, final IModel<T> model,
		final IModel<? extends List<? extends T>> choices,
		final IChoiceRenderer<? super T> renderer)
	{
		final DropDownChoice<T> cc = new LocalisedDropDownChoice<>(id, model, choices, renderer);
		cc.setOutputMarkupId(true);
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
	 * @param choices
	 *            the choices
	 * @param renderer
	 *            the renderer
	 * @return the new root {@link DropDownChoice}.
	 */
	protected DropDownChoice<T> newRootChoice(final String id, final IModel<T> model,
		final IModel<? extends List<? extends T>> choices,
		final IChoiceRenderer<? super T> renderer)
	{
		final DropDownChoice<T> rc = new LocalisedDropDownChoice<>(id, model, choices, renderer);
		rc.add(new AjaxFormComponentUpdatingBehavior("change")
		{
			/** The Constant serialVersionUID. */
			private static final long serialVersionUID = 1L;

			/**
			 * {@inheritDoc}
			 */
			@Override
			protected void onUpdate(final AjaxRequestTarget target)
			{
				target.add(childChoice);
			}
		});
		return rc;
	}

}
