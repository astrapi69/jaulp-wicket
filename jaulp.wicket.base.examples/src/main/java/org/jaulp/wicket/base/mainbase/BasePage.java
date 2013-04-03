package org.jaulp.wicket.base.mainbase;

import java.util.Set;

import net.sourceforge.jaulp.io.annotations.ImportResource;
import net.sourceforge.jaulp.io.annotations.ImportResources;

import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.CssReferenceHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.markup.head.JavaScriptReferenceHeaderItem;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.request.resource.CssResourceReference;
import org.apache.wicket.request.resource.JavaScriptResourceReference;
import org.jaulp.wicket.PackageResourceReferenceWrapper;
import org.jaulp.wicket.PackageResourceReferences;
import org.jaulp.wicket.base.enums.ResourceReferenceType;
import org.jaulp.wicket.base.parent.BaseMainPage;

/**
 * The Class BasePage.
 * 
 * @author Asterios Raptis
 */
@ImportResources(resources = {
		@ImportResource(resourceName = "BasePage.js", resourceType = "js"),
		@ImportResource(resourceName = "BasePage.css", resourceType = "css") })
public abstract class BasePage extends BaseMainPage {

	/**
	 * The serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;


	/**
	 * Instantiates a new base page.
	 */
	public BasePage() {
		super();
	}

	/**
	 * Instantiates a new base page.
	 * 
	 * @param parameters
	 *            the parameters
	 */
	public BasePage(final PageParameters parameters) {
		super(parameters);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void renderHead(IHeaderResponse response) {
		Set<PackageResourceReferenceWrapper> headerContributors = PackageResourceReferences
				.getInstance().getPackageResourceReference(BasePage.class);
		if (null != headerContributors && !headerContributors.isEmpty()) {
			for (final PackageResourceReferenceWrapper packageResourceReference : headerContributors) {
				if (packageResourceReference.getType().equals(
						ResourceReferenceType.JS)) {
					JavaScriptResourceReference reference = new JavaScriptResourceReference(
							BasePage.class, packageResourceReference
									.getPackageResourceReference().getName());
					if (!response.wasRendered(reference)) {
						JavaScriptReferenceHeaderItem headerItem = JavaScriptHeaderItem
								.forReference(reference);
						response.render(headerItem);
					}
				}
				if (packageResourceReference.getType().equals(
						ResourceReferenceType.CSS)) {
					CssResourceReference reference = new CssResourceReference(
							BasePage.class, packageResourceReference
									.getPackageResourceReference().getName());
					if (!response.wasRendered(reference)) {
						CssReferenceHeaderItem headerItem = CssHeaderItem
								.forReference(reference);
						response.render(headerItem);
					}
				}
			}
		}
	}

}