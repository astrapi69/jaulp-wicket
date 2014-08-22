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
package org.jaulp.wicket.components.swap;

import org.apache.wicket.markup.html.panel.Fragment;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;


/**
 * The abstract class SwapFragmentPanel holds to Fragment that can be swapped.
 *
 * @param <MODELOBJECT> the generic type of the model object.
 */
public abstract class SwapFragmentPanel<MODELOBJECT> extends Panel {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The view fragment. */
	protected Fragment view;
	
	/** The view fragment. */
	protected Fragment edit;
	
	/**
	 * Instantiates a new swap fragment panel.
	 *
	 * @param id the id
	 * @param model the model
	 */
	public SwapFragmentPanel(String id, IModel<MODELOBJECT> model) {
		super(id, model);
	}

	/**
	 * Swap the fragments.
	 */
	protected void swapFragments() {
		Fragment fragment = view;
		view.replaceWith(edit);
		view = edit;
		edit = fragment;
	}
	
	/**
	 * Factory method for the view fragment.
	 *
	 * @param id the id
	 * @return the view fragment
	 */
	protected abstract Fragment newFragmentView(final String id);
	

	/**
	 * Factory method for the edit fragment.
	 *
	 * @param id the id
	 * @return the edit fragment
	 */
	protected abstract Fragment newFragmentEdit(final String id);

}
