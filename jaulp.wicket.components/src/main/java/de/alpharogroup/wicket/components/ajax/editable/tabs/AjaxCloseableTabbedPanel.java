package de.alpharogroup.wicket.components.ajax.editable.tabs;

import java.util.List;

import lombok.Getter;

import org.apache.wicket.Component;
import org.apache.wicket.WicketRuntimeException;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxFallbackLink;
import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.extensions.markup.html.tabs.ITab;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.Loop;
import org.apache.wicket.markup.html.list.LoopItem;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.util.lang.Args;

/**
 * The Class AjaxCloseableTabbedPanel adds functionality to add or remove tabs from the TabbedPanel.
 *
 * @param <T>
 *            the generic type
 */
public class AjaxCloseableTabbedPanel<T extends ICloseableTab> extends Panel
{
	private static final long serialVersionUID = 1L;

	/** id used for child panels */
	public static final String TAB_PANEL_ID = "panel";

	private final List<T> tabs;

	/** the current tab */
	private int currentTab = -1;

	private transient VisibilityCache visibilityCache;
	@Getter
	private WebMarkupContainer tabsUlContainer;
	@Getter
	private WebMarkupContainer tabsContainer;
	@Getter
	private Loop tabsLoop;

	/**
	 * Constructor
	 * 
	 * @param id
	 *            component id
	 * @param tabs
	 *            list of ITab objects used to represent tabs
	 */
	public AjaxCloseableTabbedPanel(final String id, final List<T> tabs)
	{
		this(id, tabs, null);
	}

	/**
	 * Constructor
	 * 
	 * @param id
	 *            component id
	 * @param tabs
	 *            list of ITab objects used to represent tabs
	 * @param model
	 *            model holding the index of the selected tab
	 */
	public AjaxCloseableTabbedPanel(final String id, final List<T> tabs, IModel<Integer> model)
	{
		super(id, model);
		setOutputMarkupId(true);
		setVersioned(false);
		this.tabs = Args.notNull(tabs, "tabs");

		final IModel<Integer> tabCount = new AbstractReadOnlyModel<Integer>()
		{
			private static final long serialVersionUID = 1L;

			@Override
			public Integer getObject()
			{
				return AjaxCloseableTabbedPanel.this.tabs.size();
			}
		};

		tabsContainer = newTabsContainer("tabs-container");
		add(tabsContainer);

		tabsUlContainer = newTabsContainer("tabs-ul-container");
		tabsContainer.add(tabsUlContainer);
		// add the loop used to generate tab names
		tabsUlContainer.add(tabsLoop = newTabsLoop("tabs", tabCount));

		add(newPanel());
	}

	protected Loop newTabsLoop(String id, final IModel<Integer> model)
	{
		Loop tabsLoop = new Loop(id, model)
		{
			private static final long serialVersionUID = 1L;

			@Override
			protected void populateItem(final LoopItem item)
			{
				final int index = item.getIndex();
				final T tab = AjaxCloseableTabbedPanel.this.tabs.get(index);

				final WebMarkupContainer titleCloseLink = newCloseLink("closeTab", index);

				titleCloseLink.add(newCloseTitle("closeTitle", tab.getCloseTitle(), index));
				item.add(titleCloseLink);

				final WebMarkupContainer titleLink = newLink("link", index);

				titleLink.add(newTitle("title", tab.getTitle(), index));
				item.add(titleLink);
				item.add(new AttributeAppender("class", " label"));

			}

			@Override
			protected LoopItem newItem(final int iteration)
			{
				return newTabContainer(iteration);
			}
		};
		return tabsLoop;
	}

	/**
	 * Override of the default initModel behaviour. This component <strong>will not</strong> use any
	 * compound model of a parent.
	 * 
	 * @see org.apache.wicket.Component#initModel()
	 */
	@Override
	protected IModel<?> initModel()
	{
		return new Model<Integer>(-1);
	}

