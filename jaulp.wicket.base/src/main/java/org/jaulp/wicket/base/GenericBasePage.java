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

import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.StringResourceModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;

/**
 * The Class GenericBasePage.
 * 
 * @param <T>
 *            the generic type
 */
public abstract class GenericBasePage<T> extends AbstractGenericBasePage<T> {

	/**
	 * The serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;

	/** The meta tag content for description. */
	private IModel<String> description;

	/** The meta tag content for keywords. */
	private IModel<String> keywords;

	/** The meta tag content for the title. */
	private IModel<String> title;

	/**
	 * Instantiates a new GenericBasePage.
	 */
	public GenericBasePage() {
		this(new PageParameters());
	}

	/**
	 * Instantiates a new GenericBasePage.
	 * 
	 * @param parameters
	 *            the parameters
	 */
	public GenericBasePage(final PageParameters parameters) {
		super(parameters);
	}

	/**
	 * Instantiates a new GenericBasePage.
	 * 
	 * @param model
	 *            the model
	 */
	public GenericBasePage(IModel<T> model) {
		super(model);
	}

    /**
     * {@inheritDoc}
     */
	@Override
	protected void onBeforeRender() {
	    // set content of the <title> tag
		addOrReplace(new Label("title", title = newTitle()));
	    // set content attribute of the <meta name="keywords"> tag
		addOrReplace(new Label("keywords", "").add(new AttributeAppender("content", keywords = newKeywords(), " ")));
	    // set content attribute of the <meta name="description"> tag
		addOrReplace(new Label("description", "").add(new AttributeAppender("content", description = newDescription(), " ")));
	    super.onBeforeRender();
	}

	/**
	 * Gets the meta tag content for the description.
	 *
	 * @return the description
	 */
	public IModel<String> getDescription() {
		return description;
	}

	/**
	 * Gets the meta tag content for the keywords.
	 *
	 * @return the keywords
	 */
	public IModel<String> getKeywords() {
		return keywords;
	}

	/**
	 * Gets the meta tag content for the page title.
	 * 
	 * @return the page title
	 */
	public IModel<String> getTitle() {
		return title;
	}

	/**
	 * Factory method that can be overwritten for new meta tag content for
	 * description.
	 * 
	 * @return the new <code>IModel<code>
	 */
	protected IModel<String> newDescription() {
		return new StringResourceModel("page.meta.description", this, null, "");
	}

	/**
	 * Factory method that can be overwritten for new meta tag content for
	 * keywords.
	 * 
	 * @return the new <code>IModel<code>
	 */
	protected IModel<String> newKeywords() {
		return new StringResourceModel("page.meta.keywords", this, null, "");
	}

	/**
	 * Factory method that can be overwritten for new meta tag content for the title.
	 * 
	 * @return the new <code>IModel<code>
	 */
	protected IModel<String> newTitle() {
		return new StringResourceModel("page.title", this, null, "Home page");
	}

}