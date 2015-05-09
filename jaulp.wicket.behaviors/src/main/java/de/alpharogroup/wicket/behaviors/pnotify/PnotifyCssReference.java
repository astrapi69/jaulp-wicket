package de.alpharogroup.wicket.behaviors.pnotify;

import org.apache.wicket.request.resource.CssResourceReference;

public class PnotifyCssReference extends CssResourceReference
{
	private static final long serialVersionUID = 1L;
	public static final PnotifyCssReference INSTANCE = new PnotifyCssReference();

	private PnotifyCssReference()
	{
		super(PnotifyCssReference.class, "pnotify.custom.min.css");
	}
}
