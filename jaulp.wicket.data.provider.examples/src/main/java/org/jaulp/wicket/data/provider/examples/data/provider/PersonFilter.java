package org.jaulp.wicket.data.provider.examples.data.provider;

import org.apache.wicket.util.io.IClusterable;

public class PersonFilter implements IClusterable {

    /**
     * The serialVersionUID.
     */
    private static final long serialVersionUID = 1L;

	private String firstname;

	private String lastname;


	public PersonFilter() {
		super();
	}

	public String getFirstname() {
		return firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}


}
