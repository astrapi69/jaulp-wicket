package de.alpharogroup.wicket.components.examples.radios;

import java.util.Arrays;
import java.util.List;

import lombok.Getter;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.resource.CssResourceReference;
import org.jaulp.wicket.base.BasePanel;

import de.alpharogroup.wicket.components.examples.area.publicly.PubliclyBasePage;
import de.alpharogroup.wicket.components.radio.AjaxRadioPanel;
import de.alpharogroup.wicket.components.radio.RadioGroupModel;

public class AjaxRadioExamplePanel extends BasePanel<List<Company>> {

	/**
	 * The serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;
	@Getter
	AjaxRadioPanel<Company> ajaxRadioPanel;

	public AjaxRadioExamplePanel(String id, IModel<List<Company>> model) {
		super(id, model);
		// create list...
		List<Company> items = Arrays.asList(Company.builder().name("Ferrari")
				.build(), Company.builder().name("Lamborgini").build(), Company
				.builder().name("Mazerati").build(),
				Company.builder().name("Porsche").build());
		final RadioGroupModel<Company> radioGroupModel = new RadioGroupModel<>();
		setModel(model);
		radioGroupModel.setLabelPropertyExpression("name");
		// we can set the selected radio from the start or leave it blank...
		// radioGroupModel.setSelected(items.get(0));
		radioGroupModel.setRadios(items);
		ajaxRadioPanel = new AjaxRadioPanel<Company>("ajaxRadioPanel",	Model.of(radioGroupModel)) {
			private static final long serialVersionUID = 1L;

			protected void onRadioSelect(AjaxRequestTarget target, Company newSelection) {
				info("You have selected " + newSelection.getName());
				target.add(getFeedback());
			}
		};
		add(ajaxRadioPanel);
	}

	protected Component getFeedback() {
		PubliclyBasePage<?> basePage = (PubliclyBasePage<?>) getPage();
		return basePage.getFeedback();
	}

	/**
	 * {@inheritDoc}
	 */
	public void renderHead(IHeaderResponse response) {
		super.renderHead(response);
		response.render(CssHeaderItem.forReference(new CssResourceReference(
				AjaxRadioExamplePanel.class, "AjaxRadioExamplePanel.css")));

	}
}
