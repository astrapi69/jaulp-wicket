package de.alpharogroup.wicket.components.examples.fragment.swapping.person;

import java.io.Serializable;

public class PersonModel implements Serializable {

	private static final long serialVersionUID = 1L;

	private String firstName;
	
	private String lastName;
	
	private String gender;
	
	private String age;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

}
