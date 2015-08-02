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
package de.alpharogroup.wicket.components.examples.fragment.swapping;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxFallbackLink;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Fragment;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;

/**
 * The Class AddressPanel. Inspired from this blog:
 * http://pawelzubkiewicz.blogspot.de/2009/06/wicket-swapping-replacing-fragments.html
 */
public class AddressPanel extends Panel
{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The current fragment. */
	private Fragment currentFragment;

	/** The alternate fragment. */
	private Fragment alternateFragment;

	/**
	 * Instantiates a new address panel.
	 *
	 * @param id
	 *            the id
	 * @param address
	 *            the address
	 */
	public AddressPanel(final String id, final IModel<HomeAddress> address)
	{
		super(id, address);
		setDefaultModel(new CompoundPropertyModel<HomeAddress>(address));
		setOutputMarkupPlaceholderTag(true);

		currentFragment = createFragmentViewAddress();
		alternateFragment = createFragmentEditAddress();

		addCurrentFragment();
		addEditLink();
	}

	/**
	 * Adds the current fragment.
	 */
	private void addCurrentFragment()
	{
		add(currentFragment);
	}

	/**
	 * Adds the edit link.
	 */
	private void addEditLink()
	{
		add(new AjaxFallbackLink<Object>("addressEditLink")
		{
			private static final long serialVersionUID = 1L;

			@Override
			public void onClick(final AjaxRequestTarget target)
			{
				swapFragments();
				if (target != null)
				{
					target.add(currentFragment);
				}
			}
		});
	}

	/**
	 * Creates the address form.
	 *
	 * @return the component
	 */
	private Component createAddressForm()
	{
		final Form<HomeAddress> form = new Form<HomeAddress>("editAddressForm");
		form.add(new TextField<>("street"));
		form.add(new TextField<>("localNumber"));
		form.add(new TextField<>("city"));
		form.add(new TextField<>("code"));
		form.add(new AjaxButton("save", form)
		{
			private static final long serialVersionUID = 1L;

			@Override
			public void onSubmit(final AjaxRequestTarget target, final Form<?> form)
			{
				swapFragments();
				if (target != null)
				{
					target.add(currentFragment);
				}
			}
		});
		return form;
	}

	/**
	 * Creates the fragment edit address.
	 *
	 * @return the fragment
	 */
	private Fragment createFragmentEditAddress()
	{
		final Fragment editAddress = new Fragment("group", "edit", this, getDefaultModel());

		editAddress.setOutputMarkupPlaceholderTag(true);
		editAddress.add(createAddressForm());
		return editAddress;
	}

	/**
	 * Creates the fragment view address.
	 *
	 * @return the fragment
	 */
	private Fragment createFragmentViewAddress()
	{
		final Fragment viewAddress = new Fragment("group", "view", this, getDefaultModel());
		viewAddress.add(new Label("street"));
		viewAddress.add(new Label("localNumber"));
		viewAddress.add(new Label("city"));
		viewAddress.add(new Label("code"));
		viewAddress.setOutputMarkupPlaceholderTag(true);
		return viewAddress;
	}

	/**
	 * Swap fragments.
	 */
	protected void swapFragments()
	{
		final Fragment temp = currentFragment;
		currentFragment.replaceWith(alternateFragment);
		currentFragment = alternateFragment;
		alternateFragment = temp;
	}
}
