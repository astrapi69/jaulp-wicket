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
package de.alpharogroup.wicket.components.form.dropdown;

import java.util.List;

import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.IChoiceRenderer;
import org.apache.wicket.model.IModel;
import org.apache.wicket.util.string.AppendingStringBuffer;
import org.apache.wicket.util.string.Strings;

/**
 * The Class {@link OptGroupDropDownChoice} is for use with select and optgroup.
 *
 * @param <T>
 *            the generic type
 */
public abstract class OptGroupDropDownChoice<T extends OptGroup> extends DropDownChoice<T>
{

	/** The Constant CLOSE_OPTGROUP_TAG. */
	private static final String CLOSE_OPTGROUP_TAG = "\n</optgroup>";

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The last. */
	private T last;

	/**
	 * Instantiates a new {@link OptGroupDropDownChoice}.
	 *
	 * @param id
	 *            the id
	 */
	public OptGroupDropDownChoice(final String id)
	{
		super(id);
	}

	/**
	 * Instantiates a new {@link OptGroupDropDownChoice}.
	 *
	 * @param id
	 *            the id
	 * @param choices
	 *            the choices
	 */
	public OptGroupDropDownChoice(final String id, final IModel<? extends List<? extends T>> choices)
	{
		super(id, choices);
	}

	/**
	 * Instantiates a new {@link OptGroupDropDownChoice}.
	 *
	 * @param id
	 *            the id
	 * @param choices
	 *            the choices
	 * @param renderer
	 *            the renderer
	 */
	public OptGroupDropDownChoice(final String id,
		final IModel<? extends List<? extends T>> choices, final IChoiceRenderer<? super T> renderer)
	{
		super(id, choices, renderer);
	}

	/**
	 * Instantiates a new {@link OptGroupDropDownChoice}.
	 *
	 * @param id
	 *            the id
	 * @param model
	 *            the model
	 * @param choices
	 *            the choices
	 */
	public OptGroupDropDownChoice(final String id, final IModel<T> model,
		final IModel<? extends List<? extends T>> choices)
	{
		super(id, model, choices);
	}

	/**
	 * Instantiates a new {@link OptGroupDropDownChoice}.
	 *
	 * @param id
	 *            the id
	 * @param model
	 *            the model
	 * @param choices
	 *            the choices
	 * @param renderer
	 *            the renderer
	 */
	public OptGroupDropDownChoice(final String id, final IModel<T> model,
		final IModel<? extends List<? extends T>> choices, final IChoiceRenderer<? super T> renderer)
	{
		super(id, model, choices, renderer);
	}

	/**
	 * Instantiates a new {@link OptGroupDropDownChoice}.
	 *
	 * @param id
	 *            the id
	 * @param model
	 *            the model
	 * @param choices
	 *            the choices
	 */
	public OptGroupDropDownChoice(final String id, final IModel<T> model,
		final List<? extends T> choices)
	{
		super(id, model, choices);
	}

	/**
	 * Instantiates a new {@link OptGroupDropDownChoice}.
	 *
	 * @param id
	 *            the id
	 * @param model
	 *            the model
	 * @param choices
	 *            the choices
	 * @param renderer
	 *            the renderer
	 */
	public OptGroupDropDownChoice(final String id, final IModel<T> model,
		final List<? extends T> choices, final IChoiceRenderer<? super T> renderer)
	{
		super(id, model, choices, renderer);
	}

	/**
	 * Instantiates a new {@link OptGroupDropDownChoice}.
	 *
	 * @param id
	 *            the id
	 * @param choices
	 *            the choices
	 */
	public OptGroupDropDownChoice(final String id, final List<? extends T> choices)
	{
		super(id, choices);
	}

	/**
	 * Instantiates a new {@link OptGroupDropDownChoice}.
	 *
	 * @param id
	 *            the id
	 * @param choices
	 *            the choices
	 * @param renderer
	 *            the renderer
	 */
	public OptGroupDropDownChoice(final String id, final List<? extends T> choices,
		final IChoiceRenderer<? super T> renderer)
	{
		super(id, choices, renderer);
	}

	/**
	 * Appends the label of the given opt group object to the given {@link AppendingStringBuffer}
	 * object.
	 *
	 * @param buffer
	 *            the buffer
	 * @param optGroup
	 *            the opt group to append.
	 */
	private void appendOptGroupLabel(final AppendingStringBuffer buffer, final T optGroup)
	{
		buffer.append("\n<optgroup label='")
			.append(Strings.escapeMarkup(getOptGroupLabel(optGroup))).append("'>");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void appendOptionHtml(final AppendingStringBuffer buffer, final T choice,
		final int index, final String selected)
	{

		final T currentOptGroup = choice;
		if (isNewGroup(currentOptGroup))
		{
			if (!isFirst(index))
			{
				buffer.append(CLOSE_OPTGROUP_TAG);
			}
			appendOptGroupLabel(buffer, currentOptGroup);
		}

		super.appendOptionHtml(buffer, choice, index, selected);

		if (isLast(index))
		{
			buffer.append(CLOSE_OPTGROUP_TAG);
		}
		last = currentOptGroup;

	}

	/**
	 * Abstract method that have to be overwritten to get the label of the given opt group object.
	 *
	 * @param optGroup
	 *            the opt group
	 * @return the label of the given opt group object.
	 */
	protected abstract String getOptGroupLabel(final T optGroup);

	/**
	 * Checks if it is first.
	 *
	 * @param index
	 *            the index
	 * @return true, if is first
	 */
	private boolean isFirst(final int index)
	{
		return index == 0;
	}

	/**
	 * Checks if it is last.
	 *
	 * @param index
	 *            the index
	 * @return true, if is last
	 */
	private boolean isLast(final int index)
	{
		return (index - 1) == getChoices().size();
	}

	/**
	 * Checks if it is new group.
	 *
	 * @param currentOptGroup
	 *            the current opt group
	 * @return true, if is new group
	 */
	private boolean isNewGroup(final OptGroup currentOptGroup)
	{
		return (last == null) || !currentOptGroup.getLabel().equals(last.getLabel());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void onDetach()
	{
		super.onDetach();
		last = null;
	}
}