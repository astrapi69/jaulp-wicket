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

import org.apache.wicket.ajax.AjaxRequestTarget;

/**
 * The class {@link RefreshPayload} is data bean for use with the wicket event bus system.
 *
 * @param <T>
 *            the generic type
 */
public class RefreshPayload<T> extends PayloadBean<T>
{

	/**
	 * Instantiates a new {@link RefreshPayload} payload.
	 *
	 * @param target the target
	 */
	public RefreshPayload(AjaxRequestTarget target)
	{
		this(null, target);
	}

	/**
	 * Instantiates a new {@link RefreshPayload} payload.
	 *
	 * @param payload the payload
	 * @param target the target
	 */
	public RefreshPayload(T payload, AjaxRequestTarget target)
	{
		super(payload, target);
	}

}
  
