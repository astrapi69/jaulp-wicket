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
package de.alpharogroup.wicket.components.examples.ajaxtabs.addtab;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxFallbackLink;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.extensions.markup.html.tabs.ITab;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import de.alpharogroup.wicket.base.util.ComponentFinder;
import de.alpharogroup.wicket.components.ajax.editable.tabs.AbstractContentTab;
import de.alpharogroup.wicket.components.ajax.editable.tabs.AjaxCloseableTabbedPanel;
import de.alpharogroup.wicket.components.ajax.editable.tabs.ICloseableTab;
import de.alpharogroup.wicket.components.examples.ajaxtabs.tabpanels.TabModel;
import de.alpharogroup.wicket.components.examples.ajaxtabs.tabpanels.TabPanel;
import de.alpharogroup.wicket.components.examples.ajaxtabs.tabpanels.TabbedPanelModels;


public class EditableAjaxTabbedPanelDecorator extends Panel
{
	private static final long serialVersionUID = 1L;
	private final List<ICloseableTab> tabs = new ArrayList<>();
	private final AjaxCloseableTabbedPanel<ICloseableTab> ajaxTabbedPanel;

	public EditableAjaxTabbedPanelDecorator(final String id,
		final IModel<TabbedPanelModels<String>> model)
	{
		super(id, model);
		setDefaultModel(new CompoundPropertyModel<TabbedPanelModels<String>>(model));
		final List<TabModel<String>> tabModels = model.getObject().getTabModels();
		for (int i = 0; i < tabModels.size(); i++)
		{
			tabs.add(new AbstractContentTab<TabModel<String>>(tabModels.get(i).getTitle(), Model
				.of(tabModels.get(i)), Model.of("x"))
			{
				private static final long serialVersionUID = 1L;

				@Override
				public Panel getPanel(final String panelId)
				{
					final Panel p = new TabPanel(panelId, getContent().getObject().getContent());
					return p;
				}
			});
		}

		add(ajaxTabbedPanel = new AjaxCloseableTabbedPanel<ICloseableTab>("tabs", tabs)
		{
			private static final long serialVersionUID = 1L;

			@Override
			protected WebMarkupContainer newCloseLink(final String linkId, final int index)
			{
				final WebMarkupContainer wmc = super.newCloseLink(linkId, index);
				wmc.add(new AttributeAppender("class", "close label label-warning"));
				return wmc;

			}

			@Override
			protected WebMarkupContainer newLink(final String linkId, final int index)
			{
				final WebMarkupContainer wmc = super.newLink(linkId, index);
				wmc.add(new AttributeAppender("class", "label label-success"));
				return wmc;
			}
		});
		ajaxTabbedPanel.getTabsUlContainer().add(new AttributeAppender("class", " nav nav-tabs"));
		final AjaxLink<Void> addTabLink = new AjaxLink<Void>("addTabLink")
		{
			private static final long serialVersionUID = 1L;

			@SuppressWarnings("unchecked")
			@Override
			public void onClick(AjaxRequestTarget target)
			{
				if (target == null)
				{
					target = ComponentFinder.findAjaxRequestTarget();
				}
				target.add(ajaxTabbedPanel);
				final int tabNumber = tabs.size() + 1;
				final TabModel<String> thirdTabModel = new TabModel<>(Model.of("tab " + tabNumber),
					Model.of("TAB_" + tabNumber), Model.of("x"));

				final AbstractContentTab<TabModel<String>> tab = new AbstractContentTab<TabModel<String>>(
					thirdTabModel.getTitle(), Model.of(thirdTabModel), Model.of("x"))
				{
					private static final long serialVersionUID = 1L;

					@Override
					public Panel getPanel(final String panelId)
					{
						final Panel p = new TabPanel(panelId, getContent().getObject().getContent());
						return p;
					}
				};
				final Object object = EditableAjaxTabbedPanelDecorator.this.getDefaultModelObject();
				final TabbedPanelModels<String> tabbedModel = (TabbedPanelModels<String>)object;
				final List<TabModel<String>> tabModels = tabbedModel.getTabModels();
				tabModels.add(thirdTabModel);
				ajaxTabbedPanel.onNewTab(target, tab);
			}
		};
		add(addTabLink);
		final AjaxFallbackLink<Object> removeTabLink = new AjaxFallbackLink<Object>("removeTabLink")
		{
			private static final long serialVersionUID = 1L;

			@Override
			public void onClick(final AjaxRequestTarget target)
			{
				target.add(ajaxTabbedPanel);
				onRemoveTab(target, 0);
			}
		};
		add(removeTabLink);
	}

	protected void onNewTab(final AjaxRequestTarget target, final ICloseableTab tab)
	{
		ajaxTabbedPanel.getTabs().add(tab);
		ajaxTabbedPanel.setSelectedTab(tabs.size() - 1);
		target.add(ajaxTabbedPanel);
	}

	protected void onNewTab(final AjaxRequestTarget target, final ICloseableTab tab, final int index)
	{
		ajaxTabbedPanel.onNewTab(target, tab, index);
	}

	@SuppressWarnings("unchecked")
	protected void onRemoveTab(final AjaxRequestTarget target, final int index)
	{
		final Object object = EditableAjaxTabbedPanelDecorator.this.getDefaultModelObject();
		final TabbedPanelModels<String> tabbedModel = (TabbedPanelModels<String>)object;
		final List<TabModel<String>> tabModels = tabbedModel.getTabModels();
		tabModels.remove(index);
		ajaxTabbedPanel.onRemoveTab(target, index);
	}

	protected void onRemoveTab(final AjaxRequestTarget target, final ITab tab)
	{
		final int index = ajaxTabbedPanel.getTabs().indexOf(tab);
		if (0 <= index)
		{
			onRemoveTab(target, index);
		}
	}

}