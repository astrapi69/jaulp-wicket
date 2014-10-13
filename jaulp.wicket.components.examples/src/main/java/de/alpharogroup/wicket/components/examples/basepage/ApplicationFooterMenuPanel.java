package de.alpharogroup.wicket.components.examples.basepage;

import net.sourceforge.jaulp.locale.ResourceBundleKey;

import org.apache.wicket.Component;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.link.ExternalLink;

import de.alpharogroup.wicket.components.footer.FooterMenuPanel;
import de.alpharogroup.wicket.components.link.LinkUtils;
import de.alpharogroup.wicket.components.examples.imprint.ImprintPage;
import de.alpharogroup.wicket.components.examples.termofuse.TermOfUsePage;

public class ApplicationFooterMenuPanel extends FooterMenuPanel {

	private static final long serialVersionUID = 1L;
	/** The Constant COPYRIGHT_URL. */
	protected static final String COPYRIGHT_URL = "http://www.alpharogroup.de/";
	
	public ApplicationFooterMenuPanel(String id) {
		super(id);
	}

	protected Component newTermOfUseLink(String id) {
		BookmarkablePageLink<String> link = LinkUtils.newBookmarkablePageLink(
				id, getTermOfUsePageClass(), "termOfUse", ResourceBundleKey
						.builder().key("main.global.menu.term.of.use.label")
						.defaultValue("AGBs").build(), this);
		return link;
	}

	protected Component newImprintLink(String id) {
		BookmarkablePageLink<String> link = LinkUtils.newBookmarkablePageLink(
				id, getImprintPageClass(), "imprint", ResourceBundleKey
						.builder().key("main.global.menu.masthead.label")
						.defaultValue("Imprint").build(), this);
		return link;
	}

	protected Component newCopyrightLink(String id) {
		ExternalLink link = LinkUtils
				.newExternalLink(
						id,
						getCopyrightUrl(),
						"copyright",
						ResourceBundleKey.builder()
								.key("main.footer.copyright.label")
								.defaultValue(getCopyrightDefaultValue())
								.build(), this);
		return link;
	}

	@Override
	protected String getCopyrightDefaultValue()	{
	char crChar = '\u0040';
	String copyrightDefaultValue = crChar
			+ " copyright 2012 Design by Alpha Ro Group";
	return copyrightDefaultValue;
}

	protected Class<? extends WebPage> getImprintPageClass() {
		return ImprintPage.class;
	}

	protected Class<? extends WebPage> getTermOfUsePageClass() {
		return TermOfUsePage.class;
	}

	@Override
	protected String getCopyrightUrl() {
		return COPYRIGHT_URL;
	}
}
