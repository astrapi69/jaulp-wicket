package org.jaulp.wicket.behaviors;

import org.apache.wicket.Component;
import org.apache.wicket.behavior.Behavior;

/**
 * The Class RequiredBehavior adds a span tag at the end of a component that markes it as required.
 */
public class RequiredBehavior extends Behavior {

    /**
     * The serialVersionUID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Instantiates a new RequiredBehavior.
     */
    public RequiredBehavior() {
    }

    /**
     * {@inheritDoc} 
     *  for more information @see org.apache.wicket.behavior.AbstractBehavior#afterRender(org.apache.wicket.Component)
     */
    @Override
    public void afterRender(final Component component) {
        super.afterRender(component);
        component.getResponse().write("<span class=\"required\">*</span>");
    }

}
