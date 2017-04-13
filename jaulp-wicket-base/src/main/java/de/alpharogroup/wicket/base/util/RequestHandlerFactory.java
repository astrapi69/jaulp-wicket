package de.alpharogroup.wicket.base.util;

import org.apache.wicket.core.request.handler.PageProvider;
import org.apache.wicket.core.request.handler.RenderPageRequestHandler;
import org.apache.wicket.request.IRequestHandler;
import org.apache.wicket.request.component.IRequestablePage;
import org.apache.wicket.request.cycle.RequestCycle;
import org.apache.wicket.request.mapper.parameter.PageParameters;

/**
 * A factory for creating RequestHandler objects and schedule the request handler to be executed
 * after the current one.
 */
public class RequestHandlerFactory {

	/**
	 * New request handler.
	 *
	 * @param pageProvider the page provider
	 * @return the i request handler
	 */
	public static IRequestHandler newRequestHandler(final PageProvider pageProvider)
	{
		final RenderPageRequestHandler renderPageRequestHandler = new RenderPageRequestHandler(pageProvider);
		return renderPageRequestHandler;
	}

	/**
	 * Schedule the given class of type {@link IRequestablePage}.
	 *
	 * @param pageClass the page class
	 */
	public static void schedule(final Class<? extends IRequestablePage> pageClass)
	{
		RequestHandlerFactory.schedule(PageProviderFactory.newPageProvider(pageClass));
	}

	/**
	 * Schedule the given class of type {@link IRequestablePage}.
	 *
	 * @param pageClass the page class
	 * @param parameters
	 *            the parameters
	 */
	public static void schedule(final Class<? extends IRequestablePage> pageClass,
		final PageParameters parameters)
	{
		RequestHandlerFactory.schedule(PageProviderFactory.newPageProvider(pageClass, parameters));
	}

	/**
	 * Schedule the given {@link IRequestablePage} object.
	 *
	 * @param requestablePage
	 *            the requestable page
	 */
	public static void schedule(final IRequestablePage requestablePage)
	{
		RequestHandlerFactory.schedule(PageProviderFactory.newPageProvider(requestablePage));
	}

	/**
	 * Schedule the given {@link IRequestablePage} object and the given {@link PageParameters}.
	 *
	 * @param requestablePage
	 *            the requestable page
	 * @param parameters
	 *            the parameters
	 */
	public static void schedule(final IRequestablePage requestablePage,
		final PageParameters parameters)
	{
		RequestHandlerFactory
			.schedule(PageProviderFactory.newPageProvider(requestablePage, parameters));
	}

	/**
	 * Schedule the given {@link IRequestHandler} object to be executed after the current one.
	 *
	 * @param requestHandler
	 *            the request handler
	 */
	public static void schedule(final IRequestHandler requestHandler)
	{
		RequestCycle.get().scheduleRequestHandlerAfterCurrent(requestHandler);
	}

	/**
	 * Schedule the given {@link PageProvider} object.
	 *
	 * @param pageProvider
	 *            the page provider
	 */
	public static void schedule(final PageProvider pageProvider)
	{
		RequestHandlerFactory.schedule(RequestHandlerFactory.newRequestHandler(pageProvider));
	}


}
