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
package de.alpharogroup.wicket.components.examples.fragment.swapping.person;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;

import de.alpharogroup.wicket.components.swap.SwapComponentsFragmentPanel;

public class PersonPanel extends SwapComponentsFragmentPanel<PersonBean>
{

	private static final long serialVersionUID = 1L;

	public PersonPanel(final String id, final IModel<PersonBean> model)
	{
		super(id, model);
		setDefaultModel(new CompoundPropertyModel<>(model));
	}

	@Override
	protected Component newEditComponent(final String id, final IModel<PersonBean> model)
	{
		return new EditPersonPanel(id, model)
		{
			private static final long serialVersionUID = 1L;

			@Override
			protected void onSubmit(final AjaxRequestTarget target, final Form<?> form)
			{
				swapFragments(target, form);
			}
		};
	}

	@Override
	protected Component newViewComponent(final String id, final IModel<PersonBean> model)
	{
		return new ViewPersonPanel(id, model)
		{
			private static final long serialVersionUID = 1L;

			@Override
			protected void onSubmit(final AjaxRequestTarget target)
			{
				swapFragments(target, null);
			}
		};
	}

}
