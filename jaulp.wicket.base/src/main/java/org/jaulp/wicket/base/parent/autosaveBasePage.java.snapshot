package org.jaulp.wicket.base.parent;

import java.util.Set;

import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.CssReferenceHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.markup.head.JavaScriptReferenceHeaderItem;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.StringResourceModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.request.resource.CssResourceReference;
import org.apache.wicket.request.resource.JavaScriptResourceReference;
import org.jaulp.wicket.PackageResourceReferenceWrapper;
import org.jaulp.wicket.PackageResourceReferences;
import org.jaulp.wicket.base.enums.ResourceReferenceType;

/**
 * The Class BasePage.
 * 
 * @author Asterios Raptis
 */
public abstract class BaseMainPage extends org.jaulp.wicket.base.BasePage {

	/**
	 * The serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;

	/** The title. */
	private IModel<String> title;

	/**
	 * Instantiates a new base page.
	 */
	public BaseMainPage() {
		this(new PageParameters());
	}

	/**
	 * Instantiates a new base page.
	 * 
	 * @param parameters
	 *            the parameters
	 */
	public BaseMainPage(final PageParameters parameters) {
		super(parameters);
		title = new StringResourceModel("page.title", this, null);
		add(new Label("title", title));
	}

}