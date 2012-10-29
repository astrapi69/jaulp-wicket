package org.jaulp.wicket.base.examples;

import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.jaulp.wicket.components.menu.suckerfish.MenuItem;
import org.jaulp.wicket.components.menu.suckerfish.MenuPanel;
import org.jaulp.wicket.components.menu.suckerfish.MenuUtils;


public class MenubarPanel extends Panel {

	
	public MenubarPanel(String id) {
		super(id);
		MenuPanel menuBar = new MenuPanel("menuBar");
		add(menuBar);
		
		initializeMenu(menuBar);
	}
	
	public MenubarPanel(String id, IModel<?> model) {
		super(id, model);
		MenuPanel menuBar = new MenuPanel("menuBar");
		add(menuBar);
		
		initializeMenu(menuBar);
	}

	private void initializeMenu(MenuPanel menuBar) {
		
		MenuItem miHome = MenuUtils.createMenuItem(
				HomePage.class, "top.menu.home", this);

		menuBar.addMenu(miHome);
		
		MenuItem miAnother = MenuUtils.createMenuItem(
				AnotherPage.class, "top.menu.another", this);

		menuBar.addMenu(miAnother);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

}