	/**
	 * Generates the container for all tabs. The default container automatically adds the css
	 * <code>class</code> attribute based on the return value of {@link #getTabContainerCssClass()}
	 * 
	 * @param id
	 *            container id
	 * @return container
	 */
	protected WebMarkupContainer newTabsContainer(final String id)
	{
		return new WebMarkupContainer(id)
		{
			private static final long serialVersionUID = 1L;

			@Override
			protected void onComponentTag(final ComponentTag tag)
			{
				super.onComponentTag(tag);
				tag.put("class", getTabContainerCssClass());
			}
		};
	}

	/**
	 * Generates a loop item used to represent a specific tab's <code>li</code> element.
	 * 
	 * @param tabIndex
	 * @return new loop item
	 */
	protected LoopItem newTabContainer(final int tabIndex)
	{
		return new LoopItem(tabIndex)
		{
			private static final long serialVersionUID = 1L;

			@Override
			protected void onConfigure()
			{
				super.onConfigure();

				setVisible(getVisiblityCache().isVisible(tabIndex));
			}

			@Override
			protected void onComponentTag(final ComponentTag tag)
			{
				super.onComponentTag(tag);

				String cssClass = tag.getAttribute("class");
				if (cssClass == null)
				{
					cssClass = " ";
				}
				cssClass += " tab" + getIndex();

				if (getIndex() == getSelectedTab())
				{
					cssClass += ' ' + getSelectedTabCssClass();
				}
				if (getVisiblityCache().getLastVisible() == getIndex())
				{
					cssClass += ' ' + getLastTabCssClass();
				}
				tag.put("class", cssClass.trim());
			}
		};
	}

	@Override
	protected void onBeforeRender()
	{
		int index = getSelectedTab();

		if ((index == -1) || (getVisiblityCache().isVisible(index) == false))
		{
			// find first visible tab
			index = -1;
			for (int i = 0; i < tabs.size(); i++)
			{
				if (getVisiblityCache().isVisible(i))
				{
					index = i;
					break;
				}
			}

			if (index != -1)
			{
				// found a visible tab, so select it
				setSelectedTab(index);
			}
		}

		setCurrentTab(index);

		super.onBeforeRender();
	}

	/**
	 * @return the value of css class attribute that will be added to a div containing the tabs. The
	 *         default value is <code>tab-row</code>
	 */
	protected String getTabContainerCssClass()
	{
		return "tab-row";
	}

	/**
	 * @return the value of css class attribute that will be added to last tab. The default value is
	 *         <code>last</code>
	 */
	protected String getLastTabCssClass()
	{
		return "last";
	}

	/**
	 * @return the value of css class attribute that will be added to selected tab. The default
	 *         value is <code>selected</code>
	 */
	protected String getSelectedTabCssClass()
	{
		return "selected";
	}

	/**
	 * @return list of tabs that can be used by the user to add/remove/reorder tabs in the panel
	 */
	public final List<T> getTabs()
	{
		return tabs;
	}

	/**
	 * Factory method for tab titles. Returned component can be anything that can attach to span
	 * tags such as a fragment, panel, or a label
	 * 
	 * @param titleId
	 *            id of title component
	 * @param titleModel
	 *            model containing tab title
	 * @param index
	 *            index of tab
	 * @return title component
	 */
	protected Component newTitle(final String titleId, final IModel<?> titleModel, final int index)
	{
		return new Label(titleId, titleModel);
	}

	/**
	 * Factory method for tab titles. Returned component can be anything that can attach to span
	 * tags such as a fragment, panel, or a label
	 * 
	 * @param titleId
	 *            id of title component
	 * @param titleModel
	 *            model containing tab title
	 * @param index
	 *            index of tab
	 * @return title component
	 */
	protected Component newCloseTitle(final String titleId, final IModel<?> titleModel,
		final int index)
	{
		return new Label(titleId, titleModel);
	}

