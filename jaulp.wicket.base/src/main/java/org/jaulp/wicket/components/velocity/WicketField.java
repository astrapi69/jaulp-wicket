package org.jaulp.wicket.components.velocity;


import net.sourceforge.jaulp.xml.tag.Tag;

import org.apache.wicket.Component;

public class WicketField<C extends Component> extends Tag {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private C component;
	
	public C getComponent() {
		return component;
	}

	public void setComponent(C component) {
		this.component = component;
	}
	
}
