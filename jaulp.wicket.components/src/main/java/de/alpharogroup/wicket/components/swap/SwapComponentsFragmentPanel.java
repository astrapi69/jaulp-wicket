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
package de.alpharogroup.wicket.components.swap;

import org.apache.wicket.Component;
import org.apache.wicket.markup.html.panel.Fragment;
import org.apache.wicket.model.IModel;

/**
 * The abstract class SwapComponentsFragmentPanel provides the feature to swap components. The
 * components can be created with the abstract factory methods that have to be implemented.
 *
 * @param <T>
 *            the generic type of the model object.
 */
public abstract class SwapComponentsFragmentPanel<T> extends SwapFragmentPanel<T>
{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new swap components fragment panel.
	 *
	 * @param id
	 *            the id
	 * @param model
	 *            the model
	 */
	public SwapComponentsFragmentPanel(String id, IModel<T> model)
	{
		super(id, model);
	}

	/**
	 * Creates the fragment for the view.
	 *
	 * @param id
	 *            the id
	 * @return the fragment
	 */
	protected Fragment newViewFragment(final String id)
	{
		Fragment viewFragment = new Fragment(id, "view", this, getModel());
		viewFragment.setOutputMarkupPlaceholderTag(true);
		viewFragment.add(newViewComponent("viewComponent", getModel()));
		return viewFragment;
	}

	/**
	 * Abstract factory method for the new view component.
	 *
	 * @param id
	 *            the id
	 * @param model
	 *            the model
	 * @return the component
	 */
	protected abstract Component newViewComponent(String id, IModel<T> model);

	/**
	 * Creates the fragment to edit person.
	 *
	 * @param id
	 *            the id
	 * @return the fragment
	 */
	protected Fragment newEditFragment(final String id)
	{
		Fragment editFragment = new Fragment(id, "edit", this, getDefaultModel());
		editFragment.setOutputMarkupPlaceholderTag(true);
		editFragment.add(newEditComponent("editComponent", getModel()));
		return editFragment;
	}

	/**
	 * Abstract factory method for the new edit component.
	 *
	 * @param id
	 *            the id
	 * @param model
	 *            the model
	 * @return the component
	 */
	protected abstract Component newEditComponent(String id, IModel<T> model);

}
