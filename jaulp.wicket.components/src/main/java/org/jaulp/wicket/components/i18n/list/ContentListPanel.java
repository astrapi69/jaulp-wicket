package org.jaulp.wicket.components.i18n.list;

import java.util.List;

import org.apache.wicket.Component;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.jaulp.wicket.base.util.resource.ResourceModelFactory;

import net.sourceforge.jaulp.locale.ResourceBundleKey;

/**
 * The Class ContentListPanel takes a {@link ListView} of {@link ResourceBundleKey}s
 * content resource keys that should be in a resource bundle for i18n.
 */
public abstract class ContentListPanel extends Panel {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The list view. */
	private final ListView<ResourceBundleKey> listView;

	/**
	 * Instantiates a new header content list panel.
	 *
	 * @param id
	 *            the id
	 */
	public ContentListPanel(String id) {
		this(id, null);
	}

	/**
	 * Instantiates a new header content list panel.
	 *
	 * @param id
	 *            the id
	 * @param model
	 *            the model
	 */
	public ContentListPanel(String id, IModel<ContentListModel> model) {
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
		ListView<ResourceBundleKey> listView = new ListView<ResourceBundleKey>(id, model) {
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
	protected Component newListComponent(String id,
			ListItem<ResourceBundleKey> item){
		return new Label(id, ResourceModelFactory.newResourceModel(item.getModel().getObject(), this));
	}

	/**
	 * Gets the list view.
	 *
	 * @return the list view
	 */
	public ListView<ResourceBundleKey> getListView() {
		return listView;
	}

}
