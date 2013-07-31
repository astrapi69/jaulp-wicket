package org.jaulp.wicket.behaviors;

import org.apache.wicket.Component;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;

/**
 * The Class AddJavascriptBehavior adds javascript code as String with an id.
 */
public class AddJavascriptBehavior extends Behavior {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * The unique id for the javascript element. This can be null, however in
	 * that case the ajax header contribution can't detect duplicate script
	 * fragments.
	 */
	private String id;

	/** The javascript code to be rendered. */
	private CharSequence javascript;

	/**
	 * Instantiates a new adds the javascript behavior.
	 * 
	 * @param javascript
	 *            the javascript
	 * @param id
	 *            the id
	 */
	public AddJavascriptBehavior(CharSequence javascript, String id) {
		super();
		this.javascript = javascript;
		this.id = id;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.apache.wicket.behavior.Behavior#renderHead(org.apache.wicket.Component
	 * , org.apache.wicket.markup.head.IHeaderResponse)
	 */
	public void renderHead(Component component, IHeaderResponse response) {
		response.render(JavaScriptHeaderItem
				.forScript(this.javascript, this.id));
		super.renderHead(component, response);
	}

}
