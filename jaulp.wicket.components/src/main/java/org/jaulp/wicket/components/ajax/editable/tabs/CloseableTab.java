package org.jaulp.wicket.components.ajax.editable.tabs;

import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.model.IModel;

public abstract class CloseableTab implements ICloseableTab {
	private static final long serialVersionUID = 1L;

	IModel<String> title;
	
	IModel<String> closeTitle;
	public IModel<String> getCloseTitle() {
		return closeTitle;
	}

	/**
	 * Constructor
	 * 
	 * @param title
	 *            IModel used to represent the title of the tab. Must contain a string
	 */
	public CloseableTab(final IModel<String> title, final IModel<String> closeTitle)
	{
		this.title = title;
		this.closeTitle = closeTitle;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public IModel<String> getTitle()
	{
		return title;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isVisible()
	{
		return true;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public abstract WebMarkupContainer getPanel(final String panelId);
}
