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
package de.alpharogroup.wicket.dropdownchoices.examples;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.wicket.protocol.http.WebSession;
import org.apache.wicket.request.Request;

public class WicketSession extends WebSession
{


	/**
	 * The serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;

	/** The user attributes. */
	private Map<String, Object> userAttributes = new LinkedHashMap<String, Object>();


	public WicketSession(final Request request)
	{
		super(request);
	}

	/**
	 * Gets the user attribute.
	 *
	 * @param key
	 *            the key
	 * @return the user attribute
	 */
	public Object getUserAttribute(final String key)
	{
		return this.userAttributes.get(key);
	}


	/**
	 * Sets the user attribute.
	 *
	 * @param key
	 *            the key
	 * @param value
	 *            the value
	 */
	public void setUserAttribute(final String key, final Object value)
	{
		this.userAttributes.put(key, value);
	}


}
