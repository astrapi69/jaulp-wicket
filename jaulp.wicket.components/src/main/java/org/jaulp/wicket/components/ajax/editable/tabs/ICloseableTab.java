package org.jaulp.wicket.components.ajax.editable.tabs;

import org.apache.wicket.extensions.markup.html.tabs.ITab;
import org.apache.wicket.model.IModel;

public interface ICloseableTab extends ITab {
	IModel<String> getCloseTitle();
}