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
package org.jaulp.wicket.data.provider;

/**
 * The Class Person.
 */
public class Person {

    /** The name. */
    String name;

    /** The gender. */
    String gender;

    /**
     * Instantiates a new person.
     */
    public Person() {
        super();
    }

    /**
     * Gets the gender.
     *
     * @return the gender
     */
    public String getGender() {

        return gender;
    }

    /**
     * Gets the name.
     *
     * @return the name
     */
    public String getName() {

        return name;
    }

    /**
     * Sets the gender.
     *
     * @param gender the new gender
     */
    public void setGender( String gender ) {
        this.gender = gender;
    }

    /**
     * Sets the name.
     *
     * @param name the new name
     */
    public void setName( String name ) {
        this.name = name;
    }

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Person [name=" + name + ", gender=" + gender + "]";
	}

}