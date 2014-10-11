package de.alpharogroup.wicket.components.examples.ajaxtabs.addtab;

import java.util.ArrayList;
import java.util.List;

import net.sourceforge.jaulp.io.annotations.ImportResource;
import net.sourceforge.jaulp.io.annotations.ImportResources;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxFallbackLink;
import org.apache.wicket.extensions.ajax.markup.html.tabs.AjaxTabbedPanel;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.jaulp.wicket.base.util.WicketComponentUtils;
import de.alpharogroup.wicket.components.examples.ajaxtabs.tabpanels.TabModel;
import de.alpharogroup.wicket.components.examples.ajaxtabs.tabpanels.TabPanelOne;
import de.alpharogroup.wicket.components.examples.ajaxtabs.tabpanels.TabPanelThree;
import de.alpharogroup.wicket.components.examples.ajaxtabs.tabpanels.TabPanelTwo;

import de.alpharogroup.wicket.components.ajax.editable.tabs.AbstractContentTab;
import de.alpharogroup.wicket.components.examples.ajaxtabs.dynamic.UpdatingTextField;

/**
 * Ajax Tabbed panel demo to add or remove a tab.
 */
@ImportResources(resources = { @ImportResource(resourceName = "TabbedPanelPage.css", resourceType = "css", index = 1) })
public class TabbedPanelPage extends WebPage {
	private static final long serialVersionUID = 1L;
	private final List<AbstractContentTab<TabbedPanelModel>> tabs = new ArrayList<>();
	private final AjaxTabbedPanel<AbstractContentTab<TabbedPanelModel>> ajaxTabbedPanel;
	private final IModel<TabbedPanelModel> imodel;

	public TabbedPanelPage() {
		final TabModel<String> firstTabModel = new TabModel<>(
				Model.of("tab one"), Model.of("tabOne"), Model.of("x"));
		final TabModel<String> secondTabModel = new TabModel<>(
				Model.of("tab two"), Model.of("tabTwo"), Model.of("x"));
		final TabModel<String> thirdTabModel = new TabModel<>(
				Model.of("tab three"), Model.of("tabThree"), Model.of("x"));

		TabbedPanelModel model = new TabbedPanelModel();
		imodel = new CompoundPropertyModel<TabbedPanelModel>(model);

		tabs.add(new AbstractContentTab<TabbedPanelModel>(firstTabModel
				.getTitle(), imodel, Model.of("x")) {
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
				.getTitle(), imodel, Model.of("x")) {
			private static final long serialVersionUID = 1L;

			public Panel getPanel(String panelId) {
				Panel p = new TabPanelTwo(panelId, getContent());
				TextField<String> field = new UpdatingTextField<>(
						secondTabModel.getContent().getObject());
				p.add(field);
				return p;
			}
		});

		add(ajaxTabbedPanel = new AjaxTabbedPanel<>("tabs", tabs));
		AjaxFallbackLink<Object> addTabLink = new AjaxFallbackLink<Object>(
				"addTabLink") {
			private static final long serialVersionUID = 1L;

			@Override
			public void onClick(AjaxRequestTarget target) {
				target.add(ajaxTabbedPanel);
				onNewTab(target, thirdTabModel);
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

	protected void onNewTab(final AjaxRequestTarget target,
			final TabModel<String> tabModel) {
		addTab(tabModel);
		ajaxTabbedPanel.setSelectedTab(tabs.size() - 1);
		target.add(ajaxTabbedPanel);
	}

	protected void onRemoveTab(final AjaxRequestTarget target, final int index) {
		int tabSize = ajaxTabbedPanel.getTabs().size();
		// there have to be at least one tab on the ajaxTabbedPanel...
		if (2 <= tabSize
				&& index < tabSize
				) {
			ajaxTabbedPanel.setSelectedTab(index);
			ajaxTabbedPanel.getTabs().remove(index);
			target.add(ajaxTabbedPanel);
		}
	}

	protected void addTab(final TabModel<String> tabModel) {
		tabs.add(new AbstractContentTab<TabbedPanelModel>(tabModel.getTitle(),
				imodel, Model.of("x")) {
			private static final long serialVersionUID = 1L;

			public Panel getPanel(String panelId) {
				Panel p = new TabPanelThree(panelId, getContent());
				TextField<String> field = new UpdatingTextField<>(tabModel
						.getContent().getObject());
				p.add(field);
				return p;
			}
		});
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void renderHead(IHeaderResponse response) {
		super.renderHead(response);
		WicketComponentUtils.renderHeaderResponse(response, this.getClass());
	}

}