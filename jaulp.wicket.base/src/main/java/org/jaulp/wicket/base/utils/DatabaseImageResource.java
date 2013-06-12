package org.jaulp.wicket.base.utils;

import org.apache.wicket.request.resource.ByteArrayResource;

/**
 * The Class DatabaseImageResource.
 * 
 * @author Asterios Raptis
 */
public class DatabaseImageResource extends ByteArrayResource {

	/**
	 * The serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;

	/** The content type. */
	private String contentType;

	/** The data. */
	private byte[] data;

	/**
	 * Instantiates a new database image resource.
	 * 
	 * @param contentType
	 *            the content type
	 * @param data
	 *            the data
	 */
	public DatabaseImageResource(String contentType, byte[] data) {
		super(contentType, data);
		this.contentType = contentType;
		this.data = data;
	}

	/**
	 * Gets the content type.
	 * 
	 * @return the content type
	 */
	public String getContentType() {
		return contentType;
	}

	/**
	 * Gets the data.
	 * 
	 * @return the data
	 */
	public byte[] getData() {
		return data;
	}

}
