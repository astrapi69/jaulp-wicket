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
package org.jaulp.wicket.base.util;

import org.apache.commons.lang.ArrayUtils;
import org.apache.wicket.markup.html.image.Image;
import org.apache.wicket.markup.html.image.NonCachingImage;
import org.jaulp.wicket.base.util.resource.DatabaseImageResource;

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
