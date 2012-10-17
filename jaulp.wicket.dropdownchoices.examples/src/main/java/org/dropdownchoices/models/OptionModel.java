package org.dropdownchoices.models;

import org.apache.wicket.util.io.IClusterable;

public class OptionModel implements IClusterable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;

    private String value;

    public String getValue() {
        return value;
    }

    public void setValue( final String value ) {
        this.value = value;
    }
}