package de.alpharogroup.wicket.components.examples.fragment.swapping.person;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxFallbackLink;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;

public class ViewPersonPanel extends Panel {

	/**
	 * The serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	public ViewPersonPanel(String id, IModel<PersonModel> model) {
		super(id, model);
		add(new AjaxFallbackLink<Object>("editLink") {
			private static final long serialVersionUID = 1L;
			@Override
			public void onClick(AjaxRequestTarget target) {
				ViewPersonPanel.this.onSubmit(target);
			}
		});
		add(new Label("firstName"));
		add(new Label("lastName"));
		add(new Label("gender"));
		add(new Label("age"));
	}
	
	protected void onSubmit(final AjaxRequestTarget target) {		
	}

}
