package org.jaulp.wicket.base.components.labeled.examples;

import java.io.Serializable;

/**
 * The Class Person.
 */
public class Person implements Serializable {

    /**
	 * The serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;

	/** The name. */
    String name;

    /** The gender. */
    String gender;
    
    String about;
    
    Boolean married;

    public Boolean getMarried() {
		return married;
	}

	public void setMarried(Boolean married) {
		this.married = married;
	}

	public String getAbout() {
		return about;
	}

	public void setAbout(String about) {
		this.about = about;
	}

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
		return "Person [name=" + name + ", gender=" + gender + ", about:"+about+" Married:"+married+"]";
	}

}