	/**
	 * Factory method for links used to switch between tabs.
	 * 
	 * The created component is attached to the following markup. Label component with id: title
	 * will be added for you by the tabbed panel.
	 * 
	 * <pre>
	 * &lt;a href=&quot;#&quot; wicket:id=&quot;link&quot;&gt;&lt;span wicket:id=&quot;title&quot;&gt;[[tab title]]&lt;/span&gt;&lt;/a&gt;
	 * </pre>
	 * 
	 * Example implementation:
	 * 
	 * <pre>
	 * protected WebMarkupContainer newLink(String linkId, final int index)
	 * {
	 * 	return new Link(linkId)
	 * 	{
	 * 		private static final long serialVersionUID = 1L;
	 * 
	 * 		public void onClick()
	 * 		{
	 * 			setSelectedTab(index);
	 * 		}
	 * 	};
	 * }
	 * </pre>
	 * 
	 * @param linkId
	 *            component id with which the link should be created
	 * @param index
	 *            index of the tab that should be activated when this link is clicked. See
	 *            {@link #setSelectedTab(int)}.
	 * @return created link component
	 */
	protected WebMarkupContainer newLink(final String linkId, final int index)
	{
		return new AjaxFallbackLink<Void>(linkId)
		{

			private static final long serialVersionUID = 1L;

			@Override
			public void onClick(final AjaxRequestTarget target)
			{
				setSelectedTab(index);
				if (target != null)
				{
					target.add(AjaxCloseableTabbedPanel.this);
				}
				onAjaxUpdate(target);
			}

		};
	}


	/**
	 * Factory method for links used to close the selected tab.
	 * 
	 * The created component is attached to the following markup. Label component with id: title
	 * will be added for you by the tabbed panel.
	 * 
	 * <pre>
	 * &lt;a href=&quot;#&quot; wicket:id=&quot;link&quot;&gt;&lt;span wicket:id=&quot;title&quot;&gt;[[tab title]]&lt;/span&gt;&lt;/a&gt;
	 * </pre>
	 * 
	 * Example implementation:
	 * 
	 * <pre>
	 * protected WebMarkupContainer newCloseLink(String linkId, final int index)
	 * {
	 * 	return new Link(linkId)
	 * 	{
	 * 		private static final long serialVersionUID = 1L;
	 * 
	 * 		public void onClick()
	 * 		{
	 * 			setSelectedTab(index);
	 * 		}
	 * 	};
	 * }
	 * </pre>
	 * 
	 * @param linkId
	 *            component id with which the link should be created
	 * @param index
	 *            index of the tab that should be activated when this link is clicked. See
	 *            {@link #setSelectedTab(int)}.
	 * @return created link component
	 */
	protected WebMarkupContainer newCloseLink(final String linkId, final int index)
	{
		return new AjaxFallbackLink<Void>(linkId)
		{

			private static final long serialVersionUID = 1L;

			@Override
			public void onClick(final AjaxRequestTarget target)
			{
				if (target != null)
				{
					onRemoveTab(target, index);
				}
				onAjaxUpdate(target);
			}
		};
	}

	/**
	 * sets the selected tab
	 * 
	 * @param index
	 *            index of the tab to select
	 * @return this for chaining
	 * @throws IndexOutOfBoundsException
	 *             if index is not in the range of available tabs
	 */
	public AjaxCloseableTabbedPanel<T> setSelectedTab(final int index)
	{
		if ((index < 0) || (index >= tabs.size()))
		{
			throw new IndexOutOfBoundsException();
		}

		setDefaultModelObject(index);

		// force the tab's component to be aquired again if already the current tab
		currentTab = -1;
		setCurrentTab(index);

		return this;
	}

	private void setCurrentTab(int index)
	{
		if (this.currentTab == index)
		{
			// already current
			return;
		}
		this.currentTab = index;

		final Component component;

		if (currentTab == -1 || (tabs.size() == 0) || !getVisiblityCache().isVisible(currentTab))
		{
			// no tabs or the current tab is not visible
			component = newPanel();
		}
		else
		{
			// show panel from selected tab
			T tab = tabs.get(currentTab);
			component = tab.getPanel(TAB_PANEL_ID);
			if (component == null)
			{
				throw new WicketRuntimeException("ITab.getPanel() returned null. TabbedPanel ["
					+ getPath() + "] ITab index [" + currentTab + "]");
			}
		}

		if (!component.getId().equals(TAB_PANEL_ID))
		{
			throw new WicketRuntimeException(
				"ITab.getPanel() returned a panel with invalid id ["
					+ component.getId()
					+ "]. You must always return a panel with id equal to the provided panelId parameter. TabbedPanel ["
					+ getPath() + "] ITab index [" + currentTab + "]");
		}

		addOrReplace(component);
	}

