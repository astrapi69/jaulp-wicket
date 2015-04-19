/**
 * Copyright (C) 2010 Asterios Raptis
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
	 * @see WebApplication#newAjaxRequestTarget(Page)
	 *
	 */
	@SuppressWarnings("javadoc")
	public static AjaxRequestTarget newAjaxRequestTarget(WebApplication application, Page page)
	{
		return application.newAjaxRequestTarget(page);
	}

	/**
	 * Finds the current {@link AjaxRequestTarget} or creates a new ajax request target from the
	 * given application and page if the current {@link AjaxRequestTarget} is null.
	 *
	 * @param application
	 *            the web application
	 * @param page
	 *            page on which ajax response is made
	 * @return an AjaxRequestTarget instance
	 *
	 * @see WebApplication#newAjaxRequestTarget(Page)
	 */
	public static AjaxRequestTarget findOrCreateNewAjaxRequestTarget(WebApplication application, Page page)
	{
		AjaxRequestTarget target = findAjaxRequestTarget();
		if (target != null)
		{
			return target;
		}
		return newAjaxRequestTarget(application, page);
	}

	/**
	 * Finds the current {@link AjaxRequestTarget} or creates a new ajax request target from the
	 * given application and page if the current {@link AjaxRequestTarget} is null.
	 *
	 * @return an AjaxRequestTarget instance
	 *
	 * @see WebApplication#newAjaxRequestTarget(Page)
	 */
	public static AjaxRequestTarget findOrCreateNewAjaxRequestTarget()
	{
		return findOrCreateNewAjaxRequestTarget(WebApplication.get(), getCurrentPage());
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
