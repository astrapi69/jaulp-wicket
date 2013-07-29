package org.jaulp.wicket.components.velocity;


import net.sourceforge.jaulp.xml.tag.Tag;

import org.apache.wicket.Component;

public class WicketField<C extends Component> extends Tag {
	

	private static final String WICKET_ID = "wicket:id";

	public boolean removeChild(WicketField<?> child) {
		return super.removeChild(child);
	}

	public boolean addChild(WicketField<?> child) {
		return super.addChild(child);
	}
	
	public String getWicketId(){
		return getAttributes().get(WICKET_ID);
	}

	

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
