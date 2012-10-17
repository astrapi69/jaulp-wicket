package org.jaulp.wicket.annotated.header.contributors.examples.panels.home;

import org.apache.wicket.markup.html.basic.Label;
import org.jaulp.wicket.base.BasePanel;

import net.sourceforge.jaulp.io.annotations.ImportResource;
import net.sourceforge.jaulp.io.annotations.ImportResources;

/**
 * @author admin
 */
@ImportResources( resources = {
        @ImportResource( resourceName = "HomePanel.css", resourceType = "css", index = 0 ),
        @ImportResource( resourceName = "HomePanel.js", resourceType = "js", index = 1 ),
        @ImportResource( resourceName = "HomePanel-new.js", resourceType = "js", index = 2 ) } )
public class HomePanel extends BasePanel {

	/**
	 * The serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;

	public HomePanel(String id) {
		super(id);

        // Add the simplest type of label
        add(new Label("message", "If you see this message wicket is properly configured and running"));
	}

}

