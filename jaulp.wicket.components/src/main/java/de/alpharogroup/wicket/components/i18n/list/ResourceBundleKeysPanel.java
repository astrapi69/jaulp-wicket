package de.alpharogroup.wicket.components.i18n.list;

import java.util.List;

import net.sourceforge.jaulp.locale.ResourceBundleKey;

import org.apache.wicket.model.IModel;

/**
 * The Class ResourceBundleKeysPanel takes a list of content resource keys that should be
 * in a resource bundle for i18n.
 */
public abstract class ResourceBundleKeysPanel extends DivListPanel<ResourceBundleKey> {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new div list panel.
	 *
	 * @param id the id
	 * @param list the list
	 */
	public ResourceBundleKeysPanel(String id, List<ResourceBundleKey> list) {
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
	public ResourceBundleKeysPanel(String id,
			IModel<List<ResourceBundleKey>> content) {
		super(id, content);
	}

}