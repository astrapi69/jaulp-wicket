package org.jaulp.wicket.behaviors.toastr;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.HeaderItem;
import org.apache.wicket.request.resource.CssResourceReference;
import org.apache.wicket.request.resource.JavaScriptResourceReference;

/**
 * The Class ToastrResourceReference. There is a map file 'toastr.js.map' so add to the guard the
 * suffix pattern('+*.js.map') to accept the file.
 */
public class ToastrResourceReference extends JavaScriptResourceReference
{

	/** The serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new toastr resource reference.
	 */
	public ToastrResourceReference()
	{
		super(ToastrResourceReference.class, "toastr.min.js");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Iterable<? extends HeaderItem> getDependencies()
	{
		List<HeaderItem> dependencies = new ArrayList<HeaderItem>();
		dependencies.add(CssHeaderItem.forReference(new CssResourceReference(
			ToastrResourceReference.class, "toastr.min.css")));
		return dependencies;
	}

}
