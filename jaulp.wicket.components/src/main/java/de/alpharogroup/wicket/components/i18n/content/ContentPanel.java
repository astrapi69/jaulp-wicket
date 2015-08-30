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
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.basic.MultiLineLabel;
import org.apache.wicket.model.IModel;

import de.alpharogroup.locale.ResourceBundleKey;
import de.alpharogroup.wicket.base.BasePanel;
import de.alpharogroup.wicket.base.util.resource.ResourceModelFactory;
import de.alpharogroup.wicket.components.factory.ComponentFactory;

/**
 * The Class ContentPanel is a container for a header and content area.
 */
public class ContentPanel extends BasePanel<ContentModelBean>
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
	public ContentPanel(final String id, final IModel<ContentModelBean> model)
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
	 * Factory method for create a new {@link MultiLineLabel}. This method is invoked in the
	 * constructor from the derived classes and can be overridden so users can provide their own
	 * version of a new {@link MultiLineLabel}.
	 *
	 * @param <T>
	 *            the generic type of the model
	 * @param id
	 *            the id
	 * @param model
	 *            the {@link IModel} of the {@link MultiLineLabel}.
	 * @return the new {@link MultiLineLabel}.
	 */
	protected Component newContentLabel(final String id, final IModel<String> model)
	{
		return ComponentFactory.newMultiLineLabel(id, model);
	}

	/**
	 * Factory method for create a new {@link IModel} for the content of the {@link MultiLineLabel}.
	 * This method is invoked in the constructor from the derived classes and can be overridden so
	 * users can provide their own version of a new {@link IModel} for the content of the
	 * {@link MultiLineLabel}. Note: this method is only invoked if model is null of this panel.
	 *
	 * @return the new {@link IModel}.
	 */
	protected IModel<String> newContentModel()
	{
		return ResourceModelFactory.newResourceModel(newContentResourceKey(), this);
	}

	/**
	 * Factory method for create a new {@link ResourceBundleKey} for the content of the
	 * {@link MultiLineLabel}. This method is invoked in the constructor from the derived classes
	 * and can be overridden so users can provide their own version of a new
	 * {@link ResourceBundleKey} for the content of the {@link MultiLineLabel}. Note: this method is
	 * only invoked if model is null of this panel.
	 *
	 * @return the new {@link IModel}.
	 */
	protected ResourceBundleKey newContentResourceKey()
	{
		return ResourceBundleKey.builder().key("content.label").build();
	}

	/**
	 * Factory method for creating the new {@link Label}. This method is invoked in the constructor
	 * from the derived classes and can be overridden so users can provide their own version of a
	 * new {@link Label}.
	 *
	 * @param id
	 *            the id
	 * @param model
	 *            the model
	 * @return the new {@link Label}
	 */
	protected Component newHeaderLabel(final String id, final IModel<String> model)
	{
		return ComponentFactory.newLabel(id, model);
	}

	/**
	 * Factory method for create a new {@link IModel} for the header of the {@link Label}. This
	 * method is invoked in the constructor from the derived classes and can be overridden so users
	 * can provide their own version of a new {@link IModel} for the content of the {@link Label}.
	 * Note: this method is only invoked if model is null of this panel.
	 *
	 * @return the new {@link IModel}.
	 */
	protected IModel<String> newHeaderModel()
	{
		return ResourceModelFactory.newResourceModel(newHeaderResourceKey(), this);
	}

	/**
	 * Factory method for create a new {@link ResourceBundleKey} for the header of the {@link Label}
	 * . This method is invoked in the constructor from the derived classes and can be overridden so
	 * users can provide their own version of a new {@link ResourceBundleKey} for the header of the
	 * {@link Label}. Note: this method is only invoked if model is null of this panel.
	 *
	 * @return the new {@link IModel}.
	 */
	protected ResourceBundleKey newHeaderResourceKey()
	{
		return ResourceBundleKey.builder().key("header.label").build();
	}

}
