package de.alpharogroup.wicket.components.i18n.list;

import net.sourceforge.jaulp.locale.ResourceBundleKey;

import org.apache.wicket.Component;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.jaulp.wicket.base.util.resource.ResourceModelFactory;

/**
 * The Class HeaderContentListPanel takes a header resource key and a list of
 * content resource keys that should be in a resource bundle for i18n.
 */
public abstract class HeaderContentListPanel extends Panel {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The header. */
	private final Component header;

	/** The list panel. */
	private final Component listPanel;

	/**
	 * Instantiates a new header content list panel.
	 *
	 * @param id
	 *            the id
	 */
	public HeaderContentListPanel(String id) {
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
	public HeaderContentListPanel(String id,
			IModel<HeaderContentListModel> model) {
		super(id, model);
		add(header = newHeaderLabel("header", newHeaderModel(model.getObject()
				.getHeaderResourceKey()
				)));
		add(listPanel = newListPanel("listPanel",
				Model.of(ContentListModel.builder().contentResourceKeys(model.getObject().getContentResourceKeys()).build())
				));
	}

	protected Component newListPanel(String id,
			IModel<ContentListModel> model) {
		return new DivListPanel(id, Model.ofList(model.getObject().getContentResourceKeys())) {
			
			private static final long serialVersionUID = 1L;

			@Override
			protected Component newListComponent(String id, ListItem<ResourceBundleKey> item) {
				return HeaderContentListPanel.this.newListComponent(id, item);
			}
		};
	}

	/**
	 * Gets the header.
	 *
	 * @return the header
	 */
	public Component getHeader() {
		return header;
	}

	public Component getListPanel() {
		return listPanel;
	}

	/**
	 * New header model.
	 *
	 * @param resourceKey
	 *            the resource key
	 * @return the i model
	 */
	protected IModel<String> newHeaderModel(final ResourceBundleKey resourceKey) {
		return ResourceModelFactory.newResourceModel(resourceKey, this);
	}

	/**
	 * New content resource model.
	 *
	 * @param resourceKey
	 *            the resource key
	 * @return the i model
	 */
	protected IModel<String> newContentResourceModel(IModel<ResourceBundleKey> resourceKey) {
		return ResourceModelFactory.newResourceModel(resourceKey.getObject(), this);
				
	}

	/**
	 * New header label.
	 *
	 * @param id
	 *            the id
	 * @param model
	 *            the model
	 * @return the component
	 */
	protected Component newHeaderLabel(String id, IModel<String> model) {
		Label label = new Label(id, model);
		return label;
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

}
