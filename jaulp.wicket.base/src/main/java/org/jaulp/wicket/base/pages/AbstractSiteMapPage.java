package org.jaulp.wicket.base.pages;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

import net.sourceforge.jaulp.lang.AnnotationUtils;

import org.apache.log4j.Logger;
import org.apache.wicket.markup.MarkupType;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.PropertyListView;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.jaulp.wicket.base.util.WicketUrlUtils;
import org.wicketstuff.annotation.mount.MountPath;

/**
 * The Class AbstractSiteMapPage is the base class for subclass a SiteMapPage.
 */
public abstract class AbstractSiteMapPage extends WebPage
{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	/** The Constant logger. */
	private static final Logger LOGGER = Logger
			.getLogger(AbstractSiteMapPage.class.getName());

	/** The Constant PATTERN. */
	private static final Pattern PATTERN = Pattern.compile("^\\./");
	
	/**
	 * Gets the all page classes.
	 *
	 * @return the all page classes
	 */
	protected abstract List<? extends Class<? extends WebPage>> getAllPageClasses();

	
	/**
	 * Gets the package name where to search for page classes.
	 *
	 * @return the package name
	 */
	protected abstract String getPackageName();

	/**
	 * Gets the all page classes quietly.
	 *
	 * @return the all page classes quietly
	 */
	@SuppressWarnings("unchecked")
	protected List<? extends Class<? extends WebPage>> getAllPageClassesQuietly() {
		List<Class<? extends WebPage>> pages = new ArrayList<>();		
		try {
			Set<Class<?>> set = AnnotationUtils.getAllAnnotatedClasses(
					getPackageName(), MountPath.class);
			for (Class<?> class1 : set) {
					pages.add((Class<? extends WebPage>)class1);				
			}
		} catch (ClassCastException e) {
			LOGGER.error(e.getClass().getName()+" occured while scanning for MountPath annotations.", e);
		} catch (ClassNotFoundException e) {
			LOGGER.error(e.getClass().getName()+" occured while scanning for MountPath annotations.", e);
		} catch (IOException e) {
			LOGGER.error(e.getClass().getName()+" occured while scanning for MountPath annotations.", e);
		}
		return pages;
	}
	
	/**
	 * Gets the base url.
	 *
	 * @return the base url
	 */
	protected String getBaseUrl() {
		return WicketUrlUtils.getDomainUrl(false);
	}

	/**
	 * Instantiates a new abstract site map page.
	 */
	public AbstractSiteMapPage()
	{
		this.add(new PropertyListView<SiteUrl>("urls", this.newListModel()) {

			private static final long serialVersionUID = 1L;

			@Override
			protected void populateItem(ListItem<SiteUrl> item)
			{
				item.add(new Label("loc"));
				item.add(new Label("lastmod", "2014-05-08"));
			}

		});
	}

	/**
	 * New list model.
	 *
	 * @return the i model
	 */
	private IModel<List<SiteUrl>> newListModel()
	{
		return new LoadableDetachableModel<List<SiteUrl>>() {

			private static final long serialVersionUID = 1L;

			@Override
			protected List<SiteUrl> load()
			{
				final List<SiteUrl> list = new ArrayList<SiteUrl>();

				for (Class<? extends WebPage> type : getAllPageClasses())
				{
					String loc = PATTERN.matcher(AbstractSiteMapPage.this.urlFor(type, null)).replaceFirst(getBaseUrl());

					if (loc.endsWith("/."))
					{
						loc = loc.replace("/.", "");
					}

					list.add(new SiteUrl(loc));
				}

				return list;
			}
		};
	}

    /**
     * {@inheritDoc}
     */
	@Override
	public MarkupType getMarkupType()
	{
		return new MarkupType("html", "text/xml");
	}

	/**
	 * The Class SiteUrl.
	 */
	class SiteUrl
	{
		
		/** The loc. */
		private String loc;

		/**
		 * Instantiates a new site url.
		 *
		 * @param loc the loc
		 */
		public SiteUrl(String loc)
		{
			this.loc = loc;
		}

		/**
		 * Gets the loc.
		 *
		 * @return the loc
		 */
		public String getLoc()
		{
			return this.loc;
		}
	}
}