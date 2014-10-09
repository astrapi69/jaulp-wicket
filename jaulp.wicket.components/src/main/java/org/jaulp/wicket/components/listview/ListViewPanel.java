package org.jaulp.wicket.components.listview;

import java.util.List;

import org.apache.wicket.Component;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

/**
 * The abstract Class ListViewPanel.
 *
 * @param <T> the generic type
 */
public abstract class ListViewPanel<T> extends Panel {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The list view. */
	private final ListView<T> listView;

	/**
	 * Gets the list view.
	 *
	 * @return the list view
	 */
	public ListView<T> getListView() {
		return listView;
	}

	/**
	 * Instantiates a new list view panel.
	 *
	 * @param id the id
	 * @param list the list
	 */
	public ListViewPanel(final String id, final List<? extends T> list) {
		this(id, Model.ofList(list));
	}
	
	/**
	 * Instantiates a new list view panel.
	 *
	 * @param id the id
	 * @param model the model
	 */
	public ListViewPanel(String id, IModel<? extends List<? extends T>> model) {
		super(id, model);
		add(listView = newListView("listView", model));
	}

	/**
	 * New list view.
	 *
	 * @param id the id
	 * @param model the model
	 * @return the list view
	 */
	protected ListView<T> newListView(String id, IModel<? extends List<? extends T>> model) {
		ListView<T> listView = new ListView<T>(id, model) {
			/**
			 * The serialVersionUID.
			 */
			private static final long serialVersionUID = 1L;

			protected void populateItem(ListItem<T> item) {
				item.add(newListComponent("item", item));
			}

		};
		return listView;
	}

	/**
	 * New list component.
	 *
	 * @param id the id
	 * @param item the item
	 * @return the component
	 */
	protected abstract Component newListComponent(String id, ListItem<T> item);

}
