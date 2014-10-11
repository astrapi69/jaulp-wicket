package de.alpharogroup.wicket.components.examples.ajaxtabs.addtab;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxFallbackLink;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.extensions.markup.html.tabs.ITab;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.jaulp.wicket.base.util.ComponentFinder;

import de.alpharogroup.wicket.bootstrap2.components.tabs.BootstrapAjaxTabbedPanel;
import de.alpharogroup.wicket.components.ajax.editable.tabs.AbstractContentTab;
import de.alpharogroup.wicket.components.ajax.editable.tabs.ICloseableTab;
import de.alpharogroup.wicket.components.examples.ajaxtabs.dynamic.UpdatingTextField;
import de.alpharogroup.wicket.components.examples.ajaxtabs.tabpanels.TabModel;
import de.alpharogroup.wicket.components.examples.ajaxtabs.tabpanels.TabPanelOne;
import de.alpharogroup.wicket.components.examples.ajaxtabs.tabpanels.TabPanelThree;
import de.alpharogroup.wicket.components.examples.ajaxtabs.tabpanels.TabPanelTwo;


public class EditableAjaxTabbedPanelDecorator extends Panel {
	private static final long serialVersionUID = 1L;
	private final List<ICloseableTab> tabs = new ArrayList<>();
	private final BootstrapAjaxTabbedPanel<ICloseableTab> ajaxTabbedPanel;
	@SuppressWarnings("unchecked")
	public EditableAjaxTabbedPanelDecorator(String id, IModel<TabbedPanelModel> model) {
		super(id, model);
		final TabModel<String> firstTabModel = new TabModel<>(
				Model.of("tab one"), Model.of("tabOne"), Model.of("x"));
		final TabModel<String> secondTabModel = new TabModel<>(
				Model.of("tab two"), Model.of("tabTwo"), Model.of("x"));
		final TabModel<String> thirdTabModel = new TabModel<>(
				Model.of("tab three"), Model.of("tabThree"), Model.of("x"));
		setDefaultModel(new CompoundPropertyModel<TabbedPanelModel>(model));

		tabs.add(new AbstractContentTab<TabbedPanelModel>(firstTabModel
				.getTitle(), (IModel<TabbedPanelModel>) getDefaultModel(), Model.of("x")) {
			private static final long serialVersionUID = 1L;
			public Panel getPanel(String panelId) {
				Panel p = new TabPanelOne(panelId, getContent());
				TextField<String> field = new UpdatingTextField<>(firstTabModel
						.getContent().getObject());
				p.add(field);
				return p;
			}
		});

		tabs.add(new AbstractContentTab<TabbedPanelModel>(secondTabModel
				.getTitle(), (IModel<TabbedPanelModel>) getDefaultModel(), Model.of("x")) {
			private static final long serialVersionUID = 1L;

			public Panel getPanel(String panelId) {
				Panel p = new TabPanelTwo(panelId, getContent());
				TextField<String> field = new UpdatingTextField<>(
						secondTabModel.getContent().getObject());
				p.add(field);
				return p;
			}
		});

		add(ajaxTabbedPanel = new BootstrapAjaxTabbedPanel<ICloseableTab>("tabs", tabs) {
			private static final long serialVersionUID = 1L;
			protected WebMarkupContainer newCloseLink(final String linkId, final int index) {
				WebMarkupContainer wmc = super.newCloseLink(linkId, index);
				wmc.add(new AttributeAppender("class", "close label label-warning"));
				return wmc;
				
			}
			@Override
			protected WebMarkupContainer newLink(String linkId, int index) {
				WebMarkupContainer wmc = super.newLink(linkId, index);
				wmc.add(new AttributeAppender("class", "label label-success"));
				return wmc;
			}
		});
		ajaxTabbedPanel.getTabsUlContainer().add(new AttributeAppender("class", " nav nav-tabs"));
		AjaxLink<Void> addTabLink = new AjaxLink<Void>("addTabLink") {
			private static final long serialVersionUID = 1L;

			@Override
			public void onClick(AjaxRequestTarget target) {
				if(target == null) {
					target = ComponentFinder.findAjaxRequestTarget();
				}
				target.add(ajaxTabbedPanel);
				AbstractContentTab<TabbedPanelModel> tab = new AbstractContentTab<TabbedPanelModel>(thirdTabModel.getTitle(),
						(IModel<TabbedPanelModel>) getDefaultModel(), Model.of("x")) {
					private static final long serialVersionUID = 1L;

					public Panel getPanel(String panelId) {
						Panel p = new TabPanelThree(panelId, getContent());
						TextField<String> field = new UpdatingTextField<>(thirdTabModel
								.getContent().getObject());
						p.add(field);
						return p;
					}
				};
				ajaxTabbedPanel.onNewTab(target, tab);
			}
		};
		add(addTabLink);
		AjaxFallbackLink<Object> removeTabLink = new AjaxFallbackLink<Object>(
				"removeTabLink") {
			private static final long serialVersionUID = 1L;

			@Override
			public void onClick(AjaxRequestTarget target) {
				target.add(ajaxTabbedPanel);
				onRemoveTab(target, 0);
			}
		};
		add(removeTabLink);
	}	

	protected void onNewTab(final AjaxRequestTarget target,	final ICloseableTab tab) {
		ajaxTabbedPanel.getTabs().add(tab);
		ajaxTabbedPanel.setSelectedTab(tabs.size() - 1);
		target.add(ajaxTabbedPanel);
	}	

	protected void onNewTab(final AjaxRequestTarget target,	final ICloseableTab tab, final int index) {
		ajaxTabbedPanel.onNewTab(target, tab, index);
	}

	protected void onRemoveTab(final AjaxRequestTarget target, final int index) {
//		int tabSize = ajaxTabbedPanel.getTabs().size();
//		// there have to be at least one tab on the ajaxTabbedPanel...
//		if (2 <= tabSize
//				&& index < tabSize
//				) {
//			ajaxTabbedPanel.setSelectedTab(index);
//			ajaxTabbedPanel.getTabs().remove(index);
//			target.add(ajaxTabbedPanel);
//		}
		ajaxTabbedPanel.onRemoveTab(target, index);
	}

	protected void onRemoveTab(final AjaxRequestTarget target, final ITab tab) {
		final int index = ajaxTabbedPanel.getTabs().indexOf(tab);
		if(0 <= index) {
			onRemoveTab(target, index);
		}
	}

}
