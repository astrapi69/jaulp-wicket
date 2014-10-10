package de.alpharogroup.wicket.components.i18n.list;

import java.util.List;

import net.sourceforge.jaulp.locale.ResourceBundleKey;

import org.apache.wicket.Component;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

/**
 * The Class ResourceBundleKeysListPanel takes a {@link ListView} of
 * {@link ResourceBundleKey}s content resource keys that should be in a resource
 * bundle for i18n.
 */
public abstract class ResourceBundleKeysListPanel extends Panel {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The list view. */
	private final ListView<ResourceBundleKey> listView;

	/**
	 * Instantiates a new {@link ResourceBundleKeysListPanel}.
	 *
	 * @param id
	 *            the id
	 */
	public ResourceBundleKeysListPanel(String id) {
		this(id, null);
	}

	/**
	 * Instantiates a new {@link ResourceBundleKeysListPanel}.
	 *
	 * @param id
	 *            the id
	 * @param model
	 *            the model
	 */
	public ResourceBundleKeysListPanel(String id, IModel<ContentListModel> model) {
		super(id, model);
		add(listView = newListView("listView",
				Model.ofList(model.getObject().getContentResourceKeys())));
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
	protected ListView<ResourceBundleKey> newListView(String id,
			IModel<List<? extends ResourceBundleKey>> model) {
		ListView<ResourceBundleKey> listView = new ListView<ResourceBundleKey>(
				id, model) {
			/** The Constant serialVersionUID. */
			private static final long serialVersionUID = 1L;

			protected void populateItem(ListItem<ResourceBundleKey> item) {
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
	protected abstract Component newListComponent(String id,
			ListItem<ResourceBundleKey> item);

	/**
	 * Gets the list view.
	 *
	 * @return the list view
	 */
	public ListView<ResourceBundleKey> getListView() {
		return listView;
	}

}