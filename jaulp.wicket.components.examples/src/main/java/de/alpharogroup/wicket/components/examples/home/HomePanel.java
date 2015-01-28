package de.alpharogroup.wicket.components.examples.home;

import net.sourceforge.jaulp.locale.ResourceBundleKey;

import org.apache.wicket.model.Model;
import org.jaulp.wicket.base.BasePanel;
import org.jaulp.wicket.behaviors.AddJsQueryBehavior;

import de.alpharogroup.wicket.components.i18n.content.ContentModel;
import de.alpharogroup.wicket.components.i18n.content.ContentPanel;

public class HomePanel extends BasePanel<Object> {
	private static final long serialVersionUID = 1L;

	public HomePanel(String id) {
		super(id);
		ContentPanel contentPanel = new ContentPanel("contentPanel", Model.of(ContentModel.builder()
				.headerResourceKey(ResourceBundleKey.builder()
						.key("home.header.label")
						.build())
				.contentResourceKey(ResourceBundleKey.builder()
						.key("home.content.label")
						.build())
				.build()));
		contentPanel.getHeader().add(
				new AddJsQueryBehavior("wrap", "<h1></h1>"));
		contentPanel.getContent().add(
				new AddJsQueryBehavior("wrap", "<p class=\"lead\"></p>"));
		add(contentPanel);

	}

}