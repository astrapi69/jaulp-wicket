package org.jaulp.wicket.components.menu.suckerfish;

import java.util.ArrayList;
import java.util.List;

import net.sourceforge.jaulp.io.annotations.ImportResource;
import net.sourceforge.jaulp.io.annotations.ImportResources;

import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.jaulp.wicket.base.BasePanel;

@ImportResources(resources = {
		@ImportResource(resourceName = "MenuPanel.css", resourceType = "css"),
		@ImportResource(resourceName = "MenuPanel.js", resourceType = "js") })
public class MenuPanel extends BasePanel {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 0L;

	/** The Constant LINK_ID. */
	public static final String LINK_ID = "linkid";

	/** The Constant LINK_TEXT_ID. */
	public static final String LINK_TEXT_ID = "linktext";

	/**
	 * This appender is used to add a down or right arrow icon if there are
	 * children.
	 */
	public static final AttributeAppender menuHasSubmenuAppender = new AttributeAppender(
			"class", new Model<String>("menu-has-submenu"), " ");

	/** The top menu items. */
	private final List<MenuItem> topMenuItems = new ArrayList<MenuItem>();

	/**
	 * Instantiates a new suckerfish menu panel.
	 * 
	 * @param id
	 *            the id
	 */
	public MenuPanel(final String id) {
		super(id);
		// Add the top menus
		add(
				new SubMenuListView("topmenuitems",
				new PropertyModel<List<MenuItem>>(this, "topMenuItems"), this)
			);
	}

	/**
	 * Add one menu item.
	 * 
	 * @param menu
	 *            the menu
	 */
	public void addMenu(final MenuItem menu) {
		topMenuItems.add(menu);
	}

	/**
	 * Add all menus at once.
	 * 
	 * @param menuItems
	 *            the new menu items
	 */
	public void setMenuItems(final List<MenuItem> menuItems) {
		topMenuItems.clear();
		topMenuItems.addAll(menuItems);
	}
	
}
