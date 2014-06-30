/**
 * Copyright (C) 2010 Asterios Raptis
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
	public void onNewTab(final AjaxRequestTarget target, final T tab) {
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
	public void onNewTab(final AjaxRequestTarget target, final T tab,
			final int index) {
		if ((index < 0) || (index >= getTabs().size())) {
			throw new IndexOutOfBoundsException();
		}
		getTabs().add(index, tab);
		setSelectedTab(index);
		target.add(this);
	}

	/**
	 * On remove tab removes the tab of the given index.
	 *
	 * @param target the target
	 * @param index the index
	 */
	public void onRemoveTab(final AjaxRequestTarget target, final int index) {
		int tabSize = getTabs().size();
		// there have to be at least one tab on the ajaxTabbedPanel...
		if (2 <= tabSize && index < tabSize) {
			setSelectedTab(index);
			getTabs().remove(index);
			target.add(this);
		}
	}

	/**
	 * On remove tab removes the given tab if it does exists.
	 *
	 * @param target the target
	 * @param tab the tab
	 */
	public void onRemoveTab(final AjaxRequestTarget target, final T tab) {
		final int index = getTabs().indexOf(tab);
		if (0 <= index) {
			onRemoveTab(target, index);
		}
	}

}
