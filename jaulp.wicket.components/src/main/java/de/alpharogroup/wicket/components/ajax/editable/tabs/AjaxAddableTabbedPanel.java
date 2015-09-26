/**
 * Copyright (C) 2010 Asterios Raptis
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package de.alpharogroup.wicket.components.ajax.editable.tabs;

import java.util.List;

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

import de.alpharogroup.wicket.components.factory.ComponentFactory;
import lombok.Getter;

/**
 * The Class AjaxAddableTabbedPanel adds functionality to add or remove tabs from the TabbedPanel.
 *
 * @param <T>
 *            the generic type
 */
public abstract class AjaxAddableTabbedPanel<T extends ICloseableTab> extends Panel
{
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

		/**
		 * Instantiates a new {@link VisibilityCache}.
		 */
		public VisibilityCache()
		{
			visibilities = new Boolean[tabs.size()];
		}

		/**
		 * Gets the last visible tab.
		 *
		 * @return the last visible tab.
		 */
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

		/**
		 * Checks if is visible.
		 *
		 * @param index
		 *            the index
		 * @return true, if is visible
		 */
		public boolean isVisible(final int index)
		{
			if (visibilities.length < (index + 1))
			{
				final Boolean[] resized = new Boolean[index + 1];
				System.arraycopy(visibilities, 0, resized, 0, visibilities.length);
				visibilities = resized;
			}

			if (visibilities.length > 0)
			{
				Boolean visible = visibilities[index];
				if (visible == null)
				{
					if ((index == 1) && (index == tabs.size()))
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

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** id used for child panels */
	public static final String TAB_PANEL_ID = "panel";

	/** The list with the tabs. */
	private final List<T> tabs;

	/** the current tab */
	private int currentTab = -1;
	/** the cache for the visibility. */
	private transient VisibilityCache visibilityCache;
	/** the Container for the unordered list. */
	@Getter
	private WebMarkupContainer tabsUlContainer;
	/** the Container for the tabs. */
	@Getter
	private WebMarkupContainer tabsContainer;
	/** the {@link Loop} for the tabs. */
	@Getter
	private Loop tabsLoop;

	/**
	 * Instantiates a new {@link AjaxAddableTabbedPanel}.
	 *
	 * @param id
	 *            component id
	 * @param tabs
	 *            list of ITab objects used to represent tabs
	 */
	public AjaxAddableTabbedPanel(final String id, final List<T> tabs)
	{
		this(id, tabs, null);
	}

	/**
	 * Instantiates a new {@link AjaxAddableTabbedPanel}.
	 *
	 * @param id
	 *            component id
	 * @param tabs
	 *            list of ITab objects used to represent tabs
	 * @param model
	 *            model holding the index of the selected tab
	 */
	public AjaxAddableTabbedPanel(final String id, final List<T> tabs, final IModel<Integer> model)
	{
		super(id, model);
		setOutputMarkupId(true);
		setVersioned(false);
		this.tabs = Args.notNull(tabs, "tabs");

		final IModel<Integer> tabCount = new AbstractReadOnlyModel<Integer>()
		{
			/** The Constant serialVersionUID. */
			private static final long serialVersionUID = 1L;

			/**
			 * {@inheritDoc}
			 */
			@Override
			public Integer getObject()
			{
				return AjaxAddableTabbedPanel.this.tabs.size();
			}
		};

		tabsContainer = newTabsContainer("tabs-container");
		add(tabsContainer);

		tabsUlContainer = newTabsContainer("tabs-ul-container");
		tabsContainer.add(tabsUlContainer);
		// add the loop used to generate tab names
		tabsUlContainer.add(tabsLoop = newTabsLoop("tabs", tabCount));

		tabsUlContainer.add(newAddTab("add-tab-container", newAddTabLabelModel()));

		add(newPanel());
	}

	/**
	 * Get the css class of the last tab.
	 *
	 * @return the value of css class attribute that will be added to last tab. The default value is
	 *         <code>last</code>
	 */
	protected String getLastTabCssClass()
	{
		return "last";
	}

	/**
	 * Get the selected tab.
	 *
	 * @return index of the selected tab
	 */
	public final int getSelectedTab()
	{
		return (Integer)getDefaultModelObject();
	}

	/**
	 * Get the css class of the selected tab.
	 *
	 * @return the value of css class attribute that will be added to selected tab. The default
	 *         value is <code>selected</code>
	 */
	protected String getSelectedTabCssClass()
	{
		return "selected";
	}

	/**
	 * Get the css class of the tab container.
	 *
	 * @return the value of css class attribute that will be added to a div containing the tabs. The
	 *         default value is <code>tab-row</code>
	 */
	protected String getTabContainerCssClass()
	{
		return "tab-row";
	}

	/**
	 * Gets the list of tabs that can be used by the user to add/remove/reorder tabs in the panel.
	 *
	 * @return list of tabs that can be used by the user to add/remove/reorder tabs in the panel
	 */
	public final List<T> getTabs()
	{
		return tabs;
	}


	/**
	 * Gets the visiblity cache.
	 *
	 * @return the visiblity cache
	 */
	private VisibilityCache getVisiblityCache()
	{
		if (visibilityCache == null)
		{
			visibilityCache = new VisibilityCache();
		}

		return visibilityCache;
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
	 * Abstract factory method for creating the new tab {@link Component}. This method is invoked in
	 * the constructor from the derived classes and have to be implemented so users can provide
	 * their own version of a new tab {@link Component}.
	 *
	 * @param id
	 *            the id
	 * @param model
	 *            the model
	 * @return the new tab component.
	 */
	protected abstract Component newAddTab(final String id, final IModel<String> model);

	/**
	 * Factory method for creating the new label of the button.
	 *
	 * @param id
	 *            the id
	 * @param model
	 *            the model
	 * @return the new label of the button.
	 */
	protected Label newAddTabButtonLabel(final String id, final IModel<String> model)
	{
		return ComponentFactory.newLabel(id, model);
	}

	/**
	 * Abstract factory method for creating the new {@link Label} for the tab {@link Component}.
	 * This method is invoked in the constructor from the derived classes and have to be implemented
	 * so users can provide their own version of a new {@link Label} for the tab {@link Component}.
	 *
	 * @param id
	 *            the id
	 * @param model
	 *            the model
	 * @return the new {@link Label} for the tab {@link Component}.
	 */
	protected abstract Label newaddTabLabel(final String id, final IModel<String> model);

	/**
	 * Abstract factory method for creating the new {@link IModel} for the add tab {@link Label}.
	 * This method is invoked in the constructor from the derived classes and have to be implemented
	 * so users can provide their own version of a new new {@link IModel} for the add tab
	 * {@link Label}.
	 *
	 * @return the new {@link IModel} for the add tab {@link Label}.
	 */
	protected abstract IModel<String> newAddTabLabelModel();

	/**
	 * Abstract factory method for creating a new {@link Component} for the modal dialog for create
	 * a new {@link Component} for the tab. This method is invoked in the constructor from the
	 * derived classes and have to be implemented so users can provide their own version of a new
	 * {@link Component} for the modal dialog for create a new {@link Component} for the tab.
	 *
	 * @param id
	 *            the id
	 * @param model
	 *            the model
	 * @return the new {@link Component} for the modal dialog for create a new {@link Component} for
	 *         the tab.
	 */
	protected abstract Component newAddTabModalWindow(final String id, final IModel<String> model);

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

			/** The Constant serialVersionUID. */
			private static final long serialVersionUID = 1L;

			/**
			 * {@inheritDoc}
			 */
			@Override
			public void onClick(final AjaxRequestTarget target)
			{
				setSelectedTab(index);
				if (target != null)
				{
					target.add(AjaxAddableTabbedPanel.this);
				}
				onAjaxUpdate(target);
			}

		};
	}

	/**
	 * Factory method for the new tab panel.
	 *
	 * @return the new tab panel.
	 */
	private WebMarkupContainer newPanel()
	{
		return new WebMarkupContainer(TAB_PANEL_ID);
	}


	/**
	 * Generates a loop item used to represent a specific tab's <code>li</code> element.
	 *
	 * @param tabIndex
	 *            the tab index
	 * @return new loop item
	 */
	protected LoopItem newTabContainer(final int tabIndex)
	{
		return new LoopItem(tabIndex)
		{
			/** The Constant serialVersionUID. */
			private static final long serialVersionUID = 1L;

			/**
			 * {@inheritDoc}
			 */
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

			/**
			 * {@inheritDoc}
			 */
			@Override
			protected void onConfigure()
			{
				super.onConfigure();
				setVisible(getVisiblityCache().isVisible(tabIndex));
			}
		};
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
		final WebMarkupContainer wmc = new WebMarkupContainer(id)
		{
			/** The Constant serialVersionUID. */
			private static final long serialVersionUID = 1L;

			/**
			 * {@inheritDoc}
			 */
			@Override
			protected void onComponentTag(final ComponentTag tag)
			{
				super.onComponentTag(tag);
				tag.put("class", getTabContainerCssClass());
			}
		};
		wmc.setOutputMarkupId(true);
		return wmc;
	}

	/**
	 * Factory method for creating a new {@link Loop} for the tabs.
	 *
	 * @param id
	 *            the id
	 * @param model
	 *            the model
	 * @return the new {@link Loop} for the tabs.
	 */
	protected Loop newTabsLoop(final String id, final IModel<Integer> model)
	{
		final Loop localTabsLoop = new Loop(id, model)
		{
			/** The Constant serialVersionUID. */
			private static final long serialVersionUID = 1L;

			/**
			 * {@inheritDoc}
			 */
			@Override
			protected LoopItem newItem(final int iteration)
			{
				return newTabContainer(iteration);
			}

			/**
			 * {@inheritDoc}
			 */
			@Override
			protected void populateItem(final LoopItem item)
			{
				final int index = item.getIndex();
				final T tab = AjaxAddableTabbedPanel.this.tabs.get(index);

				final WebMarkupContainer titleCloseLink = newCloseLink("closeTab", index);

				titleCloseLink.add(newCloseTitle("closeTitle", tab.getCloseTitle(), index));
				item.add(titleCloseLink);

				final WebMarkupContainer titleLink = newLink("link", index);

				titleLink.add(newTitle("title", tab.getTitle(), index));
				item.add(titleLink);
				item.add(new AttributeAppender("class", " label"));

			}
		};
		localTabsLoop.setOutputMarkupId(true);
		return localTabsLoop;
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
	 * {@inheritDoc}
	 */
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
	 * {@inheritDoc}
	 */
	@Override
	protected void onDetach()
	{
		visibilityCache = null;

		super.onDetach();
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
		final int tabSize = getTabs().size();
		// there have to be at least one tab on the ajaxTabbedPanel...
		if ((2 <= tabSize) && (index < tabSize))
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
	 * Sets the current tab.
	 *
	 * @param index
	 *            the new current tab
	 */
	private void setCurrentTab(final int index)
	{
		if (this.currentTab == index)
		{
			// already current
			return;
		}
		this.currentTab = index;

		final Component component;

		if ((currentTab == -1) || tabs.isEmpty() || !getVisiblityCache().isVisible(currentTab))
		{
			// no tabs or the current tab is not visible
			component = newPanel();
		}
		else
		{
			// show panel from selected tab
			final T tab = tabs.get(currentTab);
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
				"ITab.getPanel() returned a panel with invalid id [" + component.getId()
					+ "]. You always have to return a panel with id equal to the provided panelId parameter. TabbedPanel ["
					+ getPath() + "] ITab index [" + currentTab + "]");
		}

		addOrReplace(component);
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
	public AjaxAddableTabbedPanel<T> setSelectedTab(final int index)
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
}
