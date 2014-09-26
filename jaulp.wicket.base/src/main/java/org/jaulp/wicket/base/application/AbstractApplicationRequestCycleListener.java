package org.jaulp.wicket.base.application;

import java.io.Serializable;

import org.apache.wicket.core.request.handler.PageProvider;
import org.apache.wicket.core.request.handler.RenderPageRequestHandler;
import org.apache.wicket.request.IRequestHandler;
import org.apache.wicket.request.component.IRequestablePage;
import org.apache.wicket.request.cycle.AbstractRequestCycleListener;
import org.apache.wicket.request.cycle.RequestCycle;

/**
 * Extends the {@link AbstractApplicationRequestCycleListener} and implement the
 * method
 * {@link AbstractApplicationRequestCycleListener#getExceptionPage(Exception)}
 * that return an application specific exception page.
 */
public abstract class AbstractApplicationRequestCycleListener extends
		AbstractRequestCycleListener implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.apache.wicket.request.cycle.AbstractRequestCycleListener#onException
	 * (org.apache.wicket.request.cycle.RequestCycle, java.lang.Exception)
	 */
	@Override
	public IRequestHandler onException(RequestCycle cycle, Exception e) {
		return new RenderPageRequestHandler(new PageProvider(
				getExceptionPage(e)));
	}

	/**
	 * Gets the application specific exception page.
	 *
	 * @param e
	 *            the e
	 * @return the exception page
	 */
	public abstract IRequestablePage getExceptionPage(Exception e);

}