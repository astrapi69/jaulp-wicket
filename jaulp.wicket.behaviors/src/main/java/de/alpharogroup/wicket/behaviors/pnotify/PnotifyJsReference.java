package de.alpharogroup.wicket.behaviors.pnotify;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.HeaderItem;
import org.apache.wicket.resource.JQueryPluginResourceReference;

/**
 * A JavaScript reference that loads the JavaScript resources needed by JQuery UI components.
 */
public class PnotifyJsReference extends JQueryPluginResourceReference
{
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	public static final PnotifyJsReference INSTANCE = new PnotifyJsReference();

	private PnotifyJsReference()
	{
		super(PnotifyJsReference.class, "pnotify.custom.min.js");
	}

	@Override
	public List<HeaderItem> getDependencies()
	{
		List<HeaderItem> deps = new ArrayList<HeaderItem>();
		for (HeaderItem dep : super.getDependencies())
		{
			deps.add(dep);
		}
		deps.add(CssHeaderItem.forReference(PnotifyCssReference.INSTANCE));
		return deps;
	}
}
