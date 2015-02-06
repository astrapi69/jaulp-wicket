package de.alpharogroup.wicket.components.listview;

import java.util.List;

import org.apache.wicket.Component;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.util.ListModel;

/**
 * The Class ListModelPanel takes a {@link ListView} of a generic type.
 *
 * @param <T>
 *            the generic type
 */
public abstract class ListViewPanel<T> extends Panel
{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The list view. */
	private final ListView<T> listView;

	/**
	 * Instantiates a new {@link ListViewPanel}.
	 *
	 * @param id
	 *            the id
	 * @param list
	 *            the list
	 */
	public ListViewPanel(String id, List<T> list)
	{
		this(id, new ListModel<>(list));
	}

	/**
	 * Instantiates a new {@link ListViewPanel}.
	 *
	 * @param id
	 *            the id
	 * @param model
	 *            the model
	 */
	public ListViewPanel(String id, IModel<List<T>> model)
	{
		super(id, model);
		if (model == null)
		{
			throw new IllegalArgumentException("Argument 'model' may not be null.");
		}
		add(listView = newListView("listView", model));
	}

	/**
	 * New list view.
	 *
	 * @param id
	 *            the id
	 * @param model
	 *            the model
	 * @return the list view
	 */
	protected ListView<T> newListView(String id, IModel<List<T>> model)
	{
		ListView<T> listView = new ListView<T>(id, model)
		{
			/** The Constant serialVersionUID. */
			private static final long serialVersionUID = 1L;

			@Override
			protected void populateItem(ListItem<T> item)
			{
				item.add(newListComponent("item", item));
			}

		};
		return listView;
	}

	/**
	 * New list component.
	 *
	 * @param id
	 *            the id
	 * @param item
	 *            the item
	 * @return the component
	 */
	protected abstract Component newListComponent(String id, ListItem<T> item);

	/**
	 * Gets the list view.
	 *
	 * @return the list view
	 */
	public ListView<T> getListView()
	{
		return listView;
	}

}