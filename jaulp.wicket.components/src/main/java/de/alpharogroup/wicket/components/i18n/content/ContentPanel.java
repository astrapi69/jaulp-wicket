package de.alpharogroup.wicket.components.i18n.content;

import net.sourceforge.jaulp.locale.ResourceBundleKey;

import org.apache.wicket.Component;
import org.apache.wicket.model.IModel;
import org.jaulp.wicket.base.BasePanel;
import org.jaulp.wicket.base.util.resource.ResourceModelFactory;

import de.alpharogroup.wicket.components.factory.ComponentFactory;

public class ContentPanel extends BasePanel<ContentModel> {

	private static final long serialVersionUID = 1L;

	private final Component header;
	private final Component content;

	public ContentPanel(String id) {
		this(id, null);
	}

	public ContentPanel(String id, IModel<ContentModel> model) {
		super(id, model);
		if (model != null) {
			add(header = newHeaderLabel("header",
					ResourceModelFactory.newResourceModel(model.getObject()
							.getHeaderResourceKey(), this)));
			add(content = newContentLabel("content",
					ResourceModelFactory.newResourceModel(model.getObject()
							.getContentResourceKey(), this)));
		} else {
			add(header = newHeaderLabel("header", newHeaderModel()));
			add(content = newContentLabel("content", newContentModel()));
		}
	}

	public Component getHeader() {
		return header;
	}

	public Component getContent() {
		return content;
	}

	protected Component newHeaderLabel(String id, IModel<String> model) {
		return ComponentFactory.newLabel(id, model);
	}

	protected IModel<String> newHeaderModel() {
		return ResourceModelFactory.newResourceModel(newHeaderResourceKey(), this);
	}
	
	protected ResourceBundleKey newHeaderResourceKey() {
		return ResourceBundleKey.builder().key("header.label").build();		
	}

	protected Component newContentLabel(String id, IModel<String> model) {
		return ComponentFactory.newMultiLineLabel(id, model);
	}

	protected IModel<String> newContentModel() {
		return ResourceModelFactory
				.newResourceModel(newContentResourceKey(), this);
	}
	
	protected ResourceBundleKey newContentResourceKey() {
		return ResourceBundleKey.builder().key("content.label").build();
	}

}
