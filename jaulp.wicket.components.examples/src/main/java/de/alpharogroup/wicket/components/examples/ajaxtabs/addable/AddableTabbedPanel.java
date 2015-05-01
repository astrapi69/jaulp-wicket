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
package de.alpharogroup.wicket.components.examples.ajaxtabs.addable;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.extensions.ajax.markup.html.modal.ModalWindow;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import de.alpharogroup.wicket.base.util.ComponentFinder;
import de.alpharogroup.wicket.components.ajax.editable.tabs.AbstractContentTab;
import de.alpharogroup.wicket.components.ajax.editable.tabs.AjaxAddableTabbedPanel;
import de.alpharogroup.wicket.components.ajax.editable.tabs.ICloseableTab;
import de.alpharogroup.wicket.components.examples.ajaxtabs.tabpanels.TabModel;
import de.alpharogroup.wicket.components.examples.ajaxtabs.tabpanels.TabPanel;
import de.alpharogroup.wicket.components.examples.ajaxtabs.tabpanels.TabbedPanelModels;
import de.alpharogroup.wicket.components.factory.ComponentFactory;
import de.alpharogroup.wicket.dialogs.panels.save.SaveDialogPanel;

public class AddableTabbedPanel extends Panel
{

	/**
	 * The serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;
	private final List<ICloseableTab> tabs = new ArrayList<>();
	@Getter
	private final AjaxAddableTabbedPanel<ICloseableTab> ajaxTabbedPanel;

	public AddableTabbedPanel(final String id, final IModel<TabbedPanelModels<String>> model)
	{
		super(id, model);

		setDefaultModel(new CompoundPropertyModel<TabbedPanelModels<String>>(model));
		List<TabModel<String>> tabModels = model.getObject().getTabModels();
		for (int i = 0; i < tabModels.size(); i++)
		{
			tabs.add(new AbstractContentTab<TabModel<String>>(tabModels.get(i).getTitle(), Model
				.of(tabModels.get(i)), Model.of("x"))
			{
				private static final long serialVersionUID = 1L;

				@Override
				public Panel getPanel(String panelId)
				{
					Panel p = new TabPanel(panelId, getContent().getObject().getContent());
					return p;
				}
			});
		}

		add(ajaxTabbedPanel = new AjaxAddableTabbedPanel<ICloseableTab>("tabs", tabs)
		{
			private static final long serialVersionUID = 1L;

			@Override
			protected WebMarkupContainer newCloseLink(final String linkId, final int index)
			{
				WebMarkupContainer wmc = super.newCloseLink(linkId, index);
				wmc.add(new AttributeAppender("class", "close label label-warning"));
				return wmc;

			}

			@Override
			protected WebMarkupContainer newLink(String linkId, int index)
			{
				WebMarkupContainer wmc = super.newLink(linkId, index);
				wmc.add(new AttributeAppender("class", "label label-success"));
				return wmc;
			}

			@Override
			protected IModel<String> newAddTabLabelModel()
			{
				return Model.of("+");
			}

			@Override
			protected Component newAddTab(String id, IModel<String> model)
			{
				WebMarkupContainer addTabContainer = new WebMarkupContainer(id);
				addTabContainer.setOutputMarkupId(true);
				addTabContainer.add(new AttributeAppender("class", " label"));
				final ModalWindow modalWindow = newAddTabModalWindow("modalWindow",
					Model.of("Add new tab"));
				addTabContainer.add(modalWindow);
				AjaxLink<Void> openModal = new AjaxLink<Void>("openModal")
				{
					private static final long serialVersionUID = 1L;

					@Override
					public void onClick(AjaxRequestTarget target)
					{
						target.prependJavaScript("Wicket.Window.unloadConfirmation = false;");
						modalWindow.show(target);
					}
				};
				openModal.setOutputMarkupId(true);
				openModal.add(newAddTabLabel("addTabLabel", Model.of("+")));
				openModal.add(new AttributeAppender("class", " label-success"));
				addTabContainer.add(openModal);
				return addTabContainer;
			}

			@Override
			protected ModalWindow newAddTabModalWindow(String id, IModel<String> model)
			{
				final ModalWindow modalWindow = new ModalWindow(id);
				modalWindow.setOutputMarkupId(true);
				modalWindow.setCssClassName(ModalWindow.CSS_CLASS_GRAY);
				modalWindow.setTitle(model.getObject());
				modalWindow.setInitialHeight(200);
				modalWindow.setInitialWidth(300);
				modalWindow.setContent(new SaveDialogPanel<String>(modalWindow.getContentId(),
					Model.of(new String()))
				{
					/**
					 * The serialVersionUID.
					 */
					private static final long serialVersionUID = 1L;

					@Override
					protected void onCancel(AjaxRequestTarget target, Form<?> form)
					{
						super.onCancel(target, form);
						modalWindow.close(target);
					}

					@SuppressWarnings("unchecked")
					@Override
					protected void onSave(AjaxRequestTarget target, Form<?> form)
					{
						super.onSave(target, form);
						if (target == null)
						{
							target = ComponentFinder.findAjaxRequestTarget();
						}
						Object value = getModel();
						String v = null;
						if (value instanceof IModel)
						{
							Object obj = ((IModel<?>)value).getObject();
							if (obj instanceof String)
							{
								v = (String)obj;
							}
						}
						target.add(ajaxTabbedPanel);

						final TabModel<String> newTabModel = new TabModel<>(Model.of(v), Model
							.of(v), Model.of("x"));

						AbstractContentTab<TabModel<String>> tab = new AbstractContentTab<TabModel<String>>(
							newTabModel.getTitle(), Model.of(newTabModel), Model.of("x"))
						{
							private static final long serialVersionUID = 1L;

							@Override
							public Panel getPanel(String panelId)
							{
								Panel p = new TabPanel(panelId, getContent().getObject()
									.getContent());
								return p;
							}
						};
						Object object = AddableTabbedPanel.this.getDefaultModelObject();
						TabbedPanelModels<String> tabbedModel = (TabbedPanelModels<String>)object;
						List<TabModel<String>> tabModels = tabbedModel.getTabModels();
						tabModels.add(newTabModel);
						ajaxTabbedPanel.onNewTab(target, tab);
						modalWindow.close(target);
					}
				});
				return modalWindow;
			}

			@Override
			protected Label newaddTabLabel(String id, IModel<String> model)
			{
				return ComponentFactory.newLabel(id, model);
			}
		});
	}

}
