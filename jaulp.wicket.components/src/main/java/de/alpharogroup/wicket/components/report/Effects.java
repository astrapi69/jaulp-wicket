package de.alpharogroup.wicket.components.report;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;

/**
 * The Class {@link Effects}.
 */
public class Effects
{

	/**
	 * Replace.
	 *
	 * @param target
	 *            the target
	 * @param component
	 *            the component
	 */
	public static void replace(final AjaxRequestTarget target, final Component component)
	{
		component.add(new DisplayNoneBehavior());
		// target.prependJavaScript("notify|jQuery('#"+component.getMarkupId()+"').slideUp(1000, notify);");
		target.add(component);
		target.appendJavaScript("jQuery('#" + component.getMarkupId() + "').slideDown(100);");
	}
}