package org.jaulp.wicket.behaviors;
import java.util.HashMap;
import java.util.Map;

import org.apache.wicket.Component;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.resource.ResourceReference;
import org.apache.wicket.resource.TextTemplateResourceReference;
import org.apache.wicket.util.lang.Args;
import org.jaulp.wicket.base.utils.WicketUrlUtils;


public class AddJsResourceReferenceBehavior extends Behavior {
	private static final long serialVersionUID = 1L;
	
	private final Class<? extends WebPage> pageClass;
	
	private final String filename;
	
	/**
	 * The unique id for the javascript element. This can be null, however in
	 * that case the ajax header contribution can't detect duplicate script
	 * fragments.
	 */
	private String id;

	public AddJsResourceReferenceBehavior(final Class<? extends WebPage> pageClass, final String filename, final String id) {
		Args.notNull(filename, "filename");
		Args.notNull(pageClass, "pageClass");
		this.pageClass = pageClass;
		this.filename = filename;
		this.id = id;
	}

	private ResourceReference getResourceReference() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("url", WicketUrlUtils.getUrlAsString(pageClass));
		ResourceReference resourceReference = new TextTemplateResourceReference(pageClass,
				this.filename, Model.ofMap(map));
		return resourceReference;
	}

	@Override
	public void renderHead(Component component, IHeaderResponse response) {
		super.renderHead(component, response);
		response.render(JavaScriptHeaderItem.forReference(
				getResourceReference(), this.id));	
	}

}