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
package de.alpharogroup.wicket.behaviors.events;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AbstractAjaxTimerBehavior;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.event.Broadcast;
import org.apache.wicket.util.time.Duration;

/**
 * The class {@link RefreshComponentAjaxTimerBehavior} can refresh components at a given regular
 * interval. Components that wants to be notified have to implement the
 * {@link Component#onEvent(org.apache.wicket.event.IEvent)} method and see if the payload is of
 * Type {@link RefreshPayload} and refresh them selves.
 */
public class RefreshComponentAjaxTimerBehavior extends AbstractAjaxTimerBehavior
{

	/** The constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new {@link RefreshComponentAjaxTimerBehavior} behavior.
	 *
	 * @param updateInterval
	 *            the update interval
	 */
	public RefreshComponentAjaxTimerBehavior(Duration updateInterval)
	{
		super(updateInterval);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void onTimer(AjaxRequestTarget target)
	{
		getComponent().send(getComponent().getPage(), Broadcast.DEPTH,
			new RefreshPayload<Void>(target));
	}

}
