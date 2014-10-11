package de.alpharogroup.wicket.components.examples.fragment.swapping;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxFallbackLink;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
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
public class AddressPanel extends Panel {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The current fragment. */
	private Fragment currentFragment;
	
	/** The alternate fragment. */
	private Fragment alternateFragment;

	/**
	 * Instantiates a new address panel.
	 *
	 * @param id the id
	 * @param address the address
	 */
	public AddressPanel(String id, IModel<HomeAddress> address) {
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
	private void addCurrentFragment() {
		add(currentFragment);
	}

	/**
	 * Adds the edit link.
	 */
	private void addEditLink() {
		add(new AjaxFallbackLink<Object>("addressEditLink") {
			private static final long serialVersionUID = 1L;

			@Override
			public void onClick(AjaxRequestTarget target) {
				swapFragments();
				if (target != null) {
					target.add(currentFragment);
				}
			}
		});
	}

	/**
	 * Swap fragments.
	 */
	protected void swapFragments() {
		Fragment temp = currentFragment;
		currentFragment.replaceWith(alternateFragment);
		currentFragment = alternateFragment;
		alternateFragment = temp;
	}

	/**
	 * Creates the fragment view address.
	 *
	 * @return the fragment
	 */
	private Fragment createFragmentViewAddress() {
		Fragment viewAddress = new Fragment("group", "view", this,
				getDefaultModel());
		viewAddress.add(new Label("street"));
		viewAddress.add(new Label("localNumber"));
		viewAddress.add(new Label("city"));
		viewAddress.add(new Label("code"));
		viewAddress.setOutputMarkupPlaceholderTag(true);
		return viewAddress;
	}

	/**
	 * Creates the fragment edit address.
	 *
	 * @return the fragment
	 */
	private Fragment createFragmentEditAddress() {
		Fragment editAddress = new Fragment("group", "edit", this,
				getDefaultModel());

		editAddress.setOutputMarkupPlaceholderTag(true);
		editAddress.add(createAddressForm());
		return editAddress;
	}

	/**
	 * Creates the address form.
	 *
	 * @return the component
	 */
	private Component createAddressForm() {
		Form<HomeAddress> form = new Form<HomeAddress>("editAddressForm");
		form.add(new TextField<>("street"));
		form.add(new TextField<>("localNumber"));
		form.add(new TextField<>("city"));
		form.add(new TextField<>("code"));
		form.add(new Button("save") {
			private static final long serialVersionUID = 1L;
			@Override
			public void onSubmit() {
				swapFragments();
			}
		});
		return form;
	}
}
