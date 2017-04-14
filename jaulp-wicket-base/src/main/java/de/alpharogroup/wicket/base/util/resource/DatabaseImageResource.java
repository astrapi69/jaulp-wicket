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
package de.alpharogroup.wicket.base.util.resource;

import org.apache.wicket.request.resource.ByteArrayResource;

import lombok.Getter;

/**
 * The Class DatabaseImageResource.
 *
 * @author Asterios Raptis
 */
public class DatabaseImageResource extends ByteArrayResource
{

	/**
	 * The serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;

	/** The content type. */
	@Getter
	private final String contentType;

	/**
	 * Instantiates a new database image resource.
	 *
	 * @param contentType
	 *            the content type
	 * @param data
	 *            the data
	 */
	public DatabaseImageResource(final String contentType, final byte[] data)
	{
		super(contentType, data);
		this.contentType = contentType;
	}

	/**
	 * Gets the data.
	 *
	 * @return the data
	 */
	public byte[] getData()
	{
		return getData(null);
	}

}
