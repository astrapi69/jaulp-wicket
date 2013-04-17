package org.jaulp.wicket.components.actions;

import org.apache.wicket.util.io.IClusterable;



/**
 * The Interface Action implements the command Pattern.
 * 
 * @author Asterios Raptis
 */
public interface Action extends IClusterable {

	/**
	 * Execute.
	 */
	void execute();
}