package de.alpharogroup.wicket.components.examples.fragment.replacewith;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormChoiceComponentUpdatingBehavior;
import org.apache.wicket.ajax.markup.html.AjaxFallbackLink;
import org.apache.wicket.ajax.markup.html.form.AjaxFallbackButton;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.RadioChoice;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import de.alpharogroup.wicket.components.examples.fragment.swapping.person.EditPersonPanel;
import de.alpharogroup.wicket.components.examples.fragment.swapping.person.PersonModel;
import de.alpharogroup.wicket.components.examples.fragment.swapping.person.ViewPersonPanel;

public class ReplaceWithPanel extends Panel {

	/**
	 * The serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	private static final String PLACEHOLDER_ID = "placeholderComponent";
	private Component placeholderComponent;
	private Component viewComponent;
	private Component editComponent;
	private String selectedPanel = "View person";

	public ReplaceWithPanel(String id, final IModel<PersonModel> model) {
		super(id, model);
		setDefaultModel(model);
		viewComponent = newViewPersonPanel(PLACEHOLDER_ID, model);
		editComponent = newEditPersonPanel(PLACEHOLDER_ID, model);
		List<String> choices = new ArrayList<String>();
		choices.add("View person");
		choices.add("Edit person");
		final RadioChoice<String> radioChoice = new RadioChoice<String>("radioChoice");
		radioChoice.setModel(new Model<String>(selectedPanel));
		radioChoice.setChoices(choices);
		radioChoice.add(new AjaxFormChoiceComponentUpdatingBehavior() {
			private static final long serialVersionUID = 1L;
			@Override
			protected void onUpdate(AjaxRequestTarget target) {
				Component newComponent = null;
				if ("View person".equals(radioChoice.getModelObject())) {
					newComponent = viewComponent;
				} else {
					newComponent = editComponent;
				}
				newComponent.setOutputMarkupId(true);
				placeholderComponent.replaceWith(newComponent);
                target.add(newComponent);
                placeholderComponent = newComponent; 
			}	
		});
		placeholderComponent = newViewPersonPanel(PLACEHOLDER_ID, model);
		placeholderComponent.setOutputMarkupId(true);
		Form<String> form = new Form<String>("form");
		form.add(radioChoice);
		form.add(placeholderComponent);
		this.add(form);
	}
	
	protected Component newEditPersonPanel(String id, IModel<PersonModel> model) {
		return new EditPersonPanel(id, model) {
			private static final long serialVersionUID = 1L;
			protected Component newSubmitButton(String id, Form<?> form) {
				return new AjaxFallbackButton(id, form) {
					private static final long serialVersionUID = 1L;					
					protected void onConfigure() {
						super.onConfigure();
						setVisibilityAllowed(false);
					};
				}.setOutputMarkupId(true);		
			}
		};
	}

	protected Component newViewPersonPanel(String id, final IModel<PersonModel> model) {
		return new ViewPersonPanel(id, model){
			private static final long serialVersionUID = 1L;						
			protected AjaxFallbackLink<Object> newEditLink(String id) {
				return new AjaxFallbackLink<Object>(id) {
					private static final long serialVersionUID = 1L;
					@Override
					public void onClick(AjaxRequestTarget target) {						
					}
					protected void onConfigure() {
						super.onConfigure();
						setVisibilityAllowed(false);
					};
				};
			}
		};
	}

}
