package de.alpharogroup.wicket.components.examples.termofuse;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.model.Model;

public class TermOfUsePage extends WebPage {
	private static final long serialVersionUID = 1L;
	@Override
	protected void onInitialize() {
		super.onInitialize();
		add(new ApplicationTermOfUsePanel("term", Model.of(ApplicationTermOfUseModel.getInstance().getModel())));
	}
}
