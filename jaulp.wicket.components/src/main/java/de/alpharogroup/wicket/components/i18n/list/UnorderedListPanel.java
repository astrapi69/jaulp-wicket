package de.alpharogroup.wicket.components.i18n.list;

import net.sourceforge.jaulp.locale.ResourceBundleKey;

import org.apache.wicket.Component;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.IModel;
import org.jaulp.wicket.base.util.resource.ResourceModelFactory;

/**
 * The Class ContentListPanel takes a {@link ListView} of {@link ResourceBundleKey}s
 * content resource keys that should be in a resource bundle for i18n.
 */
public abstract class UnorderedListPanel extends ResourceBundleKeysListPanel {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new header content list panel.
	 *
	 * @param id
	 *            the id
	 */
	public UnorderedListPanel(String id) {
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
	public UnorderedListPanel(String id, IModel<ContentListModel> model) {
		super(id, model);
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

}