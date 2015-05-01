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
package org.jaulp.wicket.base;

import lombok.Getter;
import de.alpharogroup.locale.ResourceBundleKey;

import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.IModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.jaulp.wicket.base.util.resource.ResourceModelFactory;

/**
 * The Class GenericBasePage.
 *
 * @param <T>
 *            the generic type
 */
public abstract class GenericBasePage<T> extends AbstractGenericBasePage<T>
{

	/**
	 * The serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;

	/** The meta tag content for description. */
	@Getter
	private IModel<String> description;

	/** The meta tag content for keywords. */
	@Getter
	private IModel<String> keywords;

	/** The meta tag content for the title. */
	@Getter
	private IModel<String> title;

	/**
	 * Instantiates a new GenericBasePage.
	 */
	public GenericBasePage()
	{
		this(new PageParameters());
	}

	/**
	 * Instantiates a new GenericBasePage.
	 *
	 * @param parameters
	 *            the parameters
	 */
	public GenericBasePage(final PageParameters parameters)
	{
		super(parameters);
	}

	/**
	 * Instantiates a new GenericBasePage.
	 *
	 * @param model
	 *            the model
	 */
	public GenericBasePage(IModel<T> model)
	{
		super(model);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void onInitialize()
	{
		super.onInitialize();
		// set content of the <title> tag
		addOrReplace(new Label("title", title = newTitle()));
		// set content attribute of the <meta name="keywords"> tag
		addOrReplace(new Label("keywords", "").add(new AttributeAppender("content",
			keywords = newKeywords(), " ")));
		// set content attribute of the <meta name="description"> tag
		addOrReplace(new Label("description", "").add(new AttributeAppender("content",
			description = newDescription(), " ")));
	}

	/**
	 * Factory method that can be overwritten for new meta tag content for description.
	 *
	 * @return the new <code>IModel</code>
	 */
	protected IModel<String> newDescription()
	{
		return ResourceModelFactory
			.newResourceModel(ResourceBundleKey.builder().key("page.meta.description")
				.defaultValue("").build(), this);
	}

	/**
	 * Factory method that can be overwritten for new meta tag content for keywords.
	 *
	 * @return the new <code>IModel</code>
	 */
	protected IModel<String> newKeywords()
	{
		return ResourceModelFactory.newResourceModel(
			ResourceBundleKey.builder().key("page.meta.keywords").defaultValue("").build(), this);
	}

	/**
	 * Factory method that can be overwritten for new meta tag content for the title.
	 *
	 * @return the new <code>IModel</code>
	 */
	protected IModel<String> newTitle()
	{
		return ResourceModelFactory.newResourceModel(ResourceBundleKey.builder().key("page.title")
			.defaultValue("Home page").build(), this);
	}
}
