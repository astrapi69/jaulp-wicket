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
package de.alpharogroup.wicket.components.form;

import java.util.List;

import org.apache.wicket.markup.html.form.IChoiceRenderer;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.IModel;
import org.apache.wicket.util.convert.IConverter;

/**
 * A base ListView that provides conveniences for rendering its items using an IChoiceRenderer.
 * 
 * This class is inspired from fiftyfive.wicket.core project. Some changes with the generic types
 * was done.
 * 
 * @param <T>
 *            the generic type
 */
public abstract class ChoicesListView<T> extends ListView<T>
{
	/**
	 * The serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;

	/** The choice renderer. */
	private final IChoiceRenderer<T> choiceRenderer;

	/**
	 * Construct a list view that will expose the specified IChoiceRenderer for rendering its list
	 * items.
	 * 
	 * @param id
	 *            the id
	 * @param choices
	 *            the list of choices
	 * @param renderer
	 *            the choice renderer
	 */
	public ChoicesListView(final String id, final IModel<List<T>> choices,
		final IChoiceRenderer<T> renderer)
	{
		super(id, choices);
		this.choiceRenderer = renderer;
	}

	/**
	 * Convenience method exposed to subclasses for determining the display value of a given list
	 * item. This delegates to {@link IChoiceRenderer#getDisplayValue getDisplayValue()} on the
	 * IChoiceRenderer. The display value will be coverted to a String if necessary using Wicket's
	 * {@link IConverter} system.
	 * 
	 * @param choice
	 *            The current value of the list that is being rendered.
	 * @return the choice label
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	protected String getChoiceLabel(final T choice)
	{
		// This code was copied from Wicket's CheckBoxMultipleChoice.java
		final Object displayValue = getChoiceRenderer().getDisplayValue(choice);
		final Class<?> objectClass = displayValue == null ? null : displayValue.getClass();
		// Get label for choice
		String label = "";
		if ((objectClass != null) && (objectClass != String.class))
		{
			final IConverter converter = getConverter(objectClass);
			label = converter.convertToString(displayValue, getLocale());
		}
		else if (displayValue != null)
		{
			label = displayValue.toString();
		}
		return label;
	}

	/**
	 * Returns the IChoiceRenderer that was passed to the constructor.
	 * 
	 * @return the choice renderer
	 */
	public IChoiceRenderer<T> getChoiceRenderer()
	{
		return this.choiceRenderer;
	}

	/**
	 * Convenience method exposed to subclasses for determining the unique ID of the given list
	 * item. This delegates to {@link IChoiceRenderer#getIdValue getIdValue()} on the
	 * IChoiceRenderer.
	 * 
	 * @param choice
	 *            The current value of the list that is being rendered.
	 * @param index
	 *            The zero-indexed position of that value in the list.
	 * @return the choice value
	 */
	protected String getChoiceValue(final T choice, final int index)
	{
		return getChoiceRenderer().getIdValue(choice, index);
	}
}
