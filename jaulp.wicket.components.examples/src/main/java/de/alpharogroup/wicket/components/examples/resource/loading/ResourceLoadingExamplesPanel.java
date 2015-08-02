/**
 * Copyright (C) 2010 Asterios Raptis
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package de.alpharogroup.wicket.components.examples.resource.loading;

import org.apache.wicket.markup.html.basic.Label;

import de.alpharogroup.locale.ResourceBundleKey;
import de.alpharogroup.wicket.base.BasePanel;
import de.alpharogroup.wicket.base.util.resource.ResourceModelFactory;

/**
 * Panel for examples how to load resources from IStringResourceLoader.
 */
public class ResourceLoadingExamplesPanel extends BasePanel<ResourceLoadingBean>
{
	private static final long serialVersionUID = 1L;

	public ResourceLoadingExamplesPanel(final String id)
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
