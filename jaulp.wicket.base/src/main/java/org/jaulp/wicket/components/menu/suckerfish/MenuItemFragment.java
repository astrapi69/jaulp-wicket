package org.jaulp.wicket.components.menu.suckerfish;

import org.apache.wicket.MarkupContainer;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.panel.Fragment;

/**
 * The Class MenuItemFragment.
 * 
 * @author Asterios Raptis
 */
public final class MenuItemFragment extends Fragment {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 0L;

	/**
	 * Instantiates a new menu item fragment.
	 * 
	 * @param menuItem
	 *            the menu item
	 */
	public MenuItemFragment(final MenuItem menuItem, final MarkupContainer markupProvider) {
		super("menuitemfragment", "MENUITEMFRAGMENT",
				markupProvider);
		// Add the menu's label (hyperlinked if a link is provided)
		if (menuItem.getLink() != null) {
			add(new LinkFragment(menuItem.getLink(), markupProvider));
		} else {
			add(new TextFragment(menuItem.getLabel(), markupProvider));
		}
		final WebMarkupContainer menuitemul = new WebMarkupContainer(
				"menuitemlist");
		add(menuitemul);
		// Hide the <ul> tag if there are no submenus
		menuitemul.setVisible(menuItem.getChildren().size() > 0);
		// Add a down or right arrow icon if there are children
		if (menuItem.getChildren().size() > 0) {
			menuItem.getLabel().add(MenuPanel.menuHasSubmenuAppender);
		}
		// Add the submenus
		menuitemul.add(new SubMenuListView("menuitemlinks", menuItem
				.getChildren(), markupProvider));
	}
}