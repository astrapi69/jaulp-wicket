package org.jaulp.wicket.base.util.resource;

import java.io.IOException;
import java.io.OutputStream;

import org.apache.wicket.util.resource.AbstractResourceStreamWriter;

/**
 * The Class ByteArrayResourceStreamWriter.
 */
public abstract class ByteArrayResourceStreamWriter extends AbstractResourceStreamWriter {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The content. */
	private byte [] content;
	
	/**
	 * Override this method to return the mime type of the content.
	 * @see org.apache.wicket.util.resource.AbstractResourceStreamWriter#getContentType()
	 */
	public abstract String getContentType();
	
	/**
	 * Initialize.
	 */
	private void initialize() throws IOException{
		if(content == null){
			content = load();
		}
	}
	
	/**
	 * Load the file content as a byte array.
	 *
	 * @return the byte[]
	 */
	protected abstract byte[] load() throws IOException;
	
	/**
	 * Writes the byte array to the OutputStream from the client.
	 * @see org.apache.wicket.util.resource.IResourceStreamWriter#write(java.io.OutputStream)
	 */
	@Override
	public void write(OutputStream output) throws IOException {
		initialize();
		if(content == null) {
			content = new byte[0];
		}
		output.write(content, 0, content.length);
		output.flush();
	}

}
