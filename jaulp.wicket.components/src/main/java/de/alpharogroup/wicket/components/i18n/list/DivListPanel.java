package de.alpharogroup.wicket.components.i18n.list;

import java.util.List;

import org.apache.wicket.model.IModel;

import de.alpharogroup.wicket.components.listview.ListViewPanel;

/**
 * The Class DivListPanel.
 * 
 * @param <T>
 *            the generic type of the list
 */
public abstract class DivListPanel<T> extends ListViewPanel<T>
{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new div list panel.
	 *
	 * @param id
	 *            the id
	 * @param list
	 *            the list
	 */
	public DivListPanel(String id, List<T> list)
	{
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
	public DivListPanel(String id, IModel<List<T>> content)
	{
		super(id, content);
	}

}