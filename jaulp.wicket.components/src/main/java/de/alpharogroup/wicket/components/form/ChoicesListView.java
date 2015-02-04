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
	private IChoiceRenderer<T> choiceRenderer;

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
	public ChoicesListView(String id, IModel<List<T>> choices, IChoiceRenderer<T> renderer)
	{
		super(id, choices);
		this.choiceRenderer = renderer;
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
	 * Convenience method exposed to subclasses for determining the display value of a given list
	 * item. This delegates to {@link IChoiceRenderer#getDisplayValue getDisplayValue()} on the
	 * IChoiceRenderer. The display value will be coverted to a String if necessary using Wicket's
	 * {@link IConverter} system.
	 * 
	 * @param choice
	 *            The current value of the list that is being rendered.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	protected String getChoiceLabel(T choice)
	{
		// This code was copied from Wicket's CheckBoxMultipleChoice.java
		Object displayValue = getChoiceRenderer().getDisplayValue(choice);
		Class<?> objectClass = displayValue == null ? null : displayValue.getClass();
		// Get label for choice
		String label = "";
		if (objectClass != null && objectClass != String.class)
		{
			IConverter converter = getConverter(objectClass);
			label = converter.convertToString(displayValue, getLocale());
		}
		else if (displayValue != null)
		{
			label = displayValue.toString();
		}
		return label;
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
	 */
	protected String getChoiceValue(T choice, int index)
	{
		return getChoiceRenderer().getIdValue(choice, index);
	}
}
