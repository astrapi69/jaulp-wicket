package de.alpharogroup.wicket.components.examples.resource.references;
import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.markup.head.HeaderItem;
import org.apache.wicket.resource.JQueryPluginResourceReference;

/**
 * A JavaScript reference that loads the JavaScript resources needed by alerts 
 * UI components.
 */
public class AlertJsReference extends JQueryPluginResourceReference {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final AlertJsReference INSTANCE = new AlertJsReference();

	private AlertJsReference() {
		super(AlertJsReference.class, "js/bootstrap_alert.js");
	}

	@Override
	public List<HeaderItem> getDependencies() {
		List<HeaderItem> deps = new ArrayList<HeaderItem>();
		for (HeaderItem dep : super.getDependencies()) {
			deps.add(dep);
		}
		return deps;
	}
}