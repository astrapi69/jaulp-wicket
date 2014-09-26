package org.jaulp.wicket.base.examples.sitemap;
import java.util.List;

import org.apache.wicket.markup.html.WebPage;
import org.jaulp.wicket.base.pages.AbstractSiteMapPage;
import org.wicketstuff.annotation.mount.MountPath;


/**
 * The Class SiteMapPage generates a site map for all pages in this WebApplication.
 */
@MountPath("/sitemap.xml")
public class SiteMapPage extends AbstractSiteMapPage {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

    /**
     * {@inheritDoc}
     */
	protected List<? extends Class<? extends WebPage>> getAllPageClasses() {
		return getAllPageClassesQuietly();
	}

    /**
     * {@inheritDoc}
     */
	protected String getPackageName() {
		return "org.jaulp.wicket.base";
	}

}