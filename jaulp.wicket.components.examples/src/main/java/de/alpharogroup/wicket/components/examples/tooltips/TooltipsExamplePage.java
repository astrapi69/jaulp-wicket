package de.alpharogroup.wicket.components.examples.tooltips;

import org.apache.wicket.Component;
import org.apache.wicket.model.Model;

import de.alpharogroup.wicket.components.examples.area.publicly.PubliclyBasePage;

/**
 * Example page for the several tooltips like tooltipster or bootstrap tooltip.
 */
public class TooltipsExamplePage extends PubliclyBasePage<Object> {

  /**
	 * The serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

@Override
  public Component getContainerPanel() {
    return new TooltipsExamplePanel(CONTAINER_PANEL_ID, Model.of(""));
  }
}
