package de.alpharogroup.wicket.components.examples.radios;

import java.util.Arrays;
import java.util.List;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormChoiceComponentUpdatingBehavior;
import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.RadioGroup;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.model.util.ListModel;
import org.jaulp.wicket.base.BasePanel;

import de.alpharogroup.wicket.components.examples.area.publicly.PubliclyBasePage;
import de.alpharogroup.wicket.components.form.RadioChoicesListView;
import de.alpharogroup.wicket.components.radio.RadioGroupModel;

public class RadioChoicesListViewExamplePanel extends BasePanel<Company> {
	private static final long serialVersionUID = 1L;
	
	public RadioChoicesListViewExamplePanel(String id, IModel<Company> model) {
		super(id, model);
		// Radio buttons must be part of a Form component.
		Form<?> form = new Form<>("form");
		add(form);
		final RadioGroupModel<Company> radioGroupModel = new RadioGroupModel<>();
		setModel(model);		
		// create list...
		List<Company> comps = Arrays.asList(
				Company.builder().name("Ferrari").build(), 
				Company.builder().name("Lamborgini").build(), 
				Company.builder().name("Mazerati").build(), 
				Company.builder().name("Porsche").build()
				);
		// we can set the selected radio from the start or leave it blank...
		//radioGroupModel.setSelected(comps.get(0));
		radioGroupModel.setRadios(comps);
		
		IModel<List<Company>> companies = new ListModel<Company>(comps);

		// Tell RadioChoicesListView what properties to use for label
		ChoiceRenderer<Company> renderer = new ChoiceRenderer<Company>("name");

		// Wire it all up!		
		final RadioGroup<Company> firstGroup = new RadioGroup<Company>("firstGroup", new PropertyModel<Company>(radioGroupModel, "selected"));
		firstGroup.add(new RadioChoicesListView<Company>("choice", companies, renderer));
		form.add(firstGroup);
		firstGroup.add(new AjaxFormChoiceComponentUpdatingBehavior() {
			private static final long serialVersionUID = 1L;
			protected void onUpdate(AjaxRequestTarget target) {
				target.add(getFeedback());
				info("Selected Type : " + radioGroupModel.getSelected());
			}
		});	

	}

	protected Component getFeedback() {
		PubliclyBasePage<?> basePage = (PubliclyBasePage<?>) getPage();
		return basePage.getFeedback();
	}

}
