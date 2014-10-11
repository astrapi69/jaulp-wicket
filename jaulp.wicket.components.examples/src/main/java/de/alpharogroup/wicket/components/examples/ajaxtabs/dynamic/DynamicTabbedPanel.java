package de.alpharogroup.wicket.components.examples.ajaxtabs.dynamic;

import java.util.List;

import org.apache.wicket.Component;
import org.apache.wicket.WicketRuntimeException;
import org.apache.wicket.extensions.markup.html.tabs.ITab;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.Loop;
import org.apache.wicket.markup.html.list.LoopItem;
import org.apache.wicket.markup.html.panel.EmptyPanel;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

/**
 * Copy and paste extension of the standard Wicket TabbedPanel but using
 * a model object to provide the tabs rather than a list. This allows the
 * specific tabs to be dynamic and depend on model data rather than being
 * hard-coded.
 *
 * @author Jonny Wray
 */
public class DynamicTabbedPanel extends Panel {
    private static final long serialVersionUID = 1L;

	/** id used for child panels */
	public static final String TAB_PANEL_ID = "panel";

	private IModel<List<ITab>> tabModel;

	private transient Boolean[] tabsVisibilityCache;

	public DynamicTabbedPanel(final String id, final IModel<List<ITab>> tabModel){
		super(id, new Model<Integer>(-1));
		this.tabModel = tabModel;

		final IModel<Integer> tabCount = new AbstractReadOnlyModel<Integer>(){
			private static final long serialVersionUID = 1L;

			@Override
			public Integer getObject(){
				return tabModel.getObject().size();
			}
		};

		WebMarkupContainer tabsContainer = newTabsContainer("tabs-container");
		add(tabsContainer);

		// add the loop used to generate tab names
		tabsContainer.add(new Loop("tabs", tabCount)
		{
			private static final long serialVersionUID = 1L;

			@Override
			protected void populateItem(final LoopItem item)
			{
				final int index = item.getIndex();
				final ITab tab = tabModel.getObject().get(index);

				final WebMarkupContainer titleLink = newLink("link", index);

				titleLink.add(newTitle("title", tab.getTitle(), index));
				item.add(titleLink);
			}

			@Override
			protected LoopItem newItem(final int iteration)
			{
				return newTabContainer(iteration);
			}

            public boolean isVisible(){
                return tabCount.getObject() > 0;
            }
		});
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

	/*
	 * Generates a loop item used to represent a specific tab's <code>li</code> element.
	 */
	protected LoopItem newTabContainer(final int tabIndex)
	{
		return new LoopItem(tabIndex)
		{
			private static final long serialVersionUID = 1L;

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
					cssClass += " selected";
				}
				if (getIndex() == getTabs().size() - 1)
				{
					cssClass += " last";
				}
				tag.put("class", cssClass.trim());
			}

			@Override
			public boolean isVisible()
			{
				return getTabs().get(tabIndex).isVisible();
			}
		};
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void onBeforeRender()
	{
		if (tabModel.getObject().size() == 0)
		{
			// force an empty container to be created every time if we have no tabs
			setSelectedTab(0);
		}
        else if(getSelectedTab() > tabModel.getObject().size()){
            setSelectedTab(0);
        }
		else if ((getSelectedTab() == -1) || (isTabVisible(getSelectedTab()) == false))
		{
			// find first visible selected tab
			int selected = 0;
			for (int i = 0; i < tabModel.getObject().size(); i++)
			{
				if (isTabVisible(i))
				{
					selected = i;
					break;
				}
			}

			if (selected == tabModel.getObject().size())
			{
				/*
				 * none of the tabs are selected...
				 *
				 * we do not need to do anything special because the check in setSelectedTab() will
				 * replace the current tab panel with an empty one
				 */
				selected = 0;
			}

			setSelectedTab(selected);
		}

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
	 * @return list of tabs that can be used by the user to add/remove/reorder tabs in the panel
	 */
	public final List<? extends ITab> getTabs()
	{
		return tabModel.getObject();
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
	protected Component newTitle(final String titleId, final IModel<?> titleModel, final int index){
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
		return new Link<Void>(linkId)
		{
			private static final long serialVersionUID = 1L;

			@Override
			public void onClick()
			{
				setSelectedTab(index);
			}
		};
	}

	/**
	 * sets the selected tab
	 *
	 * @param index
	 *            index of the tab to select
	 * @return this for chaining
	 */
	public DynamicTabbedPanel setSelectedTab(final int index)
	{
		if ((index < 0) || ((index >= tabModel.getObject().size()) && (index > 0)))
		{
			throw new IndexOutOfBoundsException();
		}

		setDefaultModelObject(index);

		final Component component;

		if ((tabModel.getObject().size() == 0) || !isTabVisible(index))
		{
			// no tabs or the currently selected tab is not visible
			component = new EmptyPanel(TAB_PANEL_ID);
		}
		else
		{
			// show panel from selected tab
			ITab tab = tabModel.getObject().get(index);
			component = tab.getPanel(TAB_PANEL_ID);
			if (component == null)
			{
				throw new WicketRuntimeException("ITab.getPanel() returned null. TabbedPanel [" +
					getPath() + "] ITab index [" + index + "]");
			}
		}

		if (!component.getId().equals(TAB_PANEL_ID))
		{
			throw new WicketRuntimeException(
				"ITab.getPanel() returned a panel with invalid id [" +
					component.getId() +
					"]. You must always return a panel with id equal to the provided panelId parameter. TabbedPanel [" +
					getPath() + "] ITab index [" + index + "]");
		}

		addOrReplace(component);

		return this;
	}

	/**
	 * @return index of the selected tab
	 */
	public final int getSelectedTab()
	{
		return (Integer)getDefaultModelObject();
	}

	/**
	 *
	 * @param tabIndex
	 * @return visible
	 */
	private boolean isTabVisible(final int tabIndex)
	{
		if (tabsVisibilityCache == null)
		{
			tabsVisibilityCache = new Boolean[tabModel.getObject().size()];
		}
		if (tabsVisibilityCache.length < tabIndex + 1)
		{
			Boolean[] resized = new Boolean[tabIndex + 1];
			System.arraycopy(tabsVisibilityCache, 0, resized, 0, tabsVisibilityCache.length);
			tabsVisibilityCache = resized;
		}
		if (tabsVisibilityCache.length > 0)
		{
			Boolean visible = tabsVisibilityCache[tabIndex];
			if (visible == null)
			{
				visible = tabModel.getObject().get(tabIndex).isVisible();
				tabsVisibilityCache[tabIndex] = visible;
			}
			return visible;
		}
		else
		{
			return false;
		}
	}

	@Override
	protected void onDetach(){
		tabsVisibilityCache = null;
        tabModel.detach();
		super.onDetach();
	}
}