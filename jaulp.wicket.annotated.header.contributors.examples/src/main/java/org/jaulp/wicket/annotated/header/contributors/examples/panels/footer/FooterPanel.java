package org.jaulp.wicket.annotated.header.contributors.examples.panels.footer;

import org.apache.wicket.markup.html.basic.Label;
import org.jaulp.wicket.base.BasePanel;

import net.sourceforge.jaulp.io.annotations.ImportResource;
import net.sourceforge.jaulp.io.annotations.ImportResources;

/**
 * @author admin
 */
@ImportResources( resources = {@ImportResource( resourceName = "FooterPanel.css", resourceType = "css", index = 0 )})
public class FooterPanel extends BasePanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public FooterPanel(String id) {
		super(id);

        // Add the simplest type of label
        add(new Label("message", "jaulp.wicket"));
	}

}

