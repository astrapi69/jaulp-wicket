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
package de.alpharogroup.wicket.base.application;

import java.io.Serializable;

import org.apache.wicket.core.request.handler.PageProvider;
import org.apache.wicket.core.request.handler.RenderPageRequestHandler;
import org.apache.wicket.request.IRequestHandler;
import org.apache.wicket.request.component.IRequestablePage;
import org.apache.wicket.request.cycle.AbstractRequestCycleListener;
import org.apache.wicket.request.cycle.RequestCycle;

/**
 * Extends the {@link AbstractApplicationRequestCycleListener} and implement the method
 * {@link AbstractApplicationRequestCycleListener#getExceptionPage(Exception)} that return an
 * application specific exception page.
 */
public abstract class AbstractApplicationRequestCycleListener extends AbstractRequestCycleListener
	implements
		Serializable
{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public IRequestHandler onException(final RequestCycle cycle, final Exception e)
	{
		return new RenderPageRequestHandler(new PageProvider(getExceptionPage(e)));
	}

	/**
	 * Gets the application specific exception page.
	 *
	 * @param e
	 *            the e
	 * @return the exception page
	 */
	public abstract IRequestablePage getExceptionPage(final Exception e);

}