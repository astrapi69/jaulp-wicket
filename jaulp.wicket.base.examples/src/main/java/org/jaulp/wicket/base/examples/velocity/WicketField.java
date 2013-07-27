package org.jaulp.wicket.base.examples.velocity;


import net.sourceforge.jaulp.xml.tag.Tag;

import org.apache.wicket.Component;

public class WicketField<C extends Component, T, M > {

	private C component;
	
	private T genericType;
	
	private M model;
	
	public M getModel() {
		return model;
	}

	public void setModel(M model) {
		this.model = model;
	}

	private Tag wicketTag;

	public Tag getWicketTag() {
		return wicketTag;
	}

	public void setWicketTag(Tag wicketTag) {
		this.wicketTag = wicketTag;
	}

	public T getGenericType() {
		return genericType;
	}

	public void setGenericType(T genericType) {
		this.genericType = genericType;
	}

	public C getComponent() {
		return component;
	}

	public void setComponent(C component) {
		this.component = component;
	}
	
}
