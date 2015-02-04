package de.alpharogroup.wicket.components.form;

import java.util.List;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Check;
import org.apache.wicket.markup.html.form.IChoiceRenderer;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import de.alpharogroup.wicket.components.factory.ComponentFactory;

/**
 * A list view that renders a list of checkboxes with appropriate labels based on an
 * {@link IChoiceRenderer}. When used in combination with a
 * {@link org.apache.wicket.markup.html.form.CheckGroup}, this a good alternative to Wicket's
 * built-in {@link org.apache.wicket.markup.html.form.CheckBoxMultipleChoice}, because it gives you
 * full control over the markup and is extensible.
 * <p>
 * Your markup must contain the following:
 * <ul>
 * <li>{@code <input type="checkbox" wicket:id="check" />} where you want the checkbox to appear.</li>
 * <li>A component with {@code wicket:id="label"} where you want the display value to appear.</li>
 * </ul>
 * <p>
 * For example:
 * 
 * <pre class="example">
 * &lt;wicket:container wicket:id="group"&gt;
 *   &lt;label wicket:id="choices"&gt;
 *     &lt;input type="checkbox" wicket:id="check" /&gt;
 *     &lt;wicket:container wicket:id="label"&gt;Label&lt;/wicket:container&gt;
 *   &lt;/label&gt;
 * &lt;/wicket:container&gt;
 * 
 * add(new CheckGroup("group", selectedItemsModel)
 *     .add(new CheckChoicesListView("choices", choicesModel, renderer)));
 * </pre>
 * <p>
 * You can also override {@link #populateItem(ListItem) populateItem()} if you want to display
 * additional data per checkbox choice, like a description paragraph.
 * 
 * This class is inspired from fiftyfive.wicket.core project. Some changes with the generic types
 * was done.
 * 
 * @param <T>
 *            the generic type
 */
public class CheckChoicesListView<T> extends ChoicesListView<T>
{
	/**
	 * The serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;

	public CheckChoicesListView(String id, IModel<List<T>> choices, IChoiceRenderer<T> renderer)
	{
		super(id, choices, renderer);
	}

	protected void populateItem(ListItem<T> it)
	{
		final int index = it.getIndex();
		it.add(newLabel("label", getChoiceLabel(it.getModelObject())));
		it.add(newCheck("check", it.getModel(), index));
	}

	protected Check<T> newCheck(String id, IModel<T> model, final int index)
	{
		Check<T> check = new Check<T>(id, model)
		{

			/**
			 * The serialVersionUID.
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public String getValue()
			{
				return getChoiceValue(getModelObject(), index);
			}

			@Override
			public IModel<String> getLabel()
			{
				return Model.of(getChoiceLabel(getModelObject()));
			}

			@Override
			protected boolean getStatelessHint()
			{
				return true;
			}
		};
		return check;
	}

	protected Label newLabel(String id, String label)
	{
		return ComponentFactory.newLabel(id, Model.of(label));
	}
}