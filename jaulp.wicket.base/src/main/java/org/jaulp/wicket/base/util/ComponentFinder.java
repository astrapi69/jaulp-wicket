package org.jaulp.wicket.base.util;

import org.apache.wicket.Component;
import org.apache.wicket.Page;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.core.request.handler.IPageRequestHandler;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.request.IRequestHandler;
import org.apache.wicket.request.cycle.RequestCycle;

/**
 * The Class ComponentFinder.
 */
public final class ComponentFinder
{

	/**
	 * Gets the current page.
	 *
	 * @return the current page
	 */
	public static Page getCurrentPage()
	{
		IRequestHandler requestHandler = RequestCycle.get().getActiveRequestHandler();
		if (requestHandler instanceof IPageRequestHandler)
		{
			IPageRequestHandler pageRequestHandler = (IPageRequestHandler)requestHandler;
			return (Page)pageRequestHandler.getPage();
		}
		return null;
	}


	/**
	 * Finds the AjaxRequestTarget from the current RequestCycle.
	 * 
	 * @return the found AjaxRequestTarget or {@code null}
	 */
	public static AjaxRequestTarget findAjaxRequestTarget()
	{
		return RequestCycle.get().find(AjaxRequestTarget.class);
	}


	/**
	 * Creates a new ajax request target from the given Page.
	 * 
	 * @param application
	 *            the web application
	 * @param page
	 *            page on which ajax response is made
	 * @return an AjaxRequestTarget instance
	 * 
	 * @see for more infomation look at
	 *      org.apache.wicket.protocol.http.WebApplication#newAjaxRequestTarget(Page)
	 */
	@SuppressWarnings("javadoc")
	public static AjaxRequestTarget newAjaxRequestTarget(WebApplication application, Page page)
	{
		return application.newAjaxRequestTarget(page);
	}

	/**
	 * Finds the first parent of the given childComponent from the given parentClass.
	 *
	 * @param childComponent
	 *            the child component
	 * @param parentClass
	 *            the parent class
	 * @return the component
	 */
	public static Component findParent(Component childComponent,
		Class<? extends Component> parentClass)
	{
		Component parent = childComponent.getParent();
		while (parent != null)
		{
			if (parent.getClass().equals(parentClass))
			{
				break;
			}
			parent = parent.getParent();
		}
		return parent;
	}
}