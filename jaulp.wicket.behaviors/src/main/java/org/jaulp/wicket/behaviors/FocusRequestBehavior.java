package org.jaulp.wicket.behaviors;

import org.apache.wicket.Component;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.OnLoadHeaderItem;

/**
 * The Class FocusRequestBehavior set the focus on a component when the page is load.
 */
public class FocusRequestBehavior extends Behavior {
	
	/**
	 * The serialVersionUID.
	 */
	private static final long serialVersionUID = 2312277970691939826L;
	/** The Constant DEFAULT_ID is the default id that will be set if the id is not set explicit. */
	public static final String DEFAULT_ID = FocusRequestBehavior.class.getSimpleName();
	
	/**
	 * Instantiates a new request focus behavior.
	 */
	public FocusRequestBehavior() {
	}

	/**
	 * Creates the java script code for request focus.
	 *
	 * @param component the component
	 * @return the string
	 */
	private String createJavaScript(Component component) {
		String javascript = "setTimeout("
				+ "function() {"
				+ "var component = document.getElementById(\""
				+ component.getMarkupId()
				+ "\");component.focus();component.select();}, 1)";				
        return javascript;
    }

	/**
     * {@inheritDoc}
     */
	public void renderHead(Component component, IHeaderResponse response) {
		component.setOutputMarkupId(true);
		response.render(OnLoadHeaderItem
				.forScript(createJavaScript(component)));
		super.renderHead(component, response);
	}
}
