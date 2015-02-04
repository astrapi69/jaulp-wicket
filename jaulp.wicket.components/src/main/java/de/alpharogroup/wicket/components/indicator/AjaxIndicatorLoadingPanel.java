package de.alpharogroup.wicket.components.indicator;

import org.apache.wicket.ajax.AbstractDefaultAjaxBehavior;
import org.apache.wicket.markup.html.image.Image;
import org.apache.wicket.markup.html.panel.Panel;

/**
 * The Class AjaxIndicatorLoadingPanel.
 */
public class AjaxIndicatorLoadingPanel extends Panel
{


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new loading indicator panel.
	 *
	 * @param id
	 *            the id
	 */
	public AjaxIndicatorLoadingPanel(String id)
	{
		super(id);
		setOutputMarkupId(true);
		Image ajaxindicator = new Image("ajaxindicator", AbstractDefaultAjaxBehavior.INDICATOR);
		add(ajaxindicator);
	}
}