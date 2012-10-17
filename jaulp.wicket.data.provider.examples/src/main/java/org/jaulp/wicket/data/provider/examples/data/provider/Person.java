package org.jaulp.wicket.data.provider.examples.data.provider;

import java.io.Serializable;

public class Person implements Serializable {


    public Person(String firstname, String lastname, String dateOfBirth) {
		super();
		this.firstname = firstname;
		this.lastname = lastname;
		this.dateOfBirth = dateOfBirth;
	}

	/**
     * The serialVersionUID.
     */
    private static final long serialVersionUID = 1L;

	private String firstname;

	private String lastname;

	private String dateOfBirth;

	public Person() {
		super();
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public String getFirstname() {
		return firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

}
