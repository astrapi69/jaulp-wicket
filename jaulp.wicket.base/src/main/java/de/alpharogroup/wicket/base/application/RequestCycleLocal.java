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

import org.apache.wicket.MetaDataEntry;
import org.apache.wicket.MetaDataKey;
import org.apache.wicket.request.cycle.RequestCycle;
import org.apache.wicket.util.lang.Args;

/**
 * Can store an object with the given key into the {@link RequestCycle}. This class is like a
 * ThreadLocal but takes the {@link RequestCycle} instead of a local thread as context. When the
 * {@link Request} is finished than the
 * {@link de.alpharogroup.wicket.base.application.RequestCycleLocal} will be destroyed.
 */
public class RequestCycleLocal<T extends Serializable>
{

	/**
	 * The {@link org.apache.wicket.MetaDataKey} that is used to store an object.
	 */
	private final MetaDataKey<T> key;

	/**
	 * Constructor that takes a {@link org.apache.wicket.MetaDataKey} that is used to store an
	 * object.
	 * 
	 * @param key
	 *            The {@link org.apache.wicket.MetaDataKey} that is used to store an object.
	 */
	public RequestCycleLocal(final MetaDataKey<T> key)
	{
		this.key = Args.notNull(key, "key");
	}

	/**
	 * Sets the object to null in the {@link MetaDataEntry}.
	 */
	public void clear()
	{
		getRequestCycle().setMetaData(key, null);
	}

	/**
	 * Gets the object from the {@link MetaDataEntry}.
	 * 
	 * @return the object from the {@link MetaDataEntry}.
	 */
	public T get()
	{
		return getRequestCycle().getMetaData(key);
	}

	/**
	 * Gets the wicket session.
	 * 
	 * @return the wicket session.
	 */
	private RequestCycle getRequestCycle()
	{
		return RequestCycle.get();
	}

	/**
	 * Sets the given object in the {@link MetaDataEntry}.
	 * 
	 * @param value
	 *            The object to set.
	 */
	public void set(final T value)
	{
		getRequestCycle().setMetaData(key, value);
	}
}
