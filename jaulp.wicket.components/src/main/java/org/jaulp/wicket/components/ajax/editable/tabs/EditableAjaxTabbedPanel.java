package org.jaulp.wicket.components.ajax.editable.tabs;

import java.util.List;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.extensions.ajax.markup.html.tabs.AjaxTabbedPanel;
import org.apache.wicket.extensions.markup.html.tabs.ITab;
import org.apache.wicket.model.IModel;

/**
 * The Class EditableAjaxTabbedPanel extends the AjaxTabbedPanel and adds functionality to add or remove tabs from the TabbedPanel.
 *
 * @param <T> the generic type
 */
public class EditableAjaxTabbedPanel<T extends ITab> extends AjaxTabbedPanel<T> {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new editable ajax tabbed panel.
	 *
	 * @param id the id
	 * @param tabs the tabs
	 * @param model the model
	 */
	public EditableAjaxTabbedPanel(String id, List<T> tabs,
			IModel<Integer> model) {
		super(id, tabs, model);
	}

	/**
	 * Instantiates a new editable ajax tabbed panel.
	 *
	 * @param id the id
	 * @param tabs the tabs
	 */
	public EditableAjaxTabbedPanel(String id, List<T> tabs) {
		super(id, tabs);
	}

	/**
	 * On new tab.
	 *
	 * @param target the target
	 * @param tab the tab
	 */
	protected void onNewTab(final AjaxRequestTarget target, final T tab) {
		getTabs().add(tab);
		setSelectedTab(getTabs().size() - 1);
		target.add(this);
	}

	/**
	 * On new tab.
	 *
	 * @param target the target
	 * @param tab the tab
	 * @param index the index
	 */
	protected void onNewTab(final AjaxRequestTarget target, final T tab,
			final int index) {
		if ((index < 0) || (index >= getTabs().size())) {
			throw new IndexOutOfBoundsException();
		}
		getTabs().add(index, tab);
		setSelectedTab(index);
		target.add(this);
	}

	/**
	 * On remove tab.
	 *
	 * @param target the target
	 * @param index the index
	 */
	protected void onRemoveTab(final AjaxRequestTarget target, final int index) {
		int tabSize = getTabs().size();
		// there have to be at least one tab on the ajaxTabbedPanel...
		if (2 <= tabSize && index < tabSize) {
			setSelectedTab(index);
			getTabs().remove(index);
			target.add(this);
		}
	}

	/**
	 * On remove tab.
	 *
	 * @param target the target
	 * @param tab the tab
	 */
	protected void onRemoveTab(final AjaxRequestTarget target, final T tab) {
		final int index = getTabs().indexOf(tab);
		if (0 <= index) {
			onRemoveTab(target, index);
		}
	}

}
