/**
 * Copyright (C) 2007 Asterios Raptis
 *
 * This program is open source software; you can redistribute it and/or modify
 * it under the terms of the Apache License V2.0 as published by
 * the Apache Foundation.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY.
 */
package org.jaulp.wicket.model.dropdownchoices;
import java.util.List;
import java.util.Map;

/**
 * The Class StringTwoDropDownChoicesModel.
 *
 * @author Asterios Raptis
 */
public class StringTwoDropDownChoicesModel extends
        TwoDropDownChoicesModel< String > {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /**
     * Instantiates a new string two drop down choices model.
     *
     * @param selectedTrademark the selected trademark
     * @param modelsMap the models map
     */
    public StringTwoDropDownChoicesModel( final String selectedTrademark,
            final Map< String, List< String >> modelsMap ) {
        super( selectedTrademark, modelsMap );
    }

}