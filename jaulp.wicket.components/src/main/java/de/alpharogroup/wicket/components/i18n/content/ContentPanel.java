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
package de.alpharogroup.wicket.components.i18n.content;

import lombok.Getter;

import org.apache.wicket.Component;
import org.apache.wicket.model.IModel;

import de.alpharogroup.locale.ResourceBundleKey;
import de.alpharogroup.wicket.base.BasePanel;
import de.alpharogroup.wicket.base.util.resource.ResourceModelFactory;
import de.alpharogroup.wicket.components.factory.ComponentFactory;

/**
 * The Class ContentPanel is a container for a header and content area.
 */
public class ContentPanel extends BasePanel<ContentModel>
{

	/** The serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * The header.
	 */
	@Getter
	private final Component header;

	/**
	 * The content.
	 */
	@Getter
	private final Component content;

	/**
	 * Instantiates a new {@link ContentPanel}.
	 *
	 * @param id
	 *            the id
	 */
	public ContentPanel(final String id)
	{
		this(id, null);
	}

	/**
	 * Instantiates a new {@link ContentPanel}.
	 *
	 * @param id
	 *            the id
	 * @param model
	 *            the model
	 */
	public ContentPanel(final String id, final IModel<ContentModel> model)
	{
		super(id, model);
		setOutputMarkupId(true);
		if (model != null)
		{
			add(header = newHeaderLabel("header", ResourceModelFactory.newResourceModel(model
				.getObject().getHeaderResourceKey(), this)));
			add(content = newContentLabel("content", ResourceModelFactory.newResourceModel(model
				.getObject().getContentResourceKey(), this)));
		}
		else
		{
			add(header = newHeaderLabel("header", newHeaderModel()));
			add(content = newContentLabel("content", newContentModel()));
		}
	}

	/**
	 * New content label.
	 *
	 * @param id
	 *            the id
	 * @param model
	 *            the model
	 * @return the component
	 */
	protected Component newContentLabel(final String id, final IModel<String> model)
	{
		return ComponentFactory.newMultiLineLabel(id, model);
	}

	/**
	 * New content model.
	 *
	 * @return the i model
	 */
	protected IModel<String> newContentModel()
	{
		return ResourceModelFactory.newResourceModel(newContentResourceKey(), this);
	}

	/**
	 * New content resource key.
	 *
	 * @return the resource bundle key
	 */
	protected ResourceBundleKey newContentResourceKey()
	{
		return ResourceBundleKey.builder().key("content.label").build();
	}

	/**
	 * New header label.
	 *
	 * @param id
	 *            the id
	 * @param model
	 *            the model
	 * @return the component
	 */
	protected Component newHeaderLabel(final String id, final IModel<String> model)
	{
		return ComponentFactory.newLabel(id, model);
	}

	/**
	 * New header model.
	 *
	 * @return the i model
	 */
	protected IModel<String> newHeaderModel()
	{
		return ResourceModelFactory.newResourceModel(newHeaderResourceKey(), this);
	}

	/**
	 * New header resource key.
	 *
	 * @return the resource bundle key
	 */
	protected ResourceBundleKey newHeaderResourceKey()
	{
		return ResourceBundleKey.builder().key("header.label").build();
	}

}
