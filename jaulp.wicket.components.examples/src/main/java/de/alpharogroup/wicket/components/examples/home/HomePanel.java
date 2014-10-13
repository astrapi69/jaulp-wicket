package de.alpharogroup.wicket.components.examples.home;

import net.sourceforge.jaulp.locale.ResourceBundleKey;

import org.jaulp.wicket.base.BasePanel;
import org.jaulp.wicket.behaviors.AddJsQueryBehavior;

import de.alpharogroup.wicket.components.i18n.content.ContentPanel;

public class HomePanel extends BasePanel<Object> {
	private static final long serialVersionUID = 1L;

	public HomePanel(String id) {
		super(id);
		ContentPanel contentPanel = new ContentPanel("contentPanel", null) {
			private static final long serialVersionUID = 1L;

			@Override
			protected ResourceBundleKey newHeaderResourceKey() {
				return ResourceBundleKey.builder().key("home.header.label")
						.build();
			}

			@Override
			protected ResourceBundleKey newContentResourceKey() {
				return ResourceBundleKey.builder().key("home.content.label")
						.build();
			}
		};
		contentPanel.getHeader().add(
				new AddJsQueryBehavior("wrap", "<h1></h1>"));
		contentPanel.getContent().add(
				new AddJsQueryBehavior("wrap", "<p class=\"lead\"></p>"));
		add(contentPanel);

	}

}