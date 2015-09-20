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
package de.alpharogroup.wicket.components.search;

import lombok.Getter;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;

import de.alpharogroup.wicket.base.BasePanel;
import de.alpharogroup.wicket.components.factory.ComponentFactory;

public abstract class SimpleSearchPanel extends BasePanel<SimpleSearchModel>
{

	/**
	 * The serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;

	/** The button label. */
	@Getter
	private final Label buttonLabel;

	/** The form. */
	@Getter
	private final Form<?> form;

	/** The searchtext. */
	@Getter
	private final Component searchtext;

	/** The search button. */
	@Getter
	private final Button searchButton;

	/**
	 * Instantiates a new {@link SimpleSearchPanel}.
	 *
	 * @param id
	 *            the id
	 */
	public SimpleSearchPanel(final String id)
	{
		super(id);
		setModel(new CompoundPropertyModel<>(new SimpleSearchModel()));
		add(form = newForm("form", getModel()));
		form.add(searchtext = newTextField("searchtext"));
		searchButton = newButton("searchButton");
		searchButton.add(buttonLabel = newButtonLabel("buttonLabel", "global.button.search",
			"Search"));
		form.add(searchButton);
	}

	/**
	 * Factory method for creating the new {@link Button}. This method is invoked in the constructor
	 * from the derived classes and can be overridden so users can provide their own version of a
	 * new {@link Button}.
	 *
	 * @param id
	 *            the id
	 * @return the new {@link Button}
	 */
	protected Button newButton(final String id)
	{
		return new Button(id)
		{
			/** The serialVersionUID. */
			private static final long serialVersionUID = 1L;

			@Override
			public void onSubmit()
			{
				onSearch(null);
			}
		};
	}

	/**
	 * Factory method for creating the new {@link Label} for the button. This method is invoked in
	 * the constructor from the derived classes and can be overridden so users can provide their own
	 * version of a new {@link Label} for the button.
	 * 
	 * @param id
	 *            the id
	 * @param resourceKey
	 *            the resource key
	 * @param defaultValue
	 *            the default value
	 * @return the new {@link Label} for the button.
	 */
	protected Label newButtonLabel(final String id, final String resourceKey,
		final String defaultValue)
	{
		return ComponentFactory.newLabel(id, resourceKey, defaultValue, this);
	}

	/**
	 * Factory method for create the new {@link Form}. This method is invoked in the constructor
	 * from the derived classes and can be overridden so users can provide their own version of a
	 * new {@link Form}.
	 *
	 * @param id
	 *            the id
	 * @param model
	 *            the model
	 * @return the new {@link Form}
	 */
	protected Form<?> newForm(final String id, final IModel<? extends SimpleSearchModel> model)
	{
		return ComponentFactory.newForm(id, model);
	}

	/**
	 * Factory method for create the new {@link TextField}. This method is invoked in the
	 * constructor from the derived classes and can be overridden so users can provide their own
	 * version of a new {@link TextField}.
	 *
	 * @param id
	 *            the id
	 * @return the new {@link TextField}
	 */
	protected Component newTextField(final String id)
	{
		return ComponentFactory.newTextField(id);
	}

	/**
	 * Abstract callback method that must be overwritten to provide specific action.
	 *
	 * @param target
	 *            the target
	 */
	protected abstract void onSearch(final AjaxRequestTarget target);
}