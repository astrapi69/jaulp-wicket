package de.alpharogroup.wicket.components.examples.ajaxtabs.addtab;

import net.sourceforge.jaulp.io.annotations.ImportResource;
import net.sourceforge.jaulp.io.annotations.ImportResources;

import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.filter.HeaderResponseContainer;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.jaulp.wicket.base.util.WicketComponentUtils;

import de.agilecoders.wicket.core.Bootstrap;
import de.agilecoders.wicket.core.markup.html.bootstrap.behavior.BootstrapBaseBehavior;
import de.alpharogroup.wicket.components.examples.application.WicketApplication;


/**
 * Ajax Tabbed panel demo to add or remove a tab.
 */
@ImportResources(resources = { @ImportResource(resourceName = "TabbedPanelPage.css", resourceType = "css", index = 1) })
public class EditableAjaxTabbedPage extends WebPage {
	private static final long serialVersionUID = 1L;

	public EditableAjaxTabbedPage() {
		add(new BootstrapBaseBehavior());
		HeaderResponseContainer headerResponseContainer = new HeaderResponseContainer(
				WicketApplication.FOOTER_FILTER_NAME,
				WicketApplication.FOOTER_FILTER_NAME);
		add(headerResponseContainer);
		TabbedPanelModel model = new TabbedPanelModel();
		IModel<TabbedPanelModel> imodel = Model.of(model);
		EditableAjaxTabbedPanelDecorator tabbedPanel = new EditableAjaxTabbedPanelDecorator("tabbedPanel", imodel);
		add(tabbedPanel);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void renderHead(IHeaderResponse response) {
		super.renderHead(response);
		Bootstrap.renderHead(response);
		WicketComponentUtils.renderHeaderResponse(response, this.getClass());
	}

}