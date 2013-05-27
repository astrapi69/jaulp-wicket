package org.jaulp.wicket.base.components.labeled.examples;

import java.util.Date;

import org.jaulp.test.objects.Person;

public class Member extends Person {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Date dateofbirth;
	public Date getDateofbirth() {
		return dateofbirth;
	}
	public void setDateofbirth(Date dateofbirth) {
		this.dateofbirth = dateofbirth;
	}
}
