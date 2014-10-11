package de.alpharogroup.wicket.components.examples.ajaxtabs.dynamic;

import java.util.ArrayList;
import java.util.List;

import net.sourceforge.jaulp.io.annotations.ImportResource;
import net.sourceforge.jaulp.io.annotations.ImportResources;

import org.apache.wicket.extensions.markup.html.tabs.ITab;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import de.alpharogroup.wicket.components.examples.ajaxtabs.addtab.TabbedPanelModel;
import de.alpharogroup.wicket.components.examples.ajaxtabs.tabpanels.TabModel;
import de.alpharogroup.wicket.components.examples.ajaxtabs.tabpanels.TabPanelOne;
import de.alpharogroup.wicket.components.examples.ajaxtabs.tabpanels.TabPanelThree;
import de.alpharogroup.wicket.components.examples.ajaxtabs.tabpanels.TabPanelTwo;

import org.jaulp.wicket.base.util.WicketComponentUtils;

import de.alpharogroup.wicket.components.ajax.editable.tabs.AbstractContentTab;

@ImportResources(resources = {
		@ImportResource(resourceName = "AjaxDynamicTabbedPage.css", resourceType = "css", index = 1)})
public class AjaxDynamicTabbedPage extends WebPage {
	private String text1 = "";
	private String text2 = "";
	private String text3 = "";
	private final List<ITab> tabs = new ArrayList<>();
	public AjaxDynamicTabbedPage() {
		final TabModel<String> firstTabModel = new TabModel<>(
				Model.of("tab one"), Model.of("tabOne"), Model.of("x"));
		final TabModel<String> secondTabModel = new TabModel<>(
				Model.of("tab two"), Model.of("tabTwo"), Model.of("x"));
		final TabModel<String> thirdTabModel = new TabModel<>(
				Model.of("tab three"), Model.of("tabThree"), Model.of("x"));

		TabbedPanelModel model = new TabbedPanelModel();
		IModel<TabbedPanelModel> imodel = Model.of(model);

		setDefaultModel(new CompoundPropertyModel<TabbedPanelModel>(imodel));
		tabs.add(new AbstractContentTab<TabbedPanelModel>(firstTabModel
				.getTitle(), (IModel<TabbedPanelModel>) getDefaultModel(), firstTabModel.getCloseTitle()) {
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
				.getTitle(), (IModel<TabbedPanelModel>) getDefaultModel(), secondTabModel.getCloseTitle()) {
			private static final long serialVersionUID = 1L;

			public Panel getPanel(String panelId) {
				Panel p = new TabPanelTwo(panelId, getContent());
				TextField<String> field = new UpdatingTextField<>(
						secondTabModel.getContent().getObject());
				p.add(field);
				return p;
			}
		});
		IModel<List<ITab>> tabModel = new CompoundPropertyModel<List<ITab>>(tabs);
		add(new AjaxDynamicTabbedPanel("tabs", tabModel));
		
	}


	/**
	 * {@inheritDoc}
	 */
	@Override
	public void renderHead(IHeaderResponse response) {
    	super.renderHead(response);
    	WicketComponentUtils.renderHeaderResponse(response, this.getClass());
	}

	public String getText1() {
		return text1;
	}

	public void setText1(String text1) {
		this.text1 = text1;
	}

	public String getText2() {
		return text2;
	}

	public void setText2(String text2) {
		this.text2 = text2;
	}

	public String getText3() {
		return text3;
	}

	public void setText3(String text3) {
		this.text3 = text3;
	}
}
