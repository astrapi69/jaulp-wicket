package de.alpharogroup.wicket.components.examples.resource.loading;

import org.apache.wicket.markup.html.basic.Label;

import de.alpharogroup.locale.ResourceBundleKey;
import de.alpharogroup.wicket.base.BasePanel;
import de.alpharogroup.wicket.base.util.resource.ResourceModelFactory;
import de.alpharogroup.wicket.components.examples.socialnet.SocialNetworkBean;

/**
 * Panel for examples how to load resources from IStringResourceLoader.
 */
public class ResourceLoadingExamplesPanel extends BasePanel<SocialNetworkBean>
{
	private static final long serialVersionUID = 1L;

	public ResourceLoadingExamplesPanel(String id)
	{
		super(id);
	}

	@Override
	protected void onInitialize()
	{
		super.onInitialize();
		// The resource is loaded from the BundleStringResourceLoader that are added in the init
		// method of application...
		// the key is in the resource bundle MessageSource.properties in this package.
		add(new Label("messageSourceLabel", ResourceModelFactory.newResourceModel(ResourceBundleKey
			.builder().key("foo.bar.bla").build())));
	}

}
