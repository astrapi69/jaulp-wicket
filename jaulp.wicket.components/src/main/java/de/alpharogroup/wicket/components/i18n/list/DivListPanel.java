package de.alpharogroup.wicket.components.i18n.list;

import org.apache.wicket.model.IModel;

/**
 * The Class HeaderContentListPanel takes a header resource key and a list of
 * content resource keys that should be in a resource bundle for i18n.
 */
public abstract class DivListPanel extends ResourceBundleKeysListPanel {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new header content list panel.
	 *
	 * @param id
	 *            the id
	 */
	public DivListPanel(String id) {
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
	public DivListPanel(String id,
			IModel<ContentListModel> model) {
		super(id, model);
	}

}