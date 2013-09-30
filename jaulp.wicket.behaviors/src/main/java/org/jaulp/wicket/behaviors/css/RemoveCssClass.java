package org.jaulp.wicket.behaviors.css;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.model.Model;

public class RemoveCssClass extends AttributeModifier {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public RemoveCssClass( String cssClass) {
		super("class", new Model<String>(cssClass));
	}

	@Override
    protected String newValue(String currentValue, String cssClass) {
		if(currentValue != null)
        return currentValue.replaceAll(cssClass, "");
		return "";
    }
}