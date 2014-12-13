package de.alpharogroup.wicket.components.examples.radiochoices;
import java.util.Arrays;
import java.util.List;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormChoiceComponentUpdatingBehavior;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.RadioChoice;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;

import de.alpharogroup.wicket.components.examples.area.publicly.PubliclyBasePage;

public class RadioChoicePanel extends Panel {

	/**
	 * The serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	private static final List<String> TYPES = Arrays
			.asList(new String[] { "Lamborgini", "Maserati", "Ferrari", "Porsche" });
	
	private String selected = "";
	public RadioChoicePanel(String id, IModel<?> model) {
		super(id, model);
		RadioChoice<String> brandingType = new RadioChoice<String>(
				"branding", new PropertyModel<String>(this, "selected"), TYPES);
		brandingType.setOutputMarkupId(true);
		brandingType.add(new AjaxFormChoiceComponentUpdatingBehavior() {
			private static final long serialVersionUID = 1L;

	        @Override 
	        protected void onUpdate(AjaxRequestTarget target) { 
				target.add(getFeedback()); 
				info("Selected Type : " + selected);
	        } 
		});
		Form<?> form = new Form<Void>("form");
 
		add(form);
		form.add(brandingType);
	}
	
	protected Component getFeedback() {
		PubliclyBasePage<?> basePage = (PubliclyBasePage<?>) getPage();
		return basePage.getFeedback();		
	}
}
