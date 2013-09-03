package org.jaulp.wicket.components.velocity;

import net.sourceforge.jaulp.xml.tag.SimpleTag;

import org.apache.wicket.Component;

/**
 * The Class WicketField encapsulates a wicket component and can create its own
 * html tag for this component. It can be used with velocity to create dynamic
 * panel without create html templates for it.
 * 
 * @param <C>
 *            the generic type for the Component
 */
public class WicketField<C extends Component> extends SimpleTag {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The Constant WICKET_ID for the corresponding html attribute. */
	public static final String WICKET_ID = "wicket:id";

	/** The component. */
	private C component;

	/**
	 * Adds the child.
	 * 
	 * @param child
	 *            the child
	 * @return true, if successful
	 */
	public boolean addChild(WicketField<?> child) {
		return super.addChild(child);
	}

	/**
	 * Gets the component.
	 * 
	 * @return the component
	 */
	public C getComponent() {
		return component;
	}

	/**
	 * Gets the wicket id.
	 * 
	 * @return the wicket id
	 */
	public String getWicketId() {
		return getAttributes().get(WICKET_ID);
	}

	/**
	 * Removes the child.
	 * 
	 * @param child
	 *            the child
	 * @return true, if successful
	 */
	public boolean removeChild(WicketField<?> child) {
		return super.removeChild(child);
	}

	/**
	 * Sets the component.
	 * 
	 * @param component
	 *            the new component
	 */
	public void setComponent(C component) {
		this.component = component;
	}

}