	private WebMarkupContainer newPanel()
	{
		return new WebMarkupContainer(TAB_PANEL_ID);
	}

	/**
	 * @return index of the selected tab
	 */
	public final int getSelectedTab()
	{
		return (Integer)getDefaultModelObject();
	}

	@Override
	protected void onDetach()
	{
		visibilityCache = null;

		super.onDetach();
	}

	private VisibilityCache getVisiblityCache()
	{
		if (visibilityCache == null)
		{
			visibilityCache = new VisibilityCache();
		}

		return visibilityCache;
	}

	/**
	 * A template method that lets users add additional behavior when ajax update occurs. This
	 * method is called after the current tab has been set so access to it can be obtained via
	 * {@link #getSelectedTab()}.
	 * <p>
	 * <strong>Note</strong> Since an {@link AjaxFallbackLink} is used to back the ajax update the
	 * <code>target</code> argument can be null when the client browser does not support ajax and
	 * the fallback mode is used. See {@link AjaxFallbackLink} for details.
	 * 
	 * @param target
	 *            ajax target used to update this component
	 */
	protected void onAjaxUpdate(final AjaxRequestTarget target)
	{
	}

	/**
	 * On new tab.
	 *
	 * @param target
	 *            the target
	 * @param tab
	 *            the tab
	 */
	public void onNewTab(final AjaxRequestTarget target, final T tab)
	{
		getTabs().add(tab);
		setSelectedTab(getTabs().size() - 1);
		target.add(this);
	}

	/**
	 * On new tab.
	 *
	 * @param target
	 *            the target
	 * @param tab
	 *            the tab
	 * @param index
	 *            the index
	 */
	public void onNewTab(final AjaxRequestTarget target, final T tab, final int index)
	{
		if ((index < 0) || (index >= getTabs().size()))
		{
			throw new IndexOutOfBoundsException();
		}
		getTabs().add(index, tab);
		setSelectedTab(index);
		target.add(this);
	}

	/**
	 * On remove tab removes the tab of the given index.
	 *
	 * @param target
	 *            the target
	 * @param index
	 *            the index
	 */
	public void onRemoveTab(final AjaxRequestTarget target, final int index)
	{
		int tabSize = getTabs().size();
		// there have to be at least one tab on the ajaxTabbedPanel...
		if (2 <= tabSize && index < tabSize)
		{
			setSelectedTab(index);
			getTabs().remove(index);
			target.add(this);
		}
	}

	/**
	 * On remove tab removes the given tab if it does exists.
	 *
	 * @param target
	 *            the target
	 * @param tab
	 *            the tab
	 */
	public void onRemoveTab(final AjaxRequestTarget target, final T tab)
	{
		final int index = getTabs().indexOf(tab);
		if (0 <= index)
		{
			onRemoveTab(target, index);
		}
	}


	/**
	 * A cache for visibilities of {@link ITab}s.
	 */
	private class VisibilityCache
	{

		/**
		 * Visibility for each tab.
		 */
		private Boolean[] visibilities;

		/**
		 * Last visible tab.
		 */
		private int lastVisible = -1;

		public VisibilityCache()
		{
			visibilities = new Boolean[tabs.size()];
		}

		public int getLastVisible()
		{
			if (lastVisible == -1)
			{
				for (int t = 0; t < tabs.size(); t++)
				{
					if (isVisible(t))
					{
						lastVisible = t;
					}
				}
			}

			return lastVisible;
		}

		public boolean isVisible(int index)
		{
			if (visibilities.length < index + 1)
			{
				Boolean[] resized = new Boolean[index + 1];
				System.arraycopy(visibilities, 0, resized, 0, visibilities.length);
				visibilities = resized;
			}

			if (visibilities.length > 0)
			{
				Boolean visible = visibilities[index];
				if (visible == null)
				{
					if (index == 1 && index == tabs.size())
					{
						visible = tabs.get(0).isVisible();
						visibilities[0] = visible;
						return visible;
					}
					visible = tabs.get(index).isVisible();
					visibilities[index] = visible;
				}
				return visible;
			}
			else
			{
				return false;
			}
		}
	}
}