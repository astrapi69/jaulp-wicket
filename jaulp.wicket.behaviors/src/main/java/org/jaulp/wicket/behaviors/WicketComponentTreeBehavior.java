package org.jaulp.wicket.behaviors;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.Component;
import org.apache.wicket.MarkupContainer;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.model.Model;

/**
 * The Class WicketComponentTreeBehavior traverses through all wicket components and
 * adds an attribute 'componentreeid' that contains the absolute path to the wicket
 * component tree.
 *
 * @author Asterios Raptis
 */
public class WicketComponentTreeBehavior extends Behavior {

	/** The serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The constant COMPONENT_TREE_ID. */
	private static String COMPONENT_TREE_ID = "componentreeid";

	/** The model. */
	private Model<String> model;


	/**
	 * {@inheritDoc}
	 */
	@Override
	public void beforeRender(final Component component) {
		WicketComponentIdVisitor componentIdVisitor = new WicketComponentIdVisitor();
		if(component instanceof MarkupContainer) {
			MarkupContainer mc = (MarkupContainer) component;
			component.visitParents(mc.getClass(), componentIdVisitor);
			String path = componentIdVisitor.getAbsoluteComponentTreePath(mc);
			if (path != null) {
				model.setObject(path);
			}			
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void bind(final Component component) {
		model = new Model<String>();
		AttributeModifier attributeModifier = new AttributeModifier(
				COMPONENT_TREE_ID, model);
		component.add(attributeModifier);
	}
}
