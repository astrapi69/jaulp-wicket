package de.alpharogroup.wicket.components.ajax.editable.tabs;

import lombok.Getter;

import org.apache.wicket.extensions.markup.html.tabs.ITab;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.model.IModel;


/**
 * Convenience class that takes care of common ITab functionality.
 *
 * @param <T>
 *            the generic type
 * @see ITab
 */
public abstract class AbstractContentTab<T> extends CloseableTab {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The content. */
	@Getter
	private final IModel<T> content;

	/**
	 * Constructor.
	 *
	 * @param title
	 *            IModel used to represent the title of the tab. Must contain a
	 *            string
	 * @param content
	 *            IModel used to represent the content of the tab.
	 */
	public AbstractContentTab(final IModel<String> title, final IModel<T> content, final IModel<String> closeTitle) {
		super(title, closeTitle);
		this.content = content;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isVisible() {
		return true;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public abstract WebMarkupContainer getPanel(final String panelId);
}