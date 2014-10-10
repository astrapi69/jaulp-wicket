package de.alpharogroup.wicket.components.sign.in.password.forgotten;

import java.io.Serializable;

public class PasswordForgottenModel implements Serializable {

	/**
	 * The serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;

	/** The email. */
	private String email;

	public PasswordForgottenModel() {
		super();
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
