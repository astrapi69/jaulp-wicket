package de.alpharogroup.wicket.components.ajax.editable.tabs;

import lombok.Getter;

import org.apache.wicket.Component;
import org.apache.wicket.extensions.ajax.markup.html.AjaxLazyLoadPanel;
import org.apache.wicket.extensions.markup.html.tabs.ITab;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.model.IModel;

public abstract class AbstractAjaxLazyLoadTab<T> implements ITab {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	/** The title of the tab. */
	@Getter
	private IModel<String> title;
	/** The model of the content. */
	@Getter
	private IModel<T> content;

	public AbstractAjaxLazyLoadTab(IModel<String> title, IModel<T> content) {
		this.title = title;
		this.content = content;
	}

	@Override
	public WebMarkupContainer getPanel(String panelId) {
		return new AjaxLazyLoadPanel(panelId) {
			private static final long serialVersionUID = 1L;

			@Override
			public Component getLazyLoadComponent(String markupId) {
				return getLazyLoadPanel(markupId);
			}
		};
	}

	public abstract Component getLazyLoadPanel(final String markupId);

}
