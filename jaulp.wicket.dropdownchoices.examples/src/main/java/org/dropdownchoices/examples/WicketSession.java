package org.dropdownchoices.examples;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.wicket.protocol.http.WebSession;
import org.apache.wicket.request.Request;

public class WicketSession extends WebSession {


    /**
     * The serialVersionUID.
     */
    private static final long serialVersionUID = 1L;

    /** The user attributes. */
    private Map< String, Object > userAttributes = new LinkedHashMap< String, Object >();


	public WicketSession(Request request) {
		super(request);
	}

    /**
     * Gets the user attribute.
     *
     * @param key the key
     * @return the user attribute
     */
    public Object getUserAttribute( final String key ) {
        return this.userAttributes.get( key );
    }


    /**
     * Sets the user attribute.
     *
     * @param key the key
     * @param value the value
     */
    public void setUserAttribute( final String key, final Object value ) {
        this.userAttributes.put( key, value );
    }


}
