package org.jaulp.wicket.base.utils;

import org.apache.commons.lang.ArrayUtils;
import org.apache.wicket.markup.html.image.Image;
import org.apache.wicket.markup.html.image.NonCachingImage;

/**
 * The Class WicketImageUtils is helper class for create wicket Image objects.
 */
public class WicketImageUtils {
	
    /**
     * Gets the image.
     * 
     * @param wicketId the id from the image for the html template.
     * @param contentType the content type
     * @param data the data
     * @return the image
     */
    public static Image getImage( final String wicketId,
            final String contentType, final byte [] data ) {
        return new Image( wicketId,
                new DatabaseImageResource( contentType, data ) );
    }

	
    /**
     * Gets the image.
     * 
     * @param wicketId the id from the image for the html template.
     * @param contentType the content type
     * @param data the data
     * @return the image
     */
    public static Image getImage( final String wicketId,
            final String contentType, final Byte [] data ) {
    	byte [] byteArrayData =ArrayUtils.toPrimitive( data );
        return getImage(wicketId, contentType,  byteArrayData);
    }

	
    /**
     * Gets a non caching image from the given wicketId, contentType and the byte array data.
     * 
     * @param wicketId the id from the image for the html template.
     * @param contentType the content type of the image.
     * @param data the data for the image as an byte array.
     * @return the non caching image
     */
    public static NonCachingImage getNonCachingImage( final String wicketId,
            final String contentType, final byte [] data ) {
        return new NonCachingImage( wicketId,
                new DatabaseImageResource( contentType, data ) );
    }
    

    /**
     * Gets a non caching image from the given wicketId, contentType and the Byte array data.
     * 
     * @param wicketId the id from the image for the html template.
     * @param contentType the content type of the image.
     * @param data the data for the image as an Byte array.
     * @return the non caching image
     */
    public static NonCachingImage getNonCachingImage( final String wicketId,
            final String contentType, final Byte [] data ) {
    	byte [] byteArrayData =ArrayUtils.toPrimitive( data );
        return getNonCachingImage(wicketId, contentType, byteArrayData);
    }
}
