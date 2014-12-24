package de.alpharogroup.wicket.components.examples.radiochoices;

import java.util.Arrays;
import java.util.List;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormChoiceComponentUpdatingBehavior;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.Radio;
import org.apache.wicket.markup.html.form.RadioGroup;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.model.util.ListModel;
import org.jaulp.wicket.base.BasePanel;

import de.alpharogroup.wicket.components.examples.area.publicly.PubliclyBasePage;
import de.alpharogroup.wicket.components.factory.ComponentFactory;

public class RadioGroupPanel extends BasePanel<Company> {
	private static final long serialVersionUID = 1L;
	Company selected
//	= Company.builder().name("Ferrari").build()
	;
	public RadioGroupPanel(String id, IModel<Company> model) {
		super(id, model);
		// Radio buttons must be part of a Form component.
		Form<?> form = new Form<>("form");
		add(form);
		final RadioGroup<Company> group = new RadioGroup<Company>("group", new PropertyModel<Company>(this, "selected"));
		group.add(new AjaxFormChoiceComponentUpdatingBehavior() {
			private static final long serialVersionUID = 1L;
			protected void onUpdate(AjaxRequestTarget target) {
				target.add(getFeedback());
				info("Selected Type : " + selected.getName());
			}
		});
		form.add(group);

		// Exercise left for the reader: load the list of companies from the database.
		List<Company> comps = Arrays.asList(
				Company.builder().name("Ferrari").build(), 
				Company.builder().name("Lamborgini").build(), 
				Company.builder().name("Mazerati").build(), 
				Company.builder().name("Porsche").build()
				);
		IModel<List<Company>> companies = new ListModel<Company>(comps);

		// Construct a radio button and label for each company.
		group.add(new ListView<Company>("choice", companies) {
		    /**
			 * The serialVersionUID.
			 */
			private static final long serialVersionUID = 1L;

			protected void populateItem(ListItem<Company> it)
		    {
				Radio<Company> radio = new Radio<Company>("radio", it.getModel(), group);
				radio.setOutputMarkupId(true);
		        it.add(radio);
		        it.add(ComponentFactory.newLabel("label", radio.getMarkupId(), Model.of(it.getModelObject().getName())));
		    }
		});
	}

	protected Component getFeedback() {
		PubliclyBasePage<?> basePage = (PubliclyBasePage<?>) getPage();
		return basePage.getFeedback();
	}

}
