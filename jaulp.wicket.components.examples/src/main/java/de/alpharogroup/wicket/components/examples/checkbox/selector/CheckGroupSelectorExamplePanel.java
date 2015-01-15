package de.alpharogroup.wicket.components.examples.checkbox.selector;

import java.util.Arrays;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import de.alpharogroup.wicket.components.examples.area.publicly.PubliclyBasePage;
import de.alpharogroup.wicket.components.examples.radios.Company;
import de.alpharogroup.wicket.components.form.checkbox.CheckGroupSelectorPanel;
import de.alpharogroup.wicket.components.form.checkbox.CheckboxModel;

public class CheckGroupSelectorExamplePanel extends Panel {

	/**
	 * The serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;

	public CheckGroupSelectorExamplePanel(String id, IModel<?> model) {
		super(id, model);
		final CheckboxModel<Company> checkboxModel = new CheckboxModel<Company>();
		checkboxModel.setChoices(Arrays.asList(
				Company.builder().name("Ferrari").build(), 
				Company.builder().name("Lamborgini").build(), 
				Company.builder().name("Mazerati").build(), 
				Company.builder().name("Porsche").build()
				));
		checkboxModel.setLabelPropertyExpression("name");
		IModel<CheckboxModel<Company>> checkGroupModel = Model.of(checkboxModel);
		add(new CheckGroupSelectorPanel<Company>("checkGroupSelectorPanel", checkGroupModel){
			/**
			 * The serialVersionUID.
			 */
			private static final long serialVersionUID = 1L;

			@Override
			protected void onUpdate(AjaxRequestTarget target) {
				target.add(getFeedback());
				info("Selected Types : " + checkboxModel.getSelectedItems());
			}
		});

	}

	protected Component getFeedback() {
		PubliclyBasePage<?> basePage = (PubliclyBasePage<?>) getPage();
		return basePage.getFeedback();
	}

}
