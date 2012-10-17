package org.jaulp.wicket.behaviors;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.wicket.MarkupContainer;
import org.apache.wicket.markup.html.TransparentWebMarkupContainer;
import org.apache.wicket.markup.html.internal.Enclosure;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.util.visit.IVisit;
import org.apache.wicket.util.visit.IVisitor;

/**
 * The Class WicketComponentIdVisitor.
 *
 * @author Asterios Raptis
 */
public class WicketComponentIdVisitor<C extends MarkupContainer, R> implements IVisitor<C, R> {

	/** The wicket component ids. */
	private List<String> wicketComponentIds = new ArrayList<String>();

	/**
	 * {@inheritDoc}.
	 */
	public void component(final C component, final IVisit<R> visit) {
		String componentId = component.getId();
		if (component instanceof Item) {
			Item<?> item = (Item<?>) component;
			componentId = Integer.toString(item.getIndex());
		} else if (component instanceof Enclosure) {
			return;
		} else if (component instanceof TransparentWebMarkupContainer) {
			return;
		}

		if (componentId != null) {
			wicketComponentIds.add(componentId);
			return;
		}
		visit.stop();
	}

	/**
	 * Gets the absolute component id path.
	 *
	 * @param component
	 *            the component
	 * @return the absolute component id path
	 */
	public String getAbsoluteComponentTreePath(final C component) {
		Collections.reverse(wicketComponentIds);
		String componentId = component.getId();
		wicketComponentIds.add(wicketComponentIds.size(), componentId);

		StringBuffer sb = new StringBuffer();
		int size = wicketComponentIds.size();
		for (int i = 0; i < size; i++) {
			sb.append(wicketComponentIds.get(i));
			if (i != size - 1) {
				sb.append(":");
			}
		}
		String path = sb.toString();
		return path;
	}

}