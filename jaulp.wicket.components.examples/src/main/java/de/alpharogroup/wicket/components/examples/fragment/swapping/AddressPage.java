package de.alpharogroup.wicket.components.examples.fragment.swapping;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.model.Model;

public class AddressPage extends WebPage {

	private static final long serialVersionUID = 1L;

	public AddressPage() {
		add(new AddressPanel("addressPanel", Model.of(new HomeAddress())));
	}
}
