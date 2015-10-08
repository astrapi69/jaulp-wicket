package de.alpharogroup.wicket.base.util;

import org.apache.wicket.core.request.handler.PageProvider;
import org.apache.wicket.core.request.handler.RenderPageRequestHandler;
import org.apache.wicket.request.IRequestHandler;
import org.apache.wicket.request.cycle.RequestCycle;

/**
 * A factory for creating RequestHandler objects.
 */
public class RequestHandlerFactory {
		
	/**
	 * New request handler.
	 *
	 * @param pageProvider the page provider
	 * @return the i request handler
	 */
	public static  IRequestHandler newRequestHandler(PageProvider pageProvider){
		RenderPageRequestHandler renderPageRequestHandler = new RenderPageRequestHandler(pageProvider);
		return renderPageRequestHandler;
	}
	
	/**
	 * Schedule.
	 *
	 * @param requestHandler the request handler
	 */
	public static void schedule(IRequestHandler requestHandler) {
		RequestCycle.get().scheduleRequestHandlerAfterCurrent(requestHandler);
	}
	
}
