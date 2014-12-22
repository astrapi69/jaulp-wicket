package de.alpharogroup.wicket.components.footer;

import java.util.List;

import org.apache.wicket.Component;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.util.ListModel;

import de.alpharogroup.wicket.components.link.LinkModel;

public abstract class FooterMenuPanel extends Panel {

	/**
	 * The serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;
	
	private Component linkListPanel;	

	public Component getLinkListPanel() {
		return linkListPanel;
	}

	public FooterMenuPanel(final String id, List<LinkModel> list) {
		this(id, new ListModel<LinkModel>(list));		
	}

	public FooterMenuPanel(final String id, IModel<List<LinkModel>> model) {
		super(id);
		add(linkListPanel = newLinkListPanel("linkListPanel", model));
	}
	
	protected abstract Component newLinkListPanel(final String id, IModel<List<LinkModel>> model);

}