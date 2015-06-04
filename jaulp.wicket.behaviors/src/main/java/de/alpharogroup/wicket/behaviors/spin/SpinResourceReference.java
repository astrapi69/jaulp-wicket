package de.alpharogroup.wicket.behaviors.spin;

import org.apache.wicket.request.resource.JavaScriptResourceReference;

/**
 * The Class SpinResourceReference holds the references(js) for the spin js library.
 */
public class SpinResourceReference extends JavaScriptResourceReference
{

	/** The serialVersionUID. */
	private static final long serialVersionUID = 1L;

	private static final SpinResourceReference INSTANCE = new SpinResourceReference();

	private SpinResourceReference()
	{
		super(SpinResourceReference.class, "spin.min.js");
	}

	public static SpinResourceReference get()
	{
		return INSTANCE;
	}
}
