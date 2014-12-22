package de.alpharogroup.wicket.components.i18n.list;

import java.util.List;

import net.sourceforge.jaulp.locale.ResourceBundleKey;

import org.apache.wicket.model.IModel;

import de.alpharogroup.wicket.components.listview.ListViewPanel;

/**
 * The Class DivListPanel takes a list of content resource keys that should be
 * in a resource bundle for i18n.
 */
public abstract class DivListPanel extends ListViewPanel<ResourceBundleKey> {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new div list panel.
	 *
	 * @param id the id
	 * @param list the list
	 */
	public DivListPanel(String id, List<ResourceBundleKey> list) {
		super(id, list);
	}

	/**
	 * Instantiates a new header content list panel.
	 *
	 * @param id
	 *            the id
	 * @param content
	 *            the model
	 */
	public DivListPanel(String id,
			IModel<List<ResourceBundleKey>> content) {
		super(id, content);
	}

}