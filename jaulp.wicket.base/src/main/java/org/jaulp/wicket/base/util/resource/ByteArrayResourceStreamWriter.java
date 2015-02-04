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
package org.jaulp.wicket.base.util.resource;

import java.io.IOException;
import java.io.OutputStream;

import org.apache.wicket.util.resource.AbstractResourceStreamWriter;

/**
 * The Class ByteArrayResourceStreamWriter.
 */
public abstract class ByteArrayResourceStreamWriter extends AbstractResourceStreamWriter
{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The content. */
	private byte[] content;

	/**
	 * Override this method to return the mime type of the content.
	 * 
	 * @return the content type
	 * @see org.apache.wicket.util.resource.AbstractResourceStreamWriter#getContentType()
	 */
	@Override
	public abstract String getContentType();

	/**
	 * Initialize.
	 *
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	private void initialize() throws IOException
	{
		if (content == null)
		{
			content = load();
		}
	}

	/**
	 * Load the file content as a byte array.
	 *
	 * @return the byte[]
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	protected abstract byte[] load() throws IOException;

	/**
	 * Writes the byte array to the OutputStream from the client.
	 *
	 * @param output
	 *            the output
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 * @see org.apache.wicket.util.resource.IResourceStreamWriter#write(java.io.OutputStream)
	 */
	@Override
	public void write(OutputStream output) throws IOException
	{
		initialize();
		if (content == null)
		{
			content = new byte[0];
		}
		output.write(content, 0, content.length);
		output.flush();
	}

}
