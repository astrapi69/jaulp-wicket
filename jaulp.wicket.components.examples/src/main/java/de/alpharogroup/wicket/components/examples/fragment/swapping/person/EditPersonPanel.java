package de.alpharogroup.wicket.components.examples.fragment.swapping.person;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxFallbackButton;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;

public class EditPersonPanel extends Panel {

	/**
	 * The serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	public EditPersonPanel(String id, IModel<PersonModel> model) {
		super(id, model);
		setOutputMarkupId(true);
		setDefaultModel(model);
		Form<PersonModel> form = new Form<PersonModel>("editPersonForm");
		add(form);
		form.add(new TextField<String>("firstName").setOutputMarkupId(true));
		form.add(new TextField<String>("lastName").setOutputMarkupId(true));
		form.add(new TextField<String>("gender").setOutputMarkupId(true));
		form.add(new TextField<String>("age").setOutputMarkupId(true));		
		form.add(newSubmitButton("submit", form));
	}
	
	protected Component newSubmitButton(String id, Form<?> form) {
		return new AjaxFallbackButton(id, form) {
			private static final long serialVersionUID = 1L;			
			public void onSubmit(final AjaxRequestTarget target, final Form<?> form) {
				EditPersonPanel.this.onSubmit(target, form);
			}
		}.setOutputMarkupId(true);		
	}
	
	protected void onSubmit(final AjaxRequestTarget target, final Form<?> form) {		
	}

}
