package de.alpharogroup.wicket.components.deregistration;

import org.apache.wicket.util.io.IClusterable;

/**
 * The Class DeregistrationModel.
 * 
 * @author Asterios Raptis
 */
public class DeregistrationModel implements IClusterable {

	/**
	 * The serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;

	/** The motivation. */
	private String motivation;

	/**
	 * Gets the motivation.
	 * 
	 * @return the motivation
	 */
	public String getMotivation() {
		return motivation;
	}

	/**
	 * Sets the motivation.
	 * 
	 * @param motivation
	 *            the new motivation
	 */
	public void setMotivation(final String motivation) {
		this.motivation = motivation;
	}

}